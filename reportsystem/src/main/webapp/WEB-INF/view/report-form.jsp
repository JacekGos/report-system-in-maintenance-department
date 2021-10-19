<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!doctype html>
<html lang="en">

<head>

<title>Register New User Form</title>

<%@ include file="/WEB-INF/view/sources.jsp"%>

<link type="text/css" rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/form-validation.css" />

</head>

<body>

	<%@ include file="/WEB-INF/view/navigation-bar.jsp"%>

	<div class="container-fluid">
		<div class="row">

			<%@ include file="/WEB-INF/view/side-bar-menu.jsp"%>

			<main class="col-md-9 ms-sm-auto col-lg-10 px-md-4">

				<div
					class="d-flex justify-content-between flex-wrap flex-md-nowrap align-items-center pt-3 pb-2 mb-3 border-bottom">
					<h1 class="h2">Dodaj raport</h1>

				</div>

				<div class="row">
					<aside class="col-sm-3"></aside>
					<aside class="col-sm-8">

						<div class="col-md-7 col-lg-8">
							<form:form class="row g-3" enctype="multipart/form-data"
								action="${pageContext.request.contextPath}/report/processReportForm?${_csrf.parameterName}=${_csrf.token}"
								modelAttribute="reportDto" accept-charset="UTF-8">

								<form:hidden path="userId" />

								<div class="row g-3">

									<!-- 									Needed for modifying drop-down lists -->
									<%-- <div class="col-md-6">
										<label class="form-label">Linia</label>
										<form:select id="first-choice" path="productionLineId"
											items="${prodLines}" class="form-select" />
									</div> --%>

									<div class="col-md-6">
										<label class="form-label"><h6>Stacja</h6></label>
										<form:select id="second-choice" path="productionMachineId"
											items="${prodMachines}" class="form-select" />
										<form:errors path="productionMachineId" cssClass="error" />
									</div>
									<br>

									<div class="col-sm-6">
										<label for="exampleFormControlTextarea1" class="form-label"><h6>Data</h6></label>
										<div class="input-append date" id="dp3"
											data-date-format="dd-mm-yyyy">
											<form:input path="date" class="form-control" size="16"
												type="date" value="${reportDto.date}"
												placeholder="DD-MM-RRRR" />
											<span class="add-on"><i class="icon-th"></i></span>
										</div>
										<form:errors path="date" cssClass="error" />
									</div>


									<div class="col-sm-6">
										<label class="form-label"><h6>Czas trwania</h6></label>
										<form:input path="duration" placeholder="minuty"
											class="form-control" />
										<form:errors path="duration" cssClass="error" />
									</div>

									<label class="form-label"><h6>Przyczyna</h6></label>
									<form:checkboxes path="failTypes"
										element="span class='checkbox'" items="${failTypes}" />
									<form:errors path="failTypes" cssClass="error" />

									<div class="mb-3">
										<label for="exampleFormControlTextarea1" class="form-label"><h6>Opis</h6></label>
										<form:textarea class="form-control" path="description"
											id="exampleFormControlTextarea1" rows="5"></form:textarea>
									<form:errors path="description" cssClass="error" />
									</div>

									<form:label path="images"></form:label>
									<input type="file" name="images" class="form-control"
										accept="image/png, image/jpeg, image/png" />
									<!--accept="image/png, image/jpeg, image/png" multiple /></td> -->

									<c:if test="${ (message != null) and (message != '') }">
										<div class="alert alert-danger col-xs-offset-1 col-xs-10">
											${message}</div>
									</c:if>

								</div>
								<div class="col-12">
									<button type="button" class="btn btn-outline-primary"
										data-bs-toggle="modal" data-bs-target="#submitModal">
										Zatwierdź</button>
								</div>

								<div class="modal fade" id="submitModal" tabindex="-1"
									aria-labelledby="exampleModalLabel" aria-hidden="true">
									<div class="modal-dialog">
										<div class="modal-content">
											<div class="modal-header">
												<button type="button" class="btn-close"
													data-bs-dismiss="modal" aria-label="Close"></button>
											</div>
											<div class="modal-body">Jesteś pewny?</div>
											<div class="modal-footer">
												<button type="button" class="btn btn-secondary"
													data-bs-dismiss="modal">Close</button>
												<button type="submit" class="btn btn-outline-primary">Zatwierdź</button>
											</div>
										</div>
									</div>
								</div>
							</form:form>
						</div>
					</aside>
					<aside class="col-sm-4"></aside>
				</div>
			</main>

		</div>
	</div>
</body>
</html>