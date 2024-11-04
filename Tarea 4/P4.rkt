#lang scheme

(define (profundidades arbol)
  ;;Descripción: Recibe el arbol y llama a la función auuxiliar 'recorrer'.
  ;;Luego al recibir el retorno, lo ordena de menor a mayor
  ;;
  ;;Parametro 'arbol': es la lista de listas que representa al arbol
  
  (sort(recorrer arbol 0)<))



(define (recorrer arbol profundidad)
  ;; Descripción: Realiza un recorrido del árbol representado por listas anidadas.
  ;; Utiliza recursión para explorar cada nodo y subárbol, registrando la profundidad
  ;; de cada nodo con el valor 'T' y haciendo append en la lista que finalmente se retorna.
  ;; Con `map` se logra que la lógica se aplique a todos los elementos de la lista de hijos.
  ;;
  ;; Parámetro 'arbol': lista de listas que representa la estructura del árbol.
  ;; Parámetro 'profundidad': número que indica la profundidad actual en el recorrido.
  (if (null? arbol)
      '()  
      (let ((nodo (car arbol))
            (hijos (cdr arbol)))
        (append
         (if (equal? nodo 'T) (list profundidad) '())
         (apply append (map (lambda (subarbol)
                              (recorrer subarbol (+ profundidad 1)))
                            hijos))))))


;;Casos de prueba:
(profundidades '(1 (T (3) (T)) (2 (4 (6 (T))) (5))))
(profundidades '(1 (T (3) (T)) (2 (4 (6 (T)) (T)) (T (7 (8 (T) (T)))))))