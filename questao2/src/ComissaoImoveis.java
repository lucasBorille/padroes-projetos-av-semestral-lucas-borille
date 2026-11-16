import java.math.BigDecimal;
import java.math.RoundingMode;

public class ComissaoImoveis implements RegraComissao {
    private static final BigDecimal PERCENTUAL = new BigDecimal("0.03");
    private static final BigDecimal VALOR_FIXO = new BigDecimal("1500.00");

    @Override
    public String getNome() {
        return "Imóveis (3% + R$ 1.500,00)";
    }

    @Override
    public BigDecimal calcular(BigDecimal valorVenda) {
        return valorVenda.multiply(PERCENTUAL).add(VALOR_FIXO).setScale(2, RoundingMode.HALF_UP);
    }
}
