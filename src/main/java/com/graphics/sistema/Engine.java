/* SISTEMA - Engine
    El bucle principal o el motor que une todo, que controla el inicio,
    el bucle y el cierre (Game Loop) */
package com.graphics.sistema;

import com.graphics.juego.AudioManagerGlobal;

public class Engine {
    /** **Ventana principal**: Gestiona la pantalla y el contexto de OpenGL. */
    private Window ventana;
    /** **Procesador gráfico**: Encargado de enviar las órdenes de dibujo a la GPU. */
    private Renderer renderer;
    /** **Cámara del mundo**: Define el punto de vista del jugador en la escena. */
    private Camera camera;
    /** **Estado del motor**: Indica si el Game Loop debe continuar ejecutándose. */
    private boolean corriendo;

    /** **Interfaz de Lógica**: Permite inyectar comportamiento personalizado
     * (objetos, colisiones, etc.) sin modificar el código del motor. */
    public interface ILogica {
        void iniciar();
        void actualizar(float dt);
        void renderizar(Renderer renderer, Camera camera);
    }

    /** **Referencia de Lógica**: Almacena el código del juego (objetos, reglas, etc.) */
    private ILogica logica;

    /** Configura los componentes base del motor (Pantalla Completa).
     * @param titulo Nombre de la ventana.
     * @param logica Implementación con la lógica del juego. */
    public Engine(String titulo, ILogica logica) {
        int anchoAuto = MonitorUtils.getAnchoMonitor();
        int altoAuto = MonitorUtils.getAltoMonitor();
        this.ventana = new Window(titulo, anchoAuto, altoAuto);
        this.renderer = new Renderer();
        this.camera = new Camera();
        this.logica = logica;
    } 
       
    /** Configura los componentes base del motor (Pantalla Personalizada)
     * @param titulo Nombre de la ventana.
     * @param ancho Pixeles de ancho.
     * @param alto Pixeles de alto.
     * @param logica Implementación con la lógica del juego. */
    public Engine(String titulo, int ancho, int alto, ILogica logica) {
        this.ventana = new Window(titulo, ancho, alto);
        this.renderer = new Renderer();
        this.camera = new Camera();
        this.logica = logica;
    }

    /** **Punto de entrada**: Inicializa hardware y arranca el bucle. */
    public void iniciar() {
        ventana.inicializar();
        Time.inicializar();
        if (logica != null) { 
            logica.iniciar();   // Inicia tus objetos
        }
        corriendo = true;
        loop();
    }

    /** **Game Loop**: El ciclo que corre a cada frame.
     * Procesa: Tiempo -> Limpieza -> Lógica -> Dibujo -> Ventana. */
    private void loop() {
        while (corriendo && !ventana.debeCerrar()) {
            // Calcula Delta Time (tiempo entre frames)
            float dt = Time.getDeltaTime();    
            // Limpiar el frame anterior    
            ventana.limpiar();
            if (logica != null) {
                logica.actualizar(dt);                  // Mueve tus objetos
                logica.renderizar(renderer, camera);    // Dibuja tus objetos
            }
            // Muestra el resultado en pantalla
            ventana.actualizar();
        }
        detener();
    }

    /** **Finalización**: Libera los recursos de la ventana al cerrar. */
    private void detener() {
        ventana.destruir();
        AudioManagerGlobal.detenerTodo();
    }
}