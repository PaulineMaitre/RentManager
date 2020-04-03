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
                Modification des informations client
            </h1>
        </section>

        <!-- Main content -->
        <section class="content">
            <div class="row">
                <div class="col-md-12">
                    <!-- Horizontal Form -->
                    <div class="box">
                        <!-- form start -->
                        <form class="form-horizontal" method="post" action="${pageContext.request.contextPath}/users/edit">
                            <div class="tab-content">
                            <!-- <div class="active tab-pane" id="user"> -->
                                <div class="box-body no-padding">
                                    <table class="table table-striped">
                                        <tr>
                                            <th>Prénom</th>
                                            <th>${user.firstName}</th>
                                            <th class="form-group col-sm-8">
                                       			<input type="text" class="form-control" id="firstName" name="firstName" value = "${firstNameValue}" placeholder="Prénom">
                                            </th>
                                        </tr>
                                        <tr>
                                        	<th>Nom</th>
                                            <th>${user.lastName}</th>
                                            <th class="form-group col-sm-8">
                                       			<input type="text" class="form-control" id="lastName" name="lastName" value = "${lastNameValue}" placeholder="Nom">
                                            </th>
                                        </tr>
                                        <tr>
                                        	<th>Email</th>
                                            <th>${user.email}</th>
                                            <th class="form-group col-sm-8">
                                       			<input type="email" class="form-control" id="email" name="email" value = "${emailValue}" placeholder="Email">
                                            </th>
                                        </tr>
                                        <tr>
                                        	<th>Date de naissance</th>
                                            <th>${user.birthday}</th>
                                            <th class="form-group col-sm-8">
                                            	<input type="date" class="form-control" id="birthday" name="birthday" 
                                       			pattern="(?:19|20)[0-9]{2}-(?:(?:0[0-9]|1[0-2])-(?:0[1-9]|1[0-9]|2[0-9])|(?:(?!02)(?:0[1-9]|1[0-2])-(?:30))|(?:(?:0[13578]|1[02])-31))" 
                                        		title="YYYY-mm-dd" value = "${birthdayValue}" placeholder="YYYY-mm-dd">
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
                            	<a class="btn btn-warning" href="${pageContext.request.contextPath}/users">
                            	<i class="fa fa-undo"></i>  Retour à la liste des clients</a>
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
