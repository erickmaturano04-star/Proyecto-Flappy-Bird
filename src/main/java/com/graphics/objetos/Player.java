/*
package com.graphics.objetos;

import com.graphics.graficos_y_recursos.Mesh;
import com.graphics.graficos_y_recursos.Vertex;
import com.graphics.matematicas.Transform;
import org.joml.Vector2f;

public class Player {
    public Transform transform;
    public Mesh mesh;
    private float velocidadY;
    private final float GRAVEDAD = -0.005f;
    private final float IMPULSO = 0.15f;

    public Player() {
        this.transform = new Transform();
        this.transform.posicion = new Vector2f(0, 0);
        this.transform.escala = new Vector2f(0.2f, 0.2f);
        this.velocidadY = 0;
        
        // Definir un cuadrado simple para el pájaro
        Vertex[] vertices = new Vertex[] {
            new Vertex(new Vector2f(-0.5f,  0.5f)),
            new Vertex(new Vector2f(-0.5f, -0.5f)),
            new Vertex(new Vector2f( 0.5f, -0.5f)),
            new Vertex(new Vector2f( 0.5f,  0.5f))
        };
        this.mesh = new Mesh(vertices);
    }

    public void actualizar() {
        this.velocidadY = this.velocidadY + GRAVEDAD;
        this.transform.posicion.y = this.transform.posicion.y + velocidadY;
    }

    public void saltar() {
        this.velocidadY = IMPULSO;
    }
} */