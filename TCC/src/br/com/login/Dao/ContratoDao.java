package br.com.login.Dao;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ViewScoped;

import org.hibernate.Criteria;
import org.hibernate.Session;

import br.com.login.model.Contrato;
import br.com.login.util.HibernateUtil;

import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;

@ViewScoped
public class ContratoDao implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public boolean Gravar(Contrato contrato) throws Exception {
		Session sessao = HibernateUtil.getSession();
		org.hibernate.Transaction transacao = sessao.beginTransaction();
		sessao.saveOrUpdate(contrato);
		transacao.commit();
		sessao.close();
		return true;

	}

	public boolean Update(Contrato contrato) throws Exception {
		Session sessao = HibernateUtil.getSession();
		org.hibernate.Transaction transacao = sessao.beginTransaction();
		try {
			sessao.update(contrato);
			transacao.commit();
			sessao.close();
			return true;
		} catch (Exception ex) {
			sessao.close();
			return false;
		}

	}

	public List<Contrato> listarContratos() throws Exception {
		Session sessao = HibernateUtil.getSession();
		Criteria criteria = sessao.createCriteria(Contrato.class);
		List<Contrato> listaRetorno = criteria.list();
		sessao.close();
		return listaRetorno;

	}

	public Contrato pesquisarContrato(String numeroContrato) throws Exception {
		Session sessao = HibernateUtil.getSession();
		Criteria criteria = sessao.createCriteria(Contrato.class);
		criteria.add(Restrictions.like("numeroContrato", numeroContrato,
				MatchMode.ANYWHERE));
		Contrato Retorno = (Contrato) criteria.uniqueResult();
		sessao.close();
		return Retorno;

	}

}
