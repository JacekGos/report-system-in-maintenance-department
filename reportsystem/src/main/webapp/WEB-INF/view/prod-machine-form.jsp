<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!doctype html>
<html lang="en">

<head>

<title>Register New User Form</title>

<%@ include file="/WEB-INF/view/sources.jsp"%>

</head>

<body>

	<%@ include file="/WEB-INF/view/navigation-bar.jsp"%>

	<div class="container-fluid">
		<div class="row">

			<%@ include file="/WEB-INF/view/side-bar-menu.jsp"%>

			<main class="col-md-9 ms-sm-auto col-lg-10 px-md-4">
				<div
					class="d-flex justify-content-between flex-wrap flex-md-nowrap align-items-center pt-3 pb-2 mb-3 border-bottom">
					<h1 class="h2">Dodaj stacje</h1>
					<div class="btn-toolbar mb-2 mb-md-0">
						<div class="btn-group me-2">
							<button type="button" class="btn btn-sm btn-outline-secondary">Share</button>
							<button type="button" class="btn btn-sm btn-outline-secondary">Export</button>
						</div>
						<button type="button"
							class="btn btn-sm btn-outline-secondary dropdown-toggle">
							<span data-feather="calendar"></span> This week
						</button>
					</div>
				</div>


				<div class="row">
					<aside class="col-sm-4"></aside>
					<aside class="col-sm-7">
						<form:form class="row g-3"
							action="${pageContext.request.contextPath}/production/processProdMachineForm"
							modelAttribute="productionMachineDto" accept-charset="UTF-8">

							<div class="col-md-4">

								<form:hidden path="id" />
								<form:hidden path="prodLineName" />

								<label class="form-label">Nazwa stacji</label>
								<form:input path="name" placeholder="Nazwa" class="form-control" />

								<form:errors path="name" cssClass="error" />

								<div class="col-md-4">

									<label class="form-label">Linia</label>
									<c:if test="${prodLineName != null}">

										<input type="text" placeholder="${prodLineName}"
											class="form-control" disabled readonly>

									</c:if>
					
									<form:select path="prodLineId" items="${prodLines}"
										class="form-select" />
								</div>

							</div>
							
							<div class="col-12">
								<button type="submit" class="btn btn-outline-primary">Zatwierdź</button>
							</div>

						</form:form>

						<c:if test="${errorMessage != null}">
							<div
								class="alert alert-danger col-sm-4 col-xs-offset-4 col-xs-10">
								${errorMessage}</div>
						</c:if>

					</aside>
					<aside class="col-sm-4"></aside>
				</div>
			</main>
		</div>
	</div>
</body>
</html>