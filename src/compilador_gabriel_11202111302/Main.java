package compilador_gabriel_11202111302;
import java.io.IOException;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        try {
            AnalisadorLexico analisador = new AnalisadorLexico("src/programa.txt");
            List<Token> tokens = analisador.getTokens();

            GeradorCodigoC geradorC = new GeradorCodigoC();
            geradorC.gerar(tokens);

            GeradorCodigoJava geradorJava = new GeradorCodigoJava();
            geradorJava.gerar(tokens);

        } catch (Exception e) {
            System.err.println("Erro ao processar o código: " + e.getMessage());
        }
    }
}
