// LA CABEZA SE VE MAL

package com.graphics.objetos.jugador;

import java.util.ArrayList;
import java.util.List;

import com.graphics.graficos_y_recursos.Color;
import com.graphics.objetos.figuras_geometricas.CirculoDegradadoV;
import com.graphics.objetos.figuras_geometricas.TrianguloDegradado;
import com.graphics.sistema.IDibujable;

public class Cabeza implements IDibujable{
    private CirculoDegradadoV cabezaPrincipal;
    private TrianguloDegradado pluma1;
    private TrianguloDegradado pluma2;

    public Cabeza(float x, float y, float escala, Color colorPrincipal) {
        // Variaciones del color principal
        Color colorOscuro = 
            new Color(Math.max(colorPrincipal.r * 0.82f, 0f),
                    Math.max(colorPrincipal.g * 0.82f, 0f),
                    Math.max(colorPrincipal.b * 0.82f, 0f),
                    1.0f);
        Color colorClaro = 
            new Color(Math.min(colorPrincipal.r * 1.12f, 1.0f),
                    Math.min(colorPrincipal.g * 1.12f, 1.0f),
                    Math.min(colorPrincipal.b * 1.12f, 1.0f),
                    1.0f);
        Color colorPluma =
            new Color(Math.max(colorPrincipal.r * 0.92f, 0f),
                    Math.max(colorPrincipal.g * 0.92f, 0f),
                    Math.max(colorPrincipal.b * 0.92f, 0f),
                    1f);
        // CABEZA PRINCIPAL
        cabezaPrincipal = 
            new CirculoDegradadoV(x, y,
                        escala, escala,
                        colorClaro, colorOscuro);
        // PLUMA 1
        pluma1 = 
            new TrianguloDegradado(x - escala * 0.22f,
                                    y + escala * 0.42f,
                                    x - escala * 0.82f,
                                    y - escala * 0.10f,
                                    x - escala * 0.42f,
                                    y - escala * 0.18f,
                                    colorPluma,
                                    colorOscuro,
                                    colorOscuro);
        // PLUMA 2
        pluma2 = 
            new TrianguloDegradado(x - escala * 0.28f,
                                    y + escala * 0.26f,
                                    x - escala * 0.70f,
                                    y - escala * 0.42f,
                                    x - escala * 0.46f,
                                    y - escala * 0.36f,
                                    colorPluma,
                                    colorOscuro,
                                    colorOscuro);
    } //Fin-Constructor

    @Override
    public List<Object> getPartes() {
        List<Object> partes = new ArrayList<>();
        partes.add(cabezaPrincipal);
        // Encima de la cabeza
        partes.add(pluma1);
        partes.add(pluma2);
        return partes;
    }

    // GETTERS
    public CirculoDegradadoV getCabezaPrincipal() {
        return cabezaPrincipal;
    }
    public TrianguloDegradado getPluma1() {
        return pluma1;
    }
    public TrianguloDegradado getPluma2() {
        return pluma2;
    }    

    // LIMPIAR
    public void destruir() {        
        cabezaPrincipal.destruir();
        pluma1.destruir();
        pluma2.destruir();        
    }
}