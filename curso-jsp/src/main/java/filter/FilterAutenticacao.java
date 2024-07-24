package filter;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import connection.SingleConnection;

@WebFilter(urlPatterns = { "/principal/*" }) /* Intercepta todas as requisições que vierem do projeto ou mapeamento */
public class FilterAutenticacao extends HttpFilter implements Filter {

	private static final long serialVersionUID = 1L;
	
	private static Connection connection;

	public FilterAutenticacao() {
		super();
	}

	/* destroy - encerra os processos quando o servidor */
	public void destroy() {
		
		try {
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	/* doFilter - intercepta as requisições e as respostas do sistema */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		
		try {

		HttpServletRequest req = (HttpServletRequest) request;
		HttpSession session = req.getSession();

		String usuarioLogado = (String) session.getAttribute("usuario");

		String urlParaAutenticar = req.getServletPath();// url que está sendo acessada

		if (usuarioLogado == null && !urlParaAutenticar.equalsIgnoreCase("/principal/ServletLogin")) {

			RequestDispatcher redireciona = request.getRequestDispatcher("/index.jsp?url=" + urlParaAutenticar);
			request.setAttribute("msg", "Por favor, realize o login!");
			redireciona.forward(request, response);
			return;// para a execução e redireciona para o login
		} else {

			chain.doFilter(request, response);
		}
		
		connection.commit();
		
		}catch (Exception e) {
			e.printStackTrace();
			try {
				connection.rollback();
				
				RequestDispatcher redirecionar = request.getRequestDispatcher("erro.jsp");
				request.setAttribute("msg", e.getMessage());
				redirecionar.forward(request, response);
				
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}

	}

	public void init(FilterConfig fConfig) throws ServletException {
		
		connection = SingleConnection.getConnection();

	}

}
