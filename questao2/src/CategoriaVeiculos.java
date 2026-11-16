public class CategoriaVeiculos implements Categoria {

    @Override
    public String getNome() {
        return "Veículos";
    }

    @Override
    public RegraComissao getRegraComissaoPadrao() {
        return new ComissaoVeiculos();
    }
}
