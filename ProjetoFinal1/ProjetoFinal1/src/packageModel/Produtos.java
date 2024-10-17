package packageModel;

public class Produtos {
	
	private String idProduto;
	private String nome;
	private String codigo;
	private String marca;
	private String descrição;
	private String preçoUnitario;
	private String estoqueDisp;
	private String estoqueMin;
	private String estoqueMax;
	private String idCategoria;
	
	
	public Produtos() {
		super ();
	}


	public String getIdProduto() {
		return idProduto;
	}


	public void setIdProduto(String idProduto) {
		this.idProduto = idProduto;
	}


	public String getNome() {
		return nome;
	}


	public void setNome(String nome) {
		this.nome = nome;
	}


	public String getCodigo() {
		return codigo;
	}


	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}


	public String getMarca() {
		return marca;
	}


	public void setMarca(String marca) {
		this.marca = marca;
	}


	public String getDescrição() {
		return descrição;
	}


	public void setDescrição(String descrição) {
		this.descrição = descrição;
	}


	public String getPreçoUnitario() {
		return preçoUnitario;
	}


	public void setPreçoUnitario(String preçoUnitario) {
		this.preçoUnitario = preçoUnitario;
	}


	public String getEstoqueDisp() {
		return estoqueDisp;
	}


	public void setEstoqueDisp(String estoqueDisp) {
		this.estoqueDisp = estoqueDisp;
	}


	public String getEstoqueMin() {
		return estoqueMin;
	}


	public void setEstoqueMin(String estoqueMin) {
		this.estoqueMin = estoqueMin;
	}


	public String getEstoqueMax() {
		return estoqueMax;
	}


	public void setEstoqueMax(String estoqueMax) {
		this.estoqueMax = estoqueMax;
	}


	public String getIdCategoria() {
		return idCategoria;
	}


	public void setIdCategoria(String idCategoria) {
		this.idCategoria = idCategoria;
	}



	public Produtos(String idProduto, String nome, String codigo, String marca, String descrição, String preçoUnitario,
			String estoqueDisp, String estoqueMin, String estoqueMax, String idCategoria) {
		super();
		this.idProduto = idProduto;
		this.nome = nome;
		this.codigo = codigo;
		this.marca = marca;
		this.descrição = descrição;
		this.preçoUnitario = preçoUnitario;
		this.estoqueDisp = estoqueDisp;
		this.estoqueMin = estoqueMin;
		this.estoqueMax = estoqueMax;
		this.idCategoria = idCategoria;
	}
	
	

}
