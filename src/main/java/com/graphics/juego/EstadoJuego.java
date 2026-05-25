/* JUEGO - EstadoJuego
    Define los estados principales del juego */
package com.graphics.juego;

public enum EstadoJuego {
    /** Menú principal del juego. */
    MENU,
    /** Partida de un jugador. */
    JUGANDO_P1,
    /** Partida de dos jugadores. */
    JUGANDO_P1P2,
    /** Pantalla de fin de partida. */
    GAME_OVER
}
