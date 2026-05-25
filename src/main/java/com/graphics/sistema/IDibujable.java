/* SISTEMA - IDibujable
    Permite que un objeto complejo exponga sus partes para ser renderizadas
    sin contener lógica de OpenGL. */
package com.graphics.sistema;

import java.util.List;

public interface IDibujable {
    /** Obtiene todas las partes individuales que conforman el objeto.
     * Cada elemento de la lista debe ser un objeto que contenga su propio 
     * Mesh (malla) y Transform (posición/escala).
     * @return Una lista de objetos (partes) que el Renderer sabe procesar. */
    List<Object> getPartes();
}