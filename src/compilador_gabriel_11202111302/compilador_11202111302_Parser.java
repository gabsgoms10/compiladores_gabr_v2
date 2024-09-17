package compilador_gabriel_11202111302;
import java.util.List;

public class compilador_11202111302_Parser {
    private List<Token> tokens;
    private int pos = 0;

    public compilador_11202111302_Parser(List<Token> tokens) {
        this.tokens = tokens;
    }

    public void parse() {
        while (pos < tokens.size()) {
            Token token = tokens.get(pos);
            if (token.getTipo() == TipoToken.KEYWORD) {
                System.out.println("Palavra-chave: " + token.getValor());
            } else if (token.getTipo() == TipoToken.IDENTIFIER) {
                System.out.println("Identificador: " + token.getValor());
            } else if (token.getTipo() == TipoToken.OPERATOR) {
                System.out.println("Operador: " + token.getValor());
            } else if (token.getTipo() == TipoToken.NUMBER) {
                System.out.println("Número: " + token.getValor());
            } else if (token.getTipo() == TipoToken.STRING) {
                System.out.println("String: " + token.getValor());
            } else if (token.getTipo() == TipoToken.INPUT_OUTPUT) {
                System.out.println("Comando de entrada/saída: " + token.getValor());
            } else if (token.getTipo() == TipoToken.LOOP) {
                System.out.println("Loop: " + token.getValor());
            } else if (token.getTipo() == TipoToken.EOF) {
                System.out.println("Fim do arquivo.");
                break;
            } else {
                throw new RuntimeException("Tipo de token desconhecido: " + token);
            }
            pos++;
        }
    }
}


