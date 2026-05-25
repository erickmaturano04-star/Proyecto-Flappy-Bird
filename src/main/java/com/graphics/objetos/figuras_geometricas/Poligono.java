package com.graphics.objetos.figuras_geometricas;

import com.graphics.graficos_y_recursos.Color;
import com.graphics.graficos_y_recursos.Mesh;
import com.graphics.graficos_y_recursos.Vertex;
import com.graphics.matematicas.Transform;
import com.graphics.sistema.IDibujable;

import java.util.Collections;
import java.util.List;

import org.joml.Vector2f;

public class Poligono implements IDibujable {
    protected Mesh mesh;
    protected Transform transform;
    protected Color color;

    public Poligono(Vertex[] vertices, float x, float y, float ancho,
                    float alto, Color color) {
        this.mesh = new Mesh(vertices);
        this.color = color;

        this.transform = new Transform();
        this.transform.posicion = new Vector2f(x, y);
        this.transform.escala = new Vector2f(ancho, alto);
    }

    @Override
    public List<Object> getPartes() {
        return Collections.singletonList(this);
    }

    public Mesh getMesh() {
        return mesh;
    }

    public Transform getTransform() {
        return transform;
    }

    public Color getColor() {
        return color;
    }

    public void destruir() {
        mesh.destruir();
    }
}