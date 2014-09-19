package br.com.login.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.model.SelectItem;
import javax.print.attribute.standard.Severity;

public class Metricas implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6233474203192271864L;
	private List<SelectItem> nivelAcesso = new ArrayList<SelectItem>();
	private List<SelectItem> urgenciaLista = new ArrayList<SelectItem>();
	private List<SelectItem> statusContratoLista = new ArrayList<SelectItem>();
	private List<SelectItem> setores = new ArrayList<SelectItem>();

	public Metricas() {

		inicializarNivelAcesso();
		inicializarUrgenciaLista();
		iniciarStatusLista();
		inicializarSetores();
	}

	public void inicializarNivelAcesso() {
		nivelAcesso.add(new SelectItem(0, "Patr�o"));
		nivelAcesso.add(new SelectItem(1, "Usu�rio J�nior"));
		nivelAcesso.add(new SelectItem(2, "Usu�rio Comum"));
		nivelAcesso.add(new SelectItem(3, "Usu�rio Avan�ado"));
		nivelAcesso.add(new SelectItem(4, "Gestor"));
		nivelAcesso.add(new SelectItem(5, "SysAdmin"));
	}
	public void inicializarSetores() {
		setores.add(new SelectItem(0, "Cadastro"));
		setores.add(new SelectItem(1, "Corre��o de cor"));
		setores.add(new SelectItem(2, "Tratamento de pele"));
		setores.add(new SelectItem(3, "Montagem"));
		setores.add(new SelectItem(4, "Impress�o"));
		setores.add(new SelectItem(5, "Backup"));
		
	}

	public void inicializarUrgenciaLista() {
		urgenciaLista.add(new SelectItem(0, "Fazendo"));
		urgenciaLista.add(new SelectItem(1, "Urgent�ssimo"));
		urgenciaLista.add(new SelectItem(2, "Muito Urgente"));
		urgenciaLista.add(new SelectItem(3, "Urgente"));
		urgenciaLista.add(new SelectItem(4, "Normal"));
		urgenciaLista.add(new SelectItem(5, "Baixa"));
		urgenciaLista.add(new SelectItem(6, "Manter Parado"));

	}

	public void iniciarStatusLista() {

		statusContratoLista.add(new SelectItem(0, "Cadastrado"));
		statusContratoLista.add(new SelectItem(1, "Renomeado"));
		statusContratoLista.add(new SelectItem(2, "Separando"));
		statusContratoLista.add(new SelectItem(3, "Parado na Cor"));
		statusContratoLista.add(new SelectItem(4, "Em Corre��o de Cor"));
		statusContratoLista.add(new SelectItem(5, "Pronto Cor"));
		statusContratoLista.add(new SelectItem(6, "Cor/Tratamento"));
		statusContratoLista.add(new SelectItem(7, "Parado tratamento de pele"));
		statusContratoLista.add(new SelectItem(8, "Em tratamento de pele"));
		statusContratoLista.add(new SelectItem(9, "Em tratamento Terceirizado"));
		statusContratoLista.add(new SelectItem(10, "Tratamento/Montagem"));
		statusContratoLista.add(new SelectItem(11, "Tratamento Pronto"));
		statusContratoLista.add(new SelectItem(12, "Parado Montagem"));
		statusContratoLista.add(new SelectItem(13, "Em Montagem"));
		statusContratoLista.add(new SelectItem(14, "Pronto Montagem"));
		statusContratoLista.add(new SelectItem(15, "Em impress�o"));
		statusContratoLista.add(new SelectItem(16, "Aguardando Backup"));
		statusContratoLista.add(new SelectItem(17, "Em backup"));
		statusContratoLista.add(new SelectItem(18, "Backup Pronto"));
	}

	public List<SelectItem> getNivelAcesso() {
		return nivelAcesso;
	}

	public void setNivelAcesso(List<SelectItem> nivelAcesso) {
		this.nivelAcesso = nivelAcesso;
	}

	public List<SelectItem> getUrgenciaLista() {
		return urgenciaLista;
	}

	public void setUrgenciaLista(List<SelectItem> urgenciaLista) {
		this.urgenciaLista = urgenciaLista;
	}

	public List<SelectItem> getStatusContratoLista() {
		return statusContratoLista;
	}

	public void setStatusContratoLista(List<SelectItem> statusContratoLista) {
		this.statusContratoLista = statusContratoLista;
	}

	public List<SelectItem> getSetores() {
		return setores;
	}

	public void setSetores(List<SelectItem> setores) {
		this.setores = setores;
	}

}
