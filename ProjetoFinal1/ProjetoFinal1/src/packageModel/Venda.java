package packageModel;

import java.sql.Date;
import java.util.List;

public class Venda {
    private int idVenda;
    private String cpfCliente;
    private String cpfFuncionario;
    private Date dataVenda;
    private double totalVenda;  // Alterado para double
    private String formaPagamento;
    private List<ItemVenda> itensVenda;

    public int getIdVenda() {
        return idVenda;
    }

    public void setIdVenda(int idVenda) {
        this.idVenda = idVenda;
    }

    public String getCpfCliente() {
        return cpfCliente;
    }

    public void setCpfCliente(String cpfCliente) {
        this.cpfCliente = cpfCliente;
    }

    public String getCpfFuncionario() {
        return cpfFuncionario;
    }

    public void setCpfFuncionario(String cpfFuncionario) {
        this.cpfFuncionario = cpfFuncionario;
    }

    public Date getDataVenda() {
        return dataVenda;
    }

    public void setDataVenda(java.util.Date date) {
        this.dataVenda = (Date) date;
    }

    public double getTotalVenda() {
        return totalVenda;  
    }

    public void setTotalVenda(double totalVenda) {
        this.totalVenda = totalVenda;  
    }

    public String getFormaPagamento() {
        return formaPagamento;
    }

    public void setFormaPagamento(String formaPagamento) {
        this.formaPagamento = formaPagamento;
    }

    public List<ItemVenda> getItensVenda() {
        return itensVenda;
    }

    public void setItensVenda(List<ItemVenda> itensVenda) {
        this.itensVenda = itensVenda;
    }
}
