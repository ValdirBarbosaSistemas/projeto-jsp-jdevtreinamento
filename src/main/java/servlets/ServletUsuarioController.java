package servlets;

import java.io.IOException;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;

import dao.DAOUsuarioRepository;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.ModelLogin;

@WebServlet("/ServletUsuarioController")
public class ServletUsuarioController extends HttpServlet {

	DAOUsuarioRepository daoUsuarioRepository = new DAOUsuarioRepository();

	private static final long serialVersionUID = 1L;

	public ServletUsuarioController() {
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		/*
		 * OBS 1 => transacoes para efetuar CONSULTA e DELETE se faz com o metodo GET,
		 * pois com o DOPOST e feito para ATUALIZAR e SALVAR. OBS 2 => Quando nao
		 * colocamos um tipo no botao, ele automaticamente vai para o post que e o
		 * padrao, e como o delete e um GET, temos que colocar o tipo 'button' no HTML
		 * para que o mesmo seja enviado como GET ao inves do POST.
		 */
		try {
			String acao = request.getParameter("acao");

			if (acao != null && !acao.isEmpty() && acao.equalsIgnoreCase("delete")) {
				String idUsuario = request.getParameter("id");

				daoUsuarioRepository.deletarUsuario(idUsuario);
				
				//metodo que serve para mostrar todos os usuarios listados
				List<ModelLogin> modelLogins = daoUsuarioRepository.consultaUsuarioList();
				request.setAttribute("modelLogins", modelLogins);
				
				request.setAttribute("msg", "Usuario excluído com sucesso");
				request.getRequestDispatcher("principal/usuario.jsp").forward(request, response);
				
			} else if (acao != null && !acao.isEmpty() && acao.equalsIgnoreCase("deletarAjax")) {

	 			String idUsuario = request.getParameter("id");

				daoUsuarioRepository.deletarUsuario(idUsuario);
				response.getWriter().write("Usuario excluído com sucesso!");//Quando se trabalha com ajax, e dessa forma que e enviada a resposta
				
			} else if (acao != null && !acao.isEmpty() && acao.equalsIgnoreCase("buscarUserAjax")) {

	 			String nomeBuscaUsuario = request.getParameter("nomeBusca");

				List<ModelLogin> dadosJsonUser = daoUsuarioRepository.consultaUsuarioList(nomeBuscaUsuario);
				ObjectMapper mapper = new ObjectMapper();
				String json = mapper.writeValueAsString(dadosJsonUser);//pegando o resultado da query e transformando para json
				
				response.getWriter().write(json);//enviando o resultado para o ajax
				
			} else if (acao != null && !acao.isEmpty() && acao.equalsIgnoreCase("buscarEditar")) {
				String id = request.getParameter("id");
				
				ModelLogin modelLogin = daoUsuarioRepository.consultarUsuarioID(id);
				
				//metodo que serve para mostrar todos os usuarios listados
				List<ModelLogin> modelLogins = daoUsuarioRepository.consultaUsuarioList();
				request.setAttribute("modelLogins", modelLogins);
				
				request.setAttribute("msg", "Usuário em Edição");
				request.setAttribute("modelLogin", modelLogin);
				
				request.getRequestDispatcher("principal/usuario.jsp").forward(request, response);
				
			} else if (acao != null && !acao.isEmpty() && acao.equalsIgnoreCase("listarUsuario")) {
				List<ModelLogin> modelLogins = daoUsuarioRepository.consultaUsuarioList();
				
				request.setAttribute("msg", "Usuários Carregados");
				request.setAttribute("modelLogins", modelLogins);
				
				request.getRequestDispatcher("principal/usuario.jsp").forward(request, response);
			}
			
			else {
				
				//metodo que serve para listar todos os usuarios cadastrados
				List<ModelLogin> modelLogins = daoUsuarioRepository.consultaUsuarioList();
				request.setAttribute("modelLogins", modelLogins);
				
				request.getRequestDispatcher("principal/usuario.jsp").forward(request, response);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			RequestDispatcher redireciona = request.getRequestDispatcher("erro.jsp");
			request.setAttribute("msg", e.getMessage());
			redireciona.forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		try {

			String msg = "Operação realizada com sucesso";

			String id = request.getParameter("id");
			String nome = request.getParameter("nome");
			String email = request.getParameter("email");
			String login = request.getParameter("login");
			String senha = request.getParameter("senha");

			ModelLogin modelLogin = new ModelLogin();
			modelLogin.setId(id != null && !id.isEmpty() ? Long.parseLong(id) : null);
			// se o id estiver preenchido, faz o parse, caso nao, deixar como null

			modelLogin.setNome(nome);
			modelLogin.setEmail(email);
			modelLogin.setLogin(login);
			modelLogin.setSenha(senha);

			if (daoUsuarioRepository.validarLogin(modelLogin.getLogin()) && modelLogin.getId() == null) {
				msg = "Já existe um usuário com o mesmo login, favor informar outro nome para o login";
			} else {

				if (modelLogin.isNovo()) {
					msg = "Usuario gravado com sucesso!";
				} else {
					msg = "Dados atualizados com sucesso!";
				}

				modelLogin = daoUsuarioRepository.gravarUsuario(modelLogin);
			}

			//metodo que serve para mostrar todos os usuarios cadastrados
			List<ModelLogin> modelLogins = daoUsuarioRepository.consultaUsuarioList();
			request.setAttribute("modelLogins", modelLogins);
			
			RequestDispatcher redirecionar = request.getRequestDispatcher("principal/usuario.jsp");
			request.setAttribute("modelLogin", modelLogin);// mostrando os dados populados
			request.setAttribute("msg", msg);
			redirecionar.forward(request, response);

		} catch (Exception e) {
			e.printStackTrace();
			RequestDispatcher redireciona = request.getRequestDispatcher("erro.jsp");
			request.setAttribute("msg", e.getMessage());
			redireciona.forward(request, response);
		}
	}
}
