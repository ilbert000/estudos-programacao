public class TabelaVerdade {
    static void main() {
        boolean[] valores = {true, false};

        for(int a = 0; a < 2; a++){
            for(int b = 0; b < 2; b++){
                for (int c = 0; c < 2; c++){

                    boolean d = valores[a];
                    boolean e = valores[b];
                    boolean f = valores[c];

                    System.out.println("A: " + d +
                            " B: " + e +
                            " C: " + f +
                            " A, B e C: " + (d && e && f));
                }
            }
        }
    }
}
