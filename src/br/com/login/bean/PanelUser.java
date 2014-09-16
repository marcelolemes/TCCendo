package br.com.login.bean;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import br.com.login.Dao.UserDao;

@ManagedBean
@ViewScoped
public class PanelUser implements Serializable {

	/**
	 * 
	 */

	UserDao userDao = new UserDao();
	private static final long serialVersionUID = -8922198411434111521L;

	@ManagedProperty("#{userBean}")
	private UserBean userBean;

	@PostConstruct
	public void init() {

	}

	

	public String btVisualizarCursos() {

		if (userBean.getUser().isLogado()) {
			if (userBean.getUserLogado().getNivelAcesso() < 2) {

				userBean.autoridadeInsuficiente();

				return "/pages/result_index.xhtml";
			} else {

				return "/pages/visualizarcursos_index.xhtml";
			}

		} else {
			userBean.nenhumUsuario();
			return "/pages/login_index.xhtml";

		}

	}

	public String btCadastro() {

		if (userBean.getUser().isLogado()) {
			if (userBean.getUserLogado().getNivelAcesso() < 4) {

				userBean.autoridadeInsuficiente();
				// return "result.xhtml";
				return "/pages/result_index.xhtml";
			} else {

				return "/pages/cadastro_index.xhtml";
			}

		} else {
			userBean.nenhumUsuario();
			return "/pages/login_index.xhtml";

		}

	}

	public String btCadastrarCursos() {

		if (userBean.getUser().isLogado()) {
			if (userBean.getUserLogado().getNivelAcesso() < 0) {

				userBean.autoridadeInsuficiente();
				return "/pages/result_index.xhtml";
			} else {

				return "/pages/cadastrarcursos_index.xhtml";
			}

		} else {
			userBean.nenhumUsuario();
			return "/pages/login_index.xhtml";

		}

	}

	public String btListarUsers() {

		if (userBean.getUser().isLogado()) {
			if (userBean.getUserLogado().getNivelAcesso() < 3) {

				userBean.autoridadeInsuficiente();
				// return "result.xhtml";
				return "/pages/result_index.xhtml";
			} else {

				return "/pages/usuarios_cadastrados_index.xhtml";
			}

		} else {
			userBean.nenhumUsuario();
			return "/pages/login_index.xhtml";

		}

	}

	public void fecharSessao() {
		// remover sess�o do manage bean selecionado
		// FacesContext.getCurrentInstance().getExternalContext().getSessionMap().remove("userBean");
		/*
		 * userBean.setUserLogado(null); userBean.setLogado(false);
		 */
		userBean.setLogado(false);
		FacesContext context = FacesContext.getCurrentInstance();
		context.getExternalContext().invalidateSession();
	}

	public String sairSessao() throws Exception {

		if (userBean.getUser().isLogado()) {
			try {
				userBean.setLogado(false);
				
				userDao.gravarTimestamp(userBean.getUserLogado());

				/*
				 * userBean.setUserLogado(null);
				 */
				

				// testando
				FacesContext context = FacesContext.getCurrentInstance();
				context.getExternalContext().invalidateSession();

				// remover sess�o do manage bean selecionado
				/*
				 * FacesContext.getCurrentInstance().getExternalContext()
				 * .getSessionMap().remove("userBean");
				 */
				FacesContext.getCurrentInstance().addMessage(
						null,
						new FacesMessage(FacesMessage.SEVERITY_INFO, "Logoff",
								"Sess�o encerrada"));

			} catch (Exception ex) {
				// TODO: handle exception
			}

			return "/pages/login_index.xhtml";
		} else {

			userBean.nenhumUsuario();
			return "/pages/login_index.xhtml";
		}

	}

	public UserBean getUserBean() {
		return userBean;
	}

	public void setUserBean(UserBean userBean) {
		this.userBean = userBean;
	}

}
