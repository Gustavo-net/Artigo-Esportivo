package packageController;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;
import packageModel.Clientes;
import package_controle.ClienteDAO;

public class controllerClientes implements Initializable {

	@FXML
	private Button btnVoltar;

	@FXML
	private Button btnEditar;

	@FXML
	private Text idButtonVendedor;

	@FXML
	private Text idButtonFornecedor;

	@FXML
	private Text idButtonProduto;

	@FXML
	private Button btnPesquisar;

	@FXML
	private TextField labelPesquisar;

	@FXML
	private Text btnButton;

	@FXML
	private Button btnDeletar;

	@FXML
	private TableColumn<Clientes, String> columncpf;

	@FXML
	private TableColumn<Clientes, String> columnDataNasc;

	@FXML
	private TableColumn<Clientes, String> columnDataPrimComp;

	@FXML
	private TableColumn<Clientes, String> columnemail;

	@FXML
	private TableColumn<Clientes, String> columnid_Endereco;

	@FXML
	private TableColumn<Clientes, String> columnID;

	@FXML
	private TableColumn<Clientes, String> columnNome;

	@FXML
	private TableColumn<Clientes, String> columntelefone;
	
	@FXML
	private TableColumn<Clientes, String> columnprogramaFidelidade;
	
	@FXML
	private TableColumn<Clientes, String> columnpontosFidelidade;

	@FXML
	private TableColumn<Clientes, String> columnTipoJuridico;
	@FXML
	private TextField txtPesquisar;

	@FXML
	private TableView<Clientes> tableCliente;

	private ObservableList<Clientes> arrayCliente;
	private ClienteDAO clienteDAO = new ClienteDAO();

	public static Clientes clienteEditor = new Clientes();

	public void carregarTableCliente() {
		arrayCliente = FXCollections.observableArrayList(ClienteDAO.read());

		columnID.setCellValueFactory(new PropertyValueFactory<>("idCliente"));
		columncpf.setCellValueFactory(new PropertyValueFactory<>("cpf"));
		columnNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
		columnDataNasc.setCellValueFactory(new PropertyValueFactory<>("email"));
		columnid_Endereco.setCellValueFactory(new PropertyValueFactory<>("Telefone"));
		columnemail.setCellValueFactory(new PropertyValueFactory<>("DataNasc"));
		columntelefone.setCellValueFactory(new PropertyValueFactory<>("DataPrimCom"));
		columnprogramaFidelidade.setCellValueFactory(new PropertyValueFactory<>("programaFidelidade"));
		columnpontosFidelidade.setCellValueFactory(new PropertyValueFactory<>("pontosFidelidade"));

		tableCliente.setItems(arrayCliente);
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// Implementação da inicialização, se necessáriopan
	}

}
