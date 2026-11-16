import java.math.BigDecimal;

public class AwsMaquinaVirtual implements MaquinaVirtual {
    public static final String PREFIXO = "i-";
    public static final BigDecimal CUSTO_HORA = new BigDecimal("0.10");

    private final String id;

    public AwsMaquinaVirtual(int numero) {
        this.id = PREFIXO + numero;
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public BigDecimal getCustoMensal() {
        return CUSTO_HORA.multiply(HORAS_POR_MES);
    }
}
