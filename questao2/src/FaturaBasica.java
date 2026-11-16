import java.math.BigDecimal;
import java.util.List;

public class FaturaBasica implements Fatura {
    private final String nomeLote;
    private final BigDecimal valorVenda;

    public FaturaBasica(Lote lote) {
        if (lote == null) {
            throw new IllegalArgumentException("Lote é obrigatório");
        }
        this.nomeLote = lote.getNome();
        this.valorVenda = lote.getValorVenda();
    }

    @Override
    public String getNomeLote() {
        return nomeLote;
    }

    @Override
    public BigDecimal getValorVenda() {
        return valorVenda;
    }

    @Override
    public List<LinhaFatura> getServicos() {
        return List.of();
    }

    @Override
    public BigDecimal getTotal() {
        return valorVenda;
    }
}
