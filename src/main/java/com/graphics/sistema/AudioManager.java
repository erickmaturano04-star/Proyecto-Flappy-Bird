/* SISTEMA - AudioManager
    Inicializa y cierra OpenAL para permitir el uso de audio */
package com.graphics.sistema;

import org.lwjgl.openal.AL;
import org.lwjgl.openal.ALC;

import java.nio.IntBuffer;

import static org.lwjgl.openal.ALC10.*;

public class AudioManager {
    /** Identificador del dispositivo de audio del sistema. */
    private static long device;
    /** Contexto principal donde OpenAL procesa el audio. */
    private static long context;

    /** **Inicialización de audio**: 
     * Abre el dispositivo de sonido y activa OpenAL */
    public static void init() {
        // Obtiene el dispositivo de audio por defecto
        String defaultDeviceName = 
            alcGetString(0, ALC_DEFAULT_DEVICE_SPECIFIER);
        // Abre el dispositivo de sonido
        device = alcOpenDevice(defaultDeviceName);
        // Verifica si OpenAL pudo abrirse correctamente
        if (device == 0) {
            throw new IllegalStateException(
                "No se pudo abrir OpenAL"
            );
        }
        // Crea el contexto principal de audio
        context = alcCreateContext(device, (IntBuffer) null);
        // Activa el contexto actual
        alcMakeContextCurrent(context);
        // Habilita las funciones de OpenAL
        AL.createCapabilities(
            ALC.createCapabilities(device)
        );
        System.out.println("OpenAL iniciado");
    }

    /** **Liberación de audio**:
     * Destruye el contexto y cierra OpenAL */
    public static void cleanup() {
        alcDestroyContext(context);
        alcCloseDevice(device);
        System.out.println("OpenAL cerrado");
    }
}