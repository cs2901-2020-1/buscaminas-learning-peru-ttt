package Buscamina;

import java.util.Random;
import java.util.Scanner;

public class Buscamina {
    private int N;
    private int Minas;
    private int Turno = 0;
    private Casillero[][] Matrix = new Casillero [N+2][N+2];
    Random rand = new Random();

    public int getMinas() {
        return Minas;
    }

    public void sumarMinasCercanas(int x, int y){
        Matrix[x + 1][y].aumentarMinasCercanas();
        Matrix[x - 1][y].aumentarMinasCercanas();
        Matrix[x][y + 1].aumentarMinasCercanas();
        Matrix[x][y - 1].aumentarMinasCercanas();
        Matrix[x + 1][y + 1].aumentarMinasCercanas();
        Matrix[x + 1][y - 1].aumentarMinasCercanas();
        Matrix[x - 1][y + 1].aumentarMinasCercanas();
        Matrix[x - 1][y - 1].aumentarMinasCercanas();
    }

    public void setJuego() {
        for (int i = 0; i < Minas; ++i) {
            int x;
            do {
                x = rand.nextInt(N * N);
            } while (Matrix[1 + x/N][1 + x%N].isMina());

            Matrix[1 + x/N][1 + x%N].setMina();
            sumarMinasCercanas(1 + x/N, 1 + x%N);
        }
    }

    public boolean casillerosRestantes() {
        if (Turno == (N*N) - Minas)
            return false;
        return true;
    }

    public void empezarJuego() {
        System.out.println("Inserte la cantidad de columnas y filas que desea = ");

        Scanner scanner = new Scanner(System.in);
        N = scanner.nextInt();

        System.out.println("Inserte la cantidad de minas que desea = ");
        do {
            Minas = scanner.nextInt();
        } while (Minas > N*N || Minas < 0);

        setJuego();

    }

    public boolean jugar(int x, int y) {
        if (Matrix[x][y].isMina())
            return false;

        if (Matrix[x][y].isActivado())
            return true;

        Matrix[x][y].setActivado();

        //TODO: Activar ceros
        //activarCeros(x, y);

        Turno++;
        return true;
    }
}