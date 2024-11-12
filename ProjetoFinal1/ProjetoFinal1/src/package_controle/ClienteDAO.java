package package_controle;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import packageConnection.ConnectionDatabase;
import packageModel.Clientes;

public class ClienteDAO {

    public static boolean validarExistente(String cpf) {
        Connection con = ConnectionDatabase.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            stmt = con.prepareStatement("SELECT COUNT(*) FROM Clientes WHERE cpf = ?");
            stmt.setString(1, cpf);
            rs = stmt.executeQuery();

            if (rs.next()) {
                return rs.getInt(1) > 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectionDatabase.closeConnection(con, stmt, rs);
        }
        return false;
    }

    public static String create(Clientes cliente) throws SQLException {
    	  String idCliente = null;
  	    Connection con = ConnectionDatabase.getConnection();
  	    PreparedStatement stmt = null;

  	    try {
  	        String sql = "INSERT INTO Clientes (nomeCliente, cpf, email, telefone, id_Endereco) VALUES (?, ?, ?, ?, ?)";
  	        stmt = con.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
  	        stmt.setString(1, cliente.getNomeCliente());
  	        stmt.setString(2, cliente.getCpf());
  	        stmt.setString(3, cliente.getEmail());
  	        stmt.setString(4, cliente.getTelefone());
  	        stmt.setString(5, cliente.getId_Endereco());
  	       
  	        int affectedRows = stmt.executeUpdate();
  	        if (affectedRows > 0) {
  	            ResultSet rs = stmt.getGeneratedKeys();
  	            if (rs.next()) {
  	            	idCliente = rs.getString(1);  
  	            }
  	        }
  	    } catch (SQLException e) {
  	        e.printStackTrace();
  	    } finally {
  	        try {
  	            if (stmt != null) stmt.close();
  	            if (con != null) con.close();
  	        } catch (SQLException e) {
  	            e.printStackTrace();
  	        }
  	    }
  	    return idCliente; 
  	}


    public static ArrayList<Clientes> read() {
        Connection con = ConnectionDatabase.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        ArrayList<Clientes> clientes = new ArrayList<>();

        try {
            String sql = "SELECT f.idCliente, f.nomeCliente, f.cpf, f.email, f.telefone, e.cep, e.rua, e.numero, e.bairro, e.complemento, e.cidadeUF " +
                         "FROM Clientes f " +
                         "JOIN Enderecos e ON f.id_Endereco = e.idEndereco";  
            stmt = con.prepareStatement(sql);
            rs = stmt.executeQuery();

            while (rs.next()) {
                Clientes c = new Clientes();
                c.setIdCliente(rs.getString("idCliente"));
                c.setNomeCliente(rs.getString("nomeCliente"));
                c.setCpf(rs.getString("cpf"));
                c.setEmail(rs.getString("email"));
                c.setTelefone(rs.getString("telefone"));

                // Dados de endereço
                c.setCep(rs.getString("cep"));
                c.setRua(rs.getString("rua"));
                c.setNumero(rs.getString("numero"));
                c.setBairro(rs.getString("bairro"));
                c.setComplemento(rs.getString("complemento"));
                c.setCidadeUF(rs.getString("cidadeUF"));

                clientes.add(c);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectionDatabase.closeConnection(con, stmt, rs);
        }

        return clientes;
    }

    public static ArrayList<Clientes> search(String string) {
        Connection con = ConnectionDatabase.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        ArrayList<Clientes> clientes = new ArrayList<>();

        try {
            String sql = "SELECT f.idCliente, f.nomeCliente, f.cpf, f.email, f.telefone, " +
                         "e.cep, e.rua, e.numero, e.bairro, e.complemento, e.cidadeUF " +
                         "FROM Clientes f " +
                         "JOIN Enderecos e ON f.id_Endereco = e.idEndereco " +
                         "WHERE f.nomeCliente LIKE ? OR f.cpf LIKE ?";

            stmt = con.prepareStatement(sql);
            stmt.setString(1, "%" + string + "%");
            stmt.setString(2, "%" + string + "%");
            rs = stmt.executeQuery();

            while (rs.next()) {
                Clientes c = new Clientes();
                c.setIdCliente(rs.getString("idCliente"));
                c.setNomeCliente(rs.getString("nomeCliente"));
                c.setCpf(rs.getString("cpf"));
                c.setEmail(rs.getString("email"));
                c.setTelefone(rs.getString("telefone"));

                c.setCep(rs.getString("cep"));
                c.setRua(rs.getString("rua"));
                c.setNumero(rs.getString("numero"));
                c.setBairro(rs.getString("bairro"));
                c.setComplemento(rs.getString("complemento"));
                c.setCidadeUF(rs.getString("cidadeUF"));

                clientes.add(c);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectionDatabase.closeConnection(con, stmt, rs);
        }

        return clientes;
    }

    public static void update(Clientes c) {
        Connection con = ConnectionDatabase.getConnection();
        PreparedStatement stmt = null;

        try {
            stmt = con.prepareStatement(
                    "UPDATE Clientes SET nomeCliente = ?, cpf = ?, id_Endereco = ?, email = ?, telefone = ? WHERE idCliente = ?");

            stmt.setString(1, c.getNomeCliente());
            stmt.setString(2, c.getCpf());
            stmt.setString(3, c.getId_Endereco());  
            stmt.setString(4, c.getEmail());
            stmt.setString(5, c.getTelefone());
            stmt.setString(6, c.getIdCliente());

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
            stmt = con.prepareStatement("DELETE FROM Clientes WHERE idCliente = ?");
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
