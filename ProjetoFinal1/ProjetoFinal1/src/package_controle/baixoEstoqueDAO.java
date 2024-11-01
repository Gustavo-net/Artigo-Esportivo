package package_controle; // Alterar para o pacote correto

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import packageConnection.ConnectionDatabase;
import packageModel.ProdutoBaixoEstoque;

public class baixoEstoqueDAO { // Nome da classe em CamelCase

    public static ArrayList<ProdutoBaixoEstoque> read() {
        Connection con = ConnectionDatabase.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        ArrayList<ProdutoBaixoEstoque> produtosBaixoEstoque = new ArrayList<>();

        try {
            stmt = con.prepareStatement("SELECT * FROM ProdutoBaixoEstoque");
            rs = stmt.executeQuery();

            while (rs.next()) {
                ProdutoBaixoEstoque produto = new ProdutoBaixoEstoque();
                produto.setNome(rs.getString("nome")); 
                produto.setCodigo(rs.getString("codigo"));
                produto.setMarca(rs.getString("marca"));
                produto.setCategoria(rs.getString("categoria"));
                produto.setQuantidadeEstoque(rs.getInt("quantidadeEstoque")); 
                produtosBaixoEstoque.add(produto);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectionDatabase.closeConnection(con, stmt, rs); // Verifique se esse método trata null
        }
        return produtosBaixoEstoque;
    }
}
