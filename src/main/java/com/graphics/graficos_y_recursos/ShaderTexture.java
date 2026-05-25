/* GRAFICOS_Y_RECURSOS - ShaderTexture
    Maneja shaders para renderizar texturas y sprites */
package com.graphics.graficos_y_recursos;

import org.joml.Matrix4f;
import org.lwjgl.system.MemoryStack;

import java.nio.FloatBuffer;

import static org.lwjgl.opengl.GL20.*;

public class ShaderTexture {
    /** ID del programa shader en OpenGL. */
    private final int programId;

    /** **Constructor del shader**: Compila y enlaza los shaders de textura. */
    public ShaderTexture() {
        // Shader de vértices
        String vertexShader = """
                #version 330 core
                layout(location = 0) in vec2 posicion;
                layout(location = 1) in vec2 uvEntrada;
                out vec2 uv;
                void main() {
                    uv = uvEntrada;
                    gl_Position = vec4(posicion, 0.0, 1.0);
                }
                """;
        // Shader de fragmentos
        String fragmentShader = """
                #version 330 core
                in vec2 uv;
                out vec4 fragColor;
                uniform sampler2D textura;
                void main() {
                    fragColor = texture(textura, uv);
                }
                """;

        // Compilacion de Vertex Shader
        int vertexId = glCreateShader(GL_VERTEX_SHADER);
        glShaderSource(vertexId, vertexShader);
        glCompileShader(vertexId);
        // Verifica errores de compilación
        if (glGetShaderi(vertexId, GL_COMPILE_STATUS) == GL_FALSE) {
            throw new RuntimeException("Vertex shader compile error: " + glGetShaderInfoLog(vertexId));
        }

        // Compilacion de Fragment Shader
        int fragmentId = glCreateShader(GL_FRAGMENT_SHADER);
        glShaderSource(fragmentId, fragmentShader);
        glCompileShader(fragmentId);
        // Verifica errores de compilación
        if (glGetShaderi(fragmentId, GL_COMPILE_STATUS) == GL_FALSE) {
            throw new RuntimeException("Fragment shader compile error: " + glGetShaderInfoLog(fragmentId));
        }

        // Creacion del Programa
        programId = glCreateProgram();
        // Une ambos shaders
        glAttachShader(programId, vertexId);
        glAttachShader(programId, fragmentId);
        // Enlaza el programa final
        glLinkProgram(programId);
        // Verifica errores de enlace
        if (glGetProgrami(programId, GL_LINK_STATUS) == GL_FALSE) {
            throw new RuntimeException("Shader program link error: " + glGetProgramInfoLog(programId));
        }
        // Libera shaders temporales
        glDeleteShader(vertexId);
        glDeleteShader(fragmentId);
    }

    /** **Activación del shader**: Usa este programa para renderizar. */
    public void usar() {
        glUseProgram(programId);
    }

    /** **Desactivación del shader**: Libera el shader actual. */
    public void detener() {
        glUseProgram(0);
    }

    /** **Envío de matrices**: Envía una Matrix4f al shader. 
         * @param name Nombre de la variable uniform.
         * @param value Matriz a enviar. */
    public void setUniform(String name, Matrix4f value) {
        // Busca la varianle uniform
        int location = glGetUniformLocation(programId, name);
        if (location < 0) {
            // Ignora si no existe
            return;
        }
        // Reserva memoria temporal
        try (MemoryStack stack = MemoryStack.stackPush()) {
            FloatBuffer buffer = stack.mallocFloat(16);
            // Convierte Matrix4f en floats
            value.get(buffer);
            // Envia la matriz al shader
            glUniformMatrix4fv(location, false, buffer);
        }
    }
}