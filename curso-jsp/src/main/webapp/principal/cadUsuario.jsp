<%@page import="model.ModelLogin"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<!DOCTYPE html>
<html lang="en">


<jsp:include page="head.jsp"></jsp:include>



<body>

	<jsp:include page="theme-loader.jsp"></jsp:include>

	<!-- Pre-loader end -->
	<div id="pcoded" class="pcoded">
		<div class="pcoded-overlay-box"></div>
		<div class="pcoded-container navbar-wrapper">

			<jsp:include page="navbarTop.jsp"></jsp:include>


			<div class="pcoded-main-container">
				<div class="pcoded-wrapper">

					<jsp:include page="navbarmainmenu.jsp"></jsp:include>

					<div class="pcoded-content">


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
															action="<%=request.getContextPath()%>/servletUsuarioController"
															method="post" id="formUser">

															<input type="hidden" name="acao" id="acao">

															<div class="form-group form-default form-static-label">
																<input type="text" name="id" id="id"
																	value="${modelLogin.id }" class="form-control"
																	readonly="readonly"> <span class="form-bar"></span>
																<label class="float-label">Id:</label>
															</div>

															<div class="form-group form-default input-group mb-4">
																<div class="input-group-prepend">
																	<c:if
																		test="${modelLogin.imagemUser !='' && modelLogin.imagemUser != null }">
																		<a
																			href="<%= request.getContextPath() %>/servletUsuarioController?acao=dowloadFoto&id=${modelLogin.id}">
																			<img alt="Imagem User" id="fotoembase64"
																			src="${modelLogin.imagemUser }" width="70px">
																		</a>
																	</c:if>

																	<c:if
																		test="${modelLogin.imagemUser == '' || modelLogin.imagemUser == null }">

																		<img alt="Imagem User" id="fotoembase64"
																			src="assets\images\user.png" width="70px">

																	</c:if>

																</div>
																<input type="file" id="filefoto" name="filefoto"
																	accept="image/*"
																	onchange="visualizaimg('fotoembase64','filefoto');"
																	class="form-control-file"
																	style="margin-top: 15px; margin-left: 5px;">
															</div>

															<div class="form-group form-default form-static-label">
																<input type="text" name="nome" id="nome"
																	value="${modelLogin.nome }" class="form-control"
																	required="required"> <span class="form-bar"></span>
																<label class="float-label">Nome:</label>
															</div>
															<div class="form-group form-default form-static-label">
																<input type="email" name="email" id="email"
																	value="${modelLogin.email }" class="form-control"
																	required="required" autocomplete="off"> <span
																	class="form-bar"></span> <label class="float-label">Email:</label>
															</div>


															<div class="form-group form-default form-static-label">
																<select name="perfil" class="form-control" id="perfil">
																	<option disabled="disabled" value=${modelLogin.perfil }>Selecione
																		um perfil</option>
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
																	class="float-label">Perfil do usuário</label>
															</div>

															<div class="form-group form-default form-static-label">
																<input type="text" onblur="pesquisaCEP();" name="cep"
																	id="cep" value="${modelLogin.cep }"
																	class="form-control" required="required"
																	autocomplete="off"> <span class="form-bar"></span>
																<label class="float-label">CEP:</label>
															</div>

															<div class="form-group form-default form-static-label">
																<input type="text" name="rua" id="rua"
																	value="${modelLogin.rua }" class="form-control"
																	required="required" autocomplete="off"> <span
																	class="form-bar"></span> <label class="float-label">Rua:</label>
															</div>
															<div class="form-group form-default form-static-label">
																<input type="text" name="bairro" id="bairro"
																	value="${modelLogin.bairro }" class="form-control"
																	required="required" autocomplete="off"> <span
																	class="form-bar"></span> <label class="float-label">Bairro:</label>
															</div>
															<div class="form-group form-default form-static-label">
																<input type="text" name="localidade" id="localidade"
																	value="${modelLogin.localidade }" class="form-control"
																	required="required" autocomplete="off"> <span
																	class="form-bar"></span> <label class="float-label">Localidade:</label>
															</div>
															<div class="form-group form-default form-static-label">
																<input type="text" name="uf" id="uf"
																	value="${modelLogin.uf }" class="form-control"
																	required="required" autocomplete="off"> <span
																	class="form-bar"></span> <label class="float-label">Estado:</label>
															</div>
															<div class="form-group form-default form-static-label">
																<input type="text" name="numero" id="numero"
																	value="${modelLogin.numero }" class="form-control"
																	required="required" autocomplete="off"> <span
																	class="form-bar"></span> <label class="float-label">Número:</label>
															</div>


															<div class="form-group form-default form-static-label">
																<input type="text" name="login" id="login"
																	value="${modelLogin.login }" class="form-control"
																	required="required" autocomplete="off"> <span
																	class="form-bar"></span> <label class="float-label">Login:</label>
															</div>
															<div class="form-group form-default form-static-label">
																<input type="password" name="senha" id="senha"
																	value="${modelLogin.senha }" class="form-control"
																	required="required" autocomplete="off"> <span
																	class="form-bar"></span> <label class="float-label">Senha:</label>
															</div>
															<div class="form-group form-default form-static-label">
																<input type="radio" name="sexo" value="Masculino"
																	required="required"
																																	<%modelLogin = (ModelLogin) request.getAttribute("modelLogin");
																if (modelLogin != null && modelLogin.getSexo().equals("Masculino")) {
																	out.print(" ");
																	out.print("checked=\"checked\"");
																	out.print(" ");
																
																}%>>Masculino</>

																<input type="radio" name="sexo" value="Feminino"
																	required="required"
																	<%modelLogin = (ModelLogin) request.getAttribute("modelLogin");
																if (modelLogin != null && modelLogin.getSexo().equals("Feminino")) {
																	out.print(" ");
																	out.print("checked=\"checked\"");
																	out.print(" ");
																
																}%>>Feminino</>
																<div class="invalid-feedback">Informe o sexo!</div>
															</div>


															<button type="button"
																class="btn btn-primary waves-effect waves-light"
																onclick="limparForm()">Novo</button>
															<button type="submit"
																class="btn btn-success waves-effect waves-light">Salvar
															</button>
															<button type="button"
																class="btn btn-info waves-effect waves-light"
																onclick="criarDeleteComAjax()">Excluir</button>
															<button type="button" class="btn btn-primary"
																data-toggle="modal" data-target="#modalUsuario">
																Pesquisa de usuário</button>
															<a
																href="<%=request.getContextPath()%>/servletUsuarioController?acao=listarUsers"
																class="btn btn-primary"
																data-i18n="nav.basic-components.alert">Listar </a>


														</form>
													</div>
												</div>
											</div>
										</div>

										<span id='msg'>${msg}</span>

										<div style="height: 300px; overflow: scroll;">
											<table class="table" id="tabelaResultadosView">
												<thead>
													<tr>
														<th>ID</th>
														<th>NOME</th>
														<th>VER</th>
													</tr>
												</thead>
												<tbody>

													<c:forEach items="${modelLogins}" var="ml">

														<tr>
															<td><c:out value="${ml.id}"></c:out></td>
															<td><c:out value="${ml.nome}"></c:out></td>
															<td><a class="btn btn-success"
																href="<%= request.getContextPath() %>/servletUsuarioController?acao=buscarEditar&id=${ml.id}">ver</a>
															</td>
															<td><a class="btn btn-success"
																href="<%= request.getContextPath() %>/servletUsuarioController?acao=deletar&id=${ml.id}">deletar</a>
															</td>
														</tr>

													</c:forEach>


												</tbody>
											</table>

										</div>

										<nav aria-label="Page navigation example">
											<ul class="pagination">
											<%
											int totalPagina = (int) request.getAttribute("totalPaginas");
											
											for(int p=0; p<totalPagina ; p++){
												
												String url = request.getContextPath() + "/servletUsuarioController?acao=paginar&pagina=" + (p * 5);
												out.print("<li class=\"page-item\"><a class=\"page-link\" href=\""+ url +"\">"+ (p + 1)+"</a></li>");
											}
											
											
											%>
											
												
											</ul>
										</nav>

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

	<!-- Modal -->
	<div class="modal fade" id="modalUsuario" tabindex="-1" role="dialog"
		aria-labelledby="exampleModalLabel" aria-hidden="true">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<h5 class="modal-title" id="exampleModalLabel">Pesquisa de
						usuário</h5>
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
				</div>
				<div class="modal-body">

					<div class="input-group mb-3">
						<input type="text" class="form-control" placeholder="Nome"
							id="nomeBusca">
						<div class="input-group-append">
							<button class="btn btn-success" type="submit"
								onclick="buscarUsuario()">Buscar</button>
						</div>
					</div>

				</div>

				<div class="container">
					<h2>Usuários</h2>
					<p>Lista de Usuários, clique em 'ver' para mais detalhes.</p>
					<div style="height: 300px; overflow: scroll;">
						<table class="table" id="tabelaResultados">
							<thead>
								<tr>
									<th>ID</th>
									<th>NOME</th>
									<th>VER</th>
								</tr>
							</thead>
							<tbody>


							</tbody>
						</table>

					</div>
					<span id="totalResultado"></span>
				</div>

				<div class="modal-footer">
					<button type="button" class="btn btn-secondary"
						data-dismiss="modal">Fechar</button>
				</div>
			</div>
		</div>
	</div>

	<jsp:include page="javascriptFile.jsp"></jsp:include>
	<script type="text/javascript">
		function pesquisaCEP() {
			var cep = $("#cep").val();
			
			 $.getJSON("https://viacep.com.br/ws/"+ cep +"/json/?callback=?", function(dados) {

                 if (!("erro" in dados)) {
                     //Atualiza os campos com os valores da consulta.
                     $("#cep").val(dados.cep);
                     $("#rua").val(dados.logradouro);
                     $("#bairro").val(dados.bairro);
                     $("#localidade").val(dados.localidade);
                     $("#uf").val(dados.uf);
                   
                 } //end if.
                 else {
                     //CEP pesquisado não foi encontrado.
                     limpa_formulário_cep();
                     alert("CEP não encontrado.");
                 }

                 });
			 }
		function visualizaimg(fotobase64, filefoto) {
			var preview = document.getElementById(fotobase64);
			var fileUser = document.getElementById(filefoto).files[0];
			var reader = new FileReader();

			reader.onloadend = function() {
				preview.src = reader.result;

			};

			if (fileUser) {
				reader.readAsDataURL(fileUser);
			} else {
				preview.src = '';
			}

		}

		function buscarUsuario() {

			var nomeBusca = document.getElementById('nomeBusca').value;
			var urlAction = document.getElementById('formUser').action;

			if (nomeBusca != null && nomeBusca != '' && nomeBusca.trim() != '') { /* Validando que quem ue ter algum valor para buscar*/

				$
						.ajax({

							method : "get",
							url : urlAction,
							data : "nomeBusca=" + nomeBusca
									+ '&acao=buscarUserAjax',
							success : function(response) {

								var json = JSON.parse(response);

								$('#tabelaResultados > tbody > tr').remove(); //Jquery para remover as linhas

								for (var p = 0; p < json.length; p++) {

									$('#tabelaResultados > tbody')
											.append(
													'<tr> <td>'
															+ json[p].id
															+ '</td> <td>'
															+ json[p].nome
															+ '</td> <td>'
															+ '<button class="btn waves-effect waves-light btn-info" onclick="verUsuario('
															+ json[p].id
															+ ')"><i "icofont icofont-eye-alt"></i> Ver </button> </td> </tr>');

								}

								document.getElementById('totalResultado').textContent = 'resultados= '
										+ json.length;

							}

						})
				fail(function(xhr, status, erroThrown) {
					alert('Erro ao buscar usuario por nome: '
							+ xhr.responseText);
				});

			}

		}

		function verUsuario(id) {

			var urlAction = document.getElementById('formUser').action;
			var idUser = document.getElementById('id').value;

			window.location.href = urlAction + '?acao=buscarEditar&id=' + id;

		}

		function criarDeleteComAjax() {

			var urlAction = document.getElementById('formUser').action;
			var idUser = document.getElementById('id').value;

			if (idUser != null && idUser != '' && idUser.trim() != '') {
				if (confirm('Deseja excluir esse usuário?')) {

					$
							.ajax({

								method : "get",
								url : urlAction,
								data : "id=" + idUser + '&acao=deletarAjax',
								success : function(response) {

									limparForm();
									document.getElementById('msg').textContent = response;

								}

							})
					fail(function(xhr, status, erroThrown) {
						alert('Erro ao deletar usuário por Id: '
								+ xhr.responseText);
					});

				}
			}
		}

		function criarDelete() {

			if (confirm("Deseja excluir esse usuário?")) {

				document.getElementById("formUser").method = 'get';
				document.getElementById("acao").value = 'deletar';
				document.getElementById("formUser").submit();

			}
		}

		function limparForm() {

			var elementos = document.getElementById("formUser").elements;

			for (i = 0; i < elementos.length; i++) {

				elementos[i].value = '';
			}

		}
	</script>

</body>

</html>
