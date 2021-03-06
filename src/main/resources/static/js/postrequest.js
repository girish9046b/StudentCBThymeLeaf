$(document).ready(function() {
	
	
	
	$(window).on('unload', function(){
		alert("unload");
		
	});
	
//	$(window).on('beforeunload', function(){
//	    return 'Are you sure you want to leave?';
//	});
	
	
	$("#postResultDiv").hide();
	$('.delStudent').click(function(event) {
		$("#postResultDiv").fadeIn(5000);
		var id = $(this).attr('id')
		if(confirm("Do you really want to delete Student with id "+id +"? Please confirm.")){
			var json = {
					"id" : id
				};
				$.ajax({
					type : "POST",
					contentType : 'application/json; charset=utf-8',
					dataType : 'html',
					url : "/deleteStudentDetails",
					data : id=id,
					//data : JSON.stringify(json),assign the value to an object(student) in controller
					success : function(result) {
						var obj = jQuery.parseJSON(result);
						console.log("SUCCESS: ", obj.status);
						if(obj.status==="Y"){
						$("#studentRow_"+id).remove();
						display(id);
					}
				else{
					displayError(id);
				}
					},
					error : function(e) {
						console.log("ERROR: ", e);
						displayError(e);
					},
					done : function(e) {
						console.log("DONE");
					}
				});
				$("#postResultDiv").fadeOut(3000);
		}
		else{
			
		}
		event.preventDefault();
	});
	
	
	$('.editStudent').click(function(event) {
		var id = $(this).attr('id');
		     closeModal();
			 openModal();
			document.getElementById("modal-content").innerHTML = "";
            $(".modal-content").load("/loadStudentDetails?id="+id);
		    event.preventDefault();
	});
	
	
	   
	   
	$('.updateButton').click(function(event) {

		var form = ($("#form").serialize());//works for thymeleaf
		form = unescape(form);
		//var form =$(this).form().serialize();works well with jsp
		var jsonForm = getJsonForm(form);
//		alert(form);
//		alert(unescape(form));
//		alert(unescape(JSON.stringify(jsonForm)));
		if(confirm("Do you really want to update Student ? Please confirm.")){
			
				$.ajax({
					type : "POST",
					contentType : 'application/json; charset=utf-8',
					dataType : 'html',
//					url : "/api/student/updateStudentCall",
					url : "/updateStudentDetails",
					  data : JSON.stringify(jsonForm),//assign the value to an object(student) in controller
					success : function(response, status) {
//						alert(response);
//						console.log(response);
						if(response.indexOf("errorValidation") != -1){
						document.getElementById("modal-content").innerHTML = "";
						$(".modal-content").html(response);
						}
						else{
							document.getElementById("modal-content").innerHTML = "";
							closeModal();
							 window.location.href ="/loadStudentList";
							//location.reload();
						}
							 
					},
					error : function(e) {
						console.log("ERROR: ", e);
						displayError(e);
					},
					done : function(e) {
						console.log("DONE");
					}
				});
				$("#postResultDiv").fadeOut(3000);
		}
		else{
			
		}
		event.preventDefault();
	});
    
	
	
	$('#closeModal').click(function(event) {
		closeModal();
	});
	
            function getJsonForm(form){
            	var jsonArray = [];
        		var jsonForm, q;
        		  q = form.replace(/\?/, "").split("&");
        		  jsonForm = {};
        		  $.each(q, function(i, arr) {
        		    arr = arr.split('=');
        		    return jsonForm[arr[0]] = arr[1];
        		  });
        		   return jsonForm;
            }
            
            function openModal() {
              document.getElementById("myModal").style.display = "block";
            }

            function closeModal() {
              document.getElementById("myModal").style.display = "none";
            }
            
            
	
	movediv();
});

function movediv(){
	

	var mousePosition;
	var offset = [100,100];
	var div=document.getElementById("myModal");
	var isDown = false;

	div.addEventListener('mousedown', function(e) {
	    isDown = true;
	    offset = [
	        div.offsetLeft - e.clientX,
	        div.offsetTop - e.clientY
	    ];
	}, true);

	document.addEventListener('mouseup', function() {
	    isDown = false;
	}, true);

	document.addEventListener('mousemove', function(event) {
	    event.preventDefault();
	    if (isDown) {
	        mousePosition = {
	    
	            x : event.clientX,
	            y : event.clientY
	    
	        };
	        div.style.left = (mousePosition.x + offset[0]) + 'px';
	        div.style.top  = (mousePosition.y + offset[1]) + 'px';
	    }
	}, true);
}
function display(id) {
	var json = "<pre> Student with id "+ id +" successfully deleted." + "</pre>";
	$('#postResultDiv').html(json);
}
function displayError(e) {
	var json = "<pre> Student record not deleted. Please Refresh and try again or contact the application Admin." + "</pre>";
	$('#postResultDiv').html(json);
}








	
	
//	function display(data) {
//		var json = "<h4>Ajax Response</h4><pre>"
//				+ JSON.stringify(data, null, 4) + "</pre>";
//		$('#feedback').html(json);
//	}
//                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                         