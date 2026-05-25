package com.graphics.objetos.obstaculos;

import java.util.ArrayList;
import java.util.List;

import com.graphics.fisica.CajaColision;
import com.graphics.fisica.MovimientoHorizontal;
import com.graphics.graficos_y_recursos.Color;

import com.graphics.objetos.figuras_geometricas.CuadradoDegradado4;
import com.graphics.sistema.IDibujable;

public class Tuberia implements IDibujable {
    private MovimientoHorizontal movimiento;
    private float x, y;
    
    // Ahora usamos dos cajas de colisión para que coincidan con la forma visual
    private CajaColision cajaCuerpo;
    private CajaColision cajaBorde;
    
    // Partes 
    private CuadradoDegradado4 cuerpo;
    private CuadradoDegradado4 borde;
    
    // Medidas base
    private static final float ANCHO_CUERPO = 0.22f;
    private static final float ALTO_CUERPO = 1.2f;
    private static final float ANCHO_BORDE = 0.30f;
    private static final float ALTO_BORDE = 0.12f;

    public Tuberia(float x, float y, boolean invertido) {
        this.x = x;
        this.y = y;
        movimiento = new MovimientoHorizontal(1.0f, -2.5f, 2.5f);
        
        // DIRECCION
        float direccion = 1.0f;
        if (invertido) {
            direccion = -1.0f;
        }
        
        // El cuerpo visual mide ANCHO_CUERPO (0.22f). Centrado en X.
        // La caja colisión será un pelín menor que el visual para que no sea injusto (0.20f)
        float altoCuerpoVisual = ALTO_CUERPO; // 1.2f
        cajaCuerpo = new CajaColision(x - 0.10f, y - 0.60f, 0.20f, 1.2f);
        
        // El borde visual está en Y = y + 0.55f * direccion y mide ALTO_BORDE (0.12f)
        float yBorde = y + (0.55f * direccion);
        // El borde visual mide ANCHO_BORDE (0.30f). Centrado en X.
        // La caja de colisión del borde será casi igual al visual (0.28f)
        cajaBorde = new CajaColision(x - 0.14f, yBorde - (ALTO_BORDE / 2.0f), 0.28f, ALTO_BORDE);

        // Colores
        Color verdeClaro = new Color(0.45f, 1.0f, 0.45f, 1.0f);
        Color verdeMedio = new Color(0.0f, 0.78f, 0.0f, 1.0f);
        Color verdeOscuro = new Color(0.0f, 0.42f, 0.0f, 1.0f);
        
        // CUERPO
        cuerpo = 
            new CuadradoDegradado4(x, y, ANCHO_CUERPO, altoCuerpoVisual, 
                                    verdeClaro, verdeOscuro, verdeClaro, verdeOscuro);
        // BORDE
        borde = new CuadradoDegradado4(x, yBorde, ANCHO_BORDE, 
                                        ALTO_BORDE, verdeClaro,
                                        verdeOscuro, verdeMedio, 
                                        verdeOscuro);        
    }

    @Override
    public List<Object> getPartes() {
        List<Object> partes = new ArrayList<>();
        partes.add(cuerpo);
        partes.add(borde);
        return partes;
    }
    
    public void actualizar(float deltaTime) {
        x = movimiento.mover(x, ANCHO_BORDE, deltaTime);
        actualizarPartes();
    }

    private void actualizarPartes() {
        cuerpo.getTransform().posicion.x = x;
        borde.getTransform().posicion.x = x;
    }

    // GETTERS
    public float getX() {
        return x;
    }
    public float getY() {
        return y;
    }
    public CuadradoDegradado4 getCuerpo() {
        return cuerpo;
    }
    public CuadradoDegradado4 getBorde() {
        return borde;
    }
    public CajaColision getCajaCuerpo() {
        return cajaCuerpo;
    }
    public CajaColision getCajaBorde() {
        return cajaBorde;
    }

    // SETTERS
    public void setX(float nuevoX) {
        float diferencia = nuevoX - this.x;
        this.x = nuevoX;
        cuerpo.getTransform().posicion.x += diferencia;
        borde.getTransform().posicion.x += diferencia;
        cajaCuerpo.setX(cajaCuerpo.getX() + diferencia);
        cajaBorde.setX(cajaBorde.getX() + diferencia);
    }
    public void setY(float nuevoY) {
        float diferencia = nuevoY - this.y;
        this.y = nuevoY;
        cuerpo.getTransform().posicion.y += diferencia;
        borde.getTransform().posicion.y += diferencia;
        cajaCuerpo.setY(cajaCuerpo.getY() + diferencia);
        cajaBorde.setY(cajaBorde.getY() + diferencia);
    }

    // LIMPIEZA
    public void destruir() {
        cuerpo.destruir();
        borde.destruir();
    }
}