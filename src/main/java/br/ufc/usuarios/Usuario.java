package br.ufc.usuarios;

import java.time.LocalDateTime;
import java.util.Objects;

public class Usuario {

    private Long id;
    private String nome;
    private String email;
    private String senha;
    private boolean ativo;
    private LocalDateTime criadoEm;

    public Usuario(Long id, String nome, String email, String senha) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.ativo = true;
        this.criadoEm = LocalDateTime.now();
    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getEmail() {
        return email;
    }

    public String getSenha() {
        return senha;
    }

    public boolean isAtivo() {
        return ativo;
    }

    public LocalDateTime getCriadoEm() {
        return criadoEm;
    }

    public void atualizarNome(String novoNome) {
        this.nome = novoNome;
    }

    public void atualizarEmail(String novoEmail) {
        this.email = novoEmail;
    }

    public void desativar() {
        this.ativo = false;
    }

    public boolean senhaCorreta(String senhaInformada) {
        return senha.equals(senhaInformada);
    }

    @Override
    public boolean equals(Object objeto) {
        if (this == objeto) {
            return true;
        }

        if (!(objeto instanceof Usuario usuario)) {
            return false;
        }

        return Objects.equals(id, usuario.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}