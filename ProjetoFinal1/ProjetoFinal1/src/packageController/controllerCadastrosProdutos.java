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
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import packageModel.Produtos;
import package_controle.ProdutoDAO;

public class controllerCadastrosProdutos implements Initializable {

    @FXML
    private ComboBox<String> boxCategoria; 

    @FXML
    private Button btnAddProduto;

    @FXML
    private Button btnCancelar;

    @FXML
    private TextField txtCodigo;

    @FXML
    private TextField txtCor;

    @FXML
    private TextField txtDescricao;

    @FXML
    private TextField txtEstoqueAtual;

    @FXML
    private TextField txtEstoqueMinimo;

    @FXML
    private TextField txtMarca;

    @FXML
    private TextField txtNomeProduto;

    @FXML
    private TextField txtPreco;

    @FXML
    private TextField txtTamanho;

    @FXML
    private TextField txtTamanhoMaximo;

    private ProdutoDAO produtoDAO = new ProdutoDAO();

    @FXML
    public void OnbtnAddProduto(ActionEvent event) {
        if (validarCampos()) {
            Produtos produto = new Produtos();
            preencherProduto(produto);
            
            produtoDAO.create(produto);
            mostrarMensagem("Produto cadastrado com sucesso!", Alert.AlertType.INFORMATION);
            
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
        return !txtNomeProduto.getText().isEmpty() &&
               !txtCodigo.getText().isEmpty() &&
               !txtPreco.getText().isEmpty() &&
               !txtMarca.getText().isEmpty() &&
               !txtEstoqueAtual.getText().isEmpty() &&
               !txtEstoqueMinimo.getText().isEmpty() &&
               !txtCor.getText().isEmpty() &&
               !txtTamanho.getText().isEmpty() &&
               !txtTamanhoMaximo.getText().isEmpty() &&
               !txtDescricao.getText().isEmpty();
    }

    private void mostrarMensagem(String mensagem, Alert.AlertType tipo) {
        Alert alert = new Alert(tipo);
        alert.setContentText(mensagem);
        alert.setHeaderText(null);
        alert.showAndWait();
    }

    private boolean confirmarCadastroOutro() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Cadastrar Novo Produto");
        alert.setHeaderText("Deseja cadastrar outro produto?");
        ButtonType simButton = new ButtonType("Sim");
        ButtonType naoButton = new ButtonType("Não");
        alert.getButtonTypes().setAll(simButton, naoButton);

        Optional<ButtonType> result = alert.showAndWait();
        return result.isPresent() && result.get() == simButton;
    }

    private void preencherProduto(Produtos produto) {
        produto.setNome(txtNomeProduto.getText());
        produto.setCodigo(txtCodigo.getText());
        produto.setPrecoUnitario(Double.parseDouble(txtPreco.getText()));
        produto.setMarca(txtMarca.getText());
        produto.setEstoqueDisp(Integer.parseInt(txtEstoqueAtual.getText()));
        produto.setDescricao(txtDescricao.getText());
    }

    @FXML
    public void OnbtnCancelar(ActionEvent event) {
        limparCampos();
        fecharJanela();
    }

    private void limparCampos() {
        txtNomeProduto.setText("");
        txtCodigo.setText("");
        txtPreco.setText("");
        txtMarca.setText("");
        txtEstoqueAtual.setText("");
        txtEstoqueMinimo.setText("");
        txtCor.setText("");
        txtTamanho.setText("");
        txtTamanhoMaximo.setText("");
        txtDescricao.setText("");
    }

    private void fecharJanela() {
        Stage stage = (Stage) btnCancelar.getScene().getWindow();
        stage.close();
    }

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        if (controllerRelatorioProduto.produtoEditor != null) {
            txtNomeProduto.setText(controllerRelatorioProduto.produtoEditor.getNome());
            txtCodigo.setText(controllerRelatorioProduto.produtoEditor.getCodigo());
            txtMarca.setText(controllerRelatorioProduto.produtoEditor.getMarca());
            txtDescricao.setText(controllerRelatorioProduto.produtoEditor.getDescricao());
            txtPreco.setText(Double.toString(controllerRelatorioProduto.produtoEditor.getPrecoUnitario()));
            txtEstoqueAtual.setText(Integer.toString(controllerRelatorioProduto.produtoEditor.getEstoqueDisp()));
        }
    }    
}
