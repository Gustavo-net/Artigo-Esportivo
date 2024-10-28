package packageController;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import packageModel.Clientes;
import packageModel.Enderecos;
import package_controle.ClienteDAO;
import package_controle.EnderecoDAO;

public class ControllerCadastrosClientes {

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


    // Variável para armazenar cliente editado
    public static Clientes clienteEditor;

    @FXML
    void onBtnAddCliente(ActionEvent event) {
        ClienteDAO cliDAO = new ClienteDAO();
        Clientes cliente = new Clientes();
        
        // Criação do Objeto Endereços
        
        Enderecos endereco = new Enderecos();
        endereco.setCep(txtCep.getText());
        endereco.setRua(txtRua.getText());
        endereco.setNumero(txtNumero.getText());
        endereco.setBairro(txtBairro.getText());
        endereco.setCidadeUF("Estado");
        
        // Salvar Endereço e obter o ID gerado
        EnderecoDAO.create(endereco);
        
        // Criação de Objeto Cliente
        
        Clientes clientes = new Clientes();
        clientes.setNomeCliente(txtNomeCliente.getText());
        clientes.setEmail(txtCep.getText());
        clientes.setEmail(txtEmail.getText());
        clientes.setTelefone(txtTelefone.getText());
        clientes.setId_Endereço(endereco.getIdEndereço());
        
        clienteDAO.create(cliente);
        
        

        if (controllerRelatorioClientes.clienteEditor == null) {
            // Adicionando um novo cliente
        	Clientes cliente1 = new Clientes();
            cliente1.setNomeCliente(txtNomeCliente.getText());
            cliente1.setEmail(txtEmail.getText());
            cliente1.setTelefone(txtTelefone.getText());
            cliente1.setCpf(txtCPF.getText());
            
            ClienteDAO cli = new ClienteDAO();
            

        } else {
            // Atualizando um cliente existente
            cliente.setIdCliente(clienteEditor.getId()); // Presumindo que a classe Clientes tenha um método getId
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
            
            cliDAO.update(cliente); // Atualiza o cliente existente
        }

        // Fecha a janela após a operação
        Stage stage = (Stage) btnAddCliente.getScene().getWindow();
        stage.close();
    }

    @FXML
    void onBtnCancelar(ActionEvent event) {
        clearFields(); // Limpa os campos

        clienteEditor = null;

        Stage stage = (Stage) btnCancelar.getScene().getWindow();
        stage.close();
    }

    public void initialize(URL location, ResourceBundle resources) {
        if (clienteEditor != null) {
            txtNomeCliente.setText(clienteEditor.getNomeCliente());
            txtCPF.setText(clienteEditor.getCpf());
            txtEmail.setText(clienteEditor.getEmail());
            txtTelefone.setText(clienteEditor.getTelefone());
            txtBairro.setText(clienteEditor.getBairro());
            txtCep.setText(clienteEditor.getCep());
            txtCidadeUF.setText(clienteEditor.getCidadeUF());
            txtComplemento.setText(clienteEditor.getComplemento());
            txtRua.setText(clienteEditor.getRua());
            txtNumero.setText(clienteEditor.getNumero());
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
