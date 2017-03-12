# nandboy-core
How to make a Game Boy emulator in Java.

## Registros de la CPU

Está compuesto de ocho registros de 8-bits y dos de 16-bits. Algunos registros
son accesibles en parejas como si fuesen un único registro de 16-bits:

- **A:** principalmente como acumulador.
- **B, C, D, E:** registros de propósito general y cuya función varía dependiendo de la instrucción.
- **H y L:** también de propósito general aunque algunas instrucciones utilizan este par como un acumulador de 16-bit.
- **F:** Flag register, no es accesible directamente por el programador y se utiliza para almacenar un valor binario que tiene asignado un significado.
- **SP** Stack Pointer, almacena la dirección de memoria de la posición actual de la pila.
- **PC** Program Counter, almacena la dirección de memoria en la que se encuentra el procesador
en la actual secuencia de instrucciones.

|15-8|7-0|
|:----:|:---:|
|A   |F  |
|B   |C  |
|D   |E  |
|H   |L  |

|15-0|
|:----:|
|SP      |
|PC      |

### F (Flag Register)

| 7 | 6 | 5 | 4 | 3 | 2 | 1 | 0 |
|---|---|---|---|---|---|---|---|
| Z | N | H | C | 0 | 0 | 0 | 0 |

El nibble de la parte baja siempre a cero, mientras que en la parte alta cada bit tendrá el siguiente significado:

- **Zero Flag (Z):** Toma valor a 1 cuando el resultado de una operación
es cero o dos valores coinciden con la instrucción CP.
- **Substract Flag (N):** Toma valor 1 cuando se produce un decremento en la
última operación aritmética.
- **Half Carry Flag (H):** Toma valor 1 si se produce accareo en el nibble de
la parte baja en la última operación aritmética.
- **Carry Flag (C):** Toma valor 1 si se produce un accareo en la última operación
aritmética -es decir, un acarreo con desbordamiento- o si el registro A es el menor al
ejecutar una instrucción CP.
