package package_controle;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Date;

import packageModel.ItemVenda;
import packageModel.Venda;

public class VendasDAO {
    private Connection connection;

    public VendasDAO(Connection connection) {
        this.connection = connection;
    }

    public void inserirVenda(Venda venda, List<ItemVenda> itensVenda) throws SQLException {
        String insertVendaSQL = "INSERT INTO Vendas (cpfCliente, cpfFuncionario, dataVenda, totalVenda, formaPagamento) VALUES (?, ?, ?, ?, ?)";
        String insertItemVendaSQL = "INSERT INTO ItensVenda (idVenda, codigoProduto, quantidade, precoUnitario, subtotal) VALUES (?, ?, ?, ?, ?)";

        try (PreparedStatement psVenda = connection.prepareStatement(insertVendaSQL, Statement.RETURN_GENERATED_KEYS)) {
            // Inserir a venda
            psVenda.setString(1, venda.getCpfCliente());
            psVenda.setString(2, venda.getCpfFuncionario());
            psVenda.setTimestamp(3, new Timestamp(venda.getDataVenda().getTime()));
            psVenda.setBigDecimal(4, venda.getTotalVenda());
            psVenda.setString(5, venda.getFormaPagamento());

            int affectedRows = psVenda.executeUpdate();

            if (affectedRows > 0) {
                try (ResultSet generatedKeys = psVenda.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        int idVenda = generatedKeys.getInt(1);
                        venda.setIdVenda(idVenda); 

                        try (PreparedStatement psItemVenda = connection.prepareStatement(insertItemVendaSQL)) {
                            for (ItemVenda item : itensVenda) {
                                psItemVenda.setInt(1, idVenda);  
                                psItemVenda.setString(2, item.getCodigoProduto());
                                psItemVenda.setInt(3, item.getQuantidade());
                                psItemVenda.setBigDecimal(4, item.getPrecoUnitario());
                                psItemVenda.setBigDecimal(5, item.getSubtotal()); 
                                psItemVenda.executeUpdate();
                            }
                        }
                    }
                }
            }
        }
    }

    public List<Venda> listarVendas() throws SQLException {
        List<Venda> vendas = new ArrayList<>();
        String queryVendas = "SELECT * FROM Vendas";
        String queryItensVenda = "SELECT * FROM ItensVenda WHERE idVenda = ?";

        try (Statement stmtVendas = connection.createStatement();
             ResultSet rsVendas = stmtVendas.executeQuery(queryVendas)) {

            while (rsVendas.next()) {
                Venda venda = new Venda();
                venda.setIdVenda(rsVendas.getInt("idVenda"));
                venda.setCpfCliente(rsVendas.getString("cpfCliente"));
                venda.setCpfFuncionario(rsVendas.getString("cpfFuncionario"));

                // Conversão de Timestamp para Date
                Timestamp timestamp = rsVendas.getTimestamp("dataVenda");
                if (timestamp != null) {
                    venda.setDataVenda(new Date(timestamp.getTime())); 
                }

                venda.setTotalVenda(rsVendas.getBigDecimal("totalVenda"));
                venda.setFormaPagamento(rsVendas.getString("formaPagamento"));

                // Buscar os itens da venda
                try (PreparedStatement psItensVenda = connection.prepareStatement(queryItensVenda)) {
                    psItensVenda.setInt(1, venda.getIdVenda());
                    try (ResultSet rsItensVenda = psItensVenda.executeQuery()) {
                        List<ItemVenda> itensVenda = new ArrayList<>();
                        while (rsItensVenda.next()) {
                            ItemVenda item = new ItemVenda();
                            item.setIdItemVenda(rsItensVenda.getInt("idItemVenda"));
                            item.setIdVenda(rsItensVenda.getInt("idVenda"));
                            item.setCodigoProduto(rsItensVenda.getString("codigoProduto"));
                            item.setQuantidade(rsItensVenda.getInt("quantidade"));
                            item.setPrecoUnitario(rsItensVenda.getBigDecimal("precoUnitario"));
                            item.setSubtotal(rsItensVenda.getBigDecimal("subtotal"));
                            itensVenda.add(item);
                        }
                        venda.setItensVenda(itensVenda);  
                    }
                }

                vendas.add(venda);
            }
        }
        return vendas;
    }

    public Venda buscarVendaPorId(int idVenda) throws SQLException {
        String queryVenda = "SELECT * FROM Vendas WHERE idVenda = ?";
        String queryItensVenda = "SELECT * FROM ItensVenda WHERE idVenda = ?";

        try (PreparedStatement psVenda = connection.prepareStatement(queryVenda)) {
            psVenda.setInt(1, idVenda);
            try (ResultSet rsVenda = psVenda.executeQuery()) {
                if (rsVenda.next()) {
                    Venda venda = new Venda();
                    venda.setIdVenda(rsVenda.getInt("idVenda"));
                    venda.setCpfCliente(rsVenda.getString("cpfCliente"));
                    venda.setCpfFuncionario(rsVenda.getString("cpfFuncionario"));

                    Timestamp timestamp = rsVenda.getTimestamp("dataVenda");
                    if (timestamp != null) {
                        venda.setDataVenda(new Date(timestamp.getTime()));  
                    }

                    venda.setTotalVenda(rsVenda.getBigDecimal("totalVenda"));
                    venda.setFormaPagamento(rsVenda.getString("formaPagamento"));

                    try (PreparedStatement psItensVenda = connection.prepareStatement(queryItensVenda)) {
                        psItensVenda.setInt(1, venda.getIdVenda());
                        try (ResultSet rsItensVenda = psItensVenda.executeQuery()) {
                            List<ItemVenda> itensVenda = new ArrayList<>();
                            while (rsItensVenda.next()) {
                                ItemVenda item = new ItemVenda();
                                item.setIdItemVenda(rsItensVenda.getInt("idItemVenda"));
                                item.setIdVenda(rsItensVenda.getInt("idVenda"));
                                item.setCodigoProduto(rsItensVenda.getString("codigoProduto"));
                                item.setQuantidade(rsItensVenda.getInt("quantidade"));
                                item.setPrecoUnitario(rsItensVenda.getBigDecimal("precoUnitario"));
                                item.setSubtotal(rsItensVenda.getBigDecimal("subtotal"));
                                itensVenda.add(item);
                            }
                            venda.setItensVenda(itensVenda);  
                        }
                    }
                    return venda;
                }
            }
        }
        return null;
    }

    public void atualizarVenda(Venda venda) throws SQLException {
        String updateSQL = "UPDATE Vendas SET cpfCliente = ?, cpfFuncionario = ?, dataVenda = ?, totalVenda = ?, formaPagamento = ? WHERE idVenda = ?";

        try (PreparedStatement ps = connection.prepareStatement(updateSQL)) {
            ps.setString(1, venda.getCpfCliente());
            ps.setString(2, venda.getCpfFuncionario());
            ps.setTimestamp(3, new Timestamp(venda.getDataVenda().getTime()));  
            ps.setBigDecimal(4, venda.getTotalVenda());
            ps.setString(5, venda.getFormaPagamento());
            ps.setInt(6, venda.getIdVenda());
            ps.executeUpdate();
        }
    }

    public void deletarVenda(int idVenda) throws SQLException {
        String deleteItensVendaSQL = "DELETE FROM ItensVenda WHERE idVenda = ?";
        String deleteVendaSQL = "DELETE FROM Vendas WHERE idVenda = ?";

        try (PreparedStatement psDeleteItens = connection.prepareStatement(deleteItensVendaSQL)) {
            psDeleteItens.setInt(1, idVenda);
            psDeleteItens.executeUpdate();
        }

        try (PreparedStatement psDeleteVenda = connection.prepareStatement(deleteVendaSQL)) {
            psDeleteVenda.setInt(1, idVenda);
            psDeleteVenda.executeUpdate();
        }
    }
}
