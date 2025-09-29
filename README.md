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

### 🔹 Búsqueda de Productos
- **Búsqueda por código** de producto
- Validación de campo de búsqueda
- **Navegación automática** al detalle cuando encuentra el producto
- Mensajes informativos para productos no encontrados

### 🔹 Detalle del Producto
- **Visualización completa** del producto encontrado
- Diseño elegante con separadores y formato profesional
- **Precio formateado** con separadores de miles
- Navegación de regreso a búsqueda

### 🔹 Mapa
- **Visualización de ubicación** del usuario
- Integración con Google Maps
- Funcionalidad de geolocalización

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
    ├── busqueda/
    │   ├── BusquedaFragment.java # Fragment para buscar productos
    │   └── BusquedaViewModel.java# ViewModel para lógica de búsqueda
    ├── detalle/
    │   ├── DetalleFragment.java  # Fragment para mostrar detalle
    │   └── DetalleViewModel.java # ViewModel para lógica de detalle
    ├── maps/
    │   └── MapsFragment.java     # Fragment para mostrar mapa
    └── salir/
        ├── SalirFragment.java    # Fragment para salir
        └── SalirViewModel.java   # ViewModel para lógica de salida

app/src/main/res/
├── layout/
│   ├── activity_main.xml         # Layout principal con Navigation Drawer
│   ├── fragment_cargar.xml       # Layout del formulario de carga
│   ├── fragment_listar.xml       # Layout de la lista de productos
│   ├── fragment_busqueda.xml     # Layout del formulario de búsqueda
│   ├── fragment_detalle.xml      # Layout del detalle del producto
│   ├── fragment_maps.xml         # Layout del mapa
│   ├── fragment_salir.xml        # Layout de confirmación de salida
│   └── item_producto.xml         # Layout del item de producto
├── drawable/
│   ├── ic_cargar_producto.png    # Ícono para cargar productos
│   ├── ic_listar.png             # Ícono para listar productos
│   ├── ic_buscar.png             # Ícono para búsqueda
│   ├── ic_maps.png               # Ícono para mapa
│   └── ic_salir.png              # Ícono para salir
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
   - **Cargar**: Agregar nuevos productos con validaciones
   - **Listar**: Ver productos ordenados alfabéticamente
   - **Búsqueda**: Buscar productos por código y ver detalle
   - **Mapa**: Ver ubicación actual del usuario
   - **Salir**: Cerrar la aplicación con confirmación

## 📱 Capturas de Pantalla

La aplicación cuenta con una interfaz intuitiva que incluye:
- **Navigation Drawer** con 5 opciones principales
- **Formularios** con validación en tiempo real
- **Lista de productos** con diseño de tarjetas y ordenamiento
- **Búsqueda y detalle** con navegación fluida
- **Integración de mapas** para geolocalización
- **Diálogos de confirmación** para acciones críticas

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
