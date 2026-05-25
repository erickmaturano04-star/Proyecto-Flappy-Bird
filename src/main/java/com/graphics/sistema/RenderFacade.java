/* SISTEMA - RenderFacade
    Centraliza el renderizado general de la aplicacion del menu,
    fondo y juego principal */
package com.graphics.sistema;

import com.graphics.graficos_y_recursos.Shader;
import com.graphics.graficos_y_recursos.ShaderTexture;
import com.graphics.juego.ui.MenuPrincipal;
import com.graphics.juego.GameManager;
import com.graphics.juego.modos.JuegoP1P2;
import com.graphics.objetos.escenas.Fondo;

public class RenderFacade {
    /** **Renderizado general**: Dibuja el fondo, menú o juego según el estado actual.
     * @param renderer Renderizador principal.
     * @param camera Cámara utilizada para la vista.
     * @param shaderBase Shader principal de renderizado.
     * @param shaderTexture Shader para texturas.
     * @param gameManager Controlador de estados del juego.
     * @param menu Menú principal.
     * @param juego Modo de juego P1 vs P2.
     * @param fondo Fondo de la escena. */
    public static void render(Renderer renderer, Camera camera,
                              Shader shaderBase, ShaderTexture shaderTexture,
                              GameManager gameManager,
                              MenuPrincipal menu,
                              JuegoP1P2 juego,
                              Fondo fondo) {
        // Renderiza el fondo animado
        shaderBase.usar();
        // Envía la matriz de vista al shader
        shaderBase.setUniform("uView", camera.getViewMatrix());
        // Dibuja el fondo
        ObjectRenderer.dibujar(fondo, shaderBase);
        // Desactiva el shader
        shaderBase.detener();
        // Verifica si el juego está en el menú
        if (gameManager.estaEnMenu()) {
            // Renderiza el menú principal
            MenuRenderer.renderMenu(shaderTexture, camera, menu);
        } else if (gameManager.estaEnJuego()) {
            // Activa el shader principal
            shaderBase.usar();
            // Envía la matriz de vista
            shaderBase.setUniform("uView", camera.getViewMatrix());
            // Dibuja el juego
            juego.renderizar(shaderBase);
            // Desactiva el shader
            shaderBase.detener();
        }
    }
}
