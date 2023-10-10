package servlets;

import java.io.IOException;

import dao.DAOLoginRepository;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.ModelLogin;

@WebServlet(urlPatterns = {"/principal/ServletLogin", "/ServletLogin"}) // mapeamento da URL que vem da tela
public class ServletLogin extends HttpServlet {

	private static final long serialVersionUID = 1L;

	
	/**
	 * O SERVLET PEGA AS INFORMACOES DA PAGINA HTML
	 */

	// chamando o daoLoginRepository
	DAOLoginRepository daoLoginRepository = new DAOLoginRepository();

	public ServletLogin() {
		super();
	}

	// recebe os dados pela URL em parametros
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String acao = request.getParameter("acao"); //vira pelo parametro da requisicao
		
		if(acao != null && !acao.isEmpty() && acao.equalsIgnoreCase("logout")) {
			request.getSession().invalidate();//invalida a sessao
			
			RequestDispatcher redirecinar = request.getRequestDispatcher("index.jsp");
			redirecinar.forward(request, response);	
		} else {
			doPost(request, response);
		}
	}

	// recebe os dados enviados por um formulario
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String login = request.getParameter("login");
		String senha = request.getParameter("senha");
		String url = request.getParameter("url");

		try {
			if (login != null && !login.isEmpty() && senha != null && !senha.isEmpty()) {// se estiver tudo preenchido
				ModelLogin modelLogin = new ModelLogin();
				modelLogin.setLogin(login);// colocando o login e senha no objeto
				modelLogin.setSenha(senha);

				/**
				 * if(modelLogin.getLogin().equalsIgnoreCase("admin") //se o login e senha for
				 * admin && modelLogin.getSenha().equalsIgnoreCase("admin")
				 */
				if (daoLoginRepository.validarAutenticacao(modelLogin)) {

					request.getSession().setAttribute("usuario", modelLogin.getLogin());// Mantendo o usuario logado

					if (url == null || url.equals("null")) {
						url = "principal/principal.jsp"; // caminho da url padrao
					}

					RequestDispatcher redirecionar = request.getRequestDispatcher(url);
					redirecionar.forward(request, response);// se tudo estiver ok, redireciona para a pagina da url

				} else {
					// se tiver errado, redireciona para a pagina de login
					RequestDispatcher redirecionar = request.getRequestDispatcher("/index.jsp");
					request.setAttribute("msg", "Informe o login e senha novamente");
					redirecionar.forward(request, response);
				}
			} else {
				/*
				 * FIXME Se caso der senha incorreta, temos que 'despachar' ou como o nome ja
				 * diz, redirecionar para outra página/local. Atraves do 'Dispatcher' fazemos o
				 * redirecionamento.
				 */
				RequestDispatcher redirecionar = request.getRequestDispatcher("index.jsp");
				request.setAttribute("msg", "Informe o login e senha novamente");
				redirecionar.forward(request, response);
			}
		} catch (Exception e) {
			e.printStackTrace();
			
			RequestDispatcher redirecinar = request.getRequestDispatcher("erro.jsp");
			request.setAttribute("msg", e.getMessage());
			redirecinar.forward(request, response);
		}
	}
}