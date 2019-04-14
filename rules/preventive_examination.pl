% patient_personal_anamnesis(Id, type). Id - patient id, type - type of personal anamnesis
patient_personal_anamnesis(1, smoking).
patient_personal_anamnesis(1, alcohol).
patient_personal_anamnesis(2, hypertension).
patient_personal_anamnesis(2, hyperlipidemia).

% patient_professional_anamnesis(Id, type). Id  - patient id, type - type of professional anamnesis


% patient_socioeconomic_anamnesis(Id, type). Id  - patient id, type - type of socioeconomic anamnesis

patient_socioeconomic_anamnesis(1, pet).
patient_socioeconomic_anamnesis(1, flat).
patient_socioeconomic_anamnesis(1, city).
patient_socioeconomic_anamnesis(2, house).

% patient_early_anamnesis(Id, type). Id  - patient id, type - type of early anamnesis, diagnosis in other words
patient_early_anamnesis(1, copd). 

% patient_family_anamnesis(Id, type). Id  - patient id, type - type of family anamnesis
patient_family_anamnesis(1, cancer).


%patient_age(Id, age). Id - patient id, age - patient's age
patient_age(1, 67).

patient_older_than_60(Id) :- patient_age(Id, Age), Age > 60. 


% preventive_examination(Id, type). Id - patient id, type - type of preventive examination 
preventive_examination(Id, x_ray) :- patient_family_anamnesis(Id, cancer), patient_personal_anamnesis(Id, smoking), patient_older_than_60(Id).
preventive_examination(Id, influenza_vaccination) :-  patient_early_anamnesis(Id, copd).
preventive_examination(Id, streptococcus_pneumoniae_vaccination) :-  patient_early_anamnesis(Id, copd).
preventive_examination(Id, correct_diet) :-  patient_early_anamnesis(Id, copd).
preventive_examination(Id, breathe_exercises) :-  patient_early_anamnesis(Id, copd).
% preventive_examination(Id, smoking_rehab) :- patient_personal_anamnesis(Id, smoking).
preventive_examination(Id, smoking_rehab) :- patient_early_anamnesis(Id, copd), patient_personal_anamnesis(Id, smoking).
