package package_controle;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import packageConnection.ConnectionDatabase;
import packageModel.Fornecedores;

public class FornecedoresDAO {
	public static String create(Fornecedores fornecedor) {
	    String idFornecedor = null;
	    Connection con = ConnectionDatabase.getConnection();
	    PreparedStatement stmt = null;

	    try {
	        String sql = "INSERT INTO Fornecedores (nomeFornecedor, cnpj, email, telefone, id_Endereco) VALUES (?, ?, ?, ?, ?)";
	        stmt = con.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
	        stmt.setString(1, fornecedor.getNomeFornecedor());
	        stmt.setString(2, fornecedor.getCnpj());
	        stmt.setString(3, fornecedor.getEmail());
	        stmt.setString(4, fornecedor.getTelefone());
	        stmt.setString(5, fornecedor.getId_Endereço()); 

	        int affectedRows = stmt.executeUpdate();
	        if (affectedRows > 0) {
	            ResultSet rs = stmt.getGeneratedKeys();
	            if (rs.next()) {
	                idFornecedor = rs.getString(1);  
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
	    return idFornecedor; 
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
