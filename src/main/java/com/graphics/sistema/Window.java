/* SISTEMA - Window
    Crea la ventana y el contexto de OpenGL */
package com.graphics.sistema;

import org.lwjgl.glfw.GLFW;
import org.lwjgl.glfw.GLFWErrorCallback;
import org.lwjgl.opengl.GL;
import org.lwjgl.opengl.GL11;
import org.lwjgl.system.MemoryUtil;

public class Window {
    /** Identificador único de la ventana en memoria (puntero). */
    private long windowHandle;
    /** **Dimensiones**: El ancho y alto de la ventana en píxeles. */
    private int ancho, alto;
    /** **Nombre de la aplicación**: El texto que aparece en la barra superior de la ventana. */
    private String titulo;

    /** **Constructor de Ventana**: Prepara los datos básicos de la interfaz.
     * Define cómo se verá y qué tamaño tendrá antes de llamar a {@link #inicializar()}.
     * @param titulo Texto que se mostrará en la barra superior.
     * @param ancho  Ancho de la pantalla en píxeles.
     * @param alto   Alto de la pantalla en píxeles. */
    public Window(String titulo, int ancho, int alto) {
        this.titulo = titulo;
        this.ancho = ancho;
        this.alto = alto;
    }

    /** **Configuración inicial**: Inicializa GLFW, crea la ventana y activa OpenGL. */
    public void inicializar() {
        // Redirige errores de GLFW a la consola
        GLFWErrorCallback.createPrint(System.err).set();
        // Inicializar la librería GLFW
        if (!GLFW.glfwInit()) {
            throw new IllegalStateException("No se pudo inicializar GLFW");
        }
        GLFW.glfwDefaultWindowHints();
        GLFW.glfwWindowHint(GLFW.GLFW_VISIBLE, GLFW.GLFW_FALSE);
        GLFW.glfwWindowHint(GLFW.GLFW_RESIZABLE, GLFW.GLFW_TRUE);
        // Creación del objeto ventana
        windowHandle = 
            GLFW.glfwCreateWindow(ancho, alto, titulo, MemoryUtil.NULL, 
                                MemoryUtil.NULL);
        if (windowHandle == MemoryUtil.NULL) {
            throw new RuntimeException("Error al crear la ventana");
        }
        // Configurar Input (Callbacks se vinculan aquí)
        GLFW.glfwSetKeyCallback(windowHandle, Input::keyboardCallback);
        // Activar el contexto de dibujo en esta ventana
        GLFW.glfwMakeContextCurrent(windowHandle);
        GLFW.glfwSwapInterval(1);   // VSync, sincroniza los Hz del monitor
        GLFW.glfwShowWindow(windowHandle);
        // Habilita las funciones de OpenGL para ser usadas
        GL.createCapabilities();
        GL11.glClearColor(0.1f, 0.1f, 0.1f, 1.0f); // Color de fondo por defecto
    }

    /** @return **true** si el usuario hizo clic en la 'X' de cerrar. */
    public boolean debeCerrar() {
        return GLFW.glfwWindowShouldClose(windowHandle);
    }

    /** **Refresco de pantalla**: Intercambia los buffers de dibujo y procesa eventos. */
    public void actualizar() {
        GLFW.glfwSwapBuffers(windowHandle);
        GLFW.glfwPollEvents();
    }

    /** **Limpieza de frame**: Borra el rastro del dibujo anterior para evitar "fantasmas". */
    public void limpiar() {
        GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT);
    }

    /** **Liberación**: Cierra la ventana y limpia la memoria de GLFW. */
    public void destruir() {
        GLFW.glfwDestroyWindow(windowHandle);
        GLFW.glfwTerminate();
    }
    
    /** @return **long** El manejador (handle) de la ventana o Id de la ventana. */
    public long getHandle() { 
        return windowHandle; 
    }
}