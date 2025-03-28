# SeatCodeTest

## 🚀 Cómo lanzar el proyecto

1. **Clona el repositorio:**
   ```bash
   git clone https://github.com/ragnorak-dev/SeatCodeTest.git
   ```

2. **Abre en Android Studio:**
    - El poryecto a sido creado con Android Studio Meerkat | 2024.3.1.
    - Kotlin versión 2.1.10.
    - JDK 17.

3. **Ejecuta la app:**
    - Selecciona el módulo `app`.
    - Elige un emulador o dispositivo físico y pulsa Run.

4. **Prueba el proyecto:**
    - Ejecuta los tests unitarios:
      ```bash
      ./gradlew testDebugUnitTest
      ```
    - Ejecuta los tests de integración (instrumentados):
      ```bash
      ./gradlew connectedDebugAndroidTest
      ```

---

## 📐 Decisiones y consideraciones

Dado que en el enunciado del ejercicio no se hacía mención a servicios REST, opté por dividir la solución en dos partes para simular la entrada y salida de datos:
- **Input:** Encargado de introducir los datos del rover.
- **Output:** Responsable de mostrar la salida esperada según la descripción del enunciado.


- **Modularización:** App dividida en módulos (`input`, `output`, `core:resources`, `core:persistence`) para mayor mantenibilidad y escalabilidad.
- **Clean Architecture:** para tener una arquitectura limpia y escalable y separación por capas en cada modulo no core(UI, domain, data).
- **Jetpack Compose:** Para crear una UI moderna y declarativa.
- **Room + Flow:** Para una persistencia reactiva de datos.
- **Hilt:** Para inyección de dependencias sencilla y escalable.
- **Use Cases:** La lógica de negocio está contenida en casos de uso que permiten testear y reutilizar fácilmente.
- **Tests unitarios y de integración:** Para garantizar el correcto funcionamiento de lógica y UI.
- **CI con GitHub Actions:** Automatización de pruebas y builds en pull requests hacia `main`.

- Compatible con Android API 29+

---

## 🛠️ Tecnologías usadas

- **Kotlin** 2.1.10
- **Jetpack Compose**
- **Room**
- **Kotlinx Coroutines + Flow**
- **Hilt**
- **JUnit + MockK**
- **Turbine**
- **Compose UI Testing**
- **GitHub Actions**


