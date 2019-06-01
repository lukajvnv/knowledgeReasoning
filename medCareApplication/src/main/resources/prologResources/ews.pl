% udisaj          ref. vrednost, bodovi, [udisaj/min]

udisaji_bodovi(Vrednost, 0) :- Vrednost >= 12, Vrednost =< 20, !.   
udisaji_bodovi(Vrednost, 1) :- Vrednost >= 9, Vrednost =< 11, !.
udisaji_bodovi(Vrednost, 2) :- Vrednost >= 21, Vrednost =< 24, !.
udisaji_bodovi(Vrednost, 3) :- Vrednost =< 8, !.
udisaji_bodovi(Vrednost, 3) :- Vrednost >= 25.

% zasicenje O2 ref. vrednost, bodovi, [%]
saturation_O2_bodovi(Vrednost, 0) :- Vrednost >= 96, !.   
saturation_O2_bodovi(Vrednost, 1) :- Vrednost >= 94, Vrednost< 96, !.
saturation_O2_bodovi(Vrednost, 2) :- Vrednost > 91, Vrednost< 94, !.
saturation_O2_bodovi(Vrednost, 3) :- Vrednost =< 91.

% sistolni pritisak          ref. vrednost, bodovi, [mmHg]
sistolni_pritisak_bodovi(Vrednost, 0) :- Vrednost >= 110, Vrednost =< 249, !.   
sistolni_pritisak_bodovi(Vrednost, 1) :- Vrednost >=101, Vrednost =< 110, !.
sistolni_pritisak_bodovi(Vrednost, 1) :- Vrednost >= 250, !.
sistolni_pritisak_bodovi(Vrednost, 2) :- Vrednost >= 91, Vrednost =< 100, !.
sistolni_pritisak_bodovi(Vrednost, 3) :- Vrednost =< 90.

% br. otkucaja         ref. vrednost, bodovi, [bpm]
otkucaji_bodovi(Vrednost, 0) :- Vrednost >= 51, Vrednost =< 90, !.   
otkucaji_bodovi(Vrednost, 1) :- Vrednost >= 41, Vrednost < 51, !.
otkucaji_bodovi(Vrednost, 1) :- Vrednost >= 91, Vrednost < 110, !.
otkucaji_bodovi(Vrednost, 2) :- Vrednost < 41, !.
otkucaji_bodovi(Vrednost, 2) :- Vrednost >= 110, Vrednost< 131, !.
otkucaji_bodovi(Vrednost, 3) :- Vrednost > 131.

% temperatura ref. vrednost, bodovi, [C]
temperatura_bodovi(Vrednost, 0) :- Vrednost >= 12, Vrednost =< 20, !.   
temperatura_bodovi(Vrednost, 1) :- Vrednost == 36, !.
temperatura_bodovi(Vrednost, 1) :- Vrednost == 38, !.
temperatura_bodovi(Vrednost, 2) :- Vrednost == 35, !.
temperatura_bodovi(Vrednost, 2) :- Vrednost == 39, !.
temperatura_bodovi(Vrednost, 3) :- Vrednost < 35.
temperatura_bodovi(Vrednost, 3) :- Vrednost >= 40.

% AUPU, nivo svesnosti         ref. vrednost (jedinica je skala: A-budan, V-odziva se na poziv, P-reaguje samo na dodir/bol, U-bez svesti) , bodovi
aupu_bodovi(Vrednost, 0) :- Vrednost == 'A', !. 
aupu_bodovi(Vrednost, 1) :-  Vrednost == 'B', !.
aupu_bodovi(Vrednost, 2) :-  Vrednost == 'C', !.
aupu_bodovi(Vrednost, 3) :-  Vrednost == 'D', !.

ukupno_bodova(V1, V2, V3, V4, V5, V6, Bod) :- udisaji_bodovi(V1, B1), saturation_O2_bodovi(V2, B2), sistolni_pritisak_bodovi(V3, B3), otkucaji_bodovi(V4, B4), temperatura_bodovi(V5, B5), aupu_bodovi(V6, B6), Bod is B1 + B2 + B3 + B4+ B5 + B6.  

stanje(Bodovi) :- Bodovi >= 1, Bodovi < 4, write(" merenje 4 puta dnevno"), nl.
stanje(Bodovi) :- Bodovi >= 5, Bodovi< 7, write(" na 1h zvati doktora "), nl.
stanje(Bodovi) :- Bodovi >= 7, write(" prebaciti u jedinicu intenzivne nege "), nl.

