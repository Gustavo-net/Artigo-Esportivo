package packageController;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import package_controle.FornecedoresDAO;
import packageModel.Fornecedores;

public class controllerFornecedor {
	
	@FXML
    private Label IDbuttonCliente;

    @FXML
    private Label IDbuttonVendedor;
    
    @FXML
    private Label IDbuttonProduto;
    
    @FXML
    private Label IDbuttonRegistrarVenda;

    @FXML
    private Label IDbuttonRelatorio;

    @FXML
    private Button btnCadastrar;

    @FXML
    private Button btnEditar;

    @FXML
    private Button btnExcluir;

    @FXML
    private Button btnPesquisar;

    @FXML
    private Button btnSalvar;

    @FXML
    private Button btnVoltar;

    @FXML
    private TableColumn<Fornecedores, String> columnCNPJ;

    @FXML
    private TableColumn<Fornecedores, String> columnEmail;

    @FXML
    private TableColumn<Fornecedores, String> columnEndereco;

    @FXML
    private TableColumn<Fornecedores, String> columnID;

    @FXML
    private TableColumn<Fornecedores, String> columnNome;

    @FXML
    private TableColumn<Fornecedores, String> columnTelefone;

    @FXML
    private TableView<Fornecedores> tableFornecedor;
    
    private ObservableList<Fornecedores> ArrayFornecedor;
    private static FornecedoresDAO Fornecedor = new FornecedoresDAO();

    @FXML
    private TextField textFieldPesquisar;

}
