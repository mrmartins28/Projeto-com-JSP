package servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.DAOUsuarioRepository;
import model.ModelLogin;

@WebServlet("/servletUsuarioController")
public class servletUsuarioController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	DAOUsuarioRepository daoUsuarioRepository = new DAOUsuarioRepository();

	public servletUsuarioController() {
		super();

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String acao = request.getParameter("acao");
		String msg = "";
		
		if(acao != null && !acao.isEmpty() && acao.equalsIgnoreCase("deletar")) {
		
		String id = request.getParameter("id");
		
		if(id != null && !id.isEmpty()) {
		
		
			try {
				daoUsuarioRepository.deletarUsuario(id);
			} catch (Exception e) {
				e.printStackTrace();
				RequestDispatcher redirecionar = request.getRequestDispatcher("erro.jsp");
				request.setAttribute("msg", e.getMessage());
				redirecionar.forward(request, response);
			}
			msg = "Usuário deletado com sucesso!";
			request.setAttribute("msg", msg);
			request.getRequestDispatcher("principal/cadUsuario.jsp").forward(request, response);
		
		} else {
			msg = "Campo Id inválido!";
		}
		
		} else if(acao != null && !acao.isEmpty() && acao.equalsIgnoreCase("deletarAjax")) {
			 String id = request.getParameter("id");
			 
			if(id != null && !id.isEmpty()) {
				
				try {
					daoUsuarioRepository.deletarUsuario(id);
					response.getWriter().write("Usuario deletado com sucesso!");
				} catch (Exception e) {
					e.printStackTrace();
				}
				
			}
			
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

			ModelLogin modelLogin = new ModelLogin();
			modelLogin.setId(id != null && !id.isEmpty() ? Long.parseLong(id) : null);
			modelLogin.setNome(nome);
			modelLogin.setEmail(email);
			modelLogin.setLogin(login);
			modelLogin.setSenha(senha);
			
			if(daoUsuarioRepository.validaLogin(modelLogin.getLogin()) && modelLogin.getId() == null) {
				if(modelLogin.isNovo()) {
				modelLogin = daoUsuarioRepository.atualizarCadastro(modelLogin);
				msg = "Esse Login já existe, Usuario atualizado";
				}
			} else if(modelLogin.getId() == null) {
				
				
				modelLogin = daoUsuarioRepository.salvarUsuario(modelLogin);
			}

			

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
