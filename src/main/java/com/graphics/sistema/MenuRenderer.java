/* SISTEMA - MenuRenderer
    Renderiza el menu principal usando OpenGL */
package com.graphics.sistema;

import com.graphics.graficos_y_recursos.ShaderTexture;
import com.graphics.juego.ui.MenuPrincipal;
import org.lwjgl.opengl.GL11;

public class MenuRenderer {
    /** **Renderizado de menú**:
     * Activa el shader, configura transparencia y dibuja los elementos visuales del menú.
     * @param shaderTexture Shader utilizado para dibujar texturas.
     * @param camera Cámara usada para la matriz de vista.
     * @param menu Menú principal a renderizar. */
    public static void renderMenu(ShaderTexture shaderTexture, Camera camera, MenuPrincipal menu) {
        // Activa el shader de texturas
        shaderTexture.usar();
        // Envía la matriz de vista al shader
        shaderTexture.setUniform("uView", camera.getViewMatrix());
        // Activa transparencia para imágenes PNG
        GL11.glEnable(GL11.GL_BLEND);
        // Configura mezcla de transparencia
        GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
        // Dibuja los elementos visuales del menú
        menu.renderizar(shaderTexture);
        // Desactiva la transparencia
        GL11.glDisable(GL11.GL_BLEND);
        // Desactiva el shader actual
        shaderTexture.detener();
    }
}
