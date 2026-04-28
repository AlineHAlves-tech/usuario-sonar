package br.ufc.usuarios;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UsuarioRepository {

    private final List<Usuario> usuarios = new ArrayList<>();
    private Long proximoId = 1L;

    public Usuario salvar(String nome, String email, String senha) {
        Usuario usuario = new Usuario(proximoId, nome, email, senha);
        usuarios.add(usuario);
        proximoId++;

        return usuario;
    }

    public List<Usuario> listarTodos() {
        return new ArrayList<>(usuarios);
    }

    public Optional<Usuario> buscarPorId(Long id) {
        return usuarios.stream()
                .filter(usuario -> usuario.getId().equals(id))
                .findFirst();
    }

    public Optional<Usuario> buscarPorEmail(String email) {
        return usuarios.stream()
                .filter(usuario -> usuario.getEmail().equalsIgnoreCase(email))
                .findFirst();
    }

    public boolean existePorEmail(String email) {
        return usuarios.stream()
                .anyMatch(usuario -> usuario.getEmail().equalsIgnoreCase(email));
    }

    public int contarUsuariosAtivos() {
        int total = 0;

        for (Usuario usuario : usuarios) {
            if (usuario.isAtivo()) {
                total++;
            }
        }

        return total;
    }

    public int contarUsuariosInativos() {
        int total = 0;

        for (Usuario usuario : usuarios) {
            if (!usuario.isAtivo()) {
                total++;
            }
        }

        return total;
    }
}