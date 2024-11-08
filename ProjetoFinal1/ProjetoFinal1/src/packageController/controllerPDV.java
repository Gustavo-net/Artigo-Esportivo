package packageController;

import java.math.BigDecimal;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import packageConnection.ConnectionDatabase;
import packageModel.ItemVenda;
import packageModel.Produtos;
import packageModel.Venda;
import package_controle.VendasDAO;

public class controllerPDV implements Initializable{
	@FXML
    private Button btnCancelar;

    @FXML
    private Button btnRegistrarVenda;

    @FXML
    private TextField clienteField;

    @FXML
    private TextField codigoProdutoField;

    @FXML
    private TableColumn<ItemVenda, String> colCodigo;

    @FXML
    private TableColumn<ItemVenda, String> colProduto;

    @FXML
    private TableColumn<ItemVenda, Integer> colQuantidade;

    @FXML
    private TableColumn<ItemVenda, BigDecimal> colSubtotal;
    @FXML
    private ComboBox<String> comboxFormaPagamento;

    @FXML
    private ComboBox<String> comboxParcelar;

    @FXML
    private DatePicker dataVendaPicker;

    @FXML
    private TextField funcionarioField;

    @FXML
    private Label labelValorTotal;

    @FXML
    private TextField quantidadeField;

    @FXML
    private TableView<ItemVenda> tableView;

    private VendasDAO vendasDAO;
    private BigDecimal totalVenda = BigDecimal.ZERO; 

    public void ControllerPDV() {
        this.vendasDAO = new VendasDAO();
    }

    @FXML
    void OnbtnAdicionarProduto(ActionEvent event) {
        String codigoProduto = codigoProdutoField.getText();
        String quantidadeStr = quantidadeField.getText();

        if (codigoProduto.isEmpty() || quantidadeStr.isEmpty()) {
            showAlert("Erro", "Código do produto e quantidade são obrigatórios!");
            return;
        }

        int quantidade;
        try {
            quantidade = Integer.parseInt(quantidadeStr);
        } catch (NumberFormatException e) {
            showAlert("Erro", "Quantidade inválida!");
            return;
        }

        Produtos produto = buscarProdutoPorCodigo(codigoProduto);
        if (produto == null) {
            showAlert("Erro", "Produto não encontrado!");
            return;
        }

        BigDecimal precoUnitario = produto.getPrecoUnitario();
        BigDecimal subtotal = precoUnitario.multiply(BigDecimal.valueOf(quantidade));

        ItemVenda item = new ItemVenda();
        item.setCodigoProduto(produto.getCodigo());
        item.setQuantidade(quantidade);
        item.setPrecoUnitario(precoUnitario);
        item.setSubtotal(subtotal);

        tableView.getItems().add(item);
        totalVenda = totalVenda.add(subtotal); 
        labelValorTotal.setText(totalVenda.toString()); 
    }

    @FXML
    void OnbtnCancelar(ActionEvent event) {
        clienteField.clear();
        funcionarioField.clear();
        codigoProdutoField.clear();
        quantidadeField.clear();
        comboxFormaPagamento.getSelectionModel().clearSelection();
        comboxParcelar.getSelectionModel().clearSelection();
        dataVendaPicker.setValue(null);
        labelValorTotal.setText("0,00");

        tableView.getItems().clear();
        totalVenda = BigDecimal.ZERO;
    }

    @FXML
    void OnbtnRegistrarVenda(ActionEvent event) {
        String cpfCliente = clienteField.getText();
        String cpfFuncionario = funcionarioField.getText();
        String formaPagamento = comboxFormaPagamento.getValue();
        String parcela = comboxParcelar.getValue();
        String dataVenda = dataVendaPicker.getValue().toString();

        if (cpfCliente.isEmpty() || cpfFuncionario.isEmpty() || formaPagamento.isEmpty() || dataVenda == null) {
            showAlert("Erro", "Todos os campos obrigatórios devem ser preenchidos.");
            return;
        }

        Venda venda = new Venda();
        venda.setCpfCliente(cpfCliente);
        venda.setCpfFuncionario(cpfFuncionario);
        venda.setFormaPagamento(formaPagamento);
        venda.setTotalVenda(totalVenda);
        venda.setDataVenda(java.sql.Date.valueOf(dataVenda)); 

        List<ItemVenda> itensVenda = tableView.getItems();

        try {
            vendasDAO.inserirVenda(venda, itensVenda);
            showAlert("Sucesso", "Venda registrada com sucesso!");
            OnbtnCancelar(event); 
        } catch (SQLException e) {
            showAlert("Erro", "Erro ao registrar a venda: " + e.getMessage());
        }
    }

    private Produtos buscarProdutoPorCodigo(String codigoProduto) {
        try {
            return vendasDAO.buscarProdutoPorCodigo(codigoProduto); 
        } catch (SQLException e) {
            showAlert("Erro", "Erro ao buscar o produto: " + e.getMessage());
            return null;
        }
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    	colCodigo.setCellValueFactory(new PropertyValueFactory<ItemVenda, String>("codigoProduto"));
        colProduto.setCellValueFactory(new PropertyValueFactory<ItemVenda, String>("descricao"));
        colQuantidade.setCellValueFactory(new PropertyValueFactory<ItemVenda, Integer>("quantidade"));
        colSubtotal.setCellValueFactory(new PropertyValueFactory<ItemVenda, BigDecimal>("subtotal"));

        ObservableList<ItemVenda> listaItensVenda = FXCollections.observableArrayList();
        tableView.setItems(listaItensVenda);
    }
}