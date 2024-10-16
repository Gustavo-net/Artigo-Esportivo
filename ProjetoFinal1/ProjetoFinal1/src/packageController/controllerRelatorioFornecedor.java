package packageController;

import application.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import packageModel.Fornecedores;

public class controllerRelatorioFornecedor {

    @FXML
	private TableColumn<Fornecedores, String> CEP;

    @FXML
    private TableColumn<Fornecedores, String> CNPJ;

    @FXML
    private TableColumn<Fornecedores, String> Email;

    @FXML
    private TableColumn<Fornecedores, String> ID;

    @FXML
    private TableColumn<Fornecedores, String> Nome;

    @FXML
    private TableColumn<Fornecedores, String> Telefone;

    @FXML
    private Button bntCadastros;

    @FXML
    private Button bntClientes;

    @FXML
    private Button bntSair;

    @FXML
    private ComboBox<Fornecedores> boxFiltrar;

    @FXML
    private Button btnEditar;

    @FXML
    private Button btnFornecedores;

    @FXML
    private Button btnFuncionarios;

    @FXML
    private Button btnInserir;

    @FXML
    private Button btnPesquisar;

    @FXML
    private Button btnProdutos;

    @FXML
    private Button btnVendas;

    @FXML
    private Button btnVoltar;

    @FXML
    private TableView<Fornecedores> tableFornecedores;

    @FXML
    private TextField txtProcurarFornecedores;

    @FXML
    void OnbntClientes(ActionEvent event) {
    	Main.changeScreen("clientes");

    }

    @FXML
    void OnbtnCadastros(ActionEvent event) {
    	

    }

    @FXML
    void OnbtnEditar(ActionEvent event) {

    }

    @FXML
    void OnbtnFornecedores(ActionEvent event) {
    	Main.changeScreen("fornecedores");

    }

    @FXML
    void OnbtnFuncionarios(ActionEvent event) {
    	Main.changeScreen("funcionarios");

    }

    @FXML
    void OnbtnInserir(ActionEvent event) {

    }

    @FXML
    void OnbtnPesquisar(ActionEvent event) {

    }

    @FXML
    void OnbtnProdutos(ActionEvent event) {
    	Main.changeScreen("produtos");

    }

    @FXML
    void OnbtnSair(ActionEvent event) {

    }

    @FXML
    void OnbtnVendas(ActionEvent event) {

    }

    @FXML
    void OnbtnVoltar(ActionEvent event) {

    }

}
