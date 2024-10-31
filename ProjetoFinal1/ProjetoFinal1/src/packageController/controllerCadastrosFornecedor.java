package packageController;

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import packageModel.Enderecos;
import packageModel.Fornecedores;
import package_controle.EnderecoDAO;
import package_controle.FornecedoresDAO;

public class controllerCadastrosFornecedor implements Initializable {

    @FXML
    private Button btnAddFornecedor;

    @FXML
    private Button btnCancelar;

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
    private TextField txtTelefone;

    private FornecedoresDAO fornecedoresDAO = new FornecedoresDAO();
    private EnderecoDAO enderecoDAO = new EnderecoDAO();

    @FXML
    public void OnbtnAddFornecedor(ActionEvent event) {
        // Verificar se todos os campos obrigatórios estão preenchidos
        if (validarCampos()) {
            Enderecos endereco = new Enderecos();
            preencherEndereco(endereco);

            // Criar o endereço
            enderecoDAO.create(endereco);

            Fornecedores fornecedor = new Fornecedores();
            preencherFornecedor(fornecedor, endereco.getIdEndereço());

            // Criar ou atualizar fornecedor
            if (controllerRelatorioFornecedor.FornecedoresEditor == null) {
                fornecedoresDAO.create(fornecedor);
                mostrarMensagem("Fornecedor cadastrado com sucesso!", Alert.AlertType.INFORMATION);
            } else {
                fornecedoresDAO.update(fornecedor);
                mostrarMensagem("Fornecedor atualizado com sucesso!", Alert.AlertType.INFORMATION);
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
        return !txtNomeFornecedor.getText().isEmpty() &&
               !txtCNPJ.getText().isEmpty() &&
               !txtEmail.getText().isEmpty() &&
               !txtTelefone.getText().isEmpty() &&
               !txtRua.getText().isEmpty() &&
               !txtNumero.getText().isEmpty() &&
               !txtBairro.getText().isEmpty() &&
               !txtCidadeUF.getText().isEmpty() &&
               !txtCep.getText().isEmpty();
    }

    private void mostrarMensagem(String mensagem, Alert.AlertType tipo) {
        Alert alert = new Alert(tipo);
        alert.setContentText(mensagem);
        alert.setHeaderText(null);
        alert.showAndWait();
    }

    private boolean confirmarCadastroOutro() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Cadastrar Novo Fornecedor");
        alert.setHeaderText("Deseja cadastrar outro fornecedor?");
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

    private void preencherFornecedor(Fornecedores fornecedor, String idEndereco) {
        fornecedor.setNomeFornecedor(txtNomeFornecedor.getText());
        fornecedor.setCnpj(txtCNPJ.getText());
        fornecedor.setEmail(txtEmail.getText());
        fornecedor.setTelefone(txtTelefone.getText());
        fornecedor.setId_Endereço(idEndereco);
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
        controllerRelatorioFornecedor.FornecedoresEditor = null;
    }

    private void fecharJanela() {
        Stage stage = (Stage) btnCancelar.getScene().getWindow();
        stage.close();
    }

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        if (controllerRelatorioFornecedor.FornecedoresEditor != null) {
            txtNomeFornecedor.setText(controllerRelatorioFornecedor.FornecedoresEditor.getNomeFornecedor());
            txtCNPJ.setText(controllerRelatorioFornecedor.FornecedoresEditor.getCnpj());
            txtEmail.setText(controllerRelatorioFornecedor.FornecedoresEditor.getEmail());
            txtTelefone.setText(controllerRelatorioFornecedor.FornecedoresEditor.getTelefone());
        }
    }
}
