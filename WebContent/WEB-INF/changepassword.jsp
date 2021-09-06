<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!-- The Modal -->
  <div class="modal fade" id="ModalChangePassword">
    <div class="modal-dialog">
      <div class="modal-content">
      
        <!-- Modal Header -->
        <div class="modal-header">
          <h4 class="modal-title">Password change</h4>
          <button type="button" class="close" data-dismiss="modal">&times;</button>
        </div>
        
        <!-- Modal body -->
        <div class="modal-body">
       <form   method="post" id="changepasswordform" oninput='password.setCustomValidity(confirm_password.value != password.value ? "Passwords do not match." : "")'>
		
		<!--  <form action="changepassword" method="post" oninput="check()"> -->
		<div class="form-group">
		    <input type="hidden" name="email"  id="email" value='<c:out value="${sessionScope.User.getEmail()}"></c:out>' />
            <input type="hidden" name="currentpassword"  id="currentpassword" value='<c:out value="${sessionScope.User.getPassword()}"></c:out>' />
            <input type="password" class="form-control" name="oldpassword" id="oldpassword" placeholder="Old Password" required="required">
        </div>
		<div class="form-group">
            <input type="password" class="form-control" name="password" id="newpassword"  placeholder="New Password" required="required">
        </div>
		<div class="form-group">
            <input type="password" class="form-control" name="confirm_password" placeholder="Confirm Password" required="required">
        </div> 
        <button type="button" class="btn btn-primary " id="changeconfirme">Confirme</button>  
        <button type="reset" class="btn btn-primary " id="changeconfirme">Reset</button>      
        </form>
        </div>
        
        <!-- Modal footer -->
         
        <div class="modal-footer" id="chngereseponse">

        </div>
        
      </div>
    </div>
  </div>
