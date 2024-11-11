package packageController;

import java.net.URL;
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
        try {
            if (validarCampos()) {
                // Criar um novo endereço e preencher
                Enderecos endereco = new Enderecos();
                preencherEndereco(endereco);
                
                // Salvar o endereço no banco de dados
                enderecoDAO.create(endereco);

                // Agora, criar um novo cliente
                Clientes novoCliente = new Clientes();
                preencherCliente(novoCliente, endereco);  // Passar o endereço já salvo como objeto
                
                // Salvar o cliente no banco de dados
                clienteDAO.create(novoCliente);
                
                mostrarMensagem("Cliente cadastrado com sucesso!", Alert.AlertType.INFORMATION);
                
                if (confirmarCadastroOutro()) {
                    limparCampos();
                } else {
                    fecharJanela();
                }
            } else {
                mostrarMensagem("Por favor, preencha todos os campos obrigatórios e certifique-se de que os dados são válidos.", Alert.AlertType.WARNING);
            }
        } catch (Exception e) {
            mostrarMensagem("Erro ao cadastrar cliente: " + e.getMessage(), Alert.AlertType.ERROR);
        }
    }

    private void mostrarMensagem(String mensagem, Alert.AlertType tipo) {
        Alert alert = new Alert(tipo);
        alert.setContentText(mensagem);
        alert.setHeaderText(null);
        alert.showAndWait();
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


    private void preencherCliente(Clientes cliente, Enderecos endereco) {
        cliente.setNomeCliente(txtNomeCliente.getText());
        cliente.setCpf(txtCPF.getText());
        cliente.setEmail(txtEmail.getText());
        cliente.setTelefone(txtTelefone.getText());
        
        cliente.setId_Endereco(endereco);  
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

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        if (controllerRelatorioClientes.clienteEditor != null) {
            txtNomeCliente.setText(controllerRelatorioClientes.clienteEditor.getNomeCliente());
            txtCPF.setText(controllerRelatorioClientes.clienteEditor.getCpf());
            txtEmail.setText(controllerRelatorioClientes.clienteEditor.getEmail());
            txtTelefone.setText(controllerRelatorioClientes.clienteEditor.getTelefone());

            if (controllerRelatorioClientes.clienteEditor.getId_Endereco() != null) {
                Enderecos endereco = controllerRelatorioClientes.clienteEditor.getId_Endereco();
                txtRua.setText(endereco.getRua());
                txtNumero.setText(endereco.getNumero());
                txtBairro.setText(endereco.getBairro());
                txtCidadeUF.setText(endereco.getCidadeUF());
                txtCep.setText(endereco.getCep());
                txtComplemento.setText(endereco.getComplemento());
            }
        }
    }

}
