public class CategoriaImoveis implements Categoria {

    @Override
    public String getNome() {
        return "Imóveis";
    }

    @Override
    public RegraComissao getRegraComissaoPadrao() {
        return new ComissaoImoveis();
    }
}
