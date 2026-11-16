import java.math.BigDecimal;

public class AwsArmazenamento implements Armazenamento {
    public static final String PREFIXO = "s3://";
    public static final BigDecimal CUSTO_GB = new BigDecimal("0.023");

    private final String id;
    private final int gb;

    public AwsArmazenamento(int numero, int gb) {
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
