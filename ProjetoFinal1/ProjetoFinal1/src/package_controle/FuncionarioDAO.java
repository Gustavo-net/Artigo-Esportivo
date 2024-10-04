package package_controle;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import packageConnection.ConnectionDatabase;
import packageModel.Funcionarios;

public class FuncionarioDAO {

	public void create(Funcionarios c) {
        Connection con = ConnectionDatabase.getConnection();
        PreparedStatement stmt = null;

        try {
            stmt = con.prepareStatement("INSERT INTO Funcionario (idFuncionario, nomeFuncionario, cpf, email, telefone, dataNasc, dataCont, cargo, sexo, senha, id_Endereco) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)");
            stmt.setString(1, c.getIdFuncionario());
            stmt.setString(2, c.getNomeFuncionario());
            stmt.setString(3, c.getCpf());
            stmt.setString(4, c.getEmail());
            stmt.setString(5, c.getTelefone());
            stmt.setString(6, c.getDataNasc());
            stmt.setString(7, c.getDataCont());
            stmt.setString(8, c.getCargo());
            stmt.setString(9, c.getSexo());
            stmt.setString(10, c.getSenha());
            stmt.setString(11, c.getId_Endereço());

            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectionDatabase.closeConnection(con, stmt);
        }
    }

    public ArrayList<Funcionarios> read() {
        Connection con = ConnectionDatabase.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        ArrayList<Funcionarios> funcionario = new ArrayList<>();

        try {
            stmt = con.prepareStatement("SELECT * FROM Funcionarios");
            rs = stmt.executeQuery();

            while (rs.next()) {
            	Funcionarios f = new Funcionarios();
                f.setIdFuncionario(rs.getString("idCliente"));
                f.setNomeFuncionario(rs.getString("nome"));
                f.setCpf(rs.getString("cpf"));
                f.setEmail(rs.getString("email"));
                f.setTelefone(rs.getString("telefone"));
                f.setDataNasc(rs.getString("dataNasc"));
                f.setDataCont(rs.getString("dataCont"));
                f.setCargo(rs.getString("cargo"));
                f.setSexo(rs.getString("sexo"));
                f.setSenha(rs.getString("senha"));
                f.setId_Endereço(rs.getString("idEndereco"));
                funcionario.add(f);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectionDatabase.closeConnection(con, stmt, rs);
        }
        return funcionario;
    }

    public ArrayList<Funcionarios> search(String string) {
        Connection con = ConnectionDatabase.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        ArrayList<Funcionarios> funcionario = new ArrayList<>();

        try {
            stmt = con.prepareStatement("SELECT * FROM Funcionarios WHERE nomeFuncionario LIKE ?"); 
            stmt.setString(1, "%" + string + "%");
            rs = stmt.executeQuery();

            while (rs.next()) {
            	Funcionarios f = new Funcionarios();
                f.setIdFuncionario(rs.getString("idFuncionario"));
                f.setNomeFuncionario(rs.getString("nomeFuncionario"));
                f.setCpf(rs.getString("cpf"));
                f.setEmail(rs.getString("email"));
                f.setTelefone(rs.getString("telefone"));
                f.setDataNasc(rs.getString("dataNasc"));
                f.setDataCont(rs.getString("dataPrimComp"));
                f.setCargo(rs.getString("endereco"));
                f.setSenha(rs.getString("senha")); 
                f.setCargo(rs.getString("endereco"));
                f.setSexo(rs.getString("sexo"));
                f.setSenha(rs.getString("senha"));
                f.setId_Endereço(rs.getString("id_Endereco"));
                funcionario.add(f);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectionDatabase.closeConnection(con, stmt, rs);
        }
        return funcionario;
    }

    public void update(Funcionarios c) {
        Connection con = ConnectionDatabase.getConnection();
        PreparedStatement stmt = null;

        try {
            stmt = con.prepareStatement("UPDATE Funcionarios SET nomeFuncionario = ?, cpf = ?, email = ?, telefone = ?, dataNasc = ?, dataCont = ?, cargo = ?, sexo = ?, senha = ?, id_Endereco WHERE idFuncionario = ?");
            stmt.setString(1, c.getNomeFuncionario());
            stmt.setString(2, c.getCpf());
            stmt.setString(3, c.getEmail());
            stmt.setString(4, c.getTelefone());
            stmt.setString(5, c.getDataNasc());
            stmt.setString(6, c.getDataCont());
            stmt.setString(7, c.getCargo());
            stmt.setString(8, c.getSexo());
            stmt.setString(9, c.getSenha());
            stmt.setString(10, c.getId_Endereço());
            stmt.setString(11, c.getIdFuncionario());

            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectionDatabase.closeConnection(con, stmt);
        }
    }

    public void delete(String idFuncionario) {
    	
        Connection con = ConnectionDatabase.getConnection();
        PreparedStatement stmt = null;

        try {
            stmt = con.prepareStatement("DELETE FROM Funcionarios WHERE idfuncionario = ?");
            stmt.setString(1, idFuncionario);

            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Erro ao excluir o funcionario: " + e.getMessage(), e);

        } finally {
            ConnectionDatabase.closeConnection(con, stmt);
        }
    }
}
