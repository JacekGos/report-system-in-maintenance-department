<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	
<html>
<head>

<title>Production details</title>

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
					<h1 class="h2">Dane linii</h1>
				</div>
				<div class="row">
					<aside class="col-sm-3">
						<h2>Linia: ${productionLine.name}</h2>
						<c:url var="detailsLink"
							value="/production/showUpdateProdLineForm">
							<c:param name="id" value="${prodLineId}" />
						</c:url>
						<a class="btn btn-outline-primary" href="${detailsLink}"
							role="button"> Edycja</a>
					</aside>
					<aside class="col-sm-6"></aside>
					<aside class="col-sm-4"></aside>
				</div>
				<div class="row">
					<aside class="col-sm-4"></aside>
					<aside class="col-sm-4">
						<div class="table-responsive">
							<table
								class="table table-striped table-sm table-bordered table-hover">
								<thead class="table-dark">
									<tr>
										<th scope="col" class="col-sm-5">Maszyna</th>
										<th scope="col" class="col-sm-3">Opcje</th>
									</tr>
								</thead>
								<tbody>
									<c:if test="${!productionMachines.isEmpty()}">
										<c:forEach var="machine" items="${productionMachines}">
											<tr>
												<c:url var="detailsLink"
													value="/production/showUpdateProdMachineForm">
													<c:param name="id" value="${machine.id}" />
												</c:url>
												<td>${machine.name}</td>
												<td><a href="${detailsLink}"><i
														class="bi bi-search"></i></a></td>
											</tr>
										</c:forEach>
									</c:if>
								</tbody>
							</table>
						</div>
					</aside>
					<aside class="col-sm-4"></aside>
				</div>
			</main>
		</div>
	</div>
	
</body>
</html>