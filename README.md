
<!-- https://www.iconfinder.com/icons/381627/gameboy_icon#size=512 -->
![logo](./doc/logo.png)
# nandboy-core
How to make a Game Boy emulator in Java.

## Table de contenidos
1. Introducción
2. Tipos de datos
2. CPU
	1. asdlaskld
	2. 
1. 2.CPU
1. 3.asdasd
1. 1. 3.Memoria

## Tipos de datos
A nivel práctico, las unidades de datos con los que vamos a operar son la palabra (8 bits) y la doble palabra (16 bits); sin embargo, a nivel conceptual, es conveniente ser un poco más específicos y declarar los siguientes tipos de datos:

| Tipo de dato | Tamaño | Implementación|
|:-|:-:|:-|
|Palabra|8 bits|Word|
|Doble palabra|16 bits|DWord|
|Dirección de memoria|16 bits|MemoryAddr|


## CPU

### Tipos de instrucciones
|#|Tipo| Descripción|
|:-:|:-|:-|
|**1**|LD r8, r'8| Copiar el valor de r'8 en r8|
|**2**|LD r8, (r16)| Copiar el valor ubicado en la dirección de memoria a la que apunta r16 en r8|
|**3**|LD r8, (imm16) | Copiar el valor ubicado en la dirección de memoria a la que apunta el valor inmediato en r8|
|**4**|LD (r16), r8| Copiar el valor de r8 en la dirección de memoria a la que apunta r16|
|**5**|LD (imm16), r8 | Copiar el valor de r8 en la dirección de memoria a la que apunta el valor inmediato|

### Repertorio de instrucciones
|Opcode|Ciclos|Tipo|Instrucción|
|:-:|:-:|:-|:-|
|0x00|1||NOP|
|0x02|8|4| LD (BC), A|
||||
|0x40|4|1|LD B, B|
|0x41|4|1|LD B, C|
|0x42|4|1|LD B, D|
|0x43|4|1|LD B, E|
|0x44|4|1|LD B, H|
|0x45|4|1|LD B, L|
|0x46|8|2|LD B, (HL)|
|0x47|4|1|LD B, A|
||||
|0x0A|8|2| LD A, (BC)|
|0x12|8|4| LD (DE), A|
|0x1A|8|2| LD A, (DE)|
|0x77|8|4| LD (HL), A|
|0x78|4|1|LD A, B|
|0x79|4|1|LD A, C|
|0x7A|4|1|LD A, D|
|0x7B|4|1|LD A, E|
|0x7C|4|1|LD A, H|
|0x7D|4|1|LD A, L|
|0x7E|8|2| LD A, (HL)|
|0x7F|4|1|LD A, A|
|||||
|0xEA|16|5| LD (*inmediato)*, A|
|0xE2|8|X|LD A, (0xFF00 + C)
|0xF2|8|2|LD A, (C)|
|0xF4|16|3|LD A, (*inmediato*)|


## Registros

### Introducción
La memoria interna de la CPU está compuesta por dos bancos de registros. El banco principial alberga ocho registros de 8-bit, los cuales pueden ser agrupados en registros de 16-bit; mientras que el banco especial, se constituye de dos registros indivisibles de 16-bits.

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

### I

#### Inmediato
Un valor inmediato es un número escrito con dígitos que acompaña a una instrucción.

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

## References

* a
* b
* c
* **Tahsin Tahil**: Gameboy logo
