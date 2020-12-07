<%@ include file="/view/jspf/page.jspf"%>
<%@ include file="/view/jspf/taglib.jspf"%>
<!doctype html>
<html lang="en">
<style>
a.btn{
background:#27A8CF;
}
a.btn:hover{
background:#14729D;
}
#add::placeholder {
  color: black;
  }
</style>
<%@ include file="/view/jspf/head.jspf"%>
<body class="bg-secondary">
<%@ include file="/view/jspf/header2.jspf"%>
<div class="container-fluid">


 <!-- Page Content -->
  <div class="container"><br>
  

        <!-- Search Widget -->
        <div class="row">
        	<div class="col-lg-4">
	        	<div class="card mx-0 mb-4">
	        	<h5 class="card-header">Become a teacher</h5>
		        	<div class="card-body">
		        		<div class="input-group">
		        			<input disabled readonly type="text" id="add" class="form-control" placeholder="You can add your own course">
		        			<span class="input-group-append">
			        			<a href="/StudyPlace/Controller?command=openAddCourse" class="btn btn-primary" type="submit" >add</a>
			        		</span>
			        	</div>
		        	</div>
	        	</div>
        	</div>
        
	        <div class="col-lg-8">
		        <div class="card mx-0 mb-4">
		          <h5 class="card-header">Search</h5>
		          <div class="card-body">
		            <div class="input-group">
		              <input name = "search" type="text" class="form-control" placeholder="Search for...">
		              <span class="input-group-append">
		   				<a href="" class="btn btn-primary">Search</a>
		              </span>
		            </div>
		          </div>
		        </div>
		    </div>
		    
        </div>



    <div class="row">
      <div class="col-lg-12">
        <div class="row">

		<c:forEach var="course" items="${sessionScope.courses}">
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
    <!-- /.row -->

  </div>
  <!-- /.container -->







</div>
<%@ include file="/view/jspf/footer.jspf"%>
<%@ include file="/view/jspf/bodyScripts.jspf"%>
</body>
</html>