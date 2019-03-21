<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%> 
<%@ taglib prefix ="fmt" uri ="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page isELIgnored="false" %> 
<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>EAGLE TIP</title>
<script src="../jquery.js"></script>
<script src="../math.min.js"></script>
<script src="../moment.js"></script>
<script src="../moment-timezone-with-data.js"></script>
<script src="../moment-data.js"></script>
<script src="../bootstrap/js/bootstrap.bundle.js" ></script>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
<link rel="stylesheet" href="../bootstrap/css/bootstrap.min.css" />
<link rel="stylesheet" href="../nav.css"/>
<script src="../bootstrap/js/bootstrap-select.js"></script>
<script src="../bootstrap/js/bootstrap-multiselect.js"></script>

<link rel="stylesheet" href="../bootstrap/css/bootstrap-multiselect.css" />
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



	#chatsection{
	 height:455px;
	 overflow-y:scroll;
	
	}
	
	


</style>



</head>
<body>

<%@include file="header.jsp"%>

<div id="contents">
   
<div class="container-fluid mt-5">
  
   
	<div class="row">
	   
		<div id="ranking-div" class="col-md-3 d-none d-md-block col-lg-3">
				  <div class="row justify-content-center">
				  <div class="col-xs-4 visible-xs visible-sm"></div>
				  <div class="col-xs-4">
				  <p class="font-weight-normal text-success d-block text-center">TOP PAID TIPSTERS</p>
				  <div class="table-responsive">
				   <table id="ranking-table" class="table table-dark text-light table-hover">
				   <thead>
				   <tr class="danger">
				   <th></th>
				   <th>Tipster</th>
				   <th>points</th>
				   <th>Yield</th>
				   </tr>
				   </thead>
				   <tbody>
				   
<tr>

<c:forEach items="${requestScope.toppaidtipsters}" var="paidtipsters">
<fmt:parseNumber var = "i" integerOnly = "true" 
         type = "number" value = "${paidtipsters.profit}" />
<fmt:parseNumber var = "yield" integerOnly = "true" 
         type = "number" value = "${paidtipsters.profit / paidtipsters.tips}" />        
<tr class="d1">
<td><img style="width:15px;height:15px" src="https://www.eagletip.com/images/sport/${paidtipsters.sportname}.svg"/></td>
<td><a class="text-warning" href="https://www.eagletip.com/tipster/activetips?name=${paidtipsters.name }">${paidtipsters.name}</a></td>

<td><span <c:if test='${i > 0}'>class='text-success'</c:if> <c:if test='${i < 0}'>class='text-danger'</c:if>>${i} </span> &nbsp;</td>
<td <c:if test='${yield > 0}'>class='text-success'</c:if> <c:if test='${yield < 0}'>class='text-danger'</c:if>>${yield} % &nbsp;</td>
</tr>

</c:forEach>
				   
				   </tbody>		   
				   </table>
				   </div>
				   </div>
	</div>  
		 
				 <div class="row justify-content-center">
				 <div class="col-xs-4 visible-xs visible-sm"></div>
				 <div class="col-xs-4">
				  <p class="font-weight-normal text-success d-block text-center">TOP FREE TIPSTERS</p>
				   <div class="table-responsive">
				   <table id="ranking-paid-table" class="table table-dark text-light table-hover mr-2">
				   <thead>
				   <tr class="danger">
				   <th></th>
				   <th>Tipster</th>
				   <th>points</th>
				   <th>Yield</th>
				   </tr>
				   </thead>
				   <tbody>	 
				   
				   <c:forEach items="${requestScope.topfreetipsters}" var="freetipsters">
<fmt:parseNumber var = "i" integerOnly = "true" 
         type = "number" value = "${freetipsters.profit}" />
<fmt:parseNumber var = "yield" integerOnly = "true" 
         type = "number" value = "${freetipsters.profit / freetipsters.tips}" />        
<tr class="d1">
<td><img style="width:15px;height:15px" src="https://www.eagletip.com/images/sport/${freetipsters.sportname}.svg"/></td>
<td><a class="text-warning" href="https://www.eagletip.com/tipster/activetips?name=${freetipsters.name }">${freetipsters.name}</a></td>

<td><span <c:if test='${i > 0}'>class='text-success'</c:if> <c:if test='${i < 0}'>class='text-danger'</c:if>>${i} </span> &nbsp;</td>
<td <c:if test='${yield > 0}'>class='text-success'</c:if> <c:if test='${yield < 0}'>class='text-danger'</c:if>>${yield} % &nbsp;</td>
</tr>

</c:forEach>
				   
				   </tbody>
		     	   </table>
		     	   </div>
		     	   </div>
				 </div>
		 
		 
		 </div>
		 <div id="last-10-div" class="col-sm-12 col-md-9 col-lg-6">
				<div class="row">
					   <div class="col-12">
					   <p class="font-weight-normal text-success d-block text-center">ACTIVE FREE TIPS</p>
				       <div class="table-responsive">
					   <table id="last-10-table" class="table table-dark text-light table-hover">
					   <thead>
					   <tr><td>&nbsp;</td><td class="text-center" style="width:70px;">Time</td><td class="text-left">Event</td><td class="text-center">Tipster</td><td class="text-center">Bookie</td></tr>
					   </thead>
					   <tbody>
					   <c:forEach items="${requestScope.freeactivetips}" var="freeactivetips">
					   <tr class="d1">
<td><img style="width:12px;height:12px" src="https://www.eagletip.com/images/sport/${freeactivetips.sportname}.svg"/></td>
<td class="text-nowrap timecolumn" style="text-align:left">${freeactivetips.time}</td>
<td class="text-nowrap"><a class="text-warning" href="https://www.eagletip.com/tip?id=${freeactivetips.tid}"> ${freeactivetips.team1} vs ${freeactivetips.team2}</a></td> 
<td class="text-nowrap"><a class="text-warning" href="https://www.eagletip.com/tipster/activetips?name=${freeactivetips.tipstername}">${freeactivetips.tipstername}</a></td>
<td class="text-nowrap bookmakercolumn" style="text-align:left">${freeactivetips.bookmaker }</td>

</tr>
	</c:forEach>				   
					   
					   </tbody>
					   </table>	 
					   </div>
					   
					   </div>  
				   </div>
				   <div class="row">
				      <div class="col-12">
				     <p class="font-weight-normal text-success d-block text-center">FINISHED FREE TIPS</p>
					    <div class="table-responsive">
					   <table id="last-10-paid-table" class="table table-dark text-light table-hover">
					   <thead>
					   <tr class="head"><td>&nbsp;</td><td class="text-center" style="width:70px;">Time</td><td class="text-left">Event</td><td class="text-center">Tipster</td><td class="text-center">Bookie</td><td class="text-center" style="width:70px">Result</td>
					   </tr>
					   </thead>
					   <tbody>
					   <c:forEach items="${requestScope.freefinishedtips}" var="freefinishedtips">

<tr class="d1">

<td><img style="width:15px;height:15px" src="https://www.eagletip.com/images/sport/${freefinishedtips.sportname}.svg"/></td>
<td class="text-nowrap timecolumn" style="text-align:left">${freefinishedtips.time}</td>
<td class="text-nowrap"><a class="text-warning" href="https://www.eagletip.com/tip?id=${freefinishedtips.tid}"> ${freefinishedtips.team1} vs ${freefinishedtips.team2}</a></td> 
<td class="text-nowrap"><a class="text-warning" href="https://www.eagletip.com/tipster/activetips?name=${freefinishedtips.tipstername}">${freefinishedtips.tipstername}</a></td>
<td class="text-nowrap bookmakercolumn" style="text-align:left">${freefinishedtips.bookmaker}</td>
<td class="text-nowrap" style="text-align:center"><c:if test='${freefinishedtips.result=="won"}'><img src="https://www.eagletip.com/images/css/right.svg" style="width:20px;height:20px"/></c:if><c:if test='${freefinishedtips.result=="lost"}'><img src="https://www.eagletip.com/images/css/wrong.svg" style="width:20px;height:20px"/></c:if></td>

</tr>

</c:forEach>
					   </tbody> 
					   </table>
					   </div>
					   <div class="text-right">
                       <a class="text-primary" style="text-decoration:underline" href="https://www.eagletip.com/tips?page=1&category=free">See all Free tips</a>
                       </div>
					   </div>	   
				   </div>
		
		</div> 
		
		<div id="prevchat" class="d-none d-lg-block col-lg-3 text-success">
		<h5 class="font-weight-bold text-center">CHAT</h5>
		   <div id="chatsection">
			
			
			</div>
            
			<div>
			<form id="chatform" method="POST" action="https://www.eagletip.com/SingleChatSubmit">
			<div class="form-group">
			<textarea id="chatstring" name="chatstring"  class="form-control" placeholder="Enter your comment"></textarea>
			</div>
			<div class="form-group">
			<button id="chatsubmit" value="submit" class="btn btn-primary">send</button>
			</div>
			</form>
			</div>
		</div>
		
		
		
	</div>

</div>

</div>

<%@include file="footer.jsp"%>


<script>


var items=0;
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

 $(document).ready(function(e){
	 $("#toptipsterslink").addClass("text-dark");
		$("#freetipslink").addClass("text-dark");
	 
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
		var sesscurrency="${sessionScope.currency}";
		var currency;
	 	var sessodds="${sessionScope.odds}";
		var odds;
		var sesstimezone="${sessionScope.timezone}";
		var timezone;
		
		
		
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










$.ajax({
	 method:"GET",
	 data:{start:"0",max:"10"},
	 url:"https://www.eagletip.com/ChatServlet",
	 contentType:"application/json",
	 success:function(response){
		 var res=JSON.parse(response);
		$.each(res,function(index,value){
			$("#chatsection").append("<div class='card'><div class='card-body bg-dark text-white'><span class='text-info'>"+value[1]+"</span>:    "+value[2]+"</div></div>");
		});
	     
	 }
	 
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


if(sessionuser==""){
	 console.log("chat should be disabled");
	 $("#chatsubmit").prop("disabled",true);
	 $("#chatstring").prop("placeholder","please register or login to post message");
}
else{
	$("#chatsubmit").prop("disabled",false);
	$("#chatsubmit").removeClass("disabled");
	 $("#chatstring").prop("placeholder","");
}

$("#chatsubmit").click(function(e){
	e.preventDefault();
	var messagestring=$("#chatstring").val();
	$.ajax({
		method:"POST",
		url:"https://www.eagletip.com/SingleChatSubmit",
		data:{messagestring:messagestring},
		success:function(){
			$.ajax({
				 method:"GET",
				 data:{start:"0",max:"10"},
				 url:"https://www.eagletip.com/ChatServlet",
				 contentType:"application/json",
				 success:function(response){
					 var res=JSON.parse(response);
					 $("#chatsection").html("");
					 $("#chatstring").val('');
					$.each(res,function(index,value){
						$("#chatsection").append("<div class='card'><div class='card-body bg-dark text-white'><span class='text-info'>"+value[1]+"</span>:    "+value[2]+"</div></div>");
					    
					});
				     
				 }
				 
			});
		}
		
	});
	
});

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

 
 var scrollnumber=0;

 $('#chatsection').on('scroll', function() {
     if($(this).scrollTop() + $(this).innerHeight() >= $(this)[0].scrollHeight) {
     	scrollnumber=scrollnumber+1;
     	$.ajax({
				 method:"GET",
				 data:{start:scrollnumber,max:"10"},
				 url:"https://www.eagletip.com/ChatServlet",
				 contentType:"application/json",
				 success:function(response){
					 var res=JSON.parse(response);
					 $("#chatstring").val('');
					$.each(res,function(index,value){
						$("#chatsection").append("<div class='card'><div class='card-body bg-dark text-white'><span class='text-info'>"+value[1]+"</span>:    "+value[2]+"</div></div>");
					    
					});
				     
				 }
				 
			});
     }
 })
 
 
});
 
 





</script>

</body>
</html>