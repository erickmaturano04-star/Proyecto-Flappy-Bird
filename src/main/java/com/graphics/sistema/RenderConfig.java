/* SISTEMA - RenderConfig
    Configura opciones basicas de renderizado en OpenGL */
package com.graphics.sistema;

import static org.lwjgl.opengl.GL11.*;

public class RenderConfig {
    /** **Configuración inicial**:
     * Activa las opciones básicas de renderizado. */
    public static void inicializar() {
        // Habilita transparencia para texturas PNG
        activarTransparencia();
    }

    /** **Transparencia**:
     * Activa la mezcla alfa para imágenes transparentes. */
    public static void activarTransparencia() {
        // Habilita el sistema de mezcla de OpenGL
        glEnable(GL_BLEND);
        // Configura cómo se mezclan los píxeles transparentes
        glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);
    }
}