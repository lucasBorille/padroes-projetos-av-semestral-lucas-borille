import java.math.BigDecimal;

public class EntregaExpressa extends ServicoAdicional {
    private static final BigDecimal CUSTO = new BigDecimal("450.00");

    public EntregaExpressa(Fatura fatura) {
        super(fatura, "Entrega expressa");
    }

    @Override
    protected BigDecimal calcularCusto() {
        return CUSTO;
    }
}
