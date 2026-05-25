/* GRAFICOS_Y_RECURSOS - Texture
    Carga imagenes y las convierte en texturas de OpenGL */
package com.graphics.graficos_y_recursos;

import org.lwjgl.BufferUtils;

import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.nio.IntBuffer;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.stb.STBImage.*;

public class Texture {
    /** ID de la textura en OpenGL. */
    private int id;

    /** **Constructor de textura**: Carga una imagen y la sube a la GPU.
     * @param ruta Ruta del archivo de imagen. */
    public Texture(String ruta) {
        // Buffers donde STB guardará datos de la imagen
        IntBuffer width = BufferUtils.createIntBuffer(1);
        IntBuffer height = BufferUtils.createIntBuffer(1);
        IntBuffer channels = BufferUtils.createIntBuffer(1);
        ByteBuffer image;

        // CARGA EL ARCHIVO
        try (InputStream stream =
            getClass().getResourceAsStream(ruta)) {
            if (stream == null) {
                throw new RuntimeException(
                    "No se encontro: " + ruta
                );
            }
            // Lee todos los bytes del archivo
            byte[] bytes = stream.readAllBytes();
            // Convierte bytes a ByteBuffer
            ByteBuffer buffer = BufferUtils.createByteBuffer(bytes.length);
            buffer.put(bytes);
            buffer.flip();
            // Decodifica la imagen
            image =
                stbi_load_from_memory(
                    buffer,
                    width,
                    height,
                    channels,
                    4
                );
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        // Verifica errores de carga
        if (image == null) {
            throw new RuntimeException(
                "Error cargando textura: "
                    + stbi_failure_reason()
            );
        }

        // CREACION DE TEXTURA
        id = glGenTextures();               // Genera ID de textura
        glBindTexture(GL_TEXTURE_2D, id);   // Activa la textura

        // CONFIGURACION DE FILTROS
        // Escalado pequeño
        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER, GL_NEAREST);
        // Escalado grande
        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_NEAREST);
        
        // ENVIO A LA GPU
        glTexImage2D(
            GL_TEXTURE_2D,
            0,
            GL_RGBA,
            width.get(),
            height.get(),
            0,
            GL_RGBA,
            GL_UNSIGNED_BYTE,
            image
        );
        // Libera memoria temporal de la imagen
        stbi_image_free(image);
        // Desactiva la textura
        glBindTexture(GL_TEXTURE_2D, 0);
        //System.out.println("Textura cargada correctamente");
    }

    /** **Activación de textura**: Usa esta textura para renderizar. */
    public void bind() {
        glBindTexture(GL_TEXTURE_2D, id);
    }
}