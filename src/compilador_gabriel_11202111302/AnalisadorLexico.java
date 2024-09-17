package compilador_gabriel_11202111302;
import java.io.IOException;
import java.io.PushbackReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class AnalisadorLexico {
    private PushbackReader reader;
    private List<Token> tokens;

    public AnalisadorLexico(String arquivo) throws IOException {
        this.reader = new PushbackReader(new FileReader(arquivo));
        this.tokens = new ArrayList<>();
        analisar();
    }

    private void analisar() throws IOException {
        int c;
        int linha = 1, coluna = 1;
        while ((c = reader.read()) != -1) {
            char caractere = (char) c;

            if (Character.isWhitespace(caractere)) {
                if (caractere == '\n') {
                    linha++;
                    coluna = 1;
                } else {
                    coluna++;
                }
                continue;
            }

            if (caractere == '/') {
                int nextChar = reader.read();
                if (nextChar == '/') {
                    while ((c = reader.read()) != -1 && (char) c != '\n') {
                    }
                    linha++;
                    coluna = 1;
                    continue;
                } else {
                    reader.unread(nextChar);
                }
            }

            if (Character.isLetter(caractere) || caractere == '_') {
                StringBuilder sb = new StringBuilder();
                sb.append(caractere);
                while ((c = reader.read()) != -1 && (Character.isLetterOrDigit((char) c) || c == '_')) {
                    sb.append((char) c);
                }
                reader.unread(c);
                String valor = sb.toString();
                TipoToken tipo;
                switch (valor) {
                    case "int":
                    case "float":
                    case "bool":
                    case "string":
                        tipo = TipoToken.KEYWORD;
                        break;
                    case "entrada":
                    case "saida":
                        tipo = TipoToken.INPUT_OUTPUT;
                        break;
                    case "para":
                    case "enquanto":
                    case "faca":
                        tipo = TipoToken.LOOP;
                        break;
                    case "se":
                    case "senao":
                        tipo = TipoToken.KEYWORD;
                        break;
                    default:
                        tipo = TipoToken.IDENTIFIER;
                }
                tokens.add(new Token(tipo, valor, linha, coluna));
                coluna += valor.length();
            } else if (Character.isDigit(caractere)) {
                StringBuilder sb = new StringBuilder();
                sb.append(caractere);
                while ((c = reader.read()) != -1 && (Character.isDigit((char) c) || (char) c == '.')) {
                    sb.append((char) c);
                }
                reader.unread(c);
                tokens.add(new Token(TipoToken.NUMBER, sb.toString(), linha, coluna));
                coluna += sb.length();
            } else if (caractere == '"') {
                StringBuilder sb = new StringBuilder();
                sb.append(caractere);
                while ((c = reader.read()) != -1 && (char) c != '"') {
                    sb.append((char) c);
                }
                if (c == '"') {
                    sb.append((char) c);
                }
                tokens.add(new Token(TipoToken.STRING, sb.toString(), linha, coluna));
                coluna += sb.length();
            } else {
                switch (caractere) {
                    case ';':
                    case '{':
                    case '}':
                    case '(':
                    case ')':
                    case '+':
                    case '-':
                    case '*':
                    case '/':
                    case '=':
                    case '!':
                    case '<':
                    case '>':
                    case '&':
                    case '|':
                    case ',':
                    case '.':
                    case ':':
                    case '#':
                        tokens.add(new Token(TipoToken.OPERATOR, Character.toString(caractere), linha, coluna));
                        coluna++;
                        break;
                    default:
                        throw new IOException("Caracter não reconhecido: " + caractere);
                }
            }
        }
        tokens.add(new Token(TipoToken.EOF, "", linha, coluna));
    }

    public List<Token> getTokens() {
        return tokens;
    }
}





