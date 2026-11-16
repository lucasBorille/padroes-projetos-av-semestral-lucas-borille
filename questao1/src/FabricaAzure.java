import java.math.BigDecimal;
import java.math.RoundingMode;

public class FabricaAzure implements FabricaNuvem {
    private static final String NOME = "Azure";

    @Override
    public String getNomeProvedor() {
        return NOME;
    }

    @Override
    public MaquinaVirtual criarMaquinaVirtual(int numero) {
        return new AzureMaquinaVirtual(numero);
    }

    @Override
    public Armazenamento criarArmazenamento(int numero, int gb) {
        return new AzureArmazenamento(numero, gb);
    }

    @Override
    public Fila criarFila(int numero) {
        return new AzureFila(numero);
    }

    @Override
    public BigDecimal estimarCustoMensal(int quantidadeMaquinas, int gb) {
        BigDecimal maquinas = AzureMaquinaVirtual.CUSTO_HORA
                .multiply(MaquinaVirtual.HORAS_POR_MES)
                .multiply(BigDecimal.valueOf(quantidadeMaquinas));
        BigDecimal armazenamento = AzureArmazenamento.CUSTO_GB.multiply(BigDecimal.valueOf(gb));
        return maquinas.add(armazenamento).add(AzureFila.CUSTO_MENSAL).setScale(2, RoundingMode.HALF_UP);
    }
}
