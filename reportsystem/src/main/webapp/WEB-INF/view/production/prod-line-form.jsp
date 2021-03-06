<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!doctype html>

<html lang="en">

<head>

<title>Add production line</title>
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
					<h1 class="h2">Dodaj linie</h1>
				</div>
				<div class="row">
					<aside class="col-sm-4"></aside>
					<aside class="col-sm-6">
						<form:form class="row g-3"
							action="${pageContext.request.contextPath}/production/processProdLineForm"
							modelAttribute="productionLineDto" accept-charset="UTF-8">
							<div class="col-md-4">
								<form:hidden path="id" />
								<label class="form-label">Nazwa linii</label>
								<form:input path="name" placeholder="Nazwa" class="form-control" />
								<form:errors path="name" cssClass="error" />
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