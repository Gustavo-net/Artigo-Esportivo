package package_controle;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import packageConnection.ConnectionDatabase;
import packageModel.Funcionarios;

public class FuncionarioDAO {

	public static void create(Funcionarios c) {
	    Connection con = ConnectionDatabase.getConnection();
	    PreparedStatement stmt = null;
	    ResultSet generatedKeys = null;

	    try {
	        // Inserção na tabela Enderecos
	        String insertEndereco = "INSERT INTO Enderecos (cep, rua, numero, bairro, complemento, cidadeUF) VALUES (?, ?, ?, ?, ?, ?)";
	        stmt = con.prepareStatement(insertEndereco, PreparedStatement.RETURN_GENERATED_KEYS);
	        stmt.setString(1, c.getCep());
	        stmt.setString(2, c.getRua());
	        stmt.setString(3, c.getNumero());
	        stmt.setString(4, c.getBairro());
	        stmt.setString(5, c.getComplemento());
	        stmt.setString(6, c.getCidadeUF());
	        stmt.executeUpdate();

	        // Recupera o ID gerado para o endereço
	        generatedKeys = stmt.getGeneratedKeys();
	        String idEndereco = null;
	        if (generatedKeys.next()) {
	            idEndereco = generatedKeys.getString(1); // Assume que o ID gerado é o primeiro campo
	        }

	        // Inserção na tabela Funcionarios
	        String sqlFuncionario = "INSERT INTO Funcionarios (nomeFuncionario, cpf, email, telefone, dataNasc, dataCont, cargo, sexo, senha, id_Endereco) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
	        stmt = con.prepareStatement(sqlFuncionario);
	        stmt.setString(1, c.getNomeFuncionario());
	        stmt.setString(2, c.getCpf());
	        stmt.setString(3, c.getEmail());
	        stmt.setString(4, c.getTelefone());

	        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	        stmt.setString(5, sdf.format(java.sql.Date.valueOf(c.getDataNasc())));
	        stmt.setString(6, sdf.format(java.sql.Date.valueOf(c.getDataCont())));

	        stmt.setString(7, c.getCargo());
	        stmt.setString(8, c.getSexo());
	        stmt.setString(9, c.getSenha());

	        // Passa o idEndereco para o campo id_Endereco
	        stmt.setString(10, idEndereco);

	        stmt.executeUpdate();

	    } catch (SQLException e) {
	        e.printStackTrace();
	    } finally {
	        ConnectionDatabase.closeConnection(con, stmt, generatedKeys);
	    }
	}

    public static ArrayList<Funcionarios> read() {
        Connection con = ConnectionDatabase.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        ArrayList<Funcionarios> funcionarios = new ArrayList<>();

        try {
            String sql = "SELECT f.*, e.cep, e.rua, e.numero, e.bairro, e.complemento, e.cidadeUF " +
                         "FROM Funcionarios f " +
                         "JOIN Enderecos e ON f.id_Endereco = e.idEndereco";
            stmt = con.prepareStatement(sql);
            rs = stmt.executeQuery();

            while (rs.next()) {
                Funcionarios f = new Funcionarios();
                f.setIdFuncionario(rs.getString("idFuncionario"));
                f.setNomeFuncionario(rs.getString("nomeFuncionario"));
                f.setCpf(rs.getString("cpf"));
                f.setEmail(rs.getString("email"));
                f.setTelefone(rs.getString("telefone"));
                f.setDataNasc(rs.getDate("dataNasc").toLocalDate());
                f.setDataCont(rs.getDate("dataCont").toLocalDate());
                f.setCargo(rs.getString("cargo"));
                f.setSexo(rs.getString("sexo"));
                f.setSenha(rs.getString("senha"));
                f.setId_Endereço(rs.getString("id_Endereco"));

                f.setCep(rs.getString("cep"));
                f.setRua(rs.getString("rua"));
                f.setNumero(rs.getString("numero"));
                f.setBairro(rs.getString("bairro"));
                f.setComplemento(rs.getString("complemento"));
                f.setCidadeUF(rs.getString("cidadeUF"));

                funcionarios.add(f);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectionDatabase.closeConnection(con, stmt, rs);
        }
        return funcionarios;
    }

    public static ArrayList<Funcionarios> search(String string) {
        Connection con = ConnectionDatabase.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        ArrayList<Funcionarios> funcionarios = new ArrayList<>();

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
                f.setDataNasc(rs.getDate("dataNasc").toLocalDate());
                f.setDataCont(rs.getDate("dataCont").toLocalDate());
                f.setCargo(rs.getString("cargo"));
                f.setSexo(rs.getString("sexo"));
                f.setSenha(rs.getString("senha"));
                f.setId_Endereço(rs.getString("id_Endereco"));
                funcionarios.add(f);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectionDatabase.closeConnection(con, stmt, rs);
        }
        return funcionarios;
    }

    public void delete(String idFuncionario) {
        Connection con = ConnectionDatabase.getConnection();
        PreparedStatement stmt = null;

        try {
            stmt = con.prepareStatement("DELETE FROM Funcionarios WHERE idFuncionario = ?");
            stmt.setString(1, idFuncionario);
            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Erro ao excluir o funcionário: " + e.getMessage(), e);
        } finally {
            ConnectionDatabase.closeConnection(con, stmt);
        }
    }
    public Funcionarios autenticarUser(String cpf, String senha) {
        Connection con = ConnectionDatabase.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Funcionarios funcionario = null;

        try {
            String sql = "SELECT * FROM Funcionarios WHERE cpf = ? AND senha = ?";
            stmt = con.prepareStatement(sql);
            stmt.setString(1, cpf);
            stmt.setString(2, senha);
           
            rs = stmt.executeQuery();

            if (rs.next()) {
                funcionario = new Funcionarios();
                funcionario.setIdFuncionario(rs.getString("idFuncionario"));
                funcionario.setNomeFuncionario(rs.getString("nomeFuncionario"));
                funcionario.setCpf(rs.getString("cpf"));
                funcionario.setEmail(rs.getString("email"));
                funcionario.setTelefone(rs.getString("telefone"));
                funcionario.setDataNasc(rs.getDate("dataNasc").toLocalDate());
                funcionario.setDataCont(rs.getDate("dataCont").toLocalDate());
                funcionario.setCargo(rs.getString("cargo"));
                funcionario.setSexo(rs.getString("sexo"));
                funcionario.setSenha(rs.getString("senha"));
                funcionario.setId_Endereço(rs.getString("id_Endereco"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectionDatabase.closeConnection(con, stmt, rs);
        }

        return funcionario;
    }
    public static void update(Funcionarios funcionario) {
        Connection con = ConnectionDatabase.getConnection();
        PreparedStatement stmt = null;

        try {
            con = ConnectionDatabase.getConnection();
            con.setAutoCommit(false); // Inicia a transação

            String sql = "UPDATE Funcionarios SET nomeFuncionario = ?, cpf = ?, email = ?, telefone = ?, dataNasc = ?, dataCont = ?, cargo = ?, sexo = ?, senha = ?, id_Endereco = ? WHERE idFuncionario = ?";
            stmt = con.prepareStatement(sql);
            stmt.setString(1, funcionario.getNomeFuncionario());
            stmt.setString(2, funcionario.getCpf());
            stmt.setString(3, funcionario.getEmail());
            stmt.setString(4, funcionario.getTelefone());

            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            stmt.setString(5, sdf.format(java.sql.Date.valueOf(funcionario.getDataNasc())));
            stmt.setString(6, sdf.format(java.sql.Date.valueOf(funcionario.getDataCont())));

            stmt.setString(7, funcionario.getCargo());
            stmt.setString(8, funcionario.getSexo());
            stmt.setString(9, funcionario.getSenha());
            stmt.setString(10, funcionario.getId_Endereço()); // Se necessário
            stmt.setString(11, funcionario.getIdFuncionario()); // Verifique o id do funcionário para garantir que a atualização ocorra corretamente

            stmt.executeUpdate();
            con.commit(); // Confirma a transação

        } catch (SQLException e) {
            e.printStackTrace();

        } finally {
            ConnectionDatabase.closeConnection(con, stmt);
        }
    }
}
