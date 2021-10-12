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
							<form:form class="row g-3" 
								action="${pageContext.request.contextPath}/report/processReportForm2?${_csrf.parameterName}=${_csrf.token}"
								enctype="multipart/form-data" accept-charset="UTF-8"
								method="post" modelAttribute="imageDto">

								<div class="col-sm-6">
									<label class="form-label"><h6>nazwa</h6></label>
									<form:input path="name" placeholder="nazwa"
										class="form-control" />
									<form:errors path="name" cssClass="error" />
								</div>


								<div class="row g-3">

									<div class="col-sm-5">
										<form:input path="image" class="form-control" type="file"
											id="file-upload" accept="image/png, image/jpeg" />
									</div>

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


							<%-- 
							<form method="post" enctype="multipart/form-data" accept="image/png, image/jpeg"
								action="${pageContext.request.contextPath}/report/processReportForm2?${_csrf.parameterName}=${_csrf.token}">
								<table>
									<tr>
										<td>File to upload:</td>
										<td><input type="file" name="file" /></td>
									</tr>
									<tr>
										<td></td>
										<td><input type="submit" value="Upload" /></td>
									</tr>
								</table>
								
								<input type="hidden" name="${_csrf.parameterName}"
                                               value="${_csrf.token}" />
							</form> --%>

						</div>
					</aside>
					<aside class="col-sm-4"></aside>
				</div>
			</main>
			<!-- 			https://www.baeldung.com/spring-file-upload -->
		</div>
	</div>
</body>
</html>