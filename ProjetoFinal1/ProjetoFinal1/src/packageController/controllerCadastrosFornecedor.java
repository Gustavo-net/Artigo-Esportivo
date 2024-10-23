package packageController;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import packageModel.Enderecos;
import packageModel.Fornecedores;
import package_controle.EnderecoDAO;
import package_controle.FornecedoresDAO;

public class controllerCadastrosFornecedor implements Initializable {

	@FXML
	private Button btnAddProduto;

	@FXML
	private TextField txtBairro;

	@FXML
	private TextField txtCNPJ;

	@FXML
	private TextField txtCep;

	@FXML
	private TextField txtCidadeUF;

	@FXML
	private TextField txtComplemento;

	@FXML
	private TextField txtEmail;

	@FXML
	private TextField txtNomeFornecedor;

	@FXML
	private TextField txtNumero;

	@FXML
	private TextField txtRua;

	@FXML
	private Button btnCancelar;

	@FXML
	private TextField txtTelefone;
	
	private FornecedoresDAO fornecedoresDAO = new FornecedoresDAO();
	private EnderecoDAO enderecoDAO = new EnderecoDAO();
	
	

	@FXML
	void OnbtnAddFornecedores(ActionEvent event) {
		
		// Criação do Objeto Endereços
		
		Enderecos endereco = new Enderecos();
		endereco.setCep(txtCep.getText());
		endereco.setRua(txtRua.getText());
		endereco.setNumero(txtNumero.getText());
		endereco.setBairro(txtBairro.getText());
		endereco.setComplemento(txtComplemento.getText());
		endereco.setCidadeUF("Estado");
		
		//Salvar Endereço e obter o ID gerado
		EnderecoDAO.create(endereco);
		
		// Criação do Objeto Fornecedores
		
		  Fornecedores fornecedor = new Fornecedores();
	        fornecedor.setNomeFornecedor(txtNomeFornecedor.getText());
	        fornecedor.setCnpj(txtCNPJ.getText());
	        fornecedor.setEmail(txtEmail.getText());
	        fornecedor.setTelefone(txtTelefone.getText());
	        fornecedor.setId_Endereço(endereco.getIdEndereço());
	        
	        fornecedoresDAO.create(fornecedor);

		if (controllerRelatorioFornecedor.FornecedoresEditor == null) {
			Fornecedores fornecedor1 = new Fornecedores();
			fornecedor1.setNomeFornecedor(txtNomeFornecedor.getText());
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

	public void initialize(URL arg0, ResourceBundle arg1) {
		FornecedoresDAO fornecedor = new FornecedoresDAO();

		// TODO Auto-generated method stub
		if (controllerRelatorioFornecedor.FornecedoresEditor != null) {
			txtNomeFornecedor.setText(controllerRelatorioFornecedor.FornecedoresEditor.getNomeFornecedor());
			txtCNPJ.setText(controllerRelatorioFornecedor.FornecedoresEditor.getCnpj());
			txtEmail.setText(controllerRelatorioFornecedor.FornecedoresEditor.getEmail());
			txtTelefone.setText(controllerRelatorioFornecedor.FornecedoresEditor.getTelefone());

		}
	}

	@FXML
	void OnbtnCancelar(ActionEvent event) {

		txtNomeFornecedor.setText("");
		txtCNPJ.setText("");
		txtEmail.setText("");
		txtTelefone.setText("");
		txtCep.setText("");
		controllerRelatorioFornecedor.FornecedoresEditor = null;

		Stage stage = (Stage) btnCancelar.getScene().getWindow();
		stage.close();

	}

}