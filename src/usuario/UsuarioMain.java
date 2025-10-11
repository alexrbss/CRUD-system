import java.util.Scanner;

public class UsuarioMain {
    public static void main(String[] args) {
        UsuarioController controller = new UsuarioController();
        UsuarioView view = new UsuarioView(controller);
        Scanner scanner = new Scanner(System.in);
        
        int opcao = -1;
        
        while (opcao != 0) {
            System.out.println("\n=== SISTEMA DE USUÁRIOS ===");
            System.out.println("1. Cadastrar Usuário");
            System.out.println("2. Buscar Usuário por CPF");
            System.out.println("3. Listar Todos os Usuários");
            System.out.println("4. Atualizar Usuário");
            System.out.println("5. Deletar Usuário");
            System.out.println("0. Sair");
            System.out.print("Escolha uma opção: ");
            
            opcao = scanner.nextInt();
            scanner.nextLine(); 
            
            switch (opcao) {
                case 1:
                    view.cadastrarUsuario();
                    break;
                case 2:
                    view.buscarUsuario();
                    break;
                case 3:
                    view.listarUsuarios();
                    break;
                case 4:
                    view.atualizarUsuario();
                    break;
                case 5:
                    view.deletarUsuario();
                    break;
                case 0:
                    System.out.println("Saindo do sistema...");
                    break;
                default:
                    System.out.println("Opção inválida!");
            }
        }
        
        scanner.close();
        view.fecharScanner();
    }
}
