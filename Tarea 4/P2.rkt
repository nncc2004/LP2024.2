#lang scheme

;Factoriales
(define (factorialSimple n)
  (if (= n 0)
      1
      (* n (factorialSimple (- n 1)))))



(define (factorialCola n)
  (define (factorialCola-aux n acumulador)
    (if (= n 0)
        acumulador
        (factorialCola-aux (- n 1) (* n acumulador))))
  (factorialCola-aux n 1))


;Funciones

(define (taylorSenoSimple n x)
  (if (= n 0)
      x 
      (+ (/ (* (expt -1 n) (expt x (+ (* 2 n) 1))) (factorialSimple (+ (* 2 n) 1)))
         (taylorSenoSimple (- n 1) x))))





(define (taylorCosenoCola n x)
  (define (cosenoAux n x acumulador i)
  (if (= n 0)
      acumulador  
      (cosenoAux (- n 1) 
                 x 
                 (+ acumulador (/ (* (expt -1 i) (expt x (* 2 i))) (factorialCola (* 2 i)))) 
                 (+ i 1))))
  (cosenoAux n x 1 1))




(taylorSenoSimple 3000 3.14)
(taylorCosenoCola 3000 3.14)