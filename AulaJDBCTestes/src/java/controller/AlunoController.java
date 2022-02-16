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
@WebServlet(name = "Alunos", urlPatterns = {"/AlunoController"})
public class AlunoController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private static String INSERT_OR_EDIT = "/aluno.jsp";
    private static String LIST_USER = "/listAluno.jsp";
    private AlunoDao dao;

    public AlunoController() {
        super();
        dao = new AlunoDao();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String forward="";
        String action = request.getParameter("action");

        if (action.equalsIgnoreCase("delete")){
            int matricula = Integer.parseInt(request.getParameter("matricula"));
            dao.deleteAluno(matricula);
            forward = LIST_USER;
            request.setAttribute("alunos", dao.getAllAlunos());
        } else if (action.equalsIgnoreCase("edit")){
            forward = INSERT_OR_EDIT;
            int matricula = Integer.parseInt(request.getParameter("matricula"));
            Aluno user = dao.getAlunoById(matricula);
            request.setAttribute("user", user);
        } else if (action.equalsIgnoreCase("listAlunos")){
            forward = LIST_USER;
            request.setAttribute("alunos", dao.getAllAlunos());
        } else {
            forward = INSERT_OR_EDIT;
        }

        RequestDispatcher view = request.getRequestDispatcher(forward);
        view.forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Aluno aluno = new Aluno();
        aluno.setNome(request.getParameter("nome"));

        try {
            Date nasc = null;
            String teste = request.getParameter("nasc");
            System.out.println(teste);
            if(request.getParameter("nasc") != null){
                nasc = new SimpleDateFormat("dd/MM/yyyy").parse(request.getParameter("nasc"));
            }
            else{
                nasc = null;
            }
                
            aluno.setNascimento(nasc);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        aluno.setEndereco(request.getParameter("endereco"));
        String matricula = request.getParameter("matricula");
        if(matricula == null || matricula.isEmpty())
        {
            dao.addAluno(aluno);
        }
        else
        {
            aluno.setMatricula(Integer.parseInt(matricula));
            dao.updateAluno(aluno);
        }
        RequestDispatcher view = request.getRequestDispatcher(LIST_USER);
        request.setAttribute("alunos", dao.getAllAlunos());
        view.forward(request, response);
    }
}