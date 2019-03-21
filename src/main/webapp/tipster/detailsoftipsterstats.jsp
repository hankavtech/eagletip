<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%> 
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
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
<link rel="stylesheet" href="https://www.eagletip.com/bootstrap/css/bootstrap.min.css" />
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
<link rel="stylesheet" href="https://www.eagletip.com/nav.css"/>
<script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>
<script type="text/javascript">
  google.charts.load('current', {packages: ['corechart']});
  google.charts.setOnLoadCallback(drawChart);
</script>
<title>EAGLE TIP</title>
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
    #oddspiechart,#marketspiechart,#bookmakerspiechart{
	width:120px;
	height:200px;
	}

#exTab1 .tab-content {
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




table {
    border-collapse: collapse;
    border-spacing: 0;
    
}
tr{
font-size:10px;
}

pie_chart @type_values,library: {legend:{position:'none'}}














</style>

</head>
<body>

<%@include file="../header.jsp" %>

<div id="contents">

     
<div class="container mt-5">   
      
	  <div class="row bg-dark mb-3">
	    <div id="imagediv" class="col-xs-12 text-center col-sm-6 col-md-4">
	    
	    <div class="card bg-dark">
	    <div class="card-block"> 
	    <img class="image-fluid rounded-circle mb-2" style="width:100px;height:100px" src="https://www.eagletip.com/images/tipsters/${requestScope.toplist[0][3]}.jpg" onerror="this.onerror=null;https://www.eagletip.com/images/tipsters/${requestScope.toplist[0][3]}.png"/>
	    <div></div>
	    <img class="image-fluid rounded-circle mb-2" style="width:20px;height:20px" src="https://www.eagletip.com/images/sport/${requestScope.toplist[0][4]}.svg" />
	    <h5 class="text-info text-nowrap"><em style="color:#14805E">${requestScope.toplist[0][3]}</em></h5>
	    </div>
	    </div>
        <span class="float-right"><a class="btn-btn-primary d-sm-none" data-toggle="collapse" data-target=".multi-collapse" aria-expanded="true" aria-controls="multi1 multi2"><img src="https://www.eagletip.com/downarrow.svg" style="width:30px;height:30px"/></a></span>  
	    </div>
	    <div class="col-xs-12 col-sm-6 col-md-4 text-center">
	    <div class="collapse multi-collapse show" id="multi1">
	    <div class="card-body">
	       <table class="table tablestat text-light table-borderless">
	    <thead>
	    </thead>
	    <tbody>
	     <tr>
	     <td class="text-right">Tips&emsp;&emsp;:</td>
	     <td class="font-weight-bold text-left text-warning">${requestScope.toplist[0][0]}</td>
	     </tr>
	     <tr>
	     <td class="text-right">Win&emsp;&emsp;:</td>
	     <td class="font-weight-bold text-left text-warning"><fmt:formatNumber type="number" maxFractionDigits="2" value="${(requestScope.toplist[1][0]*100)/requestScope.toplist[0][0]}"/>%</td>
	     </tr>
	     <tr>
	     <td class="text-right">Profit&nbsp;&emsp;:</td>
	     <td class="font-weight-bold text-left text-warning">${requestScope.toplist[0][2]}</td>
	     </tr>
	    </tbody>
	    </table>
	    </div>
	    <span class="float-right" style="color:red"><a class="btn-btn-primary d-none d-sm-block d-md-none" data-toggle="collapse" data-target="#multi2"><img src="https://www.eagletip.com/downarrow.svg" style="width:30px;height:30px"/></a></span>
	    </div>
	    
	    </div>
	    
	    
	    
	    
	    
	    <c:if test="${requestScope.category=='free'}">
	    
	    
					<div class="col-xs-12 col-sm-12 col-md-4 align-self-center">
					<div class="collapse multi-collapse show" id="multi2">
					
					 <div class="d-flex flex-column align-items-center mt-5">
					 <div id="subscribealert"></div>
		            <button class="btn d-block subscribebutton" style="background-color:#14805E;"><span class="text-warning">SUBSCRIBE</span></button>
            	    </div>
            	    </div>
            	    </div>			
	    </c:if>
	    
	    
	    
	    <c:if test="${requestScope.category!='free'}">
	    
	    
	    
	    <div class="text-center col-xs-12 col-sm-12 col-md-4">
	     <div class="collapse multi-collapse show" id="multi2">
	     <div class="d-flex flex-column align-items-center mt-5">
	     <select id="monthsselect" name="monthsselect" class="mb-4 d-block">
	      <option value="1">1 month</option>
		  <option value="2">2 months</option>
		  <option value="3">3 months</option>
	     </select>
	      <button class="btn d-block add_to_cart" style="background-color:#14805E"><span class="text-warning" id="selectedsubscription">1 month for 88 $</span></button>
	     </div>
	    </div>
	    </div>
	    </c:if>
	 
	 
	 </div>
	 
	
	  <ul class="nav nav-tabs" id="myTab" role="tablist">
  <li class="nav-item">
    <a class="nav-link" id="home-tab" href="https://www.eagletip.com/tipster/activetips?name=${requestScope.tipstername}" role="tab" aria-controls="home" aria-selected="true">Today's Tips</a>
  </li>
  <li class="nav-item">
    <a class="nav-link active" id="profile-tab" href="https://www.eagletip.com/tipster/stats?name=${requestScope.tipstername}" role="tab" aria-controls="profile" aria-selected="false">Stats</a>
  </li>
  <li class="nav-item">
    <a class="nav-link" id="settings-tab" href="https://www.eagletip.com/tipster/tips?name=${requestScope.tipstername}" role="tab" aria-controls="settings" aria-selected="false">Latest Tips</a>
  </li>
</ul>

<!-- Tab panes -->
<div class="tab-content">
  <div class="tab-pane" id="home" role="tabpanel" aria-labelledby="home-tab"></div>
  <div class="tab-pane active p-3" id="profile" role="tabpanel" aria-labelledby="profile-tab">
  <div class="container">
<div class="row">
<div class="col-xs-10 col-sm-3 text-left" id="marketspiechart">
</div>
<div id="markettable" class="col-xs-10 col-sm-6 pl-5 pull-right">
<table class="table table-striped">
<thead>
<tr>
<th>Market</th>
<th>Tips</th>
<th>Profit</th>
<th>Win %</th>
<th>Yield</th>
<th>Avg Odds</th>
</tr>
</thead>
<tbody>
</tbody>
</table>
</div>
</div>
<div class="row">
<div class="col-xs-10 col-sm-3 text-left" id="oddspiechart">
</div>
<div id="oddstable" class="pl-5 pull-right col-xs-10 col-sm-6">
<table class="table table-striped">
<thead>
<tr>
<th>Range</th>
<th>Tips</th>
<th>Profit</th>
<th>Win %</th>
<th>Yield</th>
<th>Avg Odds</th>
</tr>
</thead>
<tbody>
</tbody>
</table>
</div>
</div>
<div class="row">
<div class="col-xs-12 col-sm-3 text-left" id="bookmakerspiechart">
</div>
<div id="bookmakertable" class="col-xs-12 col-sm-6 pl-5 pull-right">
<table class="table table-striped">
<thead>
<tr>
<th>Bookie</th>
<th>Tips</th>
<th>Profit</th>
<th>Win %</th>
<th>Yield</th>
<th>Avg Odds</th>
</tr>
</thead>
<tbody>
</tbody>
</table>
</div>
</div>
</div>   
  </div>
  <div class="tab-pane" id="messages" role="tabpanel" aria-labelledby="messages-tab">...</div>
  <div class="tab-pane" id="settings" role="tabpanel" aria-labelledby="settings-tab">...</div>
</div>


   
   
</div> 

</div>

<%@include file="../footer.jsp"%>
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

$(document).ready(function(){
	
	 $("#tipsterslink").addClass("text-dark");
	
	var name="${requestScope.toplist[0][3]}";
	
	


	

		 
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
			var currencyvalue="${requestScope.toplist[0][5]}";
			console.log("currency value my"+currencyvalue);
			var months=$("#monthsselect").val();
			
			
			if(sesscurrency==""){
				console.log("initial"+sesscurrency);
				if(typeof getCookie('currency') === 'undefined'){
					console.log("initial"+$(".selectedcurrency").text().trim());
					createCookie("currency",$(".selectedcurrency").text().trim().toUpperCase(),30);
				}
				
				
			  currency=getCookie("currency");
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
				  createCookie("currency",$(this).text().trim().toUpperCase(),30);
				  window.location.replace(window.location.pathname + window.location.search + window.location.hash);
			  });
			}
			else{
				currency=sesscurrency;
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
			
			
			if(sessodds==""){
				if(typeof getCookie('odds') === 'undefined'){
					createCookie("odds",$(".selectedodds").text().trim().toUpperCase(),30);
				}
				
			  odds=getCookie("odds");
			  console.log(odds);
			  $(".selectedodds").html(odds.toUpperCase()+"<i class='fa fa-angle-down'></i>");
			  
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
				  createCookie("odds",$(this).text().trim(),30);
				  window.location.replace(window.location.pathname + window.location.search + window.location.hash);
			  });
			}
			else{
				odds=sessodds;
				$(".selectedodds").html(odds.toUpperCase()+"<i class='fa fa-angle-down'></i>");
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
						 data:{odds:$(this).text().trim(),pageredirect:window.location.href},
						 success:function(){
							 window.location.replace(window.location.pathname + window.location.search + window.location.hash);
						 }
					  });
				  });
			
				console.log("session odds is"+ odds);
			}
			
			var min;
			if(sesstimezone==""){
				if(getCookie("timezone")===null){
					if($(window).width()>575){
					createCookie("timezone",$(".selectedtimezone").text().trim(),30);
					createCookie("min",$(".selectedtimezone").attr("data-min"),30);
					console.log("minutes is"+$(".selectedtimezone").attr("data-min"));
					}
					else{
						
						$(document).on('click',".settings",function () {
						createCookie("timezone",$("#timezone-selector option:selected").attr("id"),30);
						createCookie("min",$("#timezone-selector").val(),30);
						});
					}
				}
				
			  timezone=getCookie("timezone");
			  min=getCookie("min");
			  console.log(odds);
			  if($(window).width()>575){
				  console.log("iam niffer");
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
				var calpart=timezone.substring(4,timezone.length);
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
							 $("#timezone-selector").val(timezone);
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
					console.log(currencyvalue);
					console.log(parseFloat(currencyvalue));
					$("#selectedsubscription").html(""+months +" months for " + parseFloat(currencyvalue*months).toFixed(2) + "<img style='width:20px;height:20px' src='https://www.eagletip.com/images/css/currency/"+currency+".svg'/>");
				}
				
			});
			
			
		 

		
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
		
var w_width=window.innerWidth;
if(w_width>=576 && w_width<768){
	
	$("#multi2").removeClass("show");
	
}
else if(w_width<=575){
	$("#multi1").removeClass("show");
	$("#multi2").removeClass("show");
	
}
 $(window).resize(function(){
  var new_width=window.innerWidth;
	if($("#multi1").hasClass("show")){
		if(window.innerWidth<=575)
		{
	    $("#multi1").removeClass("show");
        }
	}
	else{
		if(window.innerWidth>=576)
		{
	    $("#multi1").addClass("show");
        }
	}
	
	if($("#multi2").hasClass("show")){
		if(window.innerWidth<768)
		{
	    $("#multi2").removeClass("show");
        }
	}
	else
		{
		if(window.innerWidth>=768)
		{
	    $("#multi2").addClass("show");
        }
		
		}
		
	w_width=new_width; 
	
	
});

 $(".add_to_cart").click(function(){
	    
	   var button=$(this);
	   console.log("currency value is"+ currencyvalue);
	   var nextcurrency=currencyvalue;
	   var name="${requestScope.toplist[0][3]}";
	   console.log(""+name);
	   var items2=items;
		$.ajax({
			method:"POST",
			url:"https://www.eagletip.com/AddCartItem",
			data:{
				itemname:name,
				itemprice:"${requestScope.toplist[0][5]}",
				itemmonths:months	
			},
			success:function(response){
				console.log("item added will be"+response);

				if(response==="error"){
					alert("Item already in cart");
				}
				else{
					items=items2+1;
				  	$("#checkout_items1").html(items);
					$("#checkout_items").html(items);
					console.log(response);
					 var container=$(button).parent().parent().parent().parent();
					    var productCard=$(container).find("#imagediv").find("img:first");
					    var position = productCard.offset();	    
					    var carticon=$(".fa-shopping-cart");
					    var posicon = carticon.offset();
					    $("body").append('<div class="floating-cart" style="position:absolute;"></div>');		
						var cart = $('div.floating-cart');		
						productCard.clone().appendTo(cart);
						$(cart).css({top: position.top + "px",left: (position.left + productCard.width()) + "px"}).animate({'top' : posicon.top + 'px', "left" : posicon.left + 'px','opacity':0},3000,function(){
							$(this).remove();
						});
						var res=JSON.parse(response);
						
						
					var convcurrency=parseFloat(res.price);
					console.log("conv curre"+convcurrency);
					if(currency==='EUR'){
						convcurrency=parseFloat(convcurrency*.87).toFixed(2);
					}
					else if(currency==='GBP'){
						convcurrency=parseFloat(convcurrency*.79).toFixed(2);
					}
					$("#cartitems").append("<div class='row justify-content-between'><div class='col-xs-4'><img style='width:30px;height:30px' class='image-fluid rounded-circle mb-2' src='https://www.eagletip.com/images/tipsters/"+res.name+".jpg'/><p class='text-nowrap text-left'>"+res.name+"</p></div><div class='col-xs-2'><p><b>"+res.months+" month subscription</b></p></div><div class='col-xs-2'><span><b>"+parseFloat(convcurrency*parseInt(res.months)).toFixed(2)+"</b> </span> <img style='width:20px;height:20px' src='https://www.eagletip.com/images/css/currency/"+currency+".svg'/><button type='button' id='removesubscription' class='close' aria-label='Close'><span style='color:red;' aria-hidden='true'>&times;</span></button></div></div>");
					console.log("item is added to div");
					
					
				   
					 $("button.close").click(function(e){
							console.log("hello");
							var button1=$(this);
							$.ajax({
								method:"POST",
								url:"https://www.eagletip.com/DeleteCartItem",
								data:{
									itemname:"${requestScope.toplist[0][3]}",
									itemprice:"${requestScope.toplist[0][5]}",
									itemmonths:$("#monthsselect").val()	
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
				
			}
			
			
		});
		
		
	});	

 
 
	google.charts.load("current", {packages:["corechart"]});
    	var mm;
    	var om;
    	var bm;
       $.ajax({
    	   method:"GET",
    	   url:"https://www.eagletip.com/GetDataForPieCharts",
    	   data:{name:name},
    	   contentType:"application/json",
    	   success:function(response){
    		  var str=JSON.parse(response);
    		 mm=JSON.parse(str[0]);      //market map
    		 om=JSON.parse(str[2]);      //odds map
    		 bm=JSON.parse(str[1]);      //bookmaker map
    		 mmtable=JSON.parse(str[3]);
    		 bmtable=JSON.parse(str[4]);
    		 omtable=JSON.parse(str[5]);
    		 $.each(mmtable,function(index,value){
    			 console.log(value);
    				 $("#markettable > table").append("<tr><td class='text-nowrap'>"+index+"</td><td class='text-nowrap'>"+value[0]+"</td><td class='text-nowrap'>"+parseInt(value[1])+"</td><td class='text-nowrap'>"+parseInt(value[2])+"%</td><td class='text-nowrap'>"+parseFloat(value[3]).toFixed(2)+"</td><td class='text-nowrap oddscolumn'>"+parseFloat(value[4]).toFixed(2)+"</td></tr>");
    			});
    		 
    		 $.each(omtable,function(index,value){
    			 console.log(value);
    				 $("#oddstable > table").append("<tr><td class='text-nowrap'>"+index+"</td><td class='text-nowrap'>"+value[0]+"</td><td class='text-nowrap'>"+parseInt(value[1])+"</td><td class='text-nowrap'>"+parseInt(value[2])+"%</td><td class='text-nowrap'>"+parseFloat(value[3]).toFixed(2)+"</td><td class='text-nowrap oddscolumn'>"+parseFloat(value[4]).toFixed(2)+"</td></tr>");
    			});
    		 $.each(bmtable,function(index,value){
    			 console.log(value);
    				 $("#bookmakertable > table").append("<tr><td class='text-nowrap bookmakercolumn'>"+index+"</td><td class='text-nowrap'>"+value[0]+"</td><td class='text-nowrap'>"+parseInt(value[1])+"</td><td class='text-nowrap'>"+parseInt(value[2])+"%</td><td class='text-nowrap'>"+parseFloat(value[3]).toFixed(2)+"</td><td class='text-nowrap oddscolumn'>"+parseFloat(value[4]).toFixed(2)+"</td></tr>");
    			});
    		 
    		 
    			var bookmakercolumns=document.getElementsByClassName("bookmakercolumn");
    	    	var bookieurls={"1xbet":"https://1xbet.com","188bet":"https://www.188bet.com/","888sport":"https://www.888sport.com/","bet365":"https://www.bet365.com","betathome":"https://www.bet-at-home.com","betclick":"https://en.betclic.com","betdaq":"https://www.betdaq.com","betfairsports":"https://www.betfair.com/sport","betfred":"https://www.betfred.com","betrally":"https://www.betrally.com","betsafe":"https://www.betsafe.com/en","betvictor":"https://www.betvictor.com","betwaysports":"https://sports.betway.com/en/sports","boylesports":"http://www.boylesports.com","bwinsports":"https://sports.bwin.com/en/sports","dafabet":"https://www.dafabet.com/in","intertops":"https://intertops.eu","interwetten":"https://www.interwetten.com","ladbrokes":"https://www.ladbrokes.com/home/en","mansion88":"https://www.mansion88.com","matchbook":"https://www.matchbook.com","netbet":"https://sport.netbet.co.uk","paddypower":"https://www.paddypower.com/bet","parimatch":"https://www.parimatch.com","pinnaclesports":"https://www.pinnacle.com/en","sbobet":"https://www.sbobet.com","tipbet":"https://www.tipbet.com","totesport":"https://sports.tote.co.uk","unibet":"https://www.unibet.com","williamhillsports":"http://sports.williamhill.com"};
    	    	 for(var i=0;i<bookmakercolumns.length;i++){
    	    		 bookmakercolumns[i].innerHTML="<a href='"+bookieurls[bookmakercolumns[i].innerText.toLowerCase().trim()]+"' target='_blank'><img src='https://www.eagletip.com/images/bookmakers/"+bookmakercolumns[i].innerText.toLowerCase().trim()+".png' style='width:40px;height:20px'/></a>";
    	    		  
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
    		 var data=[];
    		 var data2=[];
    		 var data3=[];
    	       data.push(['market','tips count']);
    	       data2.push(['odds','tips count']);
    	       data3.push(['bookmaker','tips count'])
    	       console.log(data);
    	       $.each(mm,function(index,value){
    	    	 data.push([index,parseInt(value)]); 
    	       });
    	       $.each(om,function(index,value){
      	    	 data2.push([index,parseInt(value)]); 
      	       });
    	       $.each(bm,function(index,value){
      	    	 data3.push([index,parseInt(value)]); 
      	       });
    	       
    	       google.charts.setOnLoadCallback(drawChart);
    	       google.charts.setOnLoadCallback(drawoddschart);
    	       google.charts.setOnLoadCallback(drawbookmakerschart);
    	       function drawChart() {
    	         var data1 = google.visualization.arrayToDataTable(data);

    	         var options = {
    	           is3D: true,
    	           legend:'none',
    	         };

    	         var chart = new google.visualization.PieChart(document.getElementById('marketspiechart'));

    	         /* google.visualization.events.addListener(chart, 'ready', function () {
    	           document.getElementById('marketspiechart').innerHTML = '<img src="' + chart.getImageURI() + '">';
    	         }); */
    	         
    	         chart.draw(data1, options);
    	         
    	       }
    	       
    	       function drawoddschart(){
    	    	   var data1 = google.visualization.arrayToDataTable(data2);

      	         var options = {
      	           is3D: true,
      	           legend:'none',
      	         };

      	         var chart = new google.visualization.PieChart(document.getElementById('oddspiechart'));
      	         chart.draw(data1, options);
      	         
    	       }
    	       
    	       function drawbookmakerschart(){
    	    	   var data1 = google.visualization.arrayToDataTable(data3);

      	         var options = {
      	           is3D: true,
      	         legend:'none',
      	         };

      	         var chart = new google.visualization.PieChart(document.getElementById('bookmakerspiechart'));
      	         chart.draw(data1, options);
      	         
    	       }

    	       
    		 
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
    	
    	
    	
    	
    	if("${requestScope.category}"=='free'){
    		if(sessionuser.trim()!=""){
    		$.ajax({
		    	method:"POST",
		    	url:"https://www.eagletip.com/CheckToDisableSubscribeButton",
		    	data:{username:sessionuser,tipstername:"${requestScope.toplist[0][3]}",email:"${sessionScope.email}"},
		    	success:function(res){
		    		if(res.trim()=="disable"){
		    		$(".subscribebutton").addClass('subscribed');
		    		$(".subscribebutton").text("UNSUBSCRIBE");
		    		
		    		
		    		}
		    	}
		    	
		    	
		    });
    		}
    	}
    	 
    	$(".subscribebutton").click(function(){
                if(sessionuser.trim()==""){
    			$("#subscribealert").css("display","block");
    			$("#subscribealert").html("<p class='text-danger'>Please Register or Login to subscribe</p>");
    			setTimeout(function(){
    				$("#subscribealert").css("display","none");
    			},3000);
    		}
    		else{
    			if(!($(".subscribebutton").hasClass("subscribed"))){
	    		    $.ajax({
	    		    	method:"POST",
	    		    	url:"https://www.eagletip.com/SubscribeFreeTipster",
	    		    	data:{username:sessionuser,tipstername:"${requestScope.toplist[0][3]}",email:"${sessionScope.email}"},
	    		    	success:function(){
	    		    		$("#subscribealert").html("<p class='text-success'>You have now subscribed to this tipster</p>");
	    		    		$(".subscribebutton").html("UNSUBSCRIBE");
	    		    		$(".subscribebutton").addClass("subscribed");
	    	    			setTimeout(function(){
	    	    				$("#subscribealert").css("display","none");
	    	    			},3000);   		
	    		    	}
	    		    	
	    		    	
	    		    });
    			}
    			//remove the subscription
    			else{
    				console.log(sessionuser);
    				console.log("${requestScope.toplist[0][3]}");
    				console.log("${sessionScope.email}");
    				  $.ajax({
  	    		    	method:"POST",
  	    		    	url:"https://www.eagletip.com/RemoveSubscribedFreeTipster",
  	    		    	data:{username:sessionuser,tipstername:"${requestScope.toplist[0][3]}",email:"${sessionScope.email}"},
  	    		    	success:function(res){
  	    		    		console.log(res);
  	    		    		if(res.trim()=='success'){
  	    		    		$("#subscribealert").html("<p class='text-success'>You will not receive any tips</p>");
  	    		    		$(".subscribebutton").html("SUBSCRIBE");
  	    		    		$(".subscribebutton").removeClass("subscribed");
  	    	    			setTimeout(function(){
  	    	    				$("#subscribealert").css("display","none");
  	    	    			},3000);   		
  	    		    		}
  	    		    	},
  	    		    	error:function(er){
  	    		    		console.log(er);
  	    		    	}
  	    		    	
  	    		    	
  	    		    });
    				
    			}
    			
    		}
    	});
    	
    	
    	
    	
    
    	

});



</script>
</body>
</html>