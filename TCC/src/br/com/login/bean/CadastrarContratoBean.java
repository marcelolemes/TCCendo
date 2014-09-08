package br.com.login.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import br.com.login.Dao.ContratoDao;
import br.com.login.model.Contrato;
import br.com.login.model.Metricas;

@ManagedBean
@ViewScoped
public class CadastrarContratoBean implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 
	 */

	ContratoDao contDao = new ContratoDao();
	private Contrato contratoCadastro = new Contrato();
	private Metricas metricas = new Metricas();
	
	

	public void gravarContrato() throws Exception {
		if (contDao.Gravar(contratoCadastro)) {
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_INFO, "Gravar",
							"Gravado com sucesso"));
		} else {
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_FATAL, "Gravar",
							"Falha ao gravar!!!"));
		}

	}

	
	public Contrato getContratoCadastro() {
		return contratoCadastro;
	}

	public void setContratoCadastro(Contrato contratoCadastro) {
		this.contratoCadastro = contratoCadastro;
	}

	public ContratoDao getContDao() {
		return contDao;
	}

	public void setContDao(ContratoDao contDao) {
		this.contDao = contDao;
	}

	public Metricas getMetricas() {
		return metricas;
	}

	public void setMetricas(Metricas metricas) {
		this.metricas = metricas;
	}

	
}
