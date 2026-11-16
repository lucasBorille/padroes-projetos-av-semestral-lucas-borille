import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Auditor implements ObservadorDeLote {
    private static final DateTimeFormatter FORMATO = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");

    private final List<String> registros = new ArrayList<>();

    @Override
    public void atualizar(Lote lote, Lance lance) {
        registros.add("[" + FORMATO.format(LocalDateTime.now()) + "] lote=\"" + lote.getNome()
                + "\" participante=" + lance.licitante().getNome() + " valor=" + Moeda.formatar(lance.valor()));
    }

    public List<String> getRegistros() {
        return Collections.unmodifiableList(registros);
    }
}
