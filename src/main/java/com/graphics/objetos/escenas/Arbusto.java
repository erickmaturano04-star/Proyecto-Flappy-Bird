package com.graphics.objetos.escenas;

import com.graphics.graficos_y_recursos.Color;
import com.graphics.objetos.figuras_geometricas.CirculoDegradadoV;
import com.graphics.sistema.IDibujable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class Arbusto implements IDibujable {
    private float x;
    private float y;
    private CirculoDegradadoV[] hojas;

    public Arbusto(float x, float y, float ancho, float alto, int cantidadHojas) {
        this.x = x;
        this.y = y;
        Random random = new Random();
        hojas = new CirculoDegradadoV[cantidadHojas];
        // Colores
        Color verdeLuz = new Color(0.15f, 0.55f, 0.15f, 1.0f);
        Color verdeSombra = new Color(0.08f, 0.30f, 0.08f, 1.0f);
        // Generar hojas
        for (int i = 0; i < cantidadHojas; i++) {
            // Distribución elíptica
            float radioX = ancho * 0.32f;
            float radioY = alto * 0.22f;
            // Distribución uniforme
            float anguloBase = (360.0f / cantidadHojas) * i;
            // Variación natural
            float variacion = (random.nextFloat() - 0.5f) * 18.0f;
            float angulo = anguloBase + variacion;
            // Evita huecos y exceso en el centro
            float distancia = random.nextFloat() * 0.6f;
            // Posición elíptica
            float offsetX =
                (float)Math.cos(Math.toRadians(angulo)) * radioX * distancia;
            float offsetY =
                (float)Math.sin(Math.toRadians(angulo)) * radioY * distancia;
            // Compactar verticalmente
            offsetY *= 0.75f;
            // Levantar ligeramente la copa
            offsetY += alto * 0.08f;
            // Tamaños variados
            float tamaño = 0.15f + random.nextFloat() * 0.01f;
            // Generar Hojas
            hojas[i] = new CirculoDegradadoV(x + offsetX, y + offsetY, tamaño, tamaño, verdeLuz, verdeSombra);
        }  
    }

    @Override
    public List<Object> getPartes() {
        return new ArrayList<>(Arrays.asList(hojas));
    }

    // GETTERS
    public float getX() {
        return x;
    }
    public float getY() {
        return y;
    }
    public CirculoDegradadoV[] getHojas() {
        return hojas;
    }

    // SETTERS
    public void setX(float nuevoX) {
        float diferencia =
        nuevoX - this.x;
        this.x = nuevoX;
        for (CirculoDegradadoV hoja : hojas) {
            hoja.getTransform().posicion.x += diferencia;
        }
    }
    public void setY(float nuevoY) {
        float diferencia =nuevoY - this.y;
        this.y = nuevoY;
        for (CirculoDegradadoV hoja : hojas) {
            hoja.getTransform().posicion.y += diferencia;
        }
    }

    // LIMPIAR
    public void destruir() {
        for (CirculoDegradadoV hoja : hojas) {
            hoja.destruir();
        }
    }
}