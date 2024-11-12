package application;

import java.io.IOException;
import java.sql.Connection;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Modality;
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
	private static Scene Vendas;


	@Override
	public void start( Stage primaryStage) {

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
			
			 Parent fxmlFornecedores = 
			 FXMLLoader.load(getClass().getResource("/packageView/viewRelatorioFornecedores.fxml"));
			 Fornecedores = new Scene(fxmlFornecedores);
		 
			 Parent fxmlVendas = 
			 FXMLLoader.load(getClass().getResource("/packageView/viewRelatorioVendas.fxml"));
			 Vendas = new Scene(fxmlVendas);
			 
				
			 stage.getIcons().add(new Image(getClass().getResourceAsStream("/packageIcons/LogoLoja.png")));
				stage.setResizable(false);

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
		} else if (tela.equals("vendas")) {
			stage.setScene(Vendas);
			stage.centerOnScreen();
		} else if (tela.equals("cadastros")) {
			stage.setScene(Cadastros);
		}
	}
	
	private static Stage cadastroProdutos;
	
	public static void TelaCadastroProduto() throws IOException {
		FXMLLoader ProdutoCadastro = new FXMLLoader();
		ProdutoCadastro.setLocation(Main.class.getResource("/packageView/viewCadastroProdutos.fxml"));
		Parent cadastrarProduto = ProdutoCadastro.load();
		Scene scene1 = new Scene(cadastrarProduto);
		
		cadastroProdutos = new Stage();
		cadastroProdutos.setTitle("Cadastro de Produtos - Artigos Esportivos");
		cadastroProdutos.initModality(Modality.WINDOW_MODAL);
		cadastroProdutos.setScene(scene1);
		cadastroProdutos.getIcons().addAll(stage.getIcons());

		cadastroProdutos.showAndWait();
		
	
	}
	
	private static Stage cadastroFornecedores;
	
	public static void TelaCadastroFornecedores() throws IOException {
		FXMLLoader FornecedoresCadastro = new FXMLLoader();
		FornecedoresCadastro.setLocation(Main.class.getResource("/packageView/viewCadastroFornecedor.fxml"));
		Parent cadastrarFornecedores = FornecedoresCadastro.load();
		Scene scene2 = new Scene(cadastrarFornecedores);
		
		cadastroFornecedores = new Stage();
		cadastroFornecedores.setTitle("Cadastro de Fornecedores - Artigos Esportivos");
		cadastroFornecedores.initModality(Modality.WINDOW_MODAL);
		cadastroFornecedores.setScene(scene2);
		cadastroProdutos.getIcons().addAll(stage.getIcons());
		cadastroFornecedores.showAndWait();
	}
	
	private static Stage cadastroClientes;
	
	public static void TelaCcadastroClientes() throws IOException {
		FXMLLoader ClientesCadastro = new FXMLLoader();
		ClientesCadastro.setLocation(Main.class.getResource("/packageView/viewCadastroClientes.fxml"));
		Parent cadastrarFornecedores = ClientesCadastro.load();
		Scene scene3 = new Scene(cadastrarFornecedores);
		
		cadastroClientes = new Stage();
		cadastroClientes.setTitle("Cadastro de Clientes - Artigos Esportivos");
		cadastroClientes.initModality(Modality.WINDOW_MODAL);
		cadastroClientes.setScene(scene3);
		cadastroProdutos.getIcons().addAll(stage.getIcons());
		cadastroClientes.showAndWait();
	}
	
	private static Stage cadastroFuncionarios;
	
	public static void TelaCadastroFuncionarios() throws IOException {
		FXMLLoader FuncionariosCadastro = new FXMLLoader();
		FuncionariosCadastro.setLocation(Main.class.getResource("/packageView/viewCadastroFuncionarios.fxml"));
		Parent cadastrarFuncionarios = FuncionariosCadastro.load();
		Scene scene3 = new Scene(cadastrarFuncionarios);
		
		cadastroFuncionarios = new Stage();
		cadastroFuncionarios.setTitle("Cadastro de Funcionarios - Artigos Esportivos");
		cadastroFuncionarios.initModality(Modality.WINDOW_MODAL);
		cadastroFuncionarios.setScene(scene3);
		cadastroProdutos.getIcons().addAll(stage.getIcons());
		cadastroFuncionarios.showAndWait();
	}
	
	private static Stage cadastroVenda;
	
	public static void TelaCadastroVenda() throws IOException {
		FXMLLoader VendaCadastro = new FXMLLoader();
		VendaCadastro.setLocation(Main.class.getResource("/packageView/viewPDV.fxml"));
		Parent cadastrarVenda = VendaCadastro.load();
		Scene scene4 = new Scene(cadastrarVenda);
		
		cadastroVenda = new Stage();
		cadastroVenda.setTitle("Ponto de Venda - Artigos Esportivos");
		cadastroVenda.initModality(Modality.WINDOW_MODAL);
		cadastroVenda.setScene(scene4);
		cadastroProdutos.getIcons().addAll(stage.getIcons());
		cadastroVenda.showAndWait();
	}
	
	
	private static Stage TableViewProdutos;
	
	public static void TelaTabelaProduto() throws IOException {
		FXMLLoader tableProduto = new FXMLLoader();
		tableProduto.setLocation(Main.class.getResource("/packageView/viewTabelaProdutos.fxml"));
		Parent ViewTabela = tableProduto.load();
		Scene scene5 = new Scene(ViewTabela);
		
		cadastroVenda = new Stage();
		cadastroVenda.setTitle("Buscar Produto - Artigos Esportivos");
		cadastroVenda.initModality(Modality.WINDOW_MODAL);
		cadastroVenda.setScene(scene5);
		cadastroProdutos.getIcons().addAll(stage.getIcons());
		cadastroVenda.showAndWait();
	}
	
	public static void main(String[] args) {
		
	@SuppressWarnings("unused")
	Connection con = ConnectionDatabase.getConnection();
		launch(args);
	}

	public static Stage getTableViewProdutos() {
		return TableViewProdutos;
	}

	public static void setTableViewProdutos(Stage tableViewProdutos) {
		TableViewProdutos = tableViewProdutos;
	}

}