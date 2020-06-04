package Buscamina;

public class Casillero {
    private boolean isMina = false;
    private int minasCerca = 0;
    private boolean activado = false;

    public void setActivado() {
        activado = true;
    }

    public boolean isActivado() {
        return activado;
    }

    public void setMina() {
        isMina = true;
    }

    public boolean isMina() {
        return isMina;
    }

    public void aumentarMinasCercanas() {
        minasCerca++;
    }
}
