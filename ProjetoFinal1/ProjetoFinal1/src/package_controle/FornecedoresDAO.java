package package_controle;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import packageConnection.ConnectionDatabase;
import packageModel.Fornecedores;

public class FornecedoresDAO {
	public void create(Fornecedores f) {
        Connection con = ConnectionDatabase.getConnection();
        PreparedStatement stmt = null;

        try {
            stmt = con.prepareStatement("INSERT INTO Fornecedores (idFornecedor, nomeFornecedor, cnpj, email, telefone, id_Endereco) VALUES (?, ?, ?, ?, ?, ?)");
            stmt.setString(1, f.getIdFornecedor());
            stmt.setString(2, f.getNomeFornecedor());
            stmt.setString(3, f.getCnpj());
            stmt.setString(4, f.getEmail());
            stmt.setString(5, f.getTelefone());
            stmt.setString(6, f.getId_Endereço());

            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectionDatabase.closeConnection(con, stmt);
        }
    }

    public ArrayList<Fornecedores> read() {
        Connection con = ConnectionDatabase.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        ArrayList<Fornecedores> fornecedores = new ArrayList<>();

        try {
            stmt = con.prepareStatement("SELECT * FROM Fornecedores");
            rs = stmt.executeQuery();

            while (rs.next()) {
            	Fornecedores f = new Fornecedores();
                f.setIdFornecedor(rs.getString("idFornecedor"));
                f.setNomeFornecedor(rs.getString("nomeFornecedor"));
                f.setCnpj(rs.getString("cnpj"));
                f.setEmail(rs.getString("email"));
                f.setTelefone(rs.getString("telefone"));
                f.setId_Endereço(rs.getString("id_Endereco"));
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
            stmt = con.prepareStatement("SELECT * FROM Fornecedores WHERE nomeFornecedor LIKE ?"); 
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
            stmt = con.prepareStatement("UPDATE Fornecedores SET nomeFornecedor = ?, cnpj = ?, email = ?, telefone = ?, id_Endereco = ? WHERE idFornecedor = ?");
            stmt.setString(1, f.getNomeFornecedor());
            stmt.setString(2, f.getCnpj());
            stmt.setString(3, f.getEmail());
            stmt.setString(4, f.getTelefone());
            stmt.setString(5, f.getId_Endereço());
            stmt.setString(6, f.getIdFornecedor());

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
            stmt = con.prepareStatement("DELETE FROM Fornecedores WHERE idFornecedores = ?");
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
