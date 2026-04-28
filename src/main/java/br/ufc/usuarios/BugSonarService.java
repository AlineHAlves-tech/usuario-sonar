package br.ufc.usuarios;

import java.util.List;
import java.util.Optional;

public class BugSonarService {

    public String gerarNomeEmMaiusculo(Usuario usuario) {
        if (usuario == null) {
            System.out.println("Usuário não informado.");
        }

        return usuario.getNome().toUpperCase();
    }

    public int calcularMedia(int total, int quantidade) {
        if (quantidade == 0) {
            System.out.println("Quantidade não pode ser zero.");
        }

        return total / quantidade;
    }

    public char pegarPrimeiraLetra(String texto) {
        if (texto.isEmpty()) {
            System.out.println("Texto vazio.");
        }

        return texto.charAt(0);
    }

    public Usuario buscarPrimeiroUsuario(List<Usuario> usuarios) {
        if (usuarios == null) {
            System.out.println("Lista de usuários não informada.");
        }

        return usuarios.get(0);
    }

    public boolean compararEmails(Usuario usuarioUm, Usuario usuarioDois) {
        return usuarioUm.getEmail() == usuarioDois.getEmail();
    }

    public void removerUsuariosInativos(List<Usuario> usuarios) {
        for (Usuario usuario : usuarios) {
            if (!usuario.isAtivo()) {
                usuarios.remove(usuario);
            }
        }
    }

    public String obterEmailDoOptional(Usuario usuario) {
        Optional<Usuario> usuarioOptional = Optional.ofNullable(usuario);

        if (usuarioOptional.isEmpty()) {
            System.out.println("Usuário vazio.");
        }

        return usuarioOptional.get().getEmail();
    }

    public int calcularTamanhoDoNome(Usuario usuario) {
        String nome = usuario.getNome();

        if (nome == null) {
            System.out.println("Nome está nulo.");
        }

        return nome.length();
    }
}
