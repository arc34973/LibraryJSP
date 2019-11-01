<%@ include file="common/header.jspf"%>
<%@ include file="common/navigation.jspf"%>

<div class="container">
	<c:if test="${!isSearchResult}">
		<div>
			<a type="button" class="btn btn-primary btn-md" href="/add-user"><spring:message
					code="addUser" /></a>

		</div>
	</c:if>

	<br>
	<div class="panel panel-primary">
		<div class="panel-heading">
			<h3>
				<spring:message code="listOfUsers" />
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

						<th width="15%"></th>

					</tr>
				</thead>
				<tbody>
					<c:forEach items="${users}" var="user">
						<tr>
							<td>${user.firstName}</td>
							<td>${user.lastName}</td>
							<td>${user.address}</td>
							<td>${user.role}</td>
							<td>${user.status}</td>
							<td>${user.userName}</td>

							<td><c:if test="${isSearchResult}">
									<a type="button" class="btn btn-success"
										href="/showDetails?id=${user.id}"> <spring:message
											code="details" />
									</a>
									<a type="button" class="btn btn-success"
										href="/show_History?id=${user.id}"> <spring:message
											code="history" />
									</a>
								</c:if> <c:if test="${!isSearchResult}">


									<a type="button" class="btn btn-success"
										href="/update_user?id=${user.id}"> <spring:message
											code="update" />
									</a>
									<a type="button" class="btn btn-warning"
										href="/delete_user?id=${user.id}"> <spring:message
											code="delete" />
									</a>
								</c:if></td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
	</div>

</div>
<%@ include file="common/footer.jspf"%>