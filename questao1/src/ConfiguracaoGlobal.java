import java.io.IOException;
import java.io.Reader;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Properties;

public final class ConfiguracaoGlobal {
    private static final List<Path> LOCAIS = List.of(
            Path.of("config.properties"),
            Path.of("questao1", "config.properties"),
            Path.of("..", "config.properties"));

    private final String regiaoPadrao;
    private final BigDecimal orcamentoMensal;

    private ConfiguracaoGlobal() {
        Properties propriedades = carregar();
        this.regiaoPadrao = obrigatoria(propriedades, "regiao.padrao");
        this.orcamentoMensal = new BigDecimal(obrigatoria(propriedades, "orcamento.mensal.maximo"));
    }

    private static class Holder {
        private static final ConfiguracaoGlobal INSTANCIA = new ConfiguracaoGlobal();
    }

    public static ConfiguracaoGlobal getInstance() {
        return Holder.INSTANCIA;
    }

    public String getRegiaoPadrao() {
        return regiaoPadrao;
    }

    public BigDecimal getOrcamentoMensal() {
        return orcamentoMensal;
    }

    private static Properties carregar() {
        for (Path local : LOCAIS) {
            if (Files.isRegularFile(local)) {
                return ler(local);
            }
        }
        throw new IllegalStateException("Arquivo config.properties não encontrado");
    }

    private static Properties ler(Path arquivo) {
        Properties propriedades = new Properties();
        try (Reader leitor = Files.newBufferedReader(arquivo)) {
            propriedades.load(leitor);
        } catch (IOException e) {
            throw new IllegalStateException("Não foi possível ler " + arquivo, e);
        }
        return propriedades;
    }

    private static String obrigatoria(Properties propriedades, String chave) {
        String valor = propriedades.getProperty(chave);
        if (valor == null || valor.isBlank()) {
            throw new IllegalStateException("Propriedade obrigatória ausente: " + chave);
        }
        return valor.trim();
    }
}
