package packageController;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import packageModel.Produtos;

public class controllerRelatorioProduto {
	@FXML
	private TableView<Produtos> ViewProdutos;
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

	@FXML
	void OnbtnCadastros(ActionEvent event) {
	}

	@FXML
	void OnbtnEditar(ActionEvent event) {
	}

	@FXML
	void OnbtnFornecedores(ActionEvent event) {
	}

	@FXML
	void OnbtnFuncionários(ActionEvent event) {
	}

	@FXML
	void OnbtnInserir(ActionEvent event) {
	}

	@FXML
	void OnbtnPesquisar(ActionEvent event) {
	}

	@FXML
	void OnbtnVendas(ActionEvent event) {
	}

	@FXML
	void OnbtnVoltar(ActionEvent event) {
	}
}