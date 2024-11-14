%------------------------------------------------------------------------------------
% Predicado: extraer_sublista(_, A, B, [])
% Parámetros:
%   - _: Lista completa (no se utiliza en este caso).
%   - A: Índice inicial de la sublista.
%   - B: Índice final de la sublista.
%   - []: Resultado vacío en caso de que A > B.
% Descripción:
%   Caso base que termina la recursión cuando A > B, devolviendo una lista vacía.
% Retorno:
%   - []: Si A > B, retorna una lista vacía.

% Implementación:
extraer_sublista(_, A, B, []) :- A > B.
%------------------------------------------------------------------------------------
%------------------------------------------------------------------------------------
% Predicado: extraer_sublista([Cabeza|Cola], 1, B, [Cabeza|Sublista])
% Parámetros:
%   - [Cabeza|Cola]: Lista original separada en cabeza y cola.
%   - 1: Índice inicial (se ha llegado a la posición deseada).
%   - B: Índice final para extraer.
%   - [Cabeza|Sublista]: Sublista acumulada.
% Descripción:
%   En el caso de que A sea 1,
%   se extrae elementos de la lista desde la posición inicial 1 hasta el índice B.
% Retorno:
%   - [Cabeza|Sublista]: Sublista extraída entre los índices especificados.
%   - [] o false: Lista vacia en caso de algun error (Dependiendo del error podria ser false o [])

% Implementacion:
extraer_sublista([Cabeza|Cola], 1, B, [Cabeza|Sublista]) :-
    B > 0,
    B1 is B - 1,
    extraer_sublista(Cola, 1, B1, Sublista).

%------------------------------------------------------------------------------------
%------------------------------------------------------------------------------------
% Predicado: extraer_sublista([_|Cola], A, B, Sublista)
% Parámetros:
%   - [_|Cola]: Lista original ignorando la cabeza.
%   - A: Índice inicial actual.
%   - B: Índice final.
%   - Sublista: Sublista extraída.
% Descripción:
%   En el caso de A distinto a 1,
%   avanza en la lista para reducir el índice inicial hasta llegar a 1.
% Retorno:
%   - Sublista: Sublista extraída entre los índices especificados.
%   - [] o false: Lista vacia en caso de algun error (Dependiendo del error podria ser false o [])

% Implementacion:
extraer_sublista([_|Cola], A, B, Sublista) :-
    A > 1,
    A1 is A - 1,
    B1 is B - 1,
    extraer_sublista(Cola, A1, B1, Sublista).

%------------------------------------------------------------------------------------
%------------------------------------------------------------------------------------
% Predicado: esPalindroma(Lista, Rango)
% Parámetros:
%   - Lista: Lista original sobre la cual se extrae la sublista.
%   - Rango: Lista con dos elementos [L, R], los índices de inicio y fin.
% Descripción:
%   Extrae una sublista (llamando a extraer_sublista) dentro del rango especificado
%   y verifica si es un palíndromo.
% Retorno:
%   - true: Si la sublista extraída es igual a su reversa.
%   - false: Si no es un palíndromo o hubo algpun error tanto de
%            indices o de cualquier tipo.

% Implementación:
esPalindroma(Lista, Rango) :-
    Rango = [L, R],
    extraer_sublista(Lista, L, R, Sublista),
    Sublista \= [],
    writeln(Sublista),  % Ejemplo: imprimir la sublista
    reverse(Sublista, SublistaReversa),
    Sublista = SublistaReversa.

