public class PainelLeiloeiro implements ObservadorDeLote {

    @Override
    public void atualizar(Lote lote, Lance lance) {
        System.out.println("  [PAINEL] Lote \"" + lote.getNome() + "\": maior lance "
                + Moeda.formatar(lote.getLanceAtual()) + " | total de lances: " + lote.getQuantidadeLances());
    }
}
