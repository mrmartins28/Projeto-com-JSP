package servlets;

import java.io.Serializable;


import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import dao.DAOUsuarioRepository;


public class ServletGenericUtil extends HttpServlet implements Serializable{

	private static final long serialVersionUID = 1L;
	
	DAOUsuarioRepository daoUsuarioRepository = new DAOUsuarioRepository();
	
	public Long getUserLogado(HttpServletRequest request) throws Exception {
		
		HttpSession session = request.getSession();

		String usuarioLogado = (String) session.getAttribute("usuario");
		
		if(daoUsuarioRepository.isAdmin(usuarioLogado)) {
			
			return  daoUsuarioRepository.buscarUsuarioAdmin(usuarioLogado).getId();
		} else {
			
			return daoUsuarioRepository.buscarUsuario(usuarioLogado).getId();
		}
		
		
	}
	
	

}
