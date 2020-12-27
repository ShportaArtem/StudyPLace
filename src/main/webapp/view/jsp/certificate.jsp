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
              
              <div class="card-body" style="background-color: #F5EFE5">
              	<h1 style="text-align:center; font-size: 64px; color: #083A63">Certificate</h1>
                <h4 class="card-title" style="text-transform: capitalize; text-align:center; margin-bottom: 50px; color: #14729D">
                Course: ${certificateView.getNameCourse()}
                </h4>
                <h3 class="card-title" style="text-align:center; margin-bottom: 50px; color: #083A63">
                ${certificateView.getNameUser()} ${certificateView.getSurnameUser()} ended this course
                </h3>
                <h4 class="card-title" style="text-align:center; color: #083A63">
                And got an ${certificateView.getMark()} out of 100 points.
                </h4>
              </div>
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