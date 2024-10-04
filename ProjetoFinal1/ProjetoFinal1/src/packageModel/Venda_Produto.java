package packageModel;

public class Venda_Produto {
	
	private String idVenda_Produto;
	private String id_Venda;
	private String id_Produto;
	private String quantidade;
	
	public Venda_Produto() {
		super();
	}

	public String getIdVenda_Produto() {
		return idVenda_Produto;
	}

	public void setIdVenda_Produto(String idVenda_Produto) {
		this.idVenda_Produto = idVenda_Produto;
	}

	public String getId_Venda() {
		return id_Venda;
	}

	public void setId_Venda(String id_Venda) {
		this.id_Venda = id_Venda;
	}

	public String getId_Produto() {
		return id_Produto;
	}

	public void setId_Produto(String id_Produto) {
		this.id_Produto = id_Produto;
	}

	public String getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(String quantidade) {
		this.quantidade = quantidade;
	}

	public Venda_Produto(String idVenda_Produto, String id_Venda, String id_Produto, String quantidade) {
		super();
		this.idVenda_Produto = idVenda_Produto;
		this.id_Venda = id_Venda;
		this.id_Produto = id_Produto;
		this.quantidade = quantidade;
	}
	
	

}
