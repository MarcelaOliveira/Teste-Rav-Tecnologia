package Controle;

import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import util.Erro;
import Modelo.FuncionariosModelo;

@WebServlet("/HomeControle")
public class HomeControle<MultipartRequest> extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String pagina = request.getParameter("pagina").toString();
		switch(pagina) {
			case "home" :
				ArrayList<FuncionariosModelo> lista = new FuncionariosControle().consultarFuncionarios();
				request.setAttribute("lista", lista);	
				request.getRequestDispatcher("FuncionariosVisualizar.jsp").forward(request, response);
			break;
			default :
				request.getRequestDispatcher("404.jsp").forward(request, response);
				
		}
	}
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String acao = request.getParameter("form").toString();
		FuncionariosModelo funcionario = new FuncionariosModelo();
		FuncionariosControle com = new FuncionariosControle();
		Erro erros = new Erro();
		switch(acao) {
			case "cadastro" :
				try{
					funcionario.setNome(request.getParameter("nome"));
					funcionario.setEmail(request.getParameter("email"));
					funcionario.setEndereco(request.getParameter("endereco"));
					funcionario.setTelefone(request.getParameter("telefone"));
					String email = request.getParameter("email");
					if (com.consultarEmail(email)) {
						erros.add("Email já existente!");
						erros.add("Verifique se já este cadastrado ou troque o email!");
						request.setAttribute("mensagem", erros);
						ArrayList<FuncionariosModelo> lista = new FuncionariosControle().consultarFuncionarios();
						request.setAttribute("lista", lista);
						request.getRequestDispatcher("FuncionariosVisualizar.jsp").forward(request, response);
					}
					if (!erros.isExisteErros()) {
						if(new FuncionariosControle().insertFuncionarios(funcionario)) {
							ArrayList<FuncionariosModelo> lista = new FuncionariosControle().consultarFuncionarios();
							request.setAttribute("lista", lista);
							request.getRequestDispatcher("FuncionariosVisualizar.jsp").forward(request, response);
							
						}
					}
				}catch(IOException e) {
					System.out.println("Erro de PDO: "+e.getMessage());
				}
			break;
			case "editar" :
				try{
					FuncionariosModelo funciona = new FuncionariosModelo();
					funciona.setNome(request.getParameter("nome"));
					funciona.setEmail(request.getParameter("email"));
					funciona.setEndereco(request.getParameter("endereco"));
					funciona.setTelefone(request.getParameter("telefone"));
					funciona.setId(Integer.parseInt(request.getParameter("id")));
					String email = request.getParameter("email");
					if (com.consultarEmail(email)) {
						erros.add("Email já existente!");
						erros.add("Verifique se já este cadastrado ou troque o email!");
						request.setAttribute("mensagem", erros);
						ArrayList<FuncionariosModelo> lista = new FuncionariosControle().consultarFuncionarios();
						request.setAttribute("lista", lista);
						request.getRequestDispatcher("FuncionariosVisualizar.jsp").forward(request, response);
					}
					if (!erros.isExisteErros()) {
						if (new FuncionariosControle().updateFunc(funciona)) {		
							ArrayList<FuncionariosModelo> lista = new FuncionariosControle().consultarFuncionarios();
							request.setAttribute("lista", lista);
							request.getRequestDispatcher("FuncionariosVisualizar.jsp").forward(request, response);
						}	
					}
				}catch(IOException e) {
					System.out.println("Erro de PDO: "+e.getMessage());
				}
			break;
			case "deletar" :
				int id = Integer.parseInt(request.getParameter("id"));
				if(new FuncionariosControle().deletarFuncionario(id)) {
					ArrayList<FuncionariosModelo> lista = new FuncionariosControle().consultarFuncionarios();
					request.setAttribute("lista", lista);
					request.getRequestDispatcher("FuncionariosVisualizar.jsp").forward(request, response);
				}
				if (erros.isExisteErros()) {
					erros.add("Não foi possivel efetuar remoção");
					request.setAttribute("mensagens", erros);
					request.getRequestDispatcher("Remover.jsp").forward(request, response);
				}	
			break;
			case "deletarContas" :
				String[] ids = String.valueOf(request.getParameter("idd")).split(",");
				for(int i = 0; i < ids.length; i++) {
					if(new FuncionariosControle().deletarFuncionarios(ids[i])) {
						
					}
				}
				ArrayList<FuncionariosModelo> lista = new FuncionariosControle().consultarFuncionarios();
				request.setAttribute("lista", lista);
				request.getRequestDispatcher("FuncionariosVisualizar.jsp").forward(request, response);
			break;
			default :
				request.getRequestDispatcher("404.jsp").forward(request, response);
		}
	}

}
