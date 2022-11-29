

<div class="container" >
  <h2>Semantic Query Preparation</h2>
  <p> Setting up Semantic query parameters </p> 
  <br>
  <!-- Nav tabs -->
  <ul class="nav nav-tabs" role="tablist">
  <!--  
    <li class="nav-item">
      <a class="nav-link active" data-toggle="tab" href="#internal_ontology">Internal Ontology</a>
    </li>
    -->
    <li class="nav-item">
      <a class="nav-link" data-toggle="tab" href="#external_ontology"> Ontology Manager</a>
    </li>
    <li class="nav-item">
      <a class="nav-link" data-toggle="tab" href="#semantic_predicates">Semantic Predicates</a>
    </li>
  </ul>

  <!-- Tab panes -->
  <div class="tab-content" style="height: 1830px;">
    <div id="internal_ontology" class="container tab-pane active"><br>
      <h3>Internal Ontology</h3>
    <!--    <p>Please fill in the fields.</p> -->
      <form>
  <fieldset class="well">
    <legend class="well-legend">Database Networks</legend>
   <%@ include file="DatabaseNetworksForm.jsp" %>
  </fieldset>
</form>
<form>
   <fieldset class="well">
    <legend class="well-legend">Database Models</legend>
   <%@ include file="DatabaseModelsForm.jsp" %>
  </fieldset>
</form>   
      
    </div>
    <div id="external_ontology" class="container tab-pane fade"><br>
      <h3> Ontology Manager</h3>
      <%@ include file="OWLOntologyForm.jsp" %>
      
<form>
  <fieldset class="well">
    <legend class="well-legend">RDF(S)</legend>
    ... your inputs ...
  </fieldset>
</form>
<form>
  <fieldset class="well">
    <legend class="well-legend">WordNet</legend>
    ... your inputs ...
  </fieldset>
</form>
<form>
  <fieldset class="well">
    <legend class="well-legend">YAGO</legend>
    ... your inputs ...
  </fieldset>
</form>
<form>
  <fieldset class="well">
    <legend class="well-legend">MeSH</legend>
    ... your inputs ...
  </fieldset>
</form>
<form>
  <fieldset class="well">
    <legend class="well-legend">NOMED-CT</legend>
    ... your inputs ...
  </fieldset>
</form>
<form>
  <fieldset class="well">
    <legend class="well-legend">DO</legend>
    ... your inputs ...
  </fieldset>
</form>
<form>
  <fieldset class="well">
    <legend class="well-legend">OBO</legend>
    ... your inputs ...
  </fieldset>
</form>
      </div>
    <div id="semantic_predicates" class="container tab-pane fade"><br>
      <h3>Semantic Predicates</h3>
      <p>Please fill in the fields.</p>
      <%@ include file="SemanticPredicatesForm.jsp" %>
    </div>
  </div>
</div>
