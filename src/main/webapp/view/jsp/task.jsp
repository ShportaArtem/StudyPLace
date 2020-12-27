<%@ include file="/view/jspf/page.jspf"%>
<%@ include file="/view/jspf/taglib.jspf"%>
<%@ include file="/view/jspf/head.jspf"%>
<body class="bg-secondary">
<%@ include file="/view/jspf/header2.jspf"%>
<div class="container-fluid">

<!-- Page Content -->

  <div class="container">
	
    <div class="row">
    	<div class="col">
    	<br>
    	<a href="/StudyPlace/Controller?command=getCourse&courseId=${sessionScope.thisCourse.getId()}"
					class="btn btn-primary" type="submit">Back to course</a><br>
    	</div>
      <div class="col-lg-12">

        <div class="card mt-4">
          <div class="card-body">
          
          	<c:choose>
          		<c:when test="${sessionScope.mark < 0}">
		          	<c:choose>
				        <c:when test="${!sessionScope.thisCourse.getTeacherId().equals(sessionScope.thisUser.getId())}">
				         	<form action="Controller" method="post">
				         		<input type="hidden" name="command" value="endTask" />
				        </c:when>
		            </c:choose>
		            <h3 class="card-title" style="text-transform: capitalize; color:#27A8CF;">${sessionScope.taskNow.getName()}</h3>
		        	<c:choose>
		      			<c:when test="${!sessionScope.taskNow.getDescription().equals(sessionScope.emptyString)}">
		      				
		      			</c:when>
		      		</c:choose>
		        	
		        	<c:choose>
		      			<c:when test="${!sessionScope.taskNow.getDescription().equals(sessionScope.emptyString)}">
		      				<h4>Description: ${sessionScope.taskNow.getDescription()}</h4>
		      			</c:when>
		      		</c:choose>
				    <c:forEach var="question" items="${sessionScope.questions}">
						 <div class="card st-8 bg-info" style="background-color:white!important;">
						 <ul>
		        			<h3>${question.getQuestion()}</h3>
		         			<h5 style="color:#777777">${question.getDescription()}</h5>
		         			<c:forEach var="answer" items="${sessionScope.answersForQuestions}">
		         				<c:choose>
		         				<c:when test="${answer.getQuestionId().equals(question.getId())}">
		         					<li><p><input type = "checkbox" name="answerChB" value="${answer.getId()}"/>${answer.getValue()}</p></li>
		         			 	</c:when>
		         			 	</c:choose>
		         			</c:forEach>
		      			</ul>
		      			</div>
		      			<br>
					</c:forEach>
				    
				    <div class="mt-2">
				    <c:choose>
				        <c:when test="${sessionScope.thisCourse.getTeacherId().equals(sessionScope.thisUser.getId())}">
				         	<a href="/StudyPlace/Controller?command=openAddQuestion" class="btn btn-primary" type="submit" >Add question for this task</a>
				        </c:when>
				         <c:otherwise>
					        <button class="btn btn-primary" type="submit" >Finish task</button>
				        </c:otherwise>
		            </c:choose>
		            <c:choose>
						<c:when test="${sessionScope.hasNext.equals(true)}">
							<a href="/StudyPlace/Controller?command=getPublication&coursePosition=${publicationNow.getPosition()+1}"
								class="btn btn-primary" type="submit">Next publication</a>
						</c:when>
					</c:choose>
		            </div>
		            
		            <c:choose>
				        <c:when test="${!sessionScope.thisCourse.getTeacherId().equals(sessionScope.thisUser.getId())}">
				         	</form>
				        </c:when>
		            </c:choose>
            	</c:when>
            	<c:otherwise>
            		<h3 class="card-title" style="text-transform: capitalize; color:#27A8CF;">${sessionScope.taskNow.getName()}</h3>
            		<div style="display:block; width:100%; padding-top: 100px; padding-bottom:100px;">
            			<p style="font-size:24px; text-align: center;">Your mark: ${sessionScope.mark}/${sessionScope.maxMark}</p>
            		</div>
            		<c:choose>
						<c:when test="${sessionScope.hasNext.equals(true)}">
							<a href="/StudyPlace/Controller?command=getPublication&coursePosition=${publicationNow.getPosition()+1}"
								class="btn btn-primary" type="submit">Next publication</a>
						</c:when>
					</c:choose>
            	</c:otherwise>
            </c:choose>
            
		    </div>
          
        </div>
        <!-- /.card -->

      </div>
      <!-- /.col-lg-9 -->

    </div>

  </div>
  <!-- /.container -->

</div>