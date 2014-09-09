package br.com.login.bean;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

@ManagedBean
@ViewScoped
public class PanelUser implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8922198411434111521L;

	@ManagedProperty("#{userBean}")
	private UserBean userBean;

	private String sessaoAtiva;

	@PostConstruct
	public void init() {

	}

	public UserBean getUserBean() {
		return userBean;
	}

	public void setUserBean(UserBean userBean) {
		this.userBean = userBean;
	}

	public String getSessaoAtiva() {
		return sessaoAtiva;
	}

	public void setSessaoAtiva(String sessaoAtiva) {
		this.sessaoAtiva = sessaoAtiva;
	}

}
