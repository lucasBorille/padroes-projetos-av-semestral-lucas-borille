import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public final class HistoricoAuditoria {
    private static volatile HistoricoAuditoria instancia;

    private final List<RegistroAuditoria> registros = new ArrayList<>();

    private HistoricoAuditoria() {
    }

    public static HistoricoAuditoria getInstance() {
        HistoricoAuditoria resultado = instancia;
        if (resultado == null) {
            synchronized (HistoricoAuditoria.class) {
                resultado = instancia;
                if (resultado == null) {
                    resultado = new HistoricoAuditoria();
                    instancia = resultado;
                }
            }
        }
        return resultado;
    }

    public void registrar(String ambiente, String provedor, String resultado) {
        synchronized (registros) {
            registros.add(new RegistroAuditoria(LocalDateTime.now(), ambiente, provedor, resultado));
        }
    }

    public List<RegistroAuditoria> getRegistros() {
        synchronized (registros) {
            return List.copyOf(registros);
        }
    }
}
