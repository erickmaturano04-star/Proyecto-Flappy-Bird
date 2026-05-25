/* GRAFICOS_Y_RECURSOS - Mesh
    Convierte vértices en datos que OpenGL puede renderizar */
package com.graphics.graficos_y_recursos;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL15;
import org.lwjgl.opengl.GL20;
import org.lwjgl.opengl.GL30;
import org.lwjgl.system.MemoryUtil;
import java.nio.FloatBuffer;

public class Mesh {
    /** **VAO (Vertex Array Object)**: 
     * ID del contenedor que guarda la configuración de la malla. */
    private int vaoID;
    /** **VBO (Vertex Buffer Object)**: 
     * ID del buffer que contiene los datos numéricos de los vértices. */
    private int vboID;
    /** Número total de puntos a dibujar. */
    private int cantidadDeVertices;

    /** Construye la malla y sube los datos a la GPU.
     * @param vertices Arreglo de objetos Vertex con las posiciones X, Y. */
    public Mesh(Vertex[] vertices) {
        this.cantidadDeVertices = vertices.length;
        // Crear el VAO (Contenedor)
        vaoID = GL30.glGenVertexArrays();
        GL30.glBindVertexArray(vaoID);
        // Convertir Vertex[] a un FloatBuffer para OpenGL (2 porque es X, Y)
        FloatBuffer buffer = MemoryUtil.memAllocFloat(vertices.length * 2); 
        // Convierte Vertex[] en números float
        for (Vertex v : vertices) {
            buffer.put(v.posicion.x);
            buffer.put(v.posicion.y);
        }
        buffer.flip();
        // Crear el VBO (El buffer de datos real)
        vboID = GL15.glGenBuffers();
        // Activa el buffer
        GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, vboID);
        // Envía los datos a la GPU
        GL15.glBufferData(GL15.GL_ARRAY_BUFFER, buffer, GL15.GL_STATIC_DRAW);
        // Definir la estructura de los datos (Atributo 0: Posición)
        GL20.glVertexAttribPointer(0, 2, GL11.GL_FLOAT, false, 0, 0);
        // Habilita el atributo
        GL20.glEnableVertexAttribArray(0);
        // Limpieza temporal
        GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, 0);     //Desactiva el VBO
        GL30.glBindVertexArray(0);                      //Desactiva el VAO
        MemoryUtil.memFree(buffer);                     //Libera memoria RAM temporal
    }

    /** **Renderizado**: Activa el contenedor y dibuja los triángulos en pantalla. */
    public void dibujar() {
        // Activa la configuración de la malla
        GL30.glBindVertexArray(vaoID);
        // Dibuja los triángulos
        GL11.glDrawArrays(GL11.GL_TRIANGLES, 0, cantidadDeVertices);
        // Desactiva el VAO
        GL30.glBindVertexArray(0);
    }

    /** **Limpieza de Hardware**: 
     * Borra los buffers de la memoria de video al destruir el objeto. */
    public void destruir() {
        // Desactiva atributos de vértices
        GL20.glDisableVertexAttribArray(0);
        // Desactiva el VBO
        GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, 0);
        // Elimina el buffer de vértices
        GL15.glDeleteBuffers(vboID);
        // Elimina el VAO
        GL30.glDeleteVertexArrays(vaoID);
    }
}