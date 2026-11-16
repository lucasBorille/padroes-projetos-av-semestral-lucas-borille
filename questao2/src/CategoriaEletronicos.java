public class CategoriaEletronicos implements Categoria {

    @Override
    public String getNome() {
        return "Eletrônicos";
    }

    @Override
    public RegraComissao getRegraComissaoPadrao() {
        return new ComissaoEletronicos();
    }
}
