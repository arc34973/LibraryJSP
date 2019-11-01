<%@ include file="common/header.jspf"%>
<%@ include file="common/navigation.jspf"%>
<div class="container">
	<div class="row">
		<div class="col-md-6 col-md-offset-3 ">
			<div class="panel panel-primary">
				<div class="panel-heading">
					<spring:message code="userDetails" />
				</div>
				<div class="panel-body">
					<form:form method="post" modelAttribute="user">
						<form:hidden path="id" />
						<fieldset class="form-group">
							<form:label path="firstName">
								<spring:message code="firstName" />
							</form:label>
							<form:input path="firstName" type="text" class="form-control"
								required="required" />
							<form:label path="lastName">
								<spring:message code="lastName" />
							</form:label>
							<form:input path="lastName" type="text" class="form-control"
								required="required" />
							<form:label path="address">
								<spring:message code="address" />
							</form:label>
							<form:input path="address" type="text" class="form-control"
								required="required" />

							<form:label path="role">
								<spring:message code="role" />
							</form:label>
							<form:select path="role" items="${allRole}" class="form-control"></form:select>

							<br>
							<form:label path="status">
								<spring:message code="status" />
							</form:label>
							<form:select path="status" items="${allStatus}" class="form-control"></form:select>

							<br>
							<form:label path="userName">
								<spring:message code="userName" />
							</form:label>
							<form:input path="userName" type="text" class="form-control"
								required="required" />
							<form:label path="password">
								<spring:message code="password" />
							</form:label>
							<form:input path="password" type="password" class="form-control"
								required="required" />

						</fieldset>
						<button type="submit" class="btn btn-success">
							<spring:message code="save" />
						</button>
					</form:form>
				</div>
			</div>
		</div>
	</div>
</div>
<%@ include file="common/footer.jspf"%>