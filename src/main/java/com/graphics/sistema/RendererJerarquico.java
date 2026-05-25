/* SISTEMA - RendererJerarquico 
    Renderiza objetos compuestos usando transformacion jerarquica */
package com.graphics.sistema;

import com.graphics.graficos_y_recursos.Shader;
import org.joml.Matrix4f;
import java.util.List;

public class RendererJerarquico {

    /** **Renderizado jerárquico**: Dibuja el objeto usando una matriz identidad inicial.
     * @param objeto Objeto principal a renderizar.
     * @param shader Shader utilizado para el dibujo. */
    public static void dibujar(IDibujable objeto, Shader shader) {
        dibujar(objeto, shader, new Matrix4f().identity());
    }

    /** **Renderizado recursivo**: Dibuja objetos y sus partes aplicando matrices padre-hijo.
     * @param objeto Objeto actual a renderizar.
     * @param shader Shader utilizado.
     * @param parentMatrix Matriz heredada del objeto padre. */
    public static void dibujar(IDibujable objeto, Shader shader, Matrix4f parentMatrix) {
        // Verifica si el objeto existe
        if (objeto == null)     return;
        // Obtiene las partes del objeto
        List<Object> piezas = objeto.getPartes();
        // Verifica si es una pieza individual
        if (piezas == null || piezas.isEmpty() || (piezas.size() == 1 && piezas.get(0) == objeto)) {
            renderizarPiezaIndependiente(objeto, shader, parentMatrix);
            return;
        }
        // Copia la matriz del padre
        Matrix4f currentMatrix = new Matrix4f(parentMatrix);
        try {
            // Obtiene el transform del objeto
            var transform = objeto.getClass().getMethod("getTransform").invoke(objeto);
            // Aplica la matriz local del objeto
            if (transform != null) {
                var modelMatrix = (Matrix4f) transform.getClass().getMethod("getModelMatrix").invoke(transform);
                currentMatrix.mul(modelMatrix);
            }
        } catch (Exception e) {
            // Ignoramos si no tiene transform global
        }
        // Renderiza cada pieza hija
        for (Object pieza : piezas) {
            // Si la pieza también es jerárquica
            if (pieza instanceof IDibujable && pieza != objeto) {
                dibujar((IDibujable) pieza, shader, currentMatrix);
            } else {
                // Si es una pieza simple
                renderizarPiezaIndependiente(pieza, shader, currentMatrix);
            }
        }
    }

    /** **Renderizado individual**:
     * Dibuja una pieza usando la matriz final calculada.
     * @param pieza Objeto individual a renderizar.
     * @param shader Shader utilizado.
     * @param parentMatrix Matriz heredada del padre. */
    private static void renderizarPiezaIndependiente(Object pieza, Shader shader, Matrix4f parentMatrix) {
        // Verifica si la pieza existe
        if (pieza == null)      return;
        try {
            // Extracción de Transform y Matriz de Modelo local de la pieza
            var transform = pieza.getClass().getMethod("getTransform").invoke(pieza);
            Matrix4f localModelMatrix = null;
            if (transform != null) {
                localModelMatrix = (Matrix4f) transform.getClass().getMethod("getModelMatrix").invoke(transform);
            } else {
                localModelMatrix = new Matrix4f().identity();
            }
            // Combina matriz padre + matriz local
            Matrix4f finalMatrix = new Matrix4f(parentMatrix).mul(localModelMatrix);
            // Envía la matriz final al shader
            shader.setUniform("uModel", finalMatrix);
            // Obtiene la malla de la pieza
            var mesh = pieza.getClass().getMethod("getMesh").invoke(pieza);
            // Dibuja la malla
            mesh.getClass().getMethod("dibujar").invoke(mesh);
        } catch (Exception e) {
            System.err.println("Error al renderizar pieza jerárquica: " + e.getMessage());
        }
    }
}
