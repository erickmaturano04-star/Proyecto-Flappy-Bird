package com.graphics.objetos.escenas;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import com.graphics.fisica.MovimientoHorizontal;
import com.graphics.sistema.IDibujable;

public class Fondo implements IDibujable {
    private MovimientoHorizontal movimiento;
    private Random random;
    private Cielo cielo;
    private Nube[] nubes;
    private Arbol[] arboles;
    private Arbusto[] arbustos;
    private Suelo suelo;

    public Fondo() {
        movimiento =
            new MovimientoHorizontal(0.25f, 
                                    -3.0f, 
                                    3.0f);
        random = new Random();

        // CIELO
        cielo = new Cielo();

        // NUBES        
        nubes = new Nube[] {
            new Nube(
                -0.75f,
                0.65f,
                0.45f,
                0.20f,
                5
            ),
            new Nube(
                0.10f,
                0.72f,
                0.60f,
                0.25f,
                7
            ),
            new Nube(
                0.80f,
                0.58f,
                0.40f,
                0.18f,
                4
            )
        };

        // ÁRBOLES
        arboles = new Arbol[] {
            new Arbol(
                -0.75f,
                -0.42f,
                1.0f,
                8
            ),
            new Arbol(
                0.65f,
                -0.45f,
                0.85f,
                10
            )
        };

        // ARBUSTOS
        arbustos = new Arbusto[] {
            new Arbusto(
                -0.30f,
                -0.68f,
                0.35f,
                0.18f,
                4
            ),
            new Arbusto(
                0.25f,
                -0.70f,
                0.45f,
                0.20f,
                5
            ),
            new Arbusto(
                0.82f,
                -0.69f,
                0.40f,
                0.15f,
                3
            )
        };

        // SUELO
        suelo = new Suelo();
    }

    @Override
    public List<Object> getPartes() {
        List<Object> partesEscena = new ArrayList<>();
        // Capa 0: El cielo (lo más profundo)
        partesEscena.add(cielo);
        // Capa 1: Las nubes (sobre el cielo)
        partesEscena.addAll(Arrays.asList(nubes));
        // Capa 2: El suelo (base para la vegetación)
        partesEscena.add(suelo);
        // Capa 3: Árboles (detrás de los arbustos pero sobre el suelo)
        partesEscena.addAll(Arrays.asList(arboles));
        // Capa 4: Arbustos (en el primer plano)
        partesEscena.addAll(Arrays.asList(arbustos));
        return partesEscena;
    }

    public void actualizar(float deltaTime) {
        moverDecoraciones(deltaTime);
        reciclarDecoraciones();
    }

    private void moverDecoraciones(float deltaTime) {
        for (Nube nube : nubes) {
            nube.setX(nube.getX() - movimiento.getVelocidad() * deltaTime);
        }
        for (Arbol arbol : arboles) {
            arbol.setX(arbol.getX() - movimiento.getVelocidad() * deltaTime);
        }
        for (Arbusto arbusto : arbustos) {
            arbusto.setX(arbusto.getX() - movimiento.getVelocidad() * deltaTime);
        }
    }

    private void reciclarDecoraciones() {
        for (Nube nube : nubes) {
            if (nube.getX() < -1.3f) {
                nube.setX(1.2f + random.nextFloat() * 0.4f);
            }
        }
        for (Arbol arbol : arboles) {
            if (arbol.getX() < -1.4f) {
                arbol.setX(1.3f + random.nextFloat() * 0.4f);
            }
        }
        for (Arbusto arbusto : arbustos) {
            if (arbusto.getX() < -1.2f) {
                arbusto.setX(1.1f + random.nextFloat() * 0.4f);
            }
        }
    }

    // GETTERS
    public Cielo getCielo() {
        return cielo;
    }
    public Nube[] getNubes() {
        return nubes;
    }
    public Arbol[] getArboles() {
        return arboles;
    }
    public Arbusto[] getArbustos() {
        return arbustos;
    }
    public Suelo getSuelo() {
        return suelo;
    }

    // LIMPIAR
    public void destruir() {
        cielo.destruir();
        for (Nube nube : nubes) {
            nube.destruir();
        }
        for (Arbol arbol : arboles) {
            arbol.destruir();
        }
        for (Arbusto arbusto : arbustos) {
            arbusto.destruir();
        }
        suelo.destruir();
    }
}