/* GRAFICOS_Y_RECURSOS - Sprite
    Representa una imagen renderizable con textura y coordenadas UV */
package com.graphics.graficos_y_recursos;

public class Sprite {
    /** Malla usada para dibujar el sprite. */
    private MeshTexture mesh;
    /** Textura asociada al sprite. */
    private Texture texture;
    /** Posición y tamaño del sprite. */
    private float x, y, ancho, alto;

    /** **Constructor de sprite**: 
     * Crea una imagen texturizada usando coordenadas UV.
     * @param ruta Ruta de la textura.
     * @param x Posición horizontal.
     * @param y Posición vertical.
     * @param ancho Anchura del sprite.
     * @param alto Altura del sprite.
     * @param uMin Inicio horizontal de la textura.
     * @param vMin Inicio vertical de la textura.
     * @param uMax Fin horizontal de la textura.
     * @param vMax Fin vertical de la textura. */
    public Sprite(String ruta, float x, float y,
                float ancho, float alto,
                float uMin, float vMin,
                float uMax, float vMax) {
        this.x = x;
        this.y = y;
        this.ancho = ancho;
        this.alto = alto;
        // Carga la textura desde archivo
        texture = new Texture(ruta);
        // Creacion de vertices
        VertexTexture[] vertices = {
            // SUPERIOR IZQUIERDA
            new VertexTexture(x, y, uMin, vMin),
            // SUPERIOR DERECHA
            new VertexTexture(x + ancho, y, uMax, vMin),
            // INFERIOR DERECHA
            new VertexTexture(x + ancho, y - alto, uMax, vMax),
            // INFERIOR IZQUIERDA
            new VertexTexture(x, y - alto, uMin, vMax)
        };    
        // Crea la malla texturizada
        mesh = new MeshTexture(vertices);
    }

    /** **Cambio de UV**: Modifica la región visible de la textura.
     * @param u1 Inicio horizontal UV.
     * @param v1 Inicio vertical UV.
     * @param u2 Fin horizontal UV.
     * @param v2 Fin vertical UV. */
    public void cambiarUV(float u1, float v1,
                          float u2, float v2) {
        // Nuevos vértices con coordenadas UV actualizadas
        VertexTexture[] vertices = {
            new VertexTexture(x, y, u1, v1),
            new VertexTexture(x + ancho, y, u2, v1),
            new VertexTexture(x + ancho, y - alto, u2, v2),
            new VertexTexture(x, y - alto, u1, v2)
        };
        // Actualiza la malla
        mesh.actualizarVertices(vertices);
    }

    // GETTERS
    public MeshTexture getMesh() {
        return mesh;
    }
    public Texture getTexture() {
        return texture;
    }
}