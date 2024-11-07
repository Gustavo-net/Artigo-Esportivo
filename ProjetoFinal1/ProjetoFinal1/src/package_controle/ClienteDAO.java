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

    public void create(Clientes c) {
        if (validarExistente(c.getCpf())) {
            throw new IllegalArgumentException("Cliente com CPF " + c.getCpf() + " já existe.");
        }

        Connection con = ConnectionDatabase.getConnection();
        PreparedStatement stmt = null;
        try {
            if (con == null) {
                throw new SQLException("Falha ao conectar ao banco de dados.");
            }

            stmt = con.prepareStatement(
                    "INSERT INTO Clientes (nomeCliente, cpf, email, telefone, id_Endereco) VALUES (?, ?, ?, ?, ?)");
            stmt.setString(1, c.getNomeCliente());
            stmt.setString(2, c.getCpf());
            stmt.setString(3, c.getEmail());
            stmt.setString(4, c.getTelefone());
            stmt.setString(5, c.getId_Endereco()); 
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectionDatabase.closeConnection(con, stmt);
        }
    }

    public static ArrayList<Clientes> read() {
        Connection con = ConnectionDatabase.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        ArrayList<Clientes> clientes = new ArrayList<>();

        try {
            String sql = "SELECT f.idCliente, f.nomeCliente, f.cpf, f.email, f.telefone, e.cep, e.rua, e.numero, e.bairro, e.complemento, e.cidadeUF " +
                         "FROM Clientes f " +
                         "JOIN Enderecos e ON f.id_Endereco = e.idEndereco";  // Join para obter os dados do endereço
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
            stmt = con.prepareStatement("SELECT idCliente, nomeCliente, cpf, email, telefone " +
                                        "FROM Clientes WHERE nomeCliente LIKE ? OR cpf LIKE ?");
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
