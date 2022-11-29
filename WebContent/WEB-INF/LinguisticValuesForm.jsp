
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
				<label for="dbmsVersion">Linguistic Variable Name : </label>
				 <select id="lvselect">
					<c:forEach var="row" items="${resultlvid.rows}">
						<option value="${row.linguistic_variable_id}" name="${row.maximum_value}">${row.linguistic_variable_name}</option>
						 </c:forEach>
				</select>
			</div>
			<div class="form-row">
				<div class="form-group col-md-6">
					<label for="LinguisticValueName">Linguistic Value Name</label> <input
						type="text" class="form-control" id="LinguisticValueName"
						placeholder="Enter Linguistic Value Name">
				</div>
				<div class="form-group col-md-6">
					<label for="MembershipFunctionType">Membership function type</label> 
					<select class="form-select" id="MembershipFunctionType">
						<option value="Trapezoidal">Trapezoidal</option>
						<option value="Triangular">Triangular</option>
						<option value="L Function">L Function</option>
						<option value="Gamma Function">Gamma Function</option>
					</select>
				</div>
			</div>

			<div class="form-row">
				<!-- Default input -->
				<div class="form-group col-md-6">
					<label for="Alpha">&alpha; = </label> <input
						type="text" class="form-control" id="Alpha"
						placeholder="Enter Alpha Number" required="required">
				</div>
				<!-- Default input -->
				<div class="form-group col-md-6">
					<label for="Beta">&beta;= </label> <input type="text"
						class="form-control" id="Beta"
						placeholder="Enter Beta Number" required="required">
				</div>
				<div class="form-group col-md-6">
					<label for="Gamma"> &gamma;= </label> <input
						type="text" class="form-control" id="Gamma"
						placeholder="Enter Gamma Number" required="required">
				</div>
				<!-- Default input -->
				<div class="form-group col-md-6">
					<label for="Lambda">&lambda;= </label> <input
						type="text" class="form-control" id="Lambda"
						placeholder="Enter Lambda Number" required="required">
				</div>
			</div>


			<button class="btn btn-primary btn-md" id="DrawLvv">Draw</button>
			<button class="btn btn-primary btn-md" id="renderBtn" onclick="addlvv()">Add</button>
		</div>
		<div class="col">
			<!--  <div class="chart-container" style="height: 40vh; width: 40vw"> -->
			<canvas id="myChart" style="height: 40vh;width: 30vw;"></canvas>
		</div>
	</div>
	</div>
	<!--  </div> -->
	<br>
	<div id="lvvshow" style="overflow: auto;height: 400px !important;">
		<c:if test="${not empty sessionScope.conn}">
			<sql:query dataSource="${snapshot}" var="resultlvv">
                   SELECT * from LINGUISTIC_VALUE_VIEW
                 </sql:query>
			<table class="table">
				<thead class="thead-dark">
					<tr>
						<th>#</th>
						<th>Name</th>
						<th>Linguistic Variable </th>
						<th>Membership Fucntion Type</th>
						<th>Alpha</th>
						<th>Beta</th>
						<th>Gamma</th>
						<th>Lambda</th>
					</tr>

					<c:forEach var="row" items="${resultlvv.rows}">
						<tr>
							<td><c:out value="${row.LINGUISTIC_VALUE_ID}" /></td>
							<td><c:out value="${row.LINGUISTIC_VALUE}" /></td>
							<td><c:out value="${row.LINGUISTIC_VARIABLE_NAME}" /></td>
							<td><c:out value="${row.MEMBERSHIP_FUNCTION_TYPE}" /></td>
							<td><c:out value="${row.ALPHA}" /></td>
							<td><c:out value="${row.BETA}" /></td>
							<td><c:out value="${row.GAMMA}" /></td>
							<td><c:out value="${row.LAMBDA}" /></td>
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
