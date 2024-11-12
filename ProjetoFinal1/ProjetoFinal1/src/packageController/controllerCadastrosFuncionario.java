package packageController;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import packageConnection.ConnectionDatabase;
import javafx.scene.control.ButtonType;
import packageModel.Enderecos;
import packageModel.Funcionarios;
import package_controle.EnderecoDAO;
import package_controle.FuncionarioDAO;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Optional;

public class controllerCadastrosFuncionario {

    @FXML
    private TextField txtNomeFuncionario, txtCpf, txtEmail, txtTelefone, txtCep, txtCidadeUF, txtComplemento, txtRua, txtNumero, txtBairro, txtCargo;

    @FXML
    private DatePicker dataNasc, dataContr;  
    
    @FXML
    private ComboBox<String> comboxSexo;
    
    @FXML
    private Button btnAddFuncionario, btnCancelar;
    
    @FXML
    public void onbtnAddFuncionario(ActionEvent event) throws SQLException {
    	Connection con = null;
        if (validarCampos()) {
            Funcionarios funcionario = new Funcionarios();
            Enderecos endereco = new Enderecos();
            preencherFuncionario(funcionario);
            
            con = ConnectionDatabase.getConnection();
			con.setAutoCommit(false); // Inicia a transação

			String idEndereco = EnderecoDAO.create(endereco);
			if (idEndereco == null) {
				throw new SQLException("Erro ao inserir endereço.");
			}
            
            
            try {
                if (controllerRelatorioFuncionarios.funcionariosEditor == null) {
                    FuncionarioDAO.create(funcionario);
                    mostrarMensagem("Funcionário cadastrado com sucesso!", Alert.AlertType.INFORMATION);
                } else {
                    FuncionarioDAO.update(funcionario);
                    mostrarMensagem("Funcionário atualizado com sucesso!", Alert.AlertType.INFORMATION);
                }
            } catch (Exception e) {
                e.printStackTrace();
                mostrarMensagem("Erro ao cadastrar o funcionário: " + e.getMessage(), Alert.AlertType.ERROR);
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
               !txtCep.getText().isEmpty() &&
               comboxSexo.getValue() != null &&
               dataNasc.getValue() != null &&  
               dataContr.getValue() != null;  
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
        funcionario.setSexo(comboxSexo.getValue());
        funcionario.setDataNasc(dataNasc.getValue()); 
        funcionario.setDataCont(dataContr.getValue());
        funcionario.setCargo(txtCargo.getText());

        funcionario.setRua(txtRua.getText());
        funcionario.setNumero(txtNumero.getText());
        funcionario.setBairro(txtBairro.getText());
        funcionario.setComplemento(txtComplemento.getText());
        funcionario.setCidadeUF(txtCidadeUF.getText());
        funcionario.setCep(txtCep.getText());
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
        txtTelefone.setText("");
        txtCep.setText("");
        txtRua.setText("");
        txtNumero.setText("");
        txtBairro.setText("");
        txtCidadeUF.setText("");
        txtComplemento.setText("");
        txtCargo.setText("");  
        comboxSexo.setValue(null);  
        dataNasc.setValue(null); 
        dataContr.setValue(null); 
    }

    private void fecharJanela() {
        Stage stage = (Stage) btnCancelar.getScene().getWindow();
        stage.close();
    }

    @FXML
    public void initialize() {
        comboxSexo.getItems().addAll("Masculino", "Feminino");

        if (controllerRelatorioFuncionarios.funcionariosEditor != null) {
            txtNomeFuncionario.setText(controllerRelatorioFuncionarios.funcionariosEditor.getNomeFuncionario());
            txtCpf.setText(controllerRelatorioFuncionarios.funcionariosEditor.getCpf());
            txtEmail.setText(controllerRelatorioFuncionarios.funcionariosEditor.getEmail());
            txtTelefone.setText(controllerRelatorioFuncionarios.funcionariosEditor.getTelefone());
            comboxSexo.setValue(controllerRelatorioFuncionarios.funcionariosEditor.getSexo());
            dataNasc.setValue(controllerRelatorioFuncionarios.funcionariosEditor.getDataNasc());
            dataContr.setValue(controllerRelatorioFuncionarios.funcionariosEditor.getDataCont());
            txtCargo.setText(controllerRelatorioFuncionarios.funcionariosEditor.getCargo());
            txtRua.setText(controllerRelatorioFuncionarios.funcionariosEditor.getRua());
            txtNumero.setText(controllerRelatorioFuncionarios.funcionariosEditor.getNumero());
            txtBairro.setText(controllerRelatorioFuncionarios.funcionariosEditor.getBairro());
            txtComplemento.setText(controllerRelatorioFuncionarios.funcionariosEditor.getComplemento());
            txtCidadeUF.setText(controllerRelatorioFuncionarios.funcionariosEditor.getCidadeUF());
            txtCep.setText(controllerRelatorioFuncionarios.funcionariosEditor.getCep());
        }
    }
}
