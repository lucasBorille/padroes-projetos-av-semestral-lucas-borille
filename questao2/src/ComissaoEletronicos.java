import java.math.BigDecimal;
import java.math.RoundingMode;

public class ComissaoEletronicos implements RegraComissao {
    private static final BigDecimal PERCENTUAL = new BigDecimal("0.12");
    private static final BigDecimal MINIMO = new BigDecimal("50.00");

    @Override
    public String getNome() {
        return "Eletrônicos (12%, mínimo de R$ 50,00)";
    }

    @Override
    public BigDecimal calcular(BigDecimal valorVenda) {
        return valorVenda.multiply(PERCENTUAL).max(MINIMO).setScale(2, RoundingMode.HALF_UP);
    }
}
