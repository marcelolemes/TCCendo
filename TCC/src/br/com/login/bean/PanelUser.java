package br.com.login.bean;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;

@ManagedBean
public class PanelUser {
	
	@ManagedProperty("#{userBean}")
	private UserBean userBean;
	
	private String sessaoAtiva;
	//private String sessaoAtiva;
	
	
	@PostConstruct
    public void init() {
        sessaoAtiva= userBean.getSessao();
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
