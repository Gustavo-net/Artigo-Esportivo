package packageModel;

public class Variação_Produtos {
	
	private String idVariação;
	private String tamanho;
	private String cor;
	private String material;
	
	public Variação_Produtos() {
		super();
		
	}

	public String getIdVariação() {
		return idVariação;
	}

	public void setIdVariação(String idVariação) {
		this.idVariação = idVariação;
	}

	public String getTamanho() {
		return tamanho;
	}

	public void setTamanho(String tamanho) {
		this.tamanho = tamanho;
	}

	public String getCor() {
		return cor;
	}

	public void setCor(String cor) {
		this.cor = cor;
	}

	public String getMaterial() {
		return material;
	}

	public void setMaterial(String material) {
		this.material = material;
	}

	public Variação_Produtos(String idVariação, String tamanho, String cor, String material) {
		super();
		this.idVariação = idVariação;
		this.tamanho = tamanho;
		this.cor = cor;
		this.material = material;
	}
	
	

}
