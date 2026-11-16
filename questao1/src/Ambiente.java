import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

public class Ambiente {
    private final String nome;
    private final String provedor;
    private final String regiao;
    private final List<MaquinaVirtual> maquinas;
    private final Armazenamento armazenamento;
    private final Fila fila;

    public Ambiente(String nome, String provedor, String regiao, List<MaquinaVirtual> maquinas,
            Armazenamento armazenamento, Fila fila) {
        this.nome = nome;
        this.provedor = provedor;
        this.regiao = regiao;
        this.maquinas = List.copyOf(maquinas);
        this.armazenamento = armazenamento;
        this.fila = fila;
    }

    public String getNome() {
        return nome;
    }

    public String getProvedor() {
        return provedor;
    }

    public String getRegiao() {
        return regiao;
    }

    public List<MaquinaVirtual> getMaquinas() {
        return maquinas;
    }

    public Armazenamento getArmazenamento() {
        return armazenamento;
    }

    public Fila getFila() {
        return fila;
    }

    public BigDecimal getCustoMensal() {
        BigDecimal total = armazenamento.getCustoMensal().add(fila.getCustoMensal());
        for (MaquinaVirtual maquina : maquinas) {
            total = total.add(maquina.getCustoMensal());
        }
        return total.setScale(2, RoundingMode.HALF_UP);
    }
}
