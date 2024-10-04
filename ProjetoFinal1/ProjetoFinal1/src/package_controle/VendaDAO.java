package package_controle;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import packageConnection.ConnectionDatabase;
import packageModel.Vendas;

public class VendaDAO {

    public void create(Vendas venda) {
        Connection con = ConnectionDatabase.getConnection();
        PreparedStatement stmt = null;

        try {
            stmt = con.prepareStatement("INSERT INTO Vendas (idVenda, dataVenda, codigoProduto, quantidade, desconto, subtotal, valorTotal, statusVenda, canalVendas, id_Pagamento, cpf_Cliente, cpf_Funcionário) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
            stmt.setString(1, venda.getIdVenda());
            stmt.setString(2, venda.getDataVenda());
            stmt.setString(3, venda.getCodigoProduto());
            stmt.setString(4, venda.getQuantidade());
            stmt.setString(5, venda.getDesconto());
            stmt.setString(6, venda.getSubtotal());
            stmt.setString(7, venda.getValorTotal());
            stmt.setString(8, venda.getStatusVenda());
            stmt.setString(9, venda.getCanalVendas());
            stmt.setString(10, venda.getId_Pagamento());
            stmt.setString(11, venda.getCpf_Cliente());
            stmt.setString(12, venda.getCpf_Funcionário());

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
                venda.setQuantidade(rs.getString("quantidade"));
                venda.setDesconto(rs.getString("desconto"));
                venda.setSubtotal(rs.getString("subtotal"));
                venda.setValorTotal(rs.getString("valorTotal"));
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

    public ArrayList<Vendas> search(String string) {
        Connection con = ConnectionDatabase.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        ArrayList<Vendas> vendas = new ArrayList<>();

        try {
            stmt = con.prepareStatement("SELECT * FROM Vendas WHERE codigoProduto LIKE ?");
            stmt.setString(1, "%" + string + "%");
            rs = stmt.executeQuery();

            while (rs.next()) {
                Vendas venda = new Vendas();
                venda.setIdVenda(rs.getString("idVenda"));
                venda.setDataVenda(rs.getString("dataVenda"));
                venda.setCodigoProduto(rs.getString("codigoProduto"));
                venda.setQuantidade(rs.getString("quantidade"));
                venda.setDesconto(rs.getString("desconto"));
                venda.setSubtotal(rs.getString("subtotal"));
                venda.setValorTotal(rs.getString("valorTotal"));
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
            stmt = con.prepareStatement("UPDATE Vendas SET dataVenda = ?, codigoProduto = ?, quantidade = ?, desconto = ?, subtotal = ?, valorTotal = ?, statusVenda = ?, canalVendas = ?, id_Pagamento = ?, cpf_Cliente = ?, cpf_Funcionário = ? WHERE idVenda = ?");
            stmt.setString(1, venda.getDataVenda());
            stmt.setString(2, venda.getCodigoProduto());
            stmt.setString(3, venda.getQuantidade());
            stmt.setString(4, venda.getDesconto());
            stmt.setString(5, venda.getSubtotal());
            stmt.setString(6, venda.getValorTotal());
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
