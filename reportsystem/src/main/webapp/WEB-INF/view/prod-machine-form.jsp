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
							modelAttribute="formProdMachine" accept-charset="UTF-8">

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







	<%-- 
	<%@ include file="/WEB-INF/view/navigation-bar.jsp" %>
	
	<div class="container">
		
		<div id="loginbox" style="margin-top: 50px;"
			class="mainbox col-md-3 col-md-offset-2 col-sm-6 col-sm-offset-2">
			
			<div class="panel panel-primary">

				<div class="panel-heading">
					<div class="panel-title">Add Book</div>
				</div>

				<div style="padding-top: 30px" class="panel-body">

					<!-- Registration Form -->
					<form:form action="${pageContext.request.contextPath}/book/processBookForm" 
						  	   modelAttribute="formBook"
						  	   class="form-horizontal">

					    <!-- Place for messages: error, alert etc ... -->
					    <div class="form-group">
					        <div class="col-xs-15">
					            <div>
								
									<!-- Check for registration error -->
									<c:if test="${registrationError != null}">
								
										<div class="alert alert-danger col-xs-offset-1 col-xs-10">
											${registrationError}
										</div>
		
									</c:if>
																			
					            </div>
					        </div>
					    </div>
	
						<!-- title -->
						<div style="margin-bottom: 2px" class="input-group">
							<form:errors path="title" cssClass="error" />
						</div>
	
						<div style="margin-bottom: 10px" class="input-group">
							<span class="input-group-addon"><i class="bi bi-book"></i></span> 
							<form:input path="title" placeholder="title (*)" class="form-control" />
						</div>
						
						<!-- pagesNumber -->
						<div style="margin-bottom: 2px" class="input-group">
							<form:errors path="pagesNr" cssClass="error" />
						</div>
						
						<div style="margin-bottom: 10px" class="input-group">
							<span class="input-group-addon"><i class="glyphicon glyphicon-lock"></i></span> 
							<form:password path="pagesNr" placeholder="number of pages (*)" class="form-control" />
						</div>
						
						<!-- sector -->
<!-- 						<div style="margin-bottom: 25px" class="input-group"> -->
<!-- 							<span class="input-group-addon"><i class="glyphicon glyphicon-user"></i></span>  -->
							<form:select path="formRole" items="${roles}" class="form-control" />
<!-- 						</div> -->

						<!-- Register Button -->
						<div style="margin-top: 10px" class="form-group">						
							<div class="col-sm-6 controls">
								<button type="submit" class="btn btn-primary">Confirm</button>
							</div>
						</div>
						
					</form:form>

				</div>

			</div>

		</div>
	
	<br>
		<div class="card">
<article class="card-body">
	<h4 class="card-title text-center mb-4 mt-1">Sign in</h4>
	<hr>
	<p class="text-success text-center">Some message goes here</p>
	<form>
	<div class="form-group">
	<div class="input-group">
		<div class="input-group-prepend">
		    <span class="input-group-text"> <i class="fa fa-user"></i> </span>
		 </div>
		<input name="" class="form-control" placeholder="Email or login" type="email">
	</div> <!-- input-group.// -->
	</div> <!-- form-group// -->
	<div class="form-group">
	<div class="input-group">
		<div class="input-group-prepend">
		    <span class="input-group-text"> <i class="fa fa-lock"></i> </span>
		 </div>
	    <input class="form-control" placeholder="******" type="password">
	</div> <!-- input-group.// -->
	</div> <!-- form-group// -->
	<div class="form-group">
	<button type="submit" class="btn btn-primary btn-block"> Login  </button>
	</div> <!-- form-group// -->
	<p class="text-center"><a href="#" class="btn">Forgot password?</a></p>
	</form>
</article>
</div>
	</div>
 --%>


	<!-- To Report project -->
	<%-- 	<%@ include file="/WEB-INF/view/navigation-bar.jsp"%>

	<div class="container-fluid">
		<div class="row">

			<%@ include file="/WEB-INF/view/side-bar-menu.jsp"%>

			<main class="col-md-9 ms-sm-auto col-lg-10 px-md-4">
				<div
					class="d-flex justify-content-between flex-wrap flex-md-nowrap align-items-center pt-3 pb-2 mb-3 border-bottom">
					<h1 class="h2">Add User</h1>
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
					<aside class="col-sm-2"></aside>
					<aside class="col-sm-6">
						<form class="row g-3">
							<div class="col-md-6">
								<label for="inputEmail4" class="form-label">Email</label> <input
									type="email" class="form-control" id="inputEmail4">
							</div>
							<div class="col-md-6">
								<label for="inputPassword4" class="form-label">Password</label>
								<input type="password" class="form-control" id="inputPassword4">
							</div>
							<div class="col-12">
								<label for="inputAddress" class="form-label">Address</label> <input
									type="text" class="form-control" id="inputAddress"
									placeholder="1234 Main St">
							</div>
							<div class="col-12">
								<label for="inputAddress2" class="form-label">Address 2</label>
								<input type="text" class="form-control" id="inputAddress2"
									placeholder="Apartment, studio, or floor">
							</div>
							<div class="col-md-6">
								<label for="inputCity" class="form-label">City</label> <input
									type="text" class="form-control" id="inputCity">
							</div>
							<div class="col-md-4">
								<label for="inputState" class="form-label">State</label> <select
									id="inputState" class="form-select">
									<option selected>Choose...</option>
									<option>...</option>
								</select>
							</div>
							<div class="col-md-2">
								<label for="inputZip" class="form-label">Zip</label> <input
									type="text" class="form-control" id="inputZip">
							</div>
							<div class="col-12">
								<div class="form-check">
									<input class="form-check-input" type="checkbox" id="gridCheck">
									<label class="form-check-label" for="gridCheck"> Check
										me out </label>
								</div>
							</div>
							<div class="col-12">
								<button type="submit" class="btn btn-primary">Sign in</button>
							</div>
						</form>

					</aside>

					<aside class="col-sm-4"></aside>
				</div>



				<!-- 				<canvas class="my-4 w-100" id="myChart" width="900" height="380"></canvas> -->
		</div>
		</main>
	</div>
	</div> --%>

</body>
</html>