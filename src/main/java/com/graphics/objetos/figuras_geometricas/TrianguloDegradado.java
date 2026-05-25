package com.graphics.objetos.figuras_geometricas;

import java.util.Collections;
import java.util.List;

import com.graphics.graficos_y_recursos.Color;
import com.graphics.graficos_y_recursos.MeshColor;
import com.graphics.graficos_y_recursos.VertexColor;
import com.graphics.matematicas.Transform;
import com.graphics.sistema.IDibujable;

public class TrianguloDegradado implements IDibujable {
    private MeshColor mesh;
    private Transform transform;
    private VertexColor[] vertices;
    private VertexColor[] verticesOriginales;

    public TrianguloDegradado(float x1, float y1, float x2, float y2,
                            float x3, float y3, 
                            Color c1, Color c2, Color c3) {
        vertices = new VertexColor[] {
            new VertexColor(x1, y1, c1),
            new VertexColor(x2, y2, c2),
            new VertexColor(x3, y3, c3)
        };
        verticesOriginales = new VertexColor[] {
            new VertexColor(x1, y1, c1),
            new VertexColor(x2, y2, c2),
            new VertexColor(x3, y3, c3)
        };
        mesh = new MeshColor(vertices);
        transform = new Transform();
    }

    @Override
    public List<Object> getPartes() {
        return Collections.singletonList(this);
    }

    public void actualizarMesh() {
        mesh.actualizarVertices(vertices);
    }

    // GETTER
    public MeshColor getMesh() {
        return mesh;
    }
    public Transform getTransform() {
        return transform;
    }
    public VertexColor[] getVertices() {
        return vertices;
    }
    public VertexColor[] getVerticesOriginales() {
        return verticesOriginales;
    }

    // LIMPIEZA
    public void destruir() {
        mesh.destruir();
    }
}