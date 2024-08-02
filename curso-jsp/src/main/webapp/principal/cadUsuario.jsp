<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
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
														<form class="form-material"
															action="<%=request.getContextPath()%>/servletUsuarioController"
															method="post" id="formUser">
															<div class="form-group form-default">
																<input type="text" name="id" id="id"
																	value="${modelLogin.id }" class="form-control"
																	readonly="readonly"> <span class="form-bar"></span>
																<label class="float-label">Id:</label>
															</div>
															<div class="form-group form-default">
																<input type="text" name="nome" id="nome"
																	value="${modelLogin.nome }" class="form-control"
																	required="required"> <span class="form-bar"></span>
																<label class="float-label">Nome:</label>
															</div>
															<div class="form-group form-default">
																<input type="email" name="email" id="email"
																	value="${modelLogin.email }" class="form-control"
																	required="required" autocomplete="off"> <span
																	class="form-bar"></span> <label class="float-label">Email:</label>
															</div>
															<div class="form-group form-default">
																<input type="text" name="login" id="login"
																	value="${modelLogin.login }" class="form-control"
																	required="required" autocomplete="off"> <span
																	class="form-bar"></span> <label class="float-label">Login:</label>
															</div>
															<div class="form-group form-default">
																<input type="password" name="senha" id="senha"
																	value="${modelLogin.senha }" class="form-control"
																	required="required" autocomplete="off"> <span
																	class="form-bar"></span> <label class="float-label">Senha:</label>
															</div>
															<button class="btn btn-primary waves-effect waves-light" onclick="limparForm()">Novo</button>
															<button class="btn btn-success waves-effect waves-light" >Salvar </button>
															<button class="btn btn-info waves-effect waves-light">Excluir</button>
															

														</form>
													</div>
												</div>
											</div>
										</div>

										<span>${msg}</span>

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


	<jsp:include page="javascriptFile.jsp"></jsp:include>
	<script type="text/javascript">
	
	function limparForm() {
		
		var elementos = document.getElementById("formUser").elements;
		
		for(i = 0; i < elementos.length ; i++ ){

		elementos[i].value = '';
		}
		
	}
	</script>

</body>

</html>
