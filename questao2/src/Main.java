import java.math.BigDecimal;

public class Main {
    public static void main(String[] args) {
        Lote quadro = new Lote("Paisagem a óleo (1950)", new CategoriaArte(),
                new BigDecimal("10000.00"), new BigDecimal("15000.00"));
        Lote notebook = new Lote("Notebook gamer", new CategoriaEletronicos(),
                new BigDecimal("3000.00"), new BigDecimal("4000.00"));

        Participante ana = new Participante("Ana");
        Participante bruno = new Participante("Bruno");
        Participante carla = new Participante("Carla");
        PainelLeiloeiro painel = new PainelLeiloeiro();
        Auditor auditor = new Auditor();

        for (Lote lote : new Lote[] { quadro, notebook }) {
            lote.acompanhar(painel);
            lote.acompanhar(auditor);
        }
        quadro.acompanhar(ana);
        quadro.acompanhar(bruno);
        quadro.acompanhar(carla);
        notebook.acompanhar(ana);
        notebook.acompanhar(bruno);

        titulo("Lances no lote de arte");
        darLance(quadro, ana, "10500.00");
        darLance(quadro, bruno, "10600.00");
        darLance(quadro, bruno, "11025.00");
        darLance(quadro, carla, "12000.00");

        titulo("Bruno deixa de acompanhar o lote de arte");
        quadro.deixarDeAcompanhar(bruno);
        darLance(quadro, ana, "13000.00");
        darLance(quadro, carla, "16000.00");

        titulo("Lances no lote de eletrônicos");
        darLance(notebook, bruno, "3150.00");
        darLance(notebook, ana, "3300.00");
        darLance(notebook, ana, "3400.00");

        titulo("Campanha: lote de arte passa a usar a regra de eletrônicos");
        System.out.println("Regra antes: " + quadro.getRegraComissao().getNome());
        quadro.trocarRegraComissao(new ComissaoEletronicos());
        System.out.println("Regra depois: " + quadro.getRegraComissao().getNome());

        titulo("Encerramento");
        System.out.println("Lote \"" + quadro.getNome() + "\": " + quadro.encerrar());
        System.out.println("Lote \"" + notebook.getNome() + "\": " + notebook.encerrar());
        darLance(notebook, carla, "5000.00");
        System.out.println();

        AcertoVendedor acerto = new AcertoVendedor(quadro);
        acerto.imprimir();

        Fatura semServicos = new FaturaBasica(quadro);
        ImpressoraDeFatura.imprimir(semServicos);

        Fatura comServicos = new EmbalagemEspecial(
                new EmbalagemEspecial(
                        new CertificadoAutenticidade(
                                new SeguroTransporte(
                                        new FaturaBasica(quadro)))));
        ImpressoraDeFatura.imprimir(comServicos);

        System.out.println("===== REGISTROS DO AUDITOR =====");
        auditor.getRegistros().forEach(System.out::println);
    }

    private static void titulo(String texto) {
        System.out.println();
        System.out.println("--- " + texto);
    }

    private static void darLance(Lote lote, Participante participante, String valor) {
        BigDecimal lance = new BigDecimal(valor);
        System.out.println("> " + participante.getNome() + " oferece " + Moeda.formatar(lance)
                + " pelo lote \"" + lote.getNome() + "\"");
        ResultadoLance resultado = lote.darLance(participante, lance);
        System.out.println("  " + (resultado.aceito() ? "ACEITO" : "RECUSADO") + ": " + resultado.mensagem());
    }
}
