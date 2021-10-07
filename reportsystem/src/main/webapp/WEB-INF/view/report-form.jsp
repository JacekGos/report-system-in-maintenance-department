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

<script src="jquery-1.11.3.js"></script>

<script>

	var pathname = ${pageContext.request.contextPath} + "/report/loadProdMachines?"; // Returns path only (/path/example.html)
	var url      = window.location.href;     // Returns full URL (https://example.com/path/example.html)
	var origin   = window.location.origin;   // Returns base URL (https://example.com)

	$("#first-choice").change(function() {
		   $("#second-choice").load("/report/loadProdMachines?") + $("#first-choice").val());
// 			$.get("home.jsp");
		});
	
</script>

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
							<%-- 					<form class="needs-validation" novalidate> --%>
							<form:form class="row g-3"
								action="${pageContext.request.contextPath}/report/processReportForm"
								modelAttribute="reportDto" accept-charset="UTF-8">

								<div class="row g-3">

									<%-- <div class="col-md-6">
										<label class="form-label">Linia</label>
										<form:select id="first-choice" path="productionLineId"
											items="${prodLines}" class="form-select" />
									</div>

									<div class="col-md-6">
										<label class="form-label">Stacja</label>
										<form:select id="second-choice" path="productionMachineId"
											items="${prodMachines}" class="form-select" />
									</div> --%>

									<select id="first-choice">
										<option selected value="base">Please Select</option>
										<option value="beverages">Beverages</option>
										<option value="snacks">Snacks</option>
									</select> <br> <select id="second-choice">
										<option>Please choose from above</option>
									</select>

									<div class="col-sm-6">
										<label for="exampleFormControlTextarea1" class="form-label">Data</label>
										<div class="input-append date" id="dp3" data-date="12-02-2012"
											data-date-format="dd-mm-yyyy">
											<form:input path="date" class="form-control" size="16"
												type="text" value="${reportDto.date}"
												placeholder="RRRR-MM-DD" />
											<span class="add-on"><i class="icon-th"></i></span>
										</div>
									</div>

									<div class="col-sm-6">
										<label class="form-label">Czas trwania</label>
										<form:input path="duration" placeholder="minuty"
											class="form-control" />
										<form:errors path="duration" cssClass="error" />
									</div>

									<form:checkboxes path="failTypes"
										element="span class='checkbox'" items="${failTypes}" />
									<%-- <div class="form-check form-check-inline">
										<form:input path="failType" class="form-check-input" type="checkbox"
											id="inlineCheckbox1" value="option1"/> <label
											class="form-check-label" for="inlineCheckbox1">1</label>
									</div> --%>

									<div class="mb-3">
										<label for="exampleFormControlTextarea1" class="form-label">Opis</label>
										<form:textarea class="form-control" path="description"
											id="exampleFormControlTextarea1" rows="5"></form:textarea>
									</div>



									<%-- <div class="col-sm-6">
										<label class="form-label">Imię</label>
										<form:input path="firstName" placeholder="Imię"
											class="form-control" />
										<form:errors path="firstName" cssClass="error" />
									</div>

									<div class="col-sm-6">
										<label class="form-label">Nazwisko</label>
										<form:input path="lastName" placeholder="Nazwisko"
											class="form-control" />
										<form:errors path="lastName" cssClass="error" />
									</div>

									<div class="col-12">
										<label class="form-label">Email</label>
										<form:input path="email" placeholder="name@example.com"
											class="form-control" />
										<form:errors path="email" cssClass="error" />
									</div>
 --%>


								</div>

								<div class="col-12">
									<button type="submit" class="btn btn-outline-primary">Zatwierdź</button>
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