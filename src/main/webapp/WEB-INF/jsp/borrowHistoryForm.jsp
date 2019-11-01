<%@ include file="common/header.jspf"%>
<%@ include file="common/navigation.jspf"%>
<div class="container">
	<div class="row">
		<div class="col-md-6 col-md-offset-3 ">
			<div class="panel panel-primary">

				<div class="panel-heading">
					<spring:message code="borrowHistoryUpdate" />
				</div>
				<div class="panel-body">
					<form:form method="post" modelAttribute="borrowHistory">
						<form:hidden path="id" />
						<fieldset class="form-group">

							<form:label path="user.firstName">
								<spring:message code="userFirstName" />
							</form:label>
							<form:textarea path="user.firstName" type="text"
								class="form-control" required="required" />

							<form:label path="user.lastName">
								<spring:message code="userLastName" />
							</form:label>
							<form:textarea path="user.lastName" type="text"
								class="form-control" required="required" />

							<form:label path="book.title">
								<spring:message code="title" />
							</form:label>
							<form:textarea path="book.title" type="text" class="form-control"
								required="required" />

							<form:label path="dateBorrow">
								<spring:message code="dateBorrowOut" />
							</form:label>
							<form:textarea path="dateBorrow" type="text" class="form-control"
								required="required" />

							<form:label path="dateTurnBack">
								<spring:message code="dateTurnBack" />
							</form:label>
							<form:textarea path="dateTurnBack" type="text"
								class="form-control" required="required" />
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