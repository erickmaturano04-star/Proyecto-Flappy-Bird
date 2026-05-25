package com.graphics.objetos.escenas;

import java.util.ArrayList;
import java.util.List;

import com.graphics.graficos_y_recursos.Color;
import com.graphics.objetos.figuras_geometricas.CuadradoDegradado4;
import com.graphics.sistema.IDibujable;

public class Suelo implements IDibujable {
    private CuadradoDegradado4 cesped;
    private CuadradoDegradado4 brilloCesped;
    private CuadradoDegradado4 bordeCesped;
    private CuadradoDegradado4 tierra;

    public Suelo() {
        // Colores
        Color verdeBrillo = new Color(0.70f, 1.0f, 0.55f, 1.0f);
        Color verdeClaro = new Color(0.42f, 0.92f, 0.42f, 1.0f);
        Color verdeMedio = new Color(0.24f, 0.72f, 0.24f, 1.0f);
        Color verdeOscuro = new Color(0.12f, 0.45f, 0.12f, 1.0f);
        Color marronClaro = new Color(0.72f, 0.48f, 0.22f, 1.0f);
        Color marronMedio = new Color(0.55f, 0.34f, 0.16f, 1.0f );
        Color marronOscuro = new Color(0.34f, 0.20f, 0.08f, 1.0f);
        // Cesped
        cesped = new CuadradoDegradado4(0.0f, -0.72f, 2.2f,
                                    0.10f, verdeClaro, verdeClaro,
                                    verdeMedio,verdeOscuro);
        // Brillo del Cesped
        brilloCesped = 
            new CuadradoDegradado4(0.0f, -0.665f, 2.2f,
                                    0.018f, verdeBrillo, 
                                    verdeBrillo, verdeClaro, verdeClaro);
        // Borde del Cesped
        bordeCesped =
            new CuadradoDegradado4(0.0f, -0.775f, 2.2f,
                                    0.018f, verdeOscuro, 
                                    verdeOscuro, verdeOscuro, verdeOscuro);        
        // Tierra
        tierra = new CuadradoDegradado4(0.0f, -0.94f, 2.2f,
                                    0.36f, marronClaro, marronMedio,
                                    marronMedio, marronOscuro);
    }

    @Override
    public List<Object> getPartes() {
        List<Object> partes = new ArrayList<>();
        // El orden aquí importa: los primeros en la lista se dibujan primero (fondo)
        // y los últimos se dibujan encima.
        partes.add(tierra);
        partes.add(bordeCesped);
        partes.add(cesped);
        partes.add(brilloCesped);
        return partes;
    }
    
    // GETTERS
    public CuadradoDegradado4 getCesped() {
        return cesped;
    }
    public CuadradoDegradado4 getBrilloCesped() {
        return brilloCesped;
    }
    public CuadradoDegradado4 getBordeCesped() {
        return bordeCesped;
    }
    public CuadradoDegradado4 getTierra() {
        return tierra;
    }

    // LIMPIEZA
    public void destruir() {
        cesped.destruir();
        brilloCesped.destruir();
        bordeCesped.destruir();
        tierra.destruir();
    }
}