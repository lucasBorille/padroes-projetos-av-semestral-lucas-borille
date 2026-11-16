import java.math.BigDecimal;
import java.util.Optional;
import java.util.stream.Collectors;

public class Provisionador {
    private final SeletorDeProvedor seletor;

    public Provisionador(SeletorDeProvedor seletor) {
        this.seletor = seletor;
    }

    public boolean provisionar(SolicitacaoAmbiente solicitacao) {
        Optional<FabricaNuvem> escolhida = seletor.selecionar(solicitacao.provedor());
        if (escolhida.isEmpty()) {
            return recusar(solicitacao, "provedor desconhecido");
        }
        FabricaNuvem fabrica = escolhida.get();

        BigDecimal custoEstimado = fabrica.estimarCustoMensal(
                solicitacao.quantidadeMaquinas(), solicitacao.gbArmazenamento());
        BigDecimal orcamento = ConfiguracaoGlobal.getInstance().getOrcamentoMensal();
        if (custoEstimado.compareTo(orcamento) > 0) {
            return recusar(solicitacao, "custo estimado de " + Moeda.formatar(custoEstimado)
                    + " ultrapassa o orçamento de " + Moeda.formatar(orcamento));
        }

        Ambiente ambiente = new MontadorDeAmbiente(fabrica).montar(solicitacao);
        System.out.println(resumo(ambiente));
        HistoricoAuditoria.getInstance().registrar(solicitacao.nome(), solicitacao.provedor(), "ACEITO");
        return true;
    }

    private boolean recusar(SolicitacaoAmbiente solicitacao, String motivo) {
        System.out.println("Provisionamento RECUSADO: " + solicitacao.nome() + " (" + solicitacao.provedor()
                + ") - " + motivo + System.lineSeparator());
        HistoricoAuditoria.getInstance().registrar(solicitacao.nome(), solicitacao.provedor(), "RECUSADO: " + motivo);
        return false;
    }

    private String resumo(Ambiente ambiente) {
        String maquinas = ambiente.getMaquinas().stream().map(Recurso::getId).collect(Collectors.joining(", "));
        String separador = System.lineSeparator();
        return "Ambiente provisionado: " + ambiente.getNome() + separador
                + "  Provedor: " + ambiente.getProvedor() + separador
                + "  Região: " + ambiente.getRegiao() + separador
                + "  Máquinas virtuais: " + maquinas + separador
                + "  Armazenamento: " + ambiente.getArmazenamento().getId()
                + " (" + ambiente.getArmazenamento().getGb() + " GB)" + separador
                + "  Fila: " + ambiente.getFila().getId() + separador
                + "  Custo mensal estimado: " + Moeda.formatar(ambiente.getCustoMensal()) + separador;
    }
}
