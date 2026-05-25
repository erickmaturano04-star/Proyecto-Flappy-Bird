package com.graphics.objetos.jugador;

import java.util.ArrayList;
import java.util.List;

import com.graphics.graficos_y_recursos.Color;
import com.graphics.graficos_y_recursos.VertexColor;
import com.graphics.objetos.figuras_geometricas.TrianguloDegradado;
import com.graphics.sistema.IDibujable;

public class Ala implements IDibujable {
    private TrianguloDegradado pluma1;
    private TrianguloDegradado pluma2;
    private TrianguloDegradado pluma3;
    private TrianguloDegradado pluma4;
    private TrianguloDegradado pluma5;

    public Ala(float x, float y, float escala, Color colorPrincipal) {
        // Variaciones del color
        Color colorClaro = 
            new Color(Math.min(colorPrincipal.r * 1.08f, 1.0f),
                    Math.min(colorPrincipal.g * 1.08f, 1.0f),
                    Math.min(colorPrincipal.b * 1.08f, 1.0f),
                    1.0f);
        Color colorOscuro = 
            new Color(Math.max(colorPrincipal.r * 0.76f, 0f),
                    Math.max(colorPrincipal.g * 0.76f, 0f),
                    Math.max(colorPrincipal.b * 0.76f, 0f),
                    1.0f);
        // PLUMA 1
        pluma1 = 
            new TrianguloDegradado(x + escala * 0.02f,
                                    y + escala * 0.16f,
                                    x - escala * 0.26f,
                                    y + escala * 0.06f,
                                    x - escala * 0.02f,
                                    y - escala * 0.02f,
                                    colorClaro,
                                    colorOscuro,
                                    colorOscuro);
        // PLUMA 2
        pluma2 = 
            new TrianguloDegradado(x + escala * 0.04f,
                                    y + escala * 0.08f,
                                    x - escala * 0.38f,
                                    y - escala * 0.04f,
                                    x - escala * 0.04f,
                                    y - escala * 0.10f,
                                    colorClaro,
                                    colorOscuro,
                                    colorOscuro);
        // PLUMA 3
        pluma3 = 
            new TrianguloDegradado(x + escala * 0.06f,
                                    y,
                                    x - escala * 0.50f,
                                    y - escala * 0.12f,
                                    x - escala * 0.06f,
                                    y - escala * 0.18f,
                                    colorClaro,
                                    colorOscuro,
                                    colorOscuro);
        // PLUMA 4
        pluma4 =
            new TrianguloDegradado(x + escala * 0.08f,
                                    y - escala * 0.10f,
                                    x - escala * 0.62f,
                                    y - escala * 0.22f,
                                    x - escala * 0.10f,
                                    y - escala * 0.26f,
                                    colorClaro,
                                    colorOscuro,
                                    colorOscuro);
        pluma5 =
            new TrianguloDegradado(x + escala * 0.10f,
                                    y - escala * 0.20f,
                                    x - escala * 0.76f,
                                    y - escala * 0.34f,
                                    x - escala * 0.14f,
                                    y - escala * 0.36f,
                                    colorClaro,
                                    colorOscuro,
                                    colorOscuro);
    } //Fin-Constructor

    @Override
    public List<Object> getPartes() {
        List<Object> partes = new ArrayList<>();
        partes.add(pluma1);
        partes.add(pluma2);
        partes.add(pluma3);
        partes.add(pluma4);
        partes.add(pluma5);
        return partes;
    }

    public void aletear(float fuerza) {
        moverPluma(pluma1, fuerza * 0.03f);
        moverPluma(pluma2, fuerza * 0.06f);
        moverPluma(pluma3, fuerza * 0.09f);
        moverPluma(pluma4, fuerza * 0.12f);
        moverPluma(pluma5, fuerza * 0.15f);
    }

    private void moverPluma(TrianguloDegradado pluma, float offsetY) {
        VertexColor[] v = pluma.getVertices();
        VertexColor[] base = pluma.getVerticesOriginales();
        for (int i = 0; i < 3; i++) {
            v[i].posicion.y = base[i].posicion.y + offsetY;
        }
        pluma.actualizarMesh();
    }

    // GETTERS
    public TrianguloDegradado getPluma1() {
        return pluma1;
    }
    public TrianguloDegradado getPluma2() {
        return pluma2;
    }
    public TrianguloDegradado getPluma3() {
        return pluma3;
    }
    public TrianguloDegradado getPluma4() {
        return pluma4;
    }
    public TrianguloDegradado getPluma5() {
        return pluma5;
    }

    // LIMPIAR
    public void destruir() {
        pluma1.destruir();
        pluma2.destruir();
        pluma3.destruir();
        pluma4.destruir();
        pluma5.destruir();
    }
}