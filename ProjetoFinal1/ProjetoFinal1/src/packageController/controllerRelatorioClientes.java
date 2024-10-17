package packageController;

import java.net.URL;
import java.util.ResourceBundle;

import application.Main;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import packageModel.Clientes;
import package_controle.ClienteDAO;

public class controllerRelatorioClientes implements Initializable {
	@FXML
	private ComboBox<Clientes> boxFiltrar;

	@FXML
	private Button btnEditar;

	@FXML
	private Button btnInserir;

	@FXML
	private Button btnPesquisar;

	@FXML
	private Button btnVoltar;

	@FXML
	private TableColumn<Clientes, String> columnCEP;

	@FXML
	private TableColumn<Clientes, String> columnCPF;

	@FXML
	private TableColumn<Clientes, String> columnDataNasc;

	@FXML
	private TableColumn<Clientes, String> columnEmail;

	@FXML
	private TableColumn<Clientes, String> columnID;

	@FXML
	private TableColumn<Clientes, String> columnNome;

	@FXML
	private TableColumn<Clientes, String> columnTelefone;

	@FXML
	private TextField txtPesquisar;

	@FXML
	private TableView<Clientes> tableRelatorioCliente;

	private ObservableList<Clientes> arrayCliente;
	private ClienteDAO clienteDAO = new ClienteDAO();

	public static Clientes clienteEditor = new Clientes();

	public void carregarTableCliente() {
		arrayCliente = FXCollections.observableArrayList(ClienteDAO.read());

		columnID.setCellValueFactory(new PropertyValueFactory<>("idCliente"));
		columnNome.setCellValueFactory(new PropertyValueFactory<>("nomeCliente"));
		columnCPF.setCellValueFactory(new PropertyValueFactory<>("cpf"));
		columnCEP.setCellValueFactory(new PropertyValueFactory<>("id_Endereco"));
		columnEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
		columnTelefone.setCellValueFactory(new PropertyValueFactory<>("telefone"));
		columnDataNasc.setCellValueFactory(new PropertyValueFactory<>("dataNasc"));

		tableRelatorioCliente.setItems(arrayCliente);
	}
	
	public static Clientes clienteEditar = new Clientes();
	@FXML
    void OnbtnEditar(ActionEvent event) {
		if(tableRelatorioCliente.getSelectionModel().getSelectedIndex() == -1) {
    		Alert mensagemDeErro = new Alert(Alert.AlertType.INFORMATION);
    		mensagemDeErro.setContentText("Selecione um Cliente para Editar Primeiro!");
    		mensagemDeErro.show();
    	}else {
    		int i = tableRelatorioCliente.getSelectionModel().getSelectedIndex();
    		clienteEditar = tableRelatorioCliente.getItems().get(i);
//    		Main.TelaCadastroClientes();
    	}
    	carregarTableCliente();
    }

    @FXML
    void OnbtnInserir(ActionEvent event) {
    	clienteEditar = null;
//    	Main.TelaCadastroClientes();
    	carregarTableCliente();
    }

    @FXML
    void OnbtnPesquisar(ActionEvent event) {
    	arrayCliente = FXCollections.observableArrayList(clienteDAO.search(txtPesquisar.getText()));
    	
    	columnID.setCellValueFactory(new PropertyValueFactory<>("idCliente"));
		columnNome.setCellValueFactory(new PropertyValueFactory<>("nomeCliente"));
		columnCPF.setCellValueFactory(new PropertyValueFactory<>("cpf"));
		columnCEP.setCellValueFactory(new PropertyValueFactory<>("id_Endereco"));
		columnEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
		columnTelefone.setCellValueFactory(new PropertyValueFactory<>("telefone"));
		columnDataNasc.setCellValueFactory(new PropertyValueFactory<>("dataNasc"));
		
		tableRelatorioCliente.setItems(arrayCliente);
		tableRelatorioCliente.refresh();

    }

    @FXML
    void OnbtnSair(ActionEvent event) {
    	Main.changeScreen("login");
    }

    @FXML
    void OnbtnVoltar(ActionEvent event) {
    	Main.changeScreen("main");
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
    void OnbtnFuncionarios(ActionEvent event) {
    	Main.changeScreen("funcionarios");
    }
    
    @FXML
    void OnbtnProdutos(ActionEvent event) {
    	Main.changeScreen("produtos");
    }
    
    @FXML
    void OnbtnVendas(ActionEvent event) {
    	Main.changeScreen("vendas");
    }

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// Implementação da inicialização, se necessário
		carregarTableCliente();
	}

}
