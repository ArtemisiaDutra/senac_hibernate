/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.senac.dao;

import br.com.senac.entidade.Usuario;
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
public class UsuarioDaoImplTest {

    private Usuario usuario;
    private UsuarioDao usuarioDao;
    private Session sessao;

    public UsuarioDaoImplTest() {
        usuarioDao = new UsuarioDaoImpl();
    }

//    @Test
    public void testSalvar() {
        System.out.println("salvar");
        usuario = new Usuario(null, gerarNome(), gerarLogin(), gerarSenha(5));
        sessao = HibernateUtil.abrirConexao();
        usuarioDao.salvarOuAlterar(usuario, sessao);
        sessao.close();
        assertNotNull(usuario.getId());

    }

//    @Test
    public void testAlterar() {
        System.out.println("alterar");
        buscarUsuarioBd();

        usuario.setNome(gerarNome());
        usuario.setLogin(gerarLogin());

        sessao = HibernateUtil.abrirConexao();
        usuarioDao.salvarOuAlterar(usuario, sessao);
        sessao.close();

        sessao = HibernateUtil.abrirConexao();
        Usuario usuarioAlt = usuarioDao.pesquisarPorId(usuario.getId(), sessao);
        sessao.close();
        assertEquals(usuario.getNome(), usuarioAlt.getNome());
        assertEquals(usuario.getLogin(), usuario.getLogin());

    }

//   @Test
    public void testExcluir() {
        System.out.println("excluir");
        buscarUsuarioBd();

        sessao = HibernateUtil.abrirConexao();
        usuarioDao.excluir(usuario, sessao);

        Usuario usuarioExc = usuarioDao.pesquisarPorId(usuario.getId(), sessao);
        sessao.close();
        assertNull(usuarioExc);
    }

//    @Test
    public void testPesquisarPorId() {
        System.out.println("pesquisarPorId");
        buscarUsuarioBd();
        sessao = HibernateUtil.abrirConexao();
        Usuario usuarioPesquisado = usuarioDao.pesquisarPorId(usuario.getId(), sessao);
        sessao.close();
        assertNotNull(usuarioPesquisado);

    }
    
//    @Test
    public void testPesquisarTodo() {
        System.out.println("pesquisarTodo");
        buscarUsuarioBd();
        sessao = HibernateUtil.abrirConexao();
        List<Usuario> usuarios = usuarioDao.pesquisarTodo(sessao);
        sessao.close();
        assertTrue(!usuarios.isEmpty());
       
    }

    @Test
    public void testPesquisarPorNome() {
        System.out.println("pesquisarPorNome");
        buscarUsuarioBd();
        sessao = HibernateUtil.abrirConexao();
        int elemento = usuario.getNome().indexOf("");
        String nome = usuario.getNome().substring(0, elemento);
        List<Usuario> usuarios = usuarioDao.pesquisarPorNome(nome, sessao);
        sessao.close();
        assertTrue(!usuarios.isEmpty());
    }
    //preciso ter alguém no banco de dados  
    public Usuario buscarUsuarioBd() {
        // no sql normal: select * from usuario; nO Hibernate  se quero o usuario já posso colocar 
//        direto from e o nome da classe ele já monta o select *
//       select * from usuario;
        String hql = "from Usuario";
        sessao = HibernateUtil.abrirConexao();
        Query<Usuario> consulta = sessao.createQuery(hql);
        List<Usuario> usuarios = consulta.list();
        sessao.close();
        if (usuarios.isEmpty()) {
            testSalvar();
        } else {
            usuario = usuarios.get(0);// posição 0 do BD ele vai puxar o primeiro
        }
        return usuario;
    }



}
