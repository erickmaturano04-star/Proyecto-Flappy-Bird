package com.graphics.objetos.jugador;

import java.util.ArrayList;
import java.util.List;

import com.graphics.graficos_y_recursos.Color;

import com.graphics.objetos.figuras_geometricas.CuadradoDegradadoV;
import com.graphics.objetos.figuras_geometricas.TrianguloDegradado;
import com.graphics.sistema.IDibujable;

public class Patas implements IDibujable {
    private CuadradoDegradadoV piernaIzquierda;
    private CuadradoDegradadoV piernaDerecha;
    private TrianguloDegradado pieIzquierdo;
    private TrianguloDegradado pieDerecho;

    public Patas(float x, float y, float escala) {
        Color colorClaro = new Color(1.0f, 0.72f, 0.18f, 1f);
        Color colorOscuro = new Color(0.82f, 0.45f, 0.05f, 1f);
        // PIERNAS
        piernaIzquierda = 
            new CuadradoDegradadoV(x - 0.14f * escala, 
                                    y - escala * 0.58f, 
                                    0.07f * escala,
                                    0.34f * escala, 
                                    colorClaro,
                                    colorOscuro);
        piernaIzquierda.getTransform().rotacion = -18f;
        piernaDerecha = 
            new CuadradoDegradadoV(x + 0.04f * escala, 
                                    y - escala * 0.54f, 
                                    0.07f * escala, 
                                    0.34f * escala, 
                                    colorClaro,
                                    colorOscuro);
        piernaDerecha.getTransform().rotacion = -18f;
        // PIE IZQUIERDO
        pieIzquierdo = 
            new TrianguloDegradado(x - escala * 0.14f,
                                    y - escala * 0.66f,
                                    x - escala * 0.25f,
                                    y - escala * 0.74f,
                                    x + escala * 0.04f,
                                    y - escala * 0.80f,
                                    colorClaro,
                                    colorOscuro,
                                    colorOscuro);
        // PIE DERECHO
        pieDerecho = 
            new TrianguloDegradado(x + escala * 0.05f,
                                    y - escala * 0.62f,
                                    x - escala * 0.08f,
                                    y - escala * 0.70f,
                                    x + escala * 0.21f,
                                    y - escala * 0.78f,
                                    colorClaro,
                                    colorOscuro,
                                    colorOscuro);
    }

    @Override
    public List<Object> getPartes() {
        List<Object> partes = new ArrayList<>();
        partes.add(piernaIzquierda);
        partes.add(piernaDerecha);
        partes.add(pieIzquierdo);
        partes.add(pieDerecho);
        return partes;
    }

    // GETTERS
    public CuadradoDegradadoV getPiernaIzquierda() {
        return piernaIzquierda;
    }
    public CuadradoDegradadoV getPiernaDerecha() {
        return piernaDerecha;
    }
    public TrianguloDegradado getPieIzquierdo() {
        return pieIzquierdo;
    }
    public TrianguloDegradado getPieDerecho() {
        return pieDerecho;
    }

    //LIMPIAR
    public void destruir() {
        piernaIzquierda.destruir();
        piernaDerecha.destruir();
        pieIzquierdo.destruir();
        pieDerecho.destruir();
    }
}