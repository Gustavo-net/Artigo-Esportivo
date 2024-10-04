package packageModel;

public class Enderecos {
	
	private String idEndereço;
	private String cep;
	private String rua;
	private String numero;
	private String bairro;
	private String complemento;
	private String cidadeUF;

	
	public Enderecos () {
		super();
	}


	public String getIdEndereço() {
		return idEndereço;
	}


	public void setIdEndereço(String idEndereço) {
		this.idEndereço = idEndereço;
	}


	public String getCep() {
		return cep;
	}


	public void setCep(String cep) {
		this.cep = cep;
	}


	public String getRua() {
		return rua;
	}


	public void setRua(String rua) {
		this.rua = rua;
	}


	public String getNumero() {
		return numero;
	}


	public void setNumero(String numero) {
		this.numero = numero;
	}


	public String getBairro() {
		return bairro;
	}


	public void setBairro(String bairro) {
		this.bairro = bairro;
	}


	public String getComplemento() {
		return complemento;
	}


	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}


	public String getCidadeUF() {
		return cidadeUF;
	}


	public void setCidadeUF(String cidadeUF) {
		this.cidadeUF = cidadeUF;
	}


	public Enderecos(String idEndereço, String cep, String rua, String numero, String bairro, String complemento,
			String cidadeUF) {
		super();
		this.idEndereço = idEndereço;
		this.cep = cep;
		this.rua = rua;
		this.numero = numero;
		this.bairro = bairro;
		this.complemento = complemento;
		this.cidadeUF = cidadeUF;
	}
	
	
	
}
