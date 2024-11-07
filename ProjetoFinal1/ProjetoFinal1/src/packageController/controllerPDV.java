package packageController;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.util.converter.NumberStringConverter;
import packageModel.Vendas;

import java.text.DecimalFormat;
import java.util.List;

public class controllerPDV {

    // FXML components
    @FXML private TextField codigoProdutoField;
    @FXML private TextField quantidadeField;
    @FXML private TextField descontoField;
    @FXML private TextField clienteField;
    @FXML private TextField funcionarioField;
    @FXML private TextField valorTotalField;
    @FXML private ComboBox<String> statusVendaCombo;
    @FXML private ComboBox<String> canalVendasCombo;
    @FXML private ComboBox<String> metodoPagamentoCombo;
    @FXML private ComboBox<String> parcelasCombo;
    @FXML private TableView<Vendas> tableView;
    @FXML private TableColumn<Vendas, String> colCodigo;
    @FXML private TableColumn<Vendas, String> colProduto;
    @FXML private TableColumn<Vendas, String> colQuantidade;
    @FXML private TableColumn<Vendas, String> colDesconto;
    @FXML private TableColumn<Vendas, String> colSubtotal;
    @FXML private Label labelValorTotal;
    @FXML private ComboBox<String> comboxParcelar;

    private ObservableList<Vendas> vendasList = FXCollections.observableArrayList();
    private double valorTotalVenda = 0.0;

    @FXML
    public void initialize() {
        statusVendaCombo.setItems(FXCollections.observableArrayList("Pendente", "Pago", "Cancelado"));
        canalVendasCombo.setItems(FXCollections.observableArrayList("Online", "Loja Física", "Telemarketing"));
        metodoPagamentoCombo.setItems(FXCollections.observableArrayList("Dinheiro", "Cartão de Crédito", "Cartão de Débito", "Boleto"));
        comboxParcelar.setItems(FXCollections.observableArrayList("1", "2", "3", "4", "5", "6"));

        colCodigo.setCellValueFactory(new PropertyValueFactory<>("codigoProduto"));
        colProduto.setCellValueFactory(new PropertyValueFactory<>("nomeProduto"));
        colQuantidade.setCellValueFactory(new PropertyValueFactory<>("quantidade"));
        colDesconto.setCellValueFactory(new PropertyValueFactory<>("desconto"));
        colSubtotal.setCellValueFactory(new PropertyValueFactory<>("subtotal"));

        updateValorTotal();
    }
   
    @FXML
    private void onAdicionarProduto(MouseEvent event) {
        String codigoProduto = codigoProdutoField.getText();
        int quantidade = Integer.parseInt(quantidadeField.getText());
        double desconto = Double.parseDouble(descontoField.getText());

        double valorUnitario = 100.0; 

        // Criar uma nova instância de Vendas
        Vendas venda = new Vendas();
        venda.setCodigoProduto(codigoProduto);
        venda.setQuantidade(quantidade);
        venda.setDesconto(desconto);
        venda.calcularSubtotal(valorUnitario);

        vendasList.add(venda);
        tableView.setItems(vendasList); 

        updateValorTotal();
    }

    private void updateValorTotal() {
        valorTotalVenda = vendasList.stream()
                .mapToDouble(venda -> venda.getSubtotal())
                .sum();

        DecimalFormat df = new DecimalFormat("###,##0.00");
        labelValorTotal.setText("R$ " + df.format(valorTotalVenda));
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

        int parcelas = Integer.parseInt(parcelasCombo.getValue());
        double valorParcela = valorTotalVenda / parcelas;
        for (int i = 1; i <= parcelas; i++) {
            System.out.println("Parcelamento: " + i + "x de R$ " + new DecimalFormat("###,##0.00").format(valorParcela));
        }

        System.out.println("Venda registrada com sucesso!");
    }

    @FXML
    private void OnbtnCancelar(MouseEvent event) {
        codigoProdutoField.clear();
        quantidadeField.clear();
        descontoField.clear();
        clienteField.clear();
        funcionarioField.clear();
        vendasList.clear();
        updateValorTotal();  
    }
}
