<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<%@include file="/WEB-INF/views/common/head.jsp"%>
<head>
	<link rel="stylesheet" href="style.css"/>
</head>
<body class="hold-transition skin-blue sidebar-mini">
<div class="wrapper">

 <!-- Left side column. contains the logo and sidebar -->
    <%@ include file="/WEB-INF/views/common/header.jsp" %>

   <!-- Left side column. contains the logo and sidebar -->
    <%@ include file="/WEB-INF/views/common/sidebar.jsp" %>


  <!-- Content Wrapper. Contains page content -->
  <div class="content-wrapper">
    <!-- Content Header (Page header) -->
    <section class="content-header">
      <h1>
        Dashboard
      </h1>
    </section>

    <!-- Main content -->
    <section class="content">
      <!-- Info boxes -->
      <div class="row">
        <div class="col-md-4 col-sm-6 col-xs-12">
        <a style="text-decoration: none;" href="${pageContext.request.contextPath}/users">
          <div class="info-box">
            <span class="info-box-icon bg-aqua"><i class="fa fa-user"></i></span>

            <div class="info-box-content" style="color:black;">
              <span class="info-box-text">Utilisateurs</span>
              <span class="info-box-number">${nbClients}</span>
            </div>
            <!-- /.info-box-content -->
          </div>
          <!-- /.info-box -->
          </a>
        </div>
        <!-- /.col -->
        <div class="col-md-4 col-sm-6 col-xs-12">
        <a style="text-decoration: none;" href="${pageContext.request.contextPath}/vehicles">
          <div class="info-box">
            <span class="info-box-icon bg-red"><i class="fa fa-car"></i></span>

            <div class="info-box-content" style="color:black;">
              <span class="info-box-text">Véhicules</span>
              <span class="info-box-number">${nbVehicles}</span>
            </div>
            <!-- /.info-box-content -->
          </div>
          <!-- /.info-box -->
          </a>
        </div>
        <!-- /.col -->

        <!-- fix for small devices only -->
        <div class="clearfix visible-sm-block"></div>

        <div class="col-md-4 col-sm-6 col-xs-12">
        <a style="text-decoration: none;" href="${pageContext.request.contextPath}/rents">
          <div class="info-box">
            <span class="info-box-icon bg-green"><i class="fa fa-pencil"></i></span>

            <div class="info-box-content" style="color:black;">
              <span class="info-box-text">Réservations</span>
              <span class="info-box-number">${nbRents}</span>
            </div>
            <!-- /.info-box-content -->
          </div>
          <!-- /.info-box -->
          </a>
        </div>
        <!-- /.col -->
      </div>
      <!-- /.row -->
    </section>
    <!-- /.content -->
  </div>
  <!-- /.content-wrapper -->
  
   <jsp:include page='/WEB-INF/views/common/footer.jsp'></jsp:include>


</div>
<!-- ./wrapper -->

   <!-- Contains Js code imports -->
   <%@ include file="/WEB-INF/views/common/js_imports.jsp" %>

</body>
</html>
