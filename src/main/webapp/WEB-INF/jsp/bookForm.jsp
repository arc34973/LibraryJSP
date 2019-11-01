<%@ include file="common/header.jspf"%>
<%@ include file="common/navigation.jspf"%>
<div class="container">
	<div class="row">
		<div class="col-md-6 col-md-offset-3 ">
			<div class="panel panel-primary">

				<div class="panel-heading">
					<spring:message code="bookDetails" />
				</div>
				<div class="panel-body">
					<form:form method="post" modelAttribute="book">
						<form:hidden path="id" />
						<fieldset class="form-group">

							<form:label path="author">
								<spring:message code="author" />
							</form:label>
							<form:input path="author" type="text" class="form-control"
								required="required" />

							<form:label path="status">
								<spring:message code="status" />
							</form:label>
							<form:select path="status" items="${allStatus}" class="form-control"></form:select>
							<br>
							<form:label path="title">
								<spring:message code="title" />
							</form:label>
							<form:input path="title" type="text" class="form-control"
								required="required" />

							<form:label path="type">
								<spring:message code="type" />
							</form:label>
							<form:select path="type" items="${allType}" class="form-control"></form:select>

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