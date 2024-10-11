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

public class controllerLogin {

	@FXML
	private TextField txtSenha;
	@FXML
	private Button btnLogar;
	@FXML
	private PasswordField txtPassword; // Campo de senha
	@FXML
	private TextField txtUser; // Campo de usuário
	@FXML
	private ToggleButton verSenha; // Botão para visualizar a senha
	static Funcionarios funcionario = new Funcionarios(); // Alterado para Funcionario


	@FXML
	void btnActionLogar(ActionEvent event) {
		FuncionarioDAO fDAO = new FuncionarioDAO(); // Alterado para FuncionarioDAO
		
		Funcionarios funcionarios = fDAO.autenticarUser(txtUser.getText(), txtPassword.getText());

		// Verificar se a autenticação foi bem-sucedida
		if ( funcionarios.getCpf() != null && funcionarios.getSenha() != null) {
			Alert saudacao = new Alert(Alert.AlertType.CONFIRMATION);
			saudacao.setHeaderText("Saudação");
			saudacao.setTitle("Bem-vindo");
			saudacao.setContentText("Seja bem-vindo de volta, " + funcionarios.getNomeFuncionario() + "!");
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
    void OnVerSenha(ActionEvent event) {
    	if (verSenha.isSelected()) {
    		txtSenha.setText(txtPassword.getText());
			txtPassword.setVisible(false);
			txtSenha.setText(txtPassword.getText()); // Aqui pode ser melhor usar outro campo se necessário
		} else {
			txtPassword.setVisible(true);
			txtUser.setText(""); // Limpar campo de visualização
		}
	}
    
}
