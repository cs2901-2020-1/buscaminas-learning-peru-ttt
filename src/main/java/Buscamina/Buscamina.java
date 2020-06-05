package Buscamina;

import java.util.Random;
import java.util.Scanner;

public class Buscamina {
    private int N;
    private int Minas;
    private int Turno = 0;
    private int casillas_activadas=0;
    private Casillero[][] Matrix;
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
            //Scanner scanner = new Scanner(System.in);
            int x;
            do {
                x = rand.nextInt(N * N);
                //System.out.print("x = " + (1 + x/N) + " y = " + (1 + x%N));
            } while (Matrix[1 + x/N][1 + x%N].isMina());

            Matrix[1 + x/N][1 + x%N].setMina();
            sumarMinasCercanas(1 + x/N, 1 + x%N);
        }
    }

    public boolean Ganador() {
        if (Minas == (N*N) - casillas_activadas)
            return false;
        return true;
    }

    public void inicializar() {
        Matrix = new Casillero[N+2][N+2];
        for (int i = 0; i < N+2; ++i) {
            for (int j = 0; j < N+2; ++j) {
                Matrix[i][j] = new Casillero();
            }
        }
    }

    public void empezarJuego() {
        System.out.print("Inserte la cantidad de columnas y filas que desea = ");

        Scanner scanner = new Scanner(System.in);
        N = scanner.nextInt();

        System.out.print("Inserte la cantidad de minas que desea = ");
        do {
            Minas = scanner.nextInt();
        } while (Minas > N*N || Minas < 0);

        inicializar();

        setJuego();

    }

    public void print() {
        for (int i = 1; i < N + 1; ++i) {
            for (int j = 1; j < N + 1; ++j) {
                if (!Matrix[i][j].isActivado()) {
                    System.out.print("X ");
                }
                else {
                    System.out.print(Matrix[i][j].getMinasCerca());
                    System.out.print(" ");
                }
            }
            System.out.print("\n");
        }
    }

    public void activarCeros(int x,int y){
        if(Matrix[x][y].isActivado()  || x<=0 || x>N || y==0 || y>N){
        }
        else{
            Matrix[x][y].setActivado();
            casillas_activadas++;
            if ( Matrix[x][y].getMinasCerca()>0){
                return;
            }
            activarCeros(x+1,y);
            activarCeros(x-1,y);
            activarCeros(x,y+1);
            activarCeros(x,y-1);
            activarCeros(x+1,y+1);
            activarCeros(x+1,y-1);
            activarCeros(x-1,y+1);
            activarCeros(x-1,y-1);
        }
    }

    public boolean jugar(int x, int y) {
        if (Matrix[x][y].isMina())
            return false;

        if (Matrix[x][y].isActivado())
            return true;


        //TODO: Activar ceros
        activarCeros(x, y);

        print();
        Turno++;
        return true;
    }
}