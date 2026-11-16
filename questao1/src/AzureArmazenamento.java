import java.math.BigDecimal;

public class AzureArmazenamento implements Armazenamento {
    public static final String PREFIXO = "blob://";
    public static final BigDecimal CUSTO_GB = new BigDecimal("0.02");

    private final String id;
    private final int gb;

    public AzureArmazenamento(int numero, int gb) {
        if (gb <= 0) {
            throw new IllegalArgumentException("Quantidade de GB deve ser maior que zero");
        }
        this.id = PREFIXO + numero;
        this.gb = gb;
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public int getGb() {
        return gb;
    }

    @Override
    public BigDecimal getCustoMensal() {
        return CUSTO_GB.multiply(BigDecimal.valueOf(gb));
    }
}
