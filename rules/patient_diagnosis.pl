
% patient_wbc(Id, value),  Id - patient id, value - possible value of white blood cells are leukocitozis, leukopenia
patient_wbc(1, leukopenia).

find_diagnosis(Id, wbc, influenza_virus) :- patient_wbc(Id, leukopenia).
find_diagnosis(Id, wbc, sepsis) :- patient_wbc(Id, leukopenia).


% patient_rbc(Id, value),  Id - patient id, value - possible value of red white blood cells are anemia, eritrocitozis 
patient_rbc(1, anemia).

find_diagnosis(Id, rbc, pneumonia) :- patient_rbc(Id, anemia).
find_diagnosis(Id, rbc, copd) :- patient_rbc(Id, eritrocitozis).


% ct scan radiographic analyse

% patient_radiographic(Id, diagnose) Id - patient id, diagnose - diagnose based on radiographic image
patient_radiographic(1, pneumonia).

% radiogaphic scan can prove pneumonia, lung_cancer, copd, asthma
radiographic_patologic_result(pneumonia).
radiographic_patologic_result(lung_cancer).
radiographic_patologic_result(copd).
radiographic_patologic_result(pleural_efusion).


find_diagnosis(Id, radiographic, D) :- patient_radiographic(Id, D), radiographic_patologic_result(D).
