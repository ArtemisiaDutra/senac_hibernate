/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.senac.dao;

import org.hibernate.HibernateException;
import org.hibernate.Session;

/**
 *
 * @author Artemisia Dutra
 */
public interface BaseDao<T, ID>  {// o ID representa o tipo da chave primaria
    
//    por defaut, antes do void tem public abtract: public abtract void salarOuAlterar
    
    void salvarOuAlterar(T entidade, Session sessao)throws HibernateException;
//    se tiver id ele altera, se nçao tiver ele salva
    
    void excluir(T entidade, Session sessao)throws HibernateException;
    
    T pesquisarPorId(ID id, Session sessao)throws HibernateException;
    
}
