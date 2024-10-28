package packageController;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import packageModel.Clientes;
import package_controle.ClienteDAO;

public class controllerCadastrosClientes {

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

    // Variável para armazenar cliente editado
    public static Clientes ClienteEditor;

    @FXML
    void OnbtnAddCliente(ActionEvent event) {
        ClienteDAO cliDAO = new ClienteDAO();
        Clientes cliente = new Clientes();

        if (controllerRelatorioClientes.clienteEditor == null) {
            // Adicionando um novo cliente
            cliente.setNomeCliente(txtNomeCliente.getText());
            cliente.setCpf(txtCPF.getText());
            cliente.setEmail(txtEmail.getText());
            cliente.setTelefone(txtTelefone.getText());
            cliente.setBairro(txtBairro.getText());
            cliente.setCep(txtCep.getText());
            cliente.setCidadeUF(txtCidadeUF.getText());
            cliente.setComplemento(txtComplemento.getText());
            cliente.setRua(txtRua.getText());
            cliente.setNumero(txtNumero.getText());
            
            cliDAO.save(cliente); // Salva o novo cliente

        } else {
            // Atualizando um cliente existente
            cliente.setId(ClienteEditor.getId()); // Presumindo que a classe Clientes tenha um método getId
            cliente.setNomeCliente(txtNomeCliente.getText());
            cliente.setCpf(txtCPF.getText());
            cliente.setEmail(txtEmail.getText());
            cliente.setTelefone(txtTelefone.getText());
            
            cliDAO.update(cliente); // Atualiza o cliente existente
        }

        // Fecha a janela após a operação
        Stage stage = (Stage) btnAddCliente.getScene().getWindow();
        stage.close();
    }

    @FXML
    void OnbtnCancelar(ActionEvent event) {
    	
    	txtNomeCliente.setText("");
    	txtCPF.setText("");
    	txtEmail.setText("");
    	txtTelefone.setText("");
    	txtCep.setText("");
    	
    	controllerRelatorioClientes.clienteEditor = null;
    	
    	Stage stage = (Stage) btnCancelar.getScene().getWindow();
		stage.close();

       
    }

    public void initialize(URL location, ResourceBundle resources) {
        if (ClienteEditor != null) {
            txtNomeCliente.setText(ClienteEditor.getNomeCliente());
            txtCPF.setText(ClienteEditor.getCpf());
            txtEmail.setText(ClienteEditor.getEmail());
            txtTelefone.setText(ClienteEditor.getTelefone());
            txtBairro.setText(ClienteEditor.getBairro());
            txtCep.setText(ClienteEditor.getCep());
            txtCidadeUF.setText(ClienteEditor.getCidadeUF());
            txtComplemento.setText(ClienteEditor.getComplemento());
            txtRua.setText(ClienteEditor.getRua());
            txtNumero.setText(ClienteEditor.getNumero());
        }
    }

    private void clearFields() {
        txtNomeCliente.setText("");
        txtCPF.setText("");
        txtEmail.setText("");
        txtTelefone.setText("");
        txtBairro.setText("");
        txtCep.setText("");
        txtCidadeUF.setText("");
        txtComplemento.setText("");
        txtRua.setText("");
        txtNumero.setText("");
    }
}
