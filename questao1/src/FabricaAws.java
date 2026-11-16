import java.math.BigDecimal;
import java.math.RoundingMode;

public class FabricaAws implements FabricaNuvem {
    private static final String NOME = "Aws";

    @Override
    public String getNomeProvedor() {
        return NOME;
    }

    @Override
    public MaquinaVirtual criarMaquinaVirtual(int numero) {
        return new AwsMaquinaVirtual(numero);
    }

    @Override
    public Armazenamento criarArmazenamento(int numero, int gb) {
        return new AwsArmazenamento(numero, gb);
    }

    @Override
    public Fila criarFila(int numero) {
        return new AwsFila(numero);
    }

    @Override
    public BigDecimal estimarCustoMensal(int quantidadeMaquinas, int gb) {
        BigDecimal maquinas = AwsMaquinaVirtual.CUSTO_HORA
                .multiply(MaquinaVirtual.HORAS_POR_MES)
                .multiply(BigDecimal.valueOf(quantidadeMaquinas));
        BigDecimal armazenamento = AwsArmazenamento.CUSTO_GB.multiply(BigDecimal.valueOf(gb));
        return maquinas.add(armazenamento).add(AwsFila.CUSTO_MENSAL).setScale(2, RoundingMode.HALF_UP);
    }
}
