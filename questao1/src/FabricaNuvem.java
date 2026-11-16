import java.math.BigDecimal;

public interface FabricaNuvem {
    String getNomeProvedor();

    MaquinaVirtual criarMaquinaVirtual(int numero);

    Armazenamento criarArmazenamento(int numero, int gb);

    Fila criarFila(int numero);

    BigDecimal estimarCustoMensal(int quantidadeMaquinas, int gb);
}
