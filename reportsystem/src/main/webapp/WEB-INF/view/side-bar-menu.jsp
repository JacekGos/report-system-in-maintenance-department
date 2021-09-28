<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
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
					<span>Reports</span> 
				</h5>

				<li class="nav-item"><a class="nav-link active"
					aria-current="page" href="#"> <span data-feather="home"></span>
						<i class="bi bi-receipt"></i> Add Report
				</a></li>

				<li class="nav-item"><a class="nav-link active"
					aria-current="page" href="#"> <span data-feather="home"></span>
						<i class="bi bi-calendar2-minus"></i> Report history
				</a></li>

				<security:authorize access="hasAnyRole('MANAGER', 'ADMIN')">

					<h5
						class="sidebar-heading d-flex justify-content-between align-items-center px-3 mt-4 mb-1 text-muted ">
						<span>Users</span>
					</h5>

					<li class="nav-item"><a class="nav-link active"
						aria-current="page"
						href="${pageContext.request.contextPath}/book/showAddBookForm"> <span
							data-feather="home"></span> <i class="bi bi-person"></i> Add user
					</a></li>


				</security:authorize>

			</ul>
		</div>
	</nav>

</body>
</html>