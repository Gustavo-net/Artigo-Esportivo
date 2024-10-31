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
    void OnbtnAddCliente(ActionEvent event) {
        if (!validarCampos()) {
            showAlert("Por favor, preencha todos os campos obrigatórios.");
            return;
        }

        Clientes novoCliente = coletarDadosDoCliente();

        try {
            // Verifica se já existe um cliente com o CPF antes de adiciona-lo
            if (ClienteDAO.validarExistente(novoCliente.getCpf())) {
                showAlert("Erro: Cliente com CPF " + novoCliente.getCpf() + " já existe.");
                return; 
            }
            
            clienteDAO.create(novoCliente);
            showAlert("Cliente adicionado com sucesso!");

            limparCampos();
            carregarTabelaClientes();
            
        } catch (IllegalArgumentException e) {
            showAlert(e.getMessage()); 
        } catch (Exception e) {
            e.printStackTrace(); 
            showAlert("Erro ao adicionar cliente: " + e.getMessage());
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

    private void preencherCliente(Clientes cliente) {
        cliente.setNomeCliente(txtNomeCliente.getText());
        cliente.setCpf(txtCPF.getText());
        cliente.setEmail(txtEmail.getText());
        cliente.setTelefone(txtTelefone.getText());
        
    }

    @FXML
    public void OnbtnCancelar(ActionEvent event) {
        limparCampos();
        fecharJanela();
    }

    private void limparCampos() {
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
    
    private Clientes coletarDadosDoCliente() {
        Clientes cliente = new Clientes();
        preencherCliente(cliente); 
        return cliente;
    }
    
    private void showAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setContentText(message);
        alert.showAndWait();
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

    private void carregarTabelaClientes() {
    }
}
