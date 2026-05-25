/* FISICA - SistemaColisiones
    Detecta colisiones entre el pajaro y las tuberias */
package com.graphics.fisica;

import com.graphics.objetos.jugador.Pajaro;
import com.graphics.objetos.obstaculos.ParTuberias;

public class SistemaColisiones {
    /** **Detección de colisión**: Verifica si el pájaro toca alguna tubería.
     * @param pajaro Jugador a comprobar.
     * @param tubos Par de tuberías del escenario.
     * @return true si existe colisión. */
    public static boolean colisiona(Pajaro pajaro, ParTuberias tubos) {
        // Obtiene las áreas de colisión del pájaro
        CirculoColision cabeza = pajaro.getCabezaColision();
        CirculoColision cuerpo = pajaro.getCuerpoColision();
        // Revisa Tuberia Superior (Tubo Arriba) - Verifica colision con cuerpo y borde superior
        boolean colisionSup = cabeza.colisiona(tubos.getSuperior().getCajaCuerpo()) ||
                              cabeza.colisiona(tubos.getSuperior().getCajaBorde()) ||
                              cuerpo.colisiona(tubos.getSuperior().getCajaCuerpo()) ||
                              cuerpo.colisiona(tubos.getSuperior().getCajaBorde());         
        // Revisa Tuberia Superior (Tubo Abajo) - Verifica colision con cuerpo y borde inferior
        boolean colisionInf = cabeza.colisiona(tubos.getInferior().getCajaCuerpo()) ||
                              cabeza.colisiona(tubos.getInferior().getCajaBorde()) ||
                              cuerpo.colisiona(tubos.getInferior().getCajaCuerpo()) ||
                              cuerpo.colisiona(tubos.getInferior().getCajaBorde());
        // Retorna true si hubo choque con alguna tubería
        return colisionSup || colisionInf;
    }
}