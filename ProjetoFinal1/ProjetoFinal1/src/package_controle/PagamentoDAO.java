package package_controle;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import packageConnection.ConnectionDatabase;
import packageModel.Pagamentos;

public class PagamentoDAO {

    public void create(Pagamentos pag) {
        Connection con = ConnectionDatabase.getConnection();
        PreparedStatement stmt = null;

        try {
            stmt = con.prepareStatement("INSERT INTO Pagamentos (idPagamento, metodoPagamento, statusPagamento, dataPagamento) VALUES (?, ?, ?, ?)");
            stmt.setString(1, pag.getIdPagamento());
            stmt.setString(2, pag.getMetodoPagamento());
            stmt.setString(3, pag.getStatusPagamento());
            stmt.setString(4, pag.getDataPagamento());

            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectionDatabase.closeConnection(con, stmt);
        }
    }

    public ArrayList<Pagamentos> read() {
        Connection con = ConnectionDatabase.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        ArrayList<Pagamentos> pagamentos = new ArrayList<>();

        try {
            stmt = con.prepareStatement("SELECT * FROM Pagamentos");
            rs = stmt.executeQuery();

            while (rs.next()) {
                Pagamentos pag = new Pagamentos();
                pag.setIdPagamento(rs.getString("idPagamento"));
                pag.setMetodoPagamento(rs.getString("metodoPagamento"));
                pag.setStatusPagamento(rs.getString("statusPagamento"));
                pag.setDataPagamento(rs.getString("dataPagamento"));
                pagamentos.add(pag);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectionDatabase.closeConnection(con, stmt, rs);
        }
        return pagamentos;
    }

    public ArrayList<Pagamentos> search(String string) {
        Connection con = ConnectionDatabase.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        ArrayList<Pagamentos> pagamentos = new ArrayList<>();

        try {
            stmt = con.prepareStatement("SELECT * FROM Pagamentos WHERE metodoPagamento LIKE ?");
            stmt.setString(1, "%" + string + "%");
            rs = stmt.executeQuery();

            while (rs.next()) {
                Pagamentos pag = new Pagamentos();
                pag.setIdPagamento(rs.getString("idPagamento"));
                pag.setMetodoPagamento(rs.getString("metodoPagamento"));
                pag.setStatusPagamento(rs.getString("statusPagamento"));
                pag.setDataPagamento(rs.getString("dataPagamento"));
                pagamentos.add(pag);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectionDatabase.closeConnection(con, stmt, rs);
        }
        return pagamentos;
    }

    public void update(Pagamentos pag) {
        Connection con = ConnectionDatabase.getConnection();
        PreparedStatement stmt = null;

        try {
            stmt = con.prepareStatement("UPDATE Pagamentos SET metodoPagamento = ?, statusPagamento = ?, dataPagamento = ? WHERE idPagamento = ?");
            stmt.setString(1, pag.getMetodoPagamento());
            stmt.setString(2, pag.getStatusPagamento());
            stmt.setString(3, pag.getDataPagamento());
            stmt.setString(4, pag.getIdPagamento());

            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectionDatabase.closeConnection(con, stmt);
        }
    }

    public void delete(String idPagamento) {
        Connection con = ConnectionDatabase.getConnection();
        PreparedStatement stmt = null;

        try {
            stmt = con.prepareStatement("DELETE FROM Pagamentos WHERE idPagamento = ?");
            stmt.setString(1, idPagamento);

            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Erro ao excluir o pagamento: " + e.getMessage(), e);

        } finally {
            ConnectionDatabase.closeConnection(con, stmt);
        }
    }
}

