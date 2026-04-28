package br.ufc.usuarios;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class UsuarioServiceTest {

    private UsuarioService usuarioService;

    @BeforeEach
    void configurar() {
        UsuarioRepository repository = new UsuarioRepository();
        EmailService emailService = new EmailService();
        TokenService tokenService = new TokenService();

        usuarioService = new UsuarioService(repository, emailService, tokenService);
    }

    @Test
    void deveCadastrarUsuarioComSucesso() {
        Usuario usuario = usuarioService.cadastrarUsuario(
                "Aline Alves",
                "aline@email.com",
                "123456"
        );

        assertEquals(1L, usuario.getId());
        assertEquals("Aline Alves", usuario.getNome());
        assertEquals("aline@email.com", usuario.getEmail());
        assertTrue(usuario.isAtivo());
    }

    @Test
    void naoDeveCadastrarUsuarioComNomeVazio() {
        assertThrows(
                IllegalArgumentException.class,
                () -> usuarioService.cadastrarUsuario("", "aline@email.com", "123456")
        );
    }

    @Test
    void naoDeveCadastrarUsuarioComEmailInvalido() {
        assertThrows(
                IllegalArgumentException.class,
                () -> usuarioService.cadastrarUsuario("Aline Alves", "email-invalido", "123456")
        );
    }

    @Test
    void naoDeveCadastrarUsuarioComSenhaCurta() {
        assertThrows(
                IllegalArgumentException.class,
                () -> usuarioService.cadastrarUsuario("Aline Alves", "aline@email.com", "123")
        );
    }

    @Test
    void naoDeveCadastrarEmailDuplicado() {
        usuarioService.cadastrarUsuario("Aline Alves", "aline@email.com", "123456");

        assertThrows(
                IllegalArgumentException.class,
                () -> usuarioService.cadastrarUsuario("Aline Silva", "aline@email.com", "abcdef")
        );
    }

    @Test
    void deveListarUsuarios() {
        usuarioService.cadastrarUsuario("Aline Alves", "aline@email.com", "123456");
        usuarioService.cadastrarUsuario("Leticia Lima", "leticia@email.com", "abcdef");

        List<Usuario> usuarios = usuarioService.listarUsuarios();

        assertEquals(2, usuarios.size());
    }

    @Test
    void deveBuscarUsuarioPorEmail() {
        usuarioService.cadastrarUsuario("Aline Alves", "aline@email.com", "123456");

        Usuario usuario = usuarioService.buscarUsuarioPorEmail("aline@email.com");

        assertEquals("Aline Alves", usuario.getNome());
    }

    @Test
    void deveAutenticarUsuarioComSenhaCorreta() {
        usuarioService.cadastrarUsuario("Aline Alves", "aline@email.com", "123456");

        boolean autenticado = usuarioService.autenticar("aline@email.com", "123456");

        assertTrue(autenticado);
    }

    @Test
    void naoDeveAutenticarUsuarioComSenhaErrada() {
        usuarioService.cadastrarUsuario("Aline Alves", "aline@email.com", "123456");

        boolean autenticado = usuarioService.autenticar("aline@email.com", "senhaerrada");

        assertFalse(autenticado);
    }

    @Test
    void deveDesativarUsuario() {
        Usuario usuario = usuarioService.cadastrarUsuario(
                "Aline Alves",
                "aline@email.com",
                "123456"
        );

        usuarioService.desativarUsuario(usuario.getId());

        Usuario usuarioDesativado = usuarioService.buscarUsuarioPorId(usuario.getId());

        assertFalse(usuarioDesativado.isAtivo());
    }

    @Test
    void deveGerarTokenDeAcesso() {
        usuarioService.cadastrarUsuario("Aline Alves", "aline@email.com", "123456");

        String token = usuarioService.gerarTokenDeAcesso("aline@email.com");

        assertNotNull(token);
        assertFalse(token.isBlank());
    }
}