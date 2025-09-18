# Prompts de Documentación

## Prompt: Generar cuadro de endpoints y pasos de ejecución en README

### Prompt Utilizado:
"dame el cuadro de los endpoints y los pasos a seguir para la ejecución del proyecto en un readme"

### Respuesta Recibida:
Generó un cuadro en Markdown con todos los endpoints del sistema (`/api/empleados`, `/api/departamentos`, `/api/proyectos`, etc.) mostrando el método HTTP, la descripción y ejemplos de `curl`. 

### Modificaciones Realizadas:
- Reemplacé los ejemplos de `curl` con datos reales de empleados y departamentos de mi sistema.
- Agregué los enlaces a la consola H2 (`/h2-console`).

### Explicación del Prompt:
Se usó para que el README sea claro y completo, permitiendo a cualquier usuario ejecutar la aplicación y probar los endpoints sin conocimiento previo.

### Aprendizajes Obtenidos:
- Cómo presentar endpoints de forma clara en tablas Markdown.
- La importancia de detallar distintos flujos de ejecución para desarrollo y producción.
- Buenas prácticas en documentación: ejemplos reproducibles con `curl`.
