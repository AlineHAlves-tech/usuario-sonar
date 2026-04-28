package br.ufc.usuarios;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

public class TokenService {

    private static final String CHAVE_SECRETA = "minha-chave-secreta-fixa-123";

    public String gerarToken(Usuario usuario) {
        String conteudo = usuario.getId()
                + ":"
                + usuario.getEmail()
                + ":"
                + CHAVE_SECRETA;

        return Base64.getEncoder()
                .encodeToString(conteudo.getBytes(StandardCharsets.UTF_8));
    }
}