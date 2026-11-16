import java.math.BigDecimal;
import java.math.RoundingMode;

public class ComissaoVeiculos implements RegraComissao {
    private static final BigDecimal PERCENTUAL = new BigDecimal("0.08");
    private static final BigDecimal LIMITE = new BigDecimal("5000.00");

    @Override
    public String getNome() {
        return "Veículos (8%, limite de R$ 5.000,00)";
    }

    @Override
    public BigDecimal calcular(BigDecimal valorVenda) {
        return valorVenda.multiply(PERCENTUAL).min(LIMITE).setScale(2, RoundingMode.HALF_UP);
    }
}
