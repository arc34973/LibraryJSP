<%@ include file="common/header.jspf"%>
<%@ include file="common/navigation.jspf"%>

<div class="container">

	<c:if test="${!showBorrowList}">
		<h2>
			<spring:message code="borrowConfirmation" />
		</h2>
	</c:if>

	<br>
	<div class="panel panel-primary">
		<div class="panel-heading">
			<h3>
				<spring:message code="borrowHistory" />
			</h3>
		</div>
		<div class="panel-body">
			<table class="table table-striped">
				<thead>
					<tr>
						<th width="15%"><spring:message code="userFirstName" /></th>
						<th width="15%"><spring:message code="userLastName" /></th>
						<th width="30%"><spring:message code="title" /></th>
						<th width="20%"><spring:message code="dateBorrowOut" /></th>
						<th width="20%"><spring:message code="dateTurnBack" /></th>



					</tr>
				</thead>

				<c:if test="${showBorrowList}">
					<tbody>
						<c:forEach items="${borrowHistories}" var="borrowHistory">
							<tr>
								<td>${borrowHistory.user.firstName}</td>
								<td>${borrowHistory.user.lastName}</td>
								<td>${borrowHistory.book.title}</td>
								<td>${borrowHistory.dateBorrow}</td>
								<td>${borrowHistory.dateTurnBack}</td>
								<td></td>

							</tr>
						</c:forEach>
					</tbody>
				</c:if>

				<c:if test="${!showBorrowList}">
					<tbody>

						<tr>
							<td>${borrowHistory.user.firstName}</td>
							<td>${borrowHistory.user.lastName}</td>
							<td>${borrowHistory.book.title}</td>
							<td>${borrowHistory.dateBorrow}</td>
							<td>${borrowHistory.dateTurnBack}</td>
							<td></td>
						</tr>

					</tbody>
				</c:if>

			</table>



		</div>
	</div>

</div>
<%@ include file="common/footer.jspf"%>