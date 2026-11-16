import java.math.BigDecimal;

public class AzureMaquinaVirtual implements MaquinaVirtual {
    public static final String PREFIXO = "vm-";
    public static final BigDecimal CUSTO_HORA = new BigDecimal("0.12");

    private final String id;

    public AzureMaquinaVirtual(int numero) {
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
