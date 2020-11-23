<%@ include file="/view/jspf/page.jspf"%>
<%@ include file="/view/jspf/taglib.jspf"%>
<!doctype html>
<html lang="en">
<%@ include file="/view/jspf/head.jspf"%>
<body class="bg-secondary">
<%@ include file="/view/jspf/header2.jspf"%>

<div class="container mt-sm-5 " style="width: 30%;">
		<div class="jumbotron jumbotron-liquid pt-3">
			<form id="updatecourse_form" action="Controller" method="post">
				<input type="hidden" name="command" value="updateCourse" />
				
				<div class="form-group">
					<label for="name" style="color: #083A63;">Name</label> 
					<input type="text" placeholder="${sessionScope.thisCourse.getName()}"
						name="name" class="form-control" value="${sessionScope.thisCourse.getName()}"/> 
						<small id="name"
						class="form-text text-muted" style="color: #083A63;">Enter course name.</small>
				</div>
				
				<div class="form-group">
					<label for="description" style="color: #083A63;">Description</label> 
					<input type="text" placeholder="${sessionScope.thisCourse.getDescription()}"
						name="description" class="form-control" value="${sessionScope.thisCourse.getDescription()}" /> 
						<small id="description"
						class="form-text text-muted" style="color: #083A63;">Describe your course.</small>
				</div>

				<c:choose>
	                <c:when test="${!sessionScope.thisCourse.getPrice().equals(0.0)}">
	                	<div class="form-group">
							<label for="price" style="color: #083A63;">Price</label> 
							<input type="text" placeholder=""
							name="price" class="form-control" value="${sessionScope.thisCourse.getPrice()}"/> 
							<small id="price"
							class="form-text text-muted" style="color: #083A63;">Enter course price.</small>
						</div>
	                </c:when> 
	                <c:otherwise>
		                <div class="form-group">
							<label for="price" style="color: #083A63;">Price</label> 
							<input type="text" placeholder="it`s free now "
							name="price" class="form-control" /> <small id="price"
							class="form-text text-muted" style="color: #083A63;">Enter course price.</small>
						</div>
	                </c:otherwise>
                </c:choose>
                                
				
				
				<div class="form-group">
   				<button type="submit" class="btn btn-outline-success"
					data-toggle="tooltip bg-dark" data-placement="bottom">Update course</button>
			
				<button type="button" class="btn btn-outline-danger" onclick="javascript:history.back()">Back</button>
				</div>

				
			</form>
		</div>
	</div>


<%@ include file="/view/jspf/footer.jspf"%>
<%@ include file="/view/jspf/bodyScripts.jspf"%>
</body>
</html>