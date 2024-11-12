package packageController;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

import application.Main;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import packageModel.ItemVenda;
import packageModel.Produtos;
import packageModel.Venda;
import package_controle.VendasDAO;

public class controllerPDV implements Initializable {
	
    private VendasDAO vendasDAO;
    
    public controllerPDV() {
        this.vendasDAO = new VendasDAO();  
    }
    @FXML
    private Button btnCancelar, btnSair;
    
    @FXML
    private Button bntAdicionar;
    
    @FXML
    private Button btnAbrirCadastroCliente;
    
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
    private TableColumn<ItemVenda, Double> colSubtotal;

    
    @FXML
    private ComboBox<String> comboxMetodoPagamento;
    
    @FXML
    private ComboBox<String> comboxParcelar;

    @FXML
    private DatePicker dataVendaPicker;

    @FXML
    private TextField funcionarioField;

    @FXML
    private Label labelValorTotal;

    @FXML
    private Button btnAbrirTabela;
    
    @FXML
    private Button btnAlterar;

    @FXML
    private Button btnRemover;
    
    @FXML
    private TextField quantidadeField;

    @FXML
    private TableView<ItemVenda> tableView;

    private double totalVenda = 0.0;

    @FXML
    void OnbtnAdicionarProduto(ActionEvent event) {
        String codigoProduto = codigoProdutoField.getText();
        String quantidadeStr = quantidadeField.getText();

        if (codigoProduto.isEmpty()) {
            showAlert("Erro", "Por favor, insira o código do produto.");
            return;
        }

        if (quantidadeStr.isEmpty()) {
            showAlert("Erro", "Por favor, insira a quantidade do produto.");
            return;
        }

        int quantidade;
        try {
            quantidade = Integer.parseInt(quantidadeStr);
            if (quantidade <= 0) {
                showAlert("Erro", "A quantidade deve ser maior que zero.");
                return;
            }
        } catch (NumberFormatException e) {
            showAlert("Erro", "Quantidade inválida! Insira um número inteiro válido.");
            return;
        }

        Produtos produto = buscarProdutoPorCodigo(codigoProduto);
        if (produto == null) {
            showAlert("Erro", "Produto não encontrado. Verifique o código e tente novamente.");
            return;
        }

        double precoUnitario = produto.getPrecoUnitario();
        if (precoUnitario <= 0) {
            showAlert("Erro", "O preço do produto não está válido. Verifique o preço.");
            return;
        }

        double subtotal = precoUnitario * quantidade;

        ItemVenda item = new ItemVenda();
        item.setCodigoProduto(produto.getCodigo());
        item.setQuantidade(quantidade);
        item.setPrecoUnitario(precoUnitario);
        item.setSubtotal(subtotal);

        ObservableList<ItemVenda> itens = tableView.getItems();
        itens.add(item); 
        totalVenda += subtotal;
        labelValorTotal.setText(String.format("R$ %.2f", totalVenda)); 
        
        atualizarParcelas();
    }
    @FXML
    private void atualizarParcelas() {
        double total = totalVenda;  
        ObservableList<String> parcelas = FXCollections.observableArrayList();

        for (int i = 1; i <= 6; i++) {
            double valorParcela = total / i;  
            parcelas.add(i + "x de R$ " + String.format("%.2f", valorParcela));  
        }
        comboxParcelar.setItems(parcelas);
    }

    @FXML
    void btnRemover(ActionEvent event) {
        ItemVenda itemSelecionado = tableView.getSelectionModel().getSelectedItem();

        if (itemSelecionado != null) {
            tableView.getItems().remove(itemSelecionado);

            totalVenda -= itemSelecionado.getSubtotal();
            labelValorTotal.setText(String.format("R$ %.2f", totalVenda));

            atualizarParcelas();
        } else {
            showAlert("Erro", "Por favor, selecione um item para remover.");
        }
    }

    @FXML
    void btnAlterar(ActionEvent event) {
        ItemVenda itemSelecionado = tableView.getSelectionModel().getSelectedItem();

        if (itemSelecionado != null) {
            String novaQuantidadeStr = quantidadeField.getText();
            
            if (novaQuantidadeStr.isEmpty()) {
                showAlert("Erro", "Por favor, insira a nova quantidade.");
                return;
            }

            int novaQuantidade;
            try {
                novaQuantidade = Integer.parseInt(novaQuantidadeStr);
                if (novaQuantidade <= 0) {
                    showAlert("Erro", "A quantidade deve ser maior que zero.");
                    return;
                }
            } catch (NumberFormatException e) {
                showAlert("Erro", "Quantidade inválida! Insira um número inteiro válido.");
                return;
            }

            double precoUnitario = itemSelecionado.getPrecoUnitario();
            double novoSubtotal = precoUnitario * novaQuantidade;
            itemSelecionado.setQuantidade(novaQuantidade);
            itemSelecionado.setSubtotal(novoSubtotal);

            tableView.refresh();  

            totalVenda = 0.0;
            for (ItemVenda item : tableView.getItems()) {
                totalVenda += item.getSubtotal();
            }
            labelValorTotal.setText(String.format("R$ %.2f", totalVenda));

            atualizarParcelas();
        } else {
            showAlert("Erro", "Por favor, selecione um item para alterar.");
        }
    }

    
    @FXML
    void OnbtnAbrirCadastroCliente(ActionEvent event) throws IOException {
    	Main.TelaCcadastroClientes();
    }
    @FXML
    void OnbtnAbrirTabela(ActionEvent event) throws IOException {
    	Main.TelaTabelaProduto();
    }
    private void fecharJanela() {
		Stage stage = (Stage) btnSair.getScene().getWindow();
		stage.close();
	}
    @FXML
    void btnSair(ActionEvent event) throws IOException {
    	fecharJanela();
    }
    @FXML
    void OnbtnCancelar(ActionEvent event) {
        clienteField.clear();
        funcionarioField.clear();
        codigoProdutoField.clear();
        quantidadeField.clear();
        comboxMetodoPagamento.getSelectionModel().clearSelection();
        comboxParcelar.getSelectionModel().clearSelection();
        dataVendaPicker.setValue(null);
        labelValorTotal.setText("R$ 0,00");

        tableView.getItems().clear();
        totalVenda = 0.0;
    }

    @FXML
    void OnbtnRegistrarVenda(ActionEvent event) {
        String cpfCliente = clienteField.getText();
        String cpfFuncionario = funcionarioField.getText();
        String metodoPagamento = comboxMetodoPagamento.getValue();
        String parcela = comboxParcelar.getValue();
        String dataVenda = dataVendaPicker.getValue() != null ? dataVendaPicker.getValue().toString() : "";

        if (cpfCliente.isEmpty() || cpfFuncionario.isEmpty()|| dataVenda.isEmpty()) {
            showAlert("Erro", "Por favor, preencha todos os campos obrigatórios.");
            return;
        }

        Venda venda = new Venda();
        venda.setCpfCliente(cpfCliente);
        venda.setCpfFuncionario(cpfFuncionario);
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

        ObservableList<String> metodosPagamento = FXCollections.observableArrayList("Dinheiro", "Cartão de Crédito", "Cartão de Débito", "Pix");
        comboxMetodoPagamento.setItems(metodosPagamento);
       
        ObservableList<String> parcelas = FXCollections.observableArrayList();
        for (int i = 1; i <= 6; i++) {
            parcelas.add(i + "x");
        }
        comboxParcelar.setItems(parcelas);
        
        colCodigo.setCellValueFactory(new PropertyValueFactory<ItemVenda, String>("codigoProduto"));
        colProduto.setCellValueFactory(new PropertyValueFactory<ItemVenda, String>("descricao"));
        colQuantidade.setCellValueFactory(new PropertyValueFactory<ItemVenda, Integer>("quantidade"));
        colSubtotal.setCellValueFactory(new PropertyValueFactory<ItemVenda, Double>("subtotal"));

        ObservableList<ItemVenda> listaItensVenda = FXCollections.observableArrayList();
        tableView.setItems(listaItensVenda);  
    }


}
