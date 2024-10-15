package packageController;

import application.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import packageModel.Produtos;

public class controllerRelatorioProduto {

	@FXML
	private TableView<Produtos> ViewProdutos;

	@FXML
	private Button btnCadastros;

	@FXML
	private Button btnClientes;

	@FXML
	private Button btnEditar;

	@FXML
	private Button btnExcluir;

	@FXML
	private Button btnFornecedores;

	@FXML
	private Button btnFuncionários;

	@FXML
	private Button btnPesquisar;

	@FXML
	private Button btnSalvar;

	@FXML
	private Button btnVendas;

	@FXML
	private TableColumn<Produtos, String> columnCodigo;

	@FXML
	private TableColumn<Produtos, String> columnDescrição;

	@FXML
	private TableColumn<Produtos, String> columnEstoqueDisp;

	@FXML
	private TableColumn<Produtos, String> columnEstoqueMax;

	@FXML
	private TableColumn<Produtos, String> columnEstoqueMin;

	@FXML
	private TableColumn<Produtos, String> columnMarca;

	@FXML
	private TableColumn<Produtos, String> columnNome;

	@FXML
	private TableColumn<Produtos, String> columnPreçoUn;

	@FXML
	private TableColumn<Produtos, String> columnidProduto;

	@FXML
	void OnbtnCadastros(ActionEvent event) {
		Main.changeScreen("Cadastros");

	}

	@FXML
	void OnbtnFornecedores(ActionEvent event) {
		Main.changeScreen("Fornecedores");

	}

	@FXML
	void OnbtnFuncionários(ActionEvent event) {
		Main.changeScreen("Funcionarios");

	}

	@FXML
	void OnbtnVendas(ActionEvent event) {
		Main.changeScreen("Vendas");

	}

	@FXML
	void OnbtnPesquisar(ActionEvent event) {

	}

	@FXML
	void btnActionEditar(ActionEvent event) {

	}

	@FXML
	void btnActionExcluir(ActionEvent event) {

	}

	@FXML
	void btnActionSalvar(ActionEvent event) {

	}

}
