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
                Nouvel utilisateur
            </h1>
        </section>
        <!-- Main content -->
        <section class="content">
            <div class="row">
                <div class="col-md-12">
                    <!-- Horizontal Form -->
                    <div class="box">
                        <!-- form start -->
                        <form class="form-horizontal" method="post" action="${pageContext.request.contextPath}/users/create">
                            <div class="box-body">
                            <div class="form-group">
                                    <label for="firstName" class="col-sm-2 control-label">Prénom</label>

                                    <div class="col-sm-10">
                                        <input type="text" class="form-control" id="firstName" name="firstName" value = "${firstNameValue}" required placeholder="Prénom">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label for="lastName" class="col-sm-2 control-label">Nom</label>

                                    <div class="col-sm-10">
                                        <input type="text" class="form-control" id="lastName" name="lastName" value = "${lastNameValue}" required placeholder="Nom">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label for="email" class="col-sm-2 control-label">Email</label>

                                    <div class="col-sm-10">
                                        <input type="email" class="form-control" id="email" name="email" value = "${emailValue}" required placeholder="Email">
                                    </div>
                                </div>
                                
                                <div class="form-group">
                                    <label for="birthday" class="col-sm-2 control-label">Date de naissance</label>
                                    <div class="col-sm-10">
                                        <input type="date" class="form-control" id="birthday" name="birthday" 
                                        pattern="(?:19|20)[0-9]{2}-(?:(?:0[0-9]|1[0-2])-(?:0[1-9]|1[0-9]|2[0-9])|(?:(?!02)(?:0[1-9]|1[0-2])-(?:30))|(?:(?:0[13578]|1[02])-31))" 
                                        title="YYYY-mm-dd" value = "${birthdayValue}" required placeholder="YYYY-mm-dd">
                                    </div>
                                </div>
                                <c:if test="${testError}">
                                	<div class="alert alert-danger text-center col-md-6 col-md-offset-3 col-sm-8 col-sm-offset-2 col-xs-8 col-xs-offset-2"
                                	style="color: #721c24 !important; background-color: #f8d7da !important; border-color: #f5c6cb !important;">
                                	${errorMessage}</div>
                                </c:if>
                            </div>
                            <!-- /.box-body -->
                            <div class="box-footer">
                                <a class="btn btn-warning col-md-2" href="${pageContext.request.contextPath}/users">
	                 				<i class="fa fa-undo"></i>  Retour</a>
                                <button type="submit" class="btn btn-info col-md-2 pull-right">
                                <i class="fa fa-plus"></i> Ajouter</button>
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
