/* GRAFICOS_Y_RECURSOS - ShaderLibrary
    Almacena y gestiona los programas que se ejecutan en la GPU */
package com.graphics.graficos_y_recursos;

public class ShaderLibrary {

    /** **Vertex Shader (Procesador de Puntos)**: 
     * Se encarga de proyectar cada punto del modelo en el espacio de la pantalla.
     * - 'uModel': Aplica posición, rotación y escala del objeto.
     * - 'uView': Aplica la posición y zoom de la cámara. */
    private static final String VERTEX_BASE = 
          "#version 330 core\n"
        + "layout (location = 0) in vec2 aPos;\n"
        + "layout (location = 1) in vec4 aColor;\n"
        + "uniform mat4 uModel;\n"
        + "uniform mat4 uView;\n"
        + "out vec4 vColor;\n"
        + "void main(){\n"
        + "    vColor = aColor;\n"
        + "    gl_Position = uView * uModel * vec4(aPos, 0.0, 1.0);\n"
        + "}";
    
    /** **Fragment Shader (Procesador de Píxeles)**: 
     * Determina el color final de cada píxel que forma la figura.
     * En este caso, simplemente aplica el color interpolado que viene 
     * de los vértices. */
    private static final String FRAGMENT_BASE = 
          "#version 330 core\n"
        + "in vec4 vColor;\n"
        + "out vec4 FragColor;\n"
        + "void main(){\n"
        + "    FragColor = vColor;\n"
        + "}";

    /** Genera un programa de sombreado básico basado en colores por vértice.
     * @return Una instancia de {@link Shader} lista para ser vinculada 
     * al pipeline de dibujo.*/
    public static Shader getBaseShader() {
        return new Shader(VERTEX_BASE, FRAGMENT_BASE);
    }
}
