%------------------------------------------------------------------------------------
% Predicado: extraer_sublista(_, A, B, [])
% Par�metros:
%   - _: Lista completa (no se utiliza en este caso).
%   - A: �ndice inicial de la sublista.
%   - B: �ndice final de la sublista.
%   - []: Resultado vac�o en caso de que A > B.
% Descripci�n:
%   Caso base que termina la recursi�n cuando A > B, devolviendo una lista vac�a.
% Retorno:
%   - []: Si A > B, retorna una lista vac�a.

% Implementaci�n:
extraer_sublista(_, A, B, []) :- A > B.
%------------------------------------------------------------------------------------
%------------------------------------------------------------------------------------
% Predicado: extraer_sublista([Cabeza|Cola], 1, B, [Cabeza|Sublista])
% Par�metros:
%   - [Cabeza|Cola]: Lista original separada en cabeza y cola.
%   - 1: �ndice inicial (se ha llegado a la posici�n deseada).
%   - B: �ndice final para extraer.
%   - [Cabeza|Sublista]: Sublista acumulada.
% Descripci�n:
%   En el caso de que A sea 1,
%   se extrae elementos de la lista desde la posici�n inicial 1 hasta el �ndice B.
% Retorno:
%   - [Cabeza|Sublista]: Sublista extra�da entre los �ndices especificados.
%   - [] o false: Lista vacia en caso de algun error (Dependiendo del error podria ser false o [])

% Implementacion:
extraer_sublista([Cabeza|Cola], 1, B, [Cabeza|Sublista]) :-
    B > 0,
    B1 is B - 1,
    extraer_sublista(Cola, 1, B1, Sublista).

%------------------------------------------------------------------------------------
%------------------------------------------------------------------------------------
% Predicado: extraer_sublista([_|Cola], A, B, Sublista)
% Par�metros:
%   - [_|Cola]: Lista original ignorando la cabeza.
%   - A: �ndice inicial actual.
%   - B: �ndice final.
%   - Sublista: Sublista extra�da.
% Descripci�n:
%   En el caso de A distinto a 1,
%   avanza en la lista para reducir el �ndice inicial hasta llegar a 1.
% Retorno:
%   - Sublista: Sublista extra�da entre los �ndices especificados.
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
% Par�metros:
%   - Lista: Lista original sobre la cual se extrae la sublista.
%   - Rango: Lista con dos elementos [L, R], los �ndices de inicio y fin.
% Descripci�n:
%   Extrae una sublista (llamando a extraer_sublista) dentro del rango especificado
%   y verifica si es un pal�ndromo.
% Retorno:
%   - true: Si la sublista extra�da es igual a su reversa.
%   - false: Si no es un pal�ndromo o hubo algpun error tanto de
%            indices o de cualquier tipo.

% Implementaci�n:
esPalindroma(Lista, Rango) :-
    Rango = [L, R],
    extraer_sublista(Lista, L, R, Sublista),
    Sublista \= [],
    writeln(Sublista),  % Ejemplo: imprimir la sublista
    reverse(Sublista, SublistaReversa),
    Sublista = SublistaReversa.

