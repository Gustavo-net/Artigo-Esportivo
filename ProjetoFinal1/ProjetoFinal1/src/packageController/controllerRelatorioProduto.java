package packageController;

import application.Main;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import packageModel.Compras;
import packageModel.Vendas;
import package_controle.VendaDAO;

public class controllerRelatorioProduto {
	
	 @FXML
	    private Label IDbuttonProduto;

	    @FXML
	    private Label IDbuttonVendedor;

	    @FXML
	    private Label IDbuttonVendedor1;

	    @FXML
	    private Button btnPesquisar;

	    @FXML
	    private Button btnVoltar;

	    @FXML
	    private TableColumn<Vendas, String> columnCliente;

	    @FXML
	    private TableColumn<Vendas, String> columnID;

	    @FXML
	    private TableColumn<Vendas, String> columnPrecoTotal;

	    @FXML
	    private TableColumn<Vendas, String> columnProduto;

	    @FXML
	    private TableColumn<Vendas, String> columnQuantidade;

	    @FXML
	    private TableColumn<Vendas, String> columnVendedor;

	    @FXML
	    private TableView<Vendas> tableRelatorio;
	    
	    private ObservableList<Vendas> ArrayVendas;
	    private static VendaDAO Vendas = new VendaDAO();

	    @FXML
	    private TextField textFieldPesquisar;

	    @FXML 
	    void OnBtnPesquisarRelatorio(ActionEvent event) {
	    	ArrayVendas = FXCollections.observableArrayList(Vendas.search(textFieldPesquisar.getText()));
			columnID.setCellValueFactory(new PropertyValueFactory<>("id_compra"));
			
			tableRelatorio.setItems(ArrayVendas);
			tableRelatorio.refresh();
	    }

	    @FXML
	    void OnVoltarTelaMain(ActionEvent event) {
	    	Main.changeScreen("main");
	    }

	    @FXML
	    void ViewButtonProduto(MouseEvent event) {
	    	Main.changeScreen("Produto");
	    }

	    @FXML
	    void ViewButtonVendedor(MouseEvent event) {
	    	Main.changeScreen("Vendedor");
	    }
	    
	    @FXML
	    void ViewButtonFornecedor(MouseEvent event) {
			Main.changeScreen("Fornecedor");
	    }
	    
	    @FXML
	    void ViewButtonRegistrarVenda(MouseEvent event) {
			Main.changeScreen("RegistrarVenda");
	    }
	    
	    @FXML
	    void ViewButtonTelaRelatorio(MouseEvent event) {
			Main.changeScreen("TelaRelatorio");
	    }
	    
	    @FXML
	    void ViewButtonCliente(MouseEvent event) {
	    	Main.changeScreen("Cliente");
	    }

}
