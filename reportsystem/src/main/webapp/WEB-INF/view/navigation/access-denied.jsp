<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>  
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>

<html>

<head>

	<title>Access denied</title>
	
	<%@ include file="/WEB-INF/view/navigation/sources.jsp"%>

</head>
<body>

	<%@ include file="/WEB-INF/view/navigation/navigation-bar.jsp"%>

	<div class="container-fluid">
		<div class="row">
			<main class="col-md-9 ms-sm-auto col-lg-10 px-md-4">
				<div
					class="d-flex justify-content-between flex-wrap flex-md-nowrap align-items-center pt-3 pb-2 mb-3 border-bottom">
					<h1 class="h2">Brak dostępu</h1>
					<div class="btn-toolbar mb-2 mb-md-0">
					</div>
				</div>
				<h5>Nie posiadasz wystarczających uprawnień</h5>
			</main>
		</div>
	</div>

</body>
</html>