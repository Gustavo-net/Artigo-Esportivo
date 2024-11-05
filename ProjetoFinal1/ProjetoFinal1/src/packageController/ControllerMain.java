package packageController; 

import application.Main;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import packageModel.ProdutoBaixoEstoque;
import package_controle.baixoEstoqueDAO;

import java.util.ArrayList;

public class ControllerMain {

    @FXML
    private TableView<ProdutoBaixoEstoque> ViewProdutos;

    @FXML
    private Button btnCadastros;
    @FXML
    private Button btnClientes;
    @FXML
    private Button btnFornecedores;
    @FXML
    private Button btnFuncionários;
    @FXML
    private Button btnVendas;
    @FXML
    private Button btnProdutos;

    @FXML
    private TableColumn<ProdutoBaixoEstoque, String> columnNome;
    @FXML
    private TableColumn<ProdutoBaixoEstoque, String> columnCodigo;
    @FXML
    private TableColumn<ProdutoBaixoEstoque, String> columnMarca;
    @FXML
    private TableColumn<ProdutoBaixoEstoque, String> columnCategoria;
    @FXML
    private TableColumn<ProdutoBaixoEstoque, String> columnQuantidade;

    @FXML
    void initialize() {
    	 columnNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
    	    columnCodigo.setCellValueFactory(new PropertyValueFactory<>("codigo"));
    	    columnMarca.setCellValueFactory(new PropertyValueFactory<>("marca"));
    	    columnCategoria.setCellValueFactory(new PropertyValueFactory<>("categoria"));
    	    columnQuantidade.setCellValueFactory(new PropertyValueFactory<>("quantidadeEstoque"));
        carregarProdutos();
    }

    private void carregarProdutos() {
        ArrayList<ProdutoBaixoEstoque> produtosBaixoEstoque = baixoEstoqueDAO.read(); 
        ViewProdutos.setItems(FXCollections.observableArrayList(produtosBaixoEstoque));
    }

    @FXML
    void OnbtnCadastros(ActionEvent event) {
        Main.changeScreen("cadastros");
    }

    @FXML
    void OnbtnFornecedores(ActionEvent event) {
        Main.changeScreen("fornecedores");
    }
    
    @FXML
    void OnbtnFuncionários(ActionEvent event) {
        Main.changeScreen("funcionarios");
    }

    @FXML
    void OnbtnVendas(ActionEvent event) {
        Main.changeScreen("vendas");
    }

    @FXML
    void OnbtnClientes(ActionEvent event) {
        Main.changeScreen("clientes");
    }

    @FXML
    void OnbtnProdutos(ActionEvent event) {
        Main.changeScreen("produtos");
    }
}
