package com.graphics.objetos.figuras_geometricas;

import com.graphics.graficos_y_recursos.Color;
import com.graphics.graficos_y_recursos.Vertex;

import java.util.ArrayList;

public class Circulo extends Poligono {
    public Circulo(float x, float y, float ancho, float alto, 
                    Color color) {
        super(generarVertices(),
            x, y, ancho, alto, color
        );
    }

    private static Vertex[] generarVertices() {
        int segmentos = 30;
        float radio = 0.5f;
        ArrayList<Vertex> listaVertices =
                new ArrayList<>();

        for (int i = 0; i < segmentos; i++) {
            float angulo1 = (float)(2.0f * Math.PI * i / segmentos);
            float angulo2 = (float)(2.0f * Math.PI * (i + 1) / segmentos);
            // Centro
            listaVertices.add(
                new Vertex(0.0f, 0.0f)
            );
            // Punto 1
            listaVertices.add(
                new Vertex(
                        radio * (float)Math.cos(angulo1),
                        radio * (float)Math.sin(angulo1)
                )
            );
            // Punto 2
            listaVertices.add(
                new Vertex(
                        radio * (float)Math.cos(angulo2),
                        radio * (float)Math.sin(angulo2)
                )
            );
        } //Fin-For
        
        return listaVertices.toArray(new Vertex[0]);
    }
}