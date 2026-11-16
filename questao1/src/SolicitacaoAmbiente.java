public record SolicitacaoAmbiente(String nome, String provedor, int quantidadeMaquinas, int gbArmazenamento) {

    public SolicitacaoAmbiente {
        if (nome == null || nome.isBlank()) {
            throw new IllegalArgumentException("Nome do ambiente é obrigatório");
        }
        if (provedor == null || provedor.isBlank()) {
            throw new IllegalArgumentException("Provedor é obrigatório");
        }
        if (quantidadeMaquinas <= 0) {
            throw new IllegalArgumentException("Quantidade de máquinas deve ser maior que zero");
        }
        if (gbArmazenamento <= 0) {
            throw new IllegalArgumentException("Quantidade de GB deve ser maior que zero");
        }
    }
}
