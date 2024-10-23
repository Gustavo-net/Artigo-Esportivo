package packageController;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import packageModel.Fornecedores;
import package_controle.FornecedoresDAO;

public class controllerCadastrosFornecedor {

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

	@FXML
	void OnbtnAddFornecedores(ActionEvent event) {

		if (controllerRelatorioFornecedor.FornecedoresEditor == null) {
			Fornecedores fornecedor = new Fornecedores();
			fornecedor.setNomeFornecedor(txtNomeFornecedor.getText());
			fornecedor.setCnpj(txtCNPJ.getText());
			fornecedor.setEmail(txtEmail.getText());
			fornecedor.setTelefone(txtTelefone.getText());
			FornecedoresDAO forn = new FornecedoresDAO();

		} else {
			Fornecedores fornecedor = new Fornecedores();
			fornecedor.setNomeFornecedor(txtNomeFornecedor.getText());
			fornecedor.setCnpj(txtCNPJ.getText());
			fornecedor.setEmail(txtEmail.getText());
			fornecedor.setTelefone(txtTelefone.getText());
			FornecedoresDAO forn = new FornecedoresDAO();
			forn.update(fornecedor);

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
