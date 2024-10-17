package packageController;

import java.net.URL;
import java.util.ResourceBundle;

import application.Main;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import packageModel.Funcionarios;
import package_controle.FuncionarioDAO;

public class controllerRelatorioFuncionarios implements Initializable{

    @FXML
    private TableColumn<Funcionarios, String> columnCPF;

    @FXML
    private TableColumn<Funcionarios, String> columnID;

    @FXML
    private TableColumn<Funcionarios, String> columnNome;

    @FXML
    private Button btnCadastros;

    @FXML
    private Button btnClientes;

    @FXML
    private Button btnSair;

    @FXML
    private ComboBox<String> boxFiltrar;

    @FXML
    private Button btnEditar;

    @FXML
    private Text btnFornecedores;

    @FXML
    private Button btnInserir;

    @FXML
    private Button btnPesquisar;

    @FXML
    private Button btnProdutos;

    @FXML
    private Button btnVendas;

    @FXML
    private Button btnVoltar;

    @FXML
    private TableColumn<Funcionarios, String> columnCargo;

    @FXML
    private TableColumn<Funcionarios, String> columnDataCont;

    @FXML
    private TableColumn<Funcionarios, String> columnDataNasc;

    @FXML
    private TableColumn<Funcionarios, String> columnEmail;

    @FXML
    private TableColumn<Funcionarios, String> columnSexo;

    @FXML
    private TableColumn<Funcionarios, String> columnTelefone;

    @FXML
    private TableView<Funcionarios> tableFuncionarios;
    
    private ObservableList<Funcionarios> arrayFuncionario;
    private FuncionarioDAO funcionariosDAO = new FuncionarioDAO();

    @FXML
    private TextField txtProcurarFornecedores;
    
    public static Funcionarios funcionariosEditor = new Funcionarios();
    
    public void carregarTableFuncionarios(){
        arrayFuncionario = FXCollections.observableArrayList(FuncionarioDAO.read());
        
        columnID.setCellValueFactory(new PropertyValueFactory<>("idFuncionario"));
        columnNome.setCellValueFactory(new PropertyValueFactory<>("nomeFuncionario"));
        columnCPF.setCellValueFactory(new PropertyValueFactory<>("cpf"));
        columnEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        columnTelefone.setCellValueFactory(new PropertyValueFactory<>("telefone"));
        columnDataNasc.setCellValueFactory(new PropertyValueFactory<>("dataNasc"));
        columnDataCont.setCellValueFactory(new PropertyValueFactory<>("dataCont"));
        columnCargo.setCellValueFactory(new PropertyValueFactory<>("cargo"));
        columnSexo.setCellValueFactory(new PropertyValueFactory<>("sexo"));
        
        tableFuncionarios.setItems(arrayFuncionario);
    }
    
    @FXML
    void OnBtnClientes(ActionEvent event) {
        Main.changeScreen("clientes");
    }

    @FXML
    void OnBtnFornecedores(MouseEvent event) {
        Main.changeScreen("fornecedores");
    }

    @FXML
    void OnbtnCadastros(ActionEvent event) {
        Main.changeScreen("cadastros");
    }

    @FXML
    void OnBtnEditar(ActionEvent event) {
        // Implementar edição
    }

    @FXML
    void OnBtnInserir(ActionEvent event) {
        // Implementar inserção
    }

    @FXML
    void OnBtnPesquisar(ActionEvent event) {
        // Implementar pesquisa
    }

    @FXML
    void OnBtnProdutos(ActionEvent event) {
        Main.changeScreen("produtos");
    }

    @FXML
    void OnBtnSair(ActionEvent event) {
        Main.changeScreen("login");
    }

    @FXML
    void OnBtnVendas(ActionEvent event) {
        // Implementar vendas
    }

    @FXML
    void OnBtnVoltar(ActionEvent event) {
        // Implementar voltar
    }

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		carregarTableFuncionarios();
		
	}
}
