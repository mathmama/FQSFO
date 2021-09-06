<%@page import="java.sql.*"%>
<%@page import="com.octest.beans.*" %>
<%
String HostName = request.getParameter("HostName");
String ConnexionUserName = request.getParameter("ConnexionUserName");
String ConnexionPassword = request.getParameter("ConnexionPassword");
String PortNumber = request.getParameter("PortNumber");
String Sid = request.getParameter("Sid");
String oracleurl;
%>
<div class="container">
	<div class="row">
	<div class="col-12">
	<!--  ${sessionScope.username}       ${sessionScope.nameuseroracle}   -->
			<c:choose>
				<c:when test="${not empty sessionScope.conn}">
					<div class="alert alert-primary text-center" role="alert">
						<p>Connexion Successful</p>
						<p><%=session.getAttribute("oracleconnection").toString()%></p>
						<sql:setDataSource var = "snapshot" driver = "oracle.jdbc.driver.OracleDriver"
                         url = "${oracleurl}" user = "user1"  password ="user1"/>
                         
						<form method="post">
						<button type="submit" class="btn btn-primary btn-md" id="disconnectedbd" name="disconnecteddb">Disconnected</button>
						</form>
					</div>
				</c:when>
				<c:when test="${not empty sessionScope.cnerrors}">
					<div class="alert alert-danger text-center">
						<p>Warning</p>
						<p><%=session.getAttribute("cnerrors")%>
						<p>
					</div>
				</c:when>
				<c:otherwise>
					<div class="alert alert-danger text-center">please fill in
						the fields</div>
				</c:otherwise>
			</c:choose>
		</div>	
		<div class="col">
			
			<h4 style="text-align: center">Connection Form</h4>
			<hr>
			<div class="cnx-form">
				<form method="post">
					<!-- Grid row -->
					<div class="form-row">
						<div class="form-group col-md-6">
							<label for="connexionName">Connection Name</label> <input
								type="text" class="form-control" name="connexionName"
								placeholder="Enter Connexion Name"  required="required">
						</div>
					</div>
					<div class="form-row">
						<!-- Default input -->
						<div class="form-group col-md-6">
							<label for="connexionUserName">User</label> <input type="text"
								class="form-control" name="ConnexionUserName"
								placeholder="Enter User Name" required="required"
								<c:if test="${not empty ConnexionUserName}"> 
								value="<%=ConnexionUserName%>" 
								</c:if>>
						</div>
						<!-- Default input -->
						<div class="form-group col-md-6">
							<label for="connexionPassword">Password</label> <input
								type="password" class="form-control" name="ConnexionPassword"
								placeholder="Enter Password" required="required"
								<c:if test="${not empty ConnexionPassword}"> 
								value="<%=ConnexionPassword%>"
								</c:if>>
						</div>
					</div>
					<!-- Grid row -->

					<!-- Default input -->
					<div class="form-group">
						<label for="dbmsVersion">DBMS Version : </label> <select
							name="dbmsVersion">
							<option value="oracle">Oracle</option>
							<option value="mysql">MySQL</option>
							<option value="sqlserver">SQL Server</option>
							<option value="postgressql">PostgresSQL</option>
							<option value="sqlite">SQLite</option>
						</select>
					</div>

					<div class="form-row">
						<!-- Default input -->
						<div class="form-group col-md-6">
							<label for="hostName">Host Name</label> <input type="text"
								class="form-control" name="HostName"
								placeholder="Enter Host Name" required="required"
								<c:if test="${not empty HostName}"> 
								value="<%=HostName%>"
								</c:if>>
						</div>
						<!-- Default input -->
						<div class="form-group col-md-6">
							<label for="portNumber">Port Number</label> <input type="text"
								class="form-control" name="PortNumber"
								placeholder="Enter Port Number" required="required"
								<c:if test="${not empty PortNumber}"> 
								value="<%=PortNumber%>"
								</c:if>>
						</div>
					</div>

					<!-- Default input -->
					<div class="form-group">
						<label for="sid">SID</label> <input type="text"
							class="form-control" name="Sid" placeholder="Enter SID"
							<c:if test="${not empty Sid}"> 
								value="<%=Sid%>"
								</c:if>>
					</div>
					<!-- Grid row -->
					<button type="submit" class="btn btn-primary btn-md">Save</button>
					<button class="btn btn-primary btn-md">Connection test</button>
					<button type="submit" class="btn btn-primary btn-md" id="connexion"
						name="connectiondb" value="home">Connection</button>
					<button type="reset" class="btn btn-primary btn-md">Reset</button>
				</form>
			</div>
		</div>
		<div class="col">
			<h4 style="text-align: center">Connections List</h4>
			<hr>
<div class="cnx-form">
<form method="post">
<div class="select select--multiple">
 <select id="multi-select" name ="idcnx" multiple>
            <%
            try {
            	
            Connection conn = ConnectionProvider.getCon();
			conn.setAutoCommit(false);
                Statement stat = conn.createStatement();
                String UserEmail=(String)session.getAttribute("UserEmail");
                String query ="select *  from CONNECTIONS where EMAIL=\'"+UserEmail+"\';";
                ResultSet rs = stat.executeQuery(query);
                while (rs.next()) {
                	out.println("<option value="+rs.getInt("ID")+">"+rs.getString("CONNECTIONNAME")+":"+rs.getString("CONNECTIONSTRING")+"</option>");
                }
            } catch (Exception e) {
    			System.out.println(e.getClass().getName() + ": " + e.getMessage());
    		}

            %>
  </select>
  <span class="focus"></span>
  <br>
</div>
<button type="submit" class="btn btn-primary btn-md" id="connectiondburl" name="connectiondburl" value="home">Connection</button>
</form>
</div>


		</div>

	</div>

</div>
