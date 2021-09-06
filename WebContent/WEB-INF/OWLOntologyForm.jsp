<form>
  <fieldset class="well">
    <legend class="well-legend">OWL</legend>
    <div class="form-group col-md-6">
					<label for="owl_name">OWL Name:</label> <input
						type="text" class="form-control" id="owl_name"
						placeholder="Enter OWL Name" required="required">
				</div>
				<!-- Default input -->
	<div class="row">
	<div class="form-group col-md-6">
		<label for="URL">File Path(URL): </label> <input type="text"
						class="form-control" id="URL"
						placeholder="Enter URL" required="required">
	</div>
	<input type="file" id="fileUpload">
	</div>
	<button class="btn btn-primary btn-md" id="renderBtn" onclick="addlOWL_Ontology()">Add</button>
	<br>
	<br>
		<div id="owl_ontology_show" style="overflow: auto;height: 200px !important;">
		<c:if test="${not empty sessionScope.conn}">
			<sql:query dataSource="${snapshot}" var="resultlOWLOntology">
                   SELECT * from owl_ontology
                 </sql:query>
			<table class="table">
				<thead class="thead-dark">
					<tr>
						<th>#</th>
						<th>Name</th>
						<th>URL </th>
					</tr>

					<c:forEach var="row" items="${resultlOWLOntology.rows}">
						<tr>
							<td><c:out value="${row.OWL_ID}" /></td>
							<td><c:out value="${row.OWL_NAME}" /></td>
							<td><c:out value="${row.URL}" /></td>
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
										name="${row.OWL_ID}" onclick="owlontologydelete(this.name);">
										<i class="fa fa-trash"></i>
									</button>
							</li>
							</td>
						</tr>
					</c:forEach>
			</table>
		</c:if>
	</div>
  </fieldset>
</form>