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
                Modification des informations du véhicule
            </h1>
        </section>

        <!-- Main content -->
        <section class="content">
            <div class="row">
                <div class="col-md-12">
                    <!-- Horizontal Form -->
                    <div class="box">
                        <!-- form start -->
                        <form class="form-horizontal" method="post" action="${pageContext.request.contextPath}/vehicles/edit">
                            <div class="tab-content">
                                <div class="box-body no-padding">
                                    <table class="table table-striped">
                                        <tr>
                                            <th>Constructeur</th>
                                            <th>${vehicle.manufacturer}</th>
                                            <th class="form-group col-sm-8">
                                       			<input type="text" class="form-control" id="manufacturer" name="manufacturer" value = "${manufacturerValue}" placeholder="Constructeur">
                                            </th>
                                        </tr>
                                        <tr>
                                        	<th>Modèle</th>
                                            <th>${vehicle.model}</th>
                                            <th class="form-group col-sm-8">
                                       			<input type="text" class="form-control" id="model" name="model" value = "${modelValue}" placeholder="Modèle">
                                            </th>
                                        </tr>
                                        <tr>
                                        	<th>Nombre de places</th>
                                            <th>${vehicle.nbSeats}</th>
                                            <th class="form-group col-sm-8">
                                       			<input type="number" min="2" max="9" class="form-control" id="nbSeats" name="nbSeats" value = "${nbSeatsValue}" placeholder="Nombre de places">
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
                            	<a class="btn btn-warning" href="${pageContext.request.contextPath}/vehicles">
                            	<i class="fa fa-undo"></i>  Retour à la liste des véhicules</a>
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
