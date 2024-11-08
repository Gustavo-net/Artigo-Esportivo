package packageController;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import packageModel.Produtos;
import packageModel.Vendas;
import package_controle.ProdutoDAO;
import package_controle.VendaDAO;

import java.net.URL;
import java.text.DecimalFormat;
import java.util.ResourceBundle;

public class controllerPDV implements Initializable {

    @FXML private Button btnCancelar;
    @FXML private TextField codigoProdutoField;
    @FXML private TextField quantidadeField;
    @FXML private TextField descontoField;
    @FXML private TextField clienteField;
    @FXML private TextField funcionarioField;
    @FXML private Label labelValorTotal;
    @FXML private ComboBox<String> statusVendaCombo;
    @FXML private ComboBox<String> canalVendasCombo;
    @FXML private ComboBox<String> metodoPagamentoCombo;
    @FXML private TableView<Vendas> tableView;
    @FXML private TableColumn<Vendas, String> colCodigo;
    @FXML private TableColumn<Vendas, String> colProduto;
    @FXML private TableColumn<Vendas, String> colQuantidade;
    @FXML private TableColumn<Vendas, String> colDesconto;
    @FXML private TableColumn<Vendas, String> colSubtotal;
    @FXML private ComboBox<String> comboxParcelar;

    private ObservableList<Vendas> vendasList = FXCollections.observableArrayList();
    private double valorTotalVenda = 0.0;

    @FXML
    private void onAdicionarProduto(MouseEvent event) {
        String codigoProduto = codigoProdutoField.getText();
        
        // Validações
        if (codigoProduto.trim().isEmpty()) {
            showErrorMessage("Código de produto não pode estar vazio.");
            return;
        }

        ProdutoDAO produtoDAO = new ProdutoDAO();
        Produtos produto = produtoDAO.buscarProdutoPorCodigo(codigoProduto);

        if (produto == null) {
            showErrorMessage("Produto não encontrado.");
            return;
        }

        if (quantidadeField.getText().isEmpty() || descontoField.getText().isEmpty()) {
            showErrorMessage("Quantidade e desconto devem ser informados.");
            return;
        }

        int quantidade;
        double desconto;

        try {
            quantidade = Integer.parseInt(quantidadeField.getText());
            desconto = Double.parseDouble(descontoField.getText());
        } catch (NumberFormatException e) {
            showErrorMessage("Quantidade ou desconto inválidos.");
            return;
        }

        double valorUnitario = produto.getPrecoUnitario();
        double subtotal = valorUnitario * quantidade * (1 - desconto / 100);

        Vendas venda = new Vendas();
        venda.setCodigoProduto(produto.getCodigo());
        venda.setNomeProduto(produto.getNome());
        venda.setQuantidade(quantidade);
        venda.setDesconto(desconto);
        venda.setSubtotal(subtotal);

        vendasList.add(venda);
        tableView.setItems(vendasList);
        tableView.refresh();

        // Limpar campos
        codigoProdutoField.clear();
        quantidadeField.clear();
        descontoField.clear();

        // Atualizar valor total
        updateValorTotal();
    }

    private void updateValorTotal() {
        valorTotalVenda = vendasList.stream()
                .mapToDouble(Vendas::getSubtotal)
                .sum();

        DecimalFormat df = new DecimalFormat("###,##0.00");
        labelValorTotal.setText("R$ " + df.format(valorTotalVenda));
    }

    private void showErrorMessage(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Erro");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    @FXML
    private void OnbtnRegistrarVenda(MouseEvent event) {
        Vendas venda = new Vendas();
        venda.setCpf_Cliente(clienteField.getText());
        venda.setCpf_Funcionário(funcionarioField.getText());
        venda.setDataVenda(java.time.LocalDate.now().toString());
        venda.setStatusVenda(statusVendaCombo.getValue());
        venda.setCanalVendas(canalVendasCombo.getValue());
        venda.setId_Pagamento(metodoPagamentoCombo.getValue());
        venda.setValorTotal(valorTotalVenda);

        // Adiciona os produtos da lista de vendas
        for (Vendas v : vendasList) {
            venda.setCodigoProduto(v.getCodigoProduto());
            venda.setQuantidade(v.getQuantidade());
            venda.setDesconto(v.getDesconto());
            venda.setSubtotal(v.getSubtotal());
            venda.setNomeProduto(v.getNomeProduto());
        }

        // Persistir a venda
        VendaDAO vendaDAO = new VendaDAO();
        vendaDAO.create(venda);

        // Parcelamento
        int parcelas = Integer.parseInt(comboxParcelar.getValue());
        double valorParcela = valorTotalVenda / parcelas;
        for (int i = 1; i <= parcelas; i++) {
            System.out.println("Parcelamento: " + i + "x de R$ " + new DecimalFormat("###,##0.00").format(valorParcela));
        }

        System.out.println("Venda registrada com sucesso!");
    }

    @FXML
    private void OnbtnCancelar(MouseEvent event) {
        // Limpa campos
        codigoProdutoField.clear();
        quantidadeField.clear();
        descontoField.clear();
        clienteField.clear();
        funcionarioField.clear();

        // Limpa a lista de vendas
        vendasList.clear();
        updateValorTotal();
    }

    private void fecharJanela() {
        Stage stage = (Stage) btnCancelar.getScene().getWindow();
        stage.close();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // Inicializa os ComboBoxes
        statusVendaCombo.setItems(FXCollections.observableArrayList("Pendente", "Pago", "Cancelado"));
        canalVendasCombo.setItems(FXCollections.observableArrayList("Online", "Loja Física", "Telemarketing"));
        metodoPagamentoCombo.setItems(FXCollections.observableArrayList("Dinheiro", "Cartão de Crédito", "Cartão de Débito", "Boleto"));
        comboxParcelar.setItems(FXCollections.observableArrayList("1", "2", "3", "4", "5", "6"));

        // Configura as colunas da TableView
        colCodigo.setCellValueFactory(new PropertyValueFactory<>("codigoProduto"));
        colProduto.setCellValueFactory(new PropertyValueFactory<>("nomeProduto"));
        colQuantidade.setCellValueFactory(cellData -> new SimpleStringProperty(String.valueOf(cellData.getValue().getQuantidade())));
        colDesconto.setCellValueFactory(cellData -> new SimpleStringProperty(String.valueOf(cellData.getValue().getDesconto())));
        colSubtotal.setCellValueFactory(cellData -> new SimpleStringProperty(new DecimalFormat("###,##0.00").format(cellData.getValue().getSubtotal())));

        // Inicializa a lista de vendas e associa à TableView
        vendasList = FXCollections.observableArrayList();
        tableView.setItems(vendasList);
        updateValorTotal();
    }
}
