<%@ include file="/view/jspf/page.jspf"%>
<%@ include file="/view/jspf/taglib.jspf"%>
<%@ include file="/view/jspf/head.jspf"%>
<body class="bg-secondary">
<%@ include file="/view/jspf/header2.jspf"%>
<div class="container-fluid">

<!-- Page Content -->
  <div class="container">

    <div class="row">
      <div class="col-lg-12">

        <div class="card mt-4">
          <div class="card-body">
            <h3 class="card-title" style="text-transform: capitalize; color:#27A8CF;">${sessionScope.taskNow.getName()}</h3>
		         	<h4>Description: ${sessionScope.taskNow.getDescription()}</h4>
		    <c:forEach var="question" items="${sessionScope.questions}">
				 <ul>
        			<li><p>"${question.getQuestion()}"</p></li>
         			<li><p>"${question.getDescription()}"</p></li>
         			<c:forEach var="answer" items="${sessionScope.answersForQuestions}">
         				<c:when test="${answer.getQuestionId().equals(question.getId())}">
         					<li><p><input type = "checkbox" name = "answer${sessionScope.count}" />  ${answer.getValue()}</p></li>
         			 	</c:when>
         			</c:forEach>
      			</ul>
			</c:forEach>
		    
		    <div class="mt-2">
		    <c:choose>
		        <c:when test="${sessionScope.thisCourse.getTeacherId().equals(sessionScope.thisUser.getId())}">
		         	<a href="/StudyPlace/Controller?command=addQuestion" class="btn btn-primary" type="submit" >Add question</a>
		        </c:when>
		         <c:otherwise>
		        	<a href="/StudyPlace/Controller?command=endTask" class="btn btn-primary" type="submit" > Finish task</a>
		        </c:otherwise>
		         	<a href="/StudyPlace/Controller?command=nextPublication" class="btn btn-primary" type="submit" > Next </a>
            </c:choose>
            </div>
		    </div>
          
        </div>
        <!-- /.card -->

      </div>
      <!-- /.col-lg-9 -->

    </div>

  </div>
  <!-- /.container -->

</div>
