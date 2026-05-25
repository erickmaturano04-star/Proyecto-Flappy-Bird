package com.graphics.objetos.escenas;

import com.graphics.graficos_y_recursos.Color;

import com.graphics.objetos.figuras_geometricas.CirculoDegradadoV;
import com.graphics.objetos.figuras_geometricas.CuadradoDegradadoV;
import com.graphics.sistema.IDibujable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class Arbol implements IDibujable {
    private float x;
    private float y;
    private CuadradoDegradadoV tronco;
    private CuadradoDegradadoV ramaIzquierda;
    private CuadradoDegradadoV ramaDerecha;
    private CirculoDegradadoV[] hojas;
    private CirculoDegradadoV sombraCopa;

    public Arbol(float x, float y, float escala, int cantidadHojas) {
        this.x = x;
        this.y = y;
        Random random = new Random();
        // Colores
        Color marron = new Color(0.55f, 0.32f, 0.12f, 1.0f);
        Color marronOscuro = new Color(0.28f, 0.15f, 0.05f, 1.0f);
        Color verdeClaro = new Color(0.30f, 0.72f, 0.30f, 1.0f);
        Color verdeOscuro = new Color(0.16f, 0.52f, 0.16f, 1.0f);
        // TRONCO
        tronco = new CuadradoDegradadoV(x, y + 0.05f * escala, 0.10f * escala, 0.60f * escala, marron, marronOscuro);
        // RAMA IZQUIERDA
        ramaIzquierda = new CuadradoDegradadoV(x - 0.035f * escala, y + 0.20f * escala,
                                    0.07f * escala, 0.02f * escala, marron, marronOscuro);
        ramaIzquierda.getTransform().rotacion = -28.0f;
        // RAMA DERECHA
        ramaDerecha = new CuadradoDegradadoV(x + 0.035f * escala, y + 0.24f * escala,
                                    0.07f * escala, 0.02f * escala, marron, marronOscuro);
        ramaDerecha.getTransform().rotacion = 28.0f;
        // HOJAS
        hojas = new CirculoDegradadoV[cantidadHojas];
        for (int i = 0; i < cantidadHojas; i++) {
            // Tamaño de la Copa
            float radioX = 0.24f * escala;
            float radioY = 0.14f * escala;
            // Distrbucion uniforme alrededor de la copa
            float anguloBase = (360.0f / cantidadHojas) * i;
            // Variación natural
            float variacion = (random.nextFloat() - 0.5f) * 35.0f;
            float angulo = anguloBase + variacion;
            // Evita acumulación en el centro
            float distancia = 0.45f + random.nextFloat() * 0.55f;
            // Posicion Eliptica
            float offsetX = 
                (float)Math.cos(Math.toRadians(angulo)) * radioX * distancia;
            float offsetY = 
                (float)Math.sin(Math.toRadians(angulo)) * radioY * distancia;
            float tamaño = (0.2f + random.nextFloat() * 0.05f) * escala;
            hojas[i] = new CirculoDegradadoV(x + offsetX, y + 0.36f * escala + offsetY, 
                                    tamaño, tamaño, verdeClaro, verdeOscuro);
        }

        // SOMBRA GENERAL DE LA COPA
        sombraCopa = 
            new CirculoDegradadoV(x, y + 0.33f * escala, 0.55f * escala, 
                                0.22f * escala,
                                verdeClaro,
                                new Color(
                                    0.12f,
                                    0.42f,
                                    0.12f,
                                    1.0f
                                )
                            );
    }

    @Override
    public List<Object> getPartes() {
        List<Object> partes = new ArrayList<>();
        // El tronco va atrás
        partes.add(tronco);
        partes.add(ramaIzquierda);
        partes.add(ramaDerecha);
        // La sombra de la copa sirve de base para las hojas individuales
        partes.add(sombraCopa);
        // Las hojas individuales van al frente para dar detalle
        partes.addAll(Arrays.asList(hojas));
        return partes;
    }

    // GETTERS
    public float getX() {
        return x;
    }
    public float getY() {
        return y;
    }
    public CuadradoDegradadoV getTronco() {
        return tronco;
    }
    public CuadradoDegradadoV getRamaIzquierda() {
        return ramaIzquierda;
    }
    public CuadradoDegradadoV getRamaDerecha() {
        return ramaDerecha;
    }
    public CirculoDegradadoV[] getHojas() {
        return hojas;
    }
    public CirculoDegradadoV getSombraCopa() {
        return sombraCopa;
    }

    // SETTERS
    public void setX(float nuevoX) {
        float diferencia = nuevoX - this.x;
        this.x = nuevoX;
        tronco.getTransform().posicion.x += diferencia;
        ramaIzquierda.getTransform().posicion.x += diferencia;
        ramaDerecha.getTransform().posicion.x += diferencia;
        sombraCopa.getTransform().posicion.x += diferencia;
        for (CirculoDegradadoV hoja : hojas) {
            hoja.getTransform().posicion.x += diferencia;
        }
    }
    public void setY(float nuevoY) {
        float diferencia = nuevoY - this.y;
        this.y = nuevoY;
        tronco.getTransform().posicion.y += diferencia;
        ramaIzquierda.getTransform().posicion.y += diferencia;
        ramaDerecha.getTransform().posicion.y += diferencia;
        sombraCopa.getTransform().posicion.y += diferencia;
        for (CirculoDegradadoV hoja : hojas) {
            hoja.getTransform().posicion.y += diferencia;
        }
    }

    // LIMPIAR
    public void destruir() {
        tronco.destruir();
        ramaIzquierda.destruir();
        ramaDerecha.destruir();
        for (CirculoDegradadoV hoja : hojas) {
            hoja.destruir();
        }
        sombraCopa.destruir();
    }
}