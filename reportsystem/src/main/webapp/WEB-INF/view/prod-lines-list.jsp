<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>  
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>

<html>

<head>
	<title>Home Page</title>
	
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
					<h1 class="h2">Lista linii</h1>
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
					<aside class="col-sm-3"></aside>
					<aside class="col-sm-3">
						<div class="table-responsive">
							<table class="table table-striped table-sm table-bordered table-hover">
								<thead class="table-dark">
									<tr>
										<th scope="col" class="col-sm-5">Linia</th>
										<th scope="col" class="col-sm-3">Opcje</th>
									</tr>
								</thead>
								<tbody>
								
									<c:if test="${prodLines != null}">
										<c:forEach var="line" items="${prodLines}">
											<tr>

												<c:url var="detailsLink" value="/production/showProdLineDetails">
													<c:param name="id" value="${line.id}" />
												</c:url>
												
												<td>${line.name}</td>
												<td>
													<a href="${detailsLink}"><i class="bi bi-search"></i></a>
												</td>
												
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