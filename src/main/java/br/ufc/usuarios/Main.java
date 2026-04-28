package br.ufc.usuarios;

public class Main {

    public static void main(String[] args) {
        UsuarioRepository repository = new UsuarioRepository();
        EmailService emailService = new EmailService();
        TokenService tokenService = new TokenService();

        UsuarioService service = new UsuarioService(repository, emailService, tokenService);

        Usuario usuario = service.cadastrarUsuario(
                "Aline Alves",
                "aline@email.com",
                "123456"
        );

        System.out.println("Usuário cadastrado:");
        System.out.println("ID: " + usuario.getId());
        System.out.println("Nome: " + usuario.getNome());
        System.out.println("Email: " + usuario.getEmail());

        String token = service.gerarTokenDeAcesso(usuario.getEmail());

        System.out.println("Token gerado:");
        System.out.println(token);
    }
}