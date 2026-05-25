/* SISTEMA - ObjectRenderer
    Al separar esta lógica, evitamos que los moldes 
    (como Arbol, Cielo, o cualquier otro objeto) contengan código 
    de bajo nivel de la GPU. */
package com.graphics.sistema;

import com.graphics.graficos_y_recursos.Shader;
import java.util.List;

public class ObjectRenderer {

    /** Renderiza un objeto complejo descomponiéndolo en sus partes simples.
     * @param objeto El molde que contiene las piezas (debe implementar IDibujable).
     * @param shader El programa de sombreado activo para aplicar las transformaciones. */
    public static void dibujar(IDibujable objeto, Shader shader) {
        if (objeto == null)     return;
        // Obtenemos la lista de piezas que conforman el molde
        List<Object> piezas = objeto.getPartes();
        if (piezas == null || piezas.isEmpty()) {
            renderizarPiezaIndependiente(objeto, shader);
            return;
        }
        for (Object pieza : piezas) {
            if (pieza instanceof IDibujable && pieza != objeto) {
                dibujar((IDibujable) pieza, shader);
            } else {
                renderizarPiezaIndependiente(pieza, shader);
            }
        }
    }

    /** Se encarga de la comunicación directa con los Shaders y OpenGL.
     * Extrae el Transform para la posición y el Mesh para el dibujo físico. */
    private static void renderizarPiezaIndependiente(Object pieza, Shader shader) {
        if (pieza == null)  return;
        try {
            // Extracción de Transform y Matriz de Modelo
            var transform = pieza.getClass().getMethod("getTransform").invoke(pieza);
            var modelMatrix = transform.getClass().getMethod("getModelMatrix").invoke(transform);
            // Enviamos la matriz de posición del objeto al Shader
            shader.setUniform("uModel", (org.joml.Matrix4f) modelMatrix);
            // Dibujo de la Malla (Mesh)
            var mesh = pieza.getClass().getMethod("getMesh").invoke(pieza);
            mesh.getClass().getMethod("dibujar").invoke(mesh);
        } catch (Exception e) {
            // Si una pieza no cumple con la estructura esperada, se ignora silenciosamente
            // Esto evita que el juego se detenga si una parte del molde está mal definida.
            System.err.println("Error al renderizar pieza: " + e.getMessage());
        }
    }
}