/* SISTEMA - Time
    Controla el tiempo para el movimiento sea independiente de los FPS */
package com.graphics.sistema;

import org.lwjgl.glfw.GLFW;

public class Time {
    /** Tiempo registrado en el fotograma anterior. */
    private static float ultimoTiempo;

    /** **Sincronización inicial**: Captura el tiempo de arranque del motor. */
    public static void inicializar() {
        ultimoTiempo = (float) GLFW.glfwGetTime();
    }

    /** **Cálculo de Delta Time**: Mide cuánto tiempo pasó desde el último frame.
     * @return **float**: Tiempo en segundos (ej: 0.016 para 60 FPS). */
    public static float getDeltaTime() {
        float tiempoActual = (float) GLFW.glfwGetTime();
        float dt = tiempoActual - ultimoTiempo;
        ultimoTiempo = tiempoActual;
        // Capa de seguridad para evitar saltos por lag
        if (dt > 0.033f) {
            dt = 0.033f;
        }
        return dt;
    }
}