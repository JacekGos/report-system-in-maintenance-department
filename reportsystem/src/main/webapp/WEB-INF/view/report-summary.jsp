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

</head>
<body>

	<%@ include file="/WEB-INF/view/navigation-bar.jsp"%>

	<div class="container-fluid">
		<div class="row">

			<%@ include file="/WEB-INF/view/side-bar-menu.jsp"%>

			<main class="col-md-9 ms-sm-auto col-lg-10 px-md-4">
				<div
					class="d-flex justify-content-between flex-wrap flex-md-nowrap align-items-center pt-3 pb-2 mb-3 border-bottom">
					<h1 class="h2">Podsumowanie raportów</h1>
					<div class="btn-toolbar mb-2 mb-md-0">
						<div class="btn-group me-2">
							<button type="submit" class="btn btn-sm btn-outline-secondary"
								form="summaryForm">Podsumowanie</button>
						</div>
					</div>
				</div>

				<div class="row">
					<aside class="col-sm-2"></aside>
					<aside class="col-sm-8">
						<div contentEditable="false" class="form-control">

							<c:forEach var="description" items="${summaryReportDescriptions}">
								${description.getValue()} <br>

								<img class="rounded"
									src="${pageContext.request.contextPath}/report/showImage?id=${description.getKey()}"
									style="max-width: 400px; height: auto;">
								<br>
								<br>
							</c:forEach>
						</div>

					</aside>

					<div class="container">
						<!-- <p class="lead">Pin a footer to the bottom of the viewport in
							desktop browsers with this custom HTML and CSS.</p>
						<p>
							Use <a href="../examples/sticky-footer-navbar/">the sticky
								footer with a fixed navbar</a> if need be, too.
						</p> -->
					</div>
				</div>
			</main>
		</div>
	</div>

</body>
</html>