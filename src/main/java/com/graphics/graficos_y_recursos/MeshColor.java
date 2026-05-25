/* GRAFICOS_Y_RECURSOS - MeshColor
    Malla con colores por vertice para renderizado en OpenGL */
package com.graphics.graficos_y_recursos;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL15;
import org.lwjgl.opengl.GL20;
import org.lwjgl.opengl.GL30;
import org.lwjgl.system.MemoryUtil;

import java.nio.FloatBuffer;

public class MeshColor {
    /** **VAO (Vertex Array Object)**: 
     * ID del contenedor que guarda la configuración de la malla. */
    private int vaoID;
    /** **VBO (Vertex Buffer Object)**: 
     * ID del buffer que contiene los datos numéricos de los vértices. */
    private int vboID;
    /** Número total de puntos a dibujar. */
    private int cantidadVertices;

    /** **Constructor de malla**: Crea una geometría con posición y color.
     * @param vertices Arreglo de VertexColor (Contiene posición y color). */
    public MeshColor(VertexColor[] vertices) {
        cantidadVertices = vertices.length;
        // VAO
        vaoID = GL30.glGenVertexArrays();
        GL30.glBindVertexArray(vaoID);
        // BUFFER
        FloatBuffer buffer = 
            MemoryUtil.memAllocFloat(vertices.length * 6);
        for (VertexColor v : vertices) {
            // POSICION
            buffer.put(v.posicion.x);
            buffer.put(v.posicion.y);
            // COLOR
            buffer.put(v.color.r);
            buffer.put(v.color.g);
            buffer.put(v.color.b);
            buffer.put(v.color.a);
        }
        buffer.flip();
        // VBO
        vboID = GL15.glGenBuffers();
        GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, vboID);
        // Envía los datos a la GPU
        GL15.glBufferData(GL15.GL_ARRAY_BUFFER, buffer, GL15.GL_DYNAMIC_DRAW);
        // ATRIBUTO 0 -> POSICION
        GL20.glVertexAttribPointer(0, 2, GL11.GL_FLOAT, false, 
                                    6 * Float.BYTES, 0);
        GL20.glEnableVertexAttribArray(0);
        // ATRIBUTO 1 -> COLOR
        GL20.glVertexAttribPointer(1, 4, GL11.GL_FLOAT, false,
                                    6 * Float.BYTES, 2 * Float.BYTES);
        GL20.glEnableVertexAttribArray(1);
        // LIMPIEZA
        GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, 0);
        GL30.glBindVertexArray(0);
        // Libera memoria RAM usada temporalmente
        MemoryUtil.memFree(buffer);
    }

    /** **Dibujado de malla**: Renderiza la geometría con sus colores interpolados. */
    public void dibujar() {
        // Activa la configuración de la malla
        GL30.glBindVertexArray(vaoID);
        // Dibuja triángulos usando los vértices
        GL11.glDrawArrays(GL11.GL_TRIANGLES, 0, cantidadVertices);
        // Desactiva el VAO
        GL30.glBindVertexArray(0);
    }

    /** **Actualización de vértices**:
     * Reemplaza los datos actuales de posición y color.
     * @param vertices Nuevos vértices. */
    public void actualizarVertices(VertexColor[] vertices) {
        FloatBuffer buffer =
        MemoryUtil.memAllocFloat(vertices.length * 6);
        // Carga nuevos datos
        for (VertexColor v : vertices) {
            buffer.put(v.posicion.x);
            buffer.put(v.posicion.y);
            buffer.put(v.color.r);
            buffer.put(v.color.g);
            buffer.put(v.color.b);
            buffer.put(v.color.a);
        }
        buffer.flip();
        // Activa el VBO
        GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, vboID);
        // Actualiza el contenido del buffer
        GL15.glBufferSubData(GL15.GL_ARRAY_BUFFER, 0, buffer);
        // Desactiva el buffer
        GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, 0);
        // Libera memoria temporal
        MemoryUtil.memFree(buffer);
    }

    /** **Gestión de Memoria**: Libera los recursos y buffers de la GPU. */
    public void destruir() {
        // Desactiva atributos de vértice
        GL20.glDisableVertexAttribArray(0);
        GL20.glDisableVertexAttribArray(1);
        // Elimina el VBO
        GL15.glDeleteBuffers(vboID);
        // Elimina el VAO
        GL30.glDeleteVertexArrays(vaoID);
    }
}