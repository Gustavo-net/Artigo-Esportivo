package application;

import java.sql.Connection;

import javafx.application.Application;
import javafx.stage.Stage;
import packageConnection.ConnectionDatabase;

public class Main extends Application {

	@Override
	public void start(Stage primaryStage) {

		try {
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public static void main(String[] args) {
//
		Connection con = ConnectionDatabase.getConnection();
//		ConnectionDatabase.closeConnection(con);
//		ArrayList<Cliente> cliente = new ArrayList<>();
//		ClienteDAO cl = new ClienteDAO();
//		Cliente c = new Cliente();
//
//		c.setNome("Pedro");
//		c.setCPF_CNPJ("12345698709");
//		c.setDataNasc("1987-04-03");
//		c.setDataPrimCom("2024-02-23");
//		c.setEmail("pedro@teste.com");
//		c.setEndereco("Rua tal, numero tal");
//		c.setTipoJurd("PF");
//		c.setTelefone("6399205-9085");
//
//		ClienteDAO clt = new ClienteDAO();
//
//		clt.update(c);
//
//		cliente = cl.read();
//
//		for (int i = 0; i < cliente.size(); i++) {
//			Cliente clte = cliente.get(i);
//			System.out.println();
//			System.out.print(clte.getIdCliente());
//			System.out.print(clte.getNome());
//			System.out.print(clte.getCPF_CNPJ());
//			System.out.print(clte.getEmail());
//			System.out.print(clte.getTelefone());
//			System.out.print(clte.getDataNasc());
//			System.out.print(clte.getDataPrimCom());
//			System.out.print(clte.getEndereco());
//			System.out.print(clte.getTipoJurd());
//		}
//		

		launch(args);

	}

}