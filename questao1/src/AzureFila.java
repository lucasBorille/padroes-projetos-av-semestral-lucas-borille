import java.math.BigDecimal;

public class AzureFila implements Fila {
    public static final String PREFIXO = "sb-";
    public static final BigDecimal CUSTO_MENSAL = new BigDecimal("0.50");

    private final String id;

    public AzureFila(int numero) {
        this.id = PREFIXO + numero;
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public BigDecimal getCustoMensal() {
        return CUSTO_MENSAL;
    }
}
