package package_controle;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import packageConnection.ConnectionDatabase;
import packageModel.Fornecedores;

public class FornecedoresDAO {
	public static void create(Fornecedores f) {
	    Connection con = ConnectionDatabase.getConnection();
	    PreparedStatement stmtEndereco = null;
	    PreparedStatement stmtFornecedor = null;
	    ResultSet rsEndereco = null;

	    try {
	        // Inicia a transação
	        con.setAutoCommit(false);

	        // SQL para inserir o endereço
	        String sqlEndereco = "INSERT INTO Enderecos (cep, rua, numero, bairro, complemento, cidadeUF) " +
	                             "VALUES (?, ?, ?, ?, ?, ?)";
	        stmtEndereco = con.prepareStatement(sqlEndereco, PreparedStatement.RETURN_GENERATED_KEYS);
	        stmtEndereco.setString(1, f.getCep());
	        stmtEndereco.setString(2, f.getRua());
	        stmtEndereco.setString(3, f.getNumero());
	        stmtEndereco.setString(4, f.getBairro());
	        stmtEndereco.setString(5, f.getComplemento());
	        stmtEndereco.setString(6, f.getCidadeUF());

	        // Executa a inserção do endereço
	        int affectedRows = stmtEndereco.executeUpdate();

	        if (affectedRows > 0) {
	            // Obtém o ID gerado do endereço inserido
	            rsEndereco = stmtEndereco.getGeneratedKeys();
	            if (rsEndereco.next()) {
	                int idEndereco = rsEndereco.getInt(1);

	                // SQL para inserir o fornecedor com o ID do endereço
	                String sqlFornecedor = "INSERT INTO Fornecedores (nomeFornecedor, cnpj, email, telefone, id_Endereco) " +
	                                       "VALUES (?, ?, ?, ?, ?)";
	                stmtFornecedor = con.prepareStatement(sqlFornecedor);
	                stmtFornecedor.setString(1, f.getNomeFornecedor());
	                stmtFornecedor.setString(2, f.getCnpj());
	                stmtFornecedor.setString(3, f.getEmail());
	                stmtFornecedor.setString(4, f.getTelefone());
	                stmtFornecedor.setInt(5, idEndereco);  // Vincula o ID do endereço à inserção do fornecedor

	                // Executa a inserção do fornecedor
	                stmtFornecedor.executeUpdate();
	            } else {
	                throw new SQLException("Falha ao obter o ID do endereço inserido.");
	            }
	        } else {
	            throw new SQLException("Falha ao inserir o endereço.");
	        }

	        // Confirma a transação
	        con.commit();

	    } catch (SQLException e) {
	        try {
	            if (con != null) {
	                con.rollback();  // Reverte a transação em caso de erro
	            }
	        } catch (SQLException ex) {
	            ex.printStackTrace();
	        }
	        e.printStackTrace();
	    } finally {
	        try {
	            if (rsEndereco != null) rsEndereco.close();
	            if (stmtFornecedor != null) stmtFornecedor.close();
	            if (stmtEndereco != null) stmtEndereco.close();
	            if (con != null) con.setAutoCommit(true);  // Restaura o comportamento padrão de commit automático
	            if (con != null) con.close();
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }
	}



	public ArrayList<Fornecedores> read() {
	    Connection con = ConnectionDatabase.getConnection();
	    PreparedStatement stmt = null;
	    ResultSet rs = null;
	    ArrayList<Fornecedores> fornecedores = new ArrayList<>();

	    try {
	        String sql = "SELECT f.*, e.cep, e.rua, e.numero, e.bairro, e.complemento, e.cidadeUF " +
	                     "FROM Fornecedores f " +
	                     "LEFT JOIN Enderecos e ON f.id_Endereco = e.idEndereco"; 

	        stmt = con.prepareStatement(sql);
	        rs = stmt.executeQuery();

	        while (rs.next()) {
	            Fornecedores f = new Fornecedores();
	            f.setIdFornecedor(rs.getString("idFornecedor"));
	            f.setNomeFornecedor(rs.getString("nomeFornecedor"));
	            f.setCnpj(rs.getString("cnpj"));
	            f.setEmail(rs.getString("email"));
	            f.setTelefone(rs.getString("telefone"));
	            f.setId_Endereço(rs.getString("id_Endereco"));
	            
	            f.setCep(rs.getString("cep"));
	            f.setRua(rs.getString("rua"));
	            f.setNumero(rs.getString("numero"));
	            f.setBairro(rs.getString("bairro"));
	            f.setComplemento(rs.getString("complemento"));
	            f.setCidadeUF(rs.getString("cidadeUF"));

	            fornecedores.add(f);
	        }

	    } catch (SQLException e) {
	        e.printStackTrace();
	    } finally {
	        ConnectionDatabase.closeConnection(con, stmt, rs);
	    }
	    return fornecedores;
	}


	public static ArrayList<Fornecedores> search(String string) {
	    Connection con = ConnectionDatabase.getConnection();
	    PreparedStatement stmt = null;
	    ResultSet rs = null;
	    ArrayList<Fornecedores> fornecedores = new ArrayList<>();

	    try {
	        String sql = "SELECT f.*, e.cep, e.rua, e.numero, e.bairro, e.complemento, e.cidadeUF " +
	                     "FROM Fornecedores f " +
	                     "LEFT JOIN Enderecos e ON f.id_Endereco = e.idEndereco " +
	                     "WHERE f.nomeFornecedor LIKE ?";

	        stmt = con.prepareStatement(sql);
	        stmt.setString(1, "%" + string + "%");
	        rs = stmt.executeQuery();

	        while (rs.next()) {
	            Fornecedores f = new Fornecedores();
	            f.setIdFornecedor(rs.getString("idFornecedor"));
	            f.setNomeFornecedor(rs.getString("nomeFornecedor"));
	            f.setCnpj(rs.getString("cnpj"));
	            f.setEmail(rs.getString("email"));
	            f.setTelefone(rs.getString("telefone"));
	            f.setId_Endereço(rs.getString("id_Endereco"));

	            f.setCep(rs.getString("cep"));
	            f.setRua(rs.getString("rua"));
	            f.setNumero(rs.getString("numero"));
	            f.setBairro(rs.getString("bairro"));
	            f.setComplemento(rs.getString("complemento"));
	            f.setCidadeUF(rs.getString("cidadeUF"));

	            fornecedores.add(f);
	        }

	    } catch (SQLException e) {
	        e.printStackTrace();
	    } finally {
	        ConnectionDatabase.closeConnection(con, stmt, rs);
	    }

	    return fornecedores;
	}


    public void update(Fornecedores f) {
        Connection con = ConnectionDatabase.getConnection();
        PreparedStatement stmt = null;

        try {
            stmt = con.prepareStatement("UPDATE Fornecedores SET nomeFornecedor = ?, cnpj = ?, email = ?, telefone = ? WHERE idFornecedor = ?");
            stmt.setString(1, f.getNomeFornecedor());
            stmt.setString(2, f.getCnpj());
            stmt.setString(3, f.getEmail());
            stmt.setString(4, f.getTelefone());
            stmt.setString(5, f.getIdFornecedor());

            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectionDatabase.closeConnection(con, stmt);
        }
    }

    public void delete(String idFornecedor) {
    	
        Connection con = ConnectionDatabase.getConnection();
        PreparedStatement stmt = null;

        try {
            stmt = con.prepareStatement("DELETE FROM Fornecedores WHERE idFornecedor = ?");
            stmt.setString(1, idFornecedor);

            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Erro ao excluir o fornecedor: " + e.getMessage(), e);

        } finally {
            ConnectionDatabase.closeConnection(con, stmt);
        }
    }
}
