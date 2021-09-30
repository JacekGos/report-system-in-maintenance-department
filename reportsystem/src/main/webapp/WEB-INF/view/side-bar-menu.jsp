<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
	
</head>
<body>
	
<!-- 	<div class="test"> -->

	<nav id="sidebarMenu"
		class="col-md-3 col-lg-2 d-md-block bg-light sidebar collapse">
		<div class="position-sticky pt-3">
			<ul class="nav flex-column">
				<h5 class="sidebar-heading d-flex justify-content-between align-items-center px-3 mt-4 mb-1 text-muted ">
					<span>Raporty</span> 
				</h5>

				<li class="nav-item"><a class="nav-link active"
					aria-current="page" href="${pageContext.request.contextPath}/report/showAddReportForm"> 
					<span data-feather="home"></span>
						<i class="bi bi-receipt"></i> Dodaj raport
				</a></li>

				<li class="nav-item"><a class="nav-link active"
					aria-current="page" href="${pageContext.request.contextPath}/report/showReportList"> 
					<span data-feather="home"></span>
						<i class="bi bi-calendar2-minus"></i> Historia raportów
				</a></li>

				<security:authorize access="hasAnyRole('MANAGER', 'ADMIN')">

					<h5
						class="sidebar-heading d-flex justify-content-between align-items-center px-3 mt-4 mb-1 text-muted ">
						<span>Użytkownicy</span>
					</h5>

					<li class="nav-item"><a class="nav-link active"
						aria-current="page"
						href="${pageContext.request.contextPath}/user/showAddUserForm"> <span
							data-feather="home"></span><i class="bi bi-person-plus-fill"></i> Dodaj użytkownika
					</a></li>
					<li class="nav-item"><a class="nav-link active"
						aria-current="page"
						href="${pageContext.request.contextPath}/user/showUsersList"> <span
							data-feather="home"></span> <i class="bi bi-person-lines-fill"></i> Dane użytkowników
					</a></li>
					
					<h5
						class="sidebar-heading d-flex justify-content-betwe	en align-items-center px-3 mt-4 mb-1 text-muted ">
						<span>Produkcja</span>
					</h5>

					<li class="nav-item"><a class="nav-link active"
						aria-current="page"
						href="${pageContext.request.contextPath}/production/showAddProdLineForm"> <span
							data-feather="home"></span><i class="bi bi-grid-3x3-gap-fill"></i> Dodaj linie
					</a></li>
					<li class="nav-item"><a class="nav-link active"
						aria-current="page"
						href="${pageContext.request.contextPath}/production/showAddProdMachineForm"> <span
							data-feather="home"></span><i class="bi bi-grid-3x3-gap-fill"></i> Dodaj stacje
					</a></li>
					<li class="nav-item"><a class="nav-link active"
						aria-current="page"
						href="${pageContext.request.contextPath}/production/showProdLinesList"> <span
							data-feather="home"></span><i class="bi bi-grid-3x3-gap-fill"></i> Dane linii
					</a></li>

				</security:authorize>

			</ul>
		</div>
	</nav>

</body>
</html>