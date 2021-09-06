

<div class="container">
             
                <div class="col-xs-12 ">
                  <nav>
                    <div class="nav nav-tabs nav-fill" id="nav-tab" role="tablist">
                      <a class="nav-item nav-link active" id="nav-connexion-tab" data-toggle="tab" href="#nav-connexion" role="tab" aria-controls="nav-home" aria-selected="true">Database Connection</a>
                      <a class="nav-item nav-link <c:if test="${empty sessionScope.conn}"> disabled</c:if> " id="nav-fuzzy-database-modeling-tab" data-toggle="tab" href="#nav-fuzzy-database-modeling" role="tab" aria-controls="nav-fuzzy-database-modeling" aria-selected="false">Fuzzy Query Preparation</a>
                      <a class="nav-item nav-link  <c:if test="${empty sessionScope.conn}"> disabled</c:if>" id="nav-ontologie-tab" data-toggle="tab" href="#nav-ontologie" role="tab" aria-controls="nav-ontologie" aria-selected="false">Semantic Query Preparation</a>
                      <a class="nav-item nav-link <c:if test="${empty sessionScope.conn}"> disabled</c:if> " id="nav-terminal-tab" data-toggle="tab" href="#nav-terminal" role="tab" aria-controls="nav-terminal" aria-selected="false">SQLf Terminal</a>
                      <a class="nav-item nav-link <c:if test="${empty sessionScope.conn}"> disabled</c:if>" id="nav-visual-consultation-tab" data-toggle="tab" href="#nav-visual-consultation" role="tab" aria-controls="nav-visual-consultation" aria-selected="false">Visual consultation</a>
                      <a class="nav-item nav-link <c:if test="${empty sessionScope.conn}"> disabled</c:if>" id="nav-help-tab" data-toggle="tab" href="#nav-help" role="tab" aria-controls="nav-help" aria-selected="false">Help</a>
                    </div>
                  </nav>
                  <div class="tab-content py-3 px-3 px-sm-0" id="nav-tabContent">
                    <div class="tab-pane fade show active" id="nav-connexion" role="tabpanel" aria-labelledby="nav-connexion-tab">
                      <%@ include file="connexion.jsp" %>
                    </div>
                    <div class="tab-pane fade" id="nav-fuzzy-database-modeling" role="tabpanel" aria-labelledby="nav-fuzzy-database-modeling-tab">
                      <%@ include file="fuzzyquerypreparation.jsp" %> 
                    </div>
                    <div class="tab-pane fade" id="nav-ontologie" role="tabpanel" aria-labelledby="nav-ontologie-tab">
                     <%@ include file="semanticquerypreparation.jsp" %>
                     </div>
                    <div class="tab-pane fade" id="nav-terminal" role="tabpanel" aria-labelledby="nav-terminal-tab">
                     <%@ include file="SQLfTerminal.jsp" %>
                     </div>
                    <div class="tab-pane fade" id="nav-visual-consultation" role="tabpanel" aria-labelledby="nav-visual-consultation-tab">
                      visual consultation
                    </div>
                    <div class="tab-pane fade" id="nav-help" role="tabpanel" aria-labelledby="nav-help-tab">
                      help
                    </div>
                  </div>
                
                </div>
              </div>
        
<!-- Modal -->
<!--  
  <div class="modal fade" id="myModal" role="dialog">
    <div class="modal-dialog">
    -->
      <!-- Modal content-->
      <!--  
      <div class="modal-content">
        <div class="modal-header">
          <button type="button" class="close" data-dismiss="modal">&times;</button>
          <h4 class="modal-title">Modal Header</h4>
        </div>
        <div class="modal-body">
          <p>Some text in the modal.</p>
        </div>
        <div class="modal-footer">
          <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
        </div>
      </div>
      
    </div>
  </div>
  -->