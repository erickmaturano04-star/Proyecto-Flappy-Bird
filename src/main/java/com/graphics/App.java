/* MAIN - Programa Principal 
    Punto de entrada que inicia el juego y controla su flujo */
//Ejecutar con: mvn compile exec:exec "-DmainClass=com.graphics.App"
package com.graphics;

import com.graphics.fisica.ControladorPajaro;
import com.graphics.graficos_y_recursos.*;
import com.graphics.juego.MusicPlayer;
import com.graphics.juego.input.Teclas;
import com.graphics.juego.modos.JuegoP1P2;
import com.graphics.juego.ui.MenuPrincipal;
import com.graphics.juego.ui.ReintentarMenu;
import com.graphics.juego.ui.Puntuacion;
import com.graphics.juego.GameManager;
import com.graphics.objetos.escenas.Fondo;
import com.graphics.objetos.jugador.Pajaro;
import com.graphics.sistema.*;

public class App {

    public static void main(String[] args) {
        /** **Lógica principal**: Define inicio, actualización y renderizado. */
        Engine.ILogica miJuego = new Engine.ILogica() {
            // SHADERS
            private Shader shaderBase;
            private ShaderTexture shaderTexture;
            // ESCENAS Y UI
            private Fondo fondo;
            private JuegoP1P2 juego;
            private MenuPrincipal menu;
            private ReintentarMenu reintentarMenu;
            private Puntuacion puntuacion;
            // CONTROL GENERAL
            private GameManager gameManager;
            // AUDIO
            private MusicPlayer musicaMenu;
            private MusicPlayer musicaJuego;
            // JUGADORES
            private Pajaro pajaro1;
            private Pajaro pajaro2;
            // CONTROLADORES
            private ControladorPajaro ctrl1;
            private ControladorPajaro ctrl2;
            // CONTROL MENU REINTENTO
            private boolean menuReintentoActivo;

            /** **Inicialización**: Carga recursos y prepara el juego. */
            @Override
            public void iniciar() {
                shaderBase = ShaderLibrary.getBaseShader();
                shaderTexture = new ShaderTexture();

                RenderConfig.inicializar();

                fondo = new Fondo();
                menu = new MenuPrincipal();
                reintentarMenu = new ReintentarMenu();
                puntuacion = new Puntuacion();

                // Inicia mostrando el menu
                gameManager = new GameManager();

                // Musica
                musicaJuego = new MusicPlayer();
                musicaMenu = new MusicPlayer();
                musicaMenu.reproducirLoop("/audio/menu.mp3");

                menuReintentoActivo = false;
            }

            /** **Nueva partida**: Reinicia todos los objetos del juego. */
            private void iniciarJuegoP1P2() {
                musicaMenu.detener();
                musicaJuego.reproducirLoop("/audio/game.mp3");
                menuReintentoActivo = false;
                // Instanciar o reiniciar los pájaros y controles
                pajaro1 = new Pajaro(-0.9f, 0.25f, 0.125f, Color.ROJO);
                pajaro2 = new Pajaro(-0.7f, 0.25f, 0.125f, Color.AZUL);

                ctrl1 = new ControladorPajaro(pajaro1, Teclas.P1_SALTAR);
                ctrl2 = new ControladorPajaro(pajaro2, Teclas.P2_SALTAR);

                juego = new JuegoP1P2(pajaro1, pajaro2, ctrl1, ctrl2);
            }

            /** **Actualización**: Ejecuta la lógica de cada frame. */
            @Override
            public void actualizar(float dt) {
                // El fondo siempre se actualiza y se mueve, sin importar el estado
                fondo.actualizar(dt);

                if (gameManager.estaEnMenu()) {
                    // MENU PRINCIPAL
                    menu.actualizar(dt);
                    if (menu.debeIniciarP1P2()) {
                        // Inicia modo P1vsP2
                        iniciarJuegoP1P2();
                        gameManager.iniciarJuego();
                    } else if (menu.debeIniciarP1()) {
                        // Inicia modo P1 (aun no implementado)
                        System.out.println("Modo P1 no implementado aún.");
                    } else if (menu.debeSalir()) {
                        // Salir del juego
                        System.exit(0);
                    }
                } else if (gameManager.estaEnJuego()) {
                    // PARTIDA
                    float fijo = 0.015f;
                    juego.actualizar(fijo);
                    // Actualizar puntuacion de jugadores
                    puntuacion.setPuntos(juego.getPuntosP1(), juego.getPuntosP2());
                    // Activar menú de reintento
                    if (juego.isGameOver() && !menuReintentoActivo) {
                        // Evita ENTER acumulados
                        Input.limpiarTeclasPresionadas();
                        // Reinicia FLAGS del menu
                        reintentarMenu.reset();
                        // Evita REPETIR LA LIMPIEZA en cada frame
                        menuReintentoActivo = true;
                    }
                }

                // MENU REINTENTAR
                if (gameManager.estaEnJuego() && juego != null && juego.isGameOver()) {
                    reintentarMenu.actualizar(dt);
                    // ----- Volver al menú principal (NO) ---------------------------------
                    if (reintentarMenu.debeVolverAlMenu()) {
                        musicaJuego.detener();
                        musicaMenu.reproducirLoop("/audio/menu.mp3");
                        gameManager.volverMenu();       // vuelve al menú principal
                        reintentarMenu.reset();         // limpia flags
                        menuReintentoActivo = false;
                    }
                    // ----- Reiniciar partida (SI) -----------------------------------------
                    else if (reintentarMenu.debeReiniciarJuego()) {
                        iniciarJuegoP1P2();         // crea una nueva partida
                        gameManager.iniciarJuego(); // cambia estado a juego
                        reintentarMenu.reset();     // limpia flags
                        menuReintentoActivo = false;
                    }
                }
            }

            /** **Renderizado**: Dibuja todos los objetos en pantalla. */
            @Override
            public void renderizar(Renderer renderer, Camera camera) {
                // FONDO
                shaderBase.usar();
                shaderBase.setUniform("uView", camera.getViewMatrix());
                ObjectRenderer.dibujar(fondo, shaderBase);
                shaderBase.detener();

                if (gameManager.estaEnMenu()) {
                    // MENU
                    menu.renderizar(shaderTexture);
                } else if (gameManager.estaEnJuego()) {
                    // JUEGO
                    shaderBase.usar();
                    shaderBase.setUniform("uView", camera.getViewMatrix());
                    juego.renderizar(shaderBase);
                    shaderBase.detener();
                    // UI de puntos
                    puntuacion.renderizar(shaderTexture);
                    // MENU REINTENTAR
                    if (juego != null && juego.isGameOver()) {
                        reintentarMenu.renderizar(shaderTexture);
                    }
                }
            }
        };

        /** **Inicio del Engine**: Ejecuta el ciclo principal del juego. */
        new Engine("New Flappy Bird - UAGRM", miJuego).iniciar();
    }
}