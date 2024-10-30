package packageController;

import java.io.IOException;
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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import packageModel.Fornecedores;
import package_controle.FornecedoresDAO;

public class controllerRelatorioFornecedor implements Initializable {

    @FXML
    private TableView<Fornecedores> tableFornecedores;

    @FXML
    private TableColumn<Fornecedores, String> columnID;

    @FXML
    private TableColumn<Fornecedores, String> columnNome;

    @FXML
    private TableColumn<Fornecedores, String> columnCNPJ;

    @FXML
    private TableColumn<Fornecedores, String> columnEmail;

    @FXML
    private TableColumn<Fornecedores, String> columnTelefone;

    @FXML
    private TableColumn<Fornecedores, String> columnEndereco;

    @FXML
    private Button btnCadastros;
    @FXML
    private Button btnClientes;
    @FXML
    private Button btnSair;
    @FXML
    private Button btnEditar;
    @FXML
    private Button btnInserir;
    @FXML
    private Button btnPesquisar;
    @FXML
    private Button btnProdutos;
    @FXML
    private Button btnVendas;
    @FXML
    private Button btnVoltar;

    @FXML
    private TextField txtPesquisar;

    private ObservableList<Fornecedores> arrayFornecedores;
    private FornecedoresDAO fornecedorDAO = new FornecedoresDAO();

    @SuppressWarnings("exports")
    public static Fornecedores FornecedoresEditor = new Fornecedores();

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        carregarTableFornecedores();
    }

    private void carregarTableFornecedores() {
        arrayFornecedores = FXCollections.observableArrayList(fornecedorDAO.read());
        tableFornecedores.setItems(arrayFornecedores);
        atualizarTabela();
    }

    private void atualizarTabela() {
        columnID.setCellValueFactory(new PropertyValueFactory<>("idFornecedor"));
        columnNome.setCellValueFactory(new PropertyValueFactory<>("nomeFornecedor"));
        columnCNPJ.setCellValueFactory(new PropertyValueFactory<>("cnpj"));
        columnEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        columnTelefone.setCellValueFactory(new PropertyValueFactory<>("telefone"));
    //    columnEndereco.setCellValueFactory(new PropertyValueFactory<>("id_Endereço"));
    }

    @FXML
    void OnbtnInserir(ActionEvent event) throws IOException {
        FornecedoresEditor = null;
        Main.TelaCadastroFornecedores();
    }

    @FXML
    void OnbtnEditar(ActionEvent event) {
        if (tableFornecedores.getSelectionModel().getSelectedIndex() == -1) {
            showAlert("Selecione um Fornecedor para Editar Primeiro!");
        } else {
            int i = tableFornecedores.getSelectionModel().getSelectedIndex();
            FornecedoresEditor = tableFornecedores.getItems().get(i);
            
            try {
                Main.TelaCadastroFornecedores();
            } catch (IOException e) {
                e.printStackTrace();
                showAlert("Erro ao abrir a tela de edição!");
            }
        }
    }

    @FXML
    void OnbtnPesquisar(ActionEvent event) {
        String pesquisa = txtPesquisar.getText().trim();
        if (pesquisa.isEmpty()) {
            carregarTableFornecedores();
        } else {
            arrayFornecedores = FXCollections.observableArrayList(fornecedorDAO.search(pesquisa));
            tableFornecedores.setItems(arrayFornecedores);
            tableFornecedores.refresh();
        }
    }

    @FXML
    void OnbtnClientes(ActionEvent event) {
        Main.changeScreen("clientes");
    }
    
    @FXML
    void OnbtnFuncionarios(ActionEvent event) {
        Main.changeScreen("funcionarios");
    }
    
    @FXML
    void OnbtnSair(ActionEvent event) {
        Main.changeScreen("login");
    }

    @FXML
    void OnbtnProdutos(ActionEvent event) {
        Main.changeScreen("produtos");
    }

    @FXML
    void OnbtnVendas(ActionEvent event) {
        // Implementar vendas
    }

    @FXML
    void OnbtnVoltar(ActionEvent event) {
        Main.changeScreen("main");
    }

    private void showAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
