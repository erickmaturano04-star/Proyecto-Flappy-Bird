package com.graphics.objetos.escenas;

import java.util.Collections;
import java.util.List;

import com.graphics.graficos_y_recursos.Color;
import com.graphics.objetos.figuras_geometricas.CuadradoDegradadoV;
import com.graphics.sistema.IDibujable;

public class Cielo implements IDibujable {
    private CuadradoDegradadoV fondo;

    public Cielo() {
        // Colores
        Color azulSuperior = new Color(0.74f, 0.88f, 1.0f, 1.0f);
        Color azulInferior = new Color(0.42f, 0.68f, 0.96f, 1.0f);
        // Fondo principal
        fondo = new CuadradoDegradadoV(0.0f, 0.0f, 2.4f,
                                2.4f, azulSuperior, azulInferior);
    }

    @Override
    public List<Object> getPartes() {
        return Collections.singletonList(fondo);
    }
    
    public CuadradoDegradadoV getFondo() {
        return fondo;
    }

    public void destruir() {
        fondo.destruir();
    }
}