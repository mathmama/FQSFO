<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.Connection"%>
<%
Connection connection = null;
Statement statement = null;
ResultSet resultSet = null;
%>
<c:set var="fsqlquery" value="select * from emp" scope="page" />
<div class="container">
<fieldset class="well">
    <legend class="well-legend">SQLf Terminal: :</legend>
	<div>
		<div class="textarea-wrapper">
			<textarea rows="10" cols="80" name="sqlfquery" id="sqlfquery" size="20px"></textarea>
		</div>
	</div>
	</fieldset>
	<button class="btn btn-primary btn-md" id="sqlfrun" name="sqlfrun">Run</button>
<br>
<br>
  <fieldset class="well">
    <legend class="well-legend">SQL query generated :</legend>
   <div id="SQLquerygenerated">
						<p><%=session.getAttribute("sqlquery")%>
					</div>
  </fieldset>
					
<br>
	<!--  <label for="sqlfterminal">Result:</label> -->
	<fieldset class="well">
    <legend class="well-legend">Result :</legend>
	<div id="elapsed_time"></div>
	<div class="container-fluid bg-dark text-white " id="resultquery" style="height:600px; overflow-y: scroll;font-size: 12px;">
	<table id='resulttable' class="table text-white">
	</table>
	</div>
	</fieldset>
	
</div>