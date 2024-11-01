package packageModel;

public class Clientes {
	
	private String idCliente;
	private String nomeCliente;
	private String cpf;
	private String dataNasc;
	private String id_Endereço;
	private String email;
	private String telefone;
	private String programaFidelidade;
	private String pontosFidelidade;
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
	
	public Clientes () {
		super();
		
	}
	
	public String getDataNasc() {
		return dataNasc;
	}

	public void setDataNasc(String dataNasc) {
		this.dataNasc = dataNasc;
	}

	public String getIdCliente() {
		return idCliente;
	}

	public void setIdCliente(String idCliente) {
		this.idCliente = idCliente;
	}

	public String getNomeCliente() {
		return nomeCliente;
	}

	public void setNomeCliente(String nomeCliente) {
		this.nomeCliente = nomeCliente;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getId_Endereço() {
		return id_Endereço;
	}

	public void setId_Endereço(String id_Endereço) {
		this.id_Endereço = id_Endereço;
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

	public String getProgramaFidelidade() {
		return programaFidelidade;
	}

	public void setProgramaFidelidade(String programaFidelidade) {
		this.programaFidelidade = programaFidelidade;
	}

	public String getPontosFidelidade() {
		return pontosFidelidade;
	}

	public void setPontosFidelidade(String pontosFidelidade) {
		this.pontosFidelidade = pontosFidelidade;
	}

	public Clientes(String idCliente, String nomeCliente, String cpf, String id_Endereço, String email, String telefone,
			String programaFidelidade, String pontosFidelidade, String dataNasc) {
		super();
		this.idCliente = idCliente;
		this.nomeCliente = nomeCliente;
		this.cpf = cpf;
		this.id_Endereço = id_Endereço;
		this.email = email;
		this.telefone = telefone;
		this.programaFidelidade = programaFidelidade;
		this.pontosFidelidade = pontosFidelidade;
		this.dataNasc = dataNasc;
		
	}



	public Clientes(String dataNasc) {
		super();
		this.dataNasc = dataNasc;
	}
	

}
