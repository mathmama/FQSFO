

<!-- ************** -->
<c:if test="${not empty sessionScope.conn}">
         <sql:query dataSource = "${snapshot}" var = "resultfp">
             SELECT * from fuzzy_predicate_view
         </sql:query>
           <sql:query dataSource = "${snapshot}" var = "resulttables">
            SELECT table_name FROM all_tables where  OWNER='USER1' AND table_name not in ('LINGUISTIC_VARIABLE_TABLE','LINGUISTIC_VALUE_TABLE','FUZZY_PREDICATE_TABLE')
         </sql:query>
 

	<div class="container">
		<div class="row">

             <div class="col-md-6">
				<label for="Tables">Tables : </label>
				 <select id="Tables">
					<c:forEach var="row" items="${resulttables.rows}">
						<option value="${row.table_name}" name="${row.table_name}">${row.table_name}</option>
						 </c:forEach>
				</select>
			</div>
			<div class="col-md-6">
				<label for="Columns">Scalar Columns : </label>
				 <select id="Columns">
						<option disabled>---------SELECT A TABLE-------</option>
				</select>
			</div>
			</div>
			
			<div class="row">
			<div class="col-md-4">
				<label for="LvselectPredicat">Linguistic Variable Name : </label>
				 <select id="LvselectPredicat">
					<c:forEach var="row" items="${resultlvid.rows}">
						<option value="${row.linguistic_variable_id}" name="${row.maximum_value}">${row.linguistic_variable_name}</option>
						 </c:forEach>
				</select>
			</div>
			    <div class="col-md-4">
				<label for="Status">Status</label> 
					<select class="form-select" id="Status">
						<option value="VALID">VALID</option>
						<option value="INVALID">INVALID</option>
					</select>
			</div>
			</div>
			<br>
			<button class="btn btn-primary btn-md" name="addPredicate" id="addPredicate" onclick="AddFuzzyPredicate()">Add</button>
			</div>
			<br>
	<div id="FuzzyPredicatesShow" >
      <table class="table">
      <thead class="thead-dark">
         <tr>
            <th>#</th>
            <th>Table Name</th>
            <th>Column Name</th>
            <th>ID & Variable Name</th>
            <th>Status</th>
         </tr>
         
         <c:forEach var = "row" items = "${resultfp.rows}">
            <tr>
               <td> <c:out value = "${row.POSSID}"/></td>
               <td> <c:out value = "${row.TABLE_NAME}"/></td>
               <td> <c:out value = "${row.COLUMN_NAME}"/></td>
               <td> <c:out value = "${row.id_lvr_name}"/></td>
               <td> <c:out value = "${row.STATUS}"/></td>
               <td> <li class="list-inline-item">  <button class="btn btn-success btn-sm rounded-0" type="button" data-toggle="tooltip" data-placement="top" title="EditFuzzyPredicate" name="${row.POSSID}"><i class="fa fa-edit"></i></button> </li>
                <li class="list-inline-item">  <button class="btn btn-danger btn-sm rounded-0" type="button" data-toggle="tooltip" data-placement="top" title="DeleteFuzzyPredicate" name="${row.POSSID}" onclick="DeleteFuzzyPredicate(this.name);"><i class="fa fa-trash"></i></button> </li></td>
            </tr>
         </c:forEach>
      </table>
      </div>

       </c:if>