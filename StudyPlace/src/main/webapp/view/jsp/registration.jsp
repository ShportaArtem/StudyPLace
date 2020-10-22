<%@ include file="/view/jspf/page.jspf"%>
<%@ include file="/view/jspf/taglib.jspf"%>
<!doctype html>
<html lang="en">
<%@ include file="/view/jspf/head2.jspf"%>
<body class="bg-secondary">

	<%@ include file="/view/jspf/header3.jspf"%>
	<div class="container mt-sm-5 " style="width: 30%;">
		<div class="jumbotron jumbotron-liquid pt-3" style="height: 608px;">
			<form id="login_form" action="Controller" method="post">
				<input type="hidden" name="command" value="registration" />
				<div class="form-group">
					<label for="login">Login</label> <input type="text"
						name="loginUser" class="form-control" id="exampleInputEmail1"
						aria-describedby="emailHelp" /> <small id="loginHelp"
						class="form-text text-muted">Enter your login.</small>
				</div>
				<div class="form-group">
					<label for="name">Name</label> <input type="text"
						name="nameUser" class="form-control" id="exampleInputEmail1"
						aria-describedby="nameHelp" /> <small id="nameHelp"
						class="form-text text-muted">Enter your name.</small>
				</div>
				<div class="form-group">
					<label for="surname">SurName</label> <input type="text"
						name="surnameUser" class="form-control" id="exampleInputEmail1"
						aria-describedby="surnameHelp" /> <small id="surnameHelp"
						class="form-text text-muted">Enter your surname.</small>
				</div>
				<div class="form-group">
					<label for="exampleInputPassword1">Password</label> <input
						type="password" name="passwordUser" class="form-control"
						id="exampleInputPassword1" /> <small id="PasswordHelp"
						class="form-text text-muted">Enter your password.</small>
				</div>
				<button type="submit" class="btn btn-outline-success "
					data-toggle="tooltip bg-dark" data-placement="bottom"
					title="Registration new account">Register</button>
			</form>
		</div>
	</div>

	<%@ include file="/view/jspf/footer.jspf"%>
	<%@ include file="/view/jspf/bodyScripts.jspf"%>

</body>

</html>