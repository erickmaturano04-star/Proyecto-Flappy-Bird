/* UI - Puntuacion
    Muestra en pantalla los puntos de cada jugador usando sprites */
package com.graphics.juego.ui;

import com.graphics.graficos_y_recursos.ShaderTexture;
import com.graphics.graficos_y_recursos.Sprite;
import com.graphics.sistema.ObjectRendererTexture;

public class Puntuacion {
    // TEXTURAS
    private static final String TEX_NUMEROS = "/textures/numeros.png";
    private static final String TEX_JUGADOR = "/textures/jugador.png";

    // MEDIDAS TEXTURAS
    // --- numeros.png ---
    // Tamaño real de la textura de numeros
    private static final float NUM_TEX_W = 96f;
    private static final float NUM_TEX_H = 32f;
    // Tamaño de cada número dentro de la textura.
    private static final float NUM_W = 12f;
    private static final float NUM_H = 16f;
    // --- jugador.png ---
    private static final float JUGADOR_TEX_W = 90f;
    //private static final float JUGADOR_TEX_H = 11f;

    // POSICIONES UI
    // --- Titulo ---
    private static final float Y_TITULO = 0.92f;
    private static final float X_TITULO_P1 = -0.92f;
    private static final float X_TITULO_P2 = 0.05f;
    // --- Puntos ---
    private static final float Y_PUNTOS = 0.80f;
    private static final float X_PUNTOS_P1 = -0.84f;
    private static final float X_PUNTOS_P2 = 0.13f;

    // ESCALAS
    private static final float ANCHO_TITULO = 0.34f;
    private static final float ALTO_TITULO = 0.08f;
    private static final float ANCHO_NUMERO = 0.08f;
    private static final float ALTO_NUMERO = 0.12f;
    // separación entre dígitos
    private static final float ESPACIO_NUMERO = 0.07f;

    // SPRITES
    private Sprite tituloP1;
    private Sprite tituloP2;

    // PUNTAJES
    private int puntosP1;
    private int puntosP2;

    /** **Constructor**: Inicializa los títulos de la interfaz. */
    public Puntuacion() {
        crearTitulos();
    }

    /** **Crear Titulos**: Crea los sprites de los títulos de jugadores. */
    private void crearTitulos() {
        // jugador1 = izquierda
        tituloP1 = new Sprite(
                TEX_JUGADOR,
                X_TITULO_P1,
                Y_TITULO,
                ANCHO_TITULO,
                ALTO_TITULO,
                0f,
                0f,
                45f / JUGADOR_TEX_W,
                1f
        );
        // jugador2 = derecha
        tituloP2 = new Sprite(
                TEX_JUGADOR,
                X_TITULO_P2,
                Y_TITULO,
                ANCHO_TITULO,
                ALTO_TITULO,
                45f / JUGADOR_TEX_W,
                0f,
                90f / JUGADOR_TEX_W,
                1f
        );
    }

    // SETTERS
    public void setPuntos(int p1, int p2) {
        this.puntosP1 = p1;
        this.puntosP2 = p2;
    }

    /** **Renderizar**: Dibuja títulos y puntajes en pantalla. */
    public void renderizar(ShaderTexture shaderTexture) {
        // Titulos
        ObjectRendererTexture.dibujar(tituloP1, shaderTexture);
        ObjectRendererTexture.dibujar(tituloP2, shaderTexture);
        // Puntos
        renderizarNumero(
                puntosP1,
                X_PUNTOS_P1,
                Y_PUNTOS,
                shaderTexture
        );
        renderizarNumero(
                puntosP2,
                X_PUNTOS_P2,
                Y_PUNTOS,
                shaderTexture
        );
    }

    /** **Renderizar Numero**: Dibuja un número completo dígito por dígito. */
    private void renderizarNumero(int numero, float x, float y,
                                ShaderTexture shaderTexture) {
        String texto = String.valueOf(numero);
        for (int i = 0; i < texto.length(); i++) {
            int digito = texto.charAt(i) - '0';
            Sprite spriteNumero = 
                crearSpriteNumero(digito,
                                x + (i * ESPACIO_NUMERO),
                                y);
            ObjectRendererTexture.dibujar(
                    spriteNumero,
                    shaderTexture
            );
        }
    }

    /** **Crear Sprite del Numero**: 
     * Crea el sprite correspondiente al dígito indicado. */
    private Sprite crearSpriteNumero(int numero, float x, float y) {
        int indice;
        // La textura empieza en 1 y termina en 0
        if (numero == 0) {
            indice = 9;
        } else {
            indice = numero - 1;
        }
        
        // Calcula fila y columna dentro de la textura
        int columna = indice % 8;
        int fila = indice / 8;
        // Coordenadas UV del número
        float u1 = (columna * NUM_W) / NUM_TEX_W;
        float v1 = (fila * NUM_H) / NUM_TEX_H;
        float u2 = ((columna * NUM_W) + NUM_W) / NUM_TEX_W;
        float v2 = ((fila * NUM_H) + NUM_H) / NUM_TEX_H;

        return new Sprite(
                TEX_NUMEROS,
                x,
                y,
                ANCHO_NUMERO,
                ALTO_NUMERO,
                u1,
                v1,
                u2,
                v2
        );
    }
}