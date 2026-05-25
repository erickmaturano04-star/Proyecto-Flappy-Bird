package com.graphics.objetos.jugador;

import java.util.ArrayList;
import java.util.List;

import com.graphics.graficos_y_recursos.Color;
import com.graphics.objetos.figuras_geometricas.TrianguloDegradado;
import com.graphics.sistema.IDibujable;

public class Pico implements IDibujable {
    private TrianguloDegradado picoSuperior;
    private TrianguloDegradado picoInferior;

    public Pico(float x, float y, float escala) {
        Color naranjaClaro = new Color(1.0f, 0.9f, 0.3f, 1.0f);
        Color naranja = new Color(1.0f, 0.65f, 0.0f, 1.0f);
        Color naranjaOscuro = new Color(0.9f, 0.45f, 0.0f, 1.0f);
        // PICO SUPERIOR
        picoSuperior = 
            new TrianguloDegradado(x, y, 
                                    x + escala, y + 0.10f * escala,
                                    x, y + escala * 0.30f, 
                                    naranjaClaro, naranja, naranjaOscuro);
        // PICO INFERIOR
        picoInferior = 
            new TrianguloDegradado(x, y, 
                                    x + 0.85f * escala, y - 0.08f * escala,
                                    x, y - escala * 0.20f,
                                    naranja, naranjaOscuro, naranjaOscuro);
    }

    @Override
    public List<Object> getPartes() {
        List<Object> partes = new ArrayList<>();
        partes.add(picoSuperior);
        partes.add(picoInferior);
        return partes;
    }

    // GETTERS
    public TrianguloDegradado getPicoSuperior() {
        return picoSuperior;
    }
    public TrianguloDegradado getPicoInferior() {
        return picoInferior;
    }

    // LIMPIAR
    public void destruir() {
        picoSuperior.destruir();
        picoInferior.destruir();
    }
}