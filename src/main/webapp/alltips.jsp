<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%> 
<%@ page isELIgnored="false" %> 
<!DOCTYPE html>
<html>
<head>
<!-- Global site tag (gtag.js) - Google Analytics -->
<script async src="https://www.googletagmanager.com/gtag/js?id=UA-136763592-1"></script>
<script>
  window.dataLayer = window.dataLayer || [];
  function gtag(){dataLayer.push(arguments);}
  gtag('js', new Date());

  gtag('config', 'UA-136763592-1');
</script>

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



.tablestat th, .table td { 
     border-top: none !important; 
 }
 

#exTab1 .tab-content{
  color : white;
  background-color: #428bca;
  padding : 5px 15px;
}

#exTab2 h3 {
  color : white;
  background-color: #428bca;
  padding : 5px 15px;
}

/* remove border radius for the tab */

#exTab1 .nav-pills > li > a {
  border-radius: 0;
}

/* change border radius for the tab , apply corners on top*/

#exTab3 .nav-pills > li > a {
  border-radius: 4px 4px 0 0 ;
}

#exTab3 .tab-content {
  color : white;
  background-color: #428bca;
  padding : 5px 15px;
}


	
	@webkit-keyframes spin{
		0%{ -webkit-transform:rotate(0deg);}
		100% { -webkit-transform:rotate(360deg);}
		
	}
	
	@keyframes spin{
		0%{ -webkit-transform:rotate(0deg);}
		100% { -webkit-transform:rotate(360deg);}
		
	}
	
	
	.animate-bottom{
		position:relative;
	    -webkit-animation-name:animatebottom;
	    -webkit-animation-duration:1s;
	    animation-name:animatebottom;
	    animation-duration:1s
		
	}

</style>
<script>


</script>
</head>
<body>


<%@include file="header.jsp" %>

<div id="contents">



<div class="container mt-5">
   
   
   
	 
	 
	
<ul class="nav nav-tabs" id="myTab" role="tablist">
  <li class="nav-item">
    <a class="nav-link <c:if test='${requestScope.category=="paid"}'>active</c:if>" id="messages-tab" href="https://www.eagletip.com/tips?page=1&category=paid" role="tab" aria-controls="messages" aria-selected="false">Paid Tips</a>
  </li>
  <li class="nav-item">
    <a class="nav-link <c:if test='${requestScope.category=="free"}'>active</c:if>" id="settings-tab" href="https://www.eagletip.com/tips?page=1&category=free" role="tab" aria-controls="settings" aria-selected="true">Free Tips</a>
  </li>
</ul>

<!-- Tab panes -->
<div class="tab-content">
  <div class="tab-pane <c:if test='${requestScope.category=="paid"}'>active</c:if>" id="messages" role="tabpanel" aria-labelledby="messages-tab">
  <c:if test='${requestScope.category=="paid"}'>
  <div class="container">
 <div class="row mb-3 mt-3">
  <div class="col-xs-12 col-sm-6 mb-4">
    <form class="d-flex" method="POST" action="https://www.eagletip.com/tips?category=paid">
    <label class="align-self-center">Filter by:</label>
    <select class="ml-2" id="sport-select" name="s1[]" multiple="multiple">
       <c:forEach items="${requestScope.sports}" var="sport">
       <option class="text-nowrap" value="${sport}">${sport}</option>
       </c:forEach>
    </select>
    <button type="submit" id="sport-filter-submit" class="btn btn-dark text-light ml-3">submit</button>
   </form>
  </div>
 </div>
  
<div class="row justify-content-center mb-3 mt-2">  
<div class="p-2">
<div class="btn-group">
<a class="btn btn-dark text-light" id="first">&laquo;&laquo;</a>
<a class="btn btn-dark text-light" id="previous">&laquo;</a>
</div>

<div class="btn-group" id="pagination">
</div>
<div class="btn-group">
<a class="btn btn-dark text-light" id="next">&raquo;</a>
<a class="btn btn-dark text-light" id="last">&raquo;&raquo;</a>
</div>
</div>
</div>
<div class="row">
<div class="table-responsive">
<table id="mytable" class="table table-striped">
<thead>
<tr>
<th></th>
<th>Event</th>
<th>Tipster</th>
<th>Match time</th>
<th>Market</th>
<th>Prediction</th>
<th>Odds</th>
<th>Units</th>
<th>Bookmaker</th>
<th>Result</th>
<th>Profit</th>
</tr>
</thead>
<tbody>
</tbody>


</table>
</div>
</div>
</div>
</c:if>
  </div>
  <div class="tab-pane <c:if test='${requestScope.category=="free"}'>active</c:if>" id="settings" role="tabpanel" aria-labelledby="settings-tab">
 <c:if test="${requestScope.category=='free'}">
<div class="container">

  <div class="row mt-3">
  <div class="col-xs-12 col-sm-6 mb-4">
  <form class="d-flex" method="POST" action="https://www.eagletip.com/tips?category=free">
    <label class="align-self-center">Filter by:</label>
    <select class="ml-2" id="sport-select" name="s1[]" multiple="multiple">
       <c:forEach items="${requestScope.sports}" var="sport">
       <option class="text-nowrap" value="${sport}">${sport}</option>
       </c:forEach>
    </select>
    <button type="submit" id="sport-filter-submit" class="btn btn-dark text-light ml-3">submit</button>
   </form>
  </div>
 </div> 

<div class="row justify-content-center mb-3 mt-4">  
<div class="p-2">
<div class="btn-group">
<a class="btn btn-dark text-light" id="first">&laquo;&laquo;</a>
<a class="btn btn-dark text-light" id="previous">&laquo;</a>
</div>

<div class="btn-group" id="pagination">
</div>
<div class="btn-group">
<a class="btn btn-dark text-light" id="next">&raquo;</a>
<a class="btn btn-dark text-light" id="last">&raquo;&raquo;</a>
</div>
</div>
</div>
<div class="row">
<table id="mytable" class="table table-striped">
<thead>
<tr>
<th></th>
<th>Event</th>
<th>Tipster</th>
<th>Match time</th>
<th>Market</th>
<th>Prediction</th>
<th>Odds</th>
<th>Units</th>
<th>Bookmaker</th>
<th>Result</th>
<th>Profit</th>
</tr>
</thead>
<tbody>
</tbody>


</table>
</div>
</div>
 </c:if> 
  
  
  </div>
</div>


   
   
</div> 

</div>

<%@include file="footer.jsp"%>
<script>


var sports=[];
<c:forEach items="${requestScope.selsport}" var="sport">
sports.push("${sport}");
</c:forEach>
$("#sport-select").val(sports);
var category="${requestScope.category}";
$("#sport-select").multiselect('destroy');
$("#sport-select").multiselect();
var concatstr;
console.log(sports);
for(var st in sports){
	concatstr+="&s1%5B%5D="+sports[st]+"";
}
sports=concatstr.substring(9,concatstr.length);
console.log("sports is"+sports);
var current;
var max;
var items=0;
var init;
var size=7;
var arr=window.location.search.substring(1,window.location.search.length).split("&");
var pars={};
for(var i in arr){  	
pars[arr[i].split("=")[0]]=arr[i].split("=")[1];
}
var page=pars["page"];
var selcat="${requestScope.category}";

if(page==undefined){
	page="1";
}
current=parseInt(page);

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
$(document).ready(function(){
	
	
	 $("#pasttipslink").addClass("text-dark");
	
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
		        //the 'is' for buttons that trigger popups
		        //the 'has' for icons within a button that triggers a popup
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
    
    $("#pagination").html("");
	$("#mytable tbody").html("");
	

	<c:forEach items="${requestScope.tips}" var="item1">
	
	$("#mytable tbody").append("<tr><td><img src='https://www.eagletip.com/images/sport/${item1[6]}.svg' style='width:20px;height:20px'/></td><td><a class='text-warning' href='https://www.eagletip.com/tip?id=${item1[0]}'>${item1[1]} vs ${item1[2]}</a></td><td><a class='text-warning' href='https://www.eagletip.com/tipster/activetips?name=${item1[15]}'>${item1[15]}</a></td><td class='timecolumn'>${item1[3]}</td><td>${item1[7]}</td><td>${item1[8]} ${item1[9]}</td><td class='oddscolumn'>${item1[10]}</td><td>${item1[11]}</td><td class='bookmakercolumn'>${item1[12]}</td><td><c:if test='${item1[13]=="won"}'><img src='https://www.eagletip.com/images/css/right.svg' style='width:20px;height:20px'/></c:if><c:if test='${item1[13]=="lost"}'><img src='https://www.eagletip.com/images/css/wrong.svg' style='width:20px;height:20px'/></c:if></td><td>${item1[14]}</td></tr>");
	</c:forEach>
	
	
	
	 
		var sesscurrency="${sessionScope.currency}";
		var currency;
	 	var sessodds="${sessionScope.odds}";
		var odds;
		var sesstimezone="${sessionScope.timezone}";
		var timezone;
		var currencyvalue="${requestScope.topstats[0][5]}";
		var months=$("#monthsselect").val();
		
		
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
		if(currency==='USD'){
			currencyvalue=parseFloat(currencyvalue).toFixed(2);
			$("#sym").addClass("fa fa-dollar");
		}
		else if(currency==='EUR'){
			currencyvalue=parseFloat(currencyvalue*.87).toFixed(2);
			$("#sym").addClass("fa fa-euro");
		}
		else if(currency==='GBP'){
			currencyvalue=parseFloat(currencyvalue*.79).toFixed(2);
			$("#sym").addClass("fa fa-gbp");
		}
		if(months==1){
			$("#selectedsubscription").html(""+months +" month for " + parseFloat(currencyvalue*months).toFixed(2)+ "<img style='width:20px;height:20px' src='https://www.eagletip.com/images/css/currency/"+currency+".svg'/>");	
		}
		else{
			$("#selectedsubscription").html(""+months +" months for " + parseFloat(currencyvalue*months).toFixed(2) +"<img style='width:20px;height:20px' src='https://www.eagletip.com/images/css/currency/"+currency+".svg'/>");
		}
		$("#monthsselect").change(function(){
		    months=$("#monthsselect").val();
			console.log(months);
			if(months==1){
				$("#selectedsubscription").html(""+months +" month for " +  currencyvalue+ "<img style='width:20px;height:20px' src='https://www.eagletip.com/images/css/currency/"+currency+".svg'/>");	
			}
			else{
				$("#selectedsubscription").html(""+months +" months for " + parseFloat(currencyvalue*months).toFixed(2) + "<img style='width:20px;height:20px' src='https://www.eagletip.com/images/css/currency/"+currency+".svg'/>");
			}
			
		});
		var oddscolumns=document.getElementsByClassName("oddscolumn");
        if(odds.toUpperCase()==='FRACTIONAL'){
			for (var i = 0; i < oddscolumns.length; i++) {
					oddscolumns[i].innerHTML=(math.fraction(oddscolumns[i].innerHTML).n+"/"+math.fraction(oddscolumns[i].innerHTML).d);
			}
        }
        if(odds.toUpperCase()==='AMERICAN'){
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
	
	
	$.ajax({
		 method:"POST",
		 url:"https://www.eagletip.com/AddClickedUserToSession",
		 data:{usertype:usertype,username:username,usersport:usersport},
		 success:function(response){
			 window.location.href = "https://www.eagletip.com/paidtips";
			
		 }
		 
	});
	
	
	
});
	
	
	

 var value=${requestScope.tipsofcategory};
 max=Math.ceil(value/10)+1;
 $("#next").prop("href","https://www.eagletip.com/tips?page="+(current+1)+"&category="+selcat+sports+"");
 $("#previous").prop("href","https://www.eagletip.com/tips?page="+(current-1)+"&category="+selcat+sports+"");
 $("#first").prop("href","https://www.eagletip.com/tips?page="+1+"&category="+selcat+sports+"");
 $("#last").prop("href","https://www.eagletip.com/tips?page="+(max-1)+"&category="+selcat+sports+"");

	execute(current); 
	
 
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

	
	
	var bookmakercolumns=document.getElementsByClassName("bookmakercolumn");
	var bookieurls={"1xbet":"https://1xbet.com","188bet":"https://www.188bet.com/","888sport":"https://www.888sport.com/","bet365":"https://www.bet365.com","betathome":"https://www.bet-at-home.com","betclick":"https://en.betclic.com","betdaq":"https://www.betdaq.com","betfairsports":"https://www.betfair.com/sport","betfred":"https://www.betfred.com","betrally":"https://www.betrally.com","betsafe":"https://www.betsafe.com/en","betvictor":"https://www.betvictor.com","betwaysports":"https://sports.betway.com/en/sports","boylesports":"http://www.boylesports.com","bwinsports":"https://sports.bwin.com/en/sports","dafabet":"https://www.dafabet.com/in","intertops":"https://intertops.eu","interwetten":"https://www.interwetten.com","ladbrokes":"https://www.ladbrokes.com/home/en","mansion88":"https://www.mansion88.com","matchbook":"https://www.matchbook.com","netbet":"https://sport.netbet.co.uk","paddypower":"https://www.paddypower.com/bet","parimatch":"https://www.parimatch.com","pinnaclesports":"https://www.pinnacle.com/en","sbobet":"https://www.sbobet.com","tipbet":"https://www.tipbet.com","totesport":"https://sports.tote.co.uk","unibet":"https://www.unibet.com","williamhillsports":"http://sports.williamhill.com"};
	 for(var i=0;i<bookmakercolumns.length;i++){
		 bookmakercolumns[i].innerHTML="<a href='"+bookieurls[bookmakercolumns[i].innerText.toLowerCase().trim()]+"' target='_blank'><img src='https://www.eagletip.com/images/bookmakers/"+bookmakercolumns[i].innerText.toLowerCase().trim()+".png' style='width:40px;height:20px'/></a>";
		  
	}
});


function execute(myval){
	$("#pagination").html("");	
	   current=parseInt(myval);
	  
	

				   if(max<8){
					   size=max-1;
					   init=1;
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
					$("#pagination").append("<a id='"+i+"' class='btn btn-dark text-light' href='https://www.eagletip.com/tips?page="+i+"&category="+selcat+sports+"'>"+i+"</a>")
					$("#pagination a[id='"+myval+"']").addClass("active");
				}
				  
				
				  if($("#pagination a[id='"+1+"']").hasClass("active")){	
				       console.log("previous and first shoud be disabled");
					  $("#previous").addClass("disabled",true);
						$("#first").addClass("disabled",true);
				  }
					
					if($("#pagination a[id='"+parseInt(max-1)+"']").hasClass("active")){
						console.log("next and last shoud be disabled");
						$("#next").addClass("disabled",true);
						$("#last").addClass("disabled",true);
					}
						

				
				
				
					
}




</script>
</body>
</html>