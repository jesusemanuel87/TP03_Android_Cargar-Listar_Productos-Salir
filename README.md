# TP03 Android - Cargar, Listar Productos y Salir

Aplicación Android que implementa un sistema de gestión de productos utilizando el patrón arquitectónico **MVVM (Model-View-ViewModel)** con **Navigation Drawer** y **RecyclerView**.

## 📱 Funcionalidades

### 🔹 Cargar Productos
- Formulario para ingresar **código**, **descripción** y **precio**
- Validación de campos vacíos
- Validación de códigos duplicados
- Interfaz intuitiva con campos de entrada

### 🔹 Listar Productos
- **RecyclerView** con todos los productos agregados
- **Ordenamiento alfabético** automático por descripción
- Diseño de tarjetas (CardView) con:
  - Código del producto
  - Descripción destacada
  - Precio alineado a la derecha
- Actualización automática de la lista

### 🔹 Salir
- Diálogo de confirmación para cerrar la aplicación
- Opción segura de salida

## 🏗️ Arquitectura MVVM

### **Model**
- **Clase Producto**: Entidad con código, descripción y precio
- **Almacenamiento en memoria**: ArrayList estático en MainActivity

### **View**
- **Fragments**: Interfaz de usuario para cada funcionalidad
- **Data Binding**: Vinculación eficiente con layouts XML
- **Navigation Drawer**: Menú lateral para navegación

### **ViewModel**
- **Intermediario** entre View y Model
- **LiveData**: Observación reactiva de cambios
- **Lógica de presentación** y validaciones

## 📂 Estructura del Proyecto

```
app/src/main/java/.../
├── MainActivity.java              # Actividad principal con Navigation Drawer
├── model/
│   └── Producto.java             # Clase modelo del producto
└── ui/
    ├── cargar/
    │   ├── CargarFragment.java   # Fragment para cargar productos
    │   └── CargarViewModel.java  # ViewModel para lógica de carga
    ├── listar/
    │   ├── ListarFragment.java   # Fragment para mostrar lista
    │   ├── ListarViewModel.java  # ViewModel para lógica de lista
    │   └── ProductoAdapter.java  # Adapter para RecyclerView
    └── salir/
        ├── SalirFragment.java    # Fragment para salir
        └── SalirViewModel.java   # ViewModel para lógica de salida

app/src/main/res/
├── layout/
│   ├── activity_main.xml         # Layout principal con Navigation Drawer
│   ├── fragment_cargar.xml       # Layout del formulario de carga
│   ├── fragment_listar.xml       # Layout de la lista de productos
│   ├── fragment_salir.xml        # Layout de confirmación de salida
│   └── item_producto.xml         # Layout del item de producto
├── menu/
│   └── activity_main_drawer.xml  # Menú del Navigation Drawer
└── navigation/
    └── mobile_navigation.xml     # Configuración de navegación
```

## 🛠️ Tecnologías Utilizadas

- **Android SDK** - Desarrollo nativo Android
- **Java** - Lenguaje de programación principal
- **MVVM Architecture** - Patrón arquitectónico
- **Data Binding** - Vinculación de datos
- **LiveData** - Observación reactiva
- **Navigation Component** - Navegación entre fragments
- **RecyclerView** - Lista eficiente de elementos
- **CardView** - Diseño de tarjetas
- **Material Design** - Interfaz de usuario moderna

## 📋 Características Técnicas

- **Sin persistencia**: Datos almacenados solo en memoria
- **Validaciones**: Campos obligatorios y códigos únicos
- **Ordenamiento**: Lista alfabética automática por descripción
- **Responsive Design**: Adaptable a diferentes tamaños de pantalla
- **Material Design**: Interfaz moderna y consistente

## 🚀 Instalación y Uso

1. **Clonar el repositorio**
   ```bash
   git clone [URL_DEL_REPOSITORIO]
   ```

2. **Abrir en Android Studio**
   - File → Open → Seleccionar carpeta del proyecto

3. **Compilar y ejecutar**
   - Conectar dispositivo Android o usar emulador
   - Click en "Run" o presionar Shift + F10

4. **Usar la aplicación**
   - **Cargar**: Agregar nuevos productos
   - **Listar**: Ver productos ordenados alfabéticamente
   - **Salir**: Cerrar la aplicación con confirmación

## 📱 Capturas de Pantalla

La aplicación cuenta con una interfaz intuitiva que incluye:
- Navigation Drawer para navegación fácil
- Formularios con validación en tiempo real
- Lista de productos con diseño de tarjetas
- Diálogos de confirmación

## 🔧 Requisitos del Sistema

- **Android API Level**: 24+ (Android 7.0)
- **Android Studio**: 4.0+
- **Gradle**: 7.0+
- **Java**: 8+

## 📚 Patrones y Buenas Prácticas

- **MVVM Architecture**: Separación clara de responsabilidades
- **Single Responsibility**: Cada clase tiene una función específica
- **Observer Pattern**: LiveData para actualizaciones reactivas
- **Data Binding**: Reducción de findViewById
- **Material Design Guidelines**: Interfaz consistente y moderna

## 🤝 Contribución

Este proyecto fue desarrollado como parte del curso de **Dispositivos Móviles - ANDROID 2025** en la **Universidad de La Punta (ULP)**, implementando las mejores prácticas de desarrollo móvil con Android y el patrón arquitectónico MVVM.

## 📄 Licencia

**Proyecto académico - Universidad de La Punta 2025**

**Desarrollado por**: García Jesús Emanuel  
**Curso**: Dispositivos Móviles - ANDROID 2025 - ULP 2025
