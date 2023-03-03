import java.util.HashMap;
import java.util.Map;
import java.util.Stack;
/**
 * Autor: Andy Fuentes
 * Fecha: 03/03/2023
 * Descripción: Clase que evalua las cadenas, es aceptada si ambas son del mismo numero de caracteres, usando un
 * método de pila.
 * */
public class PDA {
    /**
     * @param transitions: Mapa de transiciones
     * @param startState: Estado inicial
     * @param acceptState: Estado de aceptación
     * */
    private Map<StateSymbolPair, Transition> transitions;
    private int startState;
    private int acceptState;
    /**
     * Constructor de la clase PDA
     * */
    public PDA() {
        transitions = new HashMap<>();
        startState = 0;
        acceptState = 1;
    }
    /**
     * Método que agrega las transiciones
     */

    public void addTransition(int state, char symbol, char stackSymbol,
                              char[] newStackSymbols, int nextState) {
        StateSymbolPair ssp = new StateSymbolPair(state, symbol, stackSymbol);
        Transition t = new Transition(newStackSymbols, nextState);
        transitions.put(ssp, t);
    }

    public boolean accept(int numA, int numB) {
        StringBuilder stringBuilder = new StringBuilder();

        for (int i = 0; i < numA; i++) {
            stringBuilder.append('a');
        }

        for (int i = 0; i < numB; i++) {
            stringBuilder.append('b');
        }

        String inputString = stringBuilder.toString();

        System.out.println("La cadena generada es: " + inputString);

        Stack<Character> stack = new Stack<>();
        stack.push('$');
        int state = startState;

        for (int i = 0; i < inputString.length(); i++) {
            char symbol = inputString.charAt(i);
            char stackSymbol = stack.peek();
            StateSymbolPair ssp = new StateSymbolPair(state, symbol, stackSymbol);
            Transition t = transitions.get(ssp);

            if (t != null) {
                stack.pop();

                for (int j = t.newStackSymbols.length - 1; j >= 0; j--) {
                    stack.push(t.newStackSymbols[j]);
                }

                state = t.nextState;
            } else {
                return false;
            }
        }

        return (state == acceptState) && (stack.peek() == '$');
    }
    /**
     * Clase que representa un par de estado y símbolo
     * */
    private class StateSymbolPair {
        private int state;
        private char symbol;
        private char stackSymbol;

        /**
         * Constructor de la clase StateSymbolPair
         * @param state
         * @param symbol
         * @param stackSymbol
         */
        public StateSymbolPair(int state, char symbol, char stackSymbol) {
            this.state = state;
            this.symbol = symbol;
            this.stackSymbol = stackSymbol;
        }

        public int getState() {
            return state;
        }

        public char getSymbol() {
            return symbol;
        }

        public char getStackSymbol() {
            return stackSymbol;
        }

        public boolean equals(Object o) {
            if (!(o instanceof StateSymbolPair)) {
                return false;
            }

            StateSymbolPair other = (StateSymbolPair) o;

            return (state == other.state) && (symbol == other.symbol)
                    && (stackSymbol == other.stackSymbol);
        }

        public int hashCode() {
            return (state * 7) + (symbol * 13) + (stackSymbol * 19);
        }
    }
    /**
     * Clase que representa una transición
     * */
    private class Transition {
        public char[] newStackSymbols;
        public int nextState;

        public Transition(char[] newStackSymbols, int nextState) {
            this.newStackSymbols = newStackSymbols;
            this.nextState = nextState;
        }
    }
}






