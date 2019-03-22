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

<meta charset="UTF-8">
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

@media (min-width:576px){
     .container{
    /*  background:red; */
     font-size:8px;
     }
     
     #brandimage{

       height:30px;
       width:30px;

      }
     
}

@media(min-width:758px){
     .container{
     /* background:blue; */
     font-size:10px;
     }
     
      #brandimage{

       height:50px;
       width:50px;

      }
}
@media(min-width:992px){
     .container{
    /*  background:yellow; */
     font-size:12px
     
     
     }
     
       
      #brandimage{

       height:70px;
       width:70px;

      }
}@media(min-width:1200px){
     .container{
     /* background:green; */
     font-size:14px;
     }
}
@media(max-width:575px){
     .container{
     /* background:red; */
     font-size:8px;
     }
      #brandimage{

       height:30px;
       width:30px;

      }
}

span{
font-size:12px;
}

.col-xs-6,button{
font-size:7px;
width:30px;
height:30px;
}

	

	


</style>


</head>


<body>

<%@include file="header.jsp"%>

<div id="contents">






<div class="container">


  <div class="row justify-content-left offset-1 mb-4 pt-3">
    <img class="mt-2 rounded-circle mr-4" src="https://www.eagletip.com/images/sport/${sessionScope.user_sport}.svg" style="width:30px;height:30px"/>
    <img class="rounded-circle mr-2"  src="https://www.eagletip.com/images/tipsters/${sessionScope.tipster_name}.jpg" onerror="this.onerror=null;this.src='https://www.eagletip.com/images/tipsters/${sessionScope.tipster_name}.png'" style="width:50px;height:50px"/>
    <p class="mt-3">${sessionScope.tipster_name}</p>
  </div>
  <div class="row justify-content-center">

	
	  <!-- start of tip form -->
	<div id="tipform" class="col-10">
		
		  <c:if test="${requestScope.tipstatus=='failed'}">
		              <div id="alert"class="alert alert-danger">
                         <strong>Failed</strong> Match is about to start or You have already posted the tip.
                      </div>
		              
		            </c:if>
		            <c:if test="${requestScope.tipstatus=='success'}">
		              <div id="alert" class="alert alert-success">
                         <strong>Success</strong> Succesfully posted
                      </div>
		              
		            </c:if>
		<form method="POST" class="form" action="TipsForm">
					<div class="form-group">
					<label>League:</label>
					<select id="league" name="league" class="form-control text-dark" readonly>
					</select>
					</div>
					<div class="form-group">
					<label>Tournament:</label>
					<select id="tournament" name="tournament" class="form-control text-dark" readonly>
					</select>
					</div>
					<div class="form-group">
					<label>Pick Match:</label>
					<select id="match" name="match" class="form-control text-dark" readonly required>
					</select>
					</div>
					<div class="form-group invisible">
					<label>match id:</label>
					<input id="matchid" name="matchid" class="form-control text-dark" readonly>
					</div>
					<div class="form-group">
					<label>match date:</label>
					<input id="matchdate" name="matchdate" class="form-control timecolumn text-dark" readonly>
					</div>
					<div class="form-group">
					<label>Team 1:</label>
					<input id="team1" name="team1" class="form-control text-dark" placeholder="Enter Team1" readonly>
					</div>
					<div class="form-group">
					<label>Team 2:</label>
					<input id="team2" name="team2" class="form-control text-dark"  placeholder="Enter Team 2" readonly>
					</div>
					<div class="form-group">
					<label>Market:</label>
					<select class="form-control text-dark" data-header="Pick Market" id="market" name="market" required>
					            <option selected="true" disabled="disabled" >Pick Market</option>			 
					</select>
					</div>
					<div class="form-group">
					<label>Lines:</label>	    
					<select class="form-control text-dark" id="lines" name="lines">				      
					</select>
					</div>
			      <div class="form-group sublines" style="display:none;">     
			      <input class="form-control text-dark" id="sublines" name="sublines"  placeholder="Enter Sublines ex: +2.5"/>
			      </div>
		          
		            
		            <div class="form-group">
		            <label>Bookmaker:</label>
		            <select class="form-control text-dark" data-header="Pick Bookmaker" id="bookmaker" name="bookmaker" required>
					            <option selected="true" disabled="disabled" >Pick Market</option>			 
					</select>
		            <!-- <input id="bookmaker" class="form-control text-dark" maxlength="20" name="bookmaker" placeholder="Enter the bookmaker name" required>  --> 
		            </div>
		             <div class="form-group">
		             <label>Odds:</label>
		            <input id="odds" class="form-control text-dark" name="odds" placeholder="Enter the odds in DECIMAL format" required>  
		            </div>
		             <div class="form-group">
		            <label>Units:</label>
		           <select class="form-control text-dark" id="units" name="units" required>
		              <option disabled="disabled" >Units of Investment(Max 100)</option>
					  <option value="10">10</option>
					  <option value="20">20</option>
					  <option value="30">30</option>
					  <option value="40">40</option>
					  <option value="50">50</option>
					  <option value="60">60</option>
					  <option value="70">70</option>
					  <option value="80">80</option>
					  <option value="90">90</option>
					  <option value="100">100</option>
			       </select>  
		            </div>
		            <div class="form-group">
		            <input id="submit" class="form-control btn btn-dark text-warning" type="submit" value="submit"> 
		            </div>
		
		</form>
	
	
	</div>
 </div>	
</div>

</div>

<%@include file="footer.jsp"%>

<script>
window.setTimeout(function() {
    $("#alert").css("display","none");
}, 4000);



  
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
		
		
	 	
		
		



		


		var loadedwidth=$(window).width();
		console.log(loadedwidth);
		if(loadedwidth>575){
			
			$(window).resize(function(){
				if($(window).width()<576){
				  window.location.reload();
			    }
			});
		}
		if(loadedwidth<576){
			
			$(window).resize(function(){
				if($(window).width()>575){
				  window.location.reload();
			    }
			});
		}
		
		$('.settings').popover({title: "settings", content:'<ul class="list-unstyled settings_dropdown"><li style="vertical-align:center"><label for="odds-selector" class="mb-0"><span class="text-left">Odds</span></label><select id="odds-selector" class="odds-selector mt-0"><option value="DECIMAL">DECIMAL</option><option value="FRACTIONAL">FRACTIONAL</option><option value="AMERICAN">AMERICAN</option></select></li><li style="vertical-align:center"><label for="timezone-selector" class="mb-0"><span class="text-left">Timezone</span></label><select id="timezone-selector" class="timezone-selector mt-0"><option id="UTC-12:00" value="-720">UTC-12:00</option><option id="UTC-11:00" value="-660">UTC-11:00</option><option id="UTC-10:00" value="-600" >UTC-10:00</option><option id="UTC-09:00" value="-540">UTC-09:00</option><option id="UTC-08:00" value="-480">UTC-08:00</option><option id="UTC-07:00" value="-420">UTC-07:00</option><option id="UTC-06:00" value="-360">UTC-06:00</option><option id="UTC-05:00" value="-300">UTC-05:00</option><option id="UTC-04:00" value="-240">UTC-04:00</option><option id="UTC-03:00" value="-180">UTC-03:00</option><option id="UTC-03:30" value="-210">UTC-03:30</option><option id="UTC-02:00" value="-120">UTC-02:00</option><option id="UTC-01:00" value="-60">UTC-01:00</option><option id="UTC+00:00" value="0">UTC+00:00</option><option id="UTC+01:00" value="60">UTC+01:00</option><option id="UTC+02:00" value="120">UTC+02:00</option><option id="UTC+03:00" value="180">UTC+03:00</option><option id="UTC+03:07" value="187">UTC+03:07</option><option id="UTC+03:30" value="210">UTC+03:30</option><option id="UTC+04:00" value="240">UTC+04:00</option><option id="UTC+04:30" value="270">UTC+04:30</option><option id="UTC+05:00" value="300">UTC+05:00</option><option id="UTC+05:30" value="330">UTC+05:30</option><option id="UTC+05:45" value="345">UTC+05:45</option><option id="UTC+06:00" value="360">UTC+06:00</option><option id="UTC+06:30" value="390">UTC+06:30</option><option id="UTC+07:00" value="420">UTC+07:00</option><option id="UTC+08:00" value="480">UTC+08:00</option><option id="UTC+08:45" value="525">UTC+08:45</option><option id="UTC+09:00" value="540">UTC+09:00</option><option id="UTC+09:30" value="570">UTC+09:30</option><option id="UTC+10:00" value="600">UTC+10:00</option><option id="UTC+10:30" value="630">UTC+10:00 </option><option id="UTC+11:00" value="660">UTC+11:00</option><option id="UTC+11:30" value="690">UTC+11:30</option><option id="UTC+12:00" value="720">UTC+12:00</option><option id="UTC+12:45" value="765">UTC+12:45</option><option id="UTC+13:00" value="780">UTC+13:00</option><option id="UTC+14:00" value="840">UTC+14:00</option></select></li><li style="vertical-align:center"><label for="currency-selector" class="mb-0"><span class="text-left">Currency</span></label><select id="currency-selector" class="currency-selector mt-0"><option value="USD">USD</option><option value="EUR">EUR</option><option value="GBP">GBP</option></select></li></ul>', html: true, placement: "bottom"}); 
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
						console.log("initial"+$(".selectedcurrency").text().trim());
						createCookie("currency",$(".selectedcurrency").text().trim(),30);
					}
					
					
				  currency=getCookie("currency");
				  console.log("initial"+currency);
				  $(".selectedcurrency").html(currency.toUpperCase()+"<i class='fa fa-angle-down'></i>");
				  $(".currency_selection").html("");
					if($(".selectedcurrency").text().trim()!="USD"){
						$(".currency_selection").append('<li style="vertical-align:center"><a href="#">USD</a></li>');
						}
						if($(".selectedcurrency").text().trim()!="EUR"){
							$(".currency_selection").append('<li style="vertical-align:center"><a href="#">EUR</a></li>');
							}
						if($(".selectedcurrency").text().trim()!="GBP"){
							$(".currency_selection").append('<li style="vertical-align:center"><a href="#">GBP</a></li>');
							}
				  
				  $(".currency_selection a").click(function(){
					  createCookie("currency",$(this).text().trim(),30);
					  window.location.reload();
				  });
				}
				else{
					currency=sesscurrency;
					console.log("initial"+currency);
					$(".selectedcurrency").html(currency.toUpperCase()+"<i class='fa fa-angle-down'></i>");
					
					  $(".currency_selection").html("");
						if($(".selectedcurrency").text().trim()!="USD"){
							$(".currency_selection").append('<li style="vertical-align:center"><a href="#">USD</a></li>');
							}
							if($(".selectedcurrency").text().trim()!="EUR"){
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
								 window.location.reload();
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
					  createCookie("odds",$(this).text().trim().toUpperCase(),30);
					  window.location.reload();
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
								 window.location.reload();
							 }
						  });
					  });
				
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
				  $(".selectedtimezone").text(timezone);
				  $(".selectedtimezone").attr("data-min",min);
				  $(".timezone_selection a").click(function(){
					  createCookie("timezone",$(this).text(),30);
					  createCookie("min",$(this).attr("data-min"),30)
					  console.log("minutes is"+$(this).attr("data-min"));
					  window.location.reload();
				  });
				  }
				  else{
					 
					  
					  $(document).on('click',".settings",function () {
						  $("#timezone-selector").val(min);
						  $("#timezone-selector").change(function(){
							  createCookie("timezone",$("#timezone-selector option:selected").attr("id"),30);
							  createCookie("min",$("#timezone-selector").val(),30);
							  console.log("minutes is"+$("#timezone-selector").attr("data-min"));
							  window.location.reload();  
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
								 window.location.reload();
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
												 window.location.reload();
											 }
										  });
								  });
						   });  
						  
					}
				}  
			
					var oddscolumns=document.getElementsByClassName("oddscolumn");
				
				   if(odds.toUpperCase()==='DECIMAL'){
					   $("#odds").attr("pattern","[0-9]{1,5}[.]{0,1}[0-9]{0,2}");
				   }
				
				
				
			        if(odds.toUpperCase()==='FRACTIONAL'){
			        	$("#odds").attr("pattern","[0-9]{1,5}[/]{1}[0-9]{1,5}");
			        	
						for (var i = 0; i < oddscolumns.length; i++) {
								oddscolumns[i].innerHTML=(math.fraction(oddscolumns[i].innerHTML).n+"/"+math.fraction(oddscolumns[i].innerHTML).d);
						}
			        }
			        if(odds.toUpperCase()==='AMERICAN'){
			        	$("#odds").attr("pattern","[+-]{0,1}[0-9]{1,5}");
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

		$("#signindropdown").on("click",function(e){7
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
						 window.location.reload();
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
						if(currency.toUpperCase()==='EUR'){
							convcurrency=parseFloat(convcurrency*.87).toFixed(2);
						}
						else if(currency.toUpperCase()==='GBP'){
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
					 window.location.reload();
					
				 }
				 
			});
			
			
			
		});
		
		
		
		$("#login").on("click",function(e){
			$("#email1").removeClass("is-invalid");
			$("#password1").removeClass("is-invalid");
			e.preventDefault();	
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
						 window.location.reload();
					 }
				 }
				 
			 });
			
			
			
		});
		


	
	
	
	
	
	
	
	


    $("#odds").attr("placeholder","Enter Odds in "+odds+" format");
	var marketresponse;
	var marketcall;
	
	
	
	
	
	$.ajax({
	    method:"GET",
	    url:"https://www.eagletip.com/GiveLeagues",
	    data:{sport:"${sessionScope.user_sport}"},
		success:function(response){
			marketresponse=response;
			$.each(response,function(index,value){
				$("#league").append('<option class="text-success" value='+index+'>'+index+'</option>');		
				
			});
			
			var selectedText = $("#league option:selected").html();
			$.each(response[selectedText],function(index,value){
				$("#tournament").append('<option class="text-success" value='+value+'>'+value+'</option>');	
			});
			var selectedTournament=$("#tournament option:selected").html();
			
			  $.ajax({
					 method:"get",
					 url:"https://www.eagletip.com/GiveMatches",
					 data:{league:selectedText,tournament:selectedTournament},
					 success:function(response){
						 console.log(response);
						 $.each(response,function(index2,val){
							 $("#match").append("<option data-matchid='"+val[3]+"' data-team1='"+val[0]+"' data-team2='"+val[1]+"' data-date='"+val[2]+"' value='"+val[0]+"'>"+val[0]+"<span class='text-light'> vs </span>"+val[1]+"</option>");
						 });
						 
						 $("#matchid").val($("#match").find(':selected').attr('data-matchid'));
						 $("#matchdate").val($("#match").find(':selected').attr('data-date'));
						 $("#matchdate").val(moment.utc($("#matchdate").val()).utcOffset(parseInt(min)).format('ll HH:mm'));
						 $("#team1").val($("#match").find(':selected').attr('data-team1'));
						 $("#team2").val($("#match").find(':selected').attr('data-team2'));
					 }
				});
				
			    
			
			
		}
	});
	
	$.ajax({
		method:"GET",
		url:"https://www.eagletip.com/GiveMarkets",
		data:{sport:"${sessionScope.user_sport}"},
		success:function(response){
			/* var json=JSON.parse(response); */
		   marketcall=response;
			$.each(response.markets,function(index,value){
				
				 var option = document.createElement("option");
					option.text=value.name;
					option.value=value.name;
					document.getElementById("market").appendChild(option);
					
			});
			
			
		
			
		}
					    							
	});
	
	$("#league").on('change', function (e) {
		$('#tournament')
	    .find('option')
	    .remove()
	    .end();
		$('#match')
	    .find('option')
	    .remove()
	    .end();
		var selectedText = $("#league option:selected").html();
		console.log(marketresponse);
	    $.each(marketresponse[selectedText],function(index,value){
			$("#tournament").append('<option value='+value+'>'+value+'</option>');	
			console.log(value);
		});
	    
	    var selectedTournament=$("#tournament option:selected").html();
	    //call to get list of matches-test on change in league option value
	    $.ajax({
			 method:"get",
			 url:"https://www.eagletip.com/GiveMatches",
			 data:{league:selectedText,tournament:selectedTournament},
			 success:function(response){
				 $.each(response,function(index2,val){
					 $("#match").append("<option data-matchid='"+val[3]+"' data-team1='"+val[0]+"' data-team2='"+val[1]+"' data-date='"+val[2]+"' value='"+val[0]+"'>"+val[0]+"<span class='text-light'> vs </span>"+val[1]+"</option>");
					 $("#matchid").val($("#match").find(':selected').attr('data-matchid'));
					 $("#matchdate").val($("#match").find(':selected').attr('data-date'));
					 $("#matchdate").val(moment.utc($("#matchdate").val()).utcOffset(parseInt(min)).format('ll HH:mm'));
					 $("#team1").val($("#match").find(':selected').attr('data-team1'));
					 $("#team2").val($("#match").find(':selected').attr('data-team2'));
				 });
			 }
		});
	    
	    
	    
	    
	});
	
	
	$("#tournament").on('change', function (e) {
		$('#match')
	    .find('option')
	    .remove()
	    .end();
		var selectedText = $("#league option:selected").html();
	    
	    var selectedTournament=$("#tournament option:selected").html();
	    //call to get list of matches-test on change in league option value
	    $.ajax({
			 method:"get",
			 url:"https://www.eagletip.com/GiveMatches",
			 data:{league:selectedText,tournament:selectedTournament},
			 success:function(response){
				 
				 $.each(response,function(index2,val){
					 $("#match").append("<option data-matchid='"+val[3]+"' data-team1='"+val[0]+"' data-team2='"+val[1]+"' data-date='"+val[2]+"' value='"+val[0]+"'>"+val[0]+"<span class='text-light'> vs </span>"+val[1]+"</option>");
					 $("#matchid").val($("#match").find(':selected').attr('data-matchid'));
					 $("#matchdate").val($("#match").find(':selected').attr('data-date'));
					 $("#matchdate").val(moment.utc($("#matchdate").val()).utcOffset(parseInt(min)).format('ll HH:mm'));
					 $("#team1").val($("#match").find(':selected').attr('data-team1'));
					 $("#team2").val($("#match").find(':selected').attr('data-team2'));
					 
					 
				 });
			 }
		});
	    
	    
	    
	    
	});
	
	$("#match").on('change', function (e) {
					 $("#matchid").val($("#match").find(':selected').attr('data-matchid'));
					 $("#matchdate").val($("#match").find(':selected').attr('data-date'));
					 $("#matchdate").val(moment.utc($("#matchdate").val()).utcOffset(parseInt(min)).format('ll HH:mm'));
					 $("#team1").val($("#match").find(':selected').attr('data-team1'));
					 $("#team2").val($("#match").find(':selected').attr('data-team2'));	    
	    
	    
	});
	
	$("#market").change(function(event){
		$("#lines").html("");
		var marketname=event.currentTarget.value;
		$.each(marketcall.markets,function(index2,val2){
				if(val2.name.trim()==marketname){
					$.each(val2.Mylines,function(ind,val){
						 var option = document.createElement("option");
							option.text=val;
							option.value=val;
							document.getElementById("lines").appendChild(option);
					});
					if(val2.sublines==true){
						$(".sublines").css("display","block");
					}
					else{
						$(".sublines").css("display","none");
					}
					
				    return 0;	
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



	var userexist="${sessionScope.username}";
	if(userexist!=""){
	$.ajax({
		method:"POST",
		data:{sportname:$('#sportselect').val()},
		url:"https://www.eagletip.com/CheckOneSportPerUser",
		success:function(res){
			console.log(res);
			$('#sportselect').removeClass("is-invalid");
			$('#sportselect').removeClass("is-valid");
			if(res.trim()=="twotipstersofsamesport"){
				$("#sportselect").addClass("is-invalid");
			}
			else{
				$("#sportselect").addClass("is-valid");
			}
			if($("#sportselect").hasClass("is-invalid")){
				$("#sportinvalid").show();
			}
			else{
				$("#sportinvalid").hide();
			}

		}
		
	});
	
	}


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
	  
	
	var bookieurls={"1xbet":"https://1xbet.com","188bet":"https://www.188bet.com/","888sport":"https://www.888sport.com/","bet365":"https://www.bet365.com","betathome":"https://www.bet-at-home.com","betclick":"https://en.betclic.com","betdaq":"https://www.betdaq.com","betfairsports":"https://www.betfair.com/sport","betfred":"https://www.betfred.com","betrally":"https://www.betrally.com","betsafe":"https://www.betsafe.com/en","betvictor":"https://www.betvictor.com","betwaysports":"https://sports.betway.com/en/sports","boylesports":"http://www.boylesports.com","bwinsports":"https://sports.bwin.com/en/sports","dafabet":"https://www.dafabet.com/in","intertops":"https://intertops.eu","interwetten":"https://www.interwetten.com","ladbrokes":"https://www.ladbrokes.com/home/en","mansion88":"https://www.mansion88.com","matchbook":"https://www.matchbook.com","netbet":"https://sport.netbet.co.uk","paddypower":"https://www.paddypower.com/bet","parimatch":"https://www.parimatch.com","pinnaclesports":"https://www.pinnacle.com/en","sbobet":"https://www.sbobet.com","tipbet":"https://www.tipbet.com","totesport":"https://sports.tote.co.uk","unibet":"https://www.unibet.com","williamhillsports":"http://sports.williamhill.com"};
	 
	 
	 for (var key in bookieurls) {
		    if (bookieurls.hasOwnProperty(key)) {
		    	$("#bookmaker").append('<option value="'+key+'" data-img="bookmakers/'+key+'.png">'+key+'</option>');
		    }
		}
	 
	 
	
	
});


	
	
	
	
	
		
</script>
</body>
</html>