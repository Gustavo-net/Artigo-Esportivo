package packageController;

//import java.io.IOException;
//import java.net.URL;
//import java.util.ResourceBundle;
//
//import application.Main;
//import javafx.collections.FXCollections;
//import javafx.collections.ObservableList;
//import javafx.event.ActionEvent;
//import javafx.fxml.FXML;
//import javafx.fxml.Initializable;
//import javafx.scene.control.Alert;
//import javafx.scene.control.Button;
//import javafx.scene.control.ButtonType;
//import javafx.scene.control.ComboBox;
//import javafx.scene.control.TableColumn;
//import javafx.scene.control.TableView;
//import javafx.scene.control.TextField;
//import javafx.scene.control.cell.PropertyValueFactory;
//import packageModel.Produtos;

public class ControllerRelatorioVendas  {
//
//    @FXML
//    private Button btnCadastros;
//
//    @FXML
//    private Button btnClientes;
//
//    @FXML
//    private Button btnSair;
//
//    @FXML
//    private Button btnExcluir;
//
//    @FXML
//    private ComboBox<Vendas> boxFiltrar;
//
//    @FXML
//    private Button btnEditar;
//
//    @FXML
//    private Button btnInserir;
//
//    @FXML
//    private Button btnPesquisar;
//
//    @FXML
//    private Button btnProdutos;
//
//    @FXML
//    private Button btnFuncionarios;
//
//    @FXML
//    private Button btnVoltar;
//
//    @FXML
//    private TableColumn<Vendas, String> columnCPF_Cliente;
//
//    @FXML
//    private TableColumn<Vendas, String> columnCPF_Funcionario;
//
//    @FXML
//    private TableColumn<Vendas, String> columnCanalVendas;
//
//    @FXML
//    private TableColumn<Vendas, String> columnCodigoProduto;
//
//    @FXML
//    private TableColumn<Vendas, String> columnDataVenda;
//
//    @FXML
//    private TableColumn<Vendas, String> columnDesconto;
//
//    @FXML
//    private TableColumn<Vendas, String> columnQuantidade;
//
//    @FXML
//    private TableColumn<Vendas, String> columnStatusVenda;
//
//    @FXML
//    private TableColumn<Vendas, String> columnSubTotal;
//
//    @FXML
//    private TableColumn<Vendas, String> columnValorTotal;
//
//    @FXML
//    private TableView<Vendas> tableRelatorioVenda;
//
//
//    @FXML
//    private TextField labelPesquisar;
//
//   
//
//    private ObservableList<Vendas> arrayVendas;
//    private VendaDAO vendaDAO = new VendaDAO();
//    public static Vendas vendaEditor = new Vendas();
//    public void carregarTableVendas() {
//        
//    }
//
//    @FXML
//    void OnbtnClientes(ActionEvent event) {
//        Main.changeScreen("clientes");
//    }
//    @FXML
//    void OnbtnFuncionários(ActionEvent event) {
//        Main.changeScreen("funcionarios");
//    }
//    @FXML
//    void OnbtnFornecedores(ActionEvent event) {
//        Main.changeScreen("fornecedores");
//    }
//
//    @FXML
//    void OnbtnCadastros(ActionEvent event) {
//        Main.changeScreen("cadastros");
//    }
//
//    @FXML
//    void OnbtnSair(ActionEvent event) {
//        Main.changeScreen("login");
//    }
//    
//    private void carregartableRelatorioVenda() {
//        arrayVendas = FXCollections.observableArrayList(vendaDAO.read());
//        atualizarTabela(arrayVendas);
//    }
//    private void atualizarTabela(ObservableList<Vendas> vendas) {
//    	tableRelatorioVenda.setItems(vendas);
//    	columnDataVenda.setCellValueFactory(new PropertyValueFactory<>("DataVenda"));
//    	columnCodigoProduto.setCellValueFactory(new PropertyValueFactory<>("CodigoProduto"));
//    	columnQuantidade.setCellValueFactory(new PropertyValueFactory<>("Quantidade"));
//    	columnDesconto.setCellValueFactory(new PropertyValueFactory<>("Desconto"));
//    	columnSubTotal.setCellValueFactory(new PropertyValueFactory<>("SubTotal"));
//    	columnValorTotal.setCellValueFactory(new PropertyValueFactory<>("ValorTotal"));
//    	columnStatusVenda.setCellValueFactory(new PropertyValueFactory<>("StatusVenda"));
//    	columnCanalVendas.setCellValueFactory(new PropertyValueFactory<>("CanalVendas"));
//    	columnCPF_Cliente.setCellValueFactory(new PropertyValueFactory<>("CPF_Cliente"));
//    	columnCPF_Funcionario.setCellValueFactory(new PropertyValueFactory<>("CPF_Funcionario"));
//    }
//
//    @FXML
//    void OnbtnExcluir(ActionEvent event) {
//    	int i = tableRelatorioVenda.getSelectionModel().getSelectedIndex();
//        if (i == -1) {
//            showAlert("Selecione um Produto para Excluir Primeiro!");
//            return;
//        }
//
//        Vendas vendaSelecionado = tableRelatorioVenda.getItems().get(i);
//        
//        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
//        alert.setTitle("Confirmação de Exclusão");
//        alert.setHeaderText("Você tem certeza que deseja excluir este produto?");
//        alert.setContentText("Produto: " + vendaSelecionado.getDataVenda());
//
//        alert.showAndWait().ifPresent(response -> {
//            if (response == ButtonType.OK) {
//                try {
//                    vendaDAO.delete(vendaSelecionado.getIdVenda());
//                    carregartableRelatorioVenda();
//                    showAlert("Produto excluído com sucesso!");
//                } catch (Exception e) {
//                    showAlert("Erro ao excluir o produto: " + e.getMessage());
//                }
//            }
//        });
//    }
//    private void showAlert(String message) {
//        Alert alert = new Alert(Alert.AlertType.INFORMATION);
//        alert.setContentText(message);
//        alert.showAndWait();
//    }
//    
//    @FXML
//    void OnbtnFuncionarios(ActionEvent event) {
//        Main.changeScreen("funcionarios");
//    }
//
//    @FXML
//    void OnbtnEditar(ActionEvent event) throws IOException {
//    	if (tableRelatorioVenda.getSelectionModel().getSelectedIndex() == -1) {
//            showAlert("Selecione um Produto para Editar Primeiro!");
//        } else {
//            int i = tableRelatorioVenda.getSelectionModel().getSelectedIndex();
//            vendaEditor = tableRelatorioVenda.getItems().get(i);
//            Main.TelaCadastroVenda();
//        }
//    }
//
//    @FXML
//    void OnbtnInserir(ActionEvent event) throws IOException {
//    	vendaEditor = null;
//        Main.TelaCadastroVenda();
//    }
//
//    @FXML
//    void OnbtnPesquisar(ActionEvent event) {
//    }
//
//    @FXML
//    void OnbtnProdutos(ActionEvent event) {
//        Main.changeScreen("produtos");
//    }
//
//    @FXML
//    void OnbtnVoltar(ActionEvent event) {
//    }
//
//    @Override
//    public void initialize(URL arg0, ResourceBundle arg1) {
//        carregarTableVendas();
//    }
}
