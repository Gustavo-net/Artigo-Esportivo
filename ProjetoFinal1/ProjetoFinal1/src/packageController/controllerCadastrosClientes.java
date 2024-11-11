package packageController;

import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Optional;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import packageConnection.ConnectionDatabase;
import packageModel.Clientes;
import packageModel.Enderecos;
import package_controle.ClienteDAO;
import package_controle.EnderecoDAO;

public class controllerCadastrosClientes implements Initializable {

    @FXML
    private Button btnAddCliente;

    @FXML
    private Button btnCancelar;

    @FXML
    private TextField txtBairro;

    @FXML
    private TextField txtCPF;

    @FXML
    private TextField txtCep;

    @FXML
    private TextField txtCidadeUF;

    @FXML
    private TextField txtComplemento;

    @FXML
    private TextField txtEmail;

    @FXML
    private TextField txtNomeCliente;

    @FXML
    private TextField txtNumero;

    @FXML
    private TextField txtRua;

    @FXML
    private TextField txtTelefone;

    private ClienteDAO clienteDAO = new ClienteDAO();
    private EnderecoDAO enderecoDAO = new EnderecoDAO();

    @FXML
    public void OnbtnAddCliente(ActionEvent event) {
        Connection con = null;
        try {
            if (validarCampos()) {
                // Preencher o objeto Endereço com os dados da interface
                Enderecos endereco = new Enderecos();
                preencherEndereco(endereco);

                // Estabelece conexão e inicia transação
                con = ConnectionDatabase.getConnection();
                con.setAutoCommit(false);  // Desabilitar auto commit para controlar transações manualmente

                // Inserir o endereço no banco de dados
                String idEndereco = EnderecoDAO.create(endereco); 
                if (idEndereco == null) {
                    throw new SQLException("Erro ao inserir endereço no banco.");
                }

                // Preencher o objeto Cliente com os dados da interface
                Clientes novoCliente = new Clientes();
                preencherCliente(novoCliente, idEndereco);  // Passa o idEndereco para o cliente

                // Inserir o cliente no banco de dados
                String idCliente = ClienteDAO.create(novoCliente);
                if (idCliente == null) {
                    throw new SQLException("Erro ao inserir cliente no banco.");
                }

                // Se tudo deu certo, comita a transação
                con.commit();  

                mostrarMensagem("Cliente cadastrado com sucesso!", Alert.AlertType.INFORMATION);

                if (confirmarCadastroOutro()) {
                    limparCampos();
                } else {
                    fecharJanela();
                }
            } else {
                mostrarMensagem("Por favor, preencha todos os campos obrigatórios.", Alert.AlertType.WARNING);
            }
        } catch (SQLException e) {
            if (con != null) {
                try {
                    con.rollback(); 
                } catch (SQLException rollbackEx) {
                    rollbackEx.printStackTrace();
                }
            }

            e.printStackTrace();
            mostrarMensagem("Erro ao cadastrar cliente. Tente novamente.", Alert.AlertType.ERROR);
        } finally {
            // Garante que a conexão será fechada
            if (con != null) {
                try {
                    con.setAutoCommit(true);  // Restaura o comportamento padrão da conexão
                    con.close();  // Fecha a conexão
                } catch (SQLException closeEx) {
                    closeEx.printStackTrace();
                }
            }
        }
    }

    private void mostrarMensagem(String mensagem, Alert.AlertType tipo) {
        Alert alert = new Alert(tipo);
        alert.setContentText(mensagem);
        alert.setHeaderText(null);
        alert.showAndWait();
    }

    private boolean validarCampos() {
        // Verifica se todos os campos obrigatórios foram preenchidos
        return !txtNomeCliente.getText().isEmpty() &&
               !txtCPF.getText().isEmpty() &&
               !txtEmail.getText().isEmpty() &&
               !txtTelefone.getText().isEmpty() &&
               !txtRua.getText().isEmpty() &&
               !txtNumero.getText().isEmpty() &&
               !txtBairro.getText().isEmpty() &&
               !txtCidadeUF.getText().isEmpty() &&
               !txtCep.getText().isEmpty();
    }

    private boolean confirmarCadastroOutro() {
        // Pergunta se o usuário deseja cadastrar outro cliente
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Cadastrar Novo Cliente");
        alert.setHeaderText("Deseja cadastrar outro cliente?");
        ButtonType simButton = new ButtonType("Sim");
        ButtonType naoButton = new ButtonType("Não");
        alert.getButtonTypes().setAll(simButton, naoButton);

        Optional<ButtonType> result = alert.showAndWait();
        return result.isPresent() && result.get() == simButton;
    }

    private void preencherEndereco(Enderecos endereco) {
        // Preenche o objeto Endereco com os dados da interface
        endereco.setCep(txtCep.getText());
        endereco.setRua(txtRua.getText());
        endereco.setNumero(txtNumero.getText());
        endereco.setBairro(txtBairro.getText());
        endereco.setComplemento(txtComplemento.getText());
        endereco.setCidadeUF(txtCidadeUF.getText());
    }

    private void preencherCliente(Clientes cliente, String idEndereco) {
        // Preenche o objeto Cliente com os dados da interface
        cliente.setNomeCliente(txtNomeCliente.getText());
        cliente.setCpf(txtCPF.getText());
        cliente.setEmail(txtEmail.getText());
        cliente.setTelefone(txtTelefone.getText());
        cliente.setId_Endereco(idEndereco);  // Atribui o id do endereço ao cliente
    }

    @FXML
    public void OnbtnCancelar(ActionEvent event) {
        limparCampos();
        fecharJanela();
    }

    private void limparCampos() {
        // Limpa todos os campos da interface
        txtNomeCliente.clear();
        txtCPF.clear();
        txtEmail.clear();
        txtTelefone.clear();
        txtRua.clear();
        txtNumero.clear();
        txtBairro.clear();
        txtCidadeUF.clear();
        txtCep.clear();
        txtComplemento.clear();
    }

    private void fecharJanela() {
        Stage stage = (Stage) btnCancelar.getScene().getWindow();
        stage.close();
    }

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        if (controllerRelatorioClientes.clienteEditor != null) {
            txtNomeCliente.setText(controllerRelatorioClientes.clienteEditor.getNomeCliente());
            txtCPF.setText(controllerRelatorioClientes.clienteEditor.getCpf());
            txtEmail.setText(controllerRelatorioClientes.clienteEditor.getEmail());
            txtTelefone.setText(controllerRelatorioClientes.clienteEditor.getTelefone());
            txtRua.setText(controllerRelatorioClientes.clienteEditor.getRua());
            txtNumero.setText(controllerRelatorioClientes.clienteEditor.getNumero());
            txtBairro.setText(controllerRelatorioClientes.clienteEditor.getBairro());
            txtCidadeUF.setText(controllerRelatorioClientes.clienteEditor.getCidadeUF());
            txtCep.setText(controllerRelatorioClientes.clienteEditor.getCep());
            txtComplemento.setText(controllerRelatorioClientes.clienteEditor.getComplemento());
        }
    }
}
