/* FISICA - CajaColision
    Detecta colisiones rectangulares entre objetos */
package com.graphics.fisica;

public class CajaColision {
    private float x, y, ancho, alto;

    /** **Constructor de colisión**: Define posición y tamaño de la caja.
     * @param x Posición horizontal.
     * @param y Posición vertical.
     * @param ancho Anchura de la caja.
     * @param alto Altura de la caja. */
    public CajaColision(
            float x,
            float y,
            float ancho,
            float alto) {
        this.x = x;
        this.y = y;
        this.ancho = ancho;
        this.alto = alto;
    }

    /** **Detección de colisión**:
     * Verifica si esta caja toca otra caja.
     * @param otra Caja a comparar.
     * @return true si ambas cajas colisionan. */
    public boolean colisiona(CajaColision otra) {
        return
            x < otra.x + otra.ancho &&
            x + ancho > otra.x &&
            y < otra.y + otra.alto &&
            y + alto > otra.y;
    }

    // GETTERS
    public float getX() {
        return x;
    }
    public float getY() {
        return y;
    }
    public float getAncho() {
        return ancho;
    }
    public float getAlto() {
        return alto;
    }

    // SETTERS
    public void setX(float x) {
        this.x = x;
    }
    public void setY(float y) {
        this.y = y;
    }
}