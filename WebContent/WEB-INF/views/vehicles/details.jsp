<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<!DOCTYPE html>
<html>
<%@include file="/WEB-INF/views/common/head.jsp"%>
<head>
	<link rel="stylesheet" href="../style.css"/>
</head>
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
                            <h3 class="profile-username text-center">${vehicle.manufacturer} ${vehicle.model}</h3>
							<h3 class="profile-username text-center">${vehicle.nbSeats} places</h3>
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
	                                	<c:if test="${nbClients > 1}">
	                            			Clients
	                            		</c:if>
	                            		<c:if test="${nbClients < 2}">
	                            			Client
	                            		</c:if>
	                            	</b><a class="pull-right">${nbClients}</a>
                                </li>
                            </ul>
                        </div>
                        <!-- /.box-body -->
                    </div>
                    <!-- /.box -->
                    <a class="btn btn-primary col-md-12 col-sm-12 col-xs-12" href="${pageContext.request.contextPath}/vehicles/reserve?id=${vehicle.id}">
	                            <i class="fa fa-car"></i>  Réserver ce véhicule</a>
                <div class="row"><p><br/><br/></p></div>
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
                            	<a href="#users" data-toggle="tab">
                            		<c:if test="${nbClients > 1}">
                            			Clients associés
                            		</c:if>
                            		<c:if test="${nbClients < 2}">
                            			Client associé
                            		</c:if>
                            	</a>
                            </li>
                        </ul>
                        <div class="tab-content">
                            <div class="active tab-pane" id="rents">
                                <div class="box-body no-padding">
                                	<c:if test="${nbRents > 0}">
                                		<p><em><b><i class="fa fa-info"></i> Vous pouvez cliquer sur le nom du client pour obtenir sa fiche personnelle</b></em></p>
                                    </c:if>
                                    <table class="table table-striped">
                                        <tr>
                                            <th style="width: 10px">#</th>
                                            <th>Client</th>
                                            <th>Date de début</th>
                                            <th>Date de fin</th>
                                        </tr>
                                        <c:forEach items = "${rents}" var = "rent">
	                                        <tr>
	                                           	<td>${rent.id}</td>
	                                            <td><a href = "${pageContext.request.contextPath}/users/details?id=${rent.clientId.id}">${rent.clientId.firstName} ${rent.clientId.lastName}</a></td>
	                                            <td>${rent.beginning}</td>
	                                            <td>${rent.end}</td>
	                                        </tr>
                                        </c:forEach>
                                    </table>
                                </div>
                            </div>
                            <!-- /.tab-pane -->
                            <div class="tab-pane" id="users">
                                <!-- /.box-header -->
                                <div class="box-body no-padding">
                                    <table class="table table-striped">
                                        <tr>
                                            <th style="width: 10px">#</th>
                                            <th>Prénom</th>
                                            <th>Nom</th>
                                            <th>Email</th>
                                        </tr>
                                       <c:forEach items = "${users}" var = "user">
	                                        <tr>
	                                           	<td>${user.id}</td>
	                                            <td>${user.firstName}</td>
	                                            <td>${user.lastName}</td>
	                                            <td>${user.email}</td>
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
            <!-- /.row -->
            	<div class="row"><p><br/></p></div>
                <div class="row">
       	        	<div class="col-md-1 col-sm-1"></div>
	                    <a class="btn btn-warning col-md-2 col-sm-2 col-xs-12 wrap" href="${pageContext.request.contextPath}/vehicles">
	                            <i class="fa fa-undo"></i>  Retour à la liste</a>
	                    <div class="col-md-1 col-sm-1"></div>
	                    <a class="btn btn-primary col-md-4 col-sm-4 col-xs-12 wrap" href="${pageContext.request.contextPath}/vehicles/edit?id=${vehicle.id}">
	                            <i class="fa fa-edit"></i>  Modifier les informations du véhicule</a>
	                    <div class="col-md-1 col-sm-1"></div>
	                    <a class="btn btn-danger col-md-2 col-sm-2 col-xs-12 wrap" href="${pageContext.request.contextPath}/vehicles/delete?id=${vehicle.id}">
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
