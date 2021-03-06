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
                <div class="col-md-12">

                    <!-- Profile Image -->
                    <div class="box box-primary">
                        <div class="box-body box-profile">
                            <h3 class="profile-username text-center">${user.firstName} ${user.lastName} (${user.email})</h3> 
							<h3 class="profile-username text-center">Né(e) le ${user.birthday}</h3>
                            <ul class="list-group list-group-unbordered">
                                <li class="list-group-item">
                                    <b>
	                                    <c:if test="${nbRents > 1}">
	                            			Réservations
	                            		</c:if>
	                            		<c:if test="${nbRents < 2}">
	                            			Réservation
	                            		</c:if>
                            		</b> 
                                    <a class="pull-right">${nbRents}</a>
                                </li>
                                <li class="list-group-item">
                                    <b>
	                                    <c:if test="${nbVehicles > 1}">
	                            			Véhicules
	                            		</c:if>
	                            		<c:if test="${nbVehicles < 2}">
	                            			Véhicule
	                            		</c:if>
	                            	</b> <a class="pull-right">${nbVehicles}</a>
                                </li>
                            </ul>
                        </div>
                        <!-- /.box-body -->
                    </div>
                    <!-- /.box -->
                </div>
                <!-- /.col -->
             </div>
             <div class="row">
                <div class="col-md-12">
                    <div class="nav-tabs-custom">
                        
                        <div class="tab-content">
                            
                            <h4 class = "text-center">Voulez vous vraiment supprimer ce client ?</h4>
                            <form class="form-horizontal" method="post" action="${pageContext.request.contextPath}/users/delete">
                            	
                            	<a class="btn btn-warning" href="${pageContext.request.contextPath}/users">
                            	<i class="fa fa-ban"></i>  Annuler </a>
                            	<button type="submit" class="btn btn-danger pull-right">
                            	<i class="fa fa-trash"></i>  Supprimer</button>
                            </form>
                           
                        </div>
                        <!-- /.tab-content -->
                    </div>
                    <!-- /.nav-tabs-custom -->
                </div>
                <!-- /.col -->
            </div>
            <!-- /.row -->

        </section>
        <!-- /.content -->
    </div>

    <%@ include file="/WEB-INF/views/common/footer.jsp" %>
</div>
<!-- ./wrapper -->

<%@ include file="/WEB-INF/views/common/js_imports.jsp" %>
</body>
</html>
