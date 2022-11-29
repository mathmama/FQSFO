

<!-- ************** -->
<c:if test="${not empty sessionScope.conn}">
         <sql:query dataSource = "${snapshot}" var = "semantic_predicate">
            SELECT * FROM semantic_predicate
         </sql:query>
           <sql:query dataSource = "${snapshot}" var = "resulttables">
            SELECT table_name FROM all_tables where  OWNER='USER1' AND table_name not in ('LINGUISTIC_VARIABLE_TABLE','LINGUISTIC_VALUE_TABLE','POSSIBILITY_TABLE','POSSIBILITY')
         </sql:query>
 

	<div class="container">
		<div class="row">

             <div class="col-md-6">
				<label for="Tables_SP">Tables : </label>
				 <select id="Tables_SP">
				 <option disabled>---------SELECT A TABLE-------</option>
					<c:forEach var="row" items="${resulttables.rows}">
						<option value="${row.table_name}" name="${row.table_name}">${row.table_name}</option>
						 </c:forEach>
				</select>
			</div>
			<div class="col-md-6">
				<label for="Columns_SP">Non-Scalar Columns : </label>
				 <select id="Columns_SP">
						<option>---------SELECT A TABLE-------</option>
				</select>
			</div>
			</div>
			
			<div class="row">
			<div class="col-md-6">
				<label for="selectOntologyType">Ontology Type : </label>
				 <select id="selectOntologyType">
				 <option disabled>---- General Semantic Graphs ----</option>
				 <option value="OWL">OWL</option>
				 <option value="RDF">RDF(S)</option>
				 <option value="WORDNET">WordNet</option>
				 <option value="YAGO">YAGO</option>
				  <option disabled>---- Biomedical Semantic Graphs ----</option>
				 <option value="GO">GO</option>
				 <option value="MESH">MeSH</option>
				 <option value="SNOMED-CT">SNOMED-CT</option>
				 <option value="DO">DO</option>
				 <option value="OBO">OBO</option> 
				</select>
			</div>
			
			<div class="col-md-6">
				<label for="selectOntologyReference"> Ontology Reference : </label>
				 <select id="selectOntologyReference">
				 <option>---------SELECT A ONTOLOGY TYPE -------</option>
				</select>
			</div>
			</div>
			<div class="row">
			    <div class="col-md-6">
				<label for="selectSemanticMeasure">Semantic Measure :</label> 
					<select class="form-select" id="selectSemanticMeasure">
					<option disabled>---- Graph-based Semantic measures ----</option>
					<option value="1">#1# Sanchez et al.</option>
					<option value="2">#2# Seco et al.</option>
					<option value="3">#3# Zhou et al.</option>
					<option value="4">#4# Depth Max Non-Linear</option>
					<option value="5">#5# Depth Min Non-linear</option>
					<option disabled>---- Edge-based measures ----</option>
						<option value="6">#6# Rada 1989</option>
						<option value="7">#7# Rada 1989 LCA</option>
						<option value="8">#8# Wu & Palmer 1994</option>
						<option value="9">#9# Leacock and Chodorow 1998</option>
						<option value="10">#10# Stojanovic 2001</option>
						<option value="11">#11# Pekar and Staab 2002</option>
						<option value="12">#12# Kyogoku 2011</option>
					<option disabled>---- IC-based measures ----</option>
						<option value="13">#13# Resnik 1995</option>
						<option value="14">#14# Lin 1998</option>
						<option value="15">#15# Jiang and Conrath 1997</option>
						<option value="16">#16# Jiang and Conrath Normalized 1997</option>
						<option value="17">#17# Schlicker SimRel 2006</option>
						<option value="18">#18# Jaccard IC</option>
						<option value="19">#19# Jaccard 3W IC</option>
						<option value="20">#20# Gower and Legendre IC</option>
						<option value="21">#21# Tversky IC</option>
					<option disabled>---- Set-based measures ----</option>
						<option value="22">#22# Batet 2010</option>
						<option value="23">#23# Knappe 2004</option>
						<option value="24">#24# Bader 2003</option>
						<option value="25">#25# Maryland Bridge 2003</option>
						<option value="26">#26# Korbel 2002</option>	
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
            <th>Ontology Type</th>
            <th>Ontology Reference</th>
            <th>Semantic Measure</th>
         </tr>
         
         <c:forEach var = "row" items = "${semantic_predicate.rows}">
            <tr>
               <td> <c:out value = "${row.Sem_Pred_ID}"/></td>
               <td> <c:out value = "${row.TABLE_NAME}"/></td>
               <td> <c:out value = "${row.COLUMN_NAME}"/></td>
               <td> <c:out value = "${row.ontology_type}"/></td>
               <td> <c:out value = "${row.ontology_ref}"/></td>
               <td> <c:out value = "${row.semantic_measure}"/></td>
               <td> <li class="list-inline-item">  <button class="btn btn-success btn-sm rounded-0" type="button" data-toggle="tooltip" data-placement="top" title="EditFuzzyPredicate" name="${row.POSSID}"><i class="fa fa-edit"></i></button> </li>
                <li class="list-inline-item">  <button class="btn btn-danger btn-sm rounded-0" type="button" data-toggle="tooltip" data-placement="top" title="DeleteFuzzyPredicate" name="${row.POSSID}" onclick="DeleteFuzzyPredicate(this.name);"><i class="fa fa-trash"></i></button> </li></td>
            </tr>
         </c:forEach>
      </table>
      </div>

       </c:if>