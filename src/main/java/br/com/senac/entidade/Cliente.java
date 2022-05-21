/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.senac.entidade;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;
import javax.persistence.*;

/**
 *
 * @author Artemisia Dutra
 */
@Entity
@Table(name = "cliente")
public class Cliente implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false,length = 120)
    private String nome;
    
    @Column(nullable = false,length = 120, unique = true)
    private String email;
    
    @Column(nullable = false,length = 100)
    private String cpf;
    
    @Column(nullable = false,length = 100)
    private String rg;
    
    private LocalDate ultimoAcesso;

    public Cliente() {
    }

    public Cliente(Long id, String nome, String email, String cpf, String rg) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.cpf = cpf;
        this.cpf = rg;
    }

    
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getRg() {
        return rg;
    }

    public void setRg(String rg) {
        this.rg = rg;
    }

    public LocalDate getUltimoAcesso() {
        return ultimoAcesso;
    }

    public void setUltimoAcesso(LocalDate ultimoAcesso) {
        this.ultimoAcesso = ultimoAcesso;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 61 * hash + Objects.hashCode(this.id);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Cliente)) {
            return false;
        }
        Cliente other = (Cliente) object;
        return !((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id)));
    }

    @Override
    public String toString() {
        return "br.com.senac.entidade.Cliente[ id=" + id + " ]";
    }
    
}
