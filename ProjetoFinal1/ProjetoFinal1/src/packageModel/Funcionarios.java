package packageModel;

import java.time.LocalDate;

public class Funcionarios {
	private String idFuncionario;
	private String nomeFuncionario;
	private String cpf;
	private String email;
	private String telefone;
	private LocalDate dataNasc;
	private LocalDate dataCont;
	private String cargo;
	private String sexo;
	private String senha;
	private String id_Endereço;
	 private String rua;
	    private String numero;
	    private String bairro;
	    private String complemento;
	    private String cidadeUF;

	    public String getRua() {
	        return rua;
	    }

	    public void setRua(String rua) {
	        this.rua = rua;
	    }
	    private String cep;

	    public String getCep() {
	        return cep;
	    }

	    public void setCep(String cep) {
	        this.cep = cep;
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

	public LocalDate getDataNasc() {
		return dataNasc;
	}

	public void setDataNasc(LocalDate string) {
		this.dataNasc = string;
	}

	public LocalDate getDataCont() {
		return dataCont;
	}

	public void setDataCont(LocalDate localDate) {
		this.dataCont = localDate;
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
			LocalDate dataNasc, LocalDate dataCont, String cargo, String sexo, String senha, String id_Endereço) {
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

	public void setEndereco(String text) {
		
		
	}
	
	

}
