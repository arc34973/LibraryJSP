<%@ include file="common/header.jspf"%>
<%@ include file="common/navigation.jspf"%>

<div class="container">
	<c:if test="${isAdmin}">
		<div>
			<a type="button" class="btn btn-primary btn-md" href="/search-user"><spring:message
					code="searchUser" /></a>

		</div>
	</c:if>
	<br>
	<div class="panel panel-primary">
		<div class="panel-heading">
			<h3>
				<spring:message code="searchBookByTitle" />
			</h3>
		</div>

		<spring:url value="/list-search.html" var="formUrl" />
		<form:form modelAttribute="book" method="post" class="form-horizontal">

			<div class="form-group">
				<div class="control-group" id="title">
					<label class="col-sm-2 control-label"><spring:message
							code="title" /></label>
					<div class="col-sm-10">
						<form:input class="form-control" path="title" size="30"
							maxlength="80" />
						<span class="help-inline"><form:errors path="*" /></span>
					</div>
				</div>
			</div>
			<div class="form-group">
				<div class="col-sm-offset-2 col-sm-10">
					<button type="submit" class="btn btn-default">
						<spring:message code="search" />
					</button>
				</div>
			</div>

		</form:form>

	</div>
</div>
<%@ include file="common/footer.jspf"%>