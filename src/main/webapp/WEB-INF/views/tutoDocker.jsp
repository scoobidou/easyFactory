<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:genericPage>
    <jsp:attribute name="header">
    </jsp:attribute>
    <jsp:attribute name="footer">
    </jsp:attribute>
    <jsp:body>
    <br>
    <br>
    <br>
    <br>
    
    	<!-- TUTO DOCKER -->
    	<div class="row">
	    	<div class="col-md-offset-1">
	    	<br>
	    		<h1 class="text-center"> Get started with EasyFactory</h1>
				<h2> Prerequisites : </h2>
				<ul>
					<h4><li>Download and install Ubuntu Server 16</li></h4>
				</ul>
				<h2>Dependences and updates</h2>
				<blockquote>
					<li>apt-get update -y</li>
					<li>apt-get dist-upgrade -y</li>
					<li>apt-get install -y curl ca-certificates apt-transport-https</li>
				</blockquote>
				<h2>Install Docker & Docker Compose</h2>
				<blockquote>
					<li>curl -L https://github.com/docker/compose/releases/download/1.8.0/docker-compose-`uname -s`-`uname -m` > /usr/local/bin/docker-compose</li>
					<li>chmod +x /usr/local/bin/docker-compose</li>
					<li>curl -L https://raw.githubusercontent.com/docker/compose/$(docker-compose version --short)/contrib/completion/bash/docker-compose > /etc/bash_completion.d/</li>
				</blockquote>
				<h2>Expose Docker on the internet to enable remote access to docker daemon</h2>
				<blockquote>
					<li> mkdir /etc/systemd/system/docker.service.d </li>
					<li>echo "[Service]" >> /etc/systemd/system/docker.service.d/docker_opts.conf</li>
					<li>echo "ExecStart=" >> /etc/systemd/system/docker.service.d/docker_opts.conf</li>
					<li>echo "ExecStart=/usr/bin/docker daemon -H tcp://192.168.4.248:2375 -H unix:///var/run/docker.sock" >> /etc/systemd/system/docker.service.d/docker_opts.conf</li>
					<li>systemctl daemon-reload</li>
					<li>systemctl restart docker</li>
				</blockquote>
				<h2>Execute with Docker Compose</h2>
				<blockquote>
					<li>cd /CHEMIN_ACCES_DOCKER COMPOSER/</li>
					<li>docker-compose up -d</li>
				</blockquote>
				<h4>You can also get this <a href="">script</a> to install.</h4>
	    	</div>
	    	<div class="row">
	    		<div class="col-md-offset-1">
	    			<form action="tutoDocker_2">
					    <input type="submit" class="btn btn-primary" value="Next step" />
					</form>
	    			<br>
	    			<br>
	    		</div>
	    	</div>
    	</div>
    </jsp:body>
</t:genericPage>