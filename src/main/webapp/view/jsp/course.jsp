<%@ include file="/view/jspf/page.jspf"%>
<%@ include file="/view/jspf/taglib.jspf"%>
<!doctype html>
<html lang="en">
<style>
button.btn-info{
background:#27A8CF;
}
button.btn-info:hover{
background:#14729D;
}
a.btn{
background:#27A8CF;
}
a.btn:hover{
background:#14729D;
}
</style>
<%@ include file="/view/jspf/head.jspf"%>
<body class="bg-secondary">
<%@ include file="/view/jspf/header2.jspf"%>

<div class="container-fluid">

<!-- Page Content -->
  <div class="container">

    <div class="row">
      <div class="col-lg-12">

        <div class="card mt-4">
          <img class="card-img-top " width="100%" src="http://placehold.it/900x400" alt="">
          <div class="card-body">
            <h3 class="card-title" style="text-transform: capitalize; color:#27A8CF;">${sessionScope.thisCourse.getName()}</h3>
            <input type="hidden" name="commentId" value="${sessionScope.thisCourse.getId()}" />
            
            <c:choose>
		        <c:when test="${sessionScope.thisCourse.getPrice().equals(0.0)}">
		         	<h4>It`s free </h4>
		        </c:when> 
		        <c:otherwise>
		        	<h4>${sessionScope.thisCourse.getPrice()}$</h4>
		        </c:otherwise>
            </c:choose>
            <h6>Teacher: ${sessionScope.thisCourse.getTeacherName()}</h6>
	            
            <p class="card-text">${sessionScope.thisCourse.getDescription()}</p>
		    <span class="text-warning">number of subscriptions: ${sessionScope.thisCourse.getCountFollowers()}</span>
		    <div class="mt-2">
		    <c:choose>
		        <c:when test="${sessionScope.thisCourse.getTeacherId().equals(sessionScope.thisUser.getId())}">
		         	<a href="/StudyPlace/Controller?command=getUpdateCourse&courseId=${sessionScope.thisCourse.getId()}" 
		         	class="btn btn-primary" type="submit" >Edit a course</a>
		         	<a href="/StudyPlace/Controller?command=getPublications&courseId=${sessionScope.thisCourse.getId()}" 
		         	class="btn btn-primary" type="submit" >View Publications</a>
		        </c:when> 
		        <c:when test="${sessionScope.subscribed.equals(true)}">
		         	<a href="" class="btn btn-primary" type="submit" > unsubscribe </a>
		         	<a href="/StudyPlace/Controller?command=getPublication&coursePosition=1" 
		         	class="btn btn-primary" type="submit" >Start a course</a>
		        </c:when>
		        <c:otherwise>
		        	<a href="/StudyPlace/Controller?command=subscribeCourse&courseId=${sessionScope.thisCourse.getId()}" class="btn btn-primary" type="submit" > subscribe </a>
		        </c:otherwise>
            </c:choose>
		    	
            </div>
		    </div>
          
        </div>
        <!-- /.card -->

        <div class="card card-outline-secondary my-4">
          <div class="card-header">
          Comments
          </div>
          
          <div class="card-body">
          <c:forEach var="comment" items="${sessionScope.thisComments}">
            <p>${comment.getText()}</p>
            <small class="text-muted">Posted by ${comment.getUserName()} on ${comment.getDateTime()}</small>
            <hr>
          </c:forEach>
            
            <form id="leaveComment_form" action="Controller" method="post">
            <input type="hidden" name="command" value="leaveComment" />
            <input type="hidden" name="courseId" value="${sessionScope.thisCourse.getId()}" />
            
            <textarea id="text" placeholder="write your comment"  name="text" style="color: #083A6; rows:2;" class="form-control here" ></textarea>
            <br>
            <div >
          		   <button type="submit" class="btn btn-info mb-2" style="color: #F5EFE5; background-color: #27A8CF;">Leave Comment</button>
          	</div>
          	</form>
          	
          </div>
          
          
        </div>
        		
        
        <!-- /.card -->
      </div>
      <!-- /.col-lg-9 -->

    </div>

  </div>
  <!-- /.container -->

</div>



</body>
</html>