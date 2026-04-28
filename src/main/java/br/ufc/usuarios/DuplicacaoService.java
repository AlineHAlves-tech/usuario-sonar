package br.ufc.usuarios;

import java.util.List;

public class DuplicacaoService {

    public String gerarRelatorioCompleto(List<Usuario> usuarios) {
        StringBuilder relatorio = new StringBuilder();

        relatorio.append("RELATÓRIO DE USUÁRIOS\n");
        relatorio.append("====================\n");

        for (Usuario usuario : usuarios) {
            relatorio.append("ID: ").append(usuario.getId()).append("\n");
            relatorio.append("Nome: ").append(usuario.getNome()).append("\n");
            relatorio.append("Email: ").append(usuario.getEmail()).append("\n");
            relatorio.append("Ativo: ").append(usuario.isAtivo()).append("\n");
            relatorio.append("Criado em: ").append(usuario.getCriadoEm()).append("\n");
            relatorio.append("--------------------\n");
        }

        return relatorio.toString();
    }

    public String gerarRelatorioCopia(List<Usuario> usuarios) {
        StringBuilder relatorio = new StringBuilder();

        relatorio.append("RELATÓRIO DE USUÁRIOS\n");
        relatorio.append("====================\n");

        for (Usuario usuario : usuarios) {
            relatorio.append("ID: ").append(usuario.getId()).append("\n");
            relatorio.append("Nome: ").append(usuario.getNome()).append("\n");
            relatorio.append("Email: ").append(usuario.getEmail()).append("\n");
            relatorio.append("Ativo: ").append(usuario.isAtivo()).append("\n");
            relatorio.append("Criado em: ").append(usuario.getCriadoEm()).append("\n");
            relatorio.append("--------------------\n");
        }

        return relatorio.toString();
    }
}
