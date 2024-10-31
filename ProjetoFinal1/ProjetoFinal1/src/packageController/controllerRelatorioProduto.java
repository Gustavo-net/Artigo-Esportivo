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
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import packageModel.Categorias;
import packageModel.Produtos;
import package_controle.CategoriaDAO;
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
//    @FXML
//    private TableColumn<Produtos, String> columnId;
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
        boxFiltrar.setOnAction(event -> filtrarProdutos());
    }

    private void carregarTableProduto() {
        arrayProduto = FXCollections.observableArrayList(produtoDAO.read());
        tableRelatorioProduto.setItems(arrayProduto);
        columnNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
        columnCodigo.setCellValueFactory(new PropertyValueFactory<>("codigo"));
        columnMarca.setCellValueFactory(new PropertyValueFactory<>("marca"));
        columnDescricao.setCellValueFactory(new PropertyValueFactory<>("descricao"));
        columnPrecoUn.setCellValueFactory(new PropertyValueFactory<>("precoUnitario"));
        columnEstoqueAtual.setCellValueFactory(new PropertyValueFactory<>("estoqueDisp"));
        columnCategoria.setCellValueFactory(new PropertyValueFactory<>("categoriaNome")); 
        atualizarTabela(arrayProduto);
    }

    private void atualizarTabela(ObservableList<Produtos> observableList) {
//    	columnId.setCellValueFactory(new PropertyValueFactory<>("idProduto"));
        columnNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
        columnCodigo.setCellValueFactory(new PropertyValueFactory<>("codigo"));
        columnMarca.setCellValueFactory(new PropertyValueFactory<>("marca"));
        columnDescricao.setCellValueFactory(new PropertyValueFactory<>("descricao"));
        columnPrecoUn.setCellValueFactory(new PropertyValueFactory<>("precoUnitario"));
        columnEstoqueAtual.setCellValueFactory(new PropertyValueFactory<>("estoqueDisp"));
        columnCategoria.setCellValueFactory(new PropertyValueFactory<>("categoriaNome")); 
    }

    @FXML
    void OnbtnCadastros(ActionEvent event) {
        Main.changeScreen("cadastros");
    }

    @FXML
    void OnbtnClientes(ActionEvent event) {
        Main.changeScreen("clientes");
    }
    
    @FXML
    void OnbtnExcluir(ActionEvent event) {
    } 
    @FXML
    void OnbtnProdutos (ActionEvent event) {
    }
    @FXML
    void OnbtnFuncionarios(ActionEvent event) {
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
    void OnbtnFornecedores(ActionEvent event) {
        Main.changeScreen("fornecedores");
    }

    @FXML
    void OnbtnFuncionários(ActionEvent event) {
        Main.changeScreen("funcionarios");
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
            
            String categoriaSelecionada = boxFiltrar.getValue();
            
            if (pesquisa.isEmpty() && categoriaSelecionada != null) {
                String idCategoria = new CategoriaDAO().obterIdCategoria(categoriaSelecionada);
                if (idCategoria != null) {
                    ArrayList<Produtos> produtosFiltrados = produtoDAO.buscarProdutosPorCategoria(idCategoria);
                    atualizarTabela(FXCollections.observableArrayList(produtosFiltrados));
                } else {
                    showAlert("Categoria não encontrada.");
                    carregarTableProduto();
                }
            } else if (!pesquisa.isEmpty()) {
                arrayProduto = FXCollections.observableArrayList(produtoDAO.search(pesquisa));
                atualizarTabela(arrayProduto);
            } else {
                carregarTableProduto();
            }
        } catch (Exception e) {
            showAlert("Erro ao pesquisar produtos: " + e.getMessage());
        }
    }


    
    @FXML
    void OnbtnSair(ActionEvent event) {
        Main.changeScreen("login");
    }

    @FXML
    void OnbtnVendas(ActionEvent event) {
        Main.changeScreen("vendas");
    }

    @FXML
    void OnbtnVoltar(ActionEvent event) {
        Main.changeScreen("main");
    }

    private void inicializarComboBox() {
        CategoriaDAO categoriaDAO = new CategoriaDAO();
        ArrayList<Categorias> categorias = categoriaDAO.read();
        
        for (Categorias categoria : categorias) {
            boxFiltrar.getItems().add(categoria.getNomeCategoria());
        }
      
        if (!categorias.isEmpty()) {
            boxFiltrar.setValue(categorias.get(0).getNomeCategoria()); 
            filtrarProdutos();
        }
    }

    private void filtrarProdutos() {
        String categoriaSelecionada = boxFiltrar.getValue();
        if (categoriaSelecionada == null || categoriaSelecionada.isEmpty()) {
            carregarTableProduto(); 
            return;
        }

        String idCategoria = new CategoriaDAO().obterIdCategoria(categoriaSelecionada);
        if (idCategoria != null) {
            ArrayList<Produtos> produtosFiltrados = produtoDAO.buscarProdutosPorCategoria(idCategoria);
            atualizarTabela(FXCollections.observableArrayList(produtosFiltrados));
        } else {
            showAlert("Categoria não encontrada.");
            carregarTableProduto(); 
        }
    }


    private void showAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setContentText(message);
        alert.showAndWait();
    }

    private String obterIdCategoria(String nomeCategoria) {
        CategoriaDAO categoriaDAO = new CategoriaDAO();
        ArrayList<Categorias> categorias = categoriaDAO.read();
        
        for (Categorias categoria : categorias) {
            if (categoria.getNomeCategoria().equals(nomeCategoria)) {
                return categoria.getIdCategoria(); 
            }
        }
        return null; 
    }
}
