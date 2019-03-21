<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%> 
<%@ page isELIgnored="false" %> 
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>EAGLE TIP</title>
<script src="https://www.eagletip.com/jquery.js"></script>
<script src="https://www.eagletip.com/math.min.js"></script>
<script src="https://www.eagletip.com/moment.js"></script>
<script src="https://www.eagletip.com/moment-timezone-with-data.js"></script>
<script src="https://www.eagletip.com/moment-data.js"></script>
<script src="https://www.eagletip.com/bootstrap/js/bootstrap.bundle.js" ></script>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
<link rel="stylesheet" href="https://www.eagletip.com/bootstrap/css/bootstrap.min.css" />
<link rel="stylesheet" href="https://www.eagletip.com/nav.css"/>
<script src="https://www.eagletip.com/bootstrap/js/bootstrap-select.js"></script>
<script src="https://www.eagletip.com/bootstrap/js/bootstrap-multiselect.js"></script>
<link rel="stylesheet" href="https://www.eagletip.com/bootstrap/css/bootstrap-multiselect.css" />
<style>
html, body {
  height: 100%;
}
body {
  display: flex;
  flex-direction: column;
}
#contents{
  flex: 1 0 auto;
}
.footer {
  flex-shrink: 0;
}



</style>

</head>
<body>


<%@include file="header.jsp"%>
<div id="contents">


<div class="container mt-5">

<h5 id="pagetitle"></h5>

<div class="row mt-4">
  
  <div class="col-xs-12 col-sm-6 mb-4">
  <form class="d-flex">
    <label class="align-self-center">Filter by:</label>
    <select class="ml-2" id="sport-select" name="sport-select" multiple="multiple">
       
    </select>
    <button type="button" id="sport-filter-submit" class="btn btn-dark text-light ml-3">submit</button>
   </form>
  </div>
    <div class="col-xs-12 col-sm-6 mb-4">
    <form class="form-inline float-sm-right">
    <label class="mr-2" for="tipsters-sort">Sort by:</label>
    <select id="tipsters-sort" name="tipsters-sort" class="form-control">
       <option value="profit">Profit</option>
       <option value="winpercentage">Win Percentage</option>
       <option value="yield">Yield</option>
       <option value="tips">Tips</option>
       <option value="name">Name</option>
    </select>
    </form>
    </div>
    
</div>



<div class="row justify-content-center">  
<div class="p-2">
<div class="btn-group">
<button class="btn btn-dark text-light" id="first">&laquo;&laquo;</button>
<button class="btn btn-dark text-light" id="previous">&laquo;</button>
</div>

<div class="btn-group" id="pagination">
</div>
<div class="btn-group">
<button class="btn btn-dark text-light" id="next">&raquo;</button>
<button class="btn btn-dark text-light" id="last">&raquo;&raquo;</button>
</div>
</div>
</div>

  

   <div class="row tipsters-row justify-content-around mt-5">
   
   
   </div>
   </div>
   
</div>
<%@include file="footer.jsp"%>
<script>
function get(name){
	   if(name=(new RegExp('[?&]'+encodeURIComponent(name)+'=([^&]*)')).exec(location.search))
	      return decodeURIComponent(name[1]);
	}
$(window).on('popstate', function() {
    location.reload(true);
 });
var items=0;
var current=1;
var max;
var init=1;
var numtipsters;
var size=7;
var category=get("category");
var page=1;
var order="profit";
var sport=[""];



function createCookie(name,value,days){
	var date,expires;
	if(days){
		date=new Date();
		date.setTime(date.getTime()+(days*24*60*1000));
		expires=";expires="+date.toGMTString();
		
	}else{
		expires="";
	}
	document.cookie=name+"="+value+expires+"; path=/";
}

function getCookie(name) {
	  var value = "; " + document.cookie;
	  var parts = value.split("; " + name + "=");
	  if (parts.length == 2) return parts.pop().split(";").shift();
}

function imgError(img) {
    img.onerror = "";
    var imagesource=img.src;
	img.src=imagesource.split(".")[0]+".png";
	return true;
    
}

$(document).ready(function(){
	
	

	if(category=="free"){
		$("#tipsterslink").addClass("text-dark");
		$("#freetipsterslink").addClass("text-dark");
		
		$("#pagetitle").text("FREE TIPSTERS");
	}
	else{
		$("#tipsterslink").addClass("text-dark");
		$("#paidtipsterslink").addClass("text-dark");
		$("#pagetitle").text("PAID TIPSTERS");
	}
	var loadedwidth=$(window).width();
	console.log(loadedwidth);
	if(loadedwidth>575){
		
		$(window).resize(function(){
			if($(window).width()<=575){
			  window.location.replace(window.location.pathname + window.location.search + window.location.hash);
		    }
		});
	}
	if(loadedwidth<=575){
		
		$(window).resize(function(){
			if($(window).width()>575){
			  window.location.replace(window.location.pathname + window.location.search + window.location.hash);
		    }
		});
	}
	
	$('.settings').popover({title: "settings", content:'<ul class="list-unstyled settings_dropdown"><li style="vertical-align:center"><label for="odds-selector" class="mb-0"><span class="text-left">Odds</span></label><select id="odds-selector" class="odds-selector mt-0"><option value="DECIMAL">DECIMAL</option><option value="FRACTIONAL">FRACTIONAL</option><option value="AMERICAN">AMERICAN</option></select></li><li style="vertical-align:center"><label for="timezone-selector" class="mb-0"><span class="text-left">Timezone</span></label><select id="timezone-selector" class="timezone-selector mt-0"><option id="GMT-12:00" value="-720">GMT-12:00</option><option id="GMT-11:00" value="-660">GMT-11:00</option><option id="GMT-10:00" value="-600" >GMT-10:00</option><option id="GMT-09:00" value="-540">GMT-09:00</option><option id="GMT-08:00" value="-480">GMT-08:00</option><option id="GMT-07:00" value="-420">GMT-07:00</option><option id="GMT-06:00" value="-360">GMT-06:00</option><option id="GMT-05:00" value="-300">GMT-05:00</option><option id="GMT-04:00" value="-240">GMT-04:00</option><option id="GMT-03:00" value="-180">GMT-03:00</option><option id="GMT-03:30" value="-210">GMT-03:30</option><option id="GMT-02:00" value="-120">GMT-02:00</option><option id="GMT-01:00" value="-60">GMT-01:00</option><option id="GMT+00:00" value="0">GMT+00:00</option><option id="GMT+01:00" value="60">GMT+01:00</option><option id="GMT+02:00" value="120">GMT+02:00</option><option id="GMT+03:00" value="180">GMT+03:00</option><option id="GMT+03:07" value="187">GMT+03:07</option><option id="GMT+03:30" value="210">GMT+03:30</option><option id="GMT+04:00" value="240">GMT+04:00</option><option id="GMT+04:30" value="270">GMT+04:30</option><option id="GMT+05:00" value="300">GMT+05:00</option><option id="GMT+05:30" value="330">GMT+05:30</option><option id="GMT+05:45" value="345">GMT+05:45</option><option id="GMT+06:00" value="360">GMT+06:00</option><option id="GMT+06:30" value="390">GMT+06:30</option><option id="GMT+07:00" value="420">GMT+07:00</option><option id="GMT+08:00" value="480">GMT+08:00</option><option id="GMT+08:45" value="525">GMT+08:45</option><option id="GMT+09:00" value="540">GMT+09:00</option><option id="GMT+09:30" value="570">GMT+09:30</option><option id="GMT+10:00" value="600">GMT+10:00</option><option id="GMT+10:30" value="630">GMT+10:00 </option><option id="GMT+11:00" value="660">GMT+11:00</option><option id="GMT+11:30" value="690">GMT+11:30</option><option id="GMT+12:00" value="720">GMT+12:00</option><option id="GMT+12:45" value="765">GMT+12:45</option><option id="GMT+13:00" value="780">GMT+13:00</option><option id="GMT+14:00" value="840">GMT+14:00</option></select></li><li style="vertical-align:center"><label for="currency-selector" class="mb-0"><span class="text-left">Currency</span></label><select id="currency-selector" class="currency-selector mt-0"><option value="USD">USD</option><option value="EUR">EUR</option><option value="GBP">GBP</option></select></li></ul>', html: true, placement: "bottom"}); 
	$('[data-toggle="popover"]').popover();
	
	 $(window).resize(function(e){
		 console.log($(window).width());
		 if($(window).width()>575){
		  $('[data-toggle="popover"],[data-original-title]').each(function () {
		        if (!$(this).is(e.target) && $(this).has(e.target).length === 0 && $('.popover').has(e.target).length === 0) {                
		            (($(this).popover('hide').data('bs.popover')||{}).inState||{}).click = false  // fix for BS 3.3.6
		        }

		    });
		 }
	 });
	 
	 $(document).on('click', function (e) {
		    $('[data-toggle="popover"],[data-original-title]').each(function () {
		        //the 'is' for buttons that trigger popups
		        //the 'has' for icons within a button that triggers a popup
		        if (!$(this).is(e.target) && $(this).has(e.target).length === 0 && $('.popover').has(e.target).length === 0) {                
		            (($(this).popover('hide').data('bs.popover')||{}).inState||{}).click = false  // fix for BS 3.3.6
		        }

		    });
		});
		 
			var sesscurrency="${sessionScope.currency}";
			var currency;
		 	var sessodds="${sessionScope.odds}";
			var odds;
			var sesstimezone="${sessionScope.timezone}";
			var timezone;
			/* var months=$("#monthsselect").val(); 
			*/
			
			if(sesscurrency==""){
				console.log("initial"+sesscurrency);
				if(typeof getCookie('currency') === 'undefined'){
					if($(window).width()>575){
					console.log("initial"+$(".selectedcurrency").text().trim());
					createCookie("currency",$(".selectedcurrency").text().trim().toUpperCase(),30);
					}
					else{
						
							 createCookie("currency","USD",30);
						   
						
					}
				}
				
				
			  currency=getCookie("currency");
			  if(typeof currency === 'undefined'){
		         currency="USD";
		 
	          }
			  console.log("initial"+currency);
			  if($(window).width()>575){
			  $(".selectedcurrency").html(currency.toUpperCase()+"<i class='fa fa-angle-down'></i>");
			  $(".currency_selection").html("");
				if($(".selectedcurrency").text().trim().toUpperCase()!="USD"){
					$(".currency_selection").append('<li style="vertical-align:center"><a href="#">USD</a></li>');
					}
					if($(".selectedcurrency").text().trim().toUpperCase()!="EUR"){
						$(".currency_selection").append('<li style="vertical-align:center"><a href="#">EUR</a></li>');
						}
					if($(".selectedcurrency").text().trim().toUpperCase()!="GBP"){
						$(".currency_selection").append('<li style="vertical-align:center"><a href="#">GBP</a></li>');
						}
			  
			  $(".currency_selection a").click(function(){
				  createCookie("currency",$(this).text().trim().toUpperCase(),30);
				  window.location.replace(window.location.pathname + window.location.search + window.location.hash);
			  });
			  
			  }
			  else{
				  console.log("iam in inner"+currency);
				  $(document).on('click',".settings",function () {
					  $("#currency-selector").val(currency);
					  $("#currency-selector").change(function(){
						  createCookie("currency",$("#currency-selector").val(),30);
						  window.location.replace(window.location.pathname + window.location.search + window.location.hash);
					  });
				  });
			  }
			}
			else{
				currency=sesscurrency;
				if($(window).width()>575){
				console.log("initial"+currency);
				$(".selectedcurrency").html(currency+"<i class='fa fa-angle-down'></i>");
				
				  $(".currency_selection").html("");
					if($(".selectedcurrency").text().trim().toUpperCase()!="USD"){
						$(".currency_selection").append('<li style="vertical-align:center"><a href="#">USD</a></li>');
						}
						if($(".selectedcurrency").text().trim().toUpperCase()!="EUR"){
							$(".currency_selection").append('<li style="vertical-align:center"><a href="#">EUR</a></li>');
							}
						if($(".selectedcurrency").text().trim().toUpperCase()!="GBP"){
							$(".currency_selection").append('<li style="vertical-align:center"><a href="#">GBP</a></li>');
							}
				 $(".currency_selection a").click(function(){
					  $.ajax({
						 method:"POST",
						 url:"https://www.eagletip.com/UpdateUserCurrency",
						 data:{currency:$(this).text(),pageredirect:window.location.href},
						 success:function(){
							 window.location.replace(window.location.pathname + window.location.search + window.location.hash);
						 }
					  });
				  });
			
				console.log("session currency is"+ currency);
				}
				else{
					$(document).on('click',".settings",function () {
					$("#currency-selector").val(currency);
					$("#currency-selector").change(function(){
						 $.ajax({
							 method:"POST",
							 url:"https://www.eagletip.com/UpdateUserCurrency",
							 data:{currency:$("#currency-selector").val(),pageredirect:window.location.href},
							 success:function(){
								 window.location.replace(window.location.pathname + window.location.search + window.location.hash);
							 }
						  });
					  });
				   });
				}
			}
			
			
			if(sessodds==""){
				if(typeof getCookie('odds') === 'undefined'){
					if($(window).width()>575){
					createCookie("odds",$(".selectedodds").text().trim().toUpperCase(),30);
					}
					else{
						
			
							 createCookie("odds","DECIMAL",30);
						
						
					}
				}
				
			  odds=getCookie("odds");
			  if(typeof odds === 'undefined'){
			         odds="DECIMAL";
			 
		          }
			  if($(window).width()>575){
			  $(".selectedodds").html(odds+"<i class='fa fa-angle-down'></i>");
			  
			  $(".odds_selection").html("");
				if($(".selectedodds").text().trim().toUpperCase()!="DECIMAL"){
					$(".odds_selection").append('<li style="vertical-align:center"><a href="#">DECIMAL</a></li>');
					}
					if($(".selectedodds").text().trim().toUpperCase()!="FRACTIONAL"){
						$(".odds_selection").append('<li style="vertical-align:center"><a href="#">FRACTIONAL</a></li>');
						}
					if($(".selectedodds").text().trim().toUpperCase()!="AMERICAN"){
						$(".odds_selection").append('<li style="vertical-align:center"><a href="#">AMERICAN</a></li>');
						}
			  
			  
			  $(".odds_selection a").click(function(){
				  createCookie("odds",$(this).text().trim().toUpperCase(),30);
				  window.location.replace(window.location.pathname + window.location.search + window.location.hash);
			  });
			  }
			  else{
				  console.log("iam in inner"+currency);
				  $(document).on('click',".settings",function () {
					  $("#odds-selector").val(odds);
					  $("#odds-selector").change(function(){
						  createCookie("odds",$("#odds-selector").val().toUpperCase(),30);
						  window.location.replace(window.location.pathname + window.location.search + window.location.hash);
					  });
				  });
			  }
			}
			else{
				odds=sessodds;
				if($(window).width()>575){
				$(".selectedodds").html(odds+"<i class='fa fa-angle-down'></i>");
				  $(".odds_selection").html("");
					if($(".selectedodds").text().trim().toUpperCase()!="DECIMAL"){
						$(".odds_selection").append('<li style="vertical-align:center"><a href="#">DECIMAL</a></li>');
						}
						if($(".selectedodds").text().trim().toUpperCase()!="FRACTIONAL"){
							$(".odds_selection").append('<li style="vertical-align:center"><a href="#">FRACTIONAL</a></li>');
							}
						if($(".selectedodds").text().trim().toUpperCase()!="AMERICAN"){
							$(".odds_selection").append('<li style="vertical-align:center"><a href="#">AMERICAN</a></li>');
							}
				  
				  
				
				  $(".odds_selection a").click(function(){
					  $.ajax({
						 method:"POST",
						 url:"https://www.eagletip.com/UpdateUserOdds",
						 data:{odds:$(this).text().trim().toUpperCase(),pageredirect:window.location.href},
						 success:function(){
							 window.location.replace(window.location.pathname + window.location.search + window.location.hash);
						 }
					  });
				  });
				}
				else{
					
					$(document).on('click',".settings",function () {
						$("#odds-selector").val(odds);
						$("#odds-selector").change(function(){
							 $.ajax({
								 method:"POST",
								 url:"https://www.eagletip.com/UpdateUserOdds",
								 data:{odds:$("#odds-selector").val().toUpperCase(),pageredirect:window.location.href},
								 success:function(){
									 window.location.replace(window.location.pathname + window.location.search + window.location.hash);
								 }
							  });
						  });
					 });
				}
			
				console.log("session odds is"+ odds);
			}
			
			

			var min;
			if(sesstimezone==""){
				if(typeof getCookie('timezone') === 'undefined'){
					if($(window).width()>575){
					createCookie("timezone",$(".selectedtimezone").text().trim(),30);
					createCookie("min",$(".selectedtimezone").attr("data-min"),30);
					console.log("minutes is"+$(".selectedtimezone").attr("data-min"));
					}
					else{
						
					
						createCookie("timezone","GMT+00:00",30);
						createCookie("min","0",30);
					}
				}
				
			  timezone=getCookie("timezone");
			  min=getCookie("min");
			  if(typeof timezone === 'undefined'){
			         timezone="GMT+00:00";
			 
		          }
			  if(typeof min === 'undefined'){
			         min="0";
			 
		          }
			  if($(window).width()>575){
				  
			  $(".selectedtimezone").text(timezone);
			  $(".selectedtimezone").attr("data-min",min);
			  $(".timezone_selection a").click(function(){
				  createCookie("timezone",$(this).text(),30);
				  createCookie("min",$(this).attr("data-min"),30)
				  console.log("minutes is"+$(this).attr("data-min"));
				  window.location.replace(window.location.pathname + window.location.search + window.location.hash);
			  });
			  }
			  else{
				 
				  
				  $(document).on('click',".settings",function () {
					  $("#timezone-selector").val(min);
					  $("#timezone-selector").change(function(){
						  createCookie("timezone",$("#timezone-selector option:selected").attr("id"),30);
						  createCookie("min",$("#timezone-selector").val(),30);
						  console.log("minutes is"+$("#timezone-selector").attr("data-min"));
						  window.location.replace(window.location.pathname + window.location.search + window.location.hash);  
					  });
				   });

			
			  }
			}
			else{
				timezone=sesstimezone;
				var calpart=timezone.substring(3,timezone.length);
				var htomin=parseInt(calpart.split(":")[0])*60;
				var minut=parseInt(calpart.split(":")[1]);
				var totmin=htomin+minut;
				min=totmin;
				if($(window).width()>575){
				$(".selectedtimezone").text(timezone);
				$(".selectedtimezone").attr("data-min",totmin);
				$(".timezone_selection a").click(function(){
					  $.ajax({
						 method:"POST",
						 url:"https://www.eagletip.com/UpdateUserTimeZone",
						 data:{timezone:$(this).text(),pageredirect:window.location.href},
						 success:function(){
							 window.location.replace(window.location.pathname + window.location.search + window.location.hash);
						 }
					  });
				  });
			
				console.log("session timezone is"+ timezone);
				}
				else{
					
					
					  $(document).on('click',".settings",function () {
							 $("#timezone-selector").val(min);
							  $("#timezone-selector").change(function(e){
								  $.ajax({
										 method:"POST",
										 url:"https://www.eagletip.com/UpdateUserTimeZone",
										 data:{timezone:$("#timezone-selector option:selected").attr("id"),pageredirect:window.location.href},
										 success:function(){
											 window.location.replace(window.location.pathname + window.location.search + window.location.hash);
										 }
									  });
							  });
					   });  
					  
				}
			}  
				var oddscolumns=document.getElementsByClassName("oddscolumn");
		        if(odds==='FRACTIONAL'){
					for (var i = 0; i < oddscolumns.length; i++) {
							oddscolumns[i].innerHTML=(math.fraction(oddscolumns[i].innerHTML).n+"/"+math.fraction(oddscolumns[i].innerHTML).d);
					}
		        }
		        if(odds==='american'){
					for (var i = 0; i < oddscolumns.length; i++) {
							if(parseFloat(oddscolumns[i].innerHTML)>2) {
								var amp=(parseFloat(oddscolumns[i].innerHTML)-1.0)*100.0;
								amp="+"+amp;
		                        oddscolumns[i].innerHTML= amp.split(".")[0];
							}
							else {
								var amn=(-100/(parseFloat(oddscolumns[i].innerHTML)-1));
								amn=""+amn;
								oddscolumns[i].innerHTML=amn.split(".")[0];
							}
						
						
					}
		        }
		        
		        var timecolumns=document.getElementsByClassName("timecolumn");
					for (var i = 0; i < timecolumns.length; i++) {
							timecolumns[i].innerHTML=moment.utc(timecolumns[i].innerHTML).utcOffset(parseInt(min)).format('DD/MM HH:mm');
					}
			
		 
		 
		 
		 

		
		
		  $.ajax({
				method:"GET",
				url:"https://www.eagletip.com/GetCartItems",
				success:function(response){
					items=0;
					console.log("items is"+ items);
					var res=JSON.parse(response);
				   	$.each(res,function(index,value){
				   		items=items+1;
				   		 var convcurrency=value[1];
						if(currency==='EUR'){
							convcurrency=parseFloat(convcurrency*.87).toFixed(2);
						}
						else if(currency==='GBP'){
							convcurrency=parseFloat(convcurrency*.79).toFixed(2);
						}
				   		$("#cartitems").append("<div class='row justify-content-around'><div class='col-xs-4'><img style='width:30px;height:30px' class='image-fluid rounded-circle mb-2' src='https://www.eagletip.com/images/tipsters/"+value[0]+".jpg'/><p class='nametoremove text-nowrap text-left'>"+value[0]+"</p></div><div class='col-xs-2'><p><b>"+value[2]+" month subscription</b></p></div><div class='col-xs-2'><span><b>"+parseFloat(convcurrency*value[2]).toFixed(2)+"</b> </span> <img style='width:20px;height:20px' src='https://www.eagletip.com/images/css/currency/"+currency+".svg'/><button type='button' style='margin-left:10px' id='removesubscription' class='close' aria-label='Close'><span style='color:red;' aria-hidden='true'>&times;</span></button></div></div>");
				   	});
				 
				   	$("#checkout_items1").html(items);
					$("#checkout_items").html(items);
				   	 $("button.close").click(function(e){
							
							var button1=$(this);
							console.log($(button1).parent().parent().find("p.nametoremove"));
							var nameofsub=$(button1).parent().parent().find("p.nametoremove").text();
							$.ajax({
								method:"POST",
								url:"https://www.eagletip.com/DeleteCartItem",
								data:{
									itemname:nameofsub
								},
								success:function(response){
									items=items-1;
								  	$("#checkout_items1").html(items);
									$("#checkout_items").html(items);
									console.log("deleted");
									$(button1).parent().parent().remove();
									
								}
								
								
							});
							
							
						
						});	
				   	 
				   	 
				}
			}); 
		 
		 /*cart functionality
		 
		 
		 
		 
		 */
		 
		
		 
		 
		 
		 
		 $(".account_selection a").click(function(e){
			 e.stopPropagation();
			 if($(e.target).is('[data-toggle=modal]')){
				 $($(e.target).data('target')).modal();
			 }
		 });
		 $('.dropdown-menu').click(function(event){
		     event.stopPropagation();
		 });
		 $("#spinner").hide();
		 $("#registerform").hide();
		 $("#loginform").show();
	$("#registertab").on("click",function(e){
		if(!($("#registertab").hasClass("myactive"))){
			$("#registertab").addClass("myactive");
			$("#logintab").removeClass("myactive");
		}
		$("#loginform").hide();
		$("#registerform").show();

	});

	$("#logintab").on("click",function(e){
		if(!($("#logintab").hasClass("myactive"))){
		$("#logintab").addClass("myactive");
		$("#registertab").removeClass("myactive");
		
		}
		$("#loginform").show();
		$("#registerform").hide();
		
	});

	$("#signupdropdown").on("click",function(e){
		if(!($("#registertab").hasClass("myactive"))){
			$("#registertab").addClass("myactive");
			$("#logintab").removeClass("myactive");
		}
		$("#loginform").hide();
		$("#registerform").show();

	});

	$("#signindropdown").on("click",function(e){
		if(!($("#logintab").hasClass("myactive"))){
		$("#logintab").addClass("myactive");
		$("#registertab").removeClass("myactive");
		
		}
		$("#loginform").show();
		$("#registerform").hide();
		
	});
	$("#login").on("click",function(e){
		$("#email1").removeClass("is-invalid");
		$("#password1").removeClass("is-invalid");
		e.preventDefault();	
		$("#spinner").show();
		var username2=$("#email1").val();
		var password2=$("#password1").val();
		 $.ajax({
			 method:"POST",
			 url:"https://www.eagletip.com/Login",
			 data:{username:username2,password:password2},
			 success:function(response){
				 $("#spinner").hide();
				 if(response=="nouser"){
					 $("#email1").addClass("is-invalid");
				 }
				 else if(response=="passerror"){
					 $("#password1").addClass("is-invalid");
				 }
				 else if(response="loggedin"){
					 window.location.replace(window.location.pathname + window.location.search + window.location.hash);
				 }
			 }
			 
		 });
		
		
		
	});
	
	


	$(".profiles").on("click",function(e){
		var usertype=$(this).attr("data-usertype");
		var usersport=$(this).attr("data-sport");
		var username=$(this).attr("data-name");
		console.log(usertype);
		console.log(usersport);
		console.log(username);
		
		
		$.ajax({
			 method:"POST",
			 url:"https://www.eagletip.com/AddClickedUserToSession",
			 data:{usertype:usertype,username:username,usersport:usersport},
			 success:function(response){
				 window.location.href = "https://www.eagletip.com/paidtips";
				
			 }
			 
		});
		
		
		
	});











	
	
	
	  $('#sport-select').multiselect();
	  
	  
	 
	
	$.ajax({
		method:"GET",
		url:"https://www.eagletip.com/GiveTipsters",
		data:{
			category:category,
			page:page,
			order:order
		},
		success:function(response){
			var count={};
			var dataset=[];
			console.log(response);
			var res=JSON.parse(response);
			console.log(res.length);
			$.each(res,function(index,value){
			 if(res.length-1!=index){
				 
		     	 
				 $('div.tipsters-row').append('<div class="card col-xs-12 col-sm-6 col-md-4 col-lg-2"><div class="card-header text-center"><img class="image-fluid rounded-circle mb-2 tipsterimage" style="width:100px;height:100px" src="https://www.eagletip.com/images/tipsters/'+value[1]+'.jpg" onerror="imgError(this)"/><div class="text-nowrap"><img class="image-fluid rounded-circle mb-2" style="width:20px;height:20px" src="https://www.eagletip.com/images/sport/'+value[2]+'.svg" /><p class="text-info"><em>'+value[1]+'</em></p></div></div><div class="card-body d-flex flex-column align-items-center"><div class="mb-2 text-nowrap"><span class="text-info text-left">Tips  :  <span class="text-warning font-weight-bold">'+value[5]+'</span></div><div class="mb-2 text-nowrap pl-3"><span class="text-info mb-2">Win  :  </span><span class="text-warning font-weight-bold">'+value[8]+' %</span></div><div class="text-nowrap pl-2 mb-2"><span class="text-info mb-2">Profit  :  </span><span class="text-warning font-weight-bold">'+value[4]+'</span></div><div><a class="btn btn-dark text-warning" href="https://www.eagletip.com/tipster/activetips?name='+value[1]+'">More..</a></div></div>');
			 dataset.push(value[2]);
			 } 
			 else{
				 numtipsters=value[0];
			 }
			 
			});
			
			
			    $("#next").click(function(){
				 	executeajaxandbuildbuttons(current + 1);
				 	});	
				 $("#previous").click(function(){
				 	executeajaxandbuildbuttons(current - 1);
				 });	

				 $("#first").click(function(){
				 	executeajaxandbuildbuttons(1);
				 	});	
				 $("#last").click(function(){
				 	executeajaxandbuildbuttons(max-1);
				 });	
			executeajaxandbuildbuttons(1);
			
			
			
			 dataset.forEach(function(el){
			     count[el] = count[el] + 1 || 1
			 });
			 Object.entries(count).forEach(
					    function(key, value){
					    	$("#sport-select").append('<option class="text-nowrap" value="'+key[0]+'">'+key[0]+'</option>');
					    	console.log(key[0]);
					     $("#sport-select").multiselect('destroy');
					    	$("#sport-select").multiselect(); 
					    }
					    
			 );
			 
			 
		}
		
		
	});
	
	
	$('#sport-filter-submit').click(function(){
	      console.log("hello");
		  sport=$("#sport-select").val();
		  order=$("#tipsters-sort").val();
		  page=1;
			$.ajax({
				method:"GET",
				url:"https://www.eagletip.com/GiveTipsters",
				data:{
					category:category,
					page:page,
					order:order,
					sport:sport
				},
				success:function(response){
					$('div.tipsters-row').html(""); 
					console.log(response);
					var res=JSON.parse(response);
					$.each(res,function(index,value){
						if(res.length-1!=index){
							 $('div.tipsters-row').append('<div class="card col-xs-12 col-sm-6 col-md-4 col-lg-2"><div class="card-header text-center"><img class="image-fluid rounded-circle mb-2 tipsterimage" style="width:100px;height:100px" src="https://www.eagletip.com/images/tipsters/'+value[1]+'.jpg" onerror="imgError(this)"/><div class="text-nowrap"><img class="image-fluid rounded-circle mb-2" style="width:20px;height:20px" src="https://www.eagletip.com/images/sport/'+value[2]+'.svg" /><p class="text-info"><em>'+value[1]+'</em></p></div></div><div class="card-body d-flex flex-column align-items-center"><div class="mb-2 text-nowrap"><span class="text-info text-left">Tips  :  <span class="text-warning font-weight-bold">'+value[5]+'</span></div><div class="mb-2 text-nowrap pl-3"><span class="text-info mb-2">Win  :  </span><span class="text-warning font-weight-bold">'+value[8]+' %</span></div><div class="text-nowrap pl-2 mb-2"><span class="text-info mb-2">Profit  :  </span><span class="text-warning font-weight-bold">'+value[4]+'</span></div><div><a class="btn btn-dark text-warning" href="https://www.eagletip.com/tipster/activetips?name='+value[1]+'">More..</a></div></div>');
						}
						else{
							numtipsters=value[0];
						}
					});
					executeajaxandbuildbuttons(1);
					
					 
					
				}
				
				
			});
	 });
	
	$('#tipsters-sort').change(function(){
		  order=$("#tipsters-sort").val();
			$.ajax({
				method:"GET",
				url:"https://www.eagletip.com/GiveTipsters",
				data:{
					category:category,
					page:page,
					order:order,
					sport:sport
				},
				success:function(response){
					$('div.tipsters-row').html(""); 
					console.log(response);
					var res=JSON.parse(response);
					$.each(res,function(index,value){
						if(index!=res.length-1){
							 $('div.tipsters-row').append('<div class="card col-xs-12 col-sm-6 col-md-4 col-lg-2"><div class="card-header text-center"><img class="image-fluid rounded-circle mb-2 tipsterimage" style="width:100px;height:100px" src="https://www.eagletip.com/images/tipsters/'+value[1]+'.jpg" onerror="imgError(this)"/><div class="text-nowrap"><img class="image-fluid rounded-circle mb-2" style="width:20px;height:20px" src="https://www.eagletip.com/images/sport/'+value[2]+'.svg" /><p class="text-info"><em>'+value[1]+'</em></p></div></div><div class="card-body d-flex flex-column align-items-center"><div class="mb-2 text-nowrap"><span class="text-info text-left">Tips  :  <span class="text-warning font-weight-bold">'+value[5]+'</span></div><div class="mb-2 text-nowrap pl-3"><span class="text-info mb-2">Win  :  </span><span class="text-warning font-weight-bold">'+value[8]+' %</span></div><div class="text-nowrap pl-2 mb-2"><span class="text-info mb-2">Profit  :  </span><span class="text-warning font-weight-bold">'+value[4]+'</span></div><div><a class="btn btn-dark text-warning" href="https://www.eagletip.com/tipster/activetips?name='+value[1]+'">More..</a></div></div>');
						}
						else{
							numtipsters=value[0];
						}
					});
					
					 executeajaxandbuildbuttons(1);
					
				}
				
				
			});
	 });
	
	
	


	$('#tipsterform').on("submit",function(event) {

		if ($('#tipstername').hasClass("is-invalid"))
		    event.preventDefault();        

		else if($('#sportselect').hasClass("is-invalid"))
		    event.preventDefault();  

	});

	$("#tipstername").keyup(function(){
		$(this).removeClass("is-invalid");
		$(this).removeClass("is-valid");
		if($("#tipstername").val().length<5||$("#tipstername").val().length>20){
			$("#tipstername").addClass("is-invalid");
			$(this).removeClass("is-valid");
		}
	});


	$("#username").blur(function() {
		if($("#username").val().length>=5 && $("#username").val().length<=15){
		$.ajax({
			method:"POST",
			data:{username:$('#username').val()},
			url:"https://www.eagletip.com/SendRegistrationErrors",
			success:function(res){
				$("#username").removeClass("is-valid");
				$("#username").removeClass("is-invalid");
				if(res==="already"){
					
					$("#username").addClass("is-invalid");
				}
				else if(res==="goahead"){
					
					$("#username").addClass("is-valid");
				}
				
				if($("#username").val().length<5||$("#username").val().length>15){
					$("#username").removeClass("is-valid");
					$("#username").addClass("is-invalid");
				}
				
				
			}
			
	});
		}

	});
		

		

	$("#email").blur(function() {

		if($("#email").val().length>=5 && $("#email").val().length<30){
		$.ajax({
			method:"POST",
			data:{email:$('#email').val()},
			url:"https://www.eagletip.com/SendRegistrationErrors",
			success:function(res){
				$("#email").removeClass("is-valid");
				$("#email").removeClass("is-invalid");
				
				if(res==="already"){
					$("#email").addClass("is-invalid");

				}
				else if(res==="goahead"){
					$("#email").addClass("is-valid");
				}
				
				if($("#email").val().length<5||$("#email").val().length>30){
					$("#email").removeClass("is-valid");
					$("#email").addClass("is-invalid");
				}
			}
			
	});


		
		}
		
	});

	$("#password").keyup(function(){
		$(this).removeClass("is-invalid");
		$(this).removeClass("is-valid");
		if($("#password").val().length<5||$("#password").val().length>20){
			$("#password").addClass("is-invalid");
			$(this).removeClass("is-valid");
		}
	});

	$("#repassword").keyup(function(){
		$(this).removeClass("is-invalid");
		$(this).removeClass("is-valid");
		if($('#password').val()!=$('#repassword').val()){
			$(this).addClass("is-invalid");
			$(this).removeClass("is-valid");
		}
	});

	$("#addtipster,#getsports").click(function() {
		
		

		$.ajax({
			method:"GET",
			url:"https://www.eagletip.com/CheckOneSportPerUser",
			success:function(res){
		       $("#sportselect").html("");
		        var sports=JSON.parse(res);
		        console.log(sports);
		        console.log(sports);
		        for(var i=0;i<sports.length;i++){
		        	console.log(sports[i]);
		        	$("#sportselect").append("<option style='width:15px;height:15px;' value='"+sports[i]+"'>"+sports[i]+"</option>");
		        }
		        
			}
			
	    });
		


	});


	$("#tipstername").blur(function() {

		$.ajax({
			method:"POST",
			data:{tipstername:$('#tipstername').val()},
			url:"https://www.eagletip.com/CheckForUniqueTipsterName",
			success:function(res){
				console.log(res);
				$(this).removeClass("is-valid");
				$(this).removeClass("is-invalid");
				if(res.trim()==="tipsteralready"){
					console.log("making invalid");
					$("#tipstername").addClass("is-invalid");
					$("#tipstername").removeClass("is-valid");
				}
				else if(res.trim()==="goahead"){
					console.log("making valid");
					$("#tipstername").addClass("is-valid");
					$("#tipstername").removeClass("is-invalid");
				}
				
				if($("#tipstername").val().length<5||$("#tipstername").val().length>20){
					$("#tipstername").addClass("is-invalid");
					$("#tipstername").removeClass("is-valid");
				}
			}
			
	});


	});
	
	
	
	var sessionuser="${sessionScope.username}";
	
	$("#checkout_button").click(function(e){
		e.preventDefault();
		console.log(sessionuser);
		if(sessionuser.trim()==""){
			
			$("#registeralert").css("display","block");
			setTimeout(function(){
				$("#registeralert").css("display","none");
			},3000);
		}
		else{
			location.replace("https://www.eagletip.com/payment");
		}
	});
	
	
	
	
});

function executeajaxandbuildbuttons(myval){
	
	$("#pagination").html("");	
	   current=parseInt(myval);
		$.ajax({
			method:"GET",
			url:"https://www.eagletip.com/GiveTipsters",
			data:{
				category:category,
				page:current,
				order:order,
				sport:sport
			},
			success:function(response){
				$('div.tipsters-row').html("");
				console.log(response);
				var res=JSON.parse(response);
				console.log(res.length);
				$.each(res,function(index,value){
				 if(res.length-1!=index){
					 
				 
				 $('div.tipsters-row').append('<div class="card col-xs-12 col-sm-6 col-md-4 col-lg-2"><div class="card-header text-center"><img class="image-fluid rounded-circle mb-2 tipsterimage" style="width:100px;height:100px" src="https://www.eagletip.com/images/tipsters/'+value[1]+'.jpg" onerror="imgError(this)"/><div class="text-nowrap"><img class="image-fluid rounded-circle mb-2" style="width:20px;height:20px" src="https://www.eagletip.com/images/sport/'+value[2]+'.svg" /><p class="text-info"><em>'+value[1]+'</em></p></div></div><div class="card-body d-flex flex-column align-items-center"><div class="mb-2 text-nowrap"><span class="text-info text-left">Tips  :  <span class="text-warning font-weight-bold">'+value[5]+'</span></div><div class="mb-2 text-nowrap pl-3"><span class="text-info mb-2">Win  :  </span><span class="text-warning font-weight-bold">'+value[8]+' %</span></div><div class="text-nowrap pl-2 mb-2"><span class="text-info mb-2">Profit  :  </span><span class="text-warning font-weight-bold">'+value[4]+'</span></div><div><a class="btn btn-dark text-warning" href="https://www.eagletip.com/tipster/activetips?name='+value[1]+'">More..</a></div></div>');
			
			    }
				 else{
					 numtipsters=value[0];
				 }
				 
				});
				
				 
				
			}
			
			
		});
	  
				   max=Math.ceil(numtipsters/10)+1;
				   if(max<8){
					   size=max-1;
				   }
				   else{
					   if(current>3){
						   init=current-3;
						   while(init+6>=max){
								init=init-1;  	
						   }
					   }
					   else {
						   init=1;
					   }
				   }
				 
				   
				  for(var i=init;i<init+size;i++){
					$("#pagination").append("<button id='"+i+"' class='btn btn-dark text-light'>"+i+"</button>")
					$("#pagination button[id='"+myval+"']").addClass("active");
				}
				
				if($("#pagination button[id='"+1+"']").hasClass("active")){	
					$("#previous").prop("disabled",true);
					$("#first").prop("disabled",true);
				}
				else{
					$("#previous").prop("disabled",false);
					$("#first").prop("disabled",false);
				}
				
				if($("#pagination button[id='"+parseInt(max-1)+"']").hasClass("active")){	
					$("#next").prop("disabled",true);
					$("#last").prop("disabled",true);
				}
				else{
					$("#next").prop("disabled",false);
					$("#last").prop("disabled",false);
				}
				
			

				
					$("#pagination button").click(function(e){	
						
						
						
						console.log(e.currentTarget);
						var page=$(e.currentTarget).attr("id");
						page=parseInt(page);
						executeajaxandbuildbuttons(page);
						
					
						
					});
				
					
}

</script>


</body>
</html>