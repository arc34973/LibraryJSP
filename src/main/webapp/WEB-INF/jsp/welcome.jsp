<%@ include file="common/header.jspf"%>
<%@ include file="common/navigation.jspf"%>

<div class="container">
	<div class="panel panel-primary">
		<div class="panel-heading">
			<spring:message code="homepage" />
		</div>
		<div class="panel-body">
			<spring:message code="welcome" />
			${name}! <br>
			<spring:message code="youHave" />
			${role}.
		</div>
	</div>
</div>
<%@ include file="common/footer.jspf"%>