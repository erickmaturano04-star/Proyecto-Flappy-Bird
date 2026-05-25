/* FISICA - CirculoColision
    Detecta colisiones circulares contra cajas rectangulares */
package com.graphics.fisica;

public class CirculoColision {
    private float x;
    private float y;
    private float radio;

    /** **Constructor de colisión**:
     * Define posición y tamaño del círculo.
     * @param x Posición horizontal.
     * @param y Posición vertical.
     * @param radio Tamaño del radio. */
    public CirculoColision(float x, float y, float radio) {
        this.x = x;
        this.y = y;
        this.radio = radio;
    }

    /** **Detección de colisión**:
     * Verifica si el círculo toca una caja rectangular.
     * @param caja Caja a comparar.
     * @return true si existe colisión. */
    public boolean colisiona(CajaColision caja) {
        // Busca el punto más cercano del rectángulo al círculo
        float puntoX = 
            Math.max(caja.getX(),
                    Math.min(x, caja.getX() + caja.getAncho()));
        float puntoY =
            Math.max(caja.getY(),
                    Math.min(y, caja.getY() + caja.getAlto()));
        // Distancia horizontal entre círculo y punto cercano
        float dx = x - puntoX;
        // Distancia vertical entre círculo y punto cercano
        float dy = y - puntoY;
        // Verifica si el punto está dentro del radio
        return (dx * dx + dy * dy) < (radio * radio);
    }

    // GETTERS
    public float getX() {
        return x;
    }
    public float getY() {
        return y;
    }
    public float getRadio() {
        return radio;
    }

    // SETTERS
    public void setX(float x) {
        this.x = x;
    }
    public void setY(float y) {
        this.y = y;
    }
}