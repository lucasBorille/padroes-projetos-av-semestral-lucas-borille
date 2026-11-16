import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class MontadorDeAmbiente {
    private final FabricaNuvem fabrica;

    public MontadorDeAmbiente(FabricaNuvem fabrica) {
        this.fabrica = Objects.requireNonNull(fabrica, "Fábrica é obrigatória");
    }

    public Ambiente montar(SolicitacaoAmbiente solicitacao) {
        List<MaquinaVirtual> maquinas = new ArrayList<>();
        for (int numero = 1; numero <= solicitacao.quantidadeMaquinas(); numero++) {
            maquinas.add(fabrica.criarMaquinaVirtual(numero));
        }
        Armazenamento armazenamento = fabrica.criarArmazenamento(1, solicitacao.gbArmazenamento());
        Fila fila = fabrica.criarFila(1);
        String regiao = ConfiguracaoGlobal.getInstance().getRegiaoPadrao();
        return new Ambiente(solicitacao.nome(), fabrica.getNomeProvedor(), regiao, maquinas, armazenamento, fila);
    }
}
