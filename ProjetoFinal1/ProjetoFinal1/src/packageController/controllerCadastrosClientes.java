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
    	
    	Enderecos endereco = new Enderecos();
		endereco.setCep(txtCep.getText());
		endereco.setRua(txtRua.getText());
		endereco.setNumero(txtNumero.getText());
		endereco.setBairro(txtBairro.getText());
		endereco.setComplemento(txtComplemento.getText());
		endereco.setCidadeUF("Estado");
		
		EnderecoDAO.create(endereco);
		
		Clientes clientes = new Clientes();
		clientes.setNomeCliente(txtNomeCliente.getText());
		clientes.setCpf(txtCPF.getText());
		clientes.setEmail(txtEmail.getText());
		clientes.setTelefone(txtTelefone.getText());
		clientes.setId_Endereço(endereco.getIdEndereço());
        
		ClienteDAO.create(endereco);

	if (controllerRelatorioFornecedor.FornecedoresEditor == null) {
		Fornecedores fornecedor1 = new Fornecedores();
		fornecedor1.setNomeClientes(txtNomeCliente.getText());
		fornecedor1.setCnpj(txtCNPJ.getText());
		fornecedor1.setEmail(txtEmail.getText());
		fornecedor1.setTelefone(txtTelefone.getText());
		FornecedoresDAO forn = new FornecedoresDAO();

	} else {
		Fornecedores fornecedor1 = new Fornecedores();
		fornecedor1.setNomeFornecedor(txtNomeFornecedor.getText());
		fornecedor1.setCnpj(txtCNPJ.getText());
		fornecedor1.setEmail(txtEmail.getText());
		fornecedor1.setTelefone(txtTelefone.getText());
		FornecedoresDAO forn = new FornecedoresDAO();
		forn.update(fornecedor1);

		Stage stage = (Stage) btnCancelar.getScene().getWindow();
		stage.close();
	}
    }

    @FXML
    void OnbtnCancelar(ActionEvent event) {

    }

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		
	}

}
