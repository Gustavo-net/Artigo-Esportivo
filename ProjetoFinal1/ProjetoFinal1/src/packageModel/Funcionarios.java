package packageModel;

public class Funcionarios {
	private String idFuncionario;
	private String nomeFuncionario;
	private String cpf;
	private String email;
	private String telefone;
	private String dataNasc;
	private String dataCont;
	private String cargo;
	private String sexo;
	private String senha;
	private String id_Endereço;
	
	public Funcionarios() {
		super();
	}

	public String getIdFuncionario() {
		return idFuncionario;
	}

	public void setIdFuncionario(String idFuncionario) {
		this.idFuncionario = idFuncionario;
	}

	public String getNomeFuncionario() {
		return nomeFuncionario;
	}

	public void setNomeFuncionario(String nomeFuncionario) {
		this.nomeFuncionario = nomeFuncionario;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
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

	public String getDataNasc() {
		return dataNasc;
	}

	public void setDataNasc(String dataNasc) {
		this.dataNasc = dataNasc;
	}

	public String getDataCont() {
		return dataCont;
	}

	public void setDataCont(String dataCont) {
		this.dataCont = dataCont;
	}

	public String getCargo() {
		return cargo;
	}

	public void setCargo(String cargo) {
		this.cargo = cargo;
	}

	public String getSexo() {
		return sexo;
	}

	public void setSexo(String sexo) {
		this.sexo = sexo;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getId_Endereço() {
		return id_Endereço;
	}

	public void setId_Endereço(String id_Endereço) {
		this.id_Endereço = id_Endereço;
	}

	public Funcionarios(String idFuncionario, String nomeFuncionario, String cpf, String email, String telefone,
			String dataNasc, String dataCont, String cargo, String sexo, String senha, String id_Endereço) {
		super();
		this.idFuncionario = idFuncionario;
		this.nomeFuncionario = nomeFuncionario;
		this.cpf = cpf;
		this.email = email;
		this.telefone = telefone;
		this.dataNasc = dataNasc;
		this.dataCont = dataCont;
		this.cargo = cargo;
		this.sexo = sexo;
		this.senha = senha;
		this.id_Endereço = id_Endereço;
	}
	
	

}
