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
	        	<h1 class="my-4 text-center" >${sessionScope.thisCourse.getName()}</h1>
	        </div>
        </div>
        <a href="/StudyPlace/Controller?command=getAddPublication&courseId=${sessionScope.thisCourse.getId()}" 
		         	class="btn btn-primary" type="submit" >Add Publication</a><br><br>

        <!-- Blog Post -->
        <c:forEach var="publicationNow" items="${sessionScope.publicationsNow}">
        
        <div class="card mb-4">
          <div class="card-body">
            <h2 class="card-title">${publicationNow.getName()}</h2>
            <a href="/StudyPlace/Controller?command=getPublication&coursePosition=${publicationNow.getPosition()}" 
		         	class="btn btn-primary" type="submit" >View Publication</a>
          </div>
          <div class="card-footer text-muted">
            Posted ${publicationNow.getDateTime()} 
          </div>
        </div>
        
        </c:forEach>
		
    

      </div>



    </div>
    <!-- /.row -->

  </div>
  <!-- /.container -->