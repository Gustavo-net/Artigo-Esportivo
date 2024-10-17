package packageController;

import java.net.URL;
import java.util.ResourceBundle;

import application.Main;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import packageModel.Vendas;
import package_controle.VendaDAO;

public class controllerRelatorioVendas implements Initializable {

	@FXML
	private Button btnCadastros;

	@FXML
	private Button btnClientes;

	@FXML
	private Button btnSair;

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
	private TableColumn<Vendas, String> columnID;

	@FXML
	private TableColumn<Vendas, String> columnQuantidade;

	@FXML
	private TableColumn<Vendas, String> columnPreçoUn;

	@FXML
	private TableColumn<Vendas, String> columnTotal;

	@FXML
	private TableColumn<Vendas, String> columnDesconto;

	@FXML
	private TableColumn<Vendas, String> columnDescrição;

	@FXML
	private TableColumn<Vendas, String> columnMarca;

	@FXML
	private TableColumn<Vendas, String> columnCodigo;

	@FXML
	private TextField labelpesquisar;

	@FXML
	private TableView<Vendas> tableVendas;

	private ObservableList<Vendas> arrayVendas;
	private VendaDAO vendaDAO = new VendaDAO();

	public void carregarTableVendas() {
		arrayVendas = FXCollections.observableArrayList(vendaDAO.read());

		columnID.setCellValueFactory(new PropertyValueFactory<>("idVenda"));
		columnQuantidade.setCellValueFactory(new PropertyValueFactory<>("quantidade"));
		columnPreçoUn.setCellValueFactory(new PropertyValueFactory<>("precoUnitario"));
		columnTotal.setCellValueFactory(new PropertyValueFactory<>("total"));
		columnDesconto.setCellValueFactory(new PropertyValueFactory<>("desconto"));
		columnDescrição.setCellValueFactory(new PropertyValueFactory<>("descricao"));
		columnMarca.setCellValueFactory(new PropertyValueFactory<>("marca"));
		columnCodigo.setCellValueFactory(new PropertyValueFactory<>("codigo"));

		tableVendas.setItems(arrayVendas);
	}

	@FXML
	void OnbtnClientes(ActionEvent event) {
		Main.changeScreen("clientes");
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
	void OnbtnEditar(ActionEvent event) {
		// Implementar lógica de edição
	}

	@FXML
	void OnbtnInserir(ActionEvent event) {
		// Implementar lógica de inserção
	}

	@FXML
	void OnbtnPesquisar(ActionEvent event) {

		columnID.setCellValueFactory(new PropertyValueFactory<>("idVenda"));
		columnNome.setCellValueFactory(new PropertyValueFactory<>("nomeProduto"));
		columnQuantidade.setCellValueFactory(new PropertyValueFactory<>("quantidade"));
		columnPreçoUn.setCellValueFactory(new PropertyValueFactory<>("precoUnitario"));
		columnTotal.setCellValueFactory(new PropertyValueFactory<>("total"));
		columnDesconto.setCellValueFactory(new PropertyValueFactory<>("desconto"));
		columnDescrição.setCellValueFactory(new PropertyValueFactory<>("descricao"));
		columnMarca.setCellValueFactory(new PropertyValueFactory<>("marca"));
		columnCodigo.setCellValueFactory(new PropertyValueFactory<>("codigo"));

		tableVendas.setItems(arrayVendas);
	}

	@FXML
	void OnbtnProdutos(ActionEvent event) {
		Main.changeScreen("produtos");
	}

	@FXML
	void OnBtnSair(ActionEvent event) {
		Main.changeScreen("login");
	}

	@FXML
	void OnbtnVoltar(ActionEvent event) {
		// Implementar lógica de voltar
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		carregarTableVendas();
	}
}
