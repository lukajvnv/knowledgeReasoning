% patient_fever(Id). Id - patient id
patient_fever(1).

% patient_respiratory_sound(Id, value). Id - patient id, value - possible value: {regular, patologic}
patient_respiratory_sound(1, patologic).

% patient_respiratory_noise(Id, value). Id - patient id, value - possible value: {puff, whistle}
patient_respiratory_noise(1, whistle).
patient_respiratory_noise(2, puff).

% patient_early_anamnesis(Id, type). Id  - patient id, type - type of early anamnesis, diagnosis in other words
% patient_early_anamnesis(1, pleural_efusion).


recomended_procedures(Id, cbc) :- patient_respiratory_noise(Id, puff).
recomended_procedures(Id, x_ray) :- patient_respiratory_noise(Id, puff).

recomended_procedures(Id, blood_gas_test) :- patient_respiratory_noise(Id, whistle).
recomended_procedures(Id, spirometry) :- patient_respiratory_noise(Id, whistle).

% recomended_procedures(Id, radiographic) :- patient_early_anamnesis(Id, pleural_efusion).
