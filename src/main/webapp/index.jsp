<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="ISO-8859-1">
<meta name="viewport" content="width=device-width, initial-scale=1">

<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-4bw+/aepP/YC94hEpVNVgiZdgIC5+VKNBQNGCHeKRQN+PtmoHDEXuppvnDJzQIu9"
	crossorigin="anonymous">


<style type="text/css">
form {
	position: absolute;
	top: 45%;
	left: 35%;
}

h1 {
	position: absolute;
	top: 30%;
	left: 38%;
}
</style>
<title>Projeto JSP JDEV Treinamentos</title>
</head>
<body>
	<h1>Bem vindo ao curso de JSP</h1>

	<!-- ENVIO DOS DADOS PARA OUTRA PAGINA ATRAVES DO FORMULARIO-->
	<form action="<%=request.getContextPath()%>/ServletLogin" method="post" class="row g-3 needs-validation" novalidate>
		<input type="hidden" value="<%=request.getParameter("url")%>"
			name="url">
		<!-- Quando colocamos o sinal de '=' dentro do value, ele ira ser impresso o valor na tela HTML -->

		<!-- <label>Login: </label> <input name="login" type="text"> -->
		<!-- PARAMETROS QUE SERAO ENVIADOS PARA O JSP -->
		<!-- <label> <br> <br> <br> Senha:
		</label> <input name="senha" type="password"> <input type="submit"
			value="Enviar"> -->

		<div class="col-md-6">
			<label class="form-label">Login</label> <input type="text" name = "login" 
			required="required" class="form-control" id="inputLogin">
			<div class ="invalid-feedback">Campo Obrigatório</div>
			<div class ="valid-feedback">OK</div>
		</div>
		<div class="col-md-6">
			<label class="form-label">Senha</label> <input type="password" name = "senha" 
			required="required"	class="form-control" id="inputPassword">
			<div class ="invalid-feedback">Campo Obrigatório</div>
			<div class ="valid-feedback">OK</div>
		</div>
		<div class="col-12">
			<button type="submit" class="btn btn-primary">Enviar</button>
		</div>

	</form>

	<h2>${msg}</h2>

		<script
			src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/js/bootstrap.bundle.min.js"
			integrity="sha384-HwwvtgBNo3bZJJLYd8oVXjrBZt8cqVSpeBNS5n7C8IVInixGAoxmnlMuBnhbgrkm"
			crossorigin="anonymous">
		</script>
		
		<script type="text/javascript">
		
		// Example starter JavaScript for disabling form submissions if there are invalid fields
		(() => {
		  'use strict'

		  // Fetch all the forms we want to apply custom Bootstrap validation styles to
		  const forms = document.querySelectorAll('.needs-validation')

		  // Loop over them and prevent submission
		  Array.from(forms).forEach(form => {
		    form.addEventListener('submit', event => {
		      if (!form.checkValidity()) {
		        event.preventDefault()
		        event.stopPropagation()
		      }

		      form.classList.add('was-validated')
		    }, false)
		  })
		})()
		
		</script>
</body>
</html>