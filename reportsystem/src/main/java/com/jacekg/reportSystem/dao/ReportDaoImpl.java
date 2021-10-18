package com.jacekg.reportSystem.dao;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Order;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.jacekg.reportSystem.dto.SearchReportDto;
import com.jacekg.reportSystem.entity.Report;

@Repository
public class ReportDaoImpl implements ReportDao {
	
	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public void saveReport(Report report) {
		
		Session currentSession = sessionFactory.getCurrentSession();
		
		currentSession.saveOrUpdate(report);
	}

	@Override
	public List<Report> getReportsToShowList() {
		
		Session currentSession = sessionFactory.getCurrentSession();
		
		Query<Report> query = 
				currentSession.createQuery("FROM Report r"
						+ " JOIN FETCH r.user"
						+ " JOIN FETCH r.productionLine"
						+ " JOIN FETCH r.productionMachine ORDER BY r.date DESC", Report.class).setMaxResults(20);
		
		return query.getResultList();
	}

	@Override
	public Report getReport(Long reportId) {
		
		Session currentSession = sessionFactory.getCurrentSession();
		
		Report report = currentSession.get(Report.class, reportId);
		
		return report;
	}

	@Override
	public List<Report> searchReports(SearchReportDto searchReportDto, Long userId) {
		
		Session currentSession = sessionFactory.getCurrentSession();
		
		Integer productionMachineId = searchReportDto.getProductionMachineId();
		LocalDate startDate = searchReportDto.getStartDate();
		LocalDate endDate = searchReportDto.getEndDate();
		String keyWord = searchReportDto.getKeyWord();
		
		List<Predicate> predicateList = new ArrayList<Predicate>();
		
		CriteriaBuilder criteriaBuilder = currentSession.getCriteriaBuilder();
		CriteriaQuery<Report> criteriaQuery = criteriaBuilder.createQuery(Report.class);
		Root<Report> reportObject = criteriaQuery.from(Report.class);
		
		reportObject.fetch("user", JoinType.LEFT);
		reportObject.fetch("productionLine", JoinType.LEFT);
		reportObject.fetch("productionMachine", JoinType.LEFT);
		
		if (userId != null) {
			Predicate predicate = criteriaBuilder.equal(reportObject.get("user").get("id"), userId);
			predicateList.add(predicate);
		}
		if (productionMachineId != -1) {
			Predicate predicate = criteriaBuilder.equal(reportObject.get("productionMachine").get("id"), productionMachineId);
			predicateList.add(predicate);
		}
		if (startDate != null && endDate != null) {
			Predicate predicate = criteriaBuilder.between(reportObject.get("date"), startDate, endDate);
			predicateList.add(predicate);
		}
		if (keyWord != null) {
			Predicate predicate = criteriaBuilder.like(reportObject.get("description"), "%" + keyWord + "%");
			predicateList.add(predicate);
		}
		
		if (predicateList.size() > 0) {
			Predicate finalPredicate = criteriaBuilder.and(predicateList.toArray(new Predicate[0]));
			criteriaQuery.select(reportObject).where(finalPredicate);
		} else {
			criteriaQuery.select(reportObject);
		}
		
		List<Order> orderList = new ArrayList();
		orderList.add(criteriaBuilder.desc(reportObject.get("date")));
		
		criteriaQuery.orderBy(orderList);
		
	    TypedQuery<Report> query = currentSession.createQuery(criteriaQuery).setFirstResult(0).setMaxResults(20);
	    
		List<Report> list = query.getResultList();
		
		return list;
		
	}

}







