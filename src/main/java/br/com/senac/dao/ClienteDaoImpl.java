/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.senac.dao;

import br.com.senac.entidade.Cliente;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.query.Query;

/**
 *
 * @author Artemisia Dutra
 */
public class ClienteDaoImpl extends BaseDaoImpl<Cliente, Long> implements ClienteDao{

    @Override
    public Cliente pesquisarPorId(Long id, Session sessao) throws HibernateException {
   //     Cliente cliente = sessao.get(Cliente.class, id);
    //    return null;
    return sessao.get(Cliente.class, id);
    }

    @Override
    public List<Cliente> pesquisarTodo(Session sessao) throws HibernateException {
//        Query<Cliente> consulta = sessao.createQuery("from Cliente c");
        Query<Cliente> consulta = sessao.createQuery("Select c from Cliente c");
        
        return consulta.getResultList();
    }



 
    
}
