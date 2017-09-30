package servlets;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import modelo.Usuario;

/**
 * Servlet implementation class ServletRegistro
 */
@WebServlet(
		urlPatterns = { "/ServletRegistro" }, 
		initParams = { 
				@WebInitParam(name = "author", value = "Alberto y Saul")
		})
public class ServletRegistro extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public ServletRegistro() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String usuario = request.getParameter("usuario");
		String clave = request.getParameter("clave");
		String clave2 = request.getParameter("clave2");
		String mail = request.getParameter("mail");
		
		if(!clave.equals(clave2)) {
			response.sendError(response.SC_NOT_ACCEPTABLE, "Las claves no coinciden");
		}
		
		Usuario usu = new Usuario();
		usu.setUsuario(usuario);
		usu.setClave(clave);
		usu.setMail(mail);
		
		ServletContext contexto = getServletConfig().getServletContext();
		
		if(contexto.getAttribute("mapausuarios") == null) {
			contexto.setAttribute("mapausuarios", new HashMap<String, Usuario>());
		}
		
		HashMap<String, Usuario> mapaUsuarios = (HashMap<String, Usuario>) contexto.getAttribute("mapausuarios");
		mapaUsuarios.put(usuario, usu);
		
		response.getWriter().append("<html>")
							.append("<head>Usuario Registrado</head>")
							.append("<body>Se registro el usuario: " + usuario + "<br/>")
							.append("---> Author: " + getServletConfig().getInitParameter("author"))
							.append("</body></html>");
		
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
