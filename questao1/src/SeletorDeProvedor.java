import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Optional;

public class SeletorDeProvedor {
    private final Map<String, FabricaNuvem> fabricas = new HashMap<>();

    public SeletorDeProvedor(List<? extends FabricaNuvem> disponiveis) {
        for (FabricaNuvem fabrica : disponiveis) {
            fabricas.put(chave(fabrica.getNomeProvedor()), fabrica);
        }
    }

    public Optional<FabricaNuvem> selecionar(String provedor) {
        if (provedor == null) {
            return Optional.empty();
        }
        return Optional.ofNullable(fabricas.get(chave(provedor)));
    }

    private static String chave(String provedor) {
        return provedor.trim().toLowerCase(Locale.ROOT);
    }
}
