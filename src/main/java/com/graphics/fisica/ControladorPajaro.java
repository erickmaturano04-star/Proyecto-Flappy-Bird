/* FISICA - ControladorPajaro
    Controla el movimiento, salto y animacion del pajaro */
package com.graphics.fisica;

import com.graphics.sistema.Input;
import com.graphics.objetos.jugador.Pajaro;

public class ControladorPajaro {
    private Pajaro pajaro;
    private float velocidadY;
    private float gravedad;
    private float fuerzaSalto;
    private int teclaSalto;
    private float tiempoAleteo = 0f;
    private boolean aleteando = false;
    private boolean muerto = false;

    /** **Constructor del controlador**: Configura el pájaro y su tecla de salto.
     * @param pajaro Objeto controlado.
     * @param teclaSalto Tecla asignada para saltar. */
    public ControladorPajaro(Pajaro pajaro, int teclaSalto) {
        this.pajaro = pajaro;
        this.teclaSalto = teclaSalto;
        velocidadY = 0.0f;
        gravedad = 0.0012f;     //Fuerza de caida
        fuerzaSalto = 0.022f;   //Fuerza de salto
    }

    /** Cambia el estado de vida del pájaro. 
     * @param m true si murió. */
    public void setMuerto(boolean m) {
        this.muerto = m;
        if (m && velocidadY > 0) {
            velocidadY = 0;     //Pierde el impulso de subida al chocar
        }
    }

    /** @return true si el pájaro murió. */
    public boolean isMuerto() {
        return muerto;
    }

    /** **Actualización física**: Procesa input, gravedad, movimiento y alas.
     * @param deltaTime Tiempo entre frames. */
    public void actualizar(float deltaTime) {
        boolean saltoPresionado = false;
        // Verifica si el pájaro sigue vivo
        if (!muerto) {
            saltoPresionado = manejarInput();   //Realiza el salto
        } else {
            // Cuando está muerto, se desplaza a la izquierda con el escenario
            pajaro.setX(pajaro.getX() - 1.2f * deltaTime);
        }
        aplicarGravedad();
        mover();
        actualizarRotacion();
        if (!muerto) {
            actualizarAlas(deltaTime, saltoPresionado);
        }
    }

    /** **Lectura de teclado**: Detecta si el jugador presionó salto.
     * @return true si hubo salto. */
    private boolean manejarInput() {
        // Detecta pulsación única
        if (Input.fuePresionada(teclaSalto)) {
            saltar();
            return true;
        }
        return false;
    }

    /** **Salto**: Aplica impulso vertical al pájaro. */
    private void saltar() {
        velocidadY = fuerzaSalto;
    }

    /** **Gravedad**: Reduce la velocidad vertical. */
    private void aplicarGravedad() {
        velocidadY -= gravedad;
    }

    /** **Movimiento vertical**: Actualiza la posición Y del pájaro. */
    private void mover() {
        pajaro.setY(pajaro.getY() + velocidadY);
    }

    /** **Rotación visual**: Inclina el pájaro según el movimiento. */
    private void actualizarRotacion() {
        float objetivo;
        if (velocidadY > 0.005f) {
            // SUBIENDO
            objetivo = -18.0f;
        } else if (velocidadY < -0.01f) {
            // CAYENDO
            objetivo = 25.0f;
        } else {
            // NORMAL
            objetivo = 0.0f;
        }
        if (muerto) {
            objetivo = 45.0f;   //Si está muerto cae empicada
        }

        // ROTACION ACTUAL
        float actual = pajaro.getRotacion();
        // SUAVIZA LA TRANSICION
        actual += (objetivo - actual) * 0.08f;
        pajaro.setRotacion(actual);     //Aplica la nueva rotación
    }
    
    /** **Animación de alas**: Simula el aleteo usando seno.
     * @param deltaTime Tiempo entre frames.
     * @param saltoPresionado true si hubo salto. */
    private void actualizarAlas(float deltaTime, boolean saltoPresionado) {
        // Inicia el aleteo al saltar
        if (saltoPresionado) {
            aleteando = true;
            tiempoAleteo = 0f;
        }
        // Sale si no está aleteando
        if (!aleteando)     return;
        // Avanza el tiempo de animación
        tiempoAleteo += deltaTime * 10f;  
        // Movimiento senoidal (oscilacion periodica)  
        float fuerza = (float)Math.sin(tiempoAleteo);
        // Mueve ambas alas
        pajaro.getAla1().aletear(fuerza);
        pajaro.getAla2().aletear(fuerza);
        // Finaliza la animación
        if (tiempoAleteo > Math.PI) {
            aleteando = false;
            pajaro.getAla1().aletear(0);
            pajaro.getAla2().aletear(0);
        }
    }
}