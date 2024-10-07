package package_Controller;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import packageModel.Clientes;
import package_controle.ClienteDAO;

public class ControllerClientes implements Initializable {

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
    private Text btnButton; // Corrigido o nome

    @FXML
    private Button btnDeletar;

    @FXML
    private TableColumn<Clientes, String> columnCPF_CNPJ;

    @FXML
    private TableColumn<Clientes, String> columnDataNasc;

    @FXML
    private TableColumn<Clientes, String> columnDataPrimComp;

    @FXML
    private TableColumn<Clientes, String> columnEmail;

    @FXML
    private TableColumn<Clientes, String> columnEndereco; // Corrigido para evitar acentuação

    @FXML
    private TableColumn<Clientes, String> columnID;

    @FXML
    private TableColumn<Clientes, String> columnNome;

    @FXML
    private TableColumn<Clientes, String> columnTelefone;

    @FXML
    private TableColumn<Clientes, String> columnTipoJuridico; // Corrigido para evitar acentuação

    @FXML
    private TextField txtPesquisar;

    @FXML
    private TableView<Clientes> tableCliente;

    private ObservableList<Clientes> arrayCliente;
    private ClienteDAO clienteDAO = new ClienteDAO();

    public static Clientes clienteEditor = new Clientes();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // Implementação da inicialização, se necessário
    }
}
