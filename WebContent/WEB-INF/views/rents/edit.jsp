<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<!DOCTYPE html>
<html>
<%@include file="/WEB-INF/views/common/head.jsp"%>
<body class="hold-transition skin-blue sidebar-mini">
<div class="wrapper">

    <%@ include file="/WEB-INF/views/common/header.jsp" %>
    <!-- Left side column. contains the logo and sidebar -->
    <%@ include file="/WEB-INF/views/common/sidebar.jsp" %>

    <!-- Content Wrapper. Contains page content -->
    <div class="content-wrapper">
        <!-- Content Header (Page header) -->
        <section class="content-header">
            <h1>
                Modification de la réservation
            </h1>
        </section>

        <!-- Main content -->
        <section class="content">
            <div class="row">
                <div class="col-md-12">
                    <!-- Horizontal Form -->
                    <div class="box">
                        <!-- form start -->
                        <form class="form-horizontal" method="post" action="${pageContext.request.contextPath}/rents/edit">
                            <div class="tab-content">
                            <!-- <div class="active tab-pane" id="user"> -->
                                <div class="box-body no-padding">
                                    <table class="table table-striped">
                                        <tr>
                                            <th>Véhicule</th>
                                            <th>${rent.vehicleId.manufacturer} ${rent.vehicleId.model}</th>
                                            <th class="form-group col-sm-8">
                                        		<select class="form-control" id="vehicle" name="vehicle">
                                        			<c:if test="${!testError}">
                                        				<option value="${rent.vehicleId.id}">${rent.vehicleId.manufacturer} ${rent.vehicleId.model}</option>
			                                    	</c:if>
			                                    	<c:if test="${testError}">
			                                    		<option value="${selectedVehicle.id}">${selectedVehicle.manufacturer} ${selectedVehicle.model}</option>
			                                    	</c:if>
			                                    	<c:forEach items = "${vehicles}" var = "vehicle">
			                                        	<option value = "${vehicle.id}">${vehicle.manufacturer} ${vehicle.model}</option>
			                                        </c:forEach>
                                        		</select>
                                            </th>
                                        </tr>
                                        <tr>
                                        	<th>Client</th>
                                            <th>${rent.clientId.firstName} ${rent.clientId.lastName}</th>
                                            <th class="form-group col-sm-8">
                                       			<select class="form-control" id="client" name="client">
                                       				<c:if test="${!testError}">
                                        				<option value = "${rent.clientId.id}">${rent.clientId.firstName} ${rent.clientId.lastName}</option>
			                                    	</c:if>
                                       				<c:if test="${testError}">
					                                	<option value="${selectedClient.id}">${selectedClient.firstName} ${selectedClient.lastName}</option>
					                                </c:if>
		                                            <c:forEach items = "${users}" var = "user">
		                                            	<option value = "${user.id}">${user.firstName} ${user.lastName}</option>
		                                            </c:forEach>
                                        		</select>
                                            </th>
                                        </tr>
                                        <tr>
                                        	<th>Date de début</th>
                                            <th>${rent.beginning}</th>
                                            <th class="form-group col-sm-8">
                                       			<input type="date" class="form-control" id="beginning" name="beginning" 
                                       			pattern="(?:19|20)[0-9]{2}-(?:(?:0[0-9]|1[0-2])-(?:0[1-9]|1[0-9]|2[0-9])|(?:(?!02)(?:0[1-9]|1[0-2])-(?:30))|(?:(?:0[13578]|1[02])-31))" 
                                        		title="YYYY-mm-dd" value = "${beginningValue}" placeholder="YYYY-mm-dd">
                                            </th>
                                        </tr>
                                        <tr>
                                        	<th>Date de fin</th>
                                            <th>${rent.end}</th>
                                            <th class="form-group col-sm-8">
                                       			<input type="date" class="form-control" id="end" name="end" 
                                       			pattern="(?:19|20)[0-9]{2}-(?:(?:0[0-9]|1[0-2])-(?:0[1-9]|1[0-9]|2[0-9])|(?:(?!02)(?:0[1-9]|1[0-2])-(?:30))|(?:(?:0[13578]|1[02])-31))" 
                                        		title="YYYY-mm-dd" value = "${endValue}" placeholder="YYYY-mm-dd">
                                            </th>
                                        </tr>
                                    </table>
                                </div>
                            </div>
                            
                            <div class="box-footer">
                            	<c:if test="${testError}">
	                                <div class="alert alert-danger text-center col-md-6 col-md-offset-3 col-sm-8 col-sm-offset-2 col-xs-8 col-xs-offset-2"
	                                style="color: #721c24 !important; background-color: #f8d7da !important; border-color: #f5c6cb !important;">
	                             	${errorMessage}</div>
	                            </c:if>
	                            <div class="row"></div>
                            	<a class="btn btn-warning" href="${pageContext.request.contextPath}/rents">
                            	<i class="fa fa-undo"></i>  Retour à la liste des réservations</a>
                                <button type="submit" class="btn btn-success pull-right">
                                <i class="fa fa-check"></i>  Valider les modifications </button>
                            </div>
                            <!-- /.box-footer -->
                        </form>
                    </div>
                    <!-- /.box -->
                </div>
                <!-- /.col -->
            </div>
        </section>
        <!-- /.content -->
    </div>

    <%@ include file="/WEB-INF/views/common/footer.jsp" %>
</div>
<!-- ./wrapper -->

<%@ include file="/WEB-INF/views/common/js_imports.jsp" %>
</body>
</html>
