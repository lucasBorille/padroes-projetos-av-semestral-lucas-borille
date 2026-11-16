import java.math.BigDecimal;

public class CertificadoAutenticidade extends ServicoAdicional {
    private static final BigDecimal CUSTO = new BigDecimal("300.00");

    public CertificadoAutenticidade(Fatura fatura) {
        super(fatura, "Certificado de autenticidade");
    }

    @Override
    protected BigDecimal calcularCusto() {
        return CUSTO;
    }
}
