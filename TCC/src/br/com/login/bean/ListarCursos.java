package br.com.login.bean;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.login.Dao.ContratoDao;
import br.com.login.model.Contrato;
import br.com.login.model.Metricas;

@ManagedBean
@ViewScoped
public class ListarCursos implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 
	 */

	ContratoDao contDao = new ContratoDao();
	private Metricas metricas = new Metricas();
	private List<Contrato> listaContrato;
	private List<String> urgencias;

	public ListarCursos() throws Exception {

		listaContrato = contDao.listarContratos();
	}

	public String atualizar() throws Exception {
		try {
			listaContrato.clear();
			contDao.listarContratos().clear();
		} catch (Exception ex) {
		}
		listaContrato = contDao.listarContratos();

		return "/pages/conteudo/visualizarcursos.xhtml";
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

	public List<Contrato> getListaContrato() {
		return listaContrato;
	}

	public void setListaContrato(List<Contrato> listaContrato) {
		this.listaContrato = listaContrato;
	}

	public List<String> getUrgencias() {
		return urgencias;
	}

	public void setUrgencias(List<String> urgencias) {
		this.urgencias = urgencias;
	}

}
