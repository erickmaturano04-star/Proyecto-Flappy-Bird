/* JUEGO - GameManager
    Controla el estado actual entre menú y partida */
package com.graphics.juego;

public class GameManager {
    /** Indica si el juego está mostrando el menú principal. */
    private boolean enMenu = true;
    /** Indica si una partida está en ejecución. */
    private boolean enJuego = false;
    
    /** Inicializa el juego comenzando en el menú. */
    public GameManager() {
        enMenu = true;
    }

    /** @return true si el juego está en el menú. */
    public boolean estaEnMenu() {
        return enMenu;
    }

    /** @return true si una partida está activa. */
    public boolean estaEnJuego() {
        return enJuego;
    }
    
    /** **Iniciar partida**: Cambia del menú al estado de juego. */
    public void iniciarJuego() {
        enMenu = false;
        enJuego = true;
    }

    /** **Volver al menú**: Regresa al estado principal del juego. */
    public void volverMenu() {
        enMenu = true;
        enJuego = false;
    }
}