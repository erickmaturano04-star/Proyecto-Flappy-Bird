package com.graphics.objetos.jugador;

import java.util.ArrayList;
import java.util.List;

import com.graphics.graficos_y_recursos.Color;

import com.graphics.objetos.figuras_geometricas.Circulo;
import com.graphics.objetos.figuras_geometricas.CirculoDegradadoC;
import com.graphics.sistema.IDibujable;

public class Ojo implements IDibujable {
    private CirculoDegradadoC ojo;
    private CirculoDegradadoC pupila;
    private Circulo brillo;

    public Ojo(float x, float y, float escala) {
        // GLOBO OCULAR
        ojo = 
            new CirculoDegradadoC(x, y, escala, escala, 
                                new Color(1f, 1f, 1f, 1f),
                                new Color(0.85f, 0.85f, 0.85f, 1f));
        // PUPILA
        pupila = 
            new CirculoDegradadoC(x + 0.18f * escala,
                                y - 0.06f * escala,
                                0.42f * escala,
                                0.42f * escala,
                                new Color(0.1f, 0.1f, 0.1f, 1f),
                                new Color(0.35f, 0.35f, 0.35f, 1f));
        // BRILLO DEL OJO
        brillo = 
            new Circulo(x + 0.28f * escala,
                        y + 0.12f * escala,
                        0.10f * escala,
                        0.10f * escala,
                        new Color(1f, 1f, 1f, 1f));
    }

    @Override
    public List<Object> getPartes() {
        List<Object> partes = new ArrayList<>();
        partes.add(ojo);
        partes.add(pupila);
        partes.add(brillo);
        return partes;
    }

    // GETTERS
    public CirculoDegradadoC getOjo() {
        return ojo;
    }
    public CirculoDegradadoC getPupila() {
        return pupila;
    }
    public Circulo getBrillo() {
        return brillo;
    }

    // LIMPIAR
    public void destruir() {
        ojo.destruir();
        pupila.destruir();
        brillo.destruir();
    }
}