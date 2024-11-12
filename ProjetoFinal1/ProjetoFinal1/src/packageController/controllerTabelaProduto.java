package packageController;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import packageModel.Produtos;
import package_controle.ProdutoDAO;

public class controllerTabelaProduto implements Initializable {
    @FXML
    private TableView<Produtos> tableRelatorioProduto;

    @FXML
    private ComboBox<String> boxFiltrar;

    @FXML
    private TableColumn<Produtos, String> columnCodigo;
    @FXML
    private TableColumn<Produtos, String> columnDescricao;
    @FXML
    private TableColumn<Produtos, String> columnEstoqueAtual;
    @FXML
    private TableColumn<Produtos, String> columnMarca;
    @FXML
    private TableColumn<Produtos, String> columnNome;
    @FXML
    private TableColumn<Produtos, String> columnPrecoUn;
    @FXML
    private TableColumn<Produtos, String> columnCategoria; 
    @FXML
    private TextField txtPesquisar;

    private ObservableList<Produtos> arrayProduto;
    private ProdutoDAO produtoDAO = new ProdutoDAO();

    @SuppressWarnings("exports")
	public static Produtos produtoEditor = new Produtos();

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        carregarTableProduto();
        inicializarComboBox();
    }

    private void inicializarComboBox() {
        boxFiltrar.getItems().clear();
        
        String[] categ = {
            "Roupas Masculinas",
            "Roupas Femininas",
            "Calçados Masculinos",
            "Calçados Femininos",
            "Acessórios de Esporte",
            "Equipamentos de Academia",
            "Bolsas e Mochilas Esportivas",
            "Garrafas e Squeezes",
            "Suplementos e Nutrição",
            "Proteção e Segurança",
            "Natação",
            "Ciclismo",
            "Esportes Radicais",
            "Futebol",
            "Basquete"
        };

        for (String categoria : categ) {
            boxFiltrar.getItems().add(categoria);
        }
    }

    private void carregarTableProduto() {
        arrayProduto = FXCollections.observableArrayList(produtoDAO.read());
        atualizarTabela(arrayProduto);
    }

    private void atualizarTabela(ObservableList<Produtos> produtos) {
        tableRelatorioProduto.setItems(produtos);
        columnNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
        columnCodigo.setCellValueFactory(new PropertyValueFactory<>("codigo"));
        columnMarca.setCellValueFactory(new PropertyValueFactory<>("marca"));
        columnDescricao.setCellValueFactory(new PropertyValueFactory<>("descricao"));
        columnPrecoUn.setCellValueFactory(new PropertyValueFactory<>("precoUnitario"));
        columnEstoqueAtual.setCellValueFactory(new PropertyValueFactory<>("estoqueDisp"));
        columnCategoria.setCellValueFactory(new PropertyValueFactory<>("categoriaNome"));
    }

    @FXML
    void OnPesquisarImagem(MouseEvent event) {
        try {
            String pesquisa = txtPesquisar.getText().trim();
            
            if (!pesquisa.isEmpty()) {
                ArrayList<Produtos> produtosResultado = new ArrayList<>();
                
                for (Produtos produto : arrayProduto) {
                    if (produto.getNome().toLowerCase().contains(pesquisa.toLowerCase())) {
                        produtosResultado.add(produto);
                    }
                }
                
                atualizarTabela(FXCollections.observableArrayList(produtosResultado));
                if (produtosResultado.isEmpty()) {
                    showAlert("Nenhum produto encontrado.");
                }
            } else {
                carregarTableProduto(); 
            }
        } catch (Exception e) {
            showAlert("Erro ao pesquisar produtos: " + e.getMessage());
        }
    }

    private void showAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
