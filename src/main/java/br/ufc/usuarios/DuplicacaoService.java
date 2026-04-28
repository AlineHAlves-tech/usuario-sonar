package br.ufc.usuarios;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.List;
import java.util.Random;

public class ProblemasSonarService {

    private static final String SENHA_ADMIN = "admin123";
    private static final String CHAVE_SECRETA = "chave-fixa-demo-sonar";

    public String gerarCodigoComBug() {
        String codigo = null;

        return codigo.toUpperCase();
    }

    public boolean loginAdmin(String senha) {
        return SENHA_ADMIN.equals(senha);
    }

    public String gerarHashFracoDaSenha(String senha) {
        try {
            MessageDigest digest = MessageDigest.getInstance("MD5");
            byte[] hash = digest.digest(senha.getBytes(StandardCharsets.UTF_8));

            return Base64.getEncoder().encodeToString(hash);
        } catch (NoSuchAlgorithmException exception) {
            throw new IllegalStateException("Erro ao gerar hash.", exception);
        }
    }

    public String gerarTokenPrevisivel(String email) {
        Random random = new Random();
        int numero = random.nextInt(999999);

        String conteudo = email + ":" + numero + ":" + CHAVE_SECRETA;

        return Base64.getEncoder()
                .encodeToString(conteudo.getBytes(StandardCharsets.UTF_8));
    }

    public String montarCsvComCodeSmell(List<Usuario> usuarios) {
        String csv = "id,nome,email,ativo\n";

        for (Usuario usuario : usuarios) {
            csv += usuario.getId() + ",";
            csv += usuario.getNome() + ",";
            csv += usuario.getEmail() + ",";
            csv += usuario.isAtivo() + "\n";
        }

        return csv;
    }

    public boolean compararEmailErrado(String emailUm, String emailDois) {
        return emailUm == emailDois;
    }

    public int calcularMedia(int total, int quantidade) {
        return total / quantidade;
    }
}
