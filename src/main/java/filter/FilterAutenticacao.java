package filter;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import connection.SingleConnectionBanco;
import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@WebFilter(urlPatterns = {"/principal/*"}) // Intercepta todas as requisicoes que vierem do projeto ou mapeamento
public class FilterAutenticacao extends HttpFilter implements Filter {

	private static final long serialVersionUID = 1L;

	/**
	 * @see Qualquer acesso que voce entrar, qualquer autenticacao que tiver, tudo o
	 *      que vier do sistema, qualquer tela que clicar vai entrar aqui no filter;
	 */

	/**
	 * O FILTER PEGA AS INFORMACOES DO SERVLET
	 */

	private static Connection connection;// chamando o banco criado

	public FilterAutenticacao() {
		super();
	}

	// Encerra os processos quando o servidor e parado
	public void destroy() {
		try {
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	// Intercepta todas as requisicoes do projeto e da as respostas no sistema
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		try {
			/*
			 * Para podermos pegar a sessao do usuario temos que utilizar o
			 * HttpServletRequest
			 */
			HttpServletRequest req = (HttpServletRequest) request;
			HttpSession session = req.getSession(); // pegando a sessao do usuario que esta logado

			String usuarioLogado = (String) session.getAttribute("usuario");
			String urlParaAutenticar = req.getServletPath();// pegando a url que esta sendo acessada

			// Validar se esta logado senao redireciona para a tela de login

			if (usuarioLogado == null && !urlParaAutenticar.equalsIgnoreCase("/principal/ServletLogin")) {// Nao esta
																											// logado
				RequestDispatcher redirecionar = request.getRequestDispatcher("/index.jsp?url=" + urlParaAutenticar);
				request.setAttribute("msg", "Por favor realize o login");
				redirecionar.forward(request, response);
				return;// Para a execucao e redireciona para o login
			} else {
				// Deixa o processo rodar normalmente
				chain.doFilter(request, response);
			}

			// deu tudo certo, entao envia os dados pra o banco de dados
			connection.commit();

		} catch (Exception e) {
			e.printStackTrace();
			try {
				connection.rollback(); // caso der errado, nao envia os dados ao banco
			} catch (SQLException e1) {
				e1.printStackTrace();
				
				RequestDispatcher redirecionar = request.getRequestDispatcher("erro.jsp");
				request.setAttribute("msg", e1.getMessage());
				redirecionar.forward(request, response);
			}
		}
	}

	/*
	 * Inicia os processos ou recursos quando o servidor sobe o projeto (inicia a
	 * conexao com o banco)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		connection = SingleConnectionBanco.getConnection();
	}
}
