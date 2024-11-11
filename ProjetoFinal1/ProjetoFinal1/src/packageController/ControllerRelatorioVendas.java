package packageController;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import application.Main;
import javafx.beans.property.SimpleStringProperty;
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
    private Button btnCadastros, btnClientes, btnSair, btnExcluir, btnInserir, btnPesquisar, btnProdutos, btnFuncionarios, btnVoltar;

    @FXML
    private TableColumn<Venda, String> columnDataVenda, columnCodigoProduto, columnQuantidade, columnDesconto, columnSubTotal, columnValorTotal, columnCPF_Cliente, columnCPF_Funcionario;

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
        columnCPF_Cliente.setCellValueFactory(new PropertyValueFactory<>("cpfCliente"));
        columnCPF_Funcionario.setCellValueFactory(new PropertyValueFactory<>("cpfFuncionario"));
        columnValorTotal.setCellValueFactory(new PropertyValueFactory<>("totalVenda"));

        columnCodigoProduto.setCellValueFactory(cellData -> 
            new SimpleStringProperty(cellData.getValue().getItensVenda().get(0).getCodigoProduto()));
        columnQuantidade.setCellValueFactory(cellData -> 
            new SimpleStringProperty(String.valueOf(cellData.getValue().getItensVenda().get(0).getQuantidade())));
        columnSubTotal.setCellValueFactory(cellData -> 
            new SimpleStringProperty(String.valueOf(cellData.getValue().getItensVenda().get(0).getSubtotal())));
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
    	Main.changeScreen("login");
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
