import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public abstract class ServicoAdicional implements Fatura {
    private final Fatura fatura;
    private final String descricao;

    protected ServicoAdicional(Fatura fatura, String descricao) {
        if (fatura == null) {
            throw new IllegalArgumentException("Fatura é obrigatória");
        }
        this.fatura = fatura;
        this.descricao = descricao;
    }

    protected abstract BigDecimal calcularCusto();

    @Override
    public String getNomeLote() {
        return fatura.getNomeLote();
    }

    @Override
    public BigDecimal getValorVenda() {
        return fatura.getValorVenda();
    }

    @Override
    public List<LinhaFatura> getServicos() {
        List<LinhaFatura> servicos = new ArrayList<>(fatura.getServicos());
        servicos.add(new LinhaFatura(descricao, calcularCusto()));
        return servicos;
    }

    @Override
    public BigDecimal getTotal() {
        return fatura.getTotal().add(calcularCusto());
    }
}
