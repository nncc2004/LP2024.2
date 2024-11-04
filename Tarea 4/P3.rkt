#lang scheme

(define (profundidades arbol)
  (sort(recorrer arbol 0)<))


(define (recorrer arbol profundidad)
  (if (null? arbol)
      '()  
      (let ((nodo (car arbol))
            (hijos (cdr arbol)))
        (append
         (if (equal? nodo 'T) (list profundidad) '())
         (apply append (map (lambda (subarbol)
                              (recorrer subarbol (+ profundidad 1)))
                            hijos))))))


(profundidades '(1 (T (3) (T)) (2 (4 (6 (T))) (5))) )

(profundidades '(1 (T (3) (T)) (2 (4 (6 (T)) (T)) (T (7 (8 (T) (T)))))))