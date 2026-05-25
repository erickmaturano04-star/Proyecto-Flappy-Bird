/* MODOS - JuegoP1P2
    Controla la logica del modo multijugador P1 vs P2 */
package com.graphics.juego.modos;

import com.graphics.graficos_y_recursos.Shader;
import com.graphics.objetos.obstaculos.ParTuberias;
import com.graphics.objetos.jugador.Pajaro;
import com.graphics.sistema.ObjectRenderer;
import com.graphics.sistema.RendererJerarquico;
import com.graphics.fisica.ControladorPajaro;
import com.graphics.fisica.SistemaColisiones;

public class JuegoP1P2 {
    /** Arreglo de obstáculos del escenario. */
    private ParTuberias[] tubos;
    /** Velocidad actual del escenario. */
    private float velocidad = 1.2f;
    /** Distancia horizontal entre tubos. */
    private final float separacion = 1.1f;
    /** Límite donde los tubos se reciclan. */
    private final float limiteIzquierdo = -2.0f;
    // JUGADORES
    private Pajaro pajaro1;
    private Pajaro pajaro2;
    private ControladorPajaro ctrl1;    //Fisica y control del jugador1
    private ControladorPajaro ctrl2;    //Fisica y control del jugador2
    // PUNTAJES
    private int puntosP1 = 0;
    private int puntosP2 = 0;

    /** **Constructor del modo de juego**: Inicializa jugadores, controles y tubos. */
    public JuegoP1P2(Pajaro p1, Pajaro p2, ControladorPajaro c1, ControladorPajaro c2) {
        this.pajaro1 = p1;
        this.pajaro2 = p2;
        this.ctrl1 = c1;
        this.ctrl2 = c2;
        // CREACION DE TUBOS
        tubos = new ParTuberias[] {
            new ParTuberias(1.5f, 0.0f, 0.55f),
            new ParTuberias(1.5f + separacion, 0.2f, 0.55f),
            new ParTuberias(1.5f + separacion * 2, -0.2f, 0.55f)
        };
        // Asigna velocidad inicial
        for (ParTuberias t : tubos) {
            t.setVelocidad(velocidad);
        }
    }

    /** @return true si ambos jugadores murieron. */
    public boolean isGameOver() {
        return ctrl1.isMuerto() && ctrl2.isMuerto();
    }

    /** **Actualización del juego**: Actualiza física, tubos y colisiones. 
     * @param dt DeltaTime del frame. */
    public void actualizar(float dt) {
        // Actualiza jugadores
        ctrl1.actualizar(dt);
        ctrl2.actualizar(dt);
        // ACTUALIZA TUBOS
        for (ParTuberias t : tubos) {
            t.actualizar(dt);
            // Recicla tubos fuera de pantalla
            if (t.getX() < limiteIzquierdo) {
                recolocar(t);
                t.resetPuntajePaso();
            }
            // COLISIONES Y PUNTOS P1
            if (!ctrl1.isMuerto()) {
                // Detecta si pasó el tubo
                if (t.pajaroPaso(pajaro1, true)) {
                    puntosP1++;
                    aumentarVelocidad(0.05f);
                }
                // Detecta colisión
                if (SistemaColisiones.colisiona(pajaro1, t)) {
                    ctrl1.setMuerto(true);
                }
            }
            // COLISIONES Y PUNTOS P2
            if (!ctrl2.isMuerto()) {
                // Detecta si pasó el tubo
                if (t.pajaroPaso(pajaro2, false)) {
                    puntosP2++;
                    aumentarVelocidad(0.05f);
                }
                // Detecta colisión
                if (SistemaColisiones.colisiona(pajaro2, t)) {
                    ctrl2.setMuerto(true);
                }
            }
        }

        // COLISIÓN CON LÍMITES (Colision con suelo y techo)
        // Jugador 1
        if (!ctrl1.isMuerto() && (pajaro1.getY() < -0.6f || pajaro1.getY() > 1.2f)) {
            ctrl1.setMuerto(true);
        }
        // Jugador 2
        if (!ctrl2.isMuerto() && (pajaro2.getY() < -0.6f || pajaro2.getY() > 1.2f)) {
            ctrl2.setMuerto(true);
        }
    }

    /** **Reciclaje de tubos**: Reubica un tubo al final del escenario. */
    private void recolocar(ParTuberias tubo) {
        float xMasLejana = obtenerXMasLejana();
        tubo.setX(xMasLejana + separacion);
        tubo.randomizarHueco();
    }

    /** @return La X más lejana entre todos los tubos. */
    private float obtenerXMasLejana() {
        float max = tubos[0].getX();
        for (ParTuberias t : tubos) {
            if (t.getX() > max) max = t.getX();
        }
        return max;
    }

    /** **Renderizado del juego**: Dibuja tubos y jugadores. */
    public void renderizar(Shader shader) {
        // Dibuja tubos
        for (ParTuberias t : tubos) {
            ObjectRenderer.dibujar(t, shader);
        }
        // Dibuja jugadores
        RendererJerarquico.dibujar(pajaro1, shader);
        RendererJerarquico.dibujar(pajaro2, shader);
    }

    /** **Aumento de dificultad**: Incrementa velocidad de los tubos. */
    public void aumentarVelocidad(float incremento) {
        velocidad += incremento;
        // Actualiza velocidad en todos los tubos
        for (ParTuberias t : tubos) {
            t.setVelocidad(velocidad);
        }
    }

    // GETTERS
    public ParTuberias[] getTubos() {
        return tubos;
    }
    public int getPuntosP1() { 
        return puntosP1; 
    }
    public int getPuntosP2() { 
        return puntosP2; 
    }
}