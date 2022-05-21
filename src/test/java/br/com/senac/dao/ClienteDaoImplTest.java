/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.senac.dao;

import br.com.senac.entidade.Cliente;
import static br.com.senac.util.GeradorUtil.*;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Artemisia Dutra
 */
public class ClienteDaoImplTest {

    private Cliente cliente;
    private ClienteDao clienteDao;
    private Session sessao;

    public ClienteDaoImplTest() {
        clienteDao = new ClienteDaoImpl();
    }

    @Test
    public void testSalvar() {
        System.out.println("salvar");
        cliente = new Cliente(null, gerarNome(), gerarEmail(), gerarCpf(), gerarRg());
        sessao = HibernateUtil.abrirConexao();
        clienteDao.salvarOuAlterar(cliente, sessao);
        sessao.close();
        assertNotNull(cliente.getId());

    }

    @Test
    public void testAlterar() {
        System.out.println("alterar");
        buscarClienteBd();

        cliente.setNome(gerarNome());
        cliente.setEmail(gerarEmail());

        sessao = HibernateUtil.abrirConexao();
        clienteDao.salvarOuAlterar(cliente, sessao);
        sessao.close();

        sessao = HibernateUtil.abrirConexao();
        Cliente clienteAlt = clienteDao.pesquisarPorId(cliente.getId(), sessao);
        sessao.close();
        assertEquals(cliente.getNome(), clienteAlt.getNome());
        assertEquals(cliente.getEmail(), clienteAlt.getEmail());

    }

    @Test
    public void testExcluir() {
        System.out.println("excluir");
        buscarClienteBd();

        sessao = HibernateUtil.abrirConexao();
        clienteDao.excluir(cliente, sessao);

        Cliente clienteExc = clienteDao.pesquisarPorId(cliente.getId(), sessao);
        sessao.close();
        assertNull(clienteExc);
    }

   @Test
    public void testPesquisarPorId() {
        System.out.println("pesquisarPorId");
        buscarClienteBd();
        sessao = HibernateUtil.abrirConexao();
        Cliente clientePesquisado = clienteDao.pesquisarPorId(cliente.getId(), sessao);
        sessao.close();
        assertNotNull(clientePesquisado);

    }
    //preciso ter alguém no banco de dados

    public Cliente buscarClienteBd() {
        // no sql normal: select * from cliente; nO Hibernate  se quero o cliente já posso colocar 
//        direto from e o nome da classe ele já monta o select *

        String hql = "from Cliente";
        sessao = HibernateUtil.abrirConexao();
        Query<Cliente> consulta = sessao.createQuery(hql);
        List<Cliente> clientes = consulta.list();
        sessao.close();
        if (clientes.isEmpty()) {
            testSalvar();
        } else {
            cliente = clientes.get(0);// posição 0 do BD ele vai puxar o primeiro
        }
        return cliente;
    }

    @Test
    public void testPesquisarTodo() {
        System.out.println("pesquisarTodo");
        buscarClienteBd();
        sessao = HibernateUtil.abrirConexao();
        List<Cliente> usuarios = clienteDao.pesquisarTodo(sessao);
        sessao.close();
        assertTrue(!usuarios.isEmpty());
       
    }




}
