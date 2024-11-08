package packageController;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import application.Main;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import packageModel.Venda;
import package_controle.VendasDAO;

public class ControllerRelatorioVendas implements Initializable {

    @FXML
    private Button btnCadastros, btnClientes, btnSair, btnExcluir, btnEditar, btnInserir, btnPesquisar, btnProdutos, btnFuncionarios, btnVoltar;

    @FXML
    private TableColumn<Venda, String> columnDataVenda, columnCodigoProduto, columnQuantidade, columnDesconto, columnSubTotal, columnValorTotal, columnCPF_Cliente;

    @FXML
    private TableView<Venda> tableRelatorioVenda;

    @FXML
    private TextField labelPesquisar;

    private ObservableList<Venda> arrayVendas;
    private VendasDAO vendaDAO = new VendasDAO();

    @FXML
    private void carregarTableVendas() throws SQLException {
        try {
            arrayVendas = FXCollections.observableArrayList(vendaDAO.listarVendas()); 
            atualizarTabela(arrayVendas);
        } catch (SQLException e) {
            showAlert("Erro ao carregar vendas: " + e.getMessage());
        }
    }

    private void atualizarTabela(ObservableList<Venda> vendas) {
        tableRelatorioVenda.setItems(vendas);

        columnDataVenda.setCellValueFactory(new PropertyValueFactory<>("dataVenda"));
        columnCodigoProduto.setCellValueFactory(new PropertyValueFactory<>("codigoProduto"));
        columnQuantidade.setCellValueFactory(new PropertyValueFactory<>("quantidade"));
        columnDesconto.setCellValueFactory(new PropertyValueFactory<>("desconto"));
        columnSubTotal.setCellValueFactory(new PropertyValueFactory<>("subTotal"));
        columnValorTotal.setCellValueFactory(new PropertyValueFactory<>("valorTotal"));
        columnCPF_Cliente.setCellValueFactory(new PropertyValueFactory<>("cpfCliente"));
    }

    @FXML
    void OnbtnExcluir(ActionEvent event) {
        int i = tableRelatorioVenda.getSelectionModel().getSelectedIndex();
        if (i == -1) {
            showAlert("Selecione uma venda para excluir primeiro!");
            return;
        }

        Venda vendaSelecionada = tableRelatorioVenda.getItems().get(i);
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmação de Exclusão");
        alert.setHeaderText("Você tem certeza que deseja excluir esta venda?");
        alert.setContentText("Venda: " + vendaSelecionada.getDataVenda());

        alert.showAndWait().ifPresent(response -> {
            if (response == ButtonType.OK) {
                try {
                    vendaDAO.deletarVenda(vendaSelecionada.getIdVenda());
                    carregarTableVendas();
                    showAlert("Venda excluída com sucesso!");
                } catch (Exception e) {
                    showAlert("Erro ao excluir a venda: " + e.getMessage());
                }
            }
        });
    }

    private void showAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setContentText(message);
        alert.showAndWait();
    }

    @FXML
    void OnbtnClientes(ActionEvent event) {

    }

    @FXML
    void OnbtnFornecedores(ActionEvent event) {

    }

    @FXML
    void OnbtnFuncionários(ActionEvent event) {

    }

    @FXML
    void OnbtnProdutos(ActionEvent event) {

    }

    @FXML
    void OnbtnSair(ActionEvent event) {

    }


    @FXML
    void OnbtnEditar(ActionEvent event) throws IOException {
        if (tableRelatorioVenda.getSelectionModel().getSelectedIndex() == -1) {
            showAlert("Selecione uma venda para editar primeiro!");
        } else {
            int i = tableRelatorioVenda.getSelectionModel().getSelectedIndex();
            Venda vendaSelecionada = tableRelatorioVenda.getItems().get(i);
            Main.TelaCadastroVenda(vendaSelecionada);
        }
    }

    @FXML
    void OnbtnInserir(ActionEvent event) throws IOException {
        Main.TelaCadastroVenda(null);
    }

    @FXML
    void OnbtnVoltar(ActionEvent event) {
        Main.changeScreen("main");
    }

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        try {
            carregarTableVendas();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
