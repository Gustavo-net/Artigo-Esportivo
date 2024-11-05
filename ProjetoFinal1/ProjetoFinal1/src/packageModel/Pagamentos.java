package packageModel;

public class Pagamentos {
	
	private String idPagamento;
	private String metodoPagamento;
	private double valor;
	private int parcelas;
	private String idVenda;
	private String status;

	public Pagamentos() {
		super();
	}

	public Pagamentos(String idPagamento, String metodoPagamento, double valor, int parcelas, String idVenda, String status) {
		super();
		this.idPagamento = idPagamento;
		this.metodoPagamento = metodoPagamento;
		this.valor = valor;
		this.parcelas = parcelas;
		this.idVenda = idVenda;
		this.status = status;
	}

	public String getIdPagamento() {
		return idPagamento;
	}

	public void setIdPagamento(String idPagamento) {
		this.idPagamento = idPagamento;
	}

	public String getMetodoPagamento() {
		return metodoPagamento;
	}

	public void setMetodoPagamento(String metodoPagamento) {
		this.metodoPagamento = metodoPagamento;
	}

	public double getValor() {
		return valor;
	}

	public void setValor(double valor) {
		this.valor = valor;
	}

	public int getParcelas() {
		return parcelas;
	}

	public void setParcelas(int parcelas) {
		this.parcelas = parcelas;
	}

	public String getIdVenda() {
		return idVenda;
	}

	public void setIdVenda(String idVenda) {
		this.idVenda = idVenda;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
}
