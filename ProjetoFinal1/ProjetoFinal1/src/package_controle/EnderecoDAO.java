package package_controle;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import packageConnection.ConnectionDatabase;
import packageModel.Enderecos;

public class EnderecoDAO {

	public String create(Enderecos endereco) {
	    String idEndereco = null;
	    Connection con = ConnectionDatabase.getConnection();
	    PreparedStatement stmt = null;
	    ResultSet rs = null;

	    try {
	        String sql = "INSERT INTO Enderecos (cep, rua, numero, bairro, complemento, cidadeUF) VALUES (?, ?, ?, ?, ?, ?)";
	        stmt = con.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
	        stmt.setString(1, endereco.getCep());
	        stmt.setString(2, endereco.getRua());
	        stmt.setString(3, endereco.getNumero());
	        stmt.setString(4, endereco.getBairro());
	        stmt.setString(5, endereco.getComplemento());
	        stmt.setString(6, endereco.getCidadeUF());

	        int affectedRows = stmt.executeUpdate();
	        if (affectedRows > 0) {
	            rs = stmt.getGeneratedKeys();
	            if (rs.next()) {
	                idEndereco = rs.getString(1);  
	            }
	        }

	    } catch (SQLException e) {
	        e.printStackTrace();
	    } finally {
	        try {
	            if (rs != null) rs.close();
	            if (stmt != null) stmt.close();
	            if (con != null) con.close();
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }

	    return idEndereco;
	}


	public ArrayList<Enderecos> read() {
	    Connection con = ConnectionDatabase.getConnection();
	    PreparedStatement stmt = null;
	    ResultSet rs = null;
	    ArrayList<Enderecos> enderecos = new ArrayList<>();

	    try {
	        stmt = con.prepareStatement("SELECT * FROM Enderecos");
	        rs = stmt.executeQuery();

	        while (rs.next()) {
	            Enderecos end = new Enderecos();
	            end.setIdEndereço(rs.getString("idEndereco")); 
	            end.setCep(rs.getString("cep"));
	            end.setRua(rs.getString("rua"));
	            end.setNumero(rs.getString("numero"));
	            end.setBairro(rs.getString("bairro"));
	            end.setComplemento(rs.getString("complemento"));
	            end.setCidadeUF(rs.getString("cidadeUF"));
	            enderecos.add(end);
	        }

	    } catch (SQLException e) {
	        e.printStackTrace();
	    } finally {
	        ConnectionDatabase.closeConnection(con, stmt, rs);
	    }
	    return enderecos;
	}


    public ArrayList<Enderecos> search(String string) {
        Connection con = ConnectionDatabase.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        ArrayList<Enderecos> enderecos = new ArrayList<>();

        try {
            stmt = con.prepareStatement("SELECT * FROM Enderecos WHERE cidadeUF LIKE ?"); 
            stmt.setString(1, "%" + string + "%");
            rs = stmt.executeQuery();

            while (rs.next()) {
                Enderecos end = new Enderecos();
                end.setIdEndereço(rs.getString("idEndereco"));
                end.setCep(rs.getString("cep"));
                end.setRua(rs.getString("rua"));
                end.setNumero(rs.getString("numero"));
                end.setBairro(rs.getString("bairro"));
                end.setComplemento(rs.getString("complemento"));
                end.setCidadeUF(rs.getString("cidadeUF")); 
                enderecos.add(end);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectionDatabase.closeConnection(con, stmt, rs);
        }
        return enderecos;
    }

    public void update(Enderecos end) {
        Connection con = ConnectionDatabase.getConnection();
        PreparedStatement stmt = null;

        try {
            stmt = con.prepareStatement("UPDATE Enderecos SET cep = ?, rua = ?, numero = ?, bairro = ?, complemento = ?, cidadeUF = ? WHERE idEndereco = ?");
            stmt.setString(1, end.getCep());
            stmt.setString(2, end.getRua());
            stmt.setString(3, end.getNumero());
            stmt.setString(4, end.getBairro());
            stmt.setString(5, end.getComplemento());
            stmt.setString(6, end.getCidadeUF());
            stmt.setString(7, end.getIdEndereço());

            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectionDatabase.closeConnection(con, stmt);
        }
    }

    public void delete(String idEnderecos) {
        Connection con = ConnectionDatabase.getConnection();
        PreparedStatement stmt = null;

        try {
            stmt = con.prepareStatement("DELETE FROM Enderecos WHERE idEndereco = ?");
            stmt.setString(1, idEnderecos);

            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Erro ao excluir o endereço: " + e.getMessage(), e);
        } finally {
            ConnectionDatabase.closeConnection(con, stmt);
        }
    }
}
