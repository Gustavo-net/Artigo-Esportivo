package packageController;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import application.Main;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import packageModel.Vendas;
import package_controle.VendaDAO;

public class ControllerRelatorioVendas implements Initializable {

    @FXML
    private Button btnCadastros;

    @FXML
    private Button btnClientes;

    @FXML
    private Button btnSair;

    @FXML
    private Button btnExcluir;

    @FXML
    private ComboBox<Vendas> boxFiltrar;

    @FXML
    private Button btnEditar;

    @FXML
    private Button btnInserir;

    @FXML
    private Button btnPesquisar;

    @FXML
    private Button btnProdutos;

    @FXML
    private Button btnFuncionarios;

    @FXML
    private Button btnVoltar;

    @FXML
    private TableColumn<Vendas, String> columnDataVenda;

    @FXML
    private TableColumn<Vendas, String> columnCodigo;

    @FXML
    private TableColumn<Vendas, String> columnDescricao;

    @FXML
    private TableColumn<Vendas, String> columnPrecoUn;

    @FXML
    private TableColumn<Vendas, String> columnQuantidade;

    @FXML
    private TableColumn<Vendas, String> columnTotal;

    @FXML
    private TableColumn<Vendas, String> columnStatus;

    @FXML
    private TextField labelPesquisar;

    @FXML
    private TableView<Vendas> tableVendas;

    private ObservableList<Vendas> arrayVendas;
    private VendaDAO vendaDAO = new VendaDAO();

    public void carregarTableVendas() {
        
    }

    @FXML
    void OnbtnClientes(ActionEvent event) {
        Main.changeScreen("clientes");
    }
    @FXML
    void OnbtnFuncionários(ActionEvent event) {
        Main.changeScreen("funcionarios");
    }
    @FXML
    void OnbtnFornecedores(ActionEvent event) {
        Main.changeScreen("fornecedores");
    }

    @FXML
    void OnbtnCadastros(ActionEvent event) {
        Main.changeScreen("cadastros");
    }

    @FXML
    void OnbtnSair(ActionEvent event) {
        Main.changeScreen("login");
    }

    @FXML
    void OnbtnExcluir(ActionEvent event) {
    }

    @FXML
    void OnbtnFuncionarios(ActionEvent event) {
        Main.changeScreen("funcionarios");
    }

    @FXML
    void OnbtnEditar(ActionEvent event) {
    }

    @FXML
    void OnbtnInserir(ActionEvent event) throws IOException {
        Main.TelaCadastroVenda();
    }

    @FXML
    void OnbtnPesquisar(ActionEvent event) {
    }

    @FXML
    void OnbtnProdutos(ActionEvent event) {
        Main.changeScreen("produtos");
    }

    @FXML
    void OnbtnVoltar(ActionEvent event) {
    }

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        carregarTableVendas();
    }
}
