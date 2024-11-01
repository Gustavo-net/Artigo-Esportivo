package packageController;

import java.io.IOException;
import java.net.URL;
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
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import packageModel.Funcionarios;
import package_controle.ClienteDAO;
import package_controle.FuncionarioDAO;

public class controllerRelatorioFuncionarios implements Initializable {

	@FXML
	private Button bntCadastros;
	
	@FXML
	private ImageView lupaPesquisar;

	@FXML
	private Button btnClientes;

	@FXML
	private Button bntSair;

	@FXML
	private ComboBox<Funcionarios> boxFiltrar;

	@FXML
	private Button btnEditar;

	@FXML
	private Button btnInserir;

	@FXML
	private Button btnProdutos;

	@FXML
	private Button btnVendas;

	@FXML
	private Button btnVoltar;

    @FXML
    private TableColumn<Funcionarios, String> columnCEP;

    @FXML
    private TableColumn<Funcionarios, String> columnCPF;

    @FXML
    private TableColumn<Funcionarios, String> columnCargo;

    @FXML
    private TableView<Funcionarios> tableFuncionarios;

    @FXML
    private TableColumn<Funcionarios, String> columnDataCont;

    @FXML
    private TableColumn<Funcionarios, String> columnDataNasc;

    @FXML
    private TableColumn<Funcionarios, String> columnEmail;

    @FXML
    private TableColumn<Funcionarios, String> columnNome;

    @FXML
    private TableColumn<Funcionarios, String> columnSexo;

    @FXML
    private TableColumn<Funcionarios, String> columnTelefone;
    @FXML
    private TableColumn<Funcionarios, String> columnRua;
    @FXML
    private TableColumn<Funcionarios, String> columnBairro;
    @FXML
    private TableColumn<Funcionarios, String> columnCidadeUF;

	private ObservableList<Funcionarios> arrayFuncionario;
	private FuncionarioDAO funcionariosDAO = new FuncionarioDAO();

	@FXML
	private TextField txtPesquisar;

	@SuppressWarnings("exports")
	public static Funcionarios funcionariosEditor = new Funcionarios();

	public void carregarTableFuncionarios() {
		arrayFuncionario = FXCollections.observableArrayList(FuncionarioDAO.read());

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
	void OnbtnClientes(ActionEvent event) {
		Main.changeScreen("clientes");
	}

	@FXML
	void OnbtnFornecedores(ActionEvent event) {
		Main.changeScreen("fornecedores");
	}

	@FXML
	void OnbtnCadastros(ActionEvent event) {
		Main.changeScreen("cadastros");
	}

	@FXML
	void OnbtnEditar(ActionEvent event) {
		if (tableFuncionarios.getSelectionModel().getSelectedIndex() == -1) {
            showAlert("Selecione um Cliente para Editar Primeiro!");
        } else {
            int i = tableFuncionarios.getSelectionModel().getSelectedIndex();
            funcionariosEditor = tableFuncionarios.getItems().get(i);
            
            try {
                Main.TelaCcadastroClientes();
            } catch (IOException e) {
                e.printStackTrace();
                showAlert("Erro ao abrir a tela de edição!");
            }
        }
	}

	@FXML
	void OnbtnInserir(ActionEvent event) throws IOException {
		funcionariosEditor = null;
		Main.TelaCadastroFuncionarios();
	}

	public void OnbtnVendas(ActionEvent event) {
	   
	}
	public void OnbtnSair(ActionEvent event) {
		   Main.changeScreen("login");
	}
	public void OnbtnExcluir(ActionEvent event) {
		   
	}
	public void OnbtnFuncionarios(ActionEvent event) {
		   
	}
	@FXML
	void OnPesquisarImagem(MouseEvent event) {
		String pesquisa = txtPesquisar.getText().trim();
        if (pesquisa.isEmpty()) {
            carregarTableFuncionarios();
        } else {
            arrayFuncionario = FXCollections.observableArrayList(FuncionarioDAO.search(pesquisa));
            tableFuncionarios.setItems(arrayFuncionario);
            tableFuncionarios.refresh();
        }

	}

	@FXML
	void OnbtnProdutos(ActionEvent event) {
		Main.changeScreen("produtos");
	}

	@FXML
	void OnBtnSair(ActionEvent event) {
		Main.changeScreen("login");
	}

	@FXML
	void OnBtnVendas(ActionEvent event) {
		
	}

	@FXML
	void OnbtnVoltar(ActionEvent event) {

	}
    private void showAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setContentText(message);
        alert.showAndWait();
    }
    
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		carregarTableFuncionarios();
		columnCEP.setCellValueFactory(new PropertyValueFactory<>("cep"));
		 columnRua.setCellValueFactory(new PropertyValueFactory<>("rua"));
		    columnBairro.setCellValueFactory(new PropertyValueFactory<>("bairro"));
		    columnCidadeUF.setCellValueFactory(new PropertyValueFactory<>("cidadeUF"));
	}

}
