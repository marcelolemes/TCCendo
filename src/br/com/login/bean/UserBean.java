package br.com.login.bean;

import java.io.Serializable;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;

import br.com.login.Dao.UserDao;
import br.com.login.model.Metricas;
import br.com.login.model.User;

@ManagedBean(name = "userBean", eager = true)
@SessionScoped
public class UserBean implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<SelectItem> nivelAcessoCadastro = new Metricas()
			.getNivelAcesso();

	public UserBean() {

		user = new User();
		user.setLogado(false);
		
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

	public String logar() throws Exception {
		if (user.isLogado()) {
			loginAtivo();
						
			return "/pages/result_index.xhtml";

		} else {
			userDao = new UserDao();
			if ((userLogado = userDao.testarLogin(user)) != null) {
				System.out.print(" Encontrado ");
				setSessao(user.getApelido());
				user = new User();
				user.setLogado(true);
				return messageSucessoLogin();

			} else {
				System.out.print("N�o encontrado");
				user = new User();
				user.setLogado(false);
				messageErroLogin();
			}
			return null;
		}
	}
	public String verificarLogado() throws Exception {

		if (user.isLogado() /*&& (userBean.getUserLogado() != null)*/) {

			return "/pages/result_index.xhtml";
			
		} else {
			return "/pages/login_index.xhtml";
		}
	}
	
	public String btHome() throws Exception {

		if (user.isLogado() /*&& (userBean.getUserLogado() != null)*/) {

			return "/pages/result_index.xhtml";
			
		} else {
			return "/pages/login_index.xhtml";
		}
	}
	
	public String verificarAutoridadeLoginVisualizarCursos() throws Exception {

		if (getUserLogado().getNivelAcesso() < 2) {

			return "/pages/visualizarcursos_index.xhtml";
			
		} else {
			return "/pages/result_index.xhtml";
		}
	}
	
	
	public String gravar() {
		userDao = new UserDao();
		try {
			if (userDao.Gravar(user)) {
				messageSucessoGravar();
				user = new User();
				user.setLogado(true);
			}
			return "/pages/result_index.xhtml";

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
						"Sess�o ainda ativa para o usu�rio:  " + sessao));
		// return "result.xhtml";
		return "/pages/result_index.xhtml";

	}
	
	public String reiniciarsess�o() {
		FacesContext.getCurrentInstance().addMessage(
				null,
				new FacesMessage(FacesMessage.SEVERITY_WARN, "Login Ativo",
						"Sess�o ativa reiniciada:  " + sessao));
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

	public void messageErroLogin() {
		FacesContext
				.getCurrentInstance()
				.addMessage(
						null,
						new FacesMessage(FacesMessage.SEVERITY_ERROR, "Login",
								"Usu�rio/Senha incorretos, por favor, tente novamente"));
		// remover sess�o do manage bean selecionado
//		FacesContext.getCurrentInstance().getExternalContext().getSessionMap()
//				.remove("userBean");
	}

	public void nenhumUsuario() {
		FacesContext
				.getCurrentInstance()
				.addMessage(
						null,
						new FacesMessage(FacesMessage.SEVERITY_FATAL, "Erro",
								"Nenhum usu�rio logado, para realizar alguma opera��o, efetue seu login"));

	}

	public void autoridadeInsuficiente() {
		FacesContext
				.getCurrentInstance()
				.addMessage(
						null,
						new FacesMessage(FacesMessage.SEVERITY_WARN, userLogado
								.getApelido(),
								"Seu acesso � esta fun��o n�o � permitida, acesso negado!"));

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
		UserBean.logado = logado;
	}

	public List<SelectItem> getNivelAcessoCadastro() {
		return nivelAcessoCadastro;
	}

	public void setNivelAcessoCadastro(List<SelectItem> nivelAcessoCadastro) {
		this.nivelAcessoCadastro = nivelAcessoCadastro;
	}

}
