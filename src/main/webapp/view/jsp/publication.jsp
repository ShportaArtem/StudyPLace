<%@ include file="/view/jspf/page.jspf"%>
<%@ include file="/view/jspf/taglib.jspf"%>
<!doctype html>
<html lang="en">
<%@ include file="/view/jspf/head.jspf"%>
<body class="bg-secondary">
	<%@ include file="/view/jspf/header2.jspf"%>
	<div class="container">

		<div class="row">

			<!-- Blog Entries Column -->
			<div class="col-md-12">

				<div class="card mb-4">
					<div class="card-body">
						<h1 class="my-4 text-center">${sessionScope.thisCourse.getName()}</h1>
					</div>
				</div>
				<a
					href="/StudyPlace/Controller?command=getCourse&courseId=${sessionScope.thisCourse.getId()}"
					class="btn btn-primary" type="submit">Back to course</a><br>
				<br>
				<!-- Blog Post -->

				<c:choose>
					<c:when test="${sessionScope.hasMaterial.equals(true)}">
						<div class="card mb-4">
							<div class="card-body">
								<h2 class="card-title">${sessionScope.publicationNow.getName()}</h2>
								<p class="card-text">${sessionScope.publicationNow.getDescription()}</p>
								
								<c:choose>
									<c:when test="${sessionScope.hasPrevious.equals(true)}">
										<a href="/StudyPlace/Controller?command=getPublication&coursePosition=${publicationNow.getPosition()-1}"
											class="btn btn-primary" type="submit">Previous publication</a>
									</c:when>
								</c:choose>
								
								<c:choose>
									<c:when test="${sessionScope.hasNextTask.equals(true)}">
										<a href="/StudyPlace/Controller?command=getTask"
											class="btn btn-primary" type="submit">Task for this publication</a>
									</c:when>
									<c:when test="${sessionScope.hasNext.equals(true)}">
											<a href="/StudyPlace/Controller?command=getPublication&coursePosition=${publicationNow.getPosition()+1}"
													class="btn btn-primary" type="submit">Next Publication</a>
									</c:when>
								</c:choose>
								
								<c:choose>
									<c:when test="${sessionScope.thisCourse.getTeacherId().equals(sessionScope.user.getId())}">
										<c:choose>
											<c:when test="${sessionScope.hasNext.equals(false)}">
											<a href="/StudyPlace/Controller?command=getAddPublication&courseId=${sessionScope.thisCourse.getId()}"
												class="btn btn-primary" type="submit">Add new publication</a>
											</c:when>
										</c:choose>
										
										<c:choose>
											<c:when test="${sessionScope.hasNextTask.equals(false)}">
												<a href="/StudyPlace/Controller?command=openAddTask"
													class="btn btn-primary" type="submit">Add task for this publication</a>
											</c:when>
		         						</c:choose>
									</c:when>
									
									<c:otherwise>
										<a href="/StudyPlace/Controller?command=getCourse"
											class="btn btn-primary" type="submit">Finish </a>
									</c:otherwise>
								</c:choose>


							</div>
							<div class="card-footer text-muted">Posted
								${sessionScope.publicationNow.getDateTime()}</div>
						</div>
					</c:when>
					<c:otherwise>
				        No material
				    </c:otherwise>
				</c:choose>





			</div>



		</div>
		<!-- /.row -->

	</div>
	<!-- /.container -->