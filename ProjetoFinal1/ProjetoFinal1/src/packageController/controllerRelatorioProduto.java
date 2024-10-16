package packageController;

import application.Main;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import packageModel.Produtos;
import package_controle.ProdutoDAO;

public class controllerRelatorioProduto {

	@FXML
	private TableView<Produtos> tableRelatorioProduto;

	@FXML
	private ComboBox<Produtos> boxFiltrar;

	@FXML
	private Button btnCadastros;

	@FXML
	private Button btnClientes;

	@FXML
	private Button btnEditar;

	@FXML
	private Button btnFornecedores;

	@FXML
	private Button btnFuncionários;

	@FXML
	private Button btnInserir;

	@FXML
	private Button btnPesquisar;

	@FXML
	private Button btnVendas;

	@FXML
	private Button btnVoltar;

	@FXML
	private Button btnSair;

	@FXML
	private TableColumn<Produtos, String> columnCodigo;

	@FXML
	private TableColumn<Produtos, String> columnDescricao;

	@FXML
	private TableColumn<Produtos, String> columnEstoqueAtual;

	@FXML
	private TableColumn<Produtos, String> columnId;

	@FXML
	private TableColumn<Produtos, String> columnMarca;

	@FXML
	private TableColumn<Produtos, String> columnNome;

	@FXML
	private TableColumn<Produtos, String> columnPrecoUn;

	@FXML
	private TextField txtPesquisar;

	private ObservableList<Produtos> arrayProduto;
	private ProdutoDAO produtoDAO = new ProdutoDAO();

	public static Produtos produtoEditor = new Produtos();

	public void carregarTableProduto() {
		arrayProduto = FXCollections.observableArrayList(ProdutoDAO.read());

		columnId.setCellValueFactory(new PropertyValueFactory<>("idProduto"));
		columnNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
		columnCodigo.setCellValueFactory(new PropertyValueFactory<>("codigo"));
		columnMarca.setCellValueFactory(new PropertyValueFactory<>("marca"));
		columnDescricao.setCellValueFactory(new PropertyValueFactory<>("descricao"));
		columnPrecoUn.setCellValueFactory(new PropertyValueFactory<>("precoUnitario"));
		columnEstoqueAtual.setCellValueFactory(new PropertyValueFactory<>("estoqueDisp"));

		tableRelatorioProduto.setItems(arrayProduto);
	}

	@FXML
	void OnbtnCadastros(ActionEvent event) {
		Main.changeScreen("cadastros");
	}

	@FXML
	void OnbtnClientes(ActionEvent event) {
		Main.changeScreen("clientes");
	}

	public static Produtos produtoEditar = new Produtos();

	@FXML
	void OnbtnEditar(ActionEvent event) {
		if (tableRelatorioProduto.getSelectionModel().getSelectedIndex() == -1) {
			Alert mensagemDeErro = new Alert(Alert.AlertType.INFORMATION);
			mensagemDeErro.setContentText("Selecione um Produto para Editar Primeiro!");
			mensagemDeErro.show();
		} else {
			int i = tableRelatorioProduto.getSelectionModel().getSelectedIndex();
			produtoEditar = tableRelatorioProduto.getItems().get(i);
//    		Main.TelaCadastroProduto();
		}
		carregarTableProduto();
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
	void OnbtnInserir(ActionEvent event) {
		produtoEditor = null;
//    	Main.TelaCadastroProdutos();
		carregarTableProduto();
	}

	@FXML
	void OnbtnPesquisar(ActionEvent event) {
		arrayProduto = FXCollections.observableArrayList(produtoDAO.search(txtPesquisar.getText()));

		columnId.setCellValueFactory(new PropertyValueFactory<>("idProduto"));
		columnNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
		columnCodigo.setCellValueFactory(new PropertyValueFactory<>("codigo"));
		columnMarca.setCellValueFactory(new PropertyValueFactory<>("marca"));
		columnDescricao.setCellValueFactory(new PropertyValueFactory<>("descricao"));
		columnPrecoUn.setCellValueFactory(new PropertyValueFactory<>("precoUnitario"));
		columnEstoqueAtual.setCellValueFactory(new PropertyValueFactory<>("estoqueDisp"));

		tableRelatorioProduto.setItems(arrayProduto);
		tableRelatorioProduto.refresh();
	}

	@FXML
	void OnbtnSair(ActionEvent event) {
		Main.changeScreen("login");
	}

	@FXML
	void OnbtnVendas(ActionEvent event) {
		Main.changeScreen("vendas");
	}

	@FXML
	void OnbtnVoltar(ActionEvent event) {
		Main.changeScreen("main");
	}
}
