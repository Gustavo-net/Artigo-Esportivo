package package_controle;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import packageConnection.ConnectionDatabase;
import packageModel.Produtos;
import packageModel.Vendas;

public class VendaDAO {

public void create(Vendas venda) {
        Connection con = ConnectionDatabase.getConnection();
        PreparedStatement stmt = null;

        try {
            stmt = con.prepareStatement(
                "INSERT INTO Vendas ( dataVenda, codigoProduto, quantidade, desconto, subtotal, valorTotal, statusVenda, canalVendas, id_Pagamento, cpf_Cliente, cpf_Funcionário, nomeProduto ) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");

            stmt.setString(1, venda.getDataVenda());
            stmt.setString(2, venda.getCodigoProduto());
            stmt.setInt(3, venda.getQuantidade());  
            stmt.setDouble(4, venda.getDesconto()); 
            stmt.setDouble(5, venda.getSubtotal()); 
            stmt.setDouble(6, venda.getValorTotal()); 
            stmt.setString(7, venda.getStatusVenda());
            stmt.setString(8, venda.getCanalVendas());
            stmt.setString(9, venda.getId_Pagamento());
            stmt.setString(10, venda.getCpf_Cliente());
            stmt.setString(11, venda.getCpf_Funcionário());
            stmt.setString(12, venda.getNomeProduto());  
            
            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectionDatabase.closeConnection(con, stmt);
        }
    }

public ArrayList<Vendas> read() {
    Connection con = ConnectionDatabase.getConnection();
    PreparedStatement stmt = null;
    ResultSet rs = null;
    ArrayList<Vendas> vendas = new ArrayList<>();

    try {
        stmt = con.prepareStatement("SELECT * FROM Vendas");
        rs = stmt.executeQuery();

        while (rs.next()) {
            Vendas venda = new Vendas();
            venda.setIdVenda(rs.getString("idVenda"));
            venda.setDataVenda(rs.getString("dataVenda"));
            venda.setCodigoProduto(rs.getString("codigoProduto"));
            venda.setQuantidade(rs.getInt("quantidade"));
            venda.setDesconto(rs.getDouble("desconto"));
            venda.setSubtotal(rs.getDouble("subtotal"));
            venda.setValorTotal(rs.getDouble("valorTotal"));
            venda.setStatusVenda(rs.getString("statusVenda"));
            venda.setCanalVendas(rs.getString("canalVendas"));
            venda.setId_Pagamento(rs.getString("id_Pagamento"));
            venda.setCpf_Cliente(rs.getString("cpf_Cliente"));
            venda.setCpf_Funcionário(rs.getString("cpf_Funcionário"));

            ProdutoDAO produtoDAO = new ProdutoDAO();
            Produtos produto = produtoDAO.buscarProdutoPorCodigo(venda.getCodigoProduto());
            if (produto != null) {
                venda.setNomeProduto(produto.getNome());
            }
            }

    } catch (SQLException e) {
        e.printStackTrace();
    } finally {
        ConnectionDatabase.closeConnection(con, stmt, rs);
    }

    return vendas;
}
public static ArrayList<Vendas> search(String codigoProduto) {
    Connection con = ConnectionDatabase.getConnection();
    PreparedStatement stmt = null;
    ResultSet rs = null;
    ArrayList<Vendas> vendas = new ArrayList<>();

    try {
        stmt = con.prepareStatement("SELECT * FROM Vendas WHERE codigoProduto LIKE ?");
        stmt.setString(1, "%" + codigoProduto + "%");
        rs = stmt.executeQuery();

        while (rs.next()) {
            Vendas venda = new Vendas();
            venda.setIdVenda(rs.getString("idVenda"));
            venda.setDataVenda(rs.getString("dataVenda"));
            venda.setCodigoProduto(rs.getString("codigoProduto"));
            venda.setQuantidade(rs.getInt("quantidade")); // quantidade como int
            venda.setDesconto(rs.getDouble("desconto")); // desconto como double
            venda.setSubtotal(rs.getDouble("subtotal")); // subtotal como double
            venda.setValorTotal(rs.getDouble("valorTotal")); // valorTotal como double
            venda.setStatusVenda(rs.getString("statusVenda"));
            venda.setCanalVendas(rs.getString("canalVendas"));
            venda.setId_Pagamento(rs.getString("id_Pagamento"));
            venda.setCpf_Cliente(rs.getString("cpf_Cliente"));
            venda.setCpf_Funcionário(rs.getString("cpf_Funcionário"));
            vendas.add(venda);
        }

    } catch (SQLException e) {
        e.printStackTrace();
    } finally {
        ConnectionDatabase.closeConnection(con, stmt, rs);
    }

    return vendas;
}

public void update(Vendas venda) {
    Connection con = ConnectionDatabase.getConnection();
    PreparedStatement stmt = null;

    try {
        stmt = con.prepareStatement(
            "UPDATE Vendas SET dataVenda = ?, codigoProduto = ?, quantidade = ?, desconto = ?, subtotal = ?, valorTotal = ?, statusVenda = ?, canalVendas = ?, id_Pagamento = ?, cpf_Cliente = ?, cpf_Funcionário = ? WHERE idVenda = ?");

        stmt.setString(1, venda.getDataVenda());
        stmt.setString(2, venda.getCodigoProduto());
        stmt.setInt(3, venda.getQuantidade()); 
        stmt.setDouble(4, venda.getDesconto()); 
        stmt.setDouble(5, venda.getSubtotal()); 
        stmt.setDouble(6, venda.getValorTotal()); 
        stmt.setString(7, venda.getStatusVenda());
        stmt.setString(8, venda.getCanalVendas());
        stmt.setString(9, venda.getId_Pagamento());
        stmt.setString(10, venda.getCpf_Cliente());
        stmt.setString(11, venda.getCpf_Funcionário());
        stmt.setString(12, venda.getIdVenda());

        stmt.executeUpdate();

    } catch (SQLException e) {
        e.printStackTrace();
    } finally {
        ConnectionDatabase.closeConnection(con, stmt);
    }
}

public void delete(String idVenda) {
    Connection con = ConnectionDatabase.getConnection();
    PreparedStatement stmt = null;

    try {
        stmt = con.prepareStatement("DELETE FROM Vendas WHERE idVenda = ?");
        stmt.setString(1, idVenda);
        stmt.executeUpdate();

    } catch (SQLException e) {
        e.printStackTrace();
        throw new RuntimeException("Erro ao excluir a venda: " + e.getMessage(), e);
    } finally {
        ConnectionDatabase.closeConnection(con, stmt);
    }
}
}
