package packageController;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import packageModel.Fornecedores;
import package_controle.FornecedoresDAO;

public class controllerCadastrosFornecedor {

	@FXML
	private TextField txtNomeFornecedor;
	@FXML
	private TextField txtCNPJ;
	@FXML
	private TextField txtEmail;
	@FXML
	private TextField txtTelefone;
	@FXML
	private TextField txtRua;
	@FXML
	private TextField txtNumero;
	@FXML
	private TextField txtBairro;
	@FXML
	private TextField txtCidadeUF;
	@FXML
	private TextField txtCep;
	@FXML
	private TextField txtComplemento;

	private Fornecedores fornecedorEditor;

	public void initialize() {
		if (controllerRelatorioFornecedor.FornecedoresEditor != null) {
			fornecedorEditor = controllerRelatorioFornecedor.FornecedoresEditor;
			txtNomeFornecedor.setText(fornecedorEditor.getNomeFornecedor());
			txtCNPJ.setText(fornecedorEditor.getCnpj());
			txtEmail.setText(fornecedorEditor.getEmail());
			txtTelefone.setText(fornecedorEditor.getTelefone());
			txtCep.setText(fornecedorEditor.getCep());
			txtBairro.setText(fornecedorEditor.getBairro());
			txtCidadeUF.setText(fornecedorEditor.getCidadeUF());
			txtComplemento.setText(fornecedorEditor.getComplemento());
			txtNumero.setText(fornecedorEditor.getNumero());
			txtRua.setText(fornecedorEditor.getRua());
		}
	}

	@FXML
	public void OnbtnAddFornecedor(ActionEvent event) {
		if (validarCampos()) {
			Fornecedores fornecedor = new Fornecedores();
			fornecedor.setNomeFornecedor(txtNomeFornecedor.getText());
			fornecedor.setCnpj(txtCNPJ.getText());
			fornecedor.setEmail(txtEmail.getText());
			fornecedor.setTelefone(txtTelefone.getText());
			fornecedor.setRua(txtRua.getText());
			fornecedor.setNumero(txtNumero.getText());
			fornecedor.setBairro(txtBairro.getText());
			fornecedor.setCidadeUF(txtCidadeUF.getText());
			fornecedor.setCep(txtCep.getText());
			fornecedor.setComplemento(txtComplemento.getText());

			if (fornecedorEditor != null) {
				fornecedor.setIdFornecedor(fornecedorEditor.getIdFornecedor());
				FornecedoresDAO.update(fornecedor);
				mostrarMensagem("Fornecedor atualizado com sucesso!", AlertType.INFORMATION);
			} else {
				String idFornecedor = FornecedoresDAO.create(fornecedor);
				if (idFornecedor != null) {
					mostrarMensagem("Fornecedor cadastrado com sucesso!", AlertType.INFORMATION);
				} else {
					mostrarMensagem("Erro ao cadastrar fornecedor. Tente novamente.", AlertType.ERROR);
				}
			}

			fecharJanela();
		} else {
			mostrarMensagem("Por favor, preencha todos os campos obrigatórios.", AlertType.WARNING);
		}
	}

	private boolean validarCampos() {
		return !txtNomeFornecedor.getText().isEmpty() && !txtCNPJ.getText().isEmpty() && !txtEmail.getText().isEmpty()
				&& !txtTelefone.getText().isEmpty() && !txtRua.getText().isEmpty() && !txtNumero.getText().isEmpty()
				&& !txtBairro.getText().isEmpty() && !txtCidadeUF.getText().isEmpty() && !txtCep.getText().isEmpty();
	}

	private void mostrarMensagem(String mensagem, AlertType tipo) {
		Alert alert = new Alert(tipo);
		alert.setContentText(mensagem);
		alert.setHeaderText(null);
		alert.showAndWait();
	}

	private void fecharJanela() {
		Stage stage = (Stage) txtNomeFornecedor.getScene().getWindow();
		stage.close();
	}

	@FXML
	public void OnbtnCancelar(ActionEvent event) {
		limparCampos();
		fecharJanela();
	}

	private void limparCampos() {
		txtNomeFornecedor.setText("");
		txtCNPJ.setText("");
		txtEmail.setText("");
		txtTelefone.setText("");
		txtRua.setText("");
		txtNumero.setText("");
		txtBairro.setText("");
		txtCidadeUF.setText("");
		txtCep.setText("");
		txtComplemento.setText("");
	}
}
