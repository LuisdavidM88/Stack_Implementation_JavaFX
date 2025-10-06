package Ej2;

import java.util.Stack;

// logica de balanceo usando pila
public class Balanceador {

    // clase interna que guarda el resultado para mostrar en la interfaz
    public static class Resultado {
        public final boolean balanceado;
        public final int aperturasParentesis;
        public final int aperturasLlaves;
        public final int aperturasCorchetes;
        public final String detalle;

        public Resultado(boolean ok, int ap, int al, int ac, String det) {
            this.balanceado = ok;
            this.aperturasParentesis = ap;
            this.aperturasLlaves = al;
            this.aperturasCorchetes = ac;
            this.detalle = det;
        }
    }

    // metodo que evalua el texto y revisa si esta balanceado
    public Resultado evaluar(String texto) {
        Stack<Character> pila = new Stack<>();
        int aP = 0, aL = 0, aC = 0; // contadores de aperturas
        StringBuilder log = new StringBuilder();

        for (int i = 0; i < texto.length(); i++) {
            char ch = texto.charAt(i);

            // si encontramos aperturas las guardamos en la pila y contamos
            if (ch == '(') { aP++; pila.push(ch); log.append("Push '('\n"); }
            else if (ch == '{') { aL++; pila.push(ch); log.append("Push '{'\n"); }
            else if (ch == '[') { aC++; pila.push(ch); log.append("Push '['\n"); }

            // si encontramos cierres comprobamos contra la cima
            else if (ch == ')' || ch == '}' || ch == ']') {
                if (pila.isEmpty()) {
                    log.append("Cierre sin apertura en indice ").append(i).append(" '").append(ch).append("'\n");
                    return new Resultado(false, aP, aL, aC, log.toString());
                }
                char top = pila.pop();
                if (!coincide(top, ch)) {
                    log.append("No coincide cima '").append(top).append("' con '").append(ch).append("'\n");
                    return new Resultado(false, aP, aL, aC, log.toString());
                } else {
                    log.append("Match '").append(top).append("' con '").append(ch).append("'\n");
                }
            }
        }

        // si quedan aperturas en la pila entonces no esta balanceado
        if (!pila.isEmpty()) {
            log.append("Quedaron aperturas sin cerrar ").append(pila).append("\n");
            return new Resultado(false, aP, aL, aC, log.toString());
        }

        // si no hay errores esta balanceado
        log.append("Balanceado\n");
        return new Resultado(true, aP, aL, aC, log.toString());
    }

    // metodo que comprueba si apertura y cierre son del mismo tipo
    private boolean coincide(char apertura, char cierre) {
        return (apertura == '(' && cierre == ')') ||
                (apertura == '{' && cierre == '}') ||
                (apertura == '[' && cierre == ']');
    }
}
