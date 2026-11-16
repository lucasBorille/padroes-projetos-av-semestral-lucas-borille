import java.math.BigDecimal;
import java.util.List;

public interface Fatura {
    String getNomeLote();

    BigDecimal getValorVenda();

    List<LinhaFatura> getServicos();

    BigDecimal getTotal();
}
