/* GRAFICOS_Y_RECURSOS - MeshTexture
    Malla con coordenadas UV para renderizar texturas */
package com.graphics.graficos_y_recursos;

import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL15;
import org.lwjgl.system.MemoryUtil;

import java.nio.FloatBuffer;

import static org.lwjgl.opengl.GL11.GL_FLOAT;
import static org.lwjgl.opengl.GL11.GL_TRIANGLE_FAN;
import static org.lwjgl.opengl.GL11.glDrawArrays;
import static org.lwjgl.opengl.GL15.*;
import static org.lwjgl.opengl.GL20.*;
import static org.lwjgl.opengl.GL30.*;

public class MeshTexture {
    /** ID del VAO que guarda la configuración de la malla. */
    private int vao;
    /** ID del VBO que almacena vértices y UVs. */
    private int vbo;
    /** Cantidad total de vértices. */
    private int vertexCount;

    /** **Constructor de malla texturizada**:
     * Convierte los vértices y coordenadas UV en datos para OpenGL.
     * @param vertices Datos de posición y textura. */
    public MeshTexture(VertexTexture[] vertices) {
        vertexCount = vertices.length;
        // Arreglo final de datos para OpenGL
        float[] data = new float[vertexCount * 4];
        int indice = 0;
        // Convierte VertexTexture en floats
        for (VertexTexture v : vertices) {
            // POSICIÓN X,Y
            data[indice++] = v.x;
            data[indice++] = v.y;
            // UV DE TEXTURA
            data[indice++] = v.u;
            data[indice++] = v.v;
        }
        // Crea el buffer temporal
        FloatBuffer buffer =BufferUtils.createFloatBuffer(data.length);
        buffer.put(data).flip();
        // VAO
        vao = glGenVertexArrays();
        glBindVertexArray(vao);
        // VBO
        vbo = glGenBuffers();
        glBindBuffer(GL_ARRAY_BUFFER, vbo);
        // Envía datos a la GPU
        glBufferData(GL_ARRAY_BUFFER, buffer, GL_DYNAMIC_DRAW);
        // POSICION
        glVertexAttribPointer(0, 2, GL_FLOAT, false, 4 * Float.BYTES, 0);
        glEnableVertexAttribArray(0);
        // UV
        glVertexAttribPointer(1, 2, GL_FLOAT, false, 4 * Float.BYTES, 2 * Float.BYTES);
        glEnableVertexAttribArray(1);
        // LIMPIEZA
        glBindBuffer(GL_ARRAY_BUFFER, 0);
        glBindVertexArray(0);
    }

    /** **Actualización de vértices**: Reemplaza posiciones y coordenadas UV. 
     * @param vertices Nuevos vértices. */
    public void actualizarVertices(VertexTexture[] vertices) {
        FloatBuffer buffer = MemoryUtil.memAllocFloat(vertices.length * 4);
        // Carga nuevos datos
        for (VertexTexture v : vertices) {
            buffer.put(v.x);
            buffer.put(v.y);
            buffer.put(v.u);
            buffer.put(v.v);
        }
        buffer.flip();
        // Activa el VBO
        GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, vbo);
        // Actualiza el contenido del buffer
        GL15.glBufferSubData(GL15.GL_ARRAY_BUFFER, 0, buffer);
        // Desactiva el VBO
        GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, 0);
        // Libera memoria RAM temporal
        MemoryUtil.memFree(buffer);
    }
    
    /** **Renderizado**: Dibuja la malla texturizada. */
    public void render() {
        // Activa el VAO
        glBindVertexArray(vao);
        // Dibuja la figura
        glDrawArrays(GL_TRIANGLE_FAN, 0, vertexCount);
        // Desactiva el VAO
        glBindVertexArray(0);
    }
}