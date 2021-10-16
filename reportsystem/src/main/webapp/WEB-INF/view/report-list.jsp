<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<html>

<head>
<title>Home Page</title>

<%@ include file="/WEB-INF/view/sources.jsp"%>

<style>
.modal fade {
	width: 2000px;
}
</style>

</head>
<body>

	<%@ include file="/WEB-INF/view/navigation-bar.jsp"%>

	<div class="container-fluid">
		<div class="row">

			<%@ include file="/WEB-INF/view/side-bar-menu.jsp"%>

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

										<%-- <form:form class="row g-3"
											action="${pageContext.request.contextPath}/user/setUserRole"
											modelAttribute="userDto" accept-charset="UTF-8">

											<form:hidden path="id" />
											<form:hidden path="firstName" />
											<form:hidden path="lastName" />
											<form:hidden path="password" />
											<form:hidden path="userName" />
											<form:hidden path="email" />
											<form:hidden path="enabled" />
											<form:hidden path="credentialsNonExpired" />
											<form:hidden path="nonExpired" />
											<form:hidden path="nonLocked" />

											<div class="col-md-5">
												<label class="form-label">Uprawnienia</label> <input
													type="text" placeholder="${userDto.role}"
													class="form-control" disabled readonly>

												<form:select path="role" items="${roles}"
													class="form-select" />
												<br>
												<button type="submit" class="btn btn-outline-primary">
													Zapisz zmiany</button>

											</div>

										</form:form> --%>

									</div>
									<div class="modal-footer">
										<button type="button" class="btn btn-secondary"
											data-bs-dismiss="modal">Zamknij</button>

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