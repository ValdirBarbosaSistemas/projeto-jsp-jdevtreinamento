<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
	
<%@ taglib prefix="c" uri = "http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en">

<jsp:include page="head.jsp"></jsp:include>

<body>
	<!-- Pre-loader start -->
	<jsp:include page="theme-loader.jsp"></jsp:include>
	<!-- Pre-loader end -->
	<div id="pcoded" class="pcoded">
		<div class="pcoded-overlay-box"></div>
		<div class="pcoded-container navbar-wrapper">

			<jsp:include page="nav-bar.jsp"></jsp:include>

			<div class="pcoded-main-container">
				<div class="pcoded-wrapper">

					<jsp:include page="nav-bar-mainmenu.jsp"></jsp:include>

					<div class="pcoded-content">
						<!-- Page-header start -->
						<jsp:include page="page-header.jsp"></jsp:include>
						<!-- Page-header end -->
						<div class="pcoded-inner-content">
							<!-- Main-body start -->
							<div class="main-body">
								<div class="page-wrapper">
									<!-- Page-body start -->
									<div class="page-body">

										<div class="row">
											<div class="col-sm-12">
												<!-- Basic Form Inputs card start -->
												<div class="card">
													<div class="card-block">
														<h3 class="sub-title">Cadastro de Usuário</h3>

														<form class="form-material"
															action="<%=request.getContextPath()%>/ServletUsuarioController"
															method="post" id="formUser">

															<input type="hidden" name="acao" id="acao" value=''>

															<div class="form-group form-default">
																<input type="text" name="id" id="id" readonly="readonly"
																	class="form-control" value="${modelLogin.id}">
																<span class="form-bar"></span> <label
																	class="float-label">ID: </label>
															</div>

															<div class="form-group form-default">
																<input type="text" name="nome" id="nome"
																	value="${modelLogin.nome}" class="form-control"
																	required="required"> <span class="form-bar"></span>
																<label class="float-label">Nome: </label>
															</div>

															<div class="form-group form-default">
																<input type="email" name="email" id="email"
																	value="${modelLogin.email}" class="form-control"
																	required="required"> <span class="form-bar"></span>
																<label class="float-label">Email: </label>
															</div>

															<div class="form-group form-default">
																<input type="text" name="login" id="login"
																	class="form-control" value="${modelLogin.login}">
																<span class="form-bar"></span> <label
																	class="float-label">Login: </label>
															</div>

															<div class="form-group form-default">
																<input type="password" name="senha" id="senha"
																	value="${modelLogin.senha}" class="form-control"
																	required="required"> <span class="form-bar"></span>
																<label class="float-label">Senha: </label>
															</div>

															<button type="button"
																class="btn btn-primary btn-round waves-effect waves-light"
																onclick="limparForm();">Novo</button>
															<button
																class="btn btn-success btn-round waves-effect waves-light">Salvar</button>
															<button
																class="btn btn-warning btn-round waves-effect waves-light">Editar</button>

															<!-- Button trigger modal -->
															<button type="button" class="btn btn-secondary btn-round waves-effect waves-light" data-toggle="modal" data-target="#exampleModalUsuario">
																Pesquisar</button>

															<button type="button"
																class="btn btn-danger btn-round waves-effect waves-light"
																onclick="criarDeleteAjax();">Excluir</button>

														</form>

													</div>
												</div>
											</div>
										</div>

										<span id="msg">${msg}</span>

										<div style="heigth: 300px; overflow: scroll;">
											<table class="table table-hover" id="tabelaResultadoView">
												<thead>
													<tr>
														<th scope="col">ID</th>
														<th scope="col">Nome</th>
														<th scope="col">Visualizar</th>
													</tr>
												</thead>
												<tbody>
													<c:forEach items="${modelLogins}" var="ml">
														<tr>
															<td><c:out value="${ml.id}"> </c:out></td>
															<td><c:out value="${ml.nome}"> </c:out></td>
															<td><a class="btn btn success" href="<%=request.getContextPath()%>/ServletUsuarioController?acao=buscarEditar&id=${ml.id}">Ver</a></td>
														</tr>
													</c:forEach>
												</tbody>
											</table>
										</div>
										
									</div>
									<!-- Page-body end -->
								</div>
								<div id="styleSelector"></div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<!-- Warning Section Starts -->
	<!-- Older IE warning message -->
	<!--[if lt IE 10]>
    <div class="ie-warning">
        <h1>Warning!!</h1>
        <p>You are using an outdated version of Internet Explorer, please upgrade <br/>to any of the following web browsers to access this website.</p>
        <div class="iew-container">
            <ul class="iew-download">
                <li>
                    <a href="http://www.google.com/chrome/">
                        <img src="assets/images/browser/chrome.png" alt="Chrome">
                        <div>Chrome</div>
                    </a>
                </li>
                <li>
                    <a href="https://www.mozilla.org/en-US/firefox/new/">
                        <img src="assets/images/browser/firefox.png" alt="Firefox">
                        <div>Firefox</div>
                    </a>
                </li>
                <li>
                    <a href="http://www.opera.com">
                        <img src="assets/images/browser/opera.png" alt="Opera">
                        <div>Opera</div>
                    </a>
                </li>
                <li>
                    <a href="https://www.apple.com/safari/">
                        <img src="assets/images/browser/safari.png" alt="Safari">
                        <div>Safari</div>
                    </a>
                </li>
                <li>
                    <a href="http://windows.microsoft.com/en-us/internet-explorer/download-ie">
                        <img src="assets/images/browser/ie.png" alt="">
                        <div>IE (9 & above)</div>
                    </a>
                </li>
            </ul>
        </div>
        <p>Sorry for the inconvenience!</p>
    </div>
    <![endif]-->
	<!-- Warning Section Ends -->

	<!-- Required Jquery -->
	<jsp:include page="javascriptfile.jsp"></jsp:include>


	<!-- Modal -->
	<div class="modal fade" id="exampleModalUsuario" tabindex="-1" role="dialog"
		aria-labelledby="exampleModalLabel" aria-hidden="true">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<h5 class="modal-title" id="exampleModalLabel">Pesquisa de Usuário</h5>
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
				</div>
				<div class="modal-body">
					<div class="input-group mb-3">
						<input type="text" class="form-control"
							placeholder="Nome do Usuário"
							aria-label="nome" aria-describedby="basic-addon2" id="nomeBusca">
						<div class="input-group-append">
							<button class="btn btn-success" type="button" onclick="buscarNome();">Buscar</button>
						</div>
					</div>

					<div style="heigth: 300px; overflow: scroll;">
					<table class="table table-hover" id = "tabelaResultado">
						<thead>
							<tr>
								<th scope="col">ID</th>
								<th scope="col">Nome</th>
								<th scope="col">Visualizar</th>
							</tr>
						</thead>
						<tbody>
							<!-- Codigo JS -->
						</tbody>
					</table>
					</div>
					
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-secondary"
						data-dismiss="modal">Close</button>
				</div>
			</div>
		</div>
	</div>


	<script type="text/javascript">
		function limparForm() {

			var elementos = document.getElementById("formUser").elements; //retorna os elementos html dentro do form 

			for (p = 0; p < elementos.length; p++) {
				elementos[p].value = '';
			}
		}

		function criarDelete() {

			if (confirm("Deseja realmente excluir?")) {

				document.getElementById("formUser").method = 'GET';
				document.getElementById("acao").value = "delete"
				document.getElementById("formUser").submit();
			}
		}

		function criarDeleteAjax() {
			if (confirm('deseja realmente excluir os dados?')) {
				//precisamos pegar o 'action' do formulario para que possa ser utilizado atraves do SERVLET
				var urlAction = document.getElementById('formUser').action;
				var idUser = document.getElementById('id').value;

				$.ajax({
					method : "get",//metodo que sera resolvido na servlet
					url : urlAction,//url vindo do formulario (action)
					data : "id=" + idUser + '&acao=deletarAjax',//recebendo os dados do formulario
					success : function(response) {
						limparForm();
						document.getElementById('msg').textContent = response;
						//alert(response);//resposta de sucesso
					}
				}).fail(
						function(xhr, status, errorThrown) {//caso der erro
							alert('Erro ao deletar o usuario por id!'
									+ xhr.responseText);
						});
			}
		}
		
		function buscarNome() {
			var nomeBusca = document.getElementById('nomeBusca').value;
			
 			if(nomeBusca != null && nomeBusca != '' && nomeBusca.trim() != ''){
				var urlAction = document.getElementById('formUser').action;
				
				$.ajax({
					method : "get",//metodo que sera resolvido na servlet
					url : urlAction,//url vindo do formulario (action)
					data : "nomeBusca=" + nomeBusca + '&acao=buscarUserAjax',//recebendo os dados do formulario
					success : function(response) {
						var json = JSON.parse(response);
						
						$('#tabelaResultado > tbody > tr').remove();//Aqui ele vai acessar o que tiver dentro da tabela e remover
							for (var p = 0; p < json.length; p++){
								$('#tabelaResultado > tbody').append('<tr> <td>' + json[p].id + '</td>' + '<td>'+ json[p].nome +'</td> <td> <button type="button" class="btn btn-outline-success" onclick = "editarUsuario('+json[p].id+');">Editar</button> </td></tr>');
							}
					}
				}).fail(
						function(xhr, status, errorThrown) {//caso der erro
							alert('Erro ao buscar o usuario!' + xhr.responseText);
						});
			}
		}
		
		function editarUsuario(id){
			
			var urlAction = document.getElementById('formUser').action;
			
			window.location.href = urlAction + '?acao=buscarEditar&id=' + id;//redirecionamento atraves do javascript
		}
	</script>
</body>

</html>
