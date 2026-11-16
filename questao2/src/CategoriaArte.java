public class CategoriaArte implements Categoria {

    @Override
    public String getNome() {
        return "Arte";
    }

    @Override
    public RegraComissao getRegraComissaoPadrao() {
        return new ComissaoArte();
    }
}
