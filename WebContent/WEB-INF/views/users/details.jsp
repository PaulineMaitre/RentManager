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
        <!-- Main content -->
        <section class="content">
            <div class="row">
                <div class="col-md-4">

                    <!-- Profile Image -->
                    <div class="box box-primary">
                        <div class="box-body box-profile">
                            <h3 class="profile-username text-center">${user.firstName} ${user.lastName} (${user.email})</h3> 
							<h3 class="profile-username text-center">Né(e) : ${user.birthday}</h3>
                            <ul class="list-group list-group-unbordered">
                                <li class="list-group-item">
                                    <b>
	                                    <c:if test="${nbRents > 1}">
	                            			Réservations
	                            		</c:if>
	                            		<c:if test="${nbRents < 2}">
	                            			Réservation
	                            		</c:if>
                            		</b> <a class="pull-right">${nbRents}</a>
                                </li>
                                <li class="list-group-item">
                                    <b>
	                                    <c:if test="${nbVehicles > 1}">
	                            			Véhicules
	                            		</c:if>
	                            		<c:if test="${nbVehicles < 2}">
	                            			Véhicule
	                            		</c:if>
	                            	</b><a class="pull-right">${nbVehicles}</a>
                                </li>
                            </ul>
                        </div>
                        <!-- /.box-body -->
                    </div>
                    <!-- /.box -->
                </div>
                <!-- /.col -->
                <div class="col-md-8">
                    <div class="nav-tabs-custom">
                        <ul class="nav nav-tabs">
                            <li class="active">
                            	<a href="#rents" data-toggle="tab">
                            		<c:if test="${nbRents > 1}">
                            			Réservations
                            		</c:if>
                            		<c:if test="${nbRents < 2}">
                            			Réservation
                            		</c:if>
                            	</a>
                            </li>
                            <li>
                            	<a href="#vehicles" data-toggle="tab">
                            		<c:if test="${nbVehicles > 1}">
                            			Véhicules
                            		</c:if>
                            		<c:if test="${nbVehicles < 2}">
                            			Véhicule
                            		</c:if>
                            	</a>
                           </li>
                        </ul>
                        <div class="tab-content">
                            <div class="active tab-pane" id="rents">
                                <div class="box-body no-padding">
                                	<c:if test="${nbRents > 0}">
                            			<p><em><b><i class="fa fa-info"></i> Vous pouvez cliquer sur le nom du véhicule pour obtenir sa fiche détaillée</b></em></p>
                            		</c:if>
                                
                                    <table class="table table-striped">
                                        <tr>
                                            <th style="width: 10px">#</th>
                                            <th>Voiture</th>
                                            <th>Date de début</th>
                                            <th>Date de fin</th>
                                        </tr>
                                        <c:forEach items = "${rents}" var = "rent">
	                                        <tr>
	                                           	<td>${rent.id}</td>
	                                            <td><a href = "${pageContext.request.contextPath}/vehicles/details?id=${rent.vehicleId.id}">${rent.vehicleId.manufacturer} ${rent.vehicleId.model}</a></td>
	                                            <td>${rent.beginning}</td>
	                                            <td>${rent.end}</td>
	                                        </tr>
                                        </c:forEach>
                                    </table>
                                </div>
                            </div>
                            <!-- /.tab-pane -->
                            <div class="tab-pane" id="vehicles">
                                <!-- /.box-header -->
                                <div class="box-body no-padding">
                                    <table class="table table-striped">
                                        <tr>
                                            <th style="width: 10px">#</th>
                                            <th>Constructeur</th>
                                            <th>Modèle</th>
                                            <th style=>Nombre de places</th>
                                        </tr>
                                        <c:forEach items = "${vehicles}" var = "vehicle">
	                                        <tr>
	                                           	<td>${vehicle.id}</td>
	                                            <td>${vehicle.manufacturer}</td>
	                                            <td>${vehicle.model}</td>
	                                            <td>${vehicle.nbSeats}</td>
	                                        </tr>
                                        </c:forEach>
                                    </table>
                                </div>
                            </div>
                            <!-- /.tab-pane -->
                        </div>
                        <!-- /.tab-content -->
                    </div>
                    <!-- /.nav-tabs-custom -->
                </div>
          	</div>
            <div class="row">
       	       	<div class="col-md-1 col-sm-1"></div>
		             <a class="btn btn-warning col-md-2 col-sm-2 col-xs-12" href="${pageContext.request.contextPath}/users">
	                 	<i class="fa fa-undo"></i>  Retour à la liste</a>
	                 <div class="col-md-1 col-sm-1"></div>
	                 <a class="btn btn-primary col-md-4 col-sm-4 col-xs-12" href="${pageContext.request.contextPath}/users/edit?id=${user.id}">
	             		<i class="fa fa-edit"></i>  Modifier les informations du client</a>
	                 <div class="col-md-1 col-sm-1"></div>
	                 <a class="btn btn-danger col-md-2 col-sm-2 col-xs-12" href="${pageContext.request.contextPath}/users/delete?id=${user.id}">
	                    <i class="fa fa-trash"></i>  Supprimer</a>
	     	    <div class="col-md-1 col-sm-1"></div>
           </div>
                <!-- /.col -->

        </section>
        <!-- /.content -->
    </div>

    <%@ include file="/WEB-INF/views/common/footer.jsp" %>
</div>
<!-- ./wrapper -->

<%@ include file="/WEB-INF/views/common/js_imports.jsp" %>
</body>
</html>
