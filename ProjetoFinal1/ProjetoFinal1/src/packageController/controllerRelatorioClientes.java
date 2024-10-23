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
    private TableView<Clientes> tableRelatorioCliente;

    @FXML
    private ComboBox<String> boxFiltrar;

    @FXML
    private Button btnCadastros;
    @FXML
    private Button btnFornecedores;
    @FXML
    private Button btnFuncionarios;
    @FXML
    private Button btnProdutos;
    @FXML
    private Button btnVendas;
    @FXML
    private Button btnVoltar;
    @FXML
    private Button btnSair;
    @FXML
    private Button btnEditar;
    @FXML
    private Button btnInserir;
    @FXML
    private Button btnPesquisar;

    @FXML
    private TableColumn<Clientes, String> columnID;
    @FXML
    private TableColumn<Clientes, String> columnNome;
    @FXML
    private TableColumn<Clientes, String> columnCPF;
    @FXML
    private TableColumn<Clientes, String> columnCEP;
    @FXML
    private TableColumn<Clientes, String> columnEmail;
    @FXML
    private TableColumn<Clientes, String> columnTelefone;
    @FXML
    private TableColumn<Clientes, String> columnDataNasc;

    @FXML
    private TextField txtPesquisar;

    private ObservableList<Clientes> arrayCliente;
    private ClienteDAO clienteDAO = new ClienteDAO();

    public static Clientes clienteEditor = new Clientes();

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        carregarTableCliente();
    }

    private void carregarTableCliente() {
        arrayCliente = FXCollections.observableArrayList(ClienteDAO.read());
        tableRelatorioCliente.setItems(arrayCliente);
        atualizarTabela(arrayCliente);
    }

    private void atualizarTabela(ObservableList<Clientes> observableList) {
        columnID.setCellValueFactory(new PropertyValueFactory<>("idCliente"));
        columnNome.setCellValueFactory(new PropertyValueFactory<>("nomeCliente"));
        columnCPF.setCellValueFactory(new PropertyValueFactory<>("cpf"));
        columnCEP.setCellValueFactory(new PropertyValueFactory<>("id_Endereco"));
        columnEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        columnTelefone.setCellValueFactory(new PropertyValueFactory<>("telefone"));
        columnDataNasc.setCellValueFactory(new PropertyValueFactory<>("dataNasc"));
    }

    @FXML
    void OnbtnEditar(ActionEvent event) {
        if (tableRelatorioCliente.getSelectionModel().getSelectedIndex() == -1) {
            showAlert("Selecione um Cliente para Editar Primeiro!");
        } else {
            int i = tableRelatorioCliente.getSelectionModel().getSelectedIndex();
            clienteEditor = tableRelatorioCliente.getItems().get(i);
            //Main.changeScreen("cadastroCliente"); 
        }
    }

    @FXML
    void OnbtnInserir(ActionEvent event) {
        clienteEditor = null;
       // Main.changeScreen("cadastroCliente"); 
    }

    @FXML
    void OnbtnPesquisar(ActionEvent event) {
        String pesquisa = txtPesquisar.getText().trim();
        if (pesquisa.isEmpty()) {
            carregarTableCliente();
        } else {
            arrayCliente = FXCollections.observableArrayList(ClienteDAO.search(pesquisa));
            tableRelatorioCliente.setItems(arrayCliente);
            tableRelatorioCliente.refresh();
        }
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

    private void showAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
