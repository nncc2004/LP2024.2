#lang scheme


(define (buscador lista elemento)
  ;;Descripción: Recibe la lista y el elemento. Luego define y llama a la funcion
  ;;buscador2, con un parametro de posicion que inicialmente es 1. Cuando obtenga el
  ;;retorno de buscador2, lo retorna también.
  ;;
  ;;Parametro lista:  Es la lista en la que se desea buscar el elemento
  ;;Parámetro elemento: Es el elemento que se desea buscar en la lista
  (define (buscador2 lista elemento posicion)
    ;;Descripción: buscador2 es la funcion recursiva, pues se llama a si misma en caso de no estar
    ;; en una de las condiciones de termino de la recursividad. El funcionamiento es:
    ;; Se llama a si misma con el parametro (cdr lista) que es la lista sin el elemento inicial,
    ;; con el mismo elemento a busca y con la posicion + 1 para hacer el retorno cuando se encuentre.
    ;; Las condiciones de termino de la recursividad son dos: En caso de que la lista esté vacía (-1)
    ;; y en caso de que el primer elemento de la lista (car lista) sea igual al elemento buscado (posicion)
    
    ;;Parametro lista:  Es la lista en la que se desea buscar el elemento
    ;;Parámetro elemento: Es el elemento que se desea buscar en la lista
    ;;Parámetro posicion: Representa la posicion al retornar, pero es el numero de recursiones que se lleva partiendo desde 1.
    (cond
      [(null? lista) -1];
      [(equal? (car lista) elemento) posicion];
      [else (buscador2 (cdr lista) elemento (+ posicion 1) )]))
  (buscador2 lista elemento 1)
  )



"Pruebas pre hechas"
(buscador '(1 2 3) 0)
(buscador '(ABC "ABC" 3.0 1234) "ABC")
(buscador '(ABC "ABC" 3.0 1234) 'ABC)
(buscador '(389 (2 4 5.0) (40 here 2)) '(40 here 2))
(buscador '() 'INF253)