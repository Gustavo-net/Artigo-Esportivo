package packageController;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import packageModel.Produtos;

public class controllerRelatorioProdutos {

    @FXML
    private ComboBox<Produtos> boxCategoria;

    @FXML
    private Button btnAddProduto;

    @FXML
    private Button btnSair;

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

    @FXML
    void OnbtnAddProduto(ActionEvent event) {

    }

    @FXML
    void OnbtnSair(ActionEvent event) {

    }

}
