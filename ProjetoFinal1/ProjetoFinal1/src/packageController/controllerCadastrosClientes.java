package packageController;

import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
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
import packageConnection.ConnectionDatabase;
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

	private Clientes clienteEditor;

	@FXML
	public void OnbtnAddCliente(ActionEvent event) {
		Connection con = null;
		try {
			if (validarCampos()) {
				Enderecos endereco = new Enderecos();
				preencherEndereco(endereco);

				con = ConnectionDatabase.getConnection();
				con.setAutoCommit(false);

				String idEndereco = EnderecoDAO.create(endereco);
				if (idEndereco == null) {
					throw new SQLException("Erro ao inserir endereço.");
				}

				Clientes novoCliente = new Clientes();
				preencherCliente(novoCliente, idEndereco); 

				if (clienteEditor != null) {
					novoCliente.setIdCliente(clienteEditor.getIdCliente());
					ClienteDAO.update(novoCliente);
					mostrarMensagem("Cliente atualizada com sucesso!", Alert.AlertType.INFORMATION);

				} else {
					ClienteDAO.create(novoCliente);
					mostrarMensagem("Cliente cadastrado com sucesso!", Alert.AlertType.INFORMATION);

				}

				con.commit(); 


				if (confirmarCadastroOutro()) {
					limparCampos();
				} else {
					fecharJanela();
				}
			} else {
				mostrarMensagem("Por favor, preencha todos os campos obrigatórios.", Alert.AlertType.WARNING);
			}
		} catch (SQLException e) {
			if (con != null) {
				try {
					con.rollback(); 
				} catch (SQLException rollbackEx) {
					rollbackEx.printStackTrace();
				}
			}

			e.printStackTrace();
			mostrarMensagem("Erro ao cadastrar cliente. Tente novamente.", Alert.AlertType.ERROR);
		} finally {
			if (con != null) {
				try {
					con.setAutoCommit(true); 
					con.close(); 
				} catch (SQLException closeEx) {
					closeEx.printStackTrace();
				}
			}
		}
	}

	private void mostrarMensagem(String mensagem, Alert.AlertType tipo) {
		Alert alert = new Alert(tipo);
		alert.setContentText(mensagem);
		alert.setHeaderText(null);
		alert.showAndWait();
	}

	private boolean validarCampos() {
		return !txtNomeCliente.getText().isEmpty() && !txtCPF.getText().isEmpty() && !txtEmail.getText().isEmpty()
				&& !txtTelefone.getText().isEmpty() && !txtRua.getText().isEmpty() && !txtNumero.getText().isEmpty()
				&& !txtBairro.getText().isEmpty() && !txtCidadeUF.getText().isEmpty() && !txtCep.getText().isEmpty();
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

	private void preencherCliente(Clientes cliente, String idEndereco) {
		cliente.setNomeCliente(txtNomeCliente.getText());
		cliente.setCpf(txtCPF.getText());
		cliente.setEmail(txtEmail.getText());
		cliente.setTelefone(txtTelefone.getText());
		cliente.setId_Endereco(idEndereco);
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
			clienteEditor = controllerRelatorioClientes.clienteEditor;

			txtNomeCliente.setText(clienteEditor.getNomeCliente());
			txtCPF.setText(clienteEditor.getCpf());
			txtEmail.setText(clienteEditor.getEmail());
			txtTelefone.setText(clienteEditor.getTelefone());
			txtRua.setText(clienteEditor.getRua());
			txtNumero.setText(clienteEditor.getNumero());
			txtBairro.setText(clienteEditor.getBairro());
			txtCidadeUF.setText(clienteEditor.getCidadeUF());
			txtCep.setText(clienteEditor.getCep());
			txtComplemento.setText(clienteEditor.getComplemento());
		}
	}
}
