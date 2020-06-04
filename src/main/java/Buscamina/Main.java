package Buscamina;

import java.util.Scanner;

public class Main {
    public static void main(String[] Args){
        Buscamina buscamina = new Buscamina();
        buscamina.empezarJuego();
        int x, y, Minas;

        do {
            System.out.println("Inserte X y Y = ");

            Scanner scanner = new Scanner(System.in);
            x = scanner.nextInt();
            y = scanner.nextInt();

        } while (buscamina.jugar(x, y) && buscamina.casillerosRestantes());
    }
}
