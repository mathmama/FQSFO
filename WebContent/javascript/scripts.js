/**
 * 
 */
/* script ajax for change password and get response */
/*
function navitemactive(status) {
	   var ConnexionName=$('#connexionName').val(); 
       var ConnexionUserName=$('#connexionUserName').val();
	   var ConnexionPassword=$('#connexionPassword').val();
       var DbmsVersion = $('#dbmsVersion').children("option:selected").val();
	   var HostName=$('#hostName').val();
       var PortNumber=$('#portNumber').val();
	   var Sid=$('#sid').val();
		alert(DbmsVersion);
		$("#nav-about-tab").removeClass(status);
}	
*/

$(document).ready(function(){	
$('button[title="EditLV"]').on('click', function(){
    var id = $(this).closest("tr").find('td:eq(0)').text();
    var lvname = $(this).closest("tr").find('td:eq(1)').text();
    var lvmaxvalue=$(this).closest("tr").find('td:eq(2)').text();
   // alert("id"+id);
      $('#LinguisticVariableId').val(id);
       $('#LinguisticVariableName').val(lvname);
      $('#MaximumValue').val(lvmaxvalue);
      document.getElementById('updatelinguisticvariable').style.visibility = 'visible';
});

	/********************* */
	
	$("#DrawLvv").click(function () {
		        var maxvalue=$('#lvselect').find('option:selected').attr("name");
				var label ;
				var text;
				var data = [0, 1, 1, 0];
				var mbtype=$('#MembershipFunctionType').val();
				var alpha = $('#Alpha').val();
				var beta= $('#Beta').val();
				var gamma = $('#Gamma').val();
				var lambda = $('#Lambda').val();
				text = $('#LinguisticValueName').val();
				
				switch(mbtype){
					case 'Trapezoidal':
					label=[alpha,beta,gamma,lambda]
					data = [0, 1, 1, 0];
					break;
					case 'Triangular':
					label=[alpha,beta,gamma]
					data = [0, 1, 0];
					break;
					case 'L Function':
					label=[0,beta,gamma]
					data = [1, 1, 0];
					break;
					case 'Gamma Function':
					label=[alpha,beta,maxvalue]
					data = [0, 1, 1];
					break;
					
					
				}
				
	var ctx = $("#myChart");
      var data = {
		labels : label,
		datasets : [
			{
				label : text,
				data : data ,
				backgroundColor : "blue",
				borderColor : "lightblue",
				fill : false,
				lineTension : 0,
				pointRadius : 5
			}
		]
	};

	var options = {
		title : {
			display : true,
			position : "top",
			text : text,
			fontSize : 18,
			fontColor : "#111"
		},
		legend : {
			display : true,
			position : "bottom"
		},
		scales: {
			yAxes: [{
				ticks: {
					max: 1,
					min: 0,
					stepSize: 0.1
				}
			}]
		},
	};


/*********** draw in table Lvv */


/**************** */
	
var chart = new Chart( ctx, {
		type : "line",
		data : data,
		options : options
	} );

    }
);

function drawlvv(data,options){
	
}
	
	
	/****************** */
	
	/************** */
$("button").click(function() {
    var fired_button = $(this).val();
var button_id=$(this).attr('id');
   if (button_id=='lvdelete'){
	//lvdelete(fired_button);
}
   
});
	
	/* **************/
	
	/*************/
$(document).ready(function() {
        $('#sqlfrun').click(function(event) {
	var startTime, endTime;
	startTime = new Date();
	var rows_number;
            var sqlfquery = $("#sqlfquery").val();
          jQuery.ajax( {
        	  url : "FlexibleQueryControl", 
              method : 'GET',
              data:{"sqlfquery": sqlfquery,},
              contentType : "application/json",
              success : function (data) {
            	  $("#resulttable td").remove();
                  $("#resulttable").append('<tr id="headerRow"></tr>');
                  
                  $.each(data[0], function (a, b) {
                      $("#headerRow").append(' <td >' + a + '</td>');
                  });
               
                  $.each(data, function (a, b) {
	              rows_number++;
                      $("#resulttable").append('<tr id="' + a + '"></tr>');
                      var dataRowId = '#' + a;
                      $.each(data[a], function (c, d) {
                          $(dataRowId).append('<td >' + d + '</td> ');
                      });
                  });
                 /* show response info */
                 endTime = new Date();
                 var timeDiff = endTime - startTime; //in ms         
                 $("#elapsed_time").html("All Rows Fetched: "+data.length+" in "+timeDiff+" ms");
                 $( "#SQLquerygenerated" ).load(window.location.href + " #SQLquerygenerated" );
                  
              },
              error : function (jqXHR,textStatus, errorThrown) {
            $("#resulttable").html('<td><h6>' + jqXHR.responseText + '</h6></td>');

              }
          });



//$(elapsed_time).append('All Rows Fetched: '+rows_number+' in '+timeDiff+' ms' );




        });

/**********************For get columns of selected table  in  fuzzy predicate section  */

$('#Tables').change(function() {
   var selected_table = $(this).val();
//alert(selected_table);

          $.ajax( {
        	  url : "GetTableColumns", 
              method : 'GET',
              data:{"selected_table": selected_table,"data_type":"NUMBER"},
              contentType : "application/json",
              success : function (data) {
	           $("#Columns").empty();
	           // alert("oui");
	                  $.each(data, function (a, b) {
                     // $("#columns").append('<option value='+a+'>'+a+'</option>' );

                      var dataRowId = '#' + a;
                      $.each(data[a], function (c, d) {
                         // $(dataRowId).append('<td >' + d + '</td> ');
                          
                          $("#Columns").append('<option value='+d+'>'+d+'</option>' );
                      });

                  });
                  
              },
              failure : function () {
                  alert("error...");
              }
          });

});


$('#fileUpload').on('change',function ()
        {
            var filename = $('input[type=file]').val();
            alert(filename);

        });

/**********************For get columns of selected table  (semantic) */

$('#Tables_SP').change(function() {
   var selected_table = $(this).val();
//alert(selected_table);

          $.ajax( {
        	  url : "GetTableColumns", 
              method : 'GET',
              data:{"selected_table": selected_table,"data_type":"VARCHAR2"},
              contentType : "application/json",
              success : function (data) {
	           $("#Columns_SP").empty();
	           // alert("oui");
	                  $.each(data, function (a, b) {
                     // $("#columns").append('<option value='+a+'>'+a+'</option>' );

                      var dataRowId = '#' + a;
                      $.each(data[a], function (c, d) {
                         // $(dataRowId).append('<td >' + d + '</td> ');
                          
                          $("#Columns_SP").append('<option value='+d+'>'+d+'</option>' );
                      });

                  });
                  
              },
              failure : function () {
                  alert("error...");
              }
          });

});

});

/****************/

	/***************** */
	$('#changeconfirme').click(function(event){
		var email=$('#email').val();
		var oldpassword=$('#oldpassword').val();
		var currentpassword=$('#currentpassword').val();
		/*alert(currentpassword);*/
		var newpassword=$('#newpassword').val();
		if(oldpassword!=currentpassword){
			$('#chngereseponse').text("Your old password is incorrect !! please try again");
		}else{
		$.post('ChangePassword',{email:email,oldpassword:oldpassword,newpassword:newpassword},function(responseText){
			$('#chngereseponse').text(responseText);
			// for to modify user password :
		
		});
		}
		$('#changepasswordform').get(0).reset();
	});
	

		
	});			
		
			
		function lvrefresh() {
			$( "#lvshow" ).load(window.location.href + " #lvshow" );
		}		
		
	function addlv(){
       var lvname=$('#LinguisticVariableName').val();
       var lvmaxvalue=$('#MaximumValue').val();
 		if(lvname==""){ 
			alert("Please enter the variable name");
 		}else if(isNaN(lvmaxvalue) || lvmaxvalue=="") {
			alert("Maximum Value is not a number or is empty");
				}else{
             		 $.ajax({
              		 type: "POST",
               		 url:"LinguisticVariableServlet",
               		 data:{"action":"add","lvname":lvname,"lvmaxvalue":lvmaxvalue},
               success: function (data) {
	            alert(data);
                lvrefresh();
               }
             }
);
}
}

function lvdelete(id){
if (confirm('Are you sure you want to delet this linguistic varaible ?')) {
$.ajax({
              		 type: "POST",
               		 url:"LinguisticVariableServlet",
               		 data:{"action":"delete","id":id,},
                     success: function (data) {
	                 alert(data);
                     lvrefresh();
               }
             }
);
} else {
  // Do nothing!
}
}
		//alert(HostName);
		//$("#nav-about-tab").removeClass(status);
		
		function resultrefresh() {
			$( "#result" ).load(window.location.href + " #result" );
		}
		
		function runsqlfquery(){
			var sqlfquery = $("#sqlfquery").val();
            alert(sqlfquery);
            resultrefresh();
		}

/****** update linguistic variable *******/
	function updatelv(){
       var lvname=$('#LinguisticVariableName').val();
       var lvmaxvalue=$('#MaximumValue').val();
       var id=$('#LinguisticVariableId').val();
 		if(lvname==""){ 
			alert("Please enter the variable name");
 		}else if(isNaN(lvmaxvalue) || lvmaxvalue=="") {
			alert("Maximum Value is not a number or is empty");
				}else{
             		 $.ajax({
              		 type: "POST",
               		 url:"LinguisticVariableServlet",
               		 data:{"action":"update","lvname":lvname,"lvmaxvalue":lvmaxvalue,"id":id},
               success: function (data) {
	            alert(data);
                lvrefresh();
               }
             }
);
}
}


/*
function UpdateLVActivation(){
	alert('ouii');
	//document.getElementById('updatelinguisticvariable').style.visibility = 'visible';
	   var id = $("#updaatelv").closest("tr").find('td:eq(0)').text();
    alert(id);
	
}
*/


/************ Functions for linguistic values *****/
  /** refresh display table***/
		function lvvrefresh() {
			$( "#lvvshow" ).load(window.location.href + " #lvvshow" );
		}
/****** delet  */
function lvvdelete(id){
	alert(id);
if (confirm('Are you sure you want to delet this linguistic value ?')) {
$.ajax({
              		 type: "POST",
               		 url:"LinguisticValueServlet",
               		 data:{"action":"delete","id":id,},
                     success: function (data) {
	                 alert(data);
                     lvvrefresh();
               }
             }
);
} else {
  // Do nothing!
}
}

// add :
	function addlvv(){
       var lvid=$('#lvselect').val();
       var lvvname = $('#LinguisticValueName').val();
       var lvvmtype=$('#MembershipFunctionType').val();
       var alpha =  $('#Alpha').val();
       var beta = $('#Beta').val();
       var gamma = $('#Gamma').val();
       var lambda = $('#Lambda').val();
 		if(lvvname=="" || alpha=="" || beta =="" || gamma=="" || lambda=="" ){ 
			alert("Please fill in the requested fields");
 		}else if(isNaN(alpha) || isNaN(beta) || isNaN(gamma) || isNaN(lambda)) {
			alert("Please enter correct values");
				}else{
					$.ajax({
              		 type: "POST",
               		 url:"LinguisticValueServlet",
               		 data:{"action":"add","lvid":lvid,"lvvname":lvvname,"lvvmtype":lvvmtype,"alpha":alpha,"beta":beta,"gamma":gamma,"lambda":lambda},
                     success: function (data) {
	                 alert(data);
                     lvvrefresh();
               }
             }
);
}
}

/* ############# Fuzzy Predicate Events ############### */

function FuzzyPredicatesShowRefresh() {
			$( "#FuzzyPredicatesShow" ).load(window.location.href + " #FuzzyPredicatesShow" );
		}		

/**********  Add Fuzzy Predicate *************/	
	function AddFuzzyPredicate(){
       var tablename=$('#Tables').val();
       var columnname=$('#Columns').val();
       var lvid=$('#LvselectPredicat').val();
       var status = $('#Status').val();

 		if(columnname=="---------SELECT A TABLE-------"){ 
			alert("Please select a table then a column name");
 		}else{
             		 $.ajax({
              		 type: "POST",
               		 url:"FuzzyPredicateServlet",
               		 data:{"action":"add","tablename":tablename,"columnname":columnname,"lvid":lvid,"status":status},
               success: function (data) {
	            alert(data);
                FuzzyPredicatesShowRefresh();
               }
             }
);
}
}

/************* Delete Fuzzy Predicate ************/
function DeleteFuzzyPredicate(id){
if (confirm('Are you sure you want to delete this fuzzy predicate ?')) {
$.ajax({
              		 type: "POST",
               		 url:"FuzzyPredicateServlet",
               		 data:{"action":"delete","id":id,},
                     success: function (data) {
	                 alert(data);
                     FuzzyPredicatesShowRefresh();
               }
             }
);
} else {
  // Do nothing!
}
}