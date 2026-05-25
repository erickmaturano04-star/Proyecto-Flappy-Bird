package com.graphics.objetos.jugador;

import java.util.ArrayList;
import java.util.List;

import com.graphics.graficos_y_recursos.Color;
import com.graphics.objetos.figuras_geometricas.TrianguloDegradado;
import com.graphics.sistema.IDibujable;

public class Cola implements IDibujable {
    private TrianguloDegradado plumaIzquierda;
    private TrianguloDegradado plumaCentral;
    private TrianguloDegradado plumaDerecha;

    public Cola(float x, float y, float escala, Color colorPrincipal) {
        // Variaciones del color principal
        Color colorOscuro = 
            new Color(Math.max(colorPrincipal.r * 0.78f, 0f),
                    Math.max(colorPrincipal.g * 0.78f, 0f),
                    Math.max(colorPrincipal.b * 0.78f, 0f),
                    1.0f);
        Color colorClaro = 
            new Color(Math.min(colorPrincipal.r * 1.08f, 1.0f),
                    Math.min(colorPrincipal.g * 1.08f, 1.0f),
                    Math.min(colorPrincipal.b * 1.08f, 1.0f),
                    1.0f);
        // PLUMA IZQUIERDA
        plumaIzquierda = 
            new TrianguloDegradado(x - escala * 0.10f,
                                    y + escala * 0.08f,
                                    x - escala * 0.62f,
                                    y + escala * 0.22f,
                                    x - escala * 0.18f,
                                    y - escala * 0.10f,
                                    colorClaro,
                                    colorOscuro,
                                    colorOscuro);
        // PLUMA CENTRAL
        plumaCentral = 
            new TrianguloDegradado(x - escala * 0.02f,
                                    y + escala * 0.02f,
                                    x - escala * 0.75f,
                                    y,
                                    x - escala * 0.05f,
                                    y - escala * 0.18f,
                                    colorClaro,
                                    colorOscuro,
                                    colorOscuro);
        // PLUMA DERECHA
        plumaDerecha = 
            new TrianguloDegradado(x + escala * 0.06f,
                                    y - escala * 0.06f,
                                    x - escala * 0.52f,
                                    y - escala * 0.24f,
                                    x - escala * 0.10f,
                                    y - escala * 0.26f,
                                    colorClaro,
                                    colorOscuro,
                                    colorOscuro);
    } //Fin-Constructor

    @Override
    public List<Object> getPartes() {
        List<Object> partes = new ArrayList<>();
        partes.add(plumaIzquierda);
        partes.add(plumaCentral);
        partes.add(plumaDerecha);
        return partes;
    }

    // GETTERS
    public TrianguloDegradado getPlumaIzquierda() {
        return plumaIzquierda;
    }
    public TrianguloDegradado getPlumaCentral() {
        return plumaCentral;
    }
    public TrianguloDegradado getPlumaDerecha() {
        return plumaDerecha;
    }

    // LIMPIAR
    public void destruir() {
        plumaIzquierda.destruir();
        plumaCentral.destruir();
        plumaDerecha.destruir();
    }
}