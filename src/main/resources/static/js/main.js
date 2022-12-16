
$(document).ready(function () {

    $("#mi_form").submit(function (event) {
    
	event.preventDefault();
	mi_ajax("1");
	});

});

function mi_ajax(dd) {


var mi_url = {};
if(dd=="1"){

var  the_ids_check = ["a1", "b1", "c1", "d1", "e1", "f1", "g1", "h1","i1","j1"];
var  the_ids_check = ["0", "1", "2", "3", "4", "5", "6", "7","8","9"];
var mi_var = {};
     mi_var["tx"]="";
     mi_var["command"]="";
     
     
for(i=0; i<10; i++){

	if (document.getElementById(the_ids_check[i]).checked) {
	
	
	if(mi_var["command"]!=""){
	mi_var["command"]=mi_var["command"]+";"+document.getElementById(the_ids_check[i]).value;	
	
	}else{

 mi_var["command"]=document.getElementById(the_ids_check[i]).value;
 
	
	}
	

 
 
 
 
	}


}

 mi_var["ip"] = $("#mi_search").val();
 

}
	mi_url["id"]=$(".url").attr('id');

 
if(dd=="interval"){
	//alert();
    $.ajax({
	    type: "POST",
        contentType: "application/json",  
     //contentType: "text/html"
     //	alert();
        url : mi_url["id"],
        data: JSON.stringify(dd),
        dataType: 'json',
        cache: false,
        timeout: 500000,
        success: function (data) {
		
            var json =  JSON.stringify(data, null, 4) ;
            $('#result').html(json);
       recibePaquetes(json);
            
  },
        error: function (e) {

            var json = 
              //  + e.responseText + "</pre>";
                 e.responseText;
          //  $('#result').html(json);
              recibePaquetes(json);
 }
    });
}else{


$.ajax({
    
 	    type: "POST",
        contentType: "application/json",
		url:	mi_url["id"],
        data: JSON.stringify(mi_var),
        dataType: 'text',
        cache: false,
        timeout: 500000,
        success: function (data) {
			
            var json = 
               JSON.stringify(data, null, 4);
            $('#result').html(json);
        
			$("#mi_search").val('');
			
			
			
			recibePaquetes(json);
			
			
			/*
            idinter=setInterval(mi_function, 500);
             
             function mi_function(){
             
             clearInterval(idinter);
             mi_ajax("interval");
				
			}
			*/
			//mi_ajax("interval");
			
				
        },
        error: function (e) {

            var json =
               e.responseText;
            $('#result').html(json);
			 $("#btn-search").prop("disabled", false);
			 

        }
    });
}
}