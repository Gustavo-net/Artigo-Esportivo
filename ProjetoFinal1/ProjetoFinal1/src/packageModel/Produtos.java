package packageModel;

public class Produtos {
    private String idProduto;
    private String nome;
    private String codigo;
    private String marca;
    private String descricao;
    private Double precoUnitario;
    private Integer estoqueDisp;
    private String id_Categoria;
    private String categoriaNome; 

    // Getters e Setters

    public String getCategoriaNome() {
        return categoriaNome;
    }

    public void setCategoriaNome(String categoriaNome) {
        this.categoriaNome = categoriaNome;
    }
    
    
    public String getIdProduto() {
        return idProduto;
    }
    

	public void setId_Categoria(String id_Categoria) {
		this.id_Categoria = id_Categoria;
	}

	 public String getId_Categoria() {
	        return id_Categoria;
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

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Double getPrecoUnitario() {
        return precoUnitario;
    }

    public void setPrecoUnitario(Double precoUnitario) {
        this.precoUnitario = precoUnitario;
    }

    public Integer getEstoqueDisp() {
        return estoqueDisp;
    }

    public void setEstoqueDisp(Integer estoqueDisp) {
        this.estoqueDisp = estoqueDisp;
    }
}
