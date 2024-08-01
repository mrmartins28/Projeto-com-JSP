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

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		try {

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
			
			if(!daoUsuarioRepository.validaLogin(login)) {		

			modelLogin = daoUsuarioRepository.salvarUsuario(modelLogin);

			request.setAttribute("msg", "Operação realizada com sucesso");
			request.setAttribute("modelLogin", modelLogin);

			request.getRequestDispatcher("principal/cadUsuario.jsp").forward(request, response);
			
			} else {
				
				request.setAttribute("msg", "Já existe usuário com esse login!");
				request.getRequestDispatcher("erro.jsp").forward(request, response);
				
			}

		} catch (Exception e) {
			e.printStackTrace();
			RequestDispatcher redirecionar = request.getRequestDispatcher("erro.jsp");
			request.setAttribute("msg", e.getMessage());
			redirecionar.forward(request, response);
		}

	}
}
