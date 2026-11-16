import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class Main {
    public static void main(String[] args) throws Exception {
        SeletorDeProvedor seletor = new SeletorDeProvedor(List.of(new FabricaAws(), new FabricaAzure()));
        Provisionador provisionador = new Provisionador(seletor);

        List<SolicitacaoAmbiente> solicitacoes = List.of(
                new SolicitacaoAmbiente("loja-producao", "Aws", 3, 100),
                new SolicitacaoAmbiente("analytics", "Aws", 2, 500),
                new SolicitacaoAmbiente("portal-clientes", "Azure", 4, 200),
                new SolicitacaoAmbiente("data-lake", "Aws", 8, 2000));

        ExecutorService executor = Executors.newFixedThreadPool(solicitacoes.size());
        CountDownLatch largada = new CountDownLatch(1);
        List<Future<Boolean>> execucoes = new ArrayList<>();
        for (SolicitacaoAmbiente solicitacao : solicitacoes) {
            execucoes.add(executor.submit(() -> {
                largada.await();
                return provisionador.provisionar(solicitacao);
            }));
        }
        largada.countDown();
        for (Future<Boolean> execucao : execucoes) {
            execucao.get();
        }
        executor.shutdown();

        System.out.println("===== Histórico de auditoria =====");
        HistoricoAuditoria.getInstance().getRegistros().forEach(System.out::println);
    }
}
