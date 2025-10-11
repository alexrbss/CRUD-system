import java.util.Scanner;
import java.sql.Date;
import java.util.List;

public class UsuarioView {
    private UsuarioController controller;

    private Scanner scanner = new Scanner(System.in);

    public UsuarioView(UsuarioController controller) {
        this.controller = controller;
    }

    public void fecharScanner() {
        scanner.close();
    }
    
    public void cadastrarUsuario() {
        System.out.println("\n--- CADASTRAR USUÁRIO ---");
        
        System.out.print("Nome: ");
        String nome = scanner.nextLine();
        
        System.out.print("CPF (apenas números): ");
        long cpf = scanner.nextLong();
        scanner.nextLine();
        
        System.out.print("Data de Nascimento (AAAA-MM-DD): ");
        String dataStr = scanner.nextLine();
        Date dataDeNascimento = Date.valueOf(dataStr);
        
        System.out.print("Cidade: ");
        String cidade = scanner.nextLine();
        
        controller.criarUsuario(nome, cpf, dataDeNascimento, cidade);
        System.out.println("Usuário cadastrado com sucesso!");
    }
    
    public void buscarUsuario() {
        System.out.println("\n--- BUSCAR USUÁRIO ---");
        
        System.out.print("Digite o CPF: ");
        long cpf = scanner.nextLong();
        scanner.nextLine(); 
        
        Usuario usuario = controller.buscarUsuarioPorCpf(cpf);
        
        if (usuario != null) {
            exibirUsuario(usuario);
        } else {
            System.out.println("Usuário não encontrado!");
        }
    }
    
    public void listarUsuarios() {
        System.out.println("\n--- LISTA DE USUÁRIOS ---");
        
        List<Usuario> usuarios = controller.listarUsuarios();
        
        if (usuarios != null && !usuarios.isEmpty()) {
            for (Usuario usuario : usuarios) {
                exibirUsuario(usuario);
                System.out.println("-------------------");
            }
        } else {
            System.out.println("Nenhum usuário cadastrado.");
        }
    }
    
    public void exibirUsuario(Usuario usuario) {
        System.out.println("\nID: " + usuario.getId());
        System.out.println("Nome: " + usuario.getNome());
        System.out.println("CPF: " + usuario.getCpf());
        System.out.println("Data de Nascimento: " + usuario.getDataDeNascimento());
        System.out.println("Cidade: " + usuario.getCidade());
    }
    
    public void atualizarUsuario() {
        System.out.println("\n--- ATUALIZAR USUÁRIO ---");
        
        System.out.print("Digite o CPF do usuário a ser atualizado: ");
        long cpf = scanner.nextLong();
        scanner.nextLine(); 
        
        Usuario usuarioExistente = controller.buscarUsuarioPorCpf(cpf);
        if (usuarioExistente == null) {
            System.out.println("✗ Usuário não encontrado!");
            return;
        }
        
        System.out.println("Usuário encontrado: " + usuarioExistente.getNome());
        System.out.println("Digite os novos dados (deixe em branco para manter o atual):");
        
        System.out.print("Novo nome [" + usuarioExistente.getNome() + "]: ");
        String nome = scanner.nextLine();
        if (nome.trim().isEmpty()) {
            nome = usuarioExistente.getNome();
        }
        
        System.out.print("Nova data de nascimento [" + usuarioExistente.getDataDeNascimento() + "] (AAAA-MM-DD): ");
        String dataStr = scanner.nextLine();
        Date dataDeNascimento;
        if (dataStr.trim().isEmpty()) {
            dataDeNascimento = usuarioExistente.getDataDeNascimento();
        } else {
            dataDeNascimento = Date.valueOf(dataStr);
        }
        
        System.out.print("Nova cidade [" + usuarioExistente.getCidade() + "]: ");
        String cidade = scanner.nextLine();
        if (cidade.trim().isEmpty()) {
            cidade = usuarioExistente.getCidade();
        }
        
        Usuario usuarioAtualizado = new Usuario(nome, cpf, dataDeNascimento, cidade);
        controller.atualizarUsuario(usuarioAtualizado);
        
        System.out.println("✓ Usuário atualizado com sucesso!");
    }
    
    public void deletarUsuario() {
        System.out.println("\n--- DELETAR USUÁRIO ---");
        
        System.out.print("Digite o CPF do usuário a ser deletado: ");
        long cpf = scanner.nextLong();
        scanner.nextLine(); 
        
        controller.deletarUsuario(cpf);
    }
}
