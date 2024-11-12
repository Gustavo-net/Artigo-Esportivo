package packageController;

import java.sql.SQLException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;


import application.Main;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import packageModel.Funcionarios;
import packageModel.ProdutoBaixoEstoque;
import packageModel.Venda;
import package_controle.FuncionarioDAO;
import package_controle.VendasDAO;
import package_controle.baixoEstoqueDAO;

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
    private Button btnSair;
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
    private Label labelFaturamento;
    @FXML
    private Label labelFuncionarios;
    @FXML
    private Label labelVendas;

    private VendasDAO vendasDAO;
    private FuncionarioDAO funcionarioDAO;
    private baixoEstoqueDAO baixoEstoqueDAO;

    private DoubleProperty faturamentoTotal = new SimpleDoubleProperty(0.0);
    private IntegerProperty numeroFuncionarios = new SimpleIntegerProperty(0);
    private IntegerProperty numeroVendas = new SimpleIntegerProperty(0);

    private void exibirRelatorio() {
        try {
            faturamentoTotal.set(calcularFaturamentoTotal());
            numeroFuncionarios.set(contarFuncionarios());
            numeroVendas.set(contarVendasRealizadas());

            labelFaturamento.setText(String.format("R$ %.2f", faturamentoTotal.get()));
            labelFuncionarios.setText(String.valueOf(numeroFuncionarios.get()));
            labelVendas.setText(String.valueOf(numeroVendas.get()));
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public ControllerMain() {
        this.vendasDAO = new VendasDAO();
        this.funcionarioDAO = new FuncionarioDAO();
        this.baixoEstoqueDAO = new baixoEstoqueDAO();
    }

    @FXML
    void initialize() {
        columnNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
        columnCodigo.setCellValueFactory(new PropertyValueFactory<>("codigo"));
        columnMarca.setCellValueFactory(new PropertyValueFactory<>("marca"));
        columnCategoria.setCellValueFactory(new PropertyValueFactory<>("categoria"));
        columnQuantidade.setCellValueFactory(new PropertyValueFactory<>("quantidadeEstoque"));

        carregarProdutos();
        exibirRelatorio();
    }

    private void carregarProdutos() {
        ArrayList<ProdutoBaixoEstoque> produtosBaixoEstoque = baixoEstoqueDAO.read();
        ViewProdutos.setItems(FXCollections.observableArrayList(produtosBaixoEstoque));
    }




    public double calcularFaturamentoTotal() throws SQLException {
        double faturamento = 0.0;
        List<Venda> vendas = vendasDAO.listarVendas();
        for (Venda venda : vendas) {
            faturamento += venda.getTotalVenda();
        }
        return faturamento;
    }

    public int contarFuncionarios() throws SQLException {
        List<Funcionarios> funcionarios = funcionarioDAO.read();
        return funcionarios.size();
    }

    public int contarVendasRealizadas() throws SQLException {
        List<Venda> vendas = vendasDAO.listarVendas();
        return vendas.size();
    }

    @FXML
    void OnbtnSair(ActionEvent event) {
        Main.changeScreen("login");
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
