package compilador_gabriel_11202111302;
import java.util.List;

public class GeradorCodigoJava {
    public void gerar(List<Token> tokens) {
        StringBuilder codigo = new StringBuilder();
        codigo.append("public class Main {\n");
        codigo.append("    public static void main(String[] args) {\n");

        for (Token token : tokens) {
            switch (token.getTipo()) {
                case KEYWORD:
                    if (token.getValor().equals("int")) {
                        codigo.append("int ");
                    } else if (token.getValor().equals("float")) {
                        codigo.append("float ");
                    } else if (token.getValor().equals("bool")) {
                        codigo.append("boolean ");
                    } else if (token.getValor().equals("string")) {
                        codigo.append("String ");
                    }
                    break;
                case IDENTIFIER:
                    codigo.append(token.getValor());
                    break;
                case OPERATOR:
                    if (token.getValor().equals(";")) {
                        codigo.append(";\n");
                    } else if (token.getValor().equals("{")) {
                        codigo.append(" {\n");
                    } else if (token.getValor().equals("}")) {
                        codigo.append(" }\n");
                    } else if (token.getValor().equals("=")) {
                        codigo.append(" = ");
                    } else {
                        codigo.append(" " + token.getValor() + " ");
                    }
                    break;
                case NUMBER:
                    codigo.append(token.getValor());
                    break;
                case STRING:
                    codigo.append('"' + token.getValor() + '"');
                    break;
                case INPUT_OUTPUT:
                    if (token.getValor().equals("entrada")) {
                        codigo.append("java.util.Scanner scanner = new java.util.Scanner(System.in);\n");
                        codigo.append("int var = scanner.nextInt();\n");
                    } else if (token.getValor().equals("saida")) {
                        codigo.append("System.out.println(var);\n");
                    }
                    break;
                case LOOP:
                    if (token.getValor().equals("para")) {
                        codigo.append("for (int i = 0; i < 10; i++) {\n");
                    } else if (token.getValor().equals("enquanto")) {
                        codigo.append("while (condicao) {\n");
                    } else if (token.getValor().equals("faca")) {
                        codigo.append("do {\n");
                    }
                    break;
                case EOF:
                    // Não gerar código para EOF
                    break;
                default:
                    throw new RuntimeException("Tipo de token desconhecido: " + token);
            }
        }

        codigo.append("    }\n");
        codigo.append("}\n");

        System.out.println("Código Java:");
        System.out.println(codigo.toString());
    }
}


