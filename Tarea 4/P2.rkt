#lang scheme

;Funciones auxiliares (factoriales)
define (factorialSimple n)
  ;;   Descripción: Calcula el factorial de `n` usando recursión simple.
  ;;   Si `n` es 0, retorna 1. De lo contrario, multiplica `n` por el factorial
  ;;   de `n - 1`.
  ;;
  ;;   Parámetro 'n': número entero no negativo cuyo factorial se quiere calcular.
  (if (= n 0)
      1
      (* n (factorialSimple (- n 1)))))



(define (factorialCola n)
  ;;   Descripción: Calcula el factorial de `n` usando recursión de cola.
  ;;   Utiliza una función auxiliar con un acumulador para utilizar recursión de cola.
  ;;
  ;;   Parámetro 'n': número entero no negativo cuyo factorial se quiere calcular.
  (define (factorialCola-aux n acumulador)
    ;; Descripción: Función auxiliar para realizar la recursión de cola.
    ;; Mantiene un acumulador que almacena el resultado parcial del factorial.
    ;; Caso base: Si 'n' es 0, retorna el acumulador, que contiene el resultado final.
    ;; Caso recursivo: Multiplica 'n' por el acumulador y llama recursivamente con (n - 1).
    ;;
    ;; Parámetro 'n': el número decreciente en cada llamada recursiva.
    ;; Parámetro 'acumulador': el resultado parcial acumulado del factorial.
    (if (= n 0)
        acumulador
        (factorialCola-aux (- n 1) (* n acumulador))))
  (factorialCola-aux n 1))


;Funciones principales

(define (taylorSenoSimple n x)
   ;; Descripción: Calcula una aproximación de seno(x) usando una serie de Taylor
  ;; con 'n' términos. Utiliza recursión simple para calcular los términos de la serie.
  ;; Caso base: Si 'n' es 0, retorna 'x' (primer término de la serie).
  ;; Caso recursivo: Calcula el siguiente término usando la fórmula de la serie
  ;; y lo suma a la llamada recursiva con (n - 1). Usa recursión simple y el factorial
  ;; al que llama también usa recutsión simple.
  ;;
  ;;Parametro 'n': Es la cantidad de iteraciones de la recursión
  ;;Parametro 'x': Es el numero que se desea aproximar
  (if (= n 0)
      x 
      (+ (/ (* (expt -1 n) (expt x (+ (* 2 n) 1))) (factorialSimple (+ (* 2 n) 1)))
         (taylorSenoSimple (- n 1) x))))



(define (taylorCosenoCola n x)
  ;; Descripción: Llama a la función cosenoAux con la x, n, un acumulador inicializado en 1 y un valor i para calcular
  ;; el termino de la serie. También se define aquí la función cosenoAux que usa recursión de cola.
  ;;
  ;;Parametro 'n': Es la cantidad de iteraciones de la recursión
  ;;Parametro 'x': Es el numero que se desea aproximar
  (define (cosenoAux n x acumulador i)
    ;; Descripción: Calcula una aproximación de coseno(x) usando la serie de Taylor
    ;; con 'n' términos, utilizando recursión de cola.
    ;; Utiliza un acumulador para almacenar la suma parcial y un índice 'i' para
    ;; calcular cada término de la serie.
    ;;Parametro 'n': Es la cantidad de iteraciones de la recursión
    ;;Parametro 'x': Es el numero que se desea aproximar
    ;;Parametro 'acumulador': acumula el valor de la aproximación en cada iteración.
    ;;Parametro 'i': entero utilizado como índice para la cantidad de iteraciones (terminos a calcular en la sumatoria)
  (if (= n 0)
      acumulador  
      (cosenoAux (- n 1) 
                 x 
                 (+ acumulador (/ (* (expt -1 i) (expt x (* 2 i))) (factorialCola (* 2 i)))) 
                 (+ i 1))))
  (cosenoAux n x 1 1))



;; Casos de prueba
(taylorSenoSimple 1 2.14)
(taylorCosenoCola 1 2.14)