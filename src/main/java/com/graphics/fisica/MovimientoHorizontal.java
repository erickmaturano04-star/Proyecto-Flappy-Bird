/* FISICA - MovimientoHorizontal
    Desplaza objetos horizontalmente y los reinicia al salir de pantalla */
package com.graphics.fisica;

public class MovimientoHorizontal {
    private float velocidad;        //Velocidad de Desplazamiento Horizontal
    private float limiteIzquierdo;  //Limite Izquierdo Permitido en Pantalla
    private float respawnX;         //Posicion donde el objeto reaparece

    /** **Constructor de movimiento**: Configura velocidad y límites de desplazamiento.
     * @param velocidad Velocidad horizontal.
     * @param limiteIzquierdo Límite de salida.
     * @param respawnX Nueva posición al reaparecer. */
    public MovimientoHorizontal(float velocidad,
                                float limiteIzquierdo,
                                float respawnX) {
        this.velocidad = velocidad;
        this.limiteIzquierdo = limiteIzquierdo;
        this.respawnX = respawnX;
    }

    /** **Movimiento horizontal**: Desplaza el objeto hacia la izquierda.
     * Si el objeto sale de pantalla, vuelve a aparecer en respawnX.
     * @param x Posición horizontal actual.
     * @param ancho Tamaño horizontal del objeto.
     * @param deltaTime Tiempo entre frames.
     * @return Nueva posición horizontal. */
    public float mover(float x, float ancho, float deltaTime) {
        // Mueve el objeto hacia la izquierda
        x -= velocidad * deltaTime;
        // Verifica si salió completamente de pantalla
        if (x + ancho < limiteIzquierdo) {
            // Reinicia la posición
            x = respawnX;
        }
        return x;
    }

    // GETTERS
    public float getVelocidad() {
        return velocidad;
    }
    
    // SETTERS
    public void setVelocidad(float velocidad) {
        this.velocidad = velocidad;
    }
}