# PoliEats_ProyectoEDA 🆗🍽️

PoliEats es un sistema de gestión para el restaurante de la Escuela Politécnica Nacional. Utiliza estructuras de datos avanzadas y algoritmos para optimizar el manejo de pedidos, inventarios y reservas, aplicando conceptos aprendidos en la materia de Estructura de Datos y Algoritmos para mejorar la eficiencia y funcionalidad del servicio. 🍔🍗

## Índice de Contenidos

1. [Funcionamiento del Programa](#funcionamiento-del-programa)
   - [Listas Doblemente Enlazadas](#1-listas-doblemente-enlazadas)
   - [Colas](#2-colas)
   - [Árbol Binario de Búsqueda](#3-árbol-binario-de-búsqueda)
2. [Demostración de Funcionamiento](#demostración-de-funcionamiento)
   - [Visualización del Programa](#visualización-del-programa)
3. [Herramientas Usadas](#herramientas-usadas)
4. [Autores](#autores)

## Funcionamiento del Programa

PoliEats gestiona de manera eficiente los pedidos de los clientes, el inventario de productos y las reservas de mesas utilizando varias estructuras de datos:

- **Listas Doblemente Enlazadas:** Se utilizan para manejar los pedidos, permitiendo recorrer la lista en ambas direcciones y facilitando operaciones de inserción y eliminación en cualquier posición.
- **Colas:** Se emplean para gestionar los elementos del menú y el inventario, permitiendo que los pedidos se procesen en el orden en que se reciben (FIFO - First In, First Out).
- **Árbol Binario de Búsqueda:** Se usa para organizar y buscar reservas de mesas de forma eficiente, facilitando consultas rápidas y ordenadas.

### 1. [Listas Doblemente Enlazadas](https://github.com/FernandoHuilca/ListasDoblementeEnlazadas)
<table>
<tr>
<td>
<br>

**Descripción:** 

Una lista doblemente enlazada es una estructura de datos en la que cada nodo tiene referencias al nodo siguiente y al nodo anterior, permitiendo recorrer la lista en ambas direcciones.

**Características:**  
- **Referencias Bidireccionales:** Cada nodo tiene punteros a los nodos anterior y siguiente.
- **Recorrido Doble:** Se puede recorrer hacia adelante y hacia atrás.
- **Inserción/Eliminación Rápida:** Facilita operaciones eficientes en cualquier posición.
- **Mayor Consumo de Memoria:** Requiere más memoria por nodo debido a las dos referencias.

**Código de la Estructura de Datos:**

Para obtener más información sobre la estructura de datos utilizada en este proyecto, **[haz clic aquí](https://github.com/FernandoHuilca/ListasDoblementeEnlazadas)**.
<br> 
</td>
<td>

<img src="https://github.com/user-attachments/assets/59415d26-5022-46a6-926b-c196b4633c8d" alt="Diagrama de Lista Doblemente Enlazada" width="1300">
<br>

</td>
</tr>
</table>

### 2. [Colas](https://github.com/FernandoHuilca/ListaSimple)
<table>
<tr>
<td>
<br>

**Descripción:** 

Una cola es una estructura de datos que sigue el principio FIFO (First In, First Out), donde los elementos se añaden al final y se eliminan desde el frente.

**Características:**  
- **FIFO:** Los elementos se procesan en el orden en que se reciben.
- **Operaciones Eficientes:** Inserción al final y eliminación desde el frente con complejidad O(1).
- **Aplicaciones:** Ideal para manejar tareas en espera y procesamiento de pedidos.

**Código de la Estructura de Datos:**

Para obtener más información sobre la estructura de datos utilizada en este proyecto, **[haz clic aquí](https://github.com/FernandoHuilca/ListaSimple)**.
<br> 
</td>
<td>

<img src="https://github.com/user-attachments/assets/3c999da2-10e4-4049-8082-5f2a67549889" alt="Diagrama de Cola" width="1300">
<br>

</td>
</tr>
</table>

### 3. [Árbol Binario de Búsqueda](https://github.com/FernandoHuilca/Arboles_Binarios_de_Busqueda)
<table>
<tr>
<td>
<br>

**Descripción:** 

Un árbol binario de búsqueda es una estructura de datos en la que cada nodo tiene hasta dos hijos, y los nodos están organizados de manera que el valor del nodo izquierdo es menor y el del nodo derecho es mayor que el nodo padre.

**Características:**  
- **Ordenación:** Facilita la búsqueda, inserción y eliminación de nodos en tiempo logarítmico promedio.
- **Recorrido Ordenado:** Permite recorrer los nodos en orden creciente.
- **Balanceo:** Puede ser balanceado para mantener operaciones eficientes.

**Código de la Estructura de Datos:**

Para obtener más información sobre la estructura de datos utilizada en este proyecto, **[haz clic aquí](https://github.com/FernandoHuilca/Arboles_Binarios_de_Busqueda)**.
<br> 
</td>
<td>

<img src="https://github.com/user-attachments/assets/f676c74d-5287-4a6d-a127-430d29ae8282" alt="Diagrama de Árbol Binario de Búsqueda" width="1300">
<br>

</td>
</tr>
</table>

## Demostración de Funcionamiento

### Visualización del Programa

<table>
    <tr>
    <td><img src="https://github.com/user-attachments/assets/fe8ec399-b7af-4cdb-991a-32b0204c014a" alt="Interfaz de Pedidos"></td>
    <td><img src="https://github.com/user-attachments/assets/4a597957-4c91-4c0a-bffb-1ec5cc77b142"></td>
  </tr>
     <tr>
    <td><img src="https://github.com/user-attachments/assets/df314ec9-58a8-4e58-9f90-a865b8972935" alt="Interfaz de Pedidos"></td>
    <td><img src="https://github.com/user-attachments/assets/19296f69-2ef4-430b-b597-dc9d26b21dcf"></td>
  </tr>
  <tr>
    <td><img src="https://github.com/user-attachments/assets/825ed267-1123-4f62-9022-beec250dbeaa" alt="Interfaz de Pedidos"></td>
    <td><img src="https://github.com/user-attachments/assets/b3a54258-9b08-422e-88ca-850fc3226f6d"></td>
  </tr>
  <tr>
    <td><img src="https://github.com/user-attachments/assets/415a9ae1-a3fa-4372-85fe-4de823a9b306" alt="Interfaz de Reservas"></td>
    <td><img src="https://github.com/user-attachments/assets/a963368a-4e48-4c33-84c9-a4052f3338ad" alt="Gestión de Pedidos"></td>
  </tr>
</table>



## Herramientas Usadas

- **Lenguaje de Programación:** Java
- **Versionamiento:** Git and github
- **Interfaz gráfica:** Scene Builder y JavaFX
- **IDEs:** IntelliJ y netbeans

## Autores

- **Fernando Huilca** - Programador [@FernandoHuilca](https://github.com/FernandoHuilca)
- **Mateo Simbaña** - Programador [@mateonicolasg](https://github.com/mateonicolasg)
- **Mateo Quisilema** - Programador [@JuanMateoQ](https://github.com/JuanMateoQ)
- **Alisson Betancourt** - Programadora [@AlizBet44](https://github.com/AlizBet44)
<br> 
  <table>
    <tr>
    <td><img src="https://github.com/user-attachments/assets/c7e475f4-9d6b-4a42-8d82-a6b570537d90" alt="Interfaz de Pedidos"></td>
    <td><img src="https://github.com/user-attachments/assets/4ef3bfb6-f262-4fff-a4d1-9ea810ce66e5"></td>
</table>
