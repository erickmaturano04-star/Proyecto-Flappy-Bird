/* UI - MenuPrincipal
    Controla el menu principal y sus opciones */
package com.graphics.juego.ui;

import com.graphics.graficos_y_recursos.ShaderTexture;
import com.graphics.graficos_y_recursos.Sprite;
import com.graphics.juego.input.Teclas;
import com.graphics.sistema.Input;
import com.graphics.sistema.ObjectRendererTexture;

public class MenuPrincipal {
    private Sprite titulo;
    private Sprite botonP1;
    private Sprite botonP1P2;
    private Sprite botonSalir;
    private EstadoMenu opcionSeleccionada;  //Opcion actualmente seleccionada
    private boolean salir;                  //Estado para cerrar el juego
    private boolean iniciarP1;              //Estado para iniciar modo P1
    private boolean iniciarP1P2;            //Estado para iniciar modo P1vsP2
    // POSICIONES
    private static final float X_BOTON = -0.20f;
    private static final float Y_BOTON_P1 = 0.10f;
    private static final float Y_BOTON_P1P2 = -0.18f;
    private static final float Y_BOTON_SALIR = -0.46f;
    private static final float ANCHO_BOTON = 0.40f;
    private static final float ALTO_BOTON = 0.20f;

    /** **Constructor del menú**: Crea título, botones y selección inicial. */
    public MenuPrincipal() {
        // TITULO
        titulo =
            new Sprite(
                "/textures/titulo.png",
                -0.65f,
                0.85f,
                1.3f,
                0.45f,
                0f,
                0f,
                1f,
                1f
            );
        crearBotones();
        opcionSeleccionada = EstadoMenu.P1;     //Seleccion inicial
        actualizarBotones();
    }

    /** **Creación de botones**: Inicializa todos los sprites del menú. */
    private void crearBotones() {
        botonP1 = crearBoton(X_BOTON, Y_BOTON_P1, 0);
        botonP1P2 = crearBoton(X_BOTON, Y_BOTON_P1P2, 1);
        botonSalir = crearBoton(X_BOTON, Y_BOTON_SALIR, 2);
    }

    /** **Creación de sprite botón**: Genera un botón usando coordenadas UV. */
    private Sprite crearBoton(float x, float y, int indice) {
        float anchoUV = 160f / 960f;
        float u1 = indice * 2 * anchoUV;
        float u2 = u1 + anchoUV;
        return new Sprite(
            "/textures/menuBoton.png",
            x,
            y,
            ANCHO_BOTON,
            ALTO_BOTON,
            u1,
            0.0f,
            u2,
            1.0f);
    }

    /** **Cambio de selección**: Cambia la opción activa del menú. */
    public void seleccionar(EstadoMenu opcion) {
        opcionSeleccionada = opcion;
        actualizarBotones();
    }

    /** **Actualización visual**: Cambia la apariencia de los botones. */
    private void actualizarBotones() {
        actualizarBoton(botonP1, 0, opcionSeleccionada == EstadoMenu.P1);
        actualizarBoton(botonP1P2, 1, opcionSeleccionada == EstadoMenu.P1_VS_P2);
        actualizarBoton(botonSalir, 2, opcionSeleccionada == EstadoMenu.SALIR);
    }

    /** **Cambio de UV del botón**: Muestra si el botón está seleccionado o no. */
    private void actualizarBoton(Sprite boton, int indice, boolean seleccionado) {
        float anchoUV = 160f / 960f;
        int frame = indice * 2;
        // Usa frame distinto si no está seleccionado
        if (!seleccionado) {
            frame += 1;
        }
        float u1 = frame * anchoUV;
        float u2 = u1 + anchoUV;
        boton.cambiarUV(u1, 0f, u2, 1f);
    }

    /** **Actualización del menú**: Procesa entradas del jugador. */
    public void actualizar(float dt) {
        manejarInput();
    }

    /** **Lectura de teclado**: Detecta navegación y selección. */
    private void manejarInput() {
        // ARRIBA
        if (Input.fuePresionada(Teclas.CURSOR_ARRIBA)) {
            moverArriba();
        }
        // ABAJO
        if (Input.fuePresionada(Teclas.CURSOR_ABAJO)) {
            moverAbajo();
        }
        // ENTER
        if (Input.fuePresionada(Teclas.ENTER)) {
            ejecutarOpcion();
        }
    }

    /** **Mover selección arriba**. */
    private void moverArriba() {
        switch (opcionSeleccionada) {
            case P1:
                break;
            case P1_VS_P2:
                opcionSeleccionada =
                    EstadoMenu.P1;
                break;
            case SALIR:
                opcionSeleccionada =
                    EstadoMenu.P1_VS_P2;
                break;
        }
        actualizarBotones();
    }

    /** **Mover selección abajo**. */
    private void moverAbajo() {
        switch (opcionSeleccionada) {
            case P1:
                opcionSeleccionada =
                    EstadoMenu.P1_VS_P2;
                break;
            case P1_VS_P2:
                opcionSeleccionada =
                    EstadoMenu.SALIR;
                break;
            case SALIR:
                break;
        }
        actualizarBotones();
    }

    /** **Ejecutar opción**: Activa la acción seleccionada. */
    private void ejecutarOpcion() {
        switch (opcionSeleccionada) {
            case P1:
                iniciarP1 = true;
                break;
            case P1_VS_P2:
                iniciarP1P2 = true;
                break;
            case SALIR:
                salir = true;
                break;
        }
    }

    /** **Renderizado del menú**: Dibuja título y botones. */
    public void renderizar(ShaderTexture shaderTexture) {
        ObjectRendererTexture.dibujar(titulo, shaderTexture);
        ObjectRendererTexture.dibujar(botonP1, shaderTexture);
        ObjectRendererTexture.dibujar(botonP1P2, shaderTexture);
        ObjectRendererTexture.dibujar(botonSalir,shaderTexture);
    }

    // GETTERS
    public Sprite getTitulo() {
        return titulo;
    }
    public Sprite getBotonP1() {
        return botonP1;
    }
    public Sprite getBotonP1P2() {
        return botonP1P2;
    }
    public Sprite getBotonSalir() {
        return botonSalir;
    }
    public boolean debeSalir() {
        return salir;
    }
    public boolean debeIniciarP1() {
        // Retorna 'true' una sola vez cuando se inicia P1
        if (iniciarP1) {
            iniciarP1 = false;
            return true;
        }
        return false;
    }
    public boolean debeIniciarP1P2() {
        // Retorna 'true' una sola vez cuando se inicia P1vsP2
        if (iniciarP1P2) {
            iniciarP1P2 = false;
            return true;
        }
        return false;
    }
}