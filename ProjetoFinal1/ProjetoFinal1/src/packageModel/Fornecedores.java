package packageModel;

public class Fornecedores {
	
	private String idFornecedor;
	private String nomeFornecedor;
	private String cnpj;
	private String email;
	private String telefone;
	private String id_Endereço;
	
	public Fornecedores() {
		super();
	}

	public String getIdFornecedor() {
		return idFornecedor;
	}

	public void setIdFornecedor(String idFornecedor) {
		this.idFornecedor = idFornecedor;
	}

	public String getNomeFornecedor() {
		return nomeFornecedor;
	}

	public void setNomeFornecedor(String nomeFornecedor) {
		this.nomeFornecedor = nomeFornecedor;
	}

	public String getCnpj() {
		return cnpj;
	}

	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getId_Endereço() {
		return id_Endereço;
	}

	public void setId_Endereço(String id_Endereço) {
		this.id_Endereço = id_Endereço;
	}

	public Fornecedores(String idFornecedor, String nomeFornecedor, String cnpj, String email, String telefone,
			String id_Endereço) {
		super();
		this.idFornecedor = idFornecedor;
		this.nomeFornecedor = nomeFornecedor;
		this.cnpj = cnpj;
		this.email = email;
		this.telefone = telefone;
		this.id_Endereço = id_Endereço;
	}
	
	
			

}
