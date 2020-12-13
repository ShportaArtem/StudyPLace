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
            <div class="card h-100">
              
              <div class="card-body">
                <h4 class="card-title" style="text-transform: capitalize;">
                Course: ${certificateView.getNameCourse()}
                </h4>
              </div>
                 <ul class="list-group list-group-flush">
    				<li class="list-group-item">User name: ${certificateView.getNameUser()}</li>
    				<li class="list-group-item">User surname: ${certificateView.getSurnameUser()}</li>
    				<li class="list-group-item">Mark: ${certificateView.getMark()}</li>
  				</ul>
            </div>
          	
          	<div class="form-group">
          	<br>
          		<button type="button" class="btn btn-outline-warning" onclick="javascript:history.back()">Back</button>
			</div>
         

          


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