package br.ufc.usuarios;

import java.util.List;

public class UsuarioService {

    private final UsuarioRepository repository;
    private final EmailService emailService;
    private final TokenService tokenService;

    public UsuarioService(
            UsuarioRepository repository,
            EmailService emailService,
            TokenService tokenService
    ) {
        this.repository = repository;
        this.emailService = emailService;
        this.tokenService = tokenService;
    }

    public Usuario cadastrarUsuario(String nome, String email, String senha) {
        validarNome(nome);
        validarEmail(email);
        validarSenha(senha);

        if (repository.existePorEmail(email)) {
            throw new IllegalArgumentException("Email já cadastrado.");
        }

        Usuario usuario = repository.salvar(nome, email, senha);

        emailService.enviarEmailBoasVindas(usuario);

        return usuario;
    }

    public List<Usuario> listarUsuarios() {
        return repository.listarTodos();
    }

    public Usuario buscarUsuarioPorId(Long id) {
        return repository.buscarPorId(id)
                .orElseThrow(() -> new IllegalArgumentException("Usuário não encontrado."));
    }

    public Usuario buscarUsuarioPorEmail(String email) {
        return repository.buscarPorEmail(email)
                .orElseThrow(() -> new IllegalArgumentException("Usuário não encontrado."));
    }

    public void desativarUsuario(Long id) {
        Usuario usuario = buscarUsuarioPorId(id);
        usuario.desativar();
    }

    public boolean autenticar(String email, String senha) {
        Usuario usuario = buscarUsuarioPorEmail(email);

        return usuario.senhaCorreta(senha);
    }

    public String gerarTokenDeAcesso(String email) {
        Usuario usuario = buscarUsuarioPorEmail(email);

        return tokenService.gerarToken(usuario);
    }

    public String gerarRelatorioUsuarios() {
        String relatorio = "RELATÓRIO DE USUÁRIOS\n";

        for (Usuario usuario : repository.listarTodos()) {
            relatorio += "ID: " + usuario.getId() + "\n";
            relatorio += "Nome: " + usuario.getNome() + "\n";
            relatorio += "Email: " + usuario.getEmail() + "\n";
            relatorio += "Ativo: " + usuario.isAtivo() + "\n";
            relatorio += "--------------------------\n";
        }

        return relatorio;
    }

    private void validarNome(String nome) {
        if (nome == null || nome.isBlank()) {
            throw new IllegalArgumentException("Nome é obrigatório.");
        }

        if (nome.length() < 3) {
            throw new IllegalArgumentException("Nome deve ter pelo menos 3 caracteres.");
        }
    }

    private void validarEmail(String email) {
        if (email == null || email.isBlank()) {
            throw new IllegalArgumentException("Email é obrigatório.");
        }

        if (!email.contains("@")) {
            throw new IllegalArgumentException("Email inválido.");
        }
    }

    private void validarSenha(String senha) {
        if (senha == null || senha.isBlank()) {
            throw new IllegalArgumentException("Senha é obrigatória.");
        }

        if (senha.length() < 6) {
            throw new IllegalArgumentException("Senha deve ter pelo menos 6 caracteres.");
        }
    }
}