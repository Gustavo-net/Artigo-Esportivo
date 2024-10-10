package packageController;

import application.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import packageModel.Funcionarios;
import package_controle.FuncionarioDAO; // Importando a classe FuncionarioDAO

public class ControllerLogin {

	@FXML
	private Button btnButton;

	@FXML
	private PasswordField txtPassword; // Campo de senha
	@FXML
	private TextField txtUser; // Campo de usuário
	@FXML
	private ToggleButton verSenha; // Botão para visualizar a senha
	static Funcionarios funcionario = new Funcionarios(); // Alterado para Funcionario

	@FXML
	void btnButtonTeste(ActionEvent event) {
		FuncionarioDAO fDAO = new FuncionarioDAO(); // Alterado para FuncionarioDAO

		funcionario = fDAO.autenticarUser(txtUser.getText(), txtPassword.getText());

		// Verificar se a autenticação foi bem-sucedida
		if (funcionario.getCpf() != null && funcionario.getSenha() != null) {
			Alert saudacao = new Alert(Alert.AlertType.CONFIRMATION);
			saudacao.setHeaderText("Saudação");
			saudacao.setTitle("Bem-vindo");
			saudacao.setContentText("Seja bem-vindo de volta, " + funcionario.getNomeFuncionario() + "!");
			saudacao.show();
			Main.changeScreen("main");
		} else {
			Alert erro = new Alert(Alert.AlertType.ERROR);
			erro.setTitle("Falha ao realizar o login");
			erro.setHeaderText("Erro!");
			erro.setContentText("Usuário ou senha incorretos! Verifique se as informações estão corretas.");
			erro.show();
		}
	}

	@FXML
	void visualizarSenha(ActionEvent event) {
		if (verSenha.isSelected()) {
			txtPassword.setVisible(false);
			txtUser.setText(txtPassword.getText()); // Aqui pode ser melhor usar outro campo se necessário
		} else {
			txtPassword.setVisible(true);
			txtUser.setText(""); // Limpar campo de visualização
		}
	}
}
