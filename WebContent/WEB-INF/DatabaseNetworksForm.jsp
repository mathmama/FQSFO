


			<!-- Grid row -->
			<div class="form-row">
				<div class="form-group col-md-6">
				    <!--  <input type="hidden" class="form-control" id="LinguisticVariableId"> -->
					<label for="NetworkOwner">Network Owner</label>
					<input type="text" class="form-control" id="NetworkOwner"  value="MDSYS" placeholder="Enter Network Owner Name">
				</div>
				<div class="form-group col-md-6">
				    <!--  <input type="hidden" class="form-control" id="LinguisticVariableId"> -->
					<label for="NetworkName">Network Name</label>
					<input type="text" class="form-control" id="NetworkName" placeholder="Enter Network Name">
				</div>
				<div class="form-group col-md-6">
				<label for="tablespaceselect">Select Tablespace</label>
				<c:if test="${not empty sessionScope.conn}">
<sql:query dataSource = "${snapshot}" var = "tablespace">
            SELECT TABLESPACE_NAME FROM DBA_TABLESPACES ORDER BY TABLESPACE_NAME
         </sql:query>
         </c:if>
                   <select id="tablespaceselect">
					<c:forEach var="row" items="${tablespace.rows}">
						<option value="${row.TABLESPACE_NAME}" name="${row.TABLESPACE_NAME}">${row.TABLESPACE_NAME}</option>
						 </c:forEach>
				</select>
				</div>
			</div>
		<button class="btn btn-primary btn-md" name="addlinguisticvariable" id="addlinguisticvariable" onclick="addlv()">Create</button>
		<button class="btn btn-primary btn-md" style="visibility:hidden"  id="updatelinguisticvariable" onclick="updatelv()">Update</button>
		<br>
<!--  List of linguistic variables -->
<br>
<div id="lvshow" style="overflow: auto;height: 400px !important;">
<c:if test="${not empty sessionScope.conn}">
         <sql:query dataSource = "${snapshot}" var = "resultlv">
            SELECT * from linguistic_variable_table order by linguistic_variable_id
         </sql:query>
         
          
 
      <table class="table table-hover" >
      <thead class="thead-dark">
         <tr>
            <th>#</th>
            <th>Name</th>
            <th>Maximum Value</th>
            <th>User</th>
            <th>Date</th>
            
         </tr>
         
         <c:forEach var = "row" items = "${resultlv.rows}">
            <tr>
               <td> <c:out value = "${row.linguistic_variable_id}"/></td>
               <td> <c:out value = "${row.linguistic_variable_name}"/></td>
               <td> <c:out value = "${row.maximum_value}"/></td>
               <td> <c:out value = "${row.username}"/></td>
               <td> <c:out value = "${row.time_stamp}"/></td>
               <td> <li class="list-inline-item">  <button class="btn btn-success btn-sm rounded-0"  type="button" data-toggle="tooltip" data-placement="top" title="EditLV"  id="updaatelv" name="${row.linguistic_variable_id}"  ><i class="fa fa-edit"></i></button> </li>
                <li class="list-inline-item">  <button class="btn btn-danger btn-sm rounded-0" type="button" data-toggle="tooltip" data-placement="top" title="DeleteLV" name="${row.linguistic_variable_id}" onclick="lvdelete(this.name);"><i class="fa fa-trash"></i></button> </li></td>
            </tr>
         </c:forEach>
      </table>
 </c:if>
 </div>