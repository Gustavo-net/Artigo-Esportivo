package application;

import java.sql.Connection;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import packageConnection.ConnectionDatabase;

public class Main extends Application {

	private static Stage stage;
	private static Scene Login;
	private static Scene Main;
	private static Scene Funcionarios;
	private static Scene Clientes;
	private static Scene Produtos;
	private static Scene Fornecedores;
	private static Scene Cadastros;

	@Override
	public void start(Stage primaryStage) {

		try {

			stage = primaryStage;

			primaryStage.setTitle("Artigos Esportivos");

			Parent fxmlLogin = FXMLLoader.load(getClass().getResource("/packageView/viewLogin.fxml"));
			Login = new Scene(fxmlLogin);

			Parent fxmlMain = FXMLLoader.load(getClass().getResource("/packageView/viewMain.fxml"));
			Main = new Scene(fxmlMain);

			 Parent fxmlFuncionarios =
			 FXMLLoader.load(getClass().getResource("/packageView/viewRelatorioFuncionarios.fxml"));
			 Funcionarios = new Scene(fxmlFuncionarios);

			 Parent fxmlCliente =
			 FXMLLoader.load(getClass().getResource("/packageView/viewRelatorioClientes.fxml"));
			 Clientes = new Scene(fxmlCliente);

			 Parent fxmlProduto =
			 FXMLLoader.load(getClass().getResource("/packageView/viewRelatorioProdutos.fxml"));
			 Produtos = new Scene(fxmlProduto);
			
			Parent fxmlFornecedor = FXMLLoader
					.load(getClass().getResource("/packageView/viewRelatorioFornecedores.fxml"));
			Fornecedores = new Scene(fxmlFornecedor);

			// Parent fxmlRegistrarVenda = FXMLLoader
			// .load(getClass().getResource("/packageView/viewRegistrarVendas.fxml"));
			// RegistrarVendas = new Scene(fxmlRegistrarVenda);
			//
			// Parent fxmlTelaRelatorio =
			// FXMLLoader.load(getClass().getResource("/packageView/viewTelaRelatorios.fxml"));
			// TelaRelatorios = new Scene(fxmlTelaRelatorio);

			primaryStage.setScene(Login);
			primaryStage.show();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void changeScreen(String tela) {
		if (tela.equals("login")) {
			stage.setScene(Login);
			stage.centerOnScreen();
		} else if (tela.equals("main")) {
			stage.setScene(Main);
			stage.centerOnScreen();
		} else if (tela.equals("funcionarios")) {
			stage.setScene(Funcionarios);
			stage.centerOnScreen();
		} else if (tela.equals("clientes")) {
			stage.setScene(Clientes);
			stage.centerOnScreen();
		} else if (tela.equals("produtos")) {
			stage.setScene(Produtos);
			stage.centerOnScreen();
		} else if (tela.equals("fornecedores")) {
			stage.setScene(Fornecedores);
			stage.centerOnScreen();
		} else if (tela.equals("cadastros")) {
			stage.setScene(Cadastros);
		}
		

	}

	public static void main(String[] args) {
//
		Connection con = ConnectionDatabase.getConnection();
// ConnectionDatabase.closeConnection(con);
// ArrayList<Cliente> cliente = new ArrayList<>();
// ClienteDAO cl = new ClienteDAO();
// Cliente c = new Cliente();
//
// c.setNome("Pedro");
// c.setCPF_CNPJ("12345698709");
// c.setDataNasc("1987-04-03");
// c.setDataPrimCom("2024-02-23");
// c.setEmail("pedro@teste.com");
// c.setEndereco("Rua tal, numero tal");
// c.setTipoJurd("PF");
// c.setTelefone("6399205-9085");
//
// ClienteDAO clt = new ClienteDAO();
//
// clt.update(c);
//
// cliente = cl.read();
//
// for (int i = 0; i < cliente.size(); i++) {
// Cliente clte = cliente.get(i);
// System.out.println();
// System.out.print(clte.getIdCliente());
// System.out.print(clte.getNome());
// System.out.print(clte.getCPF_CNPJ());
// System.out.print(clte.getEmail());
// System.out.print(clte.getTelefone());
// System.out.print(clte.getDataNasc());
// System.out.print(clte.getDataPrimCom());
// System.out.print(clte.getEndereco());
// System.out.print(clte.getTipoJurd());
// }
//
		launch(args);

	}

}