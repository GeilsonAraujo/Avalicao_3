package controller;


import dao.AlunoDao;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Aluno;

@ApplicationScoped
@ManagedBean
@WebServlet(name = "Login", urlPatterns = {"/LoginController"})
public class LoginController extends HttpServlet {
    private static final long serialVersionUID = 1L;
//    private static String INSERT_OR_EDIT = "/user.jsp";
//    private static String LIST_USER = "/listUser.jsp";
    private AlunoDao dao;

    public LoginController() {
        super();
        dao = new AlunoDao();
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.service(req, resp); //To change body of generated methods, choose Tools | Templates.
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.sendRedirect("login.xhtml");	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

            String nomeUsuario = request.getParameter("username");
            String senhaUsuario = request.getParameter("password");

            if (nomeUsuario.equals("admin") && senhaUsuario.equals("12345")){
                    request.getSession().setAttribute("username", nomeUsuario);
                    response.sendRedirect("AlunoController?action=listAlunos");	// vai fazer o direcionamento para a pagina pós login
            }else{
                    RequestDispatcher dispatcher = request.getRequestDispatcher("login.xhtml"); //faz o dispacho das informações obtidas na pagina formlogin
                    dispatcher.forward(request, response);			
            }
    }

}