<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<%@ taglib uri = "http://java.sun.com/jsp/jstl/sql" prefix = "sql"%>
<title>Flexible Query System for Relational Databases based Fuzzy Logic and Ontology</title>
<!--   <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" > -->
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
 <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">

 
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
  <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.6/umd/popper.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta.2/js/bootstrap.min.js"></script> 
   <script src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.3.0/Chart.min.js"></script>
  <script src="https://cdn.jsdelivr.net/npm/chart.js@2.8.0"></script>
  <script type="text/javascript" src="javascript/scripts.js"></script>
  <script src="javascript/easyTree.js"></script>
  <style>	
body{
		/*color: #fff;*/
		background: #3598dc;
		font-family:Times New Roman, Times, serif;
	}
<%@include file="/css/styles.css" %>
</style>


</head>
<body class="body">
<%@ include file="navbar.jsp" %>
<!--<center><h1 style="font-family:'Courier New'">Flexible query System</h1> </center> -->
<c:choose>
 	<c:when test="${not empty sessionScope.User.getEmail()}">
 	<!--<c:set var="UserPassword" value="${user.getPassword()}" scope="page" /> -->
 	<%@ include file="main.jsp" %>
 	<%@ include file="changepassword.jsp" %>
	</c:when>
	<c:when test="${empty sessionScope.User}">
	<%@ include file="login.jsp" %>
	</c:when>
	<c:otherwise>
			<div class="alert alert-danger text-center">
 			  Warning : Email or password is incorrect!. please try again.
 			 </div>
 	<%@ include file="login.jsp" %>
	</c:otherwise>
</c:choose>
<!-- Test login -->

<script>
    (function ($) {
        function init() {
            $('.easy-tree').EasyTree({
                addable: true,
                editable: true,
                deletable: true
            });
        }

        window.onload = init();
    })(jQuery)
</script>
</body>
</html>