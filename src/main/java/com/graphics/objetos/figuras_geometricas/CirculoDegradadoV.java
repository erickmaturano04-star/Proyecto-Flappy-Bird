package com.graphics.objetos.figuras_geometricas;

import java.util.Collections;
import java.util.List;

import com.graphics.graficos_y_recursos.Color;
import com.graphics.graficos_y_recursos.MeshColor;
import com.graphics.graficos_y_recursos.VertexColor;
import com.graphics.matematicas.Transform;
import com.graphics.sistema.IDibujable;

public class CirculoDegradadoV implements IDibujable {
    private MeshColor mesh;
    private Transform transform;

    public CirculoDegradadoV(float x, float y, float ancho, float alto, 
                            Color colorSuperior, Color colorInferior) {
        this.transform = new Transform();
        this.transform.posicion.x = x;
        this.transform.posicion.y = y;
        this.transform.escala.set(ancho, alto);

        this.mesh = new MeshColor(generarVertices(colorSuperior, colorInferior));
    }

    private VertexColor[] generarVertices(Color arriba, Color abajo) {
        int segmentos = 30;
        float radio = 0.5f; 
        VertexColor[] vertices = new VertexColor[segmentos * 3];

        for (int i = 0; i < segmentos; i++) {
            float angulo1 = (float)(2.0f * Math.PI * i / segmentos);
            float angulo2 = (float)(2.0f * Math.PI * (i + 1) / segmentos);
            // Calculamos las posiciones Y para decidir el color
            float x1 = radio * (float)Math.cos(angulo1);
            float y1 = radio * (float)Math.sin(angulo1);
            float x2 = radio * (float)Math.cos(angulo2);
            float y2 = radio * (float)Math.sin(angulo2);
            int base = i * 3;
            // EL CENTRO: Le damos un color intermedio (o el de arriba para luz)
            Color colorCentro = mezclarColores(arriba, abajo, 0.5f);
            vertices[base] = new VertexColor(0.0f, 0.0f, colorCentro);
            // PUNTO 1: Calcula el color segun su altura y1
            float t1 = (y1 + radio) / (2.0f * radio);
            vertices[base + 1] = new VertexColor(x1, y1, mezclarColores(abajo, arriba, t1));
            // PUNTO 2: Calcula el color segun su altura y2
            float t2 = (y2 + radio) / (2.0f * radio);
            vertices[base + 2] = new VertexColor(x2, y2, mezclarColores(abajo, arriba, t2));
        }
        return vertices;
    }

    private Color mezclarColores(Color colorA, Color colorB, float t) {
        float r = colorA.r + (colorB.r - colorA.r) * t;
        float g = colorA.g + (colorB.g - colorA.g) * t;
        float b = colorA.b + (colorB.b - colorA.b) * t;
        float a = colorA.a + (colorB.a - colorA.a) * t;
        return new Color(r, g, b, a);
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