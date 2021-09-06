<div class="container">
<nav class="navbar navbar-expand-lg navbar-light bg-light">
      <a style=" color: black ; font-family: Times New Roman, Times, serif;font-size:150%" >
          Flexible Query  System for Relational  Database
        </a>
 
  <div class="collapse navbar-collapse" id="navbarSupportedContent">

  </div>
  
      <ul class="navbar-nav ml-auto">
      <c:if test="${not empty sessionScope.User.getEmail()}">
      <li class="nav-item dropdown">
        <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
          <c:out value="${sessionScope.User.getFirst_Name()}"></c:out>
        </a>
        <div class="dropdown-menu" aria-labelledby="navbarDropdown">
          <a class="dropdown-item" href="#">Update profil</a>
          <a class="dropdown-item"  data-toggle="modal" href="#ModalChangePassword">Change password</a>
          <div class="dropdown-divider"></div>
          <form action="Logout" method="post" class="container bg-light">
            <button type="submit" class="btn btn-primary btn-md text-center"><i class="fas fa-sign-out-alt"></i> Log out</button>
          </form>
        </div>
      </li>
      </c:if>
      <li class="nav-item">
         <a class="nav-link" href="#">About</a>
    </li>
    </ul>
 
</nav>
</div>
<br>