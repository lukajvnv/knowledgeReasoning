% patient_personal_anamnesis(Id, type). Id - patient id, type - type of personal anamnesis
patient_personal_anamnesis(2, hypertension).
patient_personal_anamnesis(2, hyperlipidemia).

patient_personal_anamnesis_smoking(smoking).
patient_personal_anamnesis_alcohol(alcohol).

% patient_professional_anamnesis(Id, type). Id  - patient id, type - type of professional anamnesis

% patient_socioeconomic_anamnesis(Id, type). Id  - patient id, type - type of socioeconomic anamnesis
patient_socioeconomic_anamnesis(1, pet).
patient_socioeconomic_anamnesis(1, flat).
patient_socioeconomic_anamnesis(1, city).
patient_socioeconomic_anamnesis(2, house).

% patient_early_anamnesis(Id, type). Id  - patient id, type - type of early anamnesis, diagnosis in other words
patient_early_anamnesis(1, copd).
patient_early_anamnesis_copd(copd).  
patient_early_anamnesis_asthma(asthma). 


% patient_family_anamnesis(Id, type). Id  - patient id, type - type of family anamnesis
patient_family_anamnesis(1, cancer).
patient_family_anamnesis_cancer(cancer).
patient_family_anamnesis_alergy(alergy).
patient_family_anamnesis_asthma(asthma).

older_patient(Age) :- Age > 60. 
younger_patient(Age) :- Age < 25. 


% preventive_examination(Age, PA, EA, FA, Type).
% Age - patient's age, PA - PersonalAnamnesis, EA - EarlyAnamnesis - patient history of diagnosis, FA - FamilyAnamnesis,  type - type of preventive examination,  

preventive_examination(Age, PA,  _EA, FA, x_ray) :- patient_family_anamnesis_cancer(FA), patient_personal_anamnesis_smoking(PA), older_patient(Age).
preventive_examination(_Age, _PA, EA, _FA, influenza_vaccination) :- patient_early_anamnesis_copd(EA).
preventive_examination(_Age, _PA, EA, _FA, streptococcus_pneumoniae_vaccination) :- patient_early_anamnesis_copd(EA).
preventive_examination(_Age, _PA, EA, _FA, correct_diet) :- patient_early_anamnesis_copd(EA).
preventive_examination(_Age, _PA, EA, _FA, breathe_exercises) :- patient_early_anamnesis_copd(EA).
preventive_examination(_Age, PA, EA, _FA, smoking_rehab) :- patient_personal_anamnesis_smoking(PA), patient_early_anamnesis_copd(EA).
preventive_examination(_Age, PA, EA, _FA, spirometry) :- patient_early_anamnesis_asthma(EA), patient_personal_anamnesis_smoking(PA).
preventive_examination(_Age, PA, EA, _FA, spirometry) :- patient_early_anamnesis_copd(EA), patient_personal_anamnesis_smoking(PA).
preventive_examination(_Age, _PA, _EA, FA, spirometry) :- patient_family_anamnesis_asthma(FA).
preventive_examination(_Age, _PA, _EA, FA, spirometry) :- patient_family_anamnesis_alergy(FA).
