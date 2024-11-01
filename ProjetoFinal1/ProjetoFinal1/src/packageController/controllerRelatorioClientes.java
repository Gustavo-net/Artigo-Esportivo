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
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
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
    private Button btnExcluir;

    @FXML
    private ImageView lupaPesquisa;
    
    @FXML
    private TableColumn<Clientes, String> columnNome;
    @FXML
    private TableColumn<Clientes, String> columnCPF;
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

    @SuppressWarnings("exports")
    public static Clientes clienteEditor = new Clientes();

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        carregarTableCliente();
    }

    private void carregarTableCliente() {
        arrayCliente = FXCollections.observableArrayList(ClienteDAO.read());
        tableRelatorioCliente.setItems(arrayCliente);
        columnNome.setCellValueFactory(new PropertyValueFactory<>("nomeCliente"));
        columnCPF.setCellValueFactory(new PropertyValueFactory<>("cpf"));
        columnEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        columnTelefone.setCellValueFactory(new PropertyValueFactory<>("telefone"));
    }

    @FXML
    void OnbtnEditar(ActionEvent event) {
        if (tableRelatorioCliente.getSelectionModel().getSelectedIndex() == -1) {
            showAlert("Selecione um Cliente para Editar Primeiro!");
        } else {
            int i = tableRelatorioCliente.getSelectionModel().getSelectedIndex();
            clienteEditor = tableRelatorioCliente.getItems().get(i);
            
            try {
                Main.TelaCcadastroClientes();
                carregarTableCliente(); 
            } catch (IOException e) {
                e.printStackTrace();
                showAlert("Erro ao abrir a tela de edição!");
            }
        }
    }

    @FXML
    void OnbtnInserir(ActionEvent event) throws IOException {
        clienteEditor = null;
        Main.TelaCcadastroClientes();
    }

    @FXML
    void OnPesquisarImagem(MouseEvent event) {
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

    @FXML
    void OnbtnExcluir(ActionEvent event) {
        int i = tableRelatorioCliente.getSelectionModel().getSelectedIndex();
        if (i == -1) {
            showAlert("Selecione um Cliente para Excluir Primeiro!");
            return;
        }

        Clientes clienteSelecionado = tableRelatorioCliente.getItems().get(i);
        
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmação de Exclusão");
        alert.setHeaderText("Você tem certeza que deseja excluir este cliente?");
        alert.setContentText("Cliente: " + clienteSelecionado.getNomeCliente());

        alert.showAndWait().ifPresent(response -> {
            if (response == ButtonType.OK) {
                try {
                    clienteDAO.delete(clienteSelecionado.getIdCliente());
                    carregarTableCliente(); 
                    showAlert("Cliente excluído com sucesso!");
                } catch (Exception e) {
                    showAlert("Erro ao excluir o cliente: " + e.getMessage());
                }
            }
        });
    }


    private void showAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
