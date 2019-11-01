<%@ include file="common/header.jspf"%>
<%@ include file="common/navigation.jspf"%>

<div class="container">
	<div>
		<c:if test="${!searchModus}">
			<a type="button" class="btn btn-primary btn-md" href="/add-book"><spring:message
					code="addBook" /></a>
		</c:if>

	</div>
	<br>
	<div class="panel panel-primary">
		<div class="panel-heading">
			<h3>
				<spring:message code="listOfBooks" />
			</h3>
		</div>
		<div class="panel-body">
			<table class="table table-striped">
				<thead>
					<tr>
						<th width="25%"><spring:message code="title" /></th>
						<th width="20%"><spring:message code="author" /></th>
						<th width="20%"><spring:message code="type" /></th>
						<th width="20%"><spring:message code="status" /></th>
						<th width="15%"></th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${books}" var="book">
						<tr>
							<td>${book.title}</td>
							<td>${book.author}</td>
							<td>${book.type}</td>
							<td>${book.status}</td>
							<td><c:if test="${!searchModus}">
									<a type="button" class="btn btn-success"
										href="/update_book?id=${book.id}"> <spring:message
											code="update" />
									</a>
									<a type="button" class="btn btn-warning"
										href="/delete_book?id=${book.id}"> <spring:message
											code="delete" />
									</a>
								</c:if> <c:if test="${searchModus}">
									<a type="button" class="btn btn-success"
										href="/borrow?id=${book.id}"> <spring:message
											code="borrow" />
									</a>
									<a type="button" class="btn btn-success"
										href="/show_BookHistory?id=${book.id}"> <spring:message
											code="history" />
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