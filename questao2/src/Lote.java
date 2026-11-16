import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

public class Lote {
    private static final BigDecimal FATOR_INCREMENTO_MINIMO = new BigDecimal("1.05");

    private final String nome;
    private final Categoria categoria;
    private final BigDecimal precoInicial;
    private final BigDecimal precoReserva;
    private final List<Lance> lances = new ArrayList<>();
    private final List<ObservadorDeLote> observadores = new ArrayList<>();

    private RegraComissao regraComissao;
    private EstadoLote estado = EstadoLote.ABERTO;
    private Licitante comprador;
    private BigDecimal valorVenda;
    private BigDecimal comissao;

    public Lote(String nome, Categoria categoria, BigDecimal precoInicial, BigDecimal precoReserva) {
        if (nome == null || nome.isBlank()) {
            throw new IllegalArgumentException("Nome do lote é obrigatório");
        }
        if (categoria == null) {
            throw new IllegalArgumentException("Categoria é obrigatória");
        }
        if (precoInicial == null || precoInicial.signum() <= 0) {
            throw new IllegalArgumentException("Preço inicial deve ser maior que zero");
        }
        if (precoReserva == null || precoReserva.signum() < 0) {
            throw new IllegalArgumentException("Preço de reserva não pode ser negativo");
        }
        this.nome = nome;
        this.categoria = categoria;
        this.precoInicial = precoInicial;
        this.precoReserva = precoReserva;
        this.regraComissao = categoria.getRegraComissaoPadrao();
    }

    public String getNome() {
        return nome;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public BigDecimal getPrecoInicial() {
        return precoInicial;
    }

    public BigDecimal getPrecoReserva() {
        return precoReserva;
    }

    public EstadoLote getEstado() {
        return estado;
    }

    public RegraComissao getRegraComissao() {
        return regraComissao;
    }

    public int getQuantidadeLances() {
        return lances.size();
    }

    public BigDecimal getLanceAtual() {
        if (lances.isEmpty()) {
            return precoInicial;
        }
        return lances.get(lances.size() - 1).valor();
    }

    public Licitante getComprador() {
        exigirVendido();
        return comprador;
    }

    public BigDecimal getValorVenda() {
        exigirVendido();
        return valorVenda;
    }

    public BigDecimal getComissao() {
        exigirVendido();
        return comissao;
    }

    public void acompanhar(ObservadorDeLote observador) {
        if (observador == null) {
            throw new IllegalArgumentException("Observador não pode ser nulo");
        }
        if (!observadores.contains(observador)) {
            observadores.add(observador);
        }
    }

    public void deixarDeAcompanhar(ObservadorDeLote observador) {
        observadores.remove(observador);
    }

    public ResultadoLance darLance(Licitante licitante, BigDecimal valor) {
        if (licitante == null || valor == null) {
            throw new IllegalArgumentException("Licitante e valor são obrigatórios");
        }
        if (estado != EstadoLote.ABERTO) {
            return new ResultadoLance(false, "lote encerrado, não aceita novos lances");
        }
        BigDecimal minimo = getLanceAtual().multiply(FATOR_INCREMENTO_MINIMO).setScale(2, RoundingMode.CEILING);
        if (valor.compareTo(minimo) < 0) {
            return new ResultadoLance(false, "lance abaixo do mínimo de " + Moeda.formatar(minimo)
                    + " (5% acima do lance atual de " + Moeda.formatar(getLanceAtual()) + ")");
        }
        Lance lance = new Lance(licitante, valor);
        lances.add(lance);
        notificar(lance);
        return new ResultadoLance(true, "lance registrado");
    }

    public void trocarRegraComissao(RegraComissao novaRegra) {
        if (novaRegra == null) {
            throw new IllegalArgumentException("Regra de comissão é obrigatória");
        }
        if (estado != EstadoLote.ABERTO) {
            throw new IllegalStateException("A regra só pode ser trocada antes do encerramento");
        }
        this.regraComissao = novaRegra;
    }

    public EstadoLote encerrar() {
        if (estado != EstadoLote.ABERTO) {
            throw new IllegalStateException("Lote já encerrado");
        }
        if (lances.isEmpty() || getLanceAtual().compareTo(precoReserva) < 0) {
            estado = EstadoLote.NAO_VENDIDO;
            return estado;
        }
        Lance vencedor = lances.get(lances.size() - 1);
        this.comprador = vencedor.licitante();
        this.valorVenda = vencedor.valor();
        this.comissao = regraComissao.calcular(valorVenda);
        this.estado = EstadoLote.VENDIDO;
        return estado;
    }

    private void notificar(Lance lance) {
        for (ObservadorDeLote observador : new ArrayList<>(observadores)) {
            if (observador != lance.licitante()) {
                observador.atualizar(this, lance);
            }
        }
    }

    private void exigirVendido() {
        if (estado != EstadoLote.VENDIDO) {
            throw new IllegalStateException("O lote não foi vendido");
        }
    }
}
