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
            stmt = con.prepareStatement("INSERT INTO Produtos (idProduto, nome, codigo, marca, descrição, preçoUnitario, estoqueDisp, estoqueMin, estoqueMax, idCategoria, idVariação) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
            stmt.setString(1, prod.getIdProduto());
            stmt.setString(2, prod.getNome());
            stmt.setString(3, prod.getCodigo());
            stmt.setString(4, prod.getMarca());
            stmt.setString(5, prod.getDescrição());
            stmt.setString(6, prod.getPreçoUnitario());
            stmt.setString(7, prod.getEstoqueDisp());
            stmt.setString(8, prod.getEstoqueMin());
            stmt.setString(9, prod.getEstoqueMax());
            stmt.setString(10, prod.getIdCategoria());
            stmt.setString(11, prod.getIdVariação());

            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectionDatabase.closeConnection(con, stmt);
        }
    }

    public ArrayList<Produtos> read() {
        Connection con = ConnectionDatabase.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        ArrayList<Produtos> produtos = new ArrayList<>();

        try {
            stmt = con.prepareStatement("SELECT * FROM Produtos");
            rs = stmt.executeQuery();

            while (rs.next()) {
                Produtos prod = new Produtos();
                prod.setIdProduto(rs.getString("idProduto"));
                prod.setNome(rs.getString("nome"));
                prod.setCodigo(rs.getString("codigo"));
                prod.setMarca(rs.getString("marca"));
                prod.setDescrição(rs.getString("descrição"));
                prod.setPreçoUnitario(rs.getString("preçoUnitario"));
                prod.setEstoqueDisp(rs.getString("estoqueDisp"));
                prod.setEstoqueMin(rs.getString("estoqueMin"));
                prod.setEstoqueMax(rs.getString("estoqueMax"));
                prod.setIdCategoria(rs.getString("idCategoria"));
                prod.setIdVariação(rs.getString("idVariação"));
                produtos.add(prod);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectionDatabase.closeConnection(con, stmt, rs);
        }
        return produtos;
    }

    public ArrayList<Produtos> search(String string) {
        Connection con = ConnectionDatabase.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        ArrayList<Produtos> produtos = new ArrayList<>();

        try {
            stmt = con.prepareStatement("SELECT * FROM Produtos WHERE nome LIKE ?");
            stmt.setString(1, "%" + string + "%");
            rs = stmt.executeQuery();

            while (rs.next()) {
                Produtos prod = new Produtos();
                prod.setIdProduto(rs.getString("idProduto"));
                prod.setNome(rs.getString("nome"));
                prod.setCodigo(rs.getString("codigo"));
                prod.setMarca(rs.getString("marca"));
                prod.setDescrição(rs.getString("descrição"));
                prod.setPreçoUnitario(rs.getString("preçoUnitario"));
                prod.setEstoqueDisp(rs.getString("estoqueDisp"));
                prod.setEstoqueMin(rs.getString("estoqueMin"));
                prod.setEstoqueMax(rs.getString("estoqueMax"));
                prod.setIdCategoria(rs.getString("idCategoria"));
                prod.setIdVariação(rs.getString("idVariação"));
                produtos.add(prod);
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
            stmt = con.prepareStatement("UPDATE Produtos SET nome = ?, codigo = ?, marca = ?, descrição = ?, preçoUnitario = ?, estoqueDisp = ?, estoqueMin = ?, estoqueMax = ?, idCategoria = ?, idVariação = ? WHERE idProduto = ?");
            stmt.setString(1, prod.getNome());
            stmt.setString(2, prod.getCodigo());
            stmt.setString(3, prod.getMarca());
            stmt.setString(4, prod.getDescrição());
            stmt.setString(5, prod.getPreçoUnitario());
            stmt.setString(6, prod.getEstoqueDisp());
            stmt.setString(7, prod.getEstoqueMin());
            stmt.setString(8, prod.getEstoqueMax());
            stmt.setString(9, prod.getIdCategoria());
            stmt.setString(10, prod.getIdVariação());
            stmt.setString(11, prod.getIdProduto());

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
            throw new RuntimeException("Erro ao excluir o produto: " + e.getMessage(), e);

        } finally {
            ConnectionDatabase.closeConnection(con, stmt);
        }
    }
}

