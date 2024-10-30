package packageController;

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
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
        // Verificar se todos os campos obrigatórios estão preenchidos
        if (validarCampos()) {
            Enderecos endereco = new Enderecos();
            preencherEndereco(endereco);

            // Criar o endereço
            enderecoDAO.create(endereco);

            Clientes cliente = new Clientes();
            preencherCliente(cliente, endereco.getIdEndereço());

            // Criar ou atualizar cliente
            if (controllerRelatorioClientes.clienteEditor == null) {
                clienteDAO.create(cliente);
            } else {
                clienteDAO.update(cliente);
            }

            mostrarMensagem("Cliente cadastrado com sucesso!", Alert.AlertType.INFORMATION);
            if (confirmarCadastroOutro()) {
                limparCampos();
            } else {
                fecharJanela();
            }
        } else {
            mostrarMensagem("Por favor, preencha todos os campos obrigatórios.", Alert.AlertType.WARNING);
        }
    }

    private boolean validarCampos() {
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

    private void mostrarMensagem(String mensagem, Alert.AlertType tipo) {
        Alert alert = new Alert(tipo);
        alert.setContentText(mensagem);
        alert.setHeaderText(null);
        alert.showAndWait();
    }

    private boolean confirmarCadastroOutro() {
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
        endereco.setCep(txtCep.getText());
        endereco.setRua(txtRua.getText());
        endereco.setNumero(txtNumero.getText());
        endereco.setBairro(txtBairro.getText());
        endereco.setComplemento(txtComplemento.getText());
        endereco.setCidadeUF(txtCidadeUF.getText());
    }

    private void preencherCliente(Clientes cliente, String string) {
        cliente.setNomeCliente(txtNomeCliente.getText());
        cliente.setCpf(txtCPF.getText());
        cliente.setEmail(txtEmail.getText());
        cliente.setTelefone(txtTelefone.getText());
        cliente.setId_Endereço(string);
    }

    @FXML
    public void OnbtnCancelar(ActionEvent event) {
        limparCampos();
        fecharJanela();
    }

    private void limparCampos() {
        txtNomeCliente.setText("");
        txtCPF.setText("");
        txtEmail.setText("");
        txtTelefone.setText("");
        txtRua.setText("");
        txtNumero.setText("");
        txtBairro.setText("");
        txtCidadeUF.setText("");
        txtCep.setText("");
        txtComplemento.setText("");
        controllerRelatorioClientes.clienteEditor = null;
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
        }
    }
}
