/* UI - RenderizarMenu
    Controla el menú de reintentar cuando ambos jugadores pierden */
package com.graphics.juego.ui;

import com.graphics.graficos_y_recursos.ShaderTexture;
import com.graphics.graficos_y_recursos.Sprite;
import com.graphics.sistema.Input;
import com.graphics.sistema.ObjectRendererTexture;
import com.graphics.juego.input.Teclas;

public class ReintentarMenu {
    /** Coordenadas y tamaños en unidades de juego (asumiendo que 1 u = 1 m). */
    private static final float SEPARACION_BOTONES = 0.04f;
    private static final float Y_TITULO = 0.18f;
    private static final float Y_BOTON_NO = -0.02f;
    private static final float Y_BOTON_SI = -0.02f;
    private static final float ANCHO_BOTON = 0.12f; 
    private static final float ALTO_BOTON  = 0.12f;

    /** Dimensiones reales de la textura (px). */
    private static final float TEX_W = 73f;   // ancho total de la imagen
    //private static final float TEX_H = 9f;    // alto total (todos los frames comparten)

    /** Índices de los frames dentro de la textura PNG. */
    private static final int FRAME_NO   = 0; // NO no seleccionado
    private static final int FRAME_SI   = 1; // SI no seleccionado
    private static final int FRAME_NO_A = 2; // NO seleccionado (activo)
    private static final int FRAME_SI_A = 3; // SI seleccionado (activo)

    /** Estado actual del menú. */
    private enum Opcion { NO, SI }                  // Opciones disponibles del menu
    private Opcion opcionSeleccionada = Opcion.NO;  // Opcion actualmente seleccionada

    /** Sprites utilizados. */
    private final Sprite titulo;
    private final Sprite botonNo;
    private final Sprite botonSi;

    /** Señales externas para la aplicación. */
    private boolean volverAlMenu = false;
    private boolean reiniciarJuego = false;

    /** Construye los sprites a partir de la única textura PNG. */
    public ReintentarMenu() {
        // La textura tiene 4 frames horizontalmente (cada uno 9px) + título (37px)
        String tex = "/textures/reintentarBoton.png";
        // TITULO (último fragmento, 37 px de ancho)
        float u1Titulo = 36f / TEX_W;
        float u2Titulo = 73f / TEX_W;
        float anchoTitulo = 0.50f;
        float altoTitulo = 0.12f;
        titulo = new Sprite(
                tex,
                -anchoTitulo / 2f, Y_TITULO,
                anchoTitulo, altoTitulo,   // ancho total ≈ 73 px, alto 9 px
                u1Titulo, 0f,
                u2Titulo, 1f);
        float centroX = 0f;
        // BOTON NO (izquierda)
        botonNo = crearBoton(
            FRAME_NO,
            centroX - ANCHO_BOTON - SEPARACION_BOTONES,
            Y_BOTON_NO
        );
        // BOTON SI (derecha)
        botonSi = crearBoton(
            FRAME_SI,
            centroX + SEPARACION_BOTONES,
            Y_BOTON_SI
        );
    }

    /** Crea un sprite a partir del índice de frame dentro de la textura. */
    private Sprite crearBoton(int frame, float x, float y) {
        float anchoUV = 9f / TEX_W;
        float u1 = frame * anchoUV;
        float u2 = u1 + anchoUV;
        return new Sprite(
                "/textures/reintentarBoton.png",
                x, y,
                ANCHO_BOTON, ALTO_BOTON,
                u1, 0f,
                u2, 1f);
    }

    /** Actualiza la lógica de entrada del menú. */
    public void actualizar(float dt) {
        // Cambio de selección con las flechas
        if (Input.fuePresionada(Teclas.CURSOR_IZQUIERDA) ||
            Input.fuePresionada(Teclas.CURSOR_ARRIBA)) {
            opcionSeleccionada = Opcion.NO;
        }
        if (Input.fuePresionada(Teclas.CURSOR_DERECHA) ||
            Input.fuePresionada(Teclas.CURSOR_ABAJO)) {
            opcionSeleccionada = Opcion.SI;
        }
        // Cambiar la textura del botón activo
        actualizarSpriteActivo();
        // Confirmar opcion con ENTER
        if (Input.fuePresionada(Teclas.ENTER)) {
            if (opcionSeleccionada == Opcion.NO) {
                volverAlMenu = true;
            } else {
                reiniciarJuego = true;
            }
        }
    }

    /** Cambia los UV del sprite (cambia el frame del botón seleccionado). */
    private void actualizarSpriteActivo() {
        // NO
        if (opcionSeleccionada == Opcion.NO) {
            setUV(botonNo, FRAME_NO_A);
            setUV(botonSi, FRAME_SI);
        } else {
            setUV(botonNo, FRAME_NO);
            setUV(botonSi, FRAME_SI_A);
        }
    }

    /** Actualiza las coordenadas UV del sprite. */
    private void setUV(Sprite s, int frame) {
        float anchoUV = 9f / TEX_W;
        float u1 = frame * anchoUV;
        float u2 = u1 + anchoUV;
        s.cambiarUV(u1, 0f, u2, 1f);
    }

    /** Renderiza el menú usando el shader de textura suministrado. */
    public void renderizar(ShaderTexture shaderTexture) {
        ObjectRendererTexture.dibujar(titulo, shaderTexture);
        ObjectRendererTexture.dibujar(botonNo, shaderTexture);
        ObjectRendererTexture.dibujar(botonSi, shaderTexture);
    }

    /** Señales que ha pedido la aplicación. */
    public boolean debeVolverAlMenu() { 
        return volverAlMenu; 
    }
    public boolean debeReiniciarJuego() { 
        return reiniciarJuego; 
    }

    /** Resetea las señales después de que la aplicación las haya procesado. */
    public void reset() {
        volverAlMenu = false;
        reiniciarJuego = false;
    }
}