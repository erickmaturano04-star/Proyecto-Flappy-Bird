package com.graphics.objetos.figuras_geometricas;

import java.util.Collections;
import java.util.List;

import com.graphics.graficos_y_recursos.Color;
import com.graphics.graficos_y_recursos.MeshColor;
import com.graphics.graficos_y_recursos.VertexColor;

import com.graphics.matematicas.Transform;
import com.graphics.sistema.IDibujable;

public class CuadradoDegradado4 implements IDibujable {
    private MeshColor mesh;
    private Transform transform;

    public CuadradoDegradado4(float x, float y, float ancho, 
                            float alto, Color superiorIzquierdo, 
                            Color superiorDerecho, Color inferiorIzquierdo, 
                            Color inferiorDerecho) {
        transform = new Transform();
        transform.posicion.x = x;
        transform.posicion.y = y;
        // MITADES
        float hw = ancho / 2.0f;
        float hh = alto / 2.0f;
        // VERTICES
        VertexColor[] vertices = {
            // TRIANGULO 1
            new VertexColor(-hw, hh, superiorIzquierdo),
            new VertexColor(-hw, -hh, inferiorIzquierdo),
            new VertexColor(hw, -hh, inferiorDerecho),
            // TRIANGULO 2
            new VertexColor(-hw, hh, superiorIzquierdo),
            new VertexColor(hw, -hh, inferiorDerecho),
            new VertexColor(hw, hh, superiorDerecho)
        };
        mesh = new MeshColor(vertices);
    }

    @Override
    public List<Object> getPartes() {
        return Collections.singletonList(this);
    }
    
    // GETTERS    
    public MeshColor getMesh() {
        return mesh;
    }
    public Transform getTransform() {
        return transform;
    }

    // LIMPIEZA
    public void destruir() {
        mesh.destruir();
    }
}