package br.ufc.usuarios;

public class EmailService {

    public void enviarEmailBoasVindas(Usuario usuario) {
        String mensagem = montarMensagemBoasVindas(usuario);

        System.out.println("Enviando email para: " + usuario.getEmail());
        System.out.println(mensagem);
    }

    public void enviarEmailContaDesativada(Usuario usuario) {
        String mensagem = "Olá, " + usuario.getNome() + ". Sua conta foi desativada.";

        System.out.println("Enviando email para: " + usuario.getEmail());
        System.out.println(mensagem);
    }

    private String montarMensagemBoasVindas(Usuario usuario) {
        return "Olá, " + usuario.getNome() + ". Seja bem-vindo ao sistema!";
    }
}