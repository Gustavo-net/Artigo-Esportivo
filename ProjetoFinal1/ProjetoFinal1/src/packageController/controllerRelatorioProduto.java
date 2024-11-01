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
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import packageModel.Categorias;
import packageModel.Clientes;
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

        tableRelatorioProduto.setRowFactory(tv -> new TableRow<Produtos>() {
            @Override
            protected void updateItem(Produtos item, boolean empty) {
                super.updateItem(item, empty);
                if (item == null || empty) {
                    setStyle("");
                } else if (isSelected()) {
                    setStyle("-fx-background-color: #D3D3D3;"); 
                } else {
                    setStyle("");
                }
            }
        });
    }

    private void inicializarComboBox() {
        CategoriaDAO categoriaDAO = new CategoriaDAO();
        ArrayList<Categorias> categorias = categoriaDAO.read();
        
       boxFiltrar.getItems().clear();

       for (Categorias categoria : categorias) {
            boxFiltrar.getItems().add(categoria.getNomeCategoria());
        }
        
        if (!categorias.isEmpty()) {
            boxFiltrar.setValue(categorias.get(0).getNomeCategoria());
            filtrarProdutos(); 
        }
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
                    showAlert("Erro ao excluir o produtp: " + e.getMessage());
                }
            }
        });
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
            
            if (!pesquisa.isEmpty() && categoriaSelecionada != null) {
                String idCategoria = new CategoriaDAO().obterIdCategoria(categoriaSelecionada);
                ArrayList<Produtos> produtosFiltrados = produtoDAO.buscarProdutosPorCategoria(idCategoria);
                
                // Filtrar ainda mais pelos produtos que correspondem à pesquisa
                ArrayList<Produtos> produtosResultado = new ArrayList<>();
                for (Produtos produto : produtosFiltrados) {
                    if (produto.getNome().toLowerCase().contains(pesquisa.toLowerCase())) {
                        produtosResultado.add(produto);
                    }
                }
                
                atualizarTabela(FXCollections.observableArrayList(produtosResultado));
                if (produtosResultado.isEmpty()) {
                    showAlert("Nenhum produto encontrado.");
                }
            } else if (!pesquisa.isEmpty()) {
                // Se a pesquisa estiver preenchida, mas a categoria não for selecionada
                arrayProduto = FXCollections.observableArrayList(produtoDAO.search(pesquisa));
                atualizarTabela(arrayProduto);
                if (arrayProduto.isEmpty()) {
                    showAlert("Nenhum produto encontrado.");
                }
            } else {
                carregarTableProduto(); // Caso a pesquisa esteja vazia
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

}
