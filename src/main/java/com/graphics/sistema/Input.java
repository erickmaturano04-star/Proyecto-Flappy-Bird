/* SISTEMA - Input
    Consulta el teclado, si una tecla esta pulsada desde cualquier 
    parte del codigo */
package com.graphics.sistema;

import org.lwjgl.glfw.GLFW;

public class Input {
    /** **Registro de teclas**: Almacena true si la tecla está presionada. */
    private static boolean[] keys = new boolean[GLFW.GLFW_KEY_LAST];
    private static boolean[] keysPressed = new boolean[GLFW.GLFW_KEY_LAST];

    /** **Callback de Teclado**: Método que GLFW llama automáticamente al detectar eventos.
     * Actualiza el arreglo de estados según la acción del usuario.
     * @param action Puede ser GLFW_PRESS (presionado) o GLFW_RELEASE (soltado). */
    public static void keyboardCallback(long window, int key, 
                                        int scancode, int action, int mods) {
        // Verifica que la tecla exista dentro del arreglo
        if (key >= 0 && key < keys.length) {
            if (action == GLFW.GLFW_PRESS) {
                // Marca la tecla como presionada
                keys[key] = true;
                keysPressed[key] = true;
            } else if (action == GLFW.GLFW_RELEASE) {
                // Marca la tecla como soltada
                keys[key] = false;
            }
        }
    }

    /** **Consulta de continua**: Verifica si una tecla específica está activa.
     * @param key Código de la tecla (Ej: GLFW.GLFW_KEY_W).
     * @return **true** si la tecla se mantiene presionada. */
    public static boolean estaPresionada(int key) {
        return keys[key];
    }

    /** **Consulta única**: Devuelve true solo una vez cuando la tecla fue presionada
     * @param key Código GLFW de la tecla.
     * @return true si la tecla fue presionada recientemente */
    public static boolean fuePresionada(int key) {
        if (keysPressed[key]) {
            keysPressed[key] = false;
            return true;
        }
        return false;
    }

    /** **Reinicio de teclas**: Limpia todas las teclas marcadas como "presionadas una vez".
     * Se usa normalmente al final del frame */
    public static void limpiarTeclasPresionadas() {
        for (int i = 0; i < keysPressed.length; i++) {
            keysPressed[i] = false;
        }
    }
}