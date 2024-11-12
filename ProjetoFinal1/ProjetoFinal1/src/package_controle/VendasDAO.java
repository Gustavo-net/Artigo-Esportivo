package package_controle;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import packageConnection.ConnectionDatabase;

import packageModel.ItemVenda;
import packageModel.Produtos;
import packageModel.Venda;

public class VendasDAO {
    private Connection connection;

    public VendasDAO() {
        this.connection = ConnectionDatabase.getConnection();
        if (this.connection == null) {
            throw new IllegalStateException("A conexão com o banco de dados não pôde ser estabelecida.");
        }
    }

    public void inserirVenda(Venda venda, List<ItemVenda> itensVenda) throws SQLException {
        String insertVendaSQL = "INSERT INTO Vendas (cpfCliente, cpfFuncionario, dataVenda, totalVenda, formaPagamento, metodoPagamento) VALUES (?, ?, ?, ?, ?, ?)";
        String insertItemVendaSQL = "INSERT INTO ItensVenda (idVenda, codigoProduto, quantidade, precoUnitario) VALUES (?, ?, ?, ?)";  

        try (PreparedStatement psVenda = connection.prepareStatement(insertVendaSQL, Statement.RETURN_GENERATED_KEYS)) {
            psVenda.setString(1, venda.getCpfCliente());
            psVenda.setString(2, venda.getCpfFuncionario());
            psVenda.setTimestamp(3, new Timestamp(venda.getDataVenda().getTime()));
            psVenda.setDouble(4, venda.getTotalVenda());  
            psVenda.setString(5, venda.getFormaPagamento());
            psVenda.setString(6, venda.getMetodoPagamento());

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
                                psItemVenda.setDouble(4, item.getPrecoUnitario());
                                psItemVenda.executeUpdate();
                            }
                        }
                    }
                }
            }
        }
    }
    public String buscarNomeClientePorCPF(String cpfCliente) throws SQLException {
        String nomeCliente = "";
        String sql = "SELECT nomeCliente FROM Clientes WHERE cpf = ?";

        try (Connection conn = ConnectionDatabase.getConnection(); 
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, cpfCliente);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    nomeCliente = rs.getString("nomeCliente");
                }
            }
        }

        return nomeCliente;
    }
    
    public String buscarNomeFuncionarioPorCPF(String cpfFuncionario) throws SQLException {
        String nomeFuncionario = null;
        String sql = "SELECT nomeFuncionario FROM Funcionarios WHERE cpf = ?";

        try (Connection conn = ConnectionDatabase.getConnection(); 
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, cpfFuncionario); 
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    nomeFuncionario = rs.getString("nomeFuncionario"); 
                }
            }
        }
        return nomeFuncionario;
    }
    
    public List<Venda> listarVendas() throws SQLException {
        List<Venda> vendas = new ArrayList<>();
        String sql = "SELECT v.idVenda, v.cpfCliente, v.cpfFuncionario, v.dataVenda, v.totalVenda, v.formaPagamento, metodoPagamento," +
                     "iv.idItemVenda, iv.codigoProduto, iv.quantidade, iv.precoUnitario, iv.subtotal " +
                     "FROM Vendas v " +
                     "INNER JOIN ItensVenda iv ON v.idVenda = iv.idVenda";

        try (Connection conn = ConnectionDatabase.getConnection(); 
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                int idVenda = rs.getInt("idVenda");
                Venda venda = null;

                for (Venda v : vendas) {
                    if (v.getIdVenda() == idVenda) {
                        venda = v;
                        break;
                    }
                }
                if (venda == null) {
                    venda = new Venda();
                    venda.setIdVenda(idVenda);
                    venda.setCpfCliente(rs.getString("cpfCliente"));
                    venda.setCpfFuncionario(rs.getString("cpfFuncionario"));
                    venda.setDataVenda(rs.getDate("dataVenda"));
                    venda.setTotalVenda(rs.getDouble("totalVenda"));
                    venda.setFormaPagamento(rs.getString("formaPagamento"));
                    venda.setMetodoPagamento(rs.getString("metodoPagamento"));
                    venda.setItensVenda(new ArrayList<>());
                    vendas.add(venda);
                }

                ItemVenda itemVenda = new ItemVenda();
                itemVenda.setIdItemVenda(rs.getInt("idItemVenda"));
                itemVenda.setIdVenda(idVenda);
                itemVenda.setCodigoProduto(rs.getString("codigoProduto"));
                itemVenda.setQuantidade(rs.getInt("quantidade"));
                itemVenda.setPrecoUnitario(rs.getDouble("precoUnitario"));
                itemVenda.setSubtotal(rs.getDouble("subtotal"));

                venda.getItensVenda().add(itemVenda);
            }
        }

        return vendas;
    }


    public Venda buscarVendaPorId(int idVenda) throws SQLException {
        String queryVenda = "SELECT v.idVenda, v.cpfCliente, v.cpfFuncionario, v.dataVenda, v.totalVenda, v.formaPagamento,  v.metodoPagamento " +
                             "FROM Vendas v WHERE v.idVenda = ?";
        String queryItensVenda = "SELECT iv.idItemVenda, iv.idVenda, iv.codigoProduto, iv.quantidade, iv.precoUnitario, iv.subtotal " +
                                 "FROM ItensVenda iv WHERE iv.idVenda = ?";

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
                        venda.setDataVenda(new java.sql.Date(timestamp.getTime()));  
                    } else {
                        venda.setDataVenda(null); 
                    }
                    
                    venda.setTotalVenda(rsVenda.getDouble("totalVenda"));
                    venda.setFormaPagamento(rsVenda.getString("formaPagamento"));
                    venda.setMetodoPagamento(rsVenda.getString("metodoPagamento"));


                    try (PreparedStatement psItensVenda = connection.prepareStatement(queryItensVenda)) {
                        psItensVenda.setInt(1, idVenda); 
                        
                        try (ResultSet rsItensVenda = psItensVenda.executeQuery()) {
                            List<ItemVenda> itensVenda = new ArrayList<>();
                            
                            while (rsItensVenda.next()) {
                                ItemVenda item = new ItemVenda();
                                item.setIdItemVenda(rsItensVenda.getInt("idItemVenda"));
                                item.setIdVenda(rsItensVenda.getInt("idVenda"));
                                item.setCodigoProduto(rsItensVenda.getString("codigoProduto"));
                                item.setQuantidade(rsItensVenda.getInt("quantidade"));
                                item.setPrecoUnitario(rsItensVenda.getDouble("precoUnitario"));
                                item.setSubtotal(rsItensVenda.getDouble("subtotal"));
                                itensVenda.add(item);
                            }
                            
                            venda.setItensVenda(itensVenda);
                        }
                    }

                    return venda; 
                }
            }
        } catch (SQLException e) {
            throw new SQLException("Erro ao buscar a venda com ID: " + idVenda, e);
        }

        return null;  
    }


    public void atualizarVenda(Venda venda) throws SQLException {
        String updateSQL = "UPDATE Vendas SET cpfCliente = ?, cpfFuncionario = ?, dataVenda = ?, totalVenda = ?, formaPagamento = ?, formaPagamento = ? WHERE idVenda = ?";

        try (PreparedStatement ps = connection.prepareStatement(updateSQL)) {
            ps.setString(1, venda.getCpfCliente());
            ps.setString(2, venda.getCpfFuncionario());
            ps.setTimestamp(3, new Timestamp(venda.getDataVenda().getTime()));
            ps.setDouble(4, venda.getTotalVenda());
            ps.setString(5, venda.getFormaPagamento());
            ps.setString(6, venda.getMetodoPagamento());
            ps.setInt(7, venda.getIdVenda());
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

    public Produtos buscarProdutoPorCodigo(String codigoProduto) throws SQLException {
        Produtos produto = null;
        Connection conn = ConnectionDatabase.getConnection();
        String sql = "SELECT * FROM produtos WHERE codigo = ?";
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setString(1, codigoProduto); 

        ResultSet rs = stmt.executeQuery();
        if (rs.next()) {
            produto = new Produtos();
            produto.setCodigo(rs.getString("codigo"));
            produto.setDescricao(rs.getString("descricao"));
            produto.setPrecoUnitario(rs.getDouble("precoUnitario"));
        }
        rs.close();
        stmt.close();
        conn.close();
        
        return produto;
    }

}
