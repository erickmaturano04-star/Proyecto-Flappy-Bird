/* JUEGO - MusicPlayer
    Reproduce musica en segundo plano usando hilos */
package com.graphics.juego;

import javazoom.jl.player.Player;

import java.io.BufferedInputStream;
import java.io.InputStream;

public class MusicPlayer {
    /** **Hilo de reproducción**: Ejecuta la música en paralelo al juego. */
    private Thread hilo;
    /** Reproductor encargado de leer el archivo MP3. */
    private Player player;
    /** Controla si la música debe seguir reproduciéndose. */
    private volatile boolean corriendo = false;

    /** Registra automáticamente esta instancia en el administrador global. */
    public MusicPlayer() {
        AudioManagerGlobal.registrar(this);
    }

    /** **Reproducción continua**: Ejecuta un audio en bucle.
     * @param ruta Ruta del archivo de sonido dentro de resources. */
    public void reproducirLoop(String ruta) {
        detener();
        corriendo = true;
        hilo = new Thread(() -> {
            while (corriendo) {
                try {
                    InputStream input =
                        getClass().getResourceAsStream(ruta);
                    if (input == null) {
                        System.out.println("Audio no encontrado: " + ruta);
                        return;
                    }
                    BufferedInputStream buffer =
                        new BufferedInputStream(input);
                    player = new Player(buffer);
                    player.play();
                } catch (Exception e) {
                    if (corriendo) {
                        e.printStackTrace();
                    }
                }
            }
        });
        hilo.setDaemon(true);
        hilo.setName("MusicLoopThread");
        hilo.start();
    }
    
    /** **Detener música**: Finaliza el audio y libera el hilo. */
    public synchronized void detener() {
        corriendo = false;
        if (player != null) {
            player.close();
            player = null;
        }
        if (hilo != null) {
            try {
                hilo.join(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            hilo = null;
        }
    }
}