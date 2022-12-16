 
$.get("nav_type_select.html", function(data){  //pone la barra de navegacion
    $("#nav-placeholder").replaceWith(data);
});


$(document).ready(function(){ //muestra y oculta la consola


$(".tool").toggle();


  $("#validate_ip").click(function(){
$(".tool").toggle();  
  $(".url").toggle();

    });
  
  
    $(".menu_tools_nav").click(function(){
$(".tool").toggle();  
  $(".url").toggle();

  });
  
  
});

var obj = {stop : 0};

Object.defineProperty(obj, "add", {
  set : function (value) {this.stop += value;}
});


function functionClear(values){ //permite solo la entrada numerica

if (/\D/g.test(values)){

return  values.replace(/\D/g,'');
}else{
return values;
}
}

var term = new Terminal();


var tt=0;
var paquete="";
var identi=0;
function recibePaquetes(dd,ee){ //funcion llamada desde el main.js el ee es el id usuario


if(tt==0){

term.open(document.getElementById('result'));	
	
 term.write(dd);

}		

if(dd!=""){
term.write(dd);
}

if(tt==0){
identi=ee;
 }
tt++;

var res = dd.substring(dd.length-30, dd.length-1);

if(res.includes("\n\n")){ //para el callback


tt=0;

obj.add = 5;

}else{
//if(stop==0){
if(obj.stop==0){

idinter=setInterval(funcionAjax, 200);
}


if(obj.stop!=0){

tt=0;

}



}

function funcionAjax(){
clearInterval(idinter);
mi_ajax(identi,"0");

}


if(dd=="fail_address"){ //si lo introducido por el formulario no es valido


obj.add = 5;
idinter=setInterval(funcionEnd, 350);
function funcionEnd(){
clearInterval(idinter);
location.reload();

}
}


}


function stop_now(){


if(obj.stop==0){



obj.add = 5;
clearInterval(idinter);

mi_ajax(identi+";"+"aaaaaaaaaaaaa","0");

idinterStop=setInterval(funcionStop, 250);


function funcionStop(){

clearInterval(idinterStop);
location.reload();

}

}else{
location.reload();
}

}




