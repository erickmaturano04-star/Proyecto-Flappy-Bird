/* AUDIO - Sound
    Reproduce archivos de audio MP3 usando JLayer */
package com.graphics.audio;

import javazoom.jl.player.Player;

import java.io.BufferedInputStream;
import java.io.InputStream;

public class Sound {
    /** Ruta del archivo de audio dentro de resources. */
    private String ruta;

    /** **Constructor de sonido**: Guarda la ubicación del archivo MP3.
     * @param ruta Ruta del audio. */
    public Sound(String ruta) {
        this.ruta = ruta;
    }

    /** **Reproducción de audio**: Carga y reproduce el sonido en un hilo separado. */
    public void play() {
        // Ejecuta el sonido en segundo plano
        new Thread(() -> {
            try {
                // Carga el archivo desde resources
                InputStream inputStream = getClass().getResourceAsStream(ruta);
                // Verifica si el archivo existe
                if (inputStream == null) {
                    System.out.println("No se encontró el audio: " + ruta);
                    return;
                }
                // Mejora la lectura del archivo
                BufferedInputStream buffer = new BufferedInputStream(inputStream);
                // Crea el reproductor MP3
                Player player = new Player(buffer);
                // Reproduce el sonido
                player.play();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();
    }
}