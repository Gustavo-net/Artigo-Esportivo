package packageModel;

public class Pagamentos {
	
	private String idPagamento;
	private String metodoPagamento;
	private String statusPagamento;
	private String dataPagamento;
	
	public Pagamentos() {
		super();
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

	public String getStatusPagamento() {
		return statusPagamento;
	}

	public void setStatusPagamento(String statusPagamento) {
		this.statusPagamento = statusPagamento;
	}

	public String getDataPagamento() {
		return dataPagamento;
	}

	public void setDataPagamento(String dataPagamento) {
		this.dataPagamento = dataPagamento;
	}

	public Pagamentos(String idPagamento, String metodoPagamento, String statusPagamento, String dataPagamento) {
		super();
		this.idPagamento = idPagamento;
		this.metodoPagamento = metodoPagamento;
		this.statusPagamento = statusPagamento;
		this.dataPagamento = dataPagamento;
	}
	
	

}
