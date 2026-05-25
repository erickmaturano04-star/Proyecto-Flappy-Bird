/* JUEGO - AudioManagerGlobal
    Administra todas las instancias de audio activas del juego */
package com.graphics.juego;

import java.util.ArrayList;
import java.util.List;

public class AudioManagerGlobal {
    /** **Registro global**: Guarda todas las músicas activas. */
    private static final List<MusicPlayer> instancias = new ArrayList<>();

    /** **Agregar audio**: Registra un reproductor en la lista global.
     * @param a Instancia de música o sonido a controlar. */
    public static void registrar(MusicPlayer a) {
        instancias.add(a);
    }
    
    /** **Detener audios**: Pausa y limpia todas las músicas activas. */
    public static void detenerTodo() {
        for (MusicPlayer a : instancias) {
            a.detener();
        }
        instancias.clear();
    }
}