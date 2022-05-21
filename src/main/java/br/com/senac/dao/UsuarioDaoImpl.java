/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.senac.dao;
import br.com.senac.entidade.Usuario;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.query.Query;

/**
 *
 * @author Artemisia Dutra
 */
public class UsuarioDaoImpl extends BaseDaoImpl<Usuario, Long> implements UsuarioDao{

    @Override
    public Usuario pesquisarPorId(Long id, Session sessao) throws HibernateException {
//        Usuario usuario = sessao.get(Usuario.class, id);
//        return null;
    return sessao.get(Usuario.class, id);
    }

    @Override
    public List<Usuario> pesquisarTodo(Session sessao) throws HibernateException {

        Query<Usuario> consulta = sessao.createQuery("Select u from Usuario u");
        return consulta.getResultList();
    }

    @Override
    public List<Usuario> pesquisarPorNome(String nome, Session sessao) throws HibernateException {
        Query<Usuario> consulta = sessao.createQuery("from Usuario where nome  like :nomeHql");
        consulta.setParameter("nomeHql", "%" + nome + "%");
        return consulta.getResultList();
    }
    
}
