package com.graphics.objetos.figuras_geometricas;

import java.util.Collections;
import java.util.List;

import com.graphics.graficos_y_recursos.Color;
import com.graphics.graficos_y_recursos.MeshColor;
import com.graphics.graficos_y_recursos.VertexColor;
import com.graphics.matematicas.Transform;
import com.graphics.sistema.IDibujable;


public class CirculoDegradadoC implements IDibujable {

    private MeshColor mesh;
    private Transform transform;

    public CirculoDegradadoC(float x, float y, float ancho, float alto, 
                            Color colorCentro, Color colorBorde) {
        
        transform = new Transform();
        transform.posicion.x = x;
        transform.posicion.y = y;
        // Nota: Si tu MeshColor ya aplica la escala mediante el Transform en el Renderer, 
        // podrías usar hw/hh aquí o simplemente pasar ancho/alto al transform.
        transform.escala.set(ancho, alto);

        mesh = new MeshColor(generarVertices(colorCentro, colorBorde));
    }

    private VertexColor[] generarVertices(Color centro, Color borde) {
        int segmentos = 30;
        float radio = 0.5f; // Radio base para que el Transform controle el tamaño real
        VertexColor[] vertices = new VertexColor[segmentos * 3];

        for (int i = 0; i < segmentos; i++) {
            float angulo1 = (float)(2.0f * Math.PI * i / segmentos);
            float angulo2 = (float)(2.0f * Math.PI * (i + 1) / segmentos);
            
            int base = i * 3;
            
            // 1. Centro (Color del núcleo)
            vertices[base] = new VertexColor(0.0f, 0.0f, centro);
            
            // 2. Punto exterior 1 (Color del borde)
            vertices[base + 1] = new VertexColor(
                radio * (float)Math.cos(angulo1),
                radio * (float)Math.sin(angulo1),
                borde
            );
            
            // 3. Punto exterior 2 (Color del borde)
            vertices[base + 2] = new VertexColor(
                radio * (float)Math.cos(angulo2),
                radio * (float)Math.sin(angulo2),
                borde
            );
        }
        return vertices;
    }

    @Override
    public List<Object> getPartes() {
        return Collections.singletonList(this);
    }
    
    public MeshColor getMesh() {
        return mesh;
    }

    public Transform getTransform() {
        return transform;
    }

    public void destruir() {
        mesh.destruir();
    }
}