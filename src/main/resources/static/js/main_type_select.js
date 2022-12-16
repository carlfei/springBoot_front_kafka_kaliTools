$(document).ready(function () {

    $("#mi_form").submit(function (event) {
    
	event.preventDefault();
var	identi2 = Math.random().toString().substring(0,8);
	mi_ajax("1",identi2);
	});


});

function mi_ajax(dd,ee) {

if(dd==1){
//alert(ee);
}
var mi_url = {};
if(dd=="1"){


var mi_var = {};
     mi_var["tx"]="";
     mi_var["command"]="";
	 mi_var["user_id"]=ee;




if($('#0').val()!=undefined){
if($('#0').is(':checked')){
mi_var["command"]=mi_var["command"]+" "+$('#0').val();
}
}

	 
if($('#1').val()!=undefined){
if($('#1').is(':checked')){


mi_var["command"]=mi_var["command"]+" "+$('#1').val();
}
}



if($('#2').val()!=undefined){
if($('#2').is(':checked')){

mi_var["command"]=mi_var["command"]+" "+$('#2').val();

}
}

if($('#3').val()!=undefined){
if($('#3').is(':checked')){
mi_var["command"]=mi_var["command"]+" "+$('#3').val();

}
}

if($('#4').val()!=undefined){
if($('#4').is(':checked')){
mi_var["command"]=mi_var["command"]+" "+$('#4').val();

}
}


if($("#0x").val()!=undefined){	 
mi_var["tx"]=$("#0x").val();
}





if($("#0a").val()!=undefined){	 
mi_var["command"]=$("#0a").val();
}
 mi_var["ip"] = $("#mi_search").val();


}
	mi_url["id"]=$(".url").attr('id');

 
if(dd.length==8){
	
    $.ajax({
		type:"POST",
        contentType: "application/json",  
  	
  		dataType: 'json',
        url:mi_url["id"],
        data: JSON.stringify(dd),
       
 
        
        cache: true,
        timeout: 500000,
        success: function (data) {
		
          
          var json =  JSON.stringify(data);
   
      

       recibePaquetes(json,"0");
         
  },
        error: function (e) {
		
     
            var json = 
            
                 e.responseText;
           
         
              recibePaquetes(json,"0");
       
 }
    });
}else if(dd.length < 8){

$.ajax({
    
 	    type: "POST",
        contentType:"application/json",
		url:mi_url["id"],
        data: JSON.stringify(mi_var),
        dataType:'json',
        cache: false,
        timeout: 500000,
        success: function (data) {
			
            var json = 
               JSON.stringify(data, null, 4);
   
			
			
			
			recibePaquetes(json,ee);
		
		
				
        },
        error: function (e) {

            var json =
               e.responseText;
       
			 recibePaquetes(json,ee);
			 

        }
    });
}else if(dd.length > 10){

$.ajax({
    
 	    type: "POST",
        contentType:"application/json",
		url:mi_url["id"],
        data: JSON.stringify(dd),
        dataType:'json',
        cache: false,
        timeout: 500000,
        success: function (data) {
			
            var json = 
               JSON.stringify(data, null, 4);
   
			
			
			
		//	recibePaquetes(json,ee);
		
		
				
        },
        error: function (e) {

            var json =
               e.responseText;
       
			// recibePaquetes(json,ee);
			 

        }
    });
}
}









