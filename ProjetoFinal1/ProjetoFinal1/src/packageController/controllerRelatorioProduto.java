package packageController;

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
	private Button btnPesquisar;

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
	private TableColumn<Produtos, String> columnId;

	@FXML
	private TableColumn<Produtos, String> columnMarca;

	@FXML
	private TableColumn<Produtos, String> columnNome;

	@FXML
	private TableColumn<Produtos, String> columnPrecoUn;

	@FXML
	private TextField txtPesquisar;

	private ObservableList<Produtos> arrayProduto;
	private ProdutoDAO produtoDAO = new ProdutoDAO();

	public static Produtos produtoEditor = new Produtos();

	public void carregarTableProduto() {
		arrayProduto = FXCollections.observableArrayList(ProdutoDAO.read());
		tableRelatorioProduto.setItems(arrayProduto);
		atualizarTabela(arrayProduto);
	}

<<<<<<< HEAD
    private void atualizarTabela(ObservableList<Produtos> arrayProduto2) {
        columnId.setCellValueFactory(new PropertyValueFactory<>("idProduto"));
        columnNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
        columnCodigo.setCellValueFactory(new PropertyValueFactory<>("codigo"));
        columnMarca.setCellValueFactory(new PropertyValueFactory<>("marca"));
        columnDescricao.setCellValueFactory(new PropertyValueFactory<>("descricao"));
        columnPrecoUn.setCellValueFactory(new PropertyValueFactory<>("precoUnitario"));
        columnEstoqueAtual.setCellValueFactory(new PropertyValueFactory<>("estoqueDisp"));
=======
	private void atualizarTabela(ObservableList<Produtos> produtos) {
		columnId.setCellValueFactory(new PropertyValueFactory<>("idProduto"));
		columnNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
		columnCodigo.setCellValueFactory(new PropertyValueFactory<>("codigo"));
		columnMarca.setCellValueFactory(new PropertyValueFactory<>("marca"));
		columnDescricao.setCellValueFactory(new PropertyValueFactory<>("descricao"));
		columnPrecoUn.setCellValueFactory(new PropertyValueFactory<>("precoUnitario"));
		columnEstoqueAtual.setCellValueFactory(new PropertyValueFactory<>("estoqueDisp"));
>>>>>>> branch 'master' of https://github.com/Gustavo-net/Artigo-Esportivo.git

<<<<<<< HEAD
        tableRelatorioProduto.setItems(arrayProduto2);
    }
=======
		tableRelatorioProduto.setItems(produtos);
	}
>>>>>>> branch 'master' of https://github.com/Gustavo-net/Artigo-Esportivo.git

	@FXML
	void OnbtnCadastros(ActionEvent event) {
		Main.changeScreen("cadastros");
	}

	@FXML
	void OnbtnClientes(ActionEvent event) {
		Main.changeScreen("clientes");
	}

	public static Produtos produtoEditar = new Produtos();

	@FXML
	void OnbtnEditar(ActionEvent event) {
		if (tableRelatorioProduto.getSelectionModel().getSelectedIndex() == -1) {
			Alert mensagemDeErro = new Alert(Alert.AlertType.INFORMATION);
			mensagemDeErro.setContentText("Selecione um Produto para Editar Primeiro!");
			mensagemDeErro.show();
		} else {
			int i = tableRelatorioProduto.getSelectionModel().getSelectedIndex();
			produtoEditar = tableRelatorioProduto.getItems().get(i);
		}
		carregarTableProduto();
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
	void OnbtnInserir(ActionEvent event) {
		produtoEditor = null;
		carregarTableProduto();
	}

	@FXML
	void OnbtnPesquisar(ActionEvent event) {
		arrayProduto = FXCollections.observableArrayList(produtoDAO.search(txtPesquisar.getText()));
		atualizarTabela(arrayProduto);
		tableRelatorioProduto.refresh();
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

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		carregarTableProduto();

<<<<<<< HEAD
        ObservableList<String> categorias = FXCollections.observableArrayList(
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
        );
=======
		// Adicionando as opções ao ComboBox
		ObservableList<String> categorias = FXCollections.observableArrayList("Roupas Masculinas", "Roupas Femininas",
				"Calçados Masculinos", "Calçados Femininos", "Acessórios de Esporte", "Equipamentos de Academia",
				"Bolsas e Mochilas Esportivas", "Garrafas e Squeezes", "Suplementos e Nutrição", "Proteção e Segurança",
				"Natação", "Ciclismo", "Esportes Radicais", "Futebol", "Basquete");
>>>>>>> branch 'master' of https://github.com/Gustavo-net/Artigo-Esportivo.git

		boxFiltrar.setItems(categorias);

<<<<<<< HEAD
        boxFiltrar.setOnAction(event -> filtrarProdutos());
    }
    
    private void inicializarComboBox() {
        CategoriaDAO categoriaDAO = new CategoriaDAO();
        ArrayList<Categorias> categorias = categoriaDAO.read();
        
        for (Categorias categoria : categorias) {
            boxFiltrar.getItems().add(categoria.getNomeCategoria());
        }
    }

=======
		// Adicionando listener ao ComboBox para filtragem
		boxFiltrar.setOnAction(event -> filtrarProdutos());
	}
>>>>>>> branch 'master' of https://github.com/Gustavo-net/Artigo-Esportivo.git

<<<<<<< HEAD
    private void filtrarProdutos() {
        String categoriaSelecionada = boxFiltrar.getValue();
        if (categoriaSelecionada == null) {
            atualizarTabela(ProdutoDAO.read());
            return;
        }
        
        String idCategoria = obterIdCategoria(categoriaSelecionada);
=======
	private void filtrarProdutos() {
		String categoriaSelecionada = boxFiltrar.getValue();
		if (categoriaSelecionada == null) {
			atualizarTabela(arrayProduto);
			return;
		}
>>>>>>> branch 'master' of https://github.com/Gustavo-net/Artigo-Esportivo.git

<<<<<<< HEAD
        ArrayList<Produtos> produtosFiltrados = produtoDAO.buscarProdutosPorCategoria(idCategoria);
        atualizarTabela(FXCollections.observableArrayList(produtosFiltrados));
    }

=======
		ObservableList<Produtos> produtosFiltrados = FXCollections.observableArrayList();
		for (Categorias categorias : arrayProduto) {
			// Aqui você deve ajustar para buscar a categoria do produto através da FK
			String categoriaProduto = categorias.getIdCategoria(); // Ajuste conforme sua implementação
			if (categoriaProduto != null && categoriaProduto.equals(categoriaSelecionada)) {
				produtosFiltrados.add(produto);
			}
		}
		atualizarTabela(produtosFiltrados);
	}
>>>>>>> branch 'master' of https://github.com/Gustavo-net/Artigo-Esportivo.git
}
