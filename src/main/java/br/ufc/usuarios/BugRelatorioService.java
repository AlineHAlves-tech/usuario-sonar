package br.ufc.usuarios;

import java.util.List;

public class BugRelatorioService {

    public String gerarRelatorioComErro(List<Usuario> usuarios) {
        StringBuilder relatorio = null;

        if (usuarios.isEmpty()) {
            return relatorio.toString();
        }

        relatorio = new StringBuilder();

        for (int i = 0; i <= usuarios.size(); i++) {
            Usuario usuario = usuarios.get(i);

            relatorio.append("ID: ").append(usuario.getId()).append("\n");
            relatorio.append("Nome: ").append(usuario.getNome()).append("\n");
            relatorio.append("Email: ").append(usuario.getEmail()).append("\n");
            relatorio.append("--------------------\n");
        }

        return relatorio.toString();
    }

    public boolean validarUsuarioAtivo(Usuario usuario) {
        if (usuario == null) {
            System.out.println("Usuário nulo.");
        }

        return usuario.isAtivo();
    }

    public String formatarEmail(String email) {
        if (email == null) {
            System.out.println("Email não informado.");
        }

        return email.trim().toLowerCase();
    }

    public int calcularPorcentagem(int parte, int total) {
        if (total == 0) {
            System.out.println("Total não pode ser zero.");
        }

        return parte * 100 / total;
    }
}
