package packageController;

import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import packageModel.Vendas;
import package_controle.VendaDAO;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.UUID;

public class controllerCadastroVenda {

    @FXML
    private TextField txtNomeFuncionario;
    @FXML
    private TextField txtNomeCliente;
    @FXML
    private TextField txtCodigoProduto;
    @FXML
    private TextField txtQuantidade;
    @FXML
    private TextField txtDesconto;
    @FXML
    private Text lblSubtotal;
    @FXML
    private Text lblTotal;
    @FXML
    private TextArea txtListaProdutos;

    private VendaDAO vendaDAO = new VendaDAO();
    private ArrayList<Vendas> vendasList = new ArrayList<>();
    private double subtotal = 0.0;
    private double total = 0.0;

    @FXML
    private void onAddProduto() {
        // Coletar informações do produto
        String codigoProduto = txtCodigoProduto.getText();
        int quantidade = Integer.parseInt(txtQuantidade.getText());
        double desconto = Double.parseDouble(txtDesconto.getText());

        // Aqui você deve buscar o preço do produto no banco de dados ou em uma lista de produtos
        double precoProduto = buscarPrecoProduto(codigoProduto);
        double valorProduto = precoProduto * quantidade;
        double valorDesconto = (valorProduto * desconto) / 100;

        // Calcular subtotal e total
        subtotal += (valorProduto - valorDesconto);
        total = subtotal; // Aqui você pode adicionar lógica de total, considerando outros fatores

        // Adicionar à lista de produtos
        String produtoInfo = String.format("Código: %s, Quantidade: %d, Desconto: %.2f", codigoProduto, quantidade, desconto);
        txtListaProdutos.appendText(produtoInfo + "\n");

        // Atualizar o subtotal e total na interface
        atualizarLabels();
    }

    private double buscarPrecoProduto(String codigoProduto) {
        // Aqui você deve implementar a lógica para buscar o preço do produto no banco de dados
        // Por enquanto, retornamos um valor fixo para fins de exemplo
        return 50.0; // Exemplo: preço fixo
    }

    private void atualizarLabels() {
        lblSubtotal.setText(String.format("Subtotal: %.2f", subtotal));
        lblTotal.setText(String.format("Total: %.2f", total));
    }

    @FXML
    private void onFinalizarVenda() {

        Vendas currentVenda = new Vendas();
        currentVenda.setIdVenda(gerarIdVenda());
        currentVenda.setDataVenda(obterDataAtual());
        currentVenda.setCodigoProduto(txtCodigoProduto.getText());
        currentVenda.setQuantidade(txtQuantidade.getText());
        currentVenda.setDesconto(txtDesconto.getText());
        currentVenda.setSubtotal(String.valueOf(subtotal));
        currentVenda.setValorTotal(String.valueOf(total));
        currentVenda.setStatusVenda("Finalizada");
        currentVenda.setCanalVendas("Online"); 
        currentVenda.setId_Pagamento("id-pagamento"); 
        currentVenda.setCpf_Cliente(txtNomeCliente.getText());
        currentVenda.setCpf_Funcionário(txtNomeFuncionario.getText()); 

        vendaDAO.create(currentVenda);
        
        limparCampos();
    }

    private String gerarIdVenda() {
        return UUID.randomUUID().toString();
    }

    private String obterDataAtual() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return sdf.format(new Date());
    }

    @FXML
    private void onCancelar() {
        limparCampos();
    }

    private void limparCampos() {
        txtNomeFuncionario.clear();
        txtNomeCliente.clear();
        txtCodigoProduto.clear();
        txtQuantidade.clear();
        txtDesconto.clear();
        lblSubtotal.setText("Subtotal: 0.00");
        lblTotal.setText("Total: 0.00");
        txtListaProdutos.clear();
        subtotal = 0.0;
        total = 0.0; 
    }
}
