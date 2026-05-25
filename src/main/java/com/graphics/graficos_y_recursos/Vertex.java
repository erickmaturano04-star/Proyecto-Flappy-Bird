/* GRAFICOS_Y:RECURSOS - Vertex
    Define un punto en 2D usado para construir figuras */
package com.graphics.graficos_y_recursos;

import org.joml.Vector2f;

public class Vertex {
    /** **Coordenadas**: Ubicación del punto en los ejes X e Y. */
    public Vector2f posicion;

    /** Crea un nuevo vértice a partir de coordenadas individuales. 
     * @param x Posición en el eje horizontal.
     * @param y Posición en el eje vertical. */
    public Vertex(float x, float y) {
        this.posicion = new Vector2f(x, y);
    }
}