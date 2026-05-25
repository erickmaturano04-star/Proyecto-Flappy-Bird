package com.graphics.objetos.figuras_geometricas;

import com.graphics.graficos_y_recursos.Color;
import com.graphics.graficos_y_recursos.Vertex;

public class Triangulo extends Poligono {
    public Triangulo(float x, float y, float ancho, float alto, 
                    Color color) {
        super(new Vertex[] {
            // Punta superior
            new Vertex( 0.0f,  0.5f),
            // Inferior izquierda
            new Vertex(-0.5f, -0.5f),
            // Inferior derecha
            new Vertex( 0.5f, -0.5f)
            }, 
            x, y, ancho, alto, color
        );
    }
}