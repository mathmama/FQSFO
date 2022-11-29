<div class="container" >
  <h2>Fuzzy Query Preparation</h2>
  <p> Setting up fuzzy query parameters </p> 
  <br>
  <!-- Nav tabs -->
  <ul class="nav nav-tabs" role="tablist">
    <li class="nav-item">
      <a class="nav-link active" data-toggle="tab" href="#linguistic_variables">Linguistic Variables</a>
    </li>
    <li class="nav-item">
      <a class="nav-link" data-toggle="tab" href="#linguistic_values">Linguistic Values</a>
    </li>
    <li class="nav-item">
      <a class="nav-link" data-toggle="tab" href="#fuzzy_predicates">Fuzzy Predicates</a>
    </li>
  </ul>

  <!-- Tab panes -->
  <div class="tab-content" style="height: 1000px;">
    <div id="linguistic_variables" class="container tab-pane active"><br>
      <h3>Linguistic Variables</h3>
      <p>please enter the name of the variable and the maximum value.</p>
      <%@ include file="LinguisticVariablesForm.jsp" %>
    </div>
    <div id="linguistic_values" class="container tab-pane fade"><br>
      <h3>Linguistic Values</h3>
      <p>Please fill in the fields.</p>
      <%@ include file="LinguisticValuesForm.jsp" %>
      </div>
    <div id="fuzzy_predicates" class="container tab-pane fade"><br>
      <h3>Fuzzy Predicates</h3>
      <p>Please fill in the fields.</p>
      <%@ include file="FuzzyPredicatesForm.jsp" %>
    </div>
  </div>
</div>