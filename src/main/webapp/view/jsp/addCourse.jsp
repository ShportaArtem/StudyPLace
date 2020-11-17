<%@ include file="/view/jspf/page.jspf"%>
<%@ include file="/view/jspf/taglib.jspf"%>
<!doctype html>
<html lang="en">
<%@ include file="/view/jspf/head.jspf"%>
<body class="bg-secondary">
<%@ include file="/view/jspf/header2.jspf"%>

<div class="container mt-sm-5 " style="width: 30%;">
		<div class="jumbotron jumbotron-liquid pt-3">
			<form id="addcourse_form" action="Controller" method="post">
				<input type="hidden" name="command" value="addCourse" />
				<div class="form-group">
					<label for="description" style="color: #083A63;">Description</label> <input type="text"
						name="description" class="form-control"/> <small id="description"
						class="form-text text-muted" style="color: #083A63;">Describe your course.</small>
				</div>
				<div class="form-group">
					<label for="name" style="color: #083A63;">Name</label> <input type="text"
						name="name" class="form-control" /> <small id="name"
						class="form-text text-muted" style="color: #083A63;">Enter course name.</small>
				</div>

				<div class="form-group">
					<label for="exampleInputPassword1" style="color: #083A63;">Password</label> <input
						type="password" name="password" class="form-control"
						id="exampleInputPassword1" /> <small id="password"
						class="form-text text-muted" style="color: #083A63;">Enter the password for the course.</small>
				</div>
				
				<div class="form-group">
					<label for="picture" style="color: #083A63;">Picture</label> <div class="image-container">
                                    <img src="http://placehold.it/150x150" id="imgProfile" style="width: 150px; height: 150px" class="img-thumbnail" />
                                    
                                </div> <small id="pictureHelp"
						class="form-text text-muted" style="color: #083A63;">Enter course picture.</small>
				</div>


				<div class="form-group">
					<label for="price" style="color: #083A63;">Price</label> <input type="text"
						name="price" class="form-control" /> <small id="price"
						class="form-text text-muted" style="color: #083A63;">Enter course price.</small>
				</div>
				
				<div class="form-group">
   				<button type="submit" class="btn btn-outline-success"
					data-toggle="tooltip bg-dark" data-placement="bottom"
					>Add course</button>
			
				<button type="button" class="btn btn-outline-danger" onclick="javascript:history.back()">Back</button>
				</div>

				
			</form>
		</div>
	</div>


<%@ include file="/view/jspf/footer.jspf"%>
<%@ include file="/view/jspf/bodyScripts.jspf"%>
</body>
</html>