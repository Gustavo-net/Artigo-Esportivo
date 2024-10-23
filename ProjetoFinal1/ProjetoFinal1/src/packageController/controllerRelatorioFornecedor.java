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
import packageModel.Fornecedores;
import packageModel.Funcionarios;
import package_controle.FornecedoresDAO;

public class controllerRelatorioFornecedor implements Initializable {

    @FXML
    private TableColumn<Funcionarios, String> columnCNPJ;

    @FXML
    private TableColumn<Funcionarios, String> columnID;

    @FXML
    private TableColumn<Funcionarios, String> columnNome;

    @FXML
    private TableColumn<Funcionarios, String> columncep;

    @FXML
    private TableColumn<Funcionarios, String> columnemail;

    @FXML
    private TableColumn<Funcionarios, String> columntelefone;

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
	private Button btnFuncionarios;

	@FXML
	private Button btnInserir;

	@FXML
	private TextField labelpesquisar;

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

	private ObservableList<Fornecedores> arrayFornecedores;
	private FornecedoresDAO fornecedorDAO = new FornecedoresDAO();
	
	

	public void carregarTableFornecedores() {
		arrayFornecedores = FXCollections.observableArrayList(fornecedorDAO.read());

		columnID.setCellValueFactory(new PropertyValueFactory<>("idFornecedor"));
		columnNome.setCellValueFactory(new PropertyValueFactory<>("nomeFornecedor"));
		columnCNPJ.setCellValueFactory(new PropertyValueFactory<>("cnpj"));
		columnemail.setCellValueFactory(new PropertyValueFactory<>("email"));
		columntelefone.setCellValueFactory(new PropertyValueFactory<>("telefone"));
		columncep.setCellValueFactory(new PropertyValueFactory<>("id_Endereço"));

		tableFornecedores.setItems(arrayFornecedores);
	}

	@FXML
	void OnbntClientes(ActionEvent event) {
		Main.changeScreen("clientes");
	}

	@FXML
	void OnbtnCadastros(ActionEvent event) {
		Main.changeScreen("cadastros");
	}

	@FXML
	void OnbtnEditar(ActionEvent event) {
		//Main.changeScreen("");
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
		Main.changeScreen("Cadastro de Fornecedores - Artigos Esportivos");
	}
	
    public static Fornecedores FornecedoresEditor = new Fornecedores();


	@FXML
	void OnbtnPesquisar(ActionEvent event) {
		
		arrayFornecedores = FXCollections.observableArrayList(FornecedoresDAO.search(labelpesquisar.getText()));


		columnID.setCellValueFactory(new PropertyValueFactory<>("idFornecedor"));
		columnNome.setCellValueFactory(new PropertyValueFactory<>("nomeFornecedor"));
		columnCNPJ.setCellValueFactory(new PropertyValueFactory<>("cnpj"));
		columnemail.setCellValueFactory(new PropertyValueFactory<>("email"));
		columntelefone.setCellValueFactory(new PropertyValueFactory<>("telefone"));
		columncep.setCellValueFactory(new PropertyValueFactory<>("id_Endereço"));

		tableFornecedores.setItems(arrayFornecedores);
	}

	@FXML
	void OnbtnProdutos(ActionEvent event) {
		Main.changeScreen("produtos");
	}

	@FXML
	void OnbtnSair(ActionEvent event) {
		Main.changeScreen("login");
	}

	@FXML
	void OnbtnVendas(ActionEvent event) {
		// Implementar vendas
	}

	@FXML
	void OnbtnVoltar(ActionEvent event) {
		// Implementar ação de voltar
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		carregarTableFornecedores();
	}
}
