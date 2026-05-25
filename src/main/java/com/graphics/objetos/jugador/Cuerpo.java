package com.graphics.objetos.jugador;

import java.util.ArrayList;
import java.util.List;

import com.graphics.graficos_y_recursos.Color;

import com.graphics.objetos.figuras_geometricas.CirculoDegradadoV;
import com.graphics.objetos.figuras_geometricas.TrianguloDegradado;
import com.graphics.sistema.IDibujable;

public class Cuerpo implements IDibujable {
    private CirculoDegradadoV cuerpoPrincipal;
    private TrianguloDegradado pluma1;
    private TrianguloDegradado pluma2;

    public Cuerpo(float x, float y, float escala, Color colorPrincipal) {
        // Variaciones del color
        Color colorOscuro = 
            new Color(Math.max(colorPrincipal.r * 0.75f, 0f),
                    Math.max(colorPrincipal.g * 0.75f, 0f),
                    Math.max(colorPrincipal.b * 0.75f, 0f),
                    1.0f);
        Color colorClaro = 
            new Color(Math.min(colorPrincipal.r * 1.15f, 1.0f),
                    Math.min(colorPrincipal.g * 1.15f, 1.0f),
                    Math.min(colorPrincipal.b * 1.15f, 1.0f),
                    1.0f);
        Color colorPluma = 
            new Color(Math.max(colorPrincipal.r * 0.88f, 0f),
                    Math.max(colorPrincipal.g * 0.88f, 0f),
                    Math.max(colorPrincipal.b * 0.88f, 0f),
                    1.0f);
        // CUERPO PRINCIPAL
        cuerpoPrincipal = 
            new CirculoDegradadoV(x, y, 
                                escala, 1.15f * escala, 
                                colorClaro, colorOscuro);
        // PLUMA 1
        pluma1 = 
            new TrianguloDegradado(x - escala * 0.38f,
                                    y + escala * 0.10f,
                                    x - escala * 0.02f,
                                    y + escala * 0.18f,
                                    x - escala * 0.18f,
                                    y - escala * 0.08f,
                                    colorOscuro,
                                    colorPluma,
                                    colorOscuro);
        // BRILLO
        pluma2 = 
            new TrianguloDegradado(x - escala * 0.28f,
                                    y - escala * 0.02f,
                                    x + escala * 0.06f,
                                    y + escala * 0.06f,
                                    x - escala * 0.10f,
                                    y - escala * 0.18f,
                                    colorOscuro,
                                    colorPluma,
                                    colorOscuro);
    } //Fin-Constructor

    @Override
    public List<Object> getPartes() {
        List<Object> partes = new ArrayList<>();
        partes.add(cuerpoPrincipal);
        // Las plumas se dibujan encima
        partes.add(pluma1);
        partes.add(pluma2);
        return partes;
    }

    // GETTERS
    public CirculoDegradadoV getCuerpoPrincipal() {
        return cuerpoPrincipal;
    }

    // LIMPIAR
    public void destruir() {
        cuerpoPrincipal.destruir();
    }
}