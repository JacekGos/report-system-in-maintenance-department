<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	
<html>
<head>

<title>Report summary</title>
<%@ include file="/WEB-INF/view/navigation/sources.jsp"%>

<script>
	function CopyToClipboard(containerid) {
		if (document.selection) {
			var range = document.body.createTextRange();
			range.moveToElementText(document.getElementById(containerid));
			range.select().createTextRange();
			document.execCommand("copy");
		} else if (window.getSelection) {
			var range = document.createRange();
			range.selectNode(document.getElementById(containerid));
			window.getSelection().addRange(range);
			document.execCommand("copy");
		}
	}
</script>

</head>

<body>

	<%@ include file="/WEB-INF/view/navigation/navigation-bar.jsp"%>
	
	<div class="container-fluid">
		<div class="row">
			<%@ include file="/WEB-INF/view/navigation/side-bar-menu.jsp"%>
			<main class="col-md-9 ms-sm-auto col-lg-10 px-md-4">
				<div
					class="d-flex justify-content-between flex-wrap flex-md-nowrap align-items-center pt-3 pb-2 mb-3 border-bottom">
					<h1 class="h2">Podsumowanie raportów</h1>
					<div class="btn-toolbar mb-2 mb-md-0">
						<div class="btn-group me-2">
							<button class="btn btn-sm btn-outline-secondary"
								id="copyButton" onclick="CopyToClipboard('summaryDescription')">Kopiuj</button>
						</div>
					</div>
				</div>
				<div class="row">
					<aside class="col-sm-2"></aside>
					<aside class="col-sm-8">
						<div contentEditable="false" class="form-control"
							id="summaryDescription">
							<c:forEach var="description" items="${summaryReportDescriptions}">
								${description.getValue().description} <br>
								<br>
								<c:if test="${description.getValue().isImage == true}">
									<img class="rounded"
										src="${pageContext.request.contextPath}/report/showImage?id=${description.getKey()}"
										style="max-width: 400px; height: auto;">
									<br>
									<br>
								</c:if>
							</c:forEach>
						</div>
					</aside>
				</div>
			</main>
		</div>
	</div>
	
</body>
</html>