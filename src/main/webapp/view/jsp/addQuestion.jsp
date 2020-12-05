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
				<input type="hidden" name="command" value="addQuestion" />
				
				<div class="form-group">
					<label for="question" style="color: #083A63;">Question</label> <input type="text"
						name="question" class="form-control" /> <small id="question"
						class="form-text text-muted" style="color: #083A63;">Enter question.</small>
				</div>
				
				<div class="form-group">
					<label for="description" style="color: #083A63;">Description</label> <input type="text"
						name="description" class="form-control"/> <small id="description"
						class="form-text text-muted" style="color: #083A63;">Describe your task.</small>
				</div>
				
				<div class="form-group">
					<label for="mark" style="color: #083A63;">Mark</label> <input type="text"
						name="mark" class="form-control"/> <small id="mark"
						class="form-text text-muted" style="color: #083A63;">Enter mark.</small>
				</div>
				
				<div class="form-group">
					<label for="numberOfCorrectAnswers" style="color: #083A63;">How much correct answers?</label>
					<select class="form-control" id="numberOfCorrectAnswers" name="numberOfCorrectAnswers">
     					 <option>One</option>
      					<option>Two-four</option>
				    </select>
					 <small id="numberOfCorrectAnswers" class="form-text text-muted" style="color: #083A63;">How much correct .</small>
				</div>
				
				<div class="form-group">
					<label for="value1" style="color: #083A63;">Answer - 1</label> <input type="text"
						name="value1" class="form-control"/> <small id="value1"
						class="form-text text-muted" style="color: #083A63;">Enter answer.</small>
				</div>
				
				<div class="form-group">
					<label for="correct1" style="color: #083A63;">Is correct?</label>
					<select class="form-control" id="correct1" name="correct1">
     					 <option>Correct</option>
      					<option>Uncorrect</option>
				    </select>
				</div>
				
				<div class="form-group">
					<label for="value2" style="color: #083A63;">Answer - 2</label> <input type="text"
						name="value2" class="form-control"/> <small id="value2"
						class="form-text text-muted" style="color: #083A63;">Enter answer.</small>
				</div>
				
				<div class="form-group">
					<label for="correct2" style="color: #083A63;">Is correct?</label>
					<select class="form-control" id="correct2" name="correct2">
     					 <option>Correct</option>
      					<option>Uncorrect</option>
				    </select>
				</div>
				
				<div class="form-group">
					<label for="value3" style="color: #083A63;">Answer - 3</label> <input type="text"
						name="value3" class="form-control"/> <small id="value3"
						class="form-text text-muted" style="color: #083A63;">Enter answer.</small>
				</div>
				
				<div class="form-group">
					<label for="correct3" style="color: #083A63;">Is correct?</label>
					<select class="form-control" id="correct3" name="correct3">
     					 <option>Correct</option>
      					<option>Uncorrect</option>
				    </select>
				</div>
				
				<div class="form-group">
					<label for="value4" style="color: #083A63;">Answer - 4</label> <input type="text"
						name="value4" class="form-control"/> <small id="value4"
						class="form-text text-muted" style="color: #083A63;">Enter answer.</small>
				</div>
				
				<div class="form-group">
					<label for="correct4" style="color: #083A63;">Is correct?</label>
					<select class="form-control" id="correct4" name="correct4">
     					 <option>Correct</option>
      					<option>Uncorrect</option>
				    </select>
				</div>
				
				<div class="form-group">
   				<button type="submit" class="btn btn-outline-success"
					data-toggle="tooltip bg-dark" data-placement="bottom"
					>Add question</button>
			
				<button type="button" class="btn btn-outline-danger" onclick="javascript:history.back()">Cancel</button>
				</div>
				
				
			</form>
		</div>
	</div>


<%@ include file="/view/jspf/footer.jspf"%>
<%@ include file="/view/jspf/bodyScripts.jspf"%>
</body>
</html>