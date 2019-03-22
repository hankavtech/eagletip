

 $(document).ready(function(e){
	 
	 $('.settings').popover({title: "settings", content:'<ul class="list-unstyled settings_dropdown"><li style="vertical-align:center"><label for="odds-selector" class="mb-0"><span class="text-left">Odds</span></label><select id="odds-selector" class="odds-selector mt-0"><option value="decimal">Decimal</option><option value="FRACTIONAL">FRACTIONAL</option><option value="american">American</option></select></li><li style="vertical-align:center"><label for="timezone-selector" class="mb-0"><span class="text-left">Timezone</span></label><select id="timezone-selector" class="timezone-selector mt-0"><option id="UTC-12:00" value="-720">UTC-12:00</option><option id="UTC-11:00" value="-660">UTC-11:00</option><option id="UTC-10:00" value="-600" >UTC-10:00</option><option id="UTC-09:00" value="-540">UTC-09:00</option><option id="UTC-08:00" value="-480">UTC-08:00</option><option id="UTC-07:00" value="-420">UTC-07:00</option><option id="UTC-06:00" value="-360">UTC-06:00</option><option id="UTC-05:00" value="-300">UTC-05:00</option><option id="UTC-04:00" value="-240">UTC-04:00</option><option id="UTC-03:00" value="-180">UTC-03:00</option><option id="UTC-03:30" value="-210">UTC-03:30</option><option id="UTC-02:00" value="-120">UTC-02:00</option><option id="UTC-01:00" value="-60">UTC-01:00</option><option id="UTC+00:00" value="0">UTC+00:00</option><option id="UTC+01:00" value="60">UTC+01:00</option><option id="UTC+02:00" value="120">UTC+02:00</option><option id="UTC+03:00" value="180">UTC+03:00</option><option id="UTC+03:07" value="187">UTC+03:07</option><option id="UTC+03:30" value="210">UTC+03:30</option><option id="UTC+04:00" value="240">UTC+04:00</option><option id="UTC+04:30" value="270">UTC+04:30</option><option id="UTC+05:00" value="300">UTC+05:00</option><option id="UTC+05:30" value="330">UTC+05:30</option><option id="UTC+05:45" value="345">UTC+05:45</option><option id="UTC+06:00" value="360">UTC+06:00</option><option id="UTC+06:30" value="390">UTC+06:30</option><option id="UTC+07:00" value="420">UTC+07:00</option><option id="UTC+08:00" value="480">UTC+08:00</option><option id="UTC+08:45" value="525">UTC+08:45</option><option id="UTC+09:00" value="540">UTC+09:00</option><option id="UTC+09:30" value="570">UTC+09:30</option><option id="UTC+10:00" value="600">UTC+10:00</option><option id="UTC+10:30" value="630">UTC+10:00 </option><option id="UTC+11:00" value="660">UTC+11:00</option><option id="UTC+11:30" value="690">UTC+11:30</option><option id="UTC+12:00" value="720">UTC+12:00</option><option id="UTC+12:45" value="765">UTC+12:45</option><option id="UTC+13:00" value="780">UTC+13:00</option><option id="UTC+14:00" value="840">UTC+14:00</option></select></li><li style="vertical-align:center"><label for="currency-selector" class="mb-0"><span class="text-left">Currency</span></label><select id="currency-selector" class="currency-selector mt-0"><option value="USD">USD</option><option value="EUR">EUR</option><option value="GBP">GBP</option></select></li></ul>', html: true, placement: "bottom"}); 
	 
	 $(window).resize(function(e){
		 console.log(window.innerWidth);
		 if(window.innerWidth>575){
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
		
		
	
	 
	 
	 
	 
	 $(".account_selection a").click(function(e){
		 e.stopPropagation();
		 if($(e.target).is('[data-toggle=modal]')){
			 $($(e.target).data('target')).modal();
		 }
	 });
	 $('.dropdown-menu').click(function(event){
	     event.stopPropagation();
	 });




});