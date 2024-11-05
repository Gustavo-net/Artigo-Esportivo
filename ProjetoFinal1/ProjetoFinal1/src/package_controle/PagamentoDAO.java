package package_controle;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import packageModel.Pagamentos;

public class PagamentoDAO {
    
    private Connection connection;

    public PagamentoDAO(Connection connection) {
        this.connection = connection;
    }

    public void adicionarPagamento(Pagamentos pagamento) {
        String sql = "INSERT INTO Pagamentos (idPagamento, metodoPagamento, valor, parcelas, id_Venda, status) VALUES (?, ?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, pagamento.getIdPagamento());
            stmt.setString(2, pagamento.getMetodoPagamento());
            stmt.setDouble(3, pagamento.getValor());
            stmt.setInt(4, pagamento.getParcelas());
            stmt.setString(5, pagamento.getIdVenda());
            stmt.setString(6, pagamento.getStatus());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Pagamentos buscarPagamentoPorId(String idPagamento) {
        String sql = "SELECT * FROM Pagamentos WHERE idPagamento = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, idPagamento);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Pagamentos(
                    rs.getString("idPagamento"),
                    rs.getString("metodoPagamento"),
                    rs.getDouble("valor"),
                    rs.getInt("parcelas"),
                    rs.getString("id_Venda"),
                    rs.getString("status")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void atualizarPagamento(Pagamentos pagamento) {
        String sql = "UPDATE Pagamentos SET metodoPagamento = ?, valor = ?, parcelas = ?, id_Venda = ?, status = ? WHERE idPagamento = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, pagamento.getMetodoPagamento());
            stmt.setDouble(2, pagamento.getValor());
            stmt.setInt(3, pagamento.getParcelas());
            stmt.setString(4, pagamento.getIdVenda());
            stmt.setString(5, pagamento.getStatus());
            stmt.setString(6, pagamento.getIdPagamento());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deletarPagamento(String idPagamento) {
        String sql = "DELETE FROM Pagamentos WHERE idPagamento = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, idPagamento);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Pagamentos> listarPagamentos() {
        List<Pagamentos> pagamentos = new ArrayList<>();
        String sql = "SELECT * FROM Pagamentos";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Pagamentos pagamento = new Pagamentos(
                    rs.getString("idPagamento"),
                    rs.getString("metodoPagamento"),
                    rs.getDouble("valor"),
                    rs.getInt("parcelas"),
                    rs.getString("id_Venda"),
                    rs.getString("status")
                );
                pagamentos.add(pagamento);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return pagamentos;
    }
}
