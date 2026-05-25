/* JUEGO - SistemaPuntos
    Administra el puntaje de los jugadores */
package com.graphics.juego;

public class SistemaPuntos {
    /** Puntaje acumulado del jugador 1. */
    private int puntosP1;
    /** Puntaje acumulado del jugador 2. */
    private int puntosP2;

    /** **Sumar punto**: Incrementa el puntaje del jugador 1. */
    public void sumarP1() {
        puntosP1++;
    }

    /** **Sumar punto**: Incrementa el puntaje del jugador 2. */
    public void sumarP2() {
        puntosP2++;
    }

    /** @return Puntaje actual del jugador 1. */
    public int getPuntosP1() {
        return puntosP1;
    }

    /** @return Puntaje actual del jugador 2. */
    public int getPuntosP2() {
        return puntosP2;
    }

    /** **Reinicio**: Restablece ambos puntajes a cero. */
    public void reset() {
        puntosP1 = 0;
        puntosP2 = 0;
    }
}