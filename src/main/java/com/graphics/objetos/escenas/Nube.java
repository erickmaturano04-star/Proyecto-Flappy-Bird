package com.graphics.objetos.escenas;

import com.graphics.graficos_y_recursos.Color;
import com.graphics.objetos.figuras_geometricas.CirculoDegradadoV;
import com.graphics.sistema.IDibujable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class Nube implements IDibujable {
    private float x;
    private float y;
    private CirculoDegradadoV[] partes;

    public Nube(float x, float y, float ancho, float alto, int cantidadPartes) {
        this.x = x;
        this.y = y;
        Random random = new Random();
        partes = new CirculoDegradadoV[cantidadPartes];
        // Colores
        Color luz = new Color(1.0f, 1.0f, 1.0f, 1.0f);
        Color sombra = new Color(0.9f, 0.9f, 0.92f, 0.5f);
        // Generar partes de la nube
        for (int i = 0; i < cantidadPartes; i++) {
            // Posición horizontal repartida
            float offsetX = (-ancho / 2.0f) + (ancho * i / cantidadPartes);
            // Variación vertical suave
            float offsetY = (random.nextFloat() - 0.5f) * alto * 0.4f;
            // Tamaño aleatorio suave
            float tamaño = ((ancho * 0.3f) + random.nextFloat() * 0.2f);
            partes[i] = new CirculoDegradadoV(x + offsetX, y + offsetY, tamaño, tamaño, luz, sombra);
        }
    }

    @Override
    public List<Object> getPartes() {
        return new ArrayList<>(Arrays.asList(partes));
    }

    // GETTERS
    public float getX() {
        return x;
    }
    public float getY() {
        return y;
    }
    public CirculoDegradadoV[] getPartesArray() {
        return partes;
    }

    // SETTERS
    public void setX(float nuevoX) {
        float diferencia =
        nuevoX - this.x;
        this.x = nuevoX;
        for (CirculoDegradadoV parte : partes) {
            parte.getTransform().posicion.x += diferencia;
        }
    }
    public void setY(float nuevoY) {
        float diferencia = nuevoY - this.y;
        this.y = nuevoY;
        for (CirculoDegradadoV parte : partes) {
            parte.getTransform().posicion.y += diferencia;
        }
    }

    // LIMPIAR
    public void destruir() {
        for (CirculoDegradadoV parte : partes) {
            parte.destruir();
        }
    }
}