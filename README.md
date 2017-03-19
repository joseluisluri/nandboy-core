# nandboy-core
How to make a Game Boy emulator in Java.

## Registros de la CPU

### Introducción
La CPU está compuesta por un banco principial de ocho registros de 8-bits, los cuales pueden ser agrupados para formar registros de 16-bits, y un banco especial de dos registros de 16-bits.

**//TODO Añadir figura de la arquitectura**


|Registro|High [15-8]|Low [7-0]|Bits| Función |
|:-:|:-:|:-:|:-:|:-|
|AF|A|F| 8 + 8|(F) [Flag Register](#flag-register)|
|BC|B|C|8 + 8||
|DE|D|E|8 + 8||
|HL|H|L|8 + 8||
|SP|-|-|16|[Stack Pointer](#s-stack-pointer)|
|PC|-|-|16|[Program Counter](#p-program-counter)|

**Nota**: La función de los registros A, B, C, D, E, H y L varía en función de la instrucción.


### F (Flag Register) (#flarg-register)

| 7 | 6 | 5 | 4 | 3 | 2 | 1 | 0 |
|---|---|---|---|---|---|---|---|
| Z | N | H | C | 0 | 0 | 0 | 0 |

Este registro está reservado para funciones de control de la CPU. No es accesible por los programadores y el nibble de la parte baja siempre se encontrará a cero. Cada [flag](#f-flag) tiene el siguiente significado:

- **Zero Flag (Z):** Toma valor a 1 cuando el resultado de una operación es cero o dos valores coinciden con la instrucción CP.
- **Substract Flag (N):** Toma valor 1 cuando se produce un decremento en la última operación aritmética.
- **Half Carry Flag (H):** Toma valor 1 si se produce accareo en el nibble de la parte baja en la última operación aritmética.
- **Carry Flag (C):** Toma valor 1 si se produce un accareo en la última operación aritmética (es decir, un acarreo con desbordamiento) o si el registro A es el menor al ejecutar una instrucción CP.

## Glosario

### F

#### Flag (#f-flag)
Las banderas (y en inglés Flag) son un valor binario con el que se representa el estado de una determinada condición.


#### Flag Register 

### N

##### Nibble (#n-nibble)
Conjunto de 4 bits, es decir, medio byte.

### P

#### **Program Counter (PC)** (#p-program-counter)
El contador de programa (y en inglés Program Counter) es un registro utilizado por la CPU para almacenar la dirección de memoria de la última instrucción que debe ejecutar.

### R

#### **Registro** (#r-registro)
Un registro es una unidad de memoria de alta velocidad integrada dentro de la propia CPU.

### S

#### **Stack Pointer (SP)** (#s-stack-pointer)
