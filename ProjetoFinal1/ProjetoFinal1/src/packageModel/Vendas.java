package packageModel;

public class Vendas {

    private String idVenda;
    private String dataVenda;
    private String codigoProduto;
    private int quantidade;   // Alterado para int
    private double desconto;  // Alterado para double
    private double subtotal;  // Alterado para double
    private double valorTotal;  // Alterado para double
    private String statusVenda;
    private String canalVendas;
    private String id_Pagamento;
    private String cpf_Cliente;
    private String cpf_Funcionário;

    public Vendas() {
        super();
    }

    public String getIdVenda() {
        return idVenda;
    }

    public void setIdVenda(String idVenda) {
        this.idVenda = idVenda;
    }

    public String getDataVenda() {
        return dataVenda;
    }

    public void setDataVenda(String dataVenda) {
        this.dataVenda = dataVenda;
    }

    public String getCodigoProduto() {
        return codigoProduto;
    }

    public void setCodigoProduto(String codigoProduto) {
        this.codigoProduto = codigoProduto;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public double getDesconto() {
        return desconto;
    }

    public void setDesconto(double desconto) {
        this.desconto = desconto;
    }

    public double getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(double subtotal) {
        this.subtotal = subtotal;
    }

    public double getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(double valorTotal) {
        this.valorTotal = valorTotal;
    }

    public String getStatusVenda() {
        return statusVenda;
    }

    public void setStatusVenda(String statusVenda) {
        this.statusVenda = statusVenda;
    }

    public String getCanalVendas() {
        return canalVendas;
    }

    public void setCanalVendas(String canalVendas) {
        this.canalVendas = canalVendas;
    }

    public String getId_Pagamento() {
        return id_Pagamento;
    }

    public void setId_Pagamento(String id_Pagamento) {
        this.id_Pagamento = id_Pagamento;
    }

    public String getCpf_Cliente() {
        return cpf_Cliente;
    }

    public void setCpf_Cliente(String cpf_Cliente) {
        this.cpf_Cliente = cpf_Cliente;
    }

    public String getCpf_Funcionário() {
        return cpf_Funcionário;
    }

    public void setCpf_Funcionário(String cpf_Funcionário) {
        this.cpf_Funcionário = cpf_Funcionário;
    }

    public void calcularSubtotal(double valorUnitario) {
        this.subtotal = (this.quantidade * valorUnitario) - this.desconto;
    }

    public void calcularValorTotal() {
        
        this.valorTotal = this.subtotal; 
    }

    public Vendas(String idVenda, String dataVenda, String codigoProduto, int quantidade, double desconto,
                  double subtotal, double valorTotal, String statusVenda, String canalVendas, String id_Pagamento,
                  String cpf_Cliente, String cpf_Funcionário) {
        super();
        this.idVenda = idVenda;
        this.dataVenda = dataVenda;
        this.codigoProduto = codigoProduto;
        this.quantidade = quantidade;
        this.desconto = desconto;
        this.subtotal = subtotal;
        this.valorTotal = valorTotal;
        this.statusVenda = statusVenda;
        this.canalVendas = canalVendas;
        this.id_Pagamento = id_Pagamento;
        this.cpf_Cliente = cpf_Cliente;
        this.cpf_Funcionário = cpf_Funcionário;
    }

	private String nomeProduto;
	
	public void setNomeProduto(String nomeProduto) {
        this.nomeProduto = nomeProduto;
    }

    public String getNomeProduto() {
        return nomeProduto;
    }
}
