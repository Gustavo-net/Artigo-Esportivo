package packageController;

import java.io.IOException;

import org.w3c.dom.Text;

import application.Main;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import packageModel.Produtos;
import package_controle.ProdutoDAO;

public class controllerProduto {

	@FXML
	private Text idBtnFornecedor;

	@FXML
	private Text IdButtonCliente;

	@FXML
	private Text idButtonVendedor;

	@FXML
	private Text btnButton;

	@FXML
	private Button btnDeletar;

	@FXML
	private Button btnCadastrar;

	@FXML
	private Button btnEditar;

	@FXML
	private Button btnSalvar;

	@FXML
	private Button Pesquisar;

	@FXML
	private Button Voltar;

	@FXML
	private TableColumn<Produtos, Integer> columnCodigo;

	@FXML
	private TableColumn<Produtos, String> columnNome;

	@FXML
	private TableColumn<Produtos, String> columnData_fab;

	@FXML
	private TableColumn<Produtos, String> columnData_val;

	@FXML
	private TableColumn<Produtos, Integer> columnEstoque;

	@FXML
	private TableColumn<Produtos, Integer> columnID;

	@FXML
	private TableColumn<Produtos, Double> columnPreço_Un;

	@FXML
	private TableColumn<Produtos, String> columnTipo_un;

	@FXML
	private TableView<Produtos> tableProduto;

	@FXML
	private TextField labelPesquisar;

	private ObservableList<Produtos> arrayProduto;
	private static ProdutoDAO produtoDAO = new ProdutoDAO();

	@FXML
	    void btnCadastrarProduto(ActionEvent event) throws IOException{
	    	produtoEditor = null;
	    	Main.telaCadastrarProdutos;
	    }

	public static Produtos produtoEditor = new Produtos();

	@FXML 
	    void btnEditarProdutos(ActionEvent event)throws IOException{
	    	if(tableProdutos.getSelectionMode().getSelectedIndex)
	    	
	    }

}
