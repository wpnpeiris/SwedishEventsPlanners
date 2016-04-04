$(function() {
//	Initialize Select pickers
	$('#ref_mock').selectpicker();
	$('#ref_type').selectpicker();
    $('#ref_pref').selectpicker();
    
//    Initialize Date pickers
    $('#ref_fromdate').datetimepicker({
    	format: 'YYYY-MM-DD h:mm A'
    });
    
    $('#ref_todate').datetimepicker({
    	format: 'YYYY-MM-DD h:mm A'
    });
    
    
//    Initialize table view
    $("table").tablesorter({
		theme : "bootstrap",
//		sortList: [[6,1]],
		widthFixed: true,
		headerTemplate : '{content} {icon}', 
		widgets : [ "uitheme",  "zebra", "filter" ],

		widgetOptions : {
			zebra : ["even", "odd"],
			filter_reset : ".reset",
			filter_cssFilter: "form-control",
		}
	}).tablesorterPager({
		container: $(".ts-pager"),
		cssGoto  : ".pagenum",
		removeRows: false,
		output: '{startRow} - {endRow} / {filteredRows} ({totalRows})'
	});
    
    
    $('#newBtn').click(function() {
    	resetForm();
    	$("#clientRequestForm").collapse('show');
    	
    });

    $('#cancelBtn').click(function() {
    	$("#clientRequestForm").collapse('hide');
    	
    });
    
    $('#saveBtn').click(function() {
    	clientRequest = constructClientRequest();
    	saveClientRequest(clientRequest);
    	
    });
    
   $('#ref_mock').change(function() {
	   var role = $('#ref_mock').val();
	   if(role == 'role1') {
		   $('#newBtn').show();
	   } else {
		   $('#newBtn').hide();
	   }
	   
	   loadClientRequest(role);
   });
   $('#ref_mock').trigger("change");
});

function constructClientRequest() {
	var id = $("#ref_id").val(); 
	var name = $("#ref_name").val(); 
	var type = $("#ref_type").val(); 
	var pref = $("#ref_pref").val(); 
	var fromDate = $("#ref_fromdate").data('date');
	var toDate = $("#ref_todate").data('date');
	var attendees = $("#ref_attendees").val(); 
	var budget = $("#ref_budget").val(); 
	var status = $("#ref_status").val(); 
	var assignee = $("#ref_assignee").val(); 
	
	var clientRequest = {
			id: id,
			name: name,
			type: type,
			pref: pref,
			fromDate: fromDate,
			toDate: toDate,
			attendees: attendees,
			budget: budget,
			status: status,
			assignee: assignee
	};
	
	return clientRequest;
};

function saveClientRequest(clientRequest){
	var method = 'POST';
	
	if(clientRequest.id == "") {
		method = 'POST';
	} else {
		method = 'PUT';
	}
	
	$.ajax({
		 type: method,
		 url: 'http://localhost:8080/clientrequest',
		 data: JSON.stringify(clientRequest),
		 dataType: "json",
		 contentType: "application/json; charset=utf-8",
		 success: function(){
			 alert("Success");
			 loadClientRequest($('#ref_mock').val());
		  }, 
		  error: function() {
			  alert("Error");
		  }
	});

};

function  loadClientRequest(userRole) {
	$('tbody').empty();
	$.get( "/clientrequest/" + userRole, function( data ) {
		  var tablebody = "";
		  $.each( data, function( key, value ) {
			  tablebody += "<tr>";
			  tablebody += "<td>" +  value.id + "</td>";
			  tablebody += "<td>" +  value.name + "</td>";
			  tablebody += "<td>" +  value.type + "</td>";
			  tablebody += "<td>" +  value.fromDate + "</td>";
			  tablebody += "<td>" +  value.toDate + "</td>";
			  tablebody += "<td>" +  value.attendees + "</td>";
			  tablebody += "<td>" +  value.budget + "</td>";
			  tablebody += "<td>";
			  tablebody += "  <a id='editBtn' href='#' class='btn btn-sm btn-default glyphicon glyphicon-pencil' aria-hidden='true' ";
			  tablebody += "      data-id ='" +  value.id + "' ";
			  tablebody += "      data-name ='" +  value.name + "' ";
			  tablebody += "      data-type ='" +  value.type + "' ";
			  tablebody += "      data-pref ='" +  value.pref + "' ";
			  tablebody += "      data-fromdate ='" +  value.fromDate + "' ";
			  tablebody += "      data-todate ='" +  value.toDate + "' ";
			  tablebody += "      data-attendees ='" +  value.attendees + "' ";
			  tablebody += "      data-budget ='" +  value.budget + "' ";
			  tablebody += "      data-status ='" +  value.status + "' ";
			  tablebody += "      data-assignee ='" +  value.assignee + "' ";
			  tablebody += "      data-comments ='" +  value.comments + "' ";
			  tablebody += "   ></a></td>";  
			  
			  tablebody += "</tr>";
		  });
		  
		  $('tbody').append(tablebody);
		  $("table").trigger("update");
		  $('td a').click(function(e) {
			  fillForm(e);
			  $("#clientRequestForm").collapse('show');
		  });
	});
};

function resetForm() {
	$("#ref_id").val(""); 
	$("#ref_name").val(""); 
	$("#ref_type").selectpicker('val', 'evnet1'); 
	$("#ref_pref").selectpicker('val', 'pref1'); 
	$("#ref_fromdate").data('DateTimePicker').date(null);
	$("#ref_todate").data('DateTimePicker').date(null);
	$("#ref_attendees").val(10);
	$("#ref_status").val('Open'); 
	$("#ref_assignee").val('role1'); 
	$("#ref_budget").val(1000); 
};

function fillForm(e) {
	$("#ref_id").val($(e.target).data('id')); 
	$("#ref_name").val($(e.target).data('name')); 
	$("#ref_type").selectpicker('val', $(e.target).data('type')); 
	$("#ref_pref").selectpicker('val', $(e.target).data('pref')); 
	$("#ref_fromdate").data('DateTimePicker').date($(e.target).data('fromdate'));
	$("#ref_todate").data('DateTimePicker').date($(e.target).data('todate'));
	$("#ref_attendees").val($(e.target).data('attendees'));
	$("#ref_status").val($(e.target).data('status')); 
	$("#ref_assignee").val($(e.target).data('assignee')); 
	$("#ref_budget").val($(e.target).data('budget'));
	$("#ref_comments").val($(e.target).data('comments'));
};