import java.math.BigDecimal;

public class AcertoVendedor {
    private final String nomeLote;
    private final String nomeRegra;
    private final BigDecimal valorVenda;
    private final BigDecimal comissao;

    public AcertoVendedor(Lote lote) {
        if (lote == null) {
            throw new IllegalArgumentException("Lote é obrigatório");
        }
        this.nomeLote = lote.getNome();
        this.nomeRegra = lote.getRegraComissao().getNome();
        this.valorVenda = lote.getValorVenda();
        this.comissao = lote.getComissao();
    }

    public BigDecimal getValorLiquido() {
        return valorVenda.subtract(comissao);
    }

    public void imprimir() {
        System.out.println("===== ACERTO DO VENDEDOR =====");
        System.out.println("Lote: " + nomeLote);
        System.out.println("Valor de venda: " + Moeda.formatar(valorVenda));
        System.out.println("Comissão cobrada [" + nomeRegra + "]: " + Moeda.formatar(comissao));
        System.out.println("Valor líquido a repassar: " + Moeda.formatar(getValorLiquido()));
        System.out.println();
    }
}
