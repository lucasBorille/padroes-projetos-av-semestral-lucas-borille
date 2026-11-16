public class Participante implements Licitante, ObservadorDeLote {
    private final String nome;

    public Participante(String nome) {
        if (nome == null || nome.isBlank()) {
            throw new IllegalArgumentException("Nome do participante é obrigatório");
        }
        this.nome = nome;
    }

    @Override
    public String getNome() {
        return nome;
    }

    @Override
    public void atualizar(Lote lote, Lance lance) {
        System.out.println("  [" + nome + "] Lote \"" + lote.getNome() + "\": novo valor "
                + Moeda.formatar(lance.valor()) + " (lance de " + lance.licitante().getNome() + ")");
    }
}
