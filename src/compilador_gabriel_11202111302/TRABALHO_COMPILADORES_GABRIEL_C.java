package compilador_gabriel_11202111302;
import java.io.IOException;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        try {
            AnalisadorLexico analisador = new AnalisadorLexico("src/programa.txt");
            List<Token> tokens = analisador.getTokens();

            compilador_11202111302_Parser parser = new compilador_11202111302_Parser(tokens);
            parser.analisar();

            GeradorCodigoC geradorC = new GeradorCodigoC();
            geradorC.gerar(tokens);

            GeradorCodigoJava geradorJava = new GeradorCodigoJava();
            geradorJava.gerar(tokens);

        } catch (IOException e) {
            System.err.println("Erro ao processar o arquivo: " + e.getMessage());
        }
    }
}
