package package_controle;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import packageConnection.ConnectionDatabase;
import packageModel.Produtos;

public class ProdutoDAO {
	public void create(Produtos prod) {
	    Connection con = ConnectionDatabase.getConnection();
	    PreparedStatement stmt = null;

	    try {
	        stmt = con.prepareStatement("INSERT INTO Produtos (nome, codigo, marca, descricao, precoUnitario, estoqueDisp, id_Categoria) VALUES (?, ?, ?, ?, ?, ?, ?)");
	        stmt.setString(1, prod.getNome());
	        stmt.setString(2, prod.getCodigo());
	        stmt.setString(3, prod.getMarca());
	        stmt.setString(4, prod.getDescricao());
	        stmt.setDouble(5, prod.getPrecoUnitario());
	        stmt.setInt(6, prod.getEstoqueDisp());
	        stmt.setString(7, prod.getId_Categoria()); 

	        stmt.executeUpdate();
	        
	    } catch (SQLException e) {
	        e.printStackTrace();
	    } finally {
	        ConnectionDatabase.closeConnection(con, stmt);
	    }
	}

	
	public ArrayList<Produtos> read() {
	    ArrayList<Produtos> produtos = new ArrayList<>();
	    Connection con = ConnectionDatabase.getConnection();
	    PreparedStatement stmt = null;
	    ResultSet rs = null;

	    try {
	        stmt = con.prepareStatement("SELECT p.*, c.nomeCategoria FROM Produtos p JOIN Categorias c ON p.id_Categoria = c.idCategoria");
	        rs = stmt.executeQuery();

	        while (rs.next()) {
	            Produtos p = new Produtos();
	            p.setIdProduto(rs.getString("idProduto"));
	            p.setNome(rs.getString("nome"));
	            p.setCodigo(rs.getString("codigo"));
	            p.setMarca(rs.getString("marca"));
	            p.setDescricao(rs.getString("descricao"));
	            p.setPrecoUnitario(rs.getDouble("precoUnitario"));
	            p.setEstoqueDisp(rs.getInt("estoqueDisp"));
	            p.setId_Categoria(rs.getString("id_Categoria"));
	            p.setCategoriaNome(rs.getString("nomeCategoria")); 
	            produtos.add(p);
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    } finally {
	        ConnectionDatabase.closeConnection(con, stmt, rs);
	    }
	    return produtos;
	}



    public static ArrayList<Produtos> search(String pesquisa) {
        Connection con = ConnectionDatabase.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        ArrayList<Produtos> produtos = new ArrayList<>();

        try {
            stmt = con.prepareStatement("SELECT * FROM Produtos WHERE nome LIKE ?");
            stmt.setString(1, "%" + pesquisa + "%");
            rs = stmt.executeQuery();

            while (rs.next()) {
                Produtos p = new Produtos();
                p.setIdProduto(rs.getString("idProduto"));
                p.setNome(rs.getString("nome"));
                p.setCodigo(rs.getString("codigo"));
                p.setMarca(rs.getString("marca"));
                p.setDescricao(rs.getString("descricao"));
                p.setPrecoUnitario(rs.getDouble("precoUnitario"));
                p.setEstoqueDisp(rs.getInt("estoqueDisp"));
                produtos.add(p);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectionDatabase.closeConnection(con, stmt, rs);
        }
        return produtos;
    }

    public ArrayList<Produtos> buscarProdutosPorCategoria(String idCategoria) {
        Connection con = ConnectionDatabase.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        ArrayList<Produtos> produtos = new ArrayList<>();

        try {
            stmt = con.prepareStatement("SELECT * FROM Produtos WHERE id_Categoria = ?");
            stmt.setString(1, idCategoria);
            rs = stmt.executeQuery();

            while (rs.next()) {
                Produtos p = new Produtos();
                p.setIdProduto(rs.getString("idProduto"));
                p.setNome(rs.getString("nome"));
                p.setCodigo(rs.getString("codigo"));
                p.setMarca(rs.getString("marca"));
                p.setDescricao(rs.getString("descricao"));
                p.setPrecoUnitario(rs.getDouble("precoUnitario"));
                p.setEstoqueDisp(rs.getInt("estoqueDisp"));
                produtos.add(p);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectionDatabase.closeConnection(con, stmt, rs);
        }
        return produtos;
    }

    public void update(Produtos prod) {
        Connection con = ConnectionDatabase.getConnection();
        PreparedStatement stmt = null;

        try {
            stmt = con.prepareStatement("UPDATE Produtos SET nome = ?, codigo = ?, marca = ?, descricao = ?, precoUnitario = ?, estoqueDisp = ? WHERE idProduto = ?");
            stmt.setString(1, prod.getNome());
            stmt.setString(2, prod.getCodigo());
            stmt.setString(3, prod.getMarca());
            stmt.setString(4, prod.getDescricao());
            stmt.setDouble(5, prod.getPrecoUnitario());
            stmt.setInt(6, prod.getEstoqueDisp());
            stmt.setString(7, prod.getIdProduto()); // Corrigido

            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectionDatabase.closeConnection(con, stmt);
        }
    }

    public void delete(String idProduto) {
        Connection con = ConnectionDatabase.getConnection();
        PreparedStatement stmt = null;

        try {
            stmt = con.prepareStatement("DELETE FROM Produtos WHERE idProduto = ?");
            stmt.setString(1, idProduto);

            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectionDatabase.closeConnection(con, stmt);
        }
    }
    
    
}
