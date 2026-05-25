/* GRAFICOS_Y_RECURSOS - Color
    Definicion de colores (RGBA) */
package com.graphics.graficos_y_recursos;

public class Color {
    /** Componentes de color: 
     * **R** (Rojo), 
     * **G** (Verde), 
     * **B** (Azul), 
     * **A** (Transparencia). */
    public float r, g, b, a;

    /** Crea un color personalizado.
     * @param r Intensidad de rojo (0.0 a 1.0).
     * @param g Intensidad de verde (0.0 a 1.0).
     * @param b Intensidad de azul (0.0 a 1.0).
     * @param a Nivel de opacidad (0.0 transparente, 1.0 opaco). */
    public Color(float r, float g, float b, float a) {
        this.r = r;
        this.g = g;
        this.b = b;
        this.a = a;
    }

    // Colores PreDefinidos
    public static final Color BLANCO = new Color(1, 1, 1, 1);
    public static final Color NEGRO = new Color(0, 0, 0, 1);
    public static final Color ROJO = new Color(1.0f, 0.0f, 0.0f, 0.0f);
    public static final Color VERDE = new Color(0.0f, 1.0f, 0.0f, 0.0f);
    public static final Color AZUL = new Color(0.0f, 0.0f, 1.0f, 0.0f);
}