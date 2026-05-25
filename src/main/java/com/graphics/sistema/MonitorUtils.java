/* SISTEMA - MonitorUtils
    Proporciona acceso simplificado a la resolucion nativa y modos 
    de video de GLFW en el monitor */
package com.graphics.sistema;

import org.lwjgl.glfw.GLFW;
import org.lwjgl.glfw.GLFWVidMode;

public class MonitorUtils {
    /** **Consulta de ancho**: Obtiene el ancho actual del monitor principal
     * @return El ancho actual del monitor principal en píxeles. */
    public static int getAnchoMonitor() {
        // GLFW debe estar iniciado para consultar el monitor
        if (!GLFW.glfwInit()) {
            return 800; // Valor por defecto si falla
        }
        // Obtiene el monitor principal
        long monitor = GLFW.glfwGetPrimaryMonitor();
        // Obtiene el modo de video actual
        GLFWVidMode modo = GLFW.glfwGetVideoMode(monitor);
        // Verifica si GLFW obtuvo correctamente la resolución
        int resultado = 0;
        if (modo != null) {
            // Guarda el ancho del monitor
            resultado = modo.width();
        } else {
            // Valor por defecto si ocurre un error
            resultado = 800;
        }
        return resultado;
    }

    /** **Consulta de alto**: Obtiene el alto actual del monitor principal
     * @return El alto actual del monitor principal en píxeles. */
    public static int getAltoMonitor() {
        // GLFW debe estar iniciado para consultar el monitor
        if (!GLFW.glfwInit()) {
            return 600; // Valor por defecto si falla
        }
        // Obtiene el monitor principal
        long monitor = GLFW.glfwGetPrimaryMonitor();
        // Obtiene el modo de video actual
        GLFWVidMode modo = GLFW.glfwGetVideoMode(monitor);
        // Verifica si GLFW obtuvo correctamente la resolución
        int resultado = 0;
        if (modo != null) {
            // Guarda el alto del monitor
            resultado = modo.height();
        } else {
            // Valor por defecto si ocurre un error
            resultado = 600;
        }
        return resultado;
    }
}
