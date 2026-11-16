public final class ImpressoraDeFatura {

    private ImpressoraDeFatura() {
    }

    public static void imprimir(Fatura fatura) {
        System.out.println("===== FATURA DO COMPRADOR =====");
        System.out.println("Lote: " + fatura.getNomeLote());
        System.out.println("Valor de venda: " + Moeda.formatar(fatura.getValorVenda()));
        System.out.println("Serviços adicionais:");
        for (LinhaFatura servico : fatura.getServicos()) {
            System.out.println("  - " + servico.descricao() + ": " + Moeda.formatar(servico.custo()));
        }
        System.out.println("Total a pagar: " + Moeda.formatar(fatura.getTotal()));
        System.out.println();
    }
}
