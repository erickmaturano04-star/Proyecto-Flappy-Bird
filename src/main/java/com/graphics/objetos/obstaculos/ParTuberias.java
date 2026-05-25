package com.graphics.objetos.obstaculos;

import com.graphics.fisica.MovimientoHorizontal;
import com.graphics.sistema.IDibujable;
import com.graphics.objetos.jugador.Pajaro;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ParTuberias implements IDibujable {

    private float x;
    private float centroHueco;
    private float espacio;

    private Tuberia superior;
    private Tuberia inferior;

    private MovimientoHorizontal movimiento;
    private Random random;

    // control de puntuación por jugador
    private boolean yaContoP1 = false;
    private boolean yaContoP2 = false;

    public ParTuberias(float x, float centroHueco, float espacio) {
        this.x = x;
        this.centroHueco = centroHueco;
        this.espacio = espacio;

        random = new Random();
        movimiento = new MovimientoHorizontal(1.2f, -2.5f, 2.5f);

        crearTuberias();
    }

    private void crearTuberias() {
        float mitadHueco = espacio * 0.5f;
        float ySuperior = centroHueco + mitadHueco + 0.65f;
        float yInferior = centroHueco - mitadHueco - 0.65f;

        superior = new Tuberia(x, ySuperior, true);
        inferior = new Tuberia(x, yInferior, false);
    }

    public void actualizar(float deltaTime) {
        x -= movimiento.getVelocidad() * deltaTime;
        superior.setX(x);
        inferior.setX(x);
    }

    // DETECTAR PASO DEL PAJARO
    public boolean pajaroPaso(Pajaro p, boolean esP1) {
        if (p.getCabezaColision().getX() > x) {
            if (esP1 && !yaContoP1) {
                yaContoP1 = true;
                return true;
            }
            if (!esP1 && !yaContoP2) {
                yaContoP2 = true;
                return true;
            }
        }
        return false;
    }

    // 🔥 RESET cuando reciclas el tubo
    public void resetPuntajePaso() {
        yaContoP1 = false;
        yaContoP2 = false;
    }

    // 🔥 El reciclaje lo controla JuegoP1P2
    public void setX(float nuevoX) {
        this.x = nuevoX;
        superior.setX(nuevoX);
        inferior.setX(nuevoX);
    }

    public void randomizarHueco() {
        centroHueco = -0.35f + random.nextFloat() * 0.7f;
        actualizarAlturas();
    }

    private void actualizarAlturas() {
        float mitadHueco = espacio * 0.5f;
        float ySuperior = centroHueco + mitadHueco + 0.65f;
        float yInferior = centroHueco - mitadHueco - 0.65f;

        superior.setY(ySuperior);
        inferior.setY(yInferior);
    }

    @Override
    public List<Object> getPartes() {
        List<Object> partes = new ArrayList<>();
        partes.addAll(superior.getPartes());
        partes.addAll(inferior.getPartes());
        return partes;
    }

    // GETTERS
    public float getX() { return x; }
    public Tuberia getSuperior() { return superior; }
    public Tuberia getInferior() { return inferior; }

    // SETTERS
    public void setVelocidad(float velocidad) {
        movimiento.setVelocidad(velocidad);
    }

    public void destruir() {
        superior.destruir();
        inferior.destruir();
    }
}