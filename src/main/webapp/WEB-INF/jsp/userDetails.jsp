<%@ include file="common/header.jspf"%>
<%@ include file="common/navigation.jspf"%>

<div class="container">
	<div></div>
	<br>
	<div class="panel panel-primary">
		<div class="panel-heading">
			<h3>
				<spring:message code="userDetails" />
			</h3>
		</div>
		<div class="panel-body">
			<table class="table table-striped">
				<thead>
					<tr>
						<th width="10%"><spring:message code="firstName" /></th>
						<th width="10%"><spring:message code="lastName" /></th>
						<th width="25%"><spring:message code="address" /></th>
						<th width="10%"><spring:message code="role" /></th>
						<th width="10%"><spring:message code="status" /></th>
						<th width="10%"><spring:message code="userName" /></th>
						
					</tr>
				</thead>
				<tbody>
					<tr>
						<td>${user.firstName}</td>
						<td>${user.lastName}</td>
						<td>${user.address}</td>
						<td>${user.role}</td>
						<td>${user.status}</td>
						<td>${user.userName}</td>
						
					</tr>

				</tbody>

			</table>
		</div>
	</div>

</div>
<%@ include file="common/footer.jspf"%>