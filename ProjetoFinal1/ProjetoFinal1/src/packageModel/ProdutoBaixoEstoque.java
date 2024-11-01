package packageModel;

import java.util.ArrayList;

public class ProdutoBaixoEstoque {
    
    private String nome;
    private String codigo;
    private String marca;
    private String categoria;    
    private int quantidadeEstoque;  

    public ProdutoBaixoEstoque(String nome, String codigo, String marca, String categoria, int quantidadeEstoque) {
        this.nome = nome;
        this.codigo = codigo;
        this.marca = marca; 
        
        this.categoria = categoria;
        this.quantidadeEstoque = quantidadeEstoque;
    }

    public ProdutoBaixoEstoque() {
    	super();
    }

    // Getters e Setters
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

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public int getQuantidadeEstoque() { // Alterado para int
        return quantidadeEstoque;
    }

    public void setQuantidadeEstoque(int quantidadeEstoque) { // Alterado para int
        this.quantidadeEstoque = quantidadeEstoque;
    }

    public static ArrayList<ProdutoBaixoEstoque> read() {
        return new ArrayList<>(); // Retorna uma lista vazia por enquanto
    }
}
