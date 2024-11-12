package packageController;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
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
import javafx.scene.input.MouseEvent;
import packageModel.Produtos;
import package_controle.ProdutoDAO;

public class controllerRelatorioProduto implements Initializable {
    @FXML
    private TableView<Produtos> tableRelatorioProduto;

    @FXML
    private ComboBox<String> boxFiltrar;

    @FXML
    private Button btnCadastros;
    @FXML
    private Button btnClientes;
    @FXML
    private Button btnEditar;
    @FXML
    private Button btnFornecedores;
    @FXML
    private Button btnFuncionários;
    @FXML
    private Button btnInserir;
    @FXML
    private Button btnVendas;
    @FXML
    private Button btnVoltar;
    @FXML
    private Button btnSair;

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
    void OnbtnVoltar(ActionEvent event) {
        Main.changeScreen("main");
    }

    @FXML
    void OnbtnCadastros(ActionEvent event) {
        Main.changeScreen("cadastros");
    }
    @FXML
    void OnbtnVendas(ActionEvent event) {
        Main.changeScreen("vendas");
    }
    @FXML
    void OnbtnFornecedores(ActionEvent event) {
        Main.changeScreen("fornecedores");
    }
    @FXML
    void OnbtnSair(ActionEvent event) {
        Main.changeScreen("login");
    }
    @FXML
    void OnbtnFuncionários(ActionEvent event) {
        Main.changeScreen("funcionarios");
    }
    @FXML
    void OnbtnClientes(ActionEvent event) {
        Main.changeScreen("clientes");
    }

    @FXML
    void OnbtnExcluir(ActionEvent event) {
        int i = tableRelatorioProduto.getSelectionModel().getSelectedIndex();
        if (i == -1) {
            showAlert("Selecione um Produto para Excluir Primeiro!");
            return;
        }

        Produtos produtoSelecionado = tableRelatorioProduto.getItems().get(i);
        
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmação de Exclusão");
        alert.setHeaderText("Você tem certeza que deseja excluir este produto?");
        alert.setContentText("Produto: " + produtoSelecionado.getNome());

        alert.showAndWait().ifPresent(response -> {
            if (response == ButtonType.OK) {
                try {
                    produtoDAO.delete(produtoSelecionado.getIdProduto());
                    carregarTableProduto();
                    showAlert("Produto excluído com sucesso!");
                } catch (Exception e) {
                    showAlert("Erro ao excluir o produto: " + e.getMessage());
                }
            }
        });
    }

    @FXML
    void OnbtnEditar(ActionEvent event) throws IOException {
        if (tableRelatorioProduto.getSelectionModel().getSelectedIndex() == -1) {
            showAlert("Selecione um Produto para Editar Primeiro!");
        } else {
            int i = tableRelatorioProduto.getSelectionModel().getSelectedIndex();
            produtoEditor = tableRelatorioProduto.getItems().get(i);
            Main.TelaCadastroProduto();
        }
    }
    
    @FXML
    void OnbtnInserir(ActionEvent event) throws IOException {
        produtoEditor = null;
        Main.TelaCadastroProduto();
    }

    @FXML
    void OnPesquisarImagem(MouseEvent event) {
        try {
            String pesquisa = txtPesquisar.getText().trim();
            
            if (!pesquisa.isEmpty()) {
                ArrayList<Produtos> produtosResultado = new ArrayList<>();
                
                // Simula a pesquisa em produtos fictícios
                for (Produtos produto : arrayProduto) {
                    // Verifica se o nome do produto contém a pesquisa
                    if (produto.getNome().toLowerCase().contains(pesquisa.toLowerCase())) {
                        produtosResultado.add(produto);
                    }
                }
                
                atualizarTabela(FXCollections.observableArrayList(produtosResultado));
                if (produtosResultado.isEmpty()) {
                    showAlert("Nenhum produto encontrado.");
                }
            } else {
                carregarTableProduto(); // Carrega todos os produtos se a pesquisa estiver vazia
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
