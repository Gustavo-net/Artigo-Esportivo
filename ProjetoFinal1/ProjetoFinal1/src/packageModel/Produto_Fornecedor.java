package packageModel;

public class Produto_Fornecedor {
	
	private String idProduto_fornecedor;
	private String id_produto;
	private String id_fornecedor;
	
	public Produto_Fornecedor() {
		super();
	}

	public String getIdProduto_fornecedor() {
		return idProduto_fornecedor;
	}

	public void setIdProduto_fornecedor(String idProduto_fornecedor) {
		this.idProduto_fornecedor = idProduto_fornecedor;
	}

	public String getId_produto() {
		return id_produto;
	}

	public void setId_produto(String id_produto) {
		this.id_produto = id_produto;
	}

	public String getId_fornecedor() {
		return id_fornecedor;
	}

	public void setId_fornecedor(String id_fornecedor) {
		this.id_fornecedor = id_fornecedor;
	}

	public Produto_Fornecedor(String idProduto_fornecedor, String id_produto, String id_fornecedor) {
		super();
		this.idProduto_fornecedor = idProduto_fornecedor;
		this.id_produto = id_produto;
		this.id_fornecedor = id_fornecedor;
	}
	
	

}
