<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!doctype html>
<html lang="en">

<head>

<title>Register New User Form</title>

<%@ include file="/WEB-INF/view/navigation/sources.jsp"%>

<link type="text/css" rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/form-validation.css" />


<script
	src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.5.1/jquery.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0/js/materialize.min.js"></script>

<script type="text/javascript">
	$(document)
			.ready(
					function() {
						
								$.ajax({
									url : "${pageContext.request.contextPath}/test/loadProdMachines",
									method : "GET",
									data : {
										operation : 'country'
									},
// 									success : function(data, textStatus, jqXHR) {
// 										console.log(data);
// 										let obj = $.parseJSON(data);
// 										$.each(obj, function(key, value) {
// 											$('#country').append(
// 													'<option value="' + value.id + '">'
// 															+ value.name
// 															+ '</option>')
// 										});
// 										$('select').formSelect();
// 									},
// 									error : function(jqXHR, textStatus,
// 											errorThrown) {
// 										$('#country')
// 												.append(
// 														'<option>Country Unavailable</option>');
// 									},
									cache : false
								}); 

						$('#country')
								.change(
										function() {
											$('#state').find('option').remove();
											$('#state')
													.append(
															'<option>Wybierz stacje</option>');

											let cid = $('#country').val();
											let data = {
												operation : "state",
												id : cid
											};

											$.ajax({
														url : "${pageContext.request.contextPath}/test/loadProdMachines",
														method : "GET",
														data : data,
														success : function(
																data,
																textStatus,
																jqXHR) {
															console.log(data);
															let obj = $
																	.parseJSON(data);
															$.each(
																obj,
																function(
																		key,
																		value) {
																	$(
																	'#state')
																	.append(
																			'<option value="' + value.id  + '">'
																					+ value.name
																					+ '</option>')
																	});
														$('select')
																.formSelect();
													},
													error : function(jqXHR,
															textStatus,
															errorThrown) {
														$('#state')
																.append(
																		'<option>State Unavailable</option>');
													},
														cache : false
													});
										});

// 						$('#state')
// 								.change(
// 										function() {
// 											$('#city').find('option').remove();
// 											$('#city')
// 													.append(
// 															'<option>Select City</option>');

// 											let sid = $('#state').val();
// 											let data = {
// 												operation : "city",
// 												id : sid
// 											};

// 											$
// 													.ajax({
// 														url : "${pageContext.request.contextPath}/test/loadProdMachines",
// 														method : "GET",
// 														data : data,
// 														success : function(
// 																data,
// 																textStatus,
// 																jqXHR) {
// 															console.log(data);
// 															let obj = $
// 																	.parseJSON(data);
// 															$
// 																	.each(
// 																			obj,
// 																			function(
// 																					key,
// 																					value) {
// 																				$(
// 																						'#city')
// 																						.append(
// 																								'<option value="' + value.id + '">'
// 																										+ value.name
// 																										+ '</option>')
// 																			});
// 															$('select')
// 																	.formSelect();
// 														},
// 														error : function(jqXHR,
// 																textStatus,
// 																errorThrown) {
// 															$('#city')
// 																	.append(
// 																			'<option>City Unavailable</option>');
// 														},
// 														cache : false
// 													});
// 										});

					});
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
					<h1 class="h2">Dodaj raport</h1>

				</div>

				<div class="row">
					<aside class="col-sm-3"></aside>
					<aside class="col-sm-8">

						<div class="col-md-7 col-lg-8">
 
							<form:form class="row g-3"
								action="${pageContext.request.contextPath}/report/processReportForm"
								modelAttribute="reportDto" accept-charset="UTF-8">

								<div class="row g-3">

									<div class="col-md-6">
										<label class="form-label">Linia</label>
										<form:select id="school" path="productionLineId"
										 	items="${prodLines}" class="form-select" />
									</div>

									<div class="col-md-6">
										<label class="form-label">Stacja</label>
										<form:select id="course" path="productionMachineId"
										class="form-select"> 
										</form:select> 
									</div>

								</div>

								<div class="col-12">
									<button type="submit" class="btn btn-outline-primary">Zatwierdź</button>
								</div>

							</form:form>

<!--  					<div>
    <div style="float: left;">
        Select Department
        <br />
        <select id="departments"></select>
    </div>
    <div style="float: left; margin-left: 10px;">
        Select Manager
        <br />
        <select id="managers"></select>
    </div>
</div>
-->					

<script>

cars=new Array("Mercedes","Volvo","BMW","porche");
phones=new Array('Samsung','Nokia','Iphone');


$('.school').on('change',function()
		{
		      var selectedSchool=$(this).find('option:selected').val(); //Select the school code which is stored as value for option
		      $.ajax({
		             url:"${pageContext.request.contextPath}/test/loadProdMachines", //Write a function in the server side which accepts school code as argument
		             type:"POST",
		             dataType:'json',//return type from server side function [return it as JSON object]
		             contentType: "application/json",
		             data: JSON.stringify(productionMachinesList), //Pass the data to the function on server side
		             success: function (data) { //Array of data returned from server side function
		                    $.each(data,function(value){
		                          $('.course').append('<option>'+value+'</option>');
		                    });
		             },
		             error:
		                function (data) {
		                    //display any unhandled error
		             }
		       });
		});















/*  
var $departments = $("#departments");
var $managers = $("#managers");

// This data would be loaded from the DB
var departments = [
	
    {id: "1", value: "Department 1"},
    {id: "2", value: "Department 2"}
];

// This data would be loaded from the DB
var managers = [
    { departmentId: "1", value: "Dep 1 Manager 1"},
    { departmentId: "1", value: "Dep 1 Manager 2"},
    { departmentId: "1", value: "Dep 1 Manager 3"},
    { departmentId: "1", value: "Dep 1 Manager 4"},
    { departmentId: "1", value: "Dep 1 Manager 5"},
    { departmentId: "1", value: "Dep 1 Manager 6"},
    { departmentId: "1", value: "Dep 1 Manager 7"},
    { departmentId: "1", value: "Dep 1 Manager 8"},
    { departmentId: "1", value: "Dep 1 Manager 9"},
    { departmentId: "1", value: "Dep 1 Manager 10"},
    { departmentId: "2", value: "Dep 2 Manager 1"},
    { departmentId: "2", value: "Dep 2 Manager 2"},
    { departmentId: "2", value: "Dep 2 Manager 3"},
    { departmentId: "2", value: "Dep 2 Manager 4"},
    { departmentId: "2", value: "Dep 2 Manager 5"},
    { departmentId: "2", value: "Dep 2 Manager 6"},
    { departmentId: "2", value: "Dep 2 Manager 7"},
    { departmentId: "2", value: "Dep 2 Manager 8"},
    { departmentId: "2", value: "Dep 2 Manager 9"},
    { departmentId: "2", value: "Dep 2 Manager 10"}
];

function getManagers() {
    $managers.empty();

    var departmentId = $departments.val();

    for (i = 0; i < managers.length; i++) {
        if (managers[i].departmentId === departmentId) {
            $managers.append("<option value='" + managers[i].departmentId + "'>" + managers[i].value + "</option>");
        }
    }
}

for (i = 0; i < departments.length; i++) {
    $departments.append("<option value='" + departments[i].id + "'>" + departments[i].value + "</option>");
}

getManagers();

$departments.on("change", function(){
    getManagers();
});

*/

</script>



						</div>
					</aside>
					<aside class="col-sm-4"></aside>
				</div>
			</main>

		</div>
	</div>
</body>
</html>