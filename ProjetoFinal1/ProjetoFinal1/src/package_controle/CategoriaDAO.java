package package_controle;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import packageConnection.ConnectionDatabase;
import packageModel.Categorias;

public class CategoriaDAO {

	public void create(Categorias c) {
        Connection con = ConnectionDatabase.getConnection();
        PreparedStatement stmt = null;

        try {
            stmt = con.prepareStatement("INSERT INTO Categorias (idCategoria, nomeCategoria) VALUES (?, ?)");
            stmt.setString(1, c.getIdCategoria());
            stmt.setString(2, c.getNomeCategoria());

            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectionDatabase.closeConnection(con, stmt);
        }
    }

    public static ArrayList<Categorias> read() {
        Connection con = ConnectionDatabase.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        ArrayList<Categorias> categoria = new ArrayList<>();

        try {
            stmt = con.prepareStatement("SELECT * FROM Categorias");
            rs = stmt.executeQuery();

            while (rs.next()) {
                Categorias c = new Categorias();
                c.setIdCategoria(rs.getString("idCategoria"));
                c.setNomeCategoria(rs.getString("nomeCategoria"));
                categoria.add(c);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectionDatabase.closeConnection(con, stmt, rs);
        }
        return categoria;
    }

    public ArrayList<Categorias> search(String string) {
        Connection con = ConnectionDatabase.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        ArrayList<Categorias> categoria = new ArrayList<>();

        try {
            stmt = con.prepareStatement("SELECT * FROM Categorias WHERE nomeCategoria LIKE ?"); 
            stmt.setString(1, "%" + string + "%");
            rs = stmt.executeQuery();

            while (rs.next()) {
                Categorias c = new Categorias();
                c.setIdCategoria(rs.getString("idCategoria"));
                c.setNomeCategoria(rs.getString("nomeCategoria"));
                categoria.add(c);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectionDatabase.closeConnection(con, stmt, rs);
        }
        return categoria;
    }

    public void update(Categorias c) {
        Connection con = ConnectionDatabase.getConnection();
        PreparedStatement stmt = null;

        try {
            stmt = con.prepareStatement("UPDATE Categorias SET nomeCategoria = ? WHERE idCategoria = ?");
            stmt.setString(1, c.getNomeCategoria());
            stmt.setString(2, c.getIdCategoria());

            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectionDatabase.closeConnection(con, stmt);
        }
    }

    public void delete(String idCategoria) {
        Connection con = ConnectionDatabase.getConnection();
        PreparedStatement stmt = null;

        try {
            stmt = con.prepareStatement("DELETE FROM Categorias WHERE idCategoria = ?");
            stmt.setString(1, idCategoria);

            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectionDatabase.closeConnection(con, stmt);
        }
    }
    
    public String obterIdCategoria(String nomeCategoria) {
        CategoriaDAO categoriaDAO = new CategoriaDAO();
        @SuppressWarnings("static-access")
		ArrayList<Categorias> categorias = categoriaDAO.read();
        
        for (Categorias categoria : categorias) {
            if (categoria.getNomeCategoria().equals(nomeCategoria)) {
                return categoria.getIdCategoria();
            }
        }
        return null; 
    }

    
}
