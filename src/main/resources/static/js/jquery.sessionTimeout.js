
$(document).ready(function() {
	var interval;

	var timeout = $("#sessiontime").text();
	$(document).on('mousemove', function() {
		sessionExpiry()
	}).mousemove();

	var interval;
	$(document).on('keydown', function() {
		sessionExpiry()
	}).mousemove();

	function sessionExpiry() {
		clearInterval(interval);
		var coutdown = timeout, $timer = $('.timer'); // After 5 minutes session expired (keyboard button press code)
		$timer.text("Session will expire in "+ coutdown+" sec");
		interval = setInterval(function() {
			$timer.text("Session will expire in "+ --coutdown+" sec");
			if (coutdown === 0) {
				 window.location.href ="/logoutpage";
				 alert("Session expired User successfully logout.");
			}

		}, 1000);

	}

});

