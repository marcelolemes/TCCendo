package br.com.login.bean;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import br.com.login.Dao.UserDao;
import br.com.login.model.User;

@ManagedBean(name = "userBean", eager = true)
@SessionScoped
public class UserBean {

	public UserBean() {

		user = new User();

	}

	private User user;
	private User userLogado;
	UserDao userDao;
	private String sessao = new String();

	private static boolean logado = false;

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String atualizar() throws Exception {

		userLogado = userDao.atualizar(userLogado);
		// return "result.xhtml";
		return "/pages/result_index.xhtml";
	}

	public String verificarLogado() throws Exception {

		if (logado) {

			return "/pages/result_index.xhtml";
		} else {
			return "/pages/login_index.xhtml";
		}
	}

	public String logar() throws Exception {
		if (logado) {
			loginAtivo();
			return "/pages/result_index.xhtml";

		} else {
			userDao = new UserDao();
			if ((userLogado = userDao.testarLogin(user)) != null) {
				System.out.print(" Encontrado ");
				setSessao(user.getApelido());
				logado = true;
				return messageSucessoLogin();

			} else {
				System.out.print("Não encontrado");
				logado = false;
				messageErroLogin();
			}
			return null;
		}
	}

	public String gravar() {
		userDao = new UserDao();
		try {
			if (userDao.Gravar(user)) {
				messageSucessoGravar();
			}
			return sairSessao();

		} catch (Exception ex) {
			messageErroCadastro();
			ex.printStackTrace();
			ex.getMessage();
			return null;
		}

	}

	public String messageSucessoLogin() {
		FacesContext.getCurrentInstance().addMessage(
				null,
				new FacesMessage(FacesMessage.SEVERITY_INFO, "Login",
						"Seja bem vindo " + sessao));
		// return "result.xhtml";
		return "/pages/result_index.xhtml";

	}

	public String loginAtivo() {
		FacesContext.getCurrentInstance().addMessage(
				null,
				new FacesMessage(FacesMessage.SEVERITY_WARN, "Login Ativo",
						"Sessão ainda ativa para o usuário:  " + sessao));
		// return "result.xhtml";
		return "/pages/result_index.xhtml";

	}

	public void messageSucessoGravar() {
		FacesContext.getCurrentInstance().addMessage(
				null,
				new FacesMessage(FacesMessage.SEVERITY_INFO, "Gravar",
						"Cadastro realizado com sucesso, Seja bem vindo "
								+ sessao));

	}

	public void fecharSessao() {
		// remover sessão do manage bean selecionado
		FacesContext.getCurrentInstance().getExternalContext().getSessionMap()
				.remove("userBean");
		logado = false;

	}

	public String sairSessao() throws Exception {

		if (logado) {
			try {
				logado = false;
				// remover sessão do manage bean selecionado
				FacesContext.getCurrentInstance().getExternalContext()
						.getSessionMap().remove("userBean");
				userDao.gravarTimestamp(userLogado);

			} catch (Exception ex) {
				// TODO: handle exception
			}

			return "/pages/login_index.xhtml";
		} else {

			nenhumUsuario();
			return "/pages/login_index.xhtml";
		}

	}

	public String btCadastro() {

		if (logado) {
			if (userLogado.getNivelAcesso() < 3) {

				autoridadeInsuficiente();
				// return "result.xhtml";
				return "/pages/result_index.xhtml";
			} else {

				return "/pages/cadastro_index.xhtml";
			}

		} else {
			nenhumUsuario();
			return "/pages/login_index.xhtml";

		}

	}

	public void messageErroLogin() {
		FacesContext
				.getCurrentInstance()
				.addMessage(
						null,
						new FacesMessage(FacesMessage.SEVERITY_ERROR, "Login",
								"Usuário/Senha incorretos, por favor, tente novamente"));
		// remover sessão do manage bean selecionado
		FacesContext.getCurrentInstance().getExternalContext().getSessionMap()
				.remove("userBean");
	}

	public void nenhumUsuario() {
		FacesContext
				.getCurrentInstance()
				.addMessage(
						null,
						new FacesMessage(FacesMessage.SEVERITY_FATAL, "Erro",
								"Nenhum usuário logado, para realizar alguma operação, efetue seu login"));

	}

	public void autoridadeInsuficiente() {
		FacesContext
				.getCurrentInstance()
				.addMessage(
						null,
						new FacesMessage(FacesMessage.SEVERITY_WARN, userLogado
								.getApelido(),
								"Seu acesso à esta função não é permitida, acesso negado!"));

	}

	public void messageErroCadastro() {
		FacesContext.getCurrentInstance().addMessage(
				null,
				new FacesMessage(FacesMessage.SEVERITY_ERROR, "Cadastro",
						"O Cadastro falhou, por favor, tente novamente"));
	}

	public String getSessao() {
		return sessao;
	}

	public void setSessao(String sessao) {
		this.sessao = sessao;
	}

	public User getUserLogado() {
		return userLogado;
	}

	public void setUserLogado(User userLogado) {
		this.userLogado = userLogado;
	}

	public boolean isLogado() {
		return logado;
	}

	public void setLogado(boolean logado) {
		this.logado = logado;
	}

}
