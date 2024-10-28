package packageController;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import packageModel.Clientes;
import packageModel.Enderecos;
import packageModel.Fornecedores;
import package_controle.ClienteDAO;
import package_controle.EnderecoDAO;
import package_controle.FornecedoresDAO;

public class  controllerCadastrosClientes implements Initializable{

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
    	
    	//criação do objeto endereço
    	Enderecos endereco = new Enderecos();
		endereco.setCep(txtCep.getText());
		endereco.setRua(txtRua.getText());
		endereco.setNumero(txtNumero.getText());
		endereco.setBairro(txtBairro.getText());
		endereco.setComplemento(txtComplemento.getText());
		endereco.setCidadeUF("Estado");
		
		//salvar endereço
		EnderecoDAO.create(endereco);
		
		//criação do objeto cliente
		Clientes clientes = new Clientes();
		clientes.setNomeCliente(txtNomeCliente.getText());
		clientes.setCpf(txtCPF.getText());
		clientes.setEmail(txtEmail.getText());
		clientes.setTelefone(txtTelefone.getText());
		clientes.setId_Endereço(endereco.getIdEndereço());
        
		ClienteDAO.create(clientes);

	if (controllerRelatorioClientes.clienteEditor == null) {
		Clientes clientes1 = new Clientes();
		clientes1.setNomeCliente(txtNomeCliente.getText());
		clientes1.setCpf(txtCPF.getText());
		clientes1.setEmail(txtEmail.getText());
		clientes1.setTelefone(txtTelefone.getText());
		clientes1.setId_Endereço(endereco.getIdEndereço());
		ClienteDAO client = new ClienteDAO();

	} else {
		Clientes clientes1 = new Clientes();
		clientes1.setNomeCliente(txtNomeCliente.getText());
		clientes1.setCpf(txtCPF.getText());
		clientes1.setEmail(txtEmail.getText());
		clientes1.setTelefone(txtTelefone.getText());
		clientes1.setId_Endereço(endereco.getIdEndereço());

		ClienteDAO client = new ClienteDAO();
		client.update(clientes1);

		Stage stage = (Stage) btnCancelar.getScene().getWindow();
		stage.close();
	}
    }

    @FXML
    void OnbtnCancelar(ActionEvent event) {

    	txtNomeCliente.setText("");
    	txtCPF.setText("");
    	txtEmail.setText("");
    	txtTelefone.setText("");
    	
    	controllerRelatorioClientes.clienteEditor = null;
    	
    	Stage stage = (Stage) btnCancelar.getScene().getWindow() ;
    	stage.close();
    }

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		ClienteDAO clientes = new ClienteDAO();
		// TODO Auto-generated method stub
		
		if(controllerRelatorioClientes.clienteEditor != null) {
			txtNomeCliente.setText(controllerRelatorioClientes.clienteEditor.getNomeCliente());
			txtCPF.setText(controllerRelatorioClientes.clienteEditor.getCpf());
			txtEmail.setText(controllerRelatorioClientes.clienteEditor.getEmail());
			txtTelefone.setText(controllerRelatorioClientes.clienteEditor.getTelefone());

		}
		
	}

}
