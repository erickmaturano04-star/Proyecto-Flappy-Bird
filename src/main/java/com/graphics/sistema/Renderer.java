/* SISTEMA - Renderer
    El que ejecuta las ordenes de dibujo */
package com.graphics.sistema;

import com.graphics.graficos_y_recursos.Mesh;
import com.graphics.graficos_y_recursos.Shader;
import com.graphics.graficos_y_recursos.Color;
import com.graphics.matematicas.Transform;
import org.joml.Matrix4f;
import org.lwjgl.opengl.GL20;

public class Renderer {
    
    /** **Proceso de Renderizado**: Dibuja una malla aplicando transformaciones y color.
     * @param camera Cámara para obtener la matriz de vista.
     * @param mesh La geometría (vértices) a dibujar.
     * @param shader El programa que procesa los píxeles.
     * @param transform Datos de posición, rotación y escala.
     * @param color Tinte cromático para el objeto. */
    public void render(Camera camera, Mesh mesh, Shader shader, 
                        Transform transform, Color color) {
        // Activa el shader actual
        shader.usar();
        // Crea la Matriz de Modelo (Posición, Rotación, Escala del objeto)
        Matrix4f modelMatrix = new Matrix4f()
            .translation(transform.posicion.x, transform.posicion.y, 0)
            .rotateZ((float) Math.toRadians(transform.rotacion))
            .scale(transform.escala.x, transform.escala.y, 1);
        // Envia Matrices al Shader
        int locModel = GL20.glGetUniformLocation(shader.getProgramID(), "uModel");
        int locView = GL20.glGetUniformLocation(shader.getProgramID(), "uView");
        int locColor = GL20.glGetUniformLocation(shader.getProgramID(), "uColor");
        // Buffer temporal para enviar matrices a OpenGL
        float[] buffer = new float[16];
        // Envía la matriz del objeto al shader
        GL20.glUniformMatrix4fv(locModel, false, modelMatrix.get(buffer));
        // Envía la matriz de cámara al shader
        GL20.glUniformMatrix4fv(locView, false, camera.getViewMatrix().get(buffer));
        // Envia el Color del Objeto
        GL20.glUniform4f(locColor, color.r, color.g, color.b, color.a);
        // Dibuja la geometria
        mesh.dibujar();
        // Libera el shader para el siguiente renderizado
        shader.detener();
    }
}