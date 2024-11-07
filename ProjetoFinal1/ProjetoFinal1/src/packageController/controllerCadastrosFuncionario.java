package packageController;

import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Button;
import javafx.event.ActionEvent;
import packageModel.Enderecos;
import packageModel.Funcionarios;
import package_controle.FuncionarioDAO;

import java.util.Optional;

public class controllerCadastrosFuncionario {

    @FXML
    private Button btnAddFuncionario;

    @FXML
    private Button btnCancelar;

    @FXML
    private TextField txtNomeFuncionario;

    @FXML
    private TextField txtCpf;

    @FXML
    private TextField txtEmail;

    @FXML
    private TextField txtTelefone;

    @FXML
    private TextField txtCep;

    @FXML
    private TextField txtCidadeUF;

    @FXML
    private TextField txtComplemento;


    @FXML
    private TextField txtNomeCliente;

    @FXML
    private TextField txtNumero;

    @FXML
    private TextField txtRua;

    @FXML
    private TextField txtBairro;

    @FXML
    private ComboBox<String> cbSexo;

    private FuncionarioDAO funcionariosDAO = new FuncionarioDAO();

    @FXML
    public void OnbtnAddFuncionario(ActionEvent event) {
        if (validarCampos()) {
            Funcionarios funcionario = new Funcionarios();
            preencherFuncionario(funcionario);

           
            if (controllerRelatorioFuncionarios.funcionariosEditor == null) {
                funcionariosDAO.create(funcionario);
                mostrarMensagem("Funcionário cadastrado com sucesso!", Alert.AlertType.INFORMATION);
            } else {
                funcionariosDAO.update(funcionario);
                mostrarMensagem("Funcionário atualizado com sucesso!", Alert.AlertType.INFORMATION);
            }

            if (confirmarCadastroOutro()) {
                limparCampos();
            } else {
                fecharJanela();
            }
        } else {
            mostrarMensagem("Por favor, preencha todos os campos obrigatórios.", Alert.AlertType.WARNING);
        }
    }

    private boolean validarCampos() {
        return !txtNomeFuncionario.getText().isEmpty() &&
               !txtCpf.getText().isEmpty() &&
               !txtEmail.getText().isEmpty() &&
               !txtTelefone.getText().isEmpty() &&
               !txtRua.getText().isEmpty() &&
               !txtNumero.getText().isEmpty() &&
               !txtBairro.getText().isEmpty() &&
               !txtCidadeUF.getText().isEmpty() &&
               !txtCep.getText().isEmpty()&&
               cbSexo.getValue() != null;  
    }

    private void mostrarMensagem(String mensagem, Alert.AlertType tipo) {
        Alert alert = new Alert(tipo);
        alert.setContentText(mensagem);
        alert.setHeaderText(null);
        alert.showAndWait();
    }

    private boolean confirmarCadastroOutro() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Cadastrar Novo Funcionário");
        alert.setHeaderText("Deseja cadastrar outro funcionário?");
        ButtonType simButton = new ButtonType("Sim");
        ButtonType naoButton = new ButtonType("Não");
        alert.getButtonTypes().setAll(simButton, naoButton);

        Optional<ButtonType> result = alert.showAndWait();
        return result.isPresent() && result.get() == simButton;
    }

    private void preencherFuncionario(Funcionarios funcionario) {
        funcionario.setNomeFuncionario(txtNomeFuncionario.getText());
        funcionario.setCpf(txtCpf.getText());
        funcionario.setEmail(txtEmail.getText());
        funcionario.setTelefone(txtTelefone.getText());
        funcionario.setSexo(cbSexo.getValue());  
    }
    private void preencherEndereco(Enderecos endereco) {
        endereco.setCep(txtCep.getText());
        endereco.setRua(txtRua.getText());
        endereco.setNumero(txtNumero.getText());
        endereco.setBairro(txtBairro.getText());
        endereco.setComplemento(txtComplemento.getText());
        endereco.setCidadeUF(txtCidadeUF.getText());
    }

    @FXML
    public void OnbtnCancelar(ActionEvent event) {
        limparCampos();
        fecharJanela();
    }

    private void limparCampos() {
        txtNomeFuncionario.setText("");
        txtCpf.setText("");
        txtEmail.setText("");
        txtCep.setText("");
        txtCidadeUF.setText("");
        txtComplemento.setText("");
        txtNumero.setText("");
        txtRua.setText("");
        txtTelefone.setText("");
        cbSexo.setValue(null);  
    }

    private void fecharJanela() {
        Stage stage = (Stage) btnCancelar.getScene().getWindow();
        stage.close();
    }

    @FXML
    public void initialize() {

        if (controllerRelatorioFuncionarios.funcionariosEditor != null) {
            txtNomeFuncionario.setText(controllerRelatorioFuncionarios.funcionariosEditor.getNomeFuncionario());
            txtCpf.setText(controllerRelatorioFuncionarios.funcionariosEditor.getCpf());
            txtEmail.setText(controllerRelatorioFuncionarios.funcionariosEditor.getEmail());
            txtTelefone.setText(controllerRelatorioFuncionarios.funcionariosEditor.getTelefone());
            cbSexo.setValue(controllerRelatorioFuncionarios.funcionariosEditor.getSexo());
        }
    }
}
