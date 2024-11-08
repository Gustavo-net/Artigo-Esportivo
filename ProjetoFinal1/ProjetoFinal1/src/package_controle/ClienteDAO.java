package package_controle;

import java.beans.Statement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import packageConnection.ConnectionDatabase;
import packageModel.Clientes;
import packageModel.Enderecos;

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

    public static String create(Clientes c) throws SQLException {
        Connection con = null;
        PreparedStatement stmtEndereco = null;
        PreparedStatement stmtCliente = null;
        ResultSet generatedKeys = null;

        try {
            con = ConnectionDatabase.getConnection();
            if (con == null) {
                throw new SQLException("Falha ao conectar ao banco de dados.");
            }
            
            con.setAutoCommit(false);  

            String sqlEndereco = "INSERT INTO Enderecos (cep, rua, numero, bairro, complemento, cidadeUF) VALUES (?, ?, ?, ?, ?, ?)";
            stmtEndereco = con.prepareStatement(sqlEndereco, PreparedStatement.RETURN_GENERATED_KEYS);
            stmtEndereco.setString(1, c.getCep());
            stmtEndereco.setString(2, c.getRua());
            stmtEndereco.setString(3, c.getNumero());
            stmtEndereco.setString(4, c.getBairro());
            stmtEndereco.setString(5, c.getComplemento());
            stmtEndereco.setString(6, c.getCidadeUF());
            stmtEndereco.executeUpdate();  

            generatedKeys = stmtEndereco.getGeneratedKeys();
            if (!generatedKeys.next()) {
                throw new SQLException("Erro ao recuperar ID do endereço.");
            }
            String idEndereco = generatedKeys.getString(1);  

            String sqlCliente = "INSERT INTO Clientes (nomeCliente, cpf, id_Endereco, email, telefone, dataNasc) VALUES (?, ?, ?, ?, ?, ?)";
            stmtCliente = con.prepareStatement(sqlCliente);
            stmtCliente.setString(1, c.getNomeCliente());
            stmtCliente.setString(2, c.getCpf());
            stmtCliente.setString(3, idEndereco); 
            stmtCliente.setString(4, c.getEmail());
            stmtCliente.setString(5, c.getTelefone());

            stmtCliente.executeUpdate();

            con.commit(); 

            return idEndereco; 

        } catch (SQLException e) {
            if (con != null) {
                try {
                    con.rollback();  
                } catch (SQLException rollbackEx) {
                    rollbackEx.printStackTrace();
                }
            }
            throw e;  
        } finally {
            if (stmtEndereco != null) stmtEndereco.close();
            if (stmtCliente != null) stmtCliente.close();
            if (generatedKeys != null) generatedKeys.close();
            if (con != null) con.close();
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
            // SQL com join para obter os dados de Cliente e Endereço
            String sql = "SELECT f.idCliente, f.nomeCliente, f.cpf, f.email, f.telefone, " +
                         "e.cep, e.rua, e.numero, e.bairro, e.complemento, e.cidadeUF " +
                         "FROM Clientes f " +
                         "JOIN Enderecos e ON f.id_Endereco = e.idEndereco " +
                         "WHERE f.nomeCliente LIKE ? OR f.cpf LIKE ?";

            stmt = con.prepareStatement(sql);
            stmt.setString(1, "%" + string + "%"); // Busca no nomeCliente
            stmt.setString(2, "%" + string + "%"); // Busca no cpf
            rs = stmt.executeQuery();

            while (rs.next()) {
                // Cria o objeto Clientes e preenche com os dados da consulta
                Clientes c = new Clientes();
                c.setIdCliente(rs.getString("idCliente"));
                c.setNomeCliente(rs.getString("nomeCliente"));
                c.setCpf(rs.getString("cpf"));
                c.setEmail(rs.getString("email"));
                c.setTelefone(rs.getString("telefone"));

                // Preenche os dados do endereço, se disponíveis
                c.setCep(rs.getString("cep"));
                c.setRua(rs.getString("rua"));
                c.setNumero(rs.getString("numero"));
                c.setBairro(rs.getString("bairro"));
                c.setComplemento(rs.getString("complemento"));
                c.setCidadeUF(rs.getString("cidadeUF"));

                // Adiciona o cliente à lista
                clientes.add(c);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // Fecha a conexão, preparedStatement e ResultSet
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
