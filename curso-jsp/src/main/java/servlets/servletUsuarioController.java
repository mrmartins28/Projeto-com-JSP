package servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;

import dao.DAOUsuarioRepository;
import model.ModelLogin;

@WebServlet(urlPatterns = { "/servletUsuarioController"}) 
public class servletUsuarioController extends ServletGenericUtil {
	private static final long serialVersionUID = 1L;
	DAOUsuarioRepository daoUsuarioRepository = new DAOUsuarioRepository();

	public servletUsuarioController() {
		super();

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String acao = request.getParameter("acao");
		String msg = "";
		try {
			if (acao != null && !acao.isEmpty() && acao.equalsIgnoreCase("deletar")) {

				String id = request.getParameter("id");

				daoUsuarioRepository.deletarUsuario(id);
				
				List<ModelLogin> modelLogins = daoUsuarioRepository.consultaUsuariolist(super.getUserLogado(request));
				request.setAttribute("modelLogins", modelLogins);


				msg = "Usuário deletado com sucesso!";
				request.setAttribute("msg", msg);
				request.getRequestDispatcher("principal/cadUsuario.jsp").forward(request, response);

			} else if (acao != null && !acao.isEmpty() && acao.equalsIgnoreCase("deletarAjax")) {
				String id = request.getParameter("id");

				if (id != null && !id.isEmpty()) {

					daoUsuarioRepository.deletarUsuario(id);
					response.getWriter().write("Usuario deletado com sucesso!");

				}

			} else if (acao != null && !acao.isEmpty() && acao.equalsIgnoreCase("buscarUserAjax")) {

				String nomeBusca = request.getParameter("nomeBusca");

				List<ModelLogin> dadosUserJason = daoUsuarioRepository.consultaUsuariolist(nomeBusca, super.getUserLogado(request));

				ObjectMapper mapper = new ObjectMapper();

				String json = mapper.writeValueAsString(dadosUserJason);
				System.out.println(json);
				response.getWriter().write(json);

			} else if (acao != null && !acao.isEmpty() && acao.equalsIgnoreCase("buscarEditar")) {

				String id = request.getParameter("id");

				ModelLogin modelLogin = daoUsuarioRepository.buscarUsuarioId(id);
				
				List<ModelLogin> modelLogins = daoUsuarioRepository.consultaUsuariolist(super.getUserLogado(request));
				request.setAttribute("modelLogins", modelLogins);

				request.setAttribute("msg", "usuário em edição.");
				request.setAttribute("modelLogin", modelLogin);

				request.getRequestDispatcher("principal/cadUsuario.jsp").forward(request, response);
				
			} else if (acao != null && !acao.isEmpty() && acao.equalsIgnoreCase("listarUsers")) {
				
				List<ModelLogin> modelLogins = daoUsuarioRepository.consultaUsuariolist(super.getUserLogado(request));
				
				
				request.setAttribute("msg", "usuários carregados.");
				request.setAttribute("modelLogins", modelLogins);

				request.getRequestDispatcher("principal/cadUsuario.jsp").forward(request, response);
				
			}
			

			else {
				
				List<ModelLogin> modelLogins = daoUsuarioRepository.consultaUsuariolist(super.getUserLogado(request));
				request.setAttribute("modelLogins", modelLogins);


				request.getRequestDispatcher("principal/cadUsuario.jsp").forward(request, response);
			}

		} catch (Exception e) {
			e.printStackTrace();
			RequestDispatcher redirecionar = request.getRequestDispatcher("erro.jsp");
			request.setAttribute("msg", e.getMessage());
			redirecionar.forward(request, response);
		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		try {

			String msg = "Operação realizada com sucesso!";
			String id = request.getParameter("id");
			String nome = request.getParameter("nome");
			String email = request.getParameter("email");
			String login = request.getParameter("login");
			String senha = request.getParameter("senha");
			String perfil = request.getParameter("perfil");

			ModelLogin modelLogin = new ModelLogin();
			modelLogin.setId(id != null && !id.isEmpty() ? Long.parseLong(id) : null);
			modelLogin.setNome(nome);
			modelLogin.setEmail(email);
			modelLogin.setLogin(login);
			modelLogin.setSenha(senha);
			modelLogin.setPerfil(perfil);

			if (daoUsuarioRepository.validaLogin(modelLogin.getLogin()) && modelLogin.getId() != null) {
				if (!modelLogin.isNovo()) {
					modelLogin = daoUsuarioRepository.atualizarCadastro(modelLogin);
					msg = "Esse Login já existe, Usuario atualizado";
					
				}
			} else if (modelLogin.getId() == null) {

				modelLogin = daoUsuarioRepository.salvarUsuario(modelLogin, super.getUserLogado(request));
			} 
			
			List<ModelLogin> modelLogins = daoUsuarioRepository.consultaUsuariolist(super.getUserLogado(request));
			request.setAttribute("modelLogins", modelLogins);


			request.setAttribute("msg", msg);
			request.setAttribute("modelLogin", modelLogin);

			request.getRequestDispatcher("principal/cadUsuario.jsp").forward(request, response);

		} catch (Exception e) {
			e.printStackTrace();
			RequestDispatcher redirecionar = request.getRequestDispatcher("erro.jsp");
			request.setAttribute("msg", e.getMessage());
			redirecionar.forward(request, response);
		}

	}
}
