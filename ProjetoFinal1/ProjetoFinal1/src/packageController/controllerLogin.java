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
import package_controle.FuncionarioDAO;

public class controllerLogin {

    @FXML
    private TextField txtSenha;
    @FXML
    private Button btnLogar;
    @FXML
    private PasswordField txtPassword;
    @FXML
    private TextField txtUser;
    @FXML
    private ToggleButton verSenha;
    static Funcionarios funcionario = new Funcionarios();

    @FXML
    void btnActionLogar(ActionEvent event) {
        FuncionarioDAO fDAO = new FuncionarioDAO();
       
        Funcionarios funcionarios = fDAO.autenticarUser(txtUser.getText(), txtPassword.getText());

        if (funcionarios != null && funcionarios.getCpf() != null && funcionarios.getSenha() != null) {
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
            txtSenha.setText(txtPassword.getText());
        } else {
            txtPassword.setVisible(true);
            txtUser.setText("");
        }
    }
}