package projetotechsupport.apitechsupport.model.usuario;

public record UsuarioRecord(Long id, String codigo, String nome, String email, String telefone) {
    public UsuarioRecord(Usuario usuario) {this(usuario.getId(), usuario.getCodigo(), usuario.getNome(), usuario.getEmail(), usuario.getTelefone());}
}
