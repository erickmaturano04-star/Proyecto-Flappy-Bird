/* GRAFICOS_Y_RECURSOS - Shader 
    El programa que da color, que corre en la GPU */
package com.graphics.graficos_y_recursos;

import org.lwjgl.opengl.GL20;
import org.joml.Matrix4f;
import org.lwjgl.system.MemoryStack;

import java.nio.FloatBuffer;

public class Shader {
    /** **ID del Programa**: Identificador único del conjunto de shaders en la GPU. */
    private int programID;

    /** Crea, compila y vincula un programa de shaders.
     * @param vertexCode   Código fuente del Vertex Shader (procesa posiciones).
     * @param fragmentCode Código fuente del Fragment Shader (procesa colores). */
    public Shader(String vertexCode, String fragmentCode) {
        // Crea y compila Vertex Shader (Calcula la posicion de los vertices)
        int vID = GL20.glCreateShader(GL20.GL_VERTEX_SHADER);
        GL20.glShaderSource(vID, vertexCode);
        GL20.glCompileShader(vID);
        checkCompileErrors(vID, "VERTEX");
        // Crea y compila Fragment Shader (Calcula el color final de cada pixel)
        int fID = GL20.glCreateShader(GL20.GL_FRAGMENT_SHADER);
        GL20.glShaderSource(fID, fragmentCode);
        GL20.glCompileShader(fID);
        checkCompileErrors(fID, "FRAGMENT");
        // Vincular Shaders al Programa
        programID = GL20.glCreateProgram();
        GL20.glAttachShader(programID, vID);
        GL20.glAttachShader(programID, fID);
        GL20.glLinkProgram(programID);
        checkCompileErrors(programID, "PROGRAM");
        // Limpiar shaders individuales (ya están vinculados)
        GL20.glDeleteShader(vID);
        GL20.glDeleteShader(fID);
    }

    /** **Activa** este programa para que las siguientes órdenes de dibujo lo usen. */
    public void usar() { 
        GL20.glUseProgram(programID); 
    }

    /** **Desactiva** el programa actual (vuelve al estado nulo). */
    public void detener() { 
        GL20.glUseProgram(0); 
    }

    /** Libera la memoria de la GPU ocupada por este programa. */
    public void destruir() {
        detener();
        GL20.glDeleteProgram(programID);
    }

    /** **Envío de Matrices**: Sube una matriz (como la de Modelo o Vista) al Shader.
     * Utiliza un MemoryStack para una gestión de memoria rápida y eficiente.
     * @param nombre Nombre de la variable 'uniform' en el código GLSL.
     * @param valor  La matriz Matrix4f con los datos. */
    public void setUniform(String nombre, Matrix4f valor) {
        int ubicacion =
            GL20.glGetUniformLocation(programID, nombre);
        try (MemoryStack stack = MemoryStack.stackPush()) {
            FloatBuffer buffer = stack.mallocFloat(16);
            valor.get(buffer);
            GL20.glUniformMatrix4fv(ubicacion, false, buffer);
        }
    }

    /** **Control de Errores**: Verifica si el código GLSL tiene errores de sintaxis. */
    private void checkCompileErrors(int id, String type) {
        int success;
        if (!type.equals("PROGRAM")) {
            success = GL20.glGetShaderi(id, GL20.GL_COMPILE_STATUS);
            if (success == 0) {
                System.err.println("ERROR::SHADER_COMPILATION_ERROR de tipo: " + type);
                System.err.println(GL20.glGetShaderInfoLog(id));
            }
        } else {
            success = GL20.glGetProgrami(id, GL20.GL_LINK_STATUS);
            if (success == 0) {
                System.err.println("ERROR::PROGRAM_LINKING_ERROR de tipo: " + type);
                System.err.println(GL20.glGetProgramInfoLog(id));
            }
        }
    }

    /** @return **int** El identificador único del programa en la GPU.*/
    public int getProgramID() { 
        return programID; 
    }
}