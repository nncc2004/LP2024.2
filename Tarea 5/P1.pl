% Caso base: si A es mayor que B, la sublista es vacía.
extraer_sublista(_, A, B, []) :- A > B.

% En el caso de que A sea 1, con recursión se va recorriendo y reduciendo B hasta que sea 1.
extraer_sublista([Cabeza|Cola], 1, B, [Cabeza|Sublista]) :-
    B > 0,
    B1 is B - 1,
    extraer_sublista(Cola, 1, B1, Sublista).

% En el caso de que A sea mayor de 1, se va extrayendo de la lista hasta que lo sea.
extraer_sublista([_|Cola], A, B, Sublista) :-
    A > 1,
    A1 is A - 1,
    B1 is B - 1,
    extraer_sublista(Cola, A1, B1, Sublista).

esPalindroma(Lista, Rango) :-
    Rango = [L, R],
    extraer_sublista(Lista, L, R, Sublista),
    Sublista \= [],
    writeln(Sublista),  % Ejemplo: imprimir la sublista
    reverse(Sublista, SublistaReversa),
    Sublista = SublistaReversa.

