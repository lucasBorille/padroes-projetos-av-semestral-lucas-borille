import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public record RegistroAuditoria(LocalDateTime dataHora, String ambiente, String provedor, String resultado) {
    private static final DateTimeFormatter FORMATO = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss.SSS");

    @Override
    public String toString() {
        return "[" + FORMATO.format(dataHora) + "] ambiente=" + ambiente + " | provedor=" + provedor
                + " | resultado=" + resultado;
    }
}
