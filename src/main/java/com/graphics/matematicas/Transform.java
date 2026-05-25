/* MATEMATICAS - Transform
    Define la Posicion, Rotacion y Escala de un objeto */
package com.graphics.matematicas;

import org.joml.Vector2f;
import org.joml.Matrix4f;

public class Transform {
    /** **Ubicación** del objeto en el espacio 2D (X, Y). */
    public Vector2f posicion;
    /** **Tamaño** del objeto. (1, 1) es el tamaño original. */
    public Vector2f escala;
    /** **Ángulo de giro** en grados. */
    public float rotacion;

    /** Constructor por defecto: Objeto en el origen, sin rotación y tamaño real. */
    public Transform() {
        this.posicion = new Vector2f(0, 0);
        this.escala = new Vector2f(1, 1);
        this.rotacion = 0;
    }

    /** Constructor personalizado para definir el estado inicial. */
    public Transform(Vector2f posicion, Vector2f escala, float rotacion) {
        this.posicion = posicion;
        this.escala = escala;
        this.rotacion = rotacion;
    }

    /** **Matriz de Modelo**: Combina posición, rotación y escala en una sola matriz.
     * @return **Matrix4f** Matriz que posiciona al objeto en el mundo. */
    public Matrix4f getModelMatrix() {
        Matrix4f model = new Matrix4f();
        model.identity();
        model.translate(posicion.x, posicion.y, 0);
        model.rotate((float)Math.toRadians(rotacion), 0, 0, 1);
        model.scale(escala.x, escala.y, 1);
        return model;
    }
}