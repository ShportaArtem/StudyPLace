<%@ include file="/view/jspf/page.jspf"%>
<%@ include file="/view/jspf/taglib.jspf"%>
<!doctype html>
<html lang="en">
<%@ include file="/view/jspf/head.jspf"%>
<body class="bg-secondary">
<%@ include file="/view/jspf/header2.jspf"%>

<div class="container mt-sm-5 " style="width: 30%;">
		<div class="jumbotron jumbotron-liquid pt-3">
			<form id="addtask_form" action="Controller" method="post">
				<input type="hidden" name="command" value="addTask" />
				
				<div class="form-group">
					<label for="name" style="color: #083A63;">Name</label> <input type="text"
						name="name" class="form-control" /> <small id="name"
						class="form-text text-muted" style="color: #083A63;">Enter task name.</small>
				</div>
				
				<div class="form-group">
					<label for="description" style="color: #083A63;">Description</label> <input type="text"
						name="description" class="form-control"/> <small id="description"
						class="form-text text-muted" style="color: #083A63;">Describe your task.</small>
				</div>
				
				<div class="form-group">
   				<button type="submit" class="btn btn-outline-success"
					data-toggle="tooltip bg-dark" data-placement="bottom"
					>Add task</button>
			
				<button type="button" class="btn btn-outline-danger" onclick="javascript:history.back()">Cancel</button>
				</div>

				
			</form>
		</div>
	</div>


<%@ include file="/view/jspf/footer.jspf"%>
<%@ include file="/view/jspf/bodyScripts.jspf"%>
</body>
</html>