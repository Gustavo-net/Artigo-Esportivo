package packageModel;

public class Compras {
	
	private String idCompra;
	private String id_Fornecedor;
	private String id_Funcionario;
	private String quantidade;
	private String precoTotal;
	
	public Compras() {
		super();
	}

	public String getIdCompra() {
		return idCompra;
	}

	public void setIdCompra(String idCompra) {
		this.idCompra = idCompra;
	}

	public String getId_Fornecedor() {
		return id_Fornecedor;
	}

	public void setId_Fornecedor(String id_Fornecedor) {
		this.id_Fornecedor = id_Fornecedor;
	}

	public String getId_Funcionario() {
		return id_Funcionario;
	}

	public void setId_Funcionario(String id_Funcionario) {
		this.id_Funcionario = id_Funcionario;
	}

	public String getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(String quantidade) {
		this.quantidade = quantidade;
	}

	public String getPrecoTotal() {
		return precoTotal;
	}

	public void setPrecoTotal(String precoTotal) {
		this.precoTotal = precoTotal;
	}

	public Compras(String idCompra, String id_Fornecedor, String id_Funcionario, String quantidade, String precoTotal) {
		super();
		this.idCompra = idCompra;
		this.id_Fornecedor = id_Fornecedor;
		this.id_Funcionario = id_Funcionario;
		this.quantidade = quantidade;
		this.precoTotal = precoTotal;
	}
	

}
