# 🐦 New Flappy Bird - UAGRM

Proyecto realizado en Java utilizando LWJGL (OpenGL + GLFW + OpenAL).

---

# 📌 Descripción

Este proyecto es una recreación de **Flappy Bird** con:

* Renderizado 2D usando OpenGL.
* Sistema de físicas simple.
* Detección de colisiones.
* Menú interactivo.
* Música y efectos de sonido.
* Arquitectura modular orientada a objetos.
* Modo P1 vs P2.

---

# 🛠 Tecnologías Utilizadas

* Java
* LWJGL 3
* OpenGL
* GLFW
* OpenAL
* JOML
* Maven

---

# ▶️ Ejecución

Ejecutar con:

```bash
mvn compile exec:exec "-DmainClass=com.graphics.App"
```

---

# 📂 Estructura del Proyecto

```text
com.graphics
│
├── audio
├── fisica
├── graficos_y_recursos
├── juego
│   ├── input
│   ├── modos
│   └── ui
├── matematicas
├── objetos
└── sistema
```

---

# ⚙️ Arquitectura General

## 🔹 MAIN

Contiene el punto de entrada del programa.

| Clase | Función                                       |
| ----- | --------------------------------------------- |
| App   | Inicia el juego y controla el flujo principal |

---

## 🔹 SISTEMA

Clases base del motor gráfico y renderizado.

| Clase                 | Función                              |
| --------------------- | ------------------------------------ |
| Window                | Crea la ventana y el contexto OpenGL |
| Input                 | Control del teclado                  |
| Renderer              | Renderizado básico de objetos        |
| RendererJerarquico    | Dibujo de objetos compuestos         |
| ObjectRenderer        | Dibujo de objetos simples            |
| ObjectRendererTexture | Dibujo de sprites con textura        |
| RenderConfig          | Configuración de OpenGL              |
| Camera                | Manejo de la cámara                  |
| Engine                | Ciclo principal del juego            |
| MenuRenderer          | Renderizado del menú                 |
| RenderFacade          | Centraliza renderizados              |
| MonitorUtils          | Obtiene resolución del monitor       |
| AudioManager          | Inicializa OpenAL                    |

---

## 🔹 GRAFICOS_Y_RECURSOS

Carga de shaders, texturas y mallas.

| Clase         | Función              |
| ------------- | -------------------- |
| Mesh          | Malla básica         |
| MeshColor     | Malla con colores    |
| MeshTexture   | Malla con textura    |
| Shader        | Shader base          |
| ShaderTexture | Shader para texturas |
| Texture       | Carga imágenes       |
| Sprite        | Maneja sprites 2D    |
| Vertex        | Punto geométrico     |
| VertexColor   | Vértice con color    |
| VertexTexture | Vértice con UV       |
| Color         | Colores RGBA         |

---

## 🔹 FISICA

Control de movimiento y colisiones.

| Clase                | Función                      |
| -------------------- | ---------------------------- |
| CajaColision         | Colisiones rectangulares     |
| CirculoColision      | Colisiones circulares        |
| SistemaColisiones    | Detecta impactos             |
| MovimientoHorizontal | Movimiento automático        |
| ControladorPajaro    | Física y control del jugador |

---

## 🔹 JUEGO

Control general del gameplay.

| Clase              | Función                   |
| ------------------ | ------------------------- |
| GameManager        | Cambia entre menú y juego |
| EstadoJuego        | Estados principales       |
| SistemaPuntos      | Manejo de puntajes        |
| MusicPlayer        | Reproduce música          |
| AudioManagerGlobal | Control global de audios  |

---

## 🔹 UI

Interfaces y menús.

| Clase          | Función           |
| -------------- | ----------------- |
| MenuPrincipal  | Menú principal    |
| ReintentarMenu | Menú de game over |
| Puntuacion     | UI de puntajes    |
| EstadoMenu     | Opciones del menú |

---

## 🔹 MODOS

Modos de juego.

| Clase     | Función              |
| --------- | -------------------- |
| JuegoP1P2 | Partida multijugador |

---

# 🎮 Controles

| Acción     | Tecla   |
| ---------- | ------- |
| Saltar P1  | SPACE   |
| Saltar P2  | ENTER   |
| Mover menú | Flechas |
| Confirmar  | ENTER   |

---

# 🧠 Conceptos Aplicados

* Programación Orientada a Objetos.
* Renderizado con OpenGL.
* Vertex Buffer Objects (VBO).
* Vertex Array Objects (VAO).
* Shaders.
* Sistema de físicas básico.
* Detección de colisiones.
* Arquitectura modular.
* Renderizado jerárquico.
* Manejo de recursos gráficos.
* Audio multihilo.

---

# 📸 Capturas

Agregar imágenes del juego aquí.

```text
/docs/menu.png
/docs/gameplay.png
```

---

# 🚀 Posibles Mejoras

* Implementar modo P1.
* Agregar animaciones.
* Sistema de récords.
* Más obstáculos.
* Sonidos de colisión.
* Optimización de renderizado.
* Menú de pausa.

---

# 👨‍💻 Autor

Proyecto académico desarrollado en Java + LWJGL.
