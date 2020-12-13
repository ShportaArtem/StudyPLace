
<%@ include file="/view/jspf/page.jspf"%>
<%@ include file="/view/jspf/taglib.jspf"%>
<!doctype html>
<html lang="en">

<%@ include file="/view/jspf/head.jspf"%>
<body class="bg-secondary">
<%@ include file="/view/jspf/header2.jspf"%>
<div class="container-fluid">
 <div class="container"><br>
<div class="row">
      <div class="col-lg-12">
        <div class="row">

		<c:forEach var="course" items="${sessionScope.coursesByUser}">
          <div class="col-lg-4 col-md-6 mb-4">
            <div class="card h-100">
              
              <c:choose>
                  <c:when test="${course.getPicture() != null}">
                  	<a href="/StudyPlace/Controller?command=getCourse&courseId=${course.getId()}"><img class="card-img-top" width="700" height="auto" src="data:image/jpg;base64,${course.getPicture64()}" alt=""></a>
                  </c:when>
                  <c:otherwise>
                  	
                  </c:otherwise>
              </c:choose>
              
              <div class="card-body">
                <h4 class="card-title" style="text-transform: capitalize;">
                  <a href="/StudyPlace/Controller?command=getCourse&courseId=${course.getId()}" style="color:#27A8CF;" >${course.getName()}</a>
                </h4>
                <c:choose>
			        <c:when test="${course.getPrice().equals(0.0)}">
			         	<h5>It`s free </h5>
			        </c:when> 
			        <c:otherwise>
			        	<h5>${course.getPrice()}$</h5>
			        </c:otherwise>
            	</c:choose>
                <h6>Teacher: ${course.getTeacherName()}</h6>
                <p class="card-text h-25" >${course.getShortDescription()}...</p>
                <small class="text-muted " >number of subscriptions: ${course.getCountFollowers()}</small>
              </div>
                
              <div class="card-footer text-center" >
              	<a href="/StudyPlace/Controller?command=getCourse&courseId=${course.getId()}" class="btn btn-primary">Read More</a>
              </div>
            </div>
          </div>
          </c:forEach>
          
         

          

        </div>
        <!-- /.row -->

      </div>
      <!-- /.col-lg-9 -->

    </div>
    </div>
  <!-- /.container -->

</div>
<%@ include file="/view/jspf/footer.jspf"%>
<%@ include file="/view/jspf/bodyScripts.jspf"%>
</body>
</html>