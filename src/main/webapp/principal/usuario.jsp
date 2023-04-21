<%@page import="model.ModelLogin"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

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

			<jsp:include page="navbar.jsp"></jsp:include>

			<div class="pcoded-main-container">
				<div class="pcoded-wrapper">

					<jsp:include page="navbarmainmenu.jsp"></jsp:include>

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
														<h4 class="sub-title">Cadastro de usuário</h4>

														<form class="form-material" enctype="multipart/form-data"
															action="<%=request.getContextPath()%>/ServletUsuarioController"
															method="post" id="formUser">

															<input type="hidden" name="acao" id="acao" value="">

															<div class="form-group form-default form-static-label">
																<input type="text" name="id" id="id"
																	class="form-control" readonly="readonly"
																	value="${modelLogin.id}"> <span
																	class="form-bar"></span> <label class="float-label">ID:</label>
															</div>

															<div class="form-group form-default input-group mb-4">
																<div class="input-group-prepend">
																	<c:if
																		test="${modelLogin.fotousuario != '' && modelLogin.fotousuario != null}">
																		<a
																			href="<%= request.getContextPath()%>/ServletUsuarioController?acao=downloadFoto&id=${modelLogin.id}">
																			<img alt="Imagem User" id="fotobase64"
																			src="${modelLogin.fotousuario}" width="70px">
																		</a>
																	</c:if>
																	<c:if
																		test="${modelLogin.fotousuario == '' || modelLogin.fotousuario == null}">
																		<img alt="Imagem User" src="assets\images\faq_man.png"
																			width="70px">
																	</c:if>
																</div>
																<input type="file" id="fileFoto" name="fileFoto"
																	accept="image/*"
																	onchange="visualizarImg('fotobase64', 'fileFoto');"
																	class="form-control-file"
																	style="margin-top: 15px; margin-left: 5px;">
															</div>


															<div class="form-group form-default form-static-label">
																<input type="text" name="nome" id="nome"
																	class="form-control" required="required"
																	value="${modelLogin.nome}" autocomplete="off">
																<span class="form-bar"></span> <label
																	class="float-label">Nome:</label>
															</div>


															<div class="form-group form-default form-static-label">
																<input onblur="buscaCep();" type="text" name="cep" id="cep"
																	class="form-control" required="required"
																	value="${modelLogin.cep}" autocomplete="off"> <span
																	class="form-bar"></span> <label class="float-label">CEP:</label>
															</div>


															<div class="form-group form-default form-static-label">
																<input type="text" name="logradouro" id="logradouro"
																	class="form-control" required="required"
																	value="${modelLogin.logradouro}" autocomplete="off">
																<span class="form-bar"></span> <label
																	class="float-label">Logradouro:</label>
															</div>

															<div class="form-group form-default form-static-label">
																<input type="text" name="complemento" id="complemento"
																	class="form-control" required="required"
																	value="${modelLogin.complemento}" autocomplete="off">
																<span class="form-bar"></span> <label
																	class="float-label">Complemento:</label>
															</div>

															<div class="form-group form-default form-static-label">
																<input type="text" name="bairro" id="bairro"
																	class="form-control" required="required"
																	value="${modelLogin.bairro}" autocomplete="off">
																<span class="form-bar"></span> <label
																	class="float-label">Bairro:</label>
															</div>

															<div class="form-group form-default form-static-label">
																<input type="text" name="cidade" id="cidade"
																	class="form-control" required="required"
																	value="${modelLogin.cidade}" autocomplete="off">
																<span class="form-bar"></span> <label
																	class="float-label">Cidade:</label>
															</div>

															<div class="form-group form-default form-static-label">
																<input type="text" name="uf" id="uf"
																	class="form-control" required="required"
																	value="${modelLogin.uf}" autocomplete="off"> <span
																	class="form-bar"></span> <label class="float-label">UF:</label>
															</div>


															<div class="form-group form-default form-static-label">
																<input type="text" name="login" id="login"
																	class="form-control" required="required"
																	value="${modelLogin.login}" autocomplete="off">
																<span class="form-bar"></span> <label
																	class="float-label">Login:</label>
															</div>

															<div class="form-group form-default form-static-label">
																<input type="email" name="email" id="email"
																	class="form-control" required="required"
																	autocomplete="off" value="${modelLogin.email}">
																<span class="form-bar"></span> <label
																	class="float-label">E-mail:</label>
															</div>

															<div class="form-group form-default form-static-label">
																<select class="form-control"
																	aria-label="Default select example" name="perfil">
																	<option disabled="disabled" selected="selected">[Selecione
																		o Grupo]</option>
																	<option value="ADMIN"
																		<%ModelLogin modelLogin = (ModelLogin) request.getAttribute("modelLogin");

if (modelLogin != null && modelLogin.getPerfil().equals("ADMIN")) {
	out.print(" ");
	out.print("selected=\"selected\"");
	out.print(" ");
}%>>Admin</option>

																	<option value="SECRETARIA"
																		<%modelLogin = (ModelLogin) request.getAttribute("modelLogin");

if (modelLogin != null && modelLogin.getPerfil().equals("SECRETARIA")) {
	out.print(" ");
	out.print("selected=\"selected\"");
	out.print(" ");
}%>>Secretária</option>

																	<option value="AUXILIAR"
																		<%modelLogin = (ModelLogin) request.getAttribute("modelLogin");

if (modelLogin != null && modelLogin.getPerfil().equals("AUXILIAR")) {
	out.print(" ");
	out.print("selected=\"selected\"");
	out.print(" ");
}%>>Auxiliar</option>

																</select> <span class="form-bar"></span> <label
																	class="float-label">Perfil:</label>
															</div>

															<div class="form-group form-default form-static-label">
																<input type="password" name="senha" id="senha"
																	class="form-control" required="required"
																	autocomplete="off" value="${modelLogin.senha}">
																<span class="form-bar"></span> <label
																	class="float-label">Senha</label>
															</div>
															<div class="form-group form-default form-static-label">
																<input type="radio" name="sexo" , checked="checked"
																	value="MASCULINO"
																	<%modelLogin = (ModelLogin) request.getAttribute("modelLogin");

if (modelLogin != null && modelLogin.getSexo().equals("MASCULINO")) {
	out.print(" ");
	out.print("checked=\"checked\"");
	out.print(" ");
}%>>Masculino</>
																<input type="radio" name="sexo" , value="FEMININO"
																	<%modelLogin = (ModelLogin) request.getAttribute("modelLogin");

if (modelLogin != null && modelLogin.getSexo().equals("FEMININO")) {
	out.print(" ");
	out.print("checked=\"checked\"");
	out.print(" ");
}%>>Feminino</>
															</div>
															<button type="button"
																class="btn btn-primary waves-effect waves-light"
																onclick="limparForm();">Novo</button>
															<button type="submit"
																class="btn btn-success waves-effect waves-light">Salvar</button>
															<button type="button"
																class="btn btn-info waves-effect waves-light"
																onclick="deletarComAjax();">Excluir</button>
															<button type="button" class="btn btn-secondary"
																data-toggle="modal" data-target="#modalConsultarUsuario">Consultar</button>

														</form>
													</div>
												</div>
											</div>
										</div>
										<span id="msg">${msg}</span>
										<div style="height: 300px; overflow: scroll;">
											<table class="table" id="tabelaConsultaView">
												<thead>
													<tr>
														<th scope="col">ID</th>
														<th scope="col">Nome</th>
														<th scope="col">Ver</th>
													</tr>
												</thead>
												<tbody>
													<c:forEach items="${modelLogins}" var="ml">
														<tr>
															<td><c:out value="${ml.id} "></c:out></td>
															<td><c:out value="${ml.nome} "></c:out></td>
															<td><c:out value="${ml.id} "></c:out></td>
															<td><a class="btn btn-success" type="button"
																href="<%= request.getContextPath() %>/ServletUsuarioController?acao=buscarEditar&id=${ml.id}">Ver</a></td>
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
	<jsp:include page="javascriptfile.jsp"></jsp:include>

	<!-- Modal -->
	<div class="modal fade" id="modalConsultarUsuario" tabindex="-1"
		role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<h5 class="modal-title" id="exampleModalLabel">Usuários</h5>
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
				</div>
				<div class="modal-body">

					<div class="input-group mb-3">
						<input type="text" class="form-control"
							placeholder="Nome do usuario" aria-label="Nome do usuario"
							id="nomeBusca" aria-describedby="basic-addon2">
						<div class="input-group-append">
							<button class="btn btn-success" type="button"
								onclick="buscarUsuario()">Buscar</button>
						</div>
					</div>
					<div style="height: 300px; overflow: scroll;">
						<table class="table" id="tabelaConsulta">
							<thead>
								<tr>
									<th scope="col">ID</th>
									<th scope="col">Nome</th>
									<th scope="col">Ver</th>
								</tr>
							</thead>
							<tbody>

							</tbody>
						</table>
					</div>
					<span id="total"></span>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-secondary"
						data-dismiss="modal">Fechar</button>
				</div>
			</div>
		</div>
	</div>

	<script type="text/javascript">
	
	function buscaCep() {
		var cep = $("#cep").val();
		
        //Consulta o webservice viacep.com.br/
        $.getJSON("https://viacep.com.br/ws/"+ cep +"/json/?callback=?", function(dados) {
        	 if (!("erro" in dados)) {
                 $("#logradouro").val(dados.logradouro);
                 $("#bairro").val(dados.bairro);
                 $("#cidade").val(dados.localidade);
                 $("#uf").val(dados.uf);
                 $("#complemento").val(dados.complemento);
        	 }
		});
		
	}
		function visualizarImg(fotobase64, fileFoto) {
			var preview = document.getElementById(fotobase64); // campo IMG html
			var fileUser = document.getElementById(fileFoto).files[0];
			var reader = new FileReader();

			reader.onloadend = function() {
				preview.src = reader.result; /*Carrega a foto na tela*/
			};

			if (fileUser) {
				reader.readAsDataURL(fileUser); /*Preview da imagem*/
			} else {
				preview.src = '';
			}

		}
		function verEditar(id) {
			var urlAction = document.getElementById("formUser").action;
			window.location.href = urlAction + '?acao=buscarEditar&id=' + id;
		}

		function buscarUsuario() {
			var nomeBusca = document.getElementById("nomeBusca").value;
			if (nomeBusca != null && nomeBusca != '' && nomeBusca.trim() != '') {
				var urlAction = document.getElementById("formUser").action;

				$
						.ajax(
								{
									method : "get",
									url : urlAction,
									data : "nomeBusca=" + nomeBusca
											+ "&acao=buscarUserAjax",
									success : function(response) {
										var json = JSON.parse(response);

										$('#tabelaConsulta > tbody > tr')
												.remove();

										for (var i = 0; i < json.length; i++) {
											$('#tabelaConsulta > tbody')
													.append(
															'<tr> <td>'
																	+ json[i].id
																	+ ' </td> <td> '
																	+ json[i].nome
																	+ '</td>  <td> <button onclick="verEditar('
																	+ json[i].id
																	+ ')" type="button" class="btn btn-info">Ver</button> </td> </tr>');
										}
										document.getElementById('total').textContent = 'Total: '
												+ json.length;
									}

								}).fail(
								function(xhr, status, errorThrown) {
									alert('Erro ao buscar o usuário por nome: '
											+ xhr.reponseText);
								});
			}
		}

		function deletarComAjax() {
			if (confirm('Deseja realmente excluir?')) {
				var urlAction = document.getElementById("formUser").action;
				var idUser = document.getElementById("id").value;

				$.ajax({
					method : "get",
					url : urlAction,
					data : "id=" + idUser + "&acao=deletarAjax",
					success : function(response) {
						limparForm();
						alert(response);
						document.getElementById('msg').textContent = response;
					}

				}).fail(
						function(xhr, status, errorThrown) {
							alert('Erro ao deletar o usuário por id: '
									+ xhr.reponseText);
						});
			}

		}

		function deletar() {
			if (confirm('Deseja realmente excluir?')) {
				document.getElementById("formUser").method = 'get';
				document.getElementById("acao").value = 'deletar';
				document.getElementById("formUser").submit();
			}
		}

		function limparForm() {

			var elementos = document.getElementById("formUser").elements; /*Retorna os elementos html dentro do form*/

			for (p = 0; p < elementos.length; p++) {
				elementos[p].value = '';
			}
		}
	</script>
</body>
</html>