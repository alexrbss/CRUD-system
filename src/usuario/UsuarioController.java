import java.sql.Date;
import java.util.List;

public class UsuarioController {
    private UsuarioDAO usuarioDAO;
    
    public UsuarioController() {
        this.usuarioDAO = new UsuarioDAO();
    }
    
    public void criarUsuario(String nome, long cpf, Date dataDeNascimento, String cidade) {
        try {
            if (nome == null || nome.trim().isEmpty()) {
                System.err.println("Nome é obrigatório!");
                return;
            }
            
            if (cidade == null || cidade.trim().isEmpty()) {
                System.err.println("Cidade é obrigatória!");
                return;
            }
            
            if (cpf <= 0) {
                System.err.println("CPF inválido!");
                return;
            }
            
            Usuario usuarioExistente = usuarioDAO.buscarPorCpf(cpf);
            if (usuarioExistente != null) {
                System.err.println("CPF " + cpf + " já está cadastrado!");
                return;
            }
            
            Usuario usuario = new Usuario(nome, cpf, dataDeNascimento, cidade);
            usuarioDAO.salvar(usuario);
            
        } catch (Exception e) {
            System.err.println("Erro ao criar usuário: " + e.getMessage());
        }
    }

    public Usuario buscarUsuarioPorCpf(long cpf) {
        try {
            if (cpf <= 0) {
                System.err.println("CPF inválido!");
                return null;
            }
            
            return usuarioDAO.buscarPorCpf(cpf);
            
        } catch (Exception e) {
            System.err.println("Erro ao buscar usuário: " + e.getMessage());
            return null;
        }
    }

    public List<Usuario> listarUsuarios() {
        try {
            return usuarioDAO.listarTodos();
            
        } catch (Exception e) {
            System.err.println("Erro ao listar usuários: " + e.getMessage());
            return null;
        }
    }
    
    public void atualizarUsuario(Usuario usuario) {
        try {
            if (usuario == null) {
                System.err.println("Usuário não pode ser nulo!");
                return;
            }
            
            if (usuario.getCpf() <= 0) {
                System.err.println("CPF do usuário é inválido!");
                return;
            }
        
            Usuario usuarioExistente = usuarioDAO.buscarPorCpf(usuario.getCpf());
            if (usuarioExistente == null) {
                System.err.println("Usuário com CPF " + usuario.getCpf() + " não encontrado!");
                return;
            }
            
            usuarioDAO.atualizar(usuario);
            
        } catch (Exception e) {
            System.err.println("Erro ao atualizar usuário: " + e.getMessage());
        }
    }
    
    public void deletarUsuario(long cpf) {
        try {
            if (cpf <= 0) {
                System.err.println("CPF inválido!");
                return;
            }
        
            Usuario usuario = usuarioDAO.buscarPorCpf(cpf);
            if (usuario == null) {
                System.err.println("Usuário com CPF " + cpf + " não encontrado!");
                return;
            }
            
            System.out.println("Usuário a ser deletado: " + usuario.getNome() + " (CPF: " + cpf + ")");
            
            usuarioDAO.deletarPorCpf(cpf);
            
        } catch (Exception e) {
            System.err.println("Erro ao deletar usuário: " + e.getMessage());
        }
    }
}
