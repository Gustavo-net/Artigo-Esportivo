
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import packageConnection.ConnectionDatabase;
import packageModel.Clientes;

public class ClienteDAO {
    public void create(Clientes c) {
        Connection con = ConnectionDatabase.getConnection();
        PreparedStatement stmt = null;

        try {
            stmt = con.prepareStatement(
                    "INSERT INTO Cliente (idEndereco, nome, cpf, id_Endereck, email, telefone, programaFidelidade, pontosFidelidade, dataNasc) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)");
            stmt.setString(1, c.getIdCliente());
            stmt.setString(2, c.getNomeCliente());
            stmt.setString(3, c.getCpf());
            stmt.setString(4, c.getId_Endereço());
            stmt.setString(5, c.getEmail());
            stmt.setString(6, c.getTelefone());
            stmt.setString(7, c.getProgramaFidelidade());
            stmt.setString(8, c.getPontosFidelidade());
            stmt.setString(9, c.getDataNasc());

            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectionDatabase.closeConnection(con, stmt);
        }
    }
    
    	
    	
    

    public ArrayList<Clientes> search(String string) {
        Connection con = ConnectionDatabase.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        ArrayList<Clientes> clientes = new ArrayList<>();

        try {
            stmt = con.prepareStatement("SELECT * FROM Cliente WHERE nome LIKE ? or cpf LIKE ?"); 
            stmt.setString(1, "%" + string + "%");
            rs = stmt.executeQuery();

            while (rs.next()) {
                Clientes c = new Clientes();
                c.setIdCliente(rs.getString("idCliente"));
                c.setNomeCliente(rs.getString("nome"));
                c.setCpf(rs.getString("cpf"));
                c.setEmail(rs.getString("email"));
                c.setTelefone(rs.getString("telefone"));
                c.setProgramaFidelidade("programaFidelidade");
                c.setPontosFidelidade("pontosFidelidade");
                c.setDataNasc(rs.getString("dataNasc"));
                clientes.add(c);
            }
            
        } catch (SQLException e) {
            e.printStackTrace(); 
        } finally {
            ConnectionDatabase.closeConnection(con, stmt, rs);
        }
        
        return clientes;
    }
    
    public void update(Clientes c) {
        Connection con = ConnectionDatabase.getConnection();
        PreparedStatement stmt = null;

        try {
            stmt = con.prepareStatement("UPDATE Cliente SET nome = ?, cpf = ?, id_Endereco = ?, email = ?, telefone = ?, programaFidelidade = ?, pontosFidelidade = ?, dataNasc = ? WHERE idCliente = ?");
            
            stmt.setString(1, c.getNomeCliente());
            stmt.setString(2, c.getCpf());
            stmt.setString(3, c.getId_Endereço());
            stmt.setString(4, c.getEmail());
            stmt.setString(5, c.getTelefone());
            stmt.setString(6, c.getProgramaFidelidade());
            stmt.setString(7, c.getPontosFidelidade());
            stmt.setString(8, c.getDataNasc());
            stmt.setString(9, c.getIdCliente());

            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectionDatabase.closeConnection(con, stmt);
        }
    }

    public void delete(String idCliente) {
    	
        Connection con = ConnectionDatabase.getConnection();
        PreparedStatement stmt = null;

        try {
            stmt = con.prepareStatement("DELETE FROM Cliente WHERE idCliente = ?");
            stmt.setString(1, idCliente);

            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Erro ao excluir o cliente: " + e.getMessage(), e);

        } finally {
            ConnectionDatabase.closeConnection(con, stmt);
        }
    }
  
}
