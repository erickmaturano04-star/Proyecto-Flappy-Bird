/* GRAFICOS_Y_RECURSOS - VertexTexture
    Define un vertice con posicion y coordenadas UV */
package com.graphics.graficos_y_recursos;

public class VertexTexture {
    public float x;     //Posicion Horizontal del vertice
    public float y;     //Posicion Vertical del vertice
    public float u;     //Coordenada Horizontal de la Textura (UV)
    public float v;     //Coordenada Verical de la Textura (UV)

    /** **Constructor de vértice texturizado**:
     * Crea un punto con posición y coordenadas UV.
     * @param x Posición horizontal.
     * @param y Posición vertical.
     * @param u Coordenada horizontal UV.
     * @param v Coordenada vertical UV. */
    public VertexTexture(float x, float y, float u, float v) {
        this.x = x;
        this.y = y;
        this.u = u;
        this.v = v;
    }
}