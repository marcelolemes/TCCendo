package br.com.login.Dao;

import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import br.com.login.model.User;
import br.com.login.util.HibernateUtil;

public class UserDao {

	public User atualizar(User user) throws Exception {
		Session sessao = HibernateUtil.getSession();
		org.hibernate.Transaction transacao = sessao.beginTransaction();
		Criteria criteria = sessao.createCriteria(User.class);
		criteria.add(Restrictions.eq("apelido", user.getApelido()));
		return (User) criteria.uniqueResult();
	}
	
	
	
	public User testarLogin(User user) throws Exception {
		User resultado;
		Session sessao = HibernateUtil.getSession();
		org.hibernate.Transaction transacao = sessao.beginTransaction();
		Criteria criteria = sessao.createCriteria(User.class);
		criteria.add(Restrictions.eq("apelido", user.getApelido()));
		resultado = (User) criteria.uniqueResult();
		if (resultado != null) {
			if (resultado.getSenha() != null) {
				if (resultado.getSenha().equals(user.getSenha())) {
					sessao.close();

					return resultado;
				} else
					sessao.close();
				return null;

			} else
				sessao.close();
			return null;
		} else
			sessao.close();
		return null;

	}

	public boolean Gravar(User user) throws Exception {

		user.setApelido(user.getApelido().toLowerCase());
		Session sessao = HibernateUtil.getSession();
		org.hibernate.Transaction transacao = sessao.beginTransaction();
		Criteria criteria = sessao.createCriteria(User.class);
		criteria.add(Restrictions.eq("apelido", user.getApelido()));
		List<User> listaRetorno = criteria.list();

		if (user.getApelido().length() <= 3) {

			FacesContext
					.getCurrentInstance()
					.addMessage(
							null,
							new FacesMessage(
									FacesMessage.SEVERITY_FATAL,
									"Cadastro",
									"Não é possível salvar, o apelido é muito curto, insira um nome com ao menos 4 caracteres"));
			sessao.close();
			return false;
		} else {
			if (user.getSenha().length() <= 3) {

				FacesContext
						.getCurrentInstance()
						.addMessage(
								null,
								new FacesMessage(FacesMessage.SEVERITY_FATAL,
										"Cadastro",
										"Não é possível salvar, a senha é muito curta, insira ao menos 4 caracteres"));
				sessao.close();
				return false;
			} else {

				if ((listaRetorno.size() > 0)) {
					System.out.println("Tamanho " + criteria.list().size());

					FacesContext.getCurrentInstance().addMessage(
							null,
							new FacesMessage(FacesMessage.SEVERITY_FATAL,
									"Cadastro",
									"Não é possível salvar, o nome já existe"));
					sessao.close();
					return false;
				} else {
					sessao.saveOrUpdate(user);
					transacao.commit();
					sessao.close();
					return true;
				}
			}
		}
	}
}
