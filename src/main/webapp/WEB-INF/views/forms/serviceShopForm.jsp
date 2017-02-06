<form class="form" method="post" name="serviceShop" action="${pageContext.request.contextPath}/serviceShop">
	<div class="form-group">
		<label>Versionning</label>
		<select name="versionning_service" class="form-control">
			<c:forEach var="versionningService" items="${requestScope.listVersionning}">
				<option value="${versionningService.id}"><c:out value="${versionningService.serviceName}"/></option>
			</c:forEach>
		</select>
		
		<label>Quality</label>
		<select multiple name="quality_service" class="form-control">
			<c:forEach var="qualityService" items="${requestScope.listQuality}">
				<option value="${qualityService.id}"><c:out value="${qualityService.serviceName}"/></option>
			</c:forEach>
		</select>
		
		<label>Database</label>
		<select name="database_service" class="form-control">
			<c:forEach var="databaseService" items="${requestScope.listDatabase}">
				<option value="${databaseService.id}">${databaseService.serviceName}</option>
			</c:forEach>
		</select>
		
		<label>Database manager</label>
		<select name="databaseManager_service" class="form-control">
			<c:forEach var="databaseManagerService" items="${requestScope.listDatabaseManager}">
				<option value="${databaseManagerService.id}">${databaseManagerService.serviceName}</option>
			</c:forEach>
		</select>
		
		<label>Authentification</label>
		<select name="authentification_service" class="form-control">
			<c:forEach var="authentification_service" items="${requestScope.listAuthentification}">
				<option value="${authentification_service.id}">${authentification_service.serviceName}</option>
			</c:forEach>
		</select>
		<br>
		<button class="btn btn-primary btn-inline" type="submit">Create file</button>
	</div>

</form>