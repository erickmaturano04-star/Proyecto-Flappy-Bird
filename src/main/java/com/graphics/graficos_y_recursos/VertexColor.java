/* GRAFICOS_Y_RECURSOS - VertexColor
    Define un vertice con posicion y color */
package com.graphics.graficos_y_recursos;

import org.joml.Vector2f;

public class VertexColor {
    /** **Coordenadas**: Ubicación espacial del vértice (X, Y). */
    public Vector2f posicion;
    /** **Atributo de Color**: El color específico asignado a este punto exacto. */
    public Color color;

    /** Crea un punto con ubicación y color definidos.
     * @param x     Posición en el eje horizontal.
     * @param y     Posición en el eje vertical.
     * @param color Instancia de la clase {@link Color} (RGBA). */
    public VertexColor(float x, float y, Color color) {
        // Inicializa una posicion
        this.posicion = new Vector2f(x, y);
        // Asigna un color
        this.color = color;
    }
}