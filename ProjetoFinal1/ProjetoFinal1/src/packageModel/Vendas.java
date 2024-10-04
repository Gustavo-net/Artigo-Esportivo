package packageModel;

public class Vendas {
	
	private String idVenda;
	private String dataVenda;
	private String codigoProduto;
	private String quantidade;
	private String desconto;
	private String subtotal;
	private String valorTotal;
	private String statusVenda;
	private String canalVendas;
	private String id_Pagamento;
	private String cpf_Cliente;
	private String cpf_Funcionário;
	
	public Vendas() {
		super();
	}

	public String getIdVenda() {
		return idVenda;
	}

	public void setIdVenda(String idVenda) {
		this.idVenda = idVenda;
	}

	public String getDataVenda() {
		return dataVenda;
	}

	public void setDataVenda(String dataVenda) {
		this.dataVenda = dataVenda;
	}

	public String getCodigoProduto() {
		return codigoProduto;
	}

	public void setCodigoProduto(String codigoProduto) {
		this.codigoProduto = codigoProduto;
	}

	public String getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(String quantidade) {
		this.quantidade = quantidade;
	}

	public String getDesconto() {
		return desconto;
	}

	public void setDesconto(String desconto) {
		this.desconto = desconto;
	}

	public String getSubtotal() {
		return subtotal;
	}

	public void setSubtotal(String subtotal) {
		this.subtotal = subtotal;
	}

	public String getValorTotal() {
		return valorTotal;
	}

	public void setValorTotal(String valorTotal) {
		this.valorTotal = valorTotal;
	}

	public String getStatusVenda() {
		return statusVenda;
	}

	public void setStatusVenda(String statusVenda) {
		this.statusVenda = statusVenda;
	}

	public String getCanalVendas() {
		return canalVendas;
	}

	public void setCanalVendas(String canalVendas) {
		this.canalVendas = canalVendas;
	}

	public String getId_Pagamento() {
		return id_Pagamento;
	}

	public void setId_Pagamento(String id_Pagamento) {
		this.id_Pagamento = id_Pagamento;
	}

	public String getCpf_Cliente() {
		return cpf_Cliente;
	}

	public void setCpf_Cliente(String cpf_Cliente) {
		this.cpf_Cliente = cpf_Cliente;
	}

	public String getCpf_Funcionário() {
		return cpf_Funcionário;
	}

	public void setCpf_Funcionário(String cpf_Funcionário) {
		this.cpf_Funcionário = cpf_Funcionário;
	}

	public Vendas(String idVenda, String dataVenda, String codigoProduto, String quantidade, String desconto,
			String subtotal, String valorTotal, String statusVenda, String canalVendas, String id_Pagamento,
			String cpf_Cliente, String cpf_Funcionário) {
		super();
		this.idVenda = idVenda;
		this.dataVenda = dataVenda;
		this.codigoProduto = codigoProduto;
		this.quantidade = quantidade;
		this.desconto = desconto;
		this.subtotal = subtotal;
		this.valorTotal = valorTotal;
		this.statusVenda = statusVenda;
		this.canalVendas = canalVendas;
		this.id_Pagamento = id_Pagamento;
		this.cpf_Cliente = cpf_Cliente;
		this.cpf_Funcionário = cpf_Funcionário;
	}
	
	
			
			

}
