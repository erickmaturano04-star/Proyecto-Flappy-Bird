package com.graphics.objetos.jugador;

import java.util.ArrayList;
import java.util.List;

import com.graphics.fisica.CirculoColision;
import com.graphics.graficos_y_recursos.Color;
import com.graphics.matematicas.Transform;
import org.joml.Vector2f;
import com.graphics.sistema.IDibujable;

public class Pajaro implements IDibujable {
    private float x, y, rotacion;
    private Transform transformGlobal;
    private CirculoColision cabezaColision;
    private CirculoColision cuerpoColision;

    private Cabeza cabeza;
    private Cuerpo cuerpo;
    private Ala ala1;
    private Ala ala2;
    private Cola cola;
    private Ojo ojo1;
    private Ojo ojo2;
    private Pico pico;
    private Patas patas;

    public Pajaro(float x, float y, float escala, Color colorPrincipal) {
        this.x = x;
        this.y = y;
        this.rotacion = 0;
        
        this.transformGlobal = new Transform(new Vector2f(x, y), new Vector2f(1, 1), rotacion);

        cabezaColision = new CirculoColision(
                x + 0.22f * escala,
                y + 0.08f * escala,
                0.11f * escala);

        cuerpoColision = new CirculoColision(
                x - 0.02f * escala,
                y,
                0.16f * escala);

        // PIEZAS EN COORDENADAS LOCALES (relativas a 0,0)
        cola = new Cola(-0.20f * escala, 0.04f * escala, escala, colorPrincipal);
        patas = new Patas(0.02f * escala, -0.07f * escala, escala * 0.72f);
        cuerpo = new Cuerpo(0f, 0f, escala * 0.68f, colorPrincipal);
        ala1 = new Ala(-0.02f * escala, 0.16f * escala, escala * 0.88f, colorPrincipal);
        ala2 = new Ala(0.08f * escala, 0.13f * escala, escala * 0.88f, colorPrincipal);
        cabeza = new Cabeza(0.40f * escala, 0.22f * escala, escala * 0.49f, colorPrincipal);
        pico = new Pico(0.60f * escala, 0.14f * escala, escala * 0.34f);
        ojo1 = new Ojo(0.56f * escala, 0.34f * escala, escala * 0.18f);
        ojo2 = new Ojo(0.62f * escala, 0.34f * escala, escala * 0.18f);
    }

    public Transform getTransform() {
        return transformGlobal;
    }

    @Override
    public List<Object> getPartes() {
        List<Object> partes = new ArrayList<>();
        partes.addAll(ala2.getPartes());
        partes.addAll(cuerpo.getPartes());
        partes.addAll(cabeza.getPartes());
        partes.addAll(cola.getPartes());
        partes.addAll(patas.getPartes());
        partes.addAll(ala1.getPartes());
        partes.addAll(ojo2.getPartes());
        partes.addAll(ojo1.getPartes());
        partes.addAll(pico.getPartes());
        return partes;
    }

    public float getX() { return x; }
    public float getY() { return y; }
    public float getRotacion() { return rotacion; }

    public CirculoColision getCabezaColision() { return cabezaColision; }
    public CirculoColision getCuerpoColision() { return cuerpoColision; }

    public Ala getAla1() { return ala1; }
    public Ala getAla2() { return ala2; }

    public void setX(float nuevoX) {
        float difX = nuevoX - this.x;
        this.x = nuevoX;
        this.transformGlobal.posicion.x = nuevoX;
        cabezaColision.setX(cabezaColision.getX() + difX);
        cuerpoColision.setX(cuerpoColision.getX() + difX);
    }

    public void setY(float nuevoY) {
        float difY = nuevoY - this.y;
        this.y = nuevoY;
        this.transformGlobal.posicion.y = nuevoY;
        cabezaColision.setY(cabezaColision.getY() + difY);
        cuerpoColision.setY(cuerpoColision.getY() + difY);
    }

    public void setRotacion(float r) {
        this.rotacion = r;
        this.transformGlobal.rotacion = r;
    }

    public void destruir() {
        cola.destruir();
        patas.destruir();
        cuerpo.destruir();
        ala1.destruir();
        ala2.destruir();
        cabeza.destruir();
        pico.destruir();
        ojo1.destruir();
        ojo2.destruir();
    }
}