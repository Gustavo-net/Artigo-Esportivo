package packageController;

import application.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import packageModel.Produtos;

public class controllerMain {

	@FXML
	private TableView<Produtos> ViewProdutos;

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
	private TableColumn<Produtos, String> columnCategoria;

	@FXML
	private TableColumn<Produtos, String> columnCodigo;

	@FXML
	private TableColumn<Produtos, String> columnID;

	@FXML
	private TableColumn<Produtos, String> columnMarca;

	@FXML
	private TableColumn<Produtos, String> columnNome;

	@FXML
	private TableColumn<Produtos, String> columnQuantidade;

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
