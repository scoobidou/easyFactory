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
	    		<h1 class="text-center"> Get started with EasyFactory - 2nd part</h1>
				<h2> Prerequisites : </h2>
				<ul>
					<h4><li>Execute all steps of <a href="tutoDocker"> first part</a></li></h4>
				</ul>
				<h2>Docker Compose</h2>
				<blockquote>
					<h4>Docker Compose allow us to configure and execute services like Mysql, Jenkins, Sonar and others.. </h4>
					<h4>You can download this file <a href="scripts/docker-compose.yml" >here</a></h4>
				</blockquote>
				<h2>Mysql</h2>
				<blockquote>
					<h4>Default password is implemented in the docker-compose file. Here it's "NETWORK".</h4>
					<h4>You can change it before installation.</h4>
					<br>
					<h4>Create a new user</h4>
					<h4><li>mysql –u root –p </li></h4>
					<h4><li>CREATE USER "user"@"localhost";</li></h4>
					<h4><li>SET password FOR "user"@"localhost";</li></h4>
					<h4><li>quit</li></h4>
					<br>
					<h4>You can also run this in a terminal</h4>
					<h4><li>docker exec –it mysql mysql_secure_installation</li></h4>
					<h4>This will allow you to change the root password, delete anonymous account, remove remote access for root, delete test database and reload privileges.</h4>
				</blockquote>
				<h2>PhpMyAdmin</h2>
				<blockquote>
					<h4>You can connect to phpMyAdmin with id created in MySql.</h4>
				</blockquote>
				<h2>GitLab</h2>
				<blockquote>
					<h4>At the first start, you can chose a root password</h4>
					<h4>After you just have to create a project and push it to your repository</h4>
				</blockquote>
				<h2>SonarQube</h2>
				<blockquote>
					<h4>You can connect to this service with admin/admin.</h4>
					<h4>Change this and create a project. You need to keep the "project key" for Jenkins configuration.</h4>
				</blockquote>
				<h2>Jenkins</h2>
				<blockquote>
					<h4>At the first start of Jenkins, a token will be needed. To get this token please write this in your terminal.</h4>
					<h4><li>docker logs jenkins</li></h4>
					<div class="panel panel-default ">
						<img src="images/JenkinsLogs.jpg"/>
					</div>
					<h4><li>Select : install suggested plugins</li></h4>
					<h4><li>Create administrator</li></h4>
					<h4><li>Create users or use LDAP</li></h4>
					<h4><li>Create a new project</li></h4>
					<h4>Now you can link your project to differents services like GitLab, Sonar, etc..</h4>
				</blockquote>
    	</div>
    </jsp:body>
</t:genericPage>