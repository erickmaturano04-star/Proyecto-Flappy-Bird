/* SISTEMA - Camara 
    Controla parte del mundo que se renderiza, transformando coordenadas
    del mundo a coordenadas de pantalla */
package com.graphics.sistema;

import org.joml.Matrix4f;
import org.joml.Vector2f;

public class Camera {
    /** **Posición global** de la cámara en el mundo. */
    private Vector2f posicion;
    /** **Nivel de zoom**: Mayor a 1.0 acerca la vista, menor la aleja. */
    private float zoom;

    /** Inicializa la cámara en el **origen (0,0)** con zoom estándar. */
    public Camera() {
        this.posicion = new Vector2f(0, 0);
        this.zoom = 1.0f;
    }

    /** Crea la **Matriz de Vista**.
     * Aplica escala y luego **traslación invertida** 
     * (el mundo se mueve opuesto a la cámara).
     * @return Matrix4f Matriz lista para enviar al Shader. */
    public Matrix4f getViewMatrix() {
        Matrix4f view = new Matrix4f();
        view.identity();
        // El zoom y la posición afectan a cómo vemos todo el mundo
        view.scale(zoom);
        view.translate(-posicion.x, -posicion.y, 0);
        return view;
    }

    /** **Desplaza la cámara** por el escenario.
     * @param dx Distancia en el eje X.
     * @param dy Distancia en el eje Y. */
    public void mover(float dx, float dy) {
        posicion.add(dx, dy);
    }

    /** @return **Vector2f** con las coordenadas actuales del mundo. */
    public Vector2f getPosicion() { 
        return posicion; 
    }
}