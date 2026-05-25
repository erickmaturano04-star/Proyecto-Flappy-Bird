/* SISTEMA - ObjectRendererTexture
    Renderiza sprites con textura usando shaders */
package com.graphics.sistema;

import com.graphics.graficos_y_recursos.Sprite;
import com.graphics.graficos_y_recursos.ShaderTexture;

public class ObjectRendererTexture {

    /** **Renderizado de sprite**: Activa el shader, enlaza la textura y dibuja el objeto. 
     * @param sprite Sprite que contiene la textura y malla.
     * @param shader Shader utilizado para el renderizado. */
    public static void dibujar(Sprite sprite, ShaderTexture shader) {
        shader.usar();              // Activa el shader actual
        sprite.getTexture().bind(); // Activa la textura del sprite
        sprite.getMesh().render();  // Dibuja la malla del sprite
        shader.detener();           // Desactiva el shader
    }
}