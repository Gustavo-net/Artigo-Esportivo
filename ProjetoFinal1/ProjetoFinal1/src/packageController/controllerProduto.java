package packageController;


import java.io.IOException;

import org.w3c.dom.Text;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
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
	    private  TableColumn<Produto, Integer> columnCodigo;

	    @FXML
	    private  TableColumn<Produto, String> columnNome;

	    @FXML
	    private  TableColumn<Produto, String> columnData_fab;

	    @FXML
	    private  TableColumn<Produto, String> columnData_val;

	    @FXML
	    private  TableColumn<Produto, Integer> columnEstoque;

	    @FXML
	    private  TableColumn<Produto, Integer> columnID;

	    @FXML
	    private  TableColumn<Produto, Double> columnPreço_Un;

	    @FXML
	    private  TableColumn<Produto, String> columnTipo_un;

	    @FXML
	    private  TableView<Produto> tableProduto;
	    
	    @FXML
	    private  TextField labelPesquisar;

	    private  ObservableList<Produto> arrayProduto;
	    private static ProdutoDAO produtoDAO = new ProdutoDAO();
	    
	    @FXML
	    void btnCadastrarProduto(ActionEvent event) throws IOException{
	    	produtoEditor = null;
	    	Main.telaCadastrarProduto;
	    }
	    public static Produto produtoEditor = new Produto();
}
