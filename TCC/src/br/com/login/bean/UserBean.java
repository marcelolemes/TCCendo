package br.com.login.bean;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import br.com.login.Dao.UserDao;
import br.com.login.model.User;

@ManagedBean(name = "userBean")
@SessionScoped
public class UserBean {

	public UserBean() {

		user = new User();

	}

	private User user;
	private User userLogado;

	private String sessao = new String();

	private String teste = new String();

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String atualizar() throws Exception {
		UserDao userDao = new UserDao();
		userLogado = userDao.atualizar(userLogado);
		return "result.xhtml";
	}

	public String logar() throws Exception {
		UserDao userDao = new UserDao();

		if ((userLogado = userDao.testarLogin(user)) != null) {
			System.out.print("Encontrado");
			setSessao(user.getApelido());
			sessao = user.getApelido();

			return messageSucessoLogin();

		} else {
			System.out.print("N�o encontrado");
			messageErroLogin();
		}
		return null;
	}

	public String gravar() {
		UserDao userDao = new UserDao();
		try {
			if (userDao.Gravar(user)) {
				messageSucessoGravar();
			}
			return sairSessao();

		} catch (Exception ex) {
			messageErroLogin();
			return null;
		}

	}

	public String messageSucessoLogin() {
		FacesContext.getCurrentInstance().addMessage(
				null,
				new FacesMessage(FacesMessage.SEVERITY_INFO, "Login",
						"Seja bem vindo " + sessao));
		return "result.xhtml";

	}

	public void messageSucessoGravar() {
		FacesContext.getCurrentInstance().addMessage(
				null,
				new FacesMessage(FacesMessage.SEVERITY_INFO, "Gravar",
						"Cadastro realizado com sucesso, Seja bem vindo "
								+ sessao));

	}

	public void fecharSessao() {
		// remover sess�o do manage bean selecionado
		FacesContext.getCurrentInstance().getExternalContext().getSessionMap()
				.remove("userBean");

	}

	public String sairSessao() {
		// remover sess�o do manage bean selecionado
		FacesContext.getCurrentInstance().getExternalContext().getSessionMap()
				.remove("userBean");
		return "login_index.xhtml";

	}

	public void messageErroLogin() {
		FacesContext
				.getCurrentInstance()
				.addMessage(
						null,
						new FacesMessage(FacesMessage.SEVERITY_ERROR, "Login",
								"Usu�rio/Senha incorretos, por favor, tente novamente"));
		// remover sess�o do manage bean selecionado
		FacesContext.getCurrentInstance().getExternalContext().getSessionMap()
				.remove("userBean");
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

	public String getTeste() {
		return teste;
	}

	public void setTeste(String teste) {
		this.teste = teste;
	}

	public User getUserLogado() {
		return userLogado;
	}

	public void setUserLogado(User userLogado) {
		this.userLogado = userLogado;
	}

}
