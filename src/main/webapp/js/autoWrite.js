$(document).ready(
		function() {
			setInterval(function() {
				
				var user = document.getElementById("ssh_user").value
				var host = document.getElementById("ssh_host").value
				var full_host = user+"@"+host;
				if(user != "" && host!=""){
					document.getElementById("ssh_host_full").value = user+"@"+host;
				}
				else{
					document.getElementById("ssh_host_full").value="";
				}
			},100);
		});