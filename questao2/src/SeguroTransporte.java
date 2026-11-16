import java.math.BigDecimal;
import java.math.RoundingMode;

public class SeguroTransporte extends ServicoAdicional {
    private static final BigDecimal PERCENTUAL = new BigDecimal("0.015");

    public SeguroTransporte(Fatura fatura) {
        super(fatura, "Seguro de transporte (1,5% do valor de venda)");
    }

    @Override
    protected BigDecimal calcularCusto() {
        return getValorVenda().multiply(PERCENTUAL).setScale(2, RoundingMode.HALF_UP);
    }
}
