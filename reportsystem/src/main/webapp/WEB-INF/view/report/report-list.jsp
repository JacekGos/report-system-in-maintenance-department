<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	
<html>
<head>

<title>Report history</title>
<%@ include file="/WEB-INF/view/navigation/sources.jsp"%>

</head>

<body>

	<%@ include file="/WEB-INF/view/navigation/navigation-bar.jsp"%>
	
	<div class="container-fluid">
		<div class="row">
			<%@ include file="/WEB-INF/view/navigation/side-bar-menu.jsp"%>
			<main class="col-md-9 ms-sm-auto col-lg-10 px-md-4">
				<div
					class="d-flex justify-content-between flex-wrap flex-md-nowrap align-items-center pt-3 pb-2 mb-3 border-bottom">
					<h1 class="h2">Historia raportów</h1>
					<div class="btn-toolbar mb-2 mb-md-0">
						<div class="btn-group me-2">
							<button type="button" class="btn btn-sm btn-outline-secondary"
								data-bs-toggle="modal" data-bs-target="#exampleModal">
								Wyszukaj</button>
						</div>
						<div class="btn-group me-2">
							<button type="submit" class="btn btn-sm btn-outline-secondary"
								form="summaryForm">Podsumowanie</button>
						</div>
						<div class="modal fade" id="exampleModal" tabindex="-1"
							aria-labelledby="exampleModalLabel" aria-hidden="true">
							<div class="modal-dialog modal-lg">
								<div class="modal-content">
									<div class="modal-header">
										<h5 class="modal-title" id="exampleModalLabel">Opcje
											wyszukiwania</h5>
										<button type="button" class="btn-close"
											data-bs-dismiss="modal" aria-label="Close"></button>
									</div>
									<div class="modal-body">
										<form:form class="row g-3"
											action="${pageContext.request.contextPath}/report/searchReport"
											modelAttribute="searchReportDto" accept-charset="UTF-8">
											<div class="col-sm-6">
												<label class="form-label"><h6>Użytkownik</h6></label>
												<form:input path="userName" placeholder="Nazwa użytkownika"
													class="form-control" />
												<form:errors path="userName" cssClass="error" />
											</div>
											<div class="col-md-6">
												<label class="form-label"><h6>Stacja</h6></label>
												<form:select id="second-choice" path="productionMachineId"
													items="${prodMachines}" class="form-select" />
											</div>
											<div class="col-sm-6">
												<label for="exampleFormControlTextarea1" class="form-label"><h6>Data
														początkowa</h6></label>
												<div class="input-append date" id="dp3"
													data-date-format="dd-mm-yyyy">
													<form:input path="startDate" class="form-control" size="16"
														type="date" placeholder="DD-MM-RRRR" />
													<span class="add-on"><i class="icon-th"></i></span>
												</div>
												<form:errors path="startDate" cssClass="error" />
											</div>
											<div class="col-sm-6">
												<label for="exampleFormControlTextarea1" class="form-label"><h6>Data
														końcowa</h6></label>
												<div class="input-append date" id="dp3"
													data-date-format="dd-mm-yyyy">
													<form:input path="endDate" class="form-control" size="16"
														type="date" placeholder="DD-MM-RRRR" />
													<span class="add-on"><i class="icon-th"></i></span>
												</div>
												<form:errors path="endDate" cssClass="error" />
											</div>
											<div class="col-sm-6">
												<label class="form-label"><h6>Słowa kluczowe</h6></label>
												<form:input path="keyWord" placeholder="(max 100 znaków)"
													class="form-control" />
												<form:errors path="keyWord" cssClass="error" />
											</div>
											<div class="modal-footer">
												<button type="submit" class="btn btn-outline-primary">
													Wyszukaj</button>
												<button type="button" class="btn btn-secondary"
													data-bs-dismiss="modal">Zamknij</button>
											</div>
										</form:form>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
				<div class="row">
					<div class="table-responsive">
						<table
							class="table table-striped table-sm table-bordered table-hover">
							<thead class="table-dark">
								<tr>
									<th scope="col">#</th>
									<th scope="col">Nazwa użytkownika</th>
									<th scope="col">Linia</th>
									<th scope="col">Stacja</th>
									<th scope="col">Data</th>
									<th scope="col">Opcje</th>
								</tr>
							</thead>
							<tbody>
								<form:form
									action="${pageContext.request.contextPath}/report/showReportsSummary"
									modelAttribute="selectedReports" id="summaryForm" method="GET">
									<c:if test="${reportsList != null}">
										<c:forEach var="report" items="${reportsList}">
											<tr>
												<c:url var="detailsLink" value="/report/showReportDetails">
													<c:param name="id" value="${report.id}" />
												</c:url>
												<td>${report.id}</td>
												<td>${report.userName}</td>
												<td>${report.productionLineName}</td>
												<td>${report.productionMachineName}</td>
												<td>${report.date}</td>
												<td><a href="${detailsLink}"><i
														class="bi bi-search"></i></a> &emsp; <form:checkbox
														path="selectedReportsId" value="${report.id}" /></td>
											</tr>
										</c:forEach>
									</c:if>
								</form:form>
							</tbody>
						</table>
					</div>
				</div>
			</main>
		</div>
	</div>
	
</body>
</html>