
<!-- ************** -->
<c:if test="${not empty sessionScope.conn}">
<sql:query dataSource = "${snapshot}" var = "resultlvid">
            SELECT * from linguistic_variable_table order by linguistic_variable_id
         </sql:query>
</c:if>
         
<div class="container">

	<div class="row">
		<div class="col">

			<div class="form-group">
				<label for="NetworkOwner">Network Owner: </label>
				 <select id="NetworkOwner">
						<option value="MDSYS" name="MDSYS">MDSYS</option>		 
				</select>
			</div>
			<div class="form-row">
				<div class="form-group col-md-6">
					<label for="ModeleName">Model Name</label> <input
						type="text" class="form-control" id="LinguisticValueName"
						placeholder="Enter model name">
				</div>

			</div>
			</div>
			</div>
			<div class="form-row">
				<!-- Default input -->
				<div class="form-group col-md-6">
									<label for="AppTable">Application table : </label>
				 <select id="AppTable">
					<c:forEach var="row" items="${resulttables.rows}">
						<option value="${row.table_name}" name="${row.table_name}">${row.table_name}</option>
						 </c:forEach>
				</select> 
				</div>
				<!-- Default input -->
				<div class="form-group col-md-6">
									<label for="ColumnName">Column name : </label>
				 <select id="ColumnName">
						<option>---------SELECT A TABLE-------</option>
				</select>
				</div>
			</div>

			<button class="btn btn-primary btn-md" id="renderBtn" onclick="addsemanticmodel()">Create</button>
		</div>
		<div class="col">
		
</div>
</br>
	<div id="lvvshow" style="overflow: auto;height: 400px !important;">
		<c:if test="${not empty sessionScope.conn}">
			<sql:query dataSource="${snapshot}" var="resultlvv">
                  select * from MDSYS.rdf_model$ ORDER BY MODEL_ID DESC
                 </sql:query>
			<table class="table">
				<thead class="thead-dark">
					<tr>
						<th>#</th>
						<th>Model</th>
						<th>Owner </th>
						<th>Table</th>
						<th>Column Name</th>
						<th>Network</th>
					</tr>

					<c:forEach var="row" items="${resultlvv.rows}">
						<tr>
							<td><c:out value="${row.MODEL_ID}" /></td>
							<td><c:out value="${row.MODEL_Name}" /></td>
							<td><c:out value="${row.OWNER}" /></td>
							<td><c:out value="${row.TABLE_NAME}" /></td>
							<td><c:out value="${row.COLUMN_NAME}" /></td>
							<td><c:out value="MDSYS" /></td>
							<td>
								<li class="list-inline-item">
									<button class="btn btn-success btn-sm rounded-0" type="button"
										data-toggle="tooltip" data-placement="top" title="Edit">
										<i class="fa fa-edit"></i>
									</button>
							</li>
								<li class="list-inline-item">
									<button class="btn btn-danger btn-sm rounded-0" type="button"
										data-toggle="tooltip" data-placement="top" title="Delete"
										name="${row.LINGUISTIC_VALUE_ID}" onclick="lvvdelete(this.name);">
										<i class="fa fa-trash"></i>
									</button>
							</li>
							</td>
						</tr>
					</c:forEach>
			</table>
		</c:if>
	</div>
