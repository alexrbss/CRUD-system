package src.usuario;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import src.database.DataBaseConnection;

public class UsuarioDAO {
    public void salvar(Usuario usuario) {
        String sql = "INSERT INTO usuarios (nome, cpf, data_nascimento, cidade) VALUES (?, ?, ?, ?)";
        
        try (Connection conn = DataBaseConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, usuario.getNome());
            stmt.setLong(2, usuario.getCpf());
            stmt.setDate(3, usuario.getDataDeNascimento());
            stmt.setString(4, usuario.getCidade());
            
            stmt.executeUpdate();
            System.out.println("Usuário salvo no banco!");
            
        } catch (SQLException e) {
            System.err.println("Erro ao salvar usuário: " + e.getMessage());
            e.printStackTrace(); 
        }
    }

    public Usuario buscarPorCpf(long cpf) {
        String sql = "SELECT * FROM usuarios WHERE cpf = ?";
        
        try (Connection conn = DataBaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setLong(1, cpf);
            ResultSet rs = stmt.executeQuery();
            
            if (rs.next()) {
                return new Usuario(
                    rs.getLong("id"),
                    rs.getString("nome"),
                    rs.getLong("cpf"),
                    rs.getDate("data_nascimento"),
                    rs.getString("cidade")
                );
            }
            
        } catch (SQLException e) {
            System.err.println("Erro ao buscar usuário: " + e.getMessage());
            e.printStackTrace();
        }
        
        return null;
    }
    
        public List<Usuario> listarTodos() {
        List<Usuario> usuarios = new ArrayList<>();
        String sql = "SELECT * FROM usuarios ORDER BY id";
        
        try (Connection conn = DataBaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            
            while (rs.next()) {
                Usuario usuario = new Usuario(
                    rs.getLong("id"),
                    rs.getString("nome"),
                    rs.getLong("cpf"),
                    rs.getDate("data_nascimento"),
                    rs.getString("cidade")
                );
                usuarios.add(usuario);
            }
            
        } catch (SQLException e) {
            System.err.println("Erro ao listar usuários: " + e.getMessage());
            e.printStackTrace();
        }
        
        return usuarios;
    }
    
    // UPDATE - Atualizar usuário por CPF
    public void atualizar(Usuario usuario) {
        String sql = "UPDATE usuarios SET nome = ?, data_nascimento = ?, cidade = ? WHERE cpf = ?";
        
        try (Connection conn = DataBaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setString(1, usuario.getNome());
            stmt.setDate(2, usuario.getDataDeNascimento());
            stmt.setString(3, usuario.getCidade());
            stmt.setLong(4, usuario.getCpf());
            
            int rowsAffected = stmt.executeUpdate();
            
            if (rowsAffected > 0) {
                System.out.println("Usuário com CPF " + usuario.getCpf() + " atualizado com sucesso!");
            } else {
                System.out.println("Nenhum usuário foi atualizado. CPF " + usuario.getCpf() + " não encontrado.");
            }
            
        } catch (SQLException e) {
            System.err.println("Erro ao atualizar usuário: " + e.getMessage());
            e.printStackTrace();
        }
    }
    
    public void deletarPorCpf(long cpf) {
        String sql = "DELETE FROM usuarios WHERE cpf = ?";
        
        try (Connection conn = DataBaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setLong(1, cpf);
            int rowsAffected = stmt.executeUpdate();
            
            if (rowsAffected > 0) {
                System.out.println("Usuário com CPF " + cpf + " deletado com sucesso!");
            } else {
                System.out.println("Nenhum usuário foi deletado. CPF " + cpf + " não encontrado.");
            }
            
        } catch (SQLException e) {
            System.err.println("Erro ao deletar usuário por CPF: " + e.getMessage());
            e.printStackTrace();
        }
    }
}