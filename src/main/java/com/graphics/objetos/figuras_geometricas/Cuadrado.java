/* FIGURAS_GEOMETRICAS - Cuadrado */
package com.graphics.objetos.figuras_geometricas;

import com.graphics.graficos_y_recursos.Color;
import com.graphics.graficos_y_recursos.Vertex;

public class Cuadrado extends Poligono {
    public Cuadrado(float x, float y, float ancho, float alto, 
                    Color color) {
        super(new Vertex[] {
                // Triángulo 1
                new Vertex(-0.5f,  0.5f),
                new Vertex(-0.5f, -0.5f),
                new Vertex( 0.5f, -0.5f),

                // Triángulo 2
                new Vertex(-0.5f,  0.5f),
                new Vertex( 0.5f, -0.5f),
                new Vertex( 0.5f,  0.5f)
            }, 
            x, y, ancho, alto, color
        );
    }
}