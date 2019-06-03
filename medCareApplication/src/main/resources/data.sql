insert into student (student_id, student_age, student_name) values (1, 21, 'marko');


/*Pacijent*/
insert into patient (Id_Pacijenta, Ime, Prezime, Jmbg, Telefon, Datum_rodjenja, Adresa)
values (1, 'Luka', 'Jovanovic', '2903996800005', '064/449-86-28', '29.03.1996.', 'Drage Spasic 7');
insert into patient (Id_Pacijenta, Ime, Prezime, Jmbg, Telefon, Datum_rodjenja, Adresa) 
values (2, 'Scepan', 'Scekic', '1234996800005', '064/449-99-28', '05.03.1966.', 'Lovcenska 12');
insert into patient (Id_Pacijenta, Ime, Prezime, Jmbg, Telefon, Datum_rodjenja, Adresa) 
values (3, 'Mitar', 'Miric', '0703996800005', '064/555-86-28', '06.03.1980.', 'Bulevar revolucije 5');


/*Fizikalni pregled*/
insert into physical_examination (physical_Examination_Id, Id_Pacijenta, Temperatura, Disajni_zvuk, Sumovi)
values (1, 1, 'Normalna', 'Regularni', 'Zvizduci');
insert into physical_examination (physical_Examination_Id, Id_Pacijenta, Temperatura, Disajni_zvuk, Sumovi)
values (2, 2, 'Snizena', 'Patoloski', 'Pukoti');
insert into physical_examination (physical_Examination_Id, Id_Pacijenta, Temperatura, Disajni_zvuk, Sumovi)
values (3, 3, 'Povisena', 'Patoloski', 'Normalan');

/*Anamneza*/
insert into anamnesis (anamnesis_id, Id_Pacijenta, Pusenje, Alkohol, Stanje, Tezina, Zivi, Stanuje, Ljubimci,dopunska_ispitivanja)
values (1, 1, 'Ne', 'Ne', 'Nezaposlen', 'Fizicki lak posao', 'Grad', 'Stan', 'Ne', 'UZ_PLUCNE_MARAMICE');
insert into anamnesis (anamnesis_id, Id_Pacijenta, Pusenje, Alkohol, Stanje, Tezina, Zivi, Stanuje, Ljubimci,dopunska_ispitivanja)
values (2, 3, 'Da', 'Da', 'Zaposlen', 'Fizicki tezak posao', 'Grad', 'Kuca', 'Da', 'KRVNA_SLIKA');
insert into anamnesis (anamnesis_id, Id_Pacijenta, Pusenje, Alkohol, Stanje, Tezina, Zivi, Stanuje, Ljubimci,dopunska_ispitivanja)
values (3, 2, 'Ne', 'Ne', 'Nezaposlen', 'Fizicki lak posao', 'Grad', 'Stan', 'Ne', 'KRVNA_SLIKA');
insert into anamnesis (anamnesis_id, Id_Pacijenta, Pusenje, Alkohol, Stanje, Tezina, Zivi, Stanuje, Ljubimci,dopunska_ispitivanja)
values (4, 3, 'Ne', 'Ne', 'Nezaposlen', 'Fizicki lak posao', 'Grad', 'Kuca', 'Da', 'KRVNA_SLIKA');
/*insert into anamnesis (anamnesis_id, Id_Pacijenta, Pusenje, Alkohol, Stanje, Tezina, Zivi, Stanuje, Ljubimci)
values (4, 3, 'Ne', 'Ne', 'Nezaposlen', 'Fizicki lak posao', 'Grad', 'Stan', 'Da');
insert into anamnesis (anamnesis_id, Id_Pacijenta, Pusenje, Alkohol, Stanje, Tezina, Zivi, Stanuje, Ljubimci)
values (5, 1, 'Ne', 'Ne', 'Nezaposlen', 'Fizicki lak posao', 'Grad', 'Stan', 'Da');
insert into anamnesis (anamnesis_id, Id_Pacijenta, Pusenje, Alkohol, Stanje, Tezina, Zivi, Stanuje, Ljubimci)
values (6, 1, 'Da', 'Da', 'Zaposlen', 'Fizicki lak posao', 'Grad', 'Stan', 'Da');*/

/*Svi relevantni podaci*/
	/*Simptomi*/
insert into resources (resource_id, resource_name, resource_type) values (1, 'congestion_in_chest', 'symptom');
insert into resources (resource_id, resource_name, resource_type) values (2, 'cough', 'symptom');
insert into resources (resource_id, resource_name, resource_type) values (3, 'coughing_up_sputum', 'symptom');
insert into resources (resource_id, resource_name, resource_type) values (4, 'fever', 'symptom');
insert into resources (resource_id, resource_name, resource_type) values (5, 'hemoptysis', 'symptom');
insert into resources (resource_id, resource_name, resource_type) values (6, 'shortness_of_breath', 'symptom');
insert into resources (resource_id, resource_name, resource_type) values (7, 'sinus_congestion', 'symptom');
insert into resources (resource_id, resource_name, resource_type) values (8, 'nasal_congestion', 'symptom');
insert into resources (resource_id, resource_name, resource_type) values (9, 'chills', 'symptom');
insert into resources (resource_id, resource_name, resource_type) values (10, 'hemopthysis', 'symptom');
insert into resources (resource_id, resource_name, resource_type) values (11, 'coryza', 'symptom');
insert into resources (resource_id, resource_name, resource_type) values (12, 'sore_throat', 'symptom');
insert into resources (resource_id, resource_name, resource_type) values (13, 'sharp_chest_pain', 'symptom');

	/* Procedure */
insert into resources (resource_id, resource_name, resource_type) values (14, 'cbc', 'procedure');
insert into resources (resource_id, resource_name, resource_type) values (15, 'x_ray', 'procedure');
insert into resources (resource_id, resource_name, resource_type) values (16, 'blood_gas_test', 'procedure');
insert into resources (resource_id, resource_name, resource_type) values (17, 'spirometry', 'procedure');
insert into resources (resource_id, resource_name, resource_type) values (18, 'nebulizer_therapy', 'procedure');
insert into resources (resource_id, resource_name, resource_type) values (19, 'other', 'procedure');
insert into resources (resource_id, resource_name, resource_type) values (20, 'influenzavirus', 'procedure');
insert into resources (resource_id, resource_name, resource_type) values (21, 'blood_culture', 'procedure');
insert into resources (resource_id, resource_name, resource_type) values (22, 'influenzavirus_antibody_assay', 'procedure');
insert into resources (resource_id, resource_name, resource_type) values (23, 'bronchoscopy', 'procedure');
insert into resources (resource_id, resource_name, resource_type) values (24, 'ecg', 'procedure');
insert into resources (resource_id, resource_name, resource_type) values (25, 'radiographic', 'procedure');
insert into resources (resource_id, resource_name, resource_type) values (26, 'kidney_function_test', 'procedure');
insert into resources (resource_id, resource_name, resource_type) values (27, 'urinalysis', 'procedure');
insert into resources (resource_id, resource_name, resource_type) values (28, 'intravenous_fluid_replacment', 'procedure');
insert into resources (resource_id, resource_name, resource_type) values (29, 'glucose_level', 'procedure');
insert into resources (resource_id, resource_name, resource_type) values (30, 'electrolytes_panel', 'procedure');
insert into resources (resource_id, resource_name, resource_type) values (31, 'blood_test', 'procedure');
insert into resources (resource_id, resource_name, resource_type) values (32, 'influenza_vaccination', 'procedure');
insert into resources (resource_id, resource_name, resource_type) values (33, 'streptococcus_pneumoniae_vaccination', 'procedure');
insert into resources (resource_id, resource_name, resource_type) values (34, 'correct_diet', 'procedure');
insert into resources (resource_id, resource_name, resource_type) values (35, 'breathe_exercises', 'procedure');
insert into resources (resource_id, resource_name, resource_type) values (36, 'smoking_rehab', 'procedure');

	/* Dijagnoze */
insert into resources (resource_id, resource_name, resource_type) values (37, 'acute_bronchitis', 'diagnosis');
insert into resources (resource_id, resource_name, resource_type) values (38, 'acute_sinusitis', 'diagnosis');
insert into resources (resource_id, resource_name, resource_type) values (39, 'alergy', 'diagnosis');
insert into resources (resource_id, resource_name, resource_type) values (40, 'asthma', 'diagnosis');
insert into resources (resource_id, resource_name, resource_type) values (41, 'chronic_sinusitis', 'diagnosis');
insert into resources (resource_id, resource_name, resource_type) values (42, 'common_cold', 'diagnosis');
insert into resources (resource_id, resource_name, resource_type) values (43, 'copd', 'diagnosis');
insert into resources (resource_id, resource_name, resource_type) values (44, 'lung_cancer', 'diagnosis');
insert into resources (resource_id, resource_name, resource_type) values (45, 'pleural_efusion', 'diagnosis');
insert into resources (resource_id, resource_name, resource_type) values (46, 'pneumonia', 'diagnosis');
insert into resources (resource_id, resource_name, resource_type) values (47, 'pneumothorax', 'diagnosis');
insert into resources (resource_id, resource_name, resource_type) values (48, 'smoking_addiction', 'diagnosis');
insert into resources (resource_id, resource_name, resource_type) values (49, 'otitis_media', 'diagnosis');
insert into resources (resource_id, resource_name, resource_type) values (50, 'seasonal_allergies', 'diagnosis');
insert into resources (resource_id, resource_name, resource_type) values (52, 'flu', 'diagnosis');

	/* Terapija - lekovi */
insert into resources (resource_id, resource_name, resource_type) values (53, 'azithromycin', 'medicaments');
insert into resources (resource_id, resource_name, resource_type) values (54, 'mucinex', 'medicaments');
insert into resources (resource_id, resource_name, resource_type) values (55, 'ipratropium', 'medicaments');
insert into resources (resource_id, resource_name, resource_type) values (56, 'benzonatate', 'medicaments');
insert into resources (resource_id, resource_name, resource_type) values (57, 'robitussin_ac', 'medicaments');
insert into resources (resource_id, resource_name, resource_type) values (58, 'amoxicillin', 'medicaments');
insert into resources (resource_id, resource_name, resource_type) values (59, 'augmentin', 'medicaments');
insert into resources (resource_id, resource_name, resource_type) values (60, 'clarithromycin', 'medicaments');
insert into resources (resource_id, resource_name, resource_type) values (61, 'benadryl', 'medicaments');
insert into resources (resource_id, resource_name, resource_type) values (62, 'prednisone', 'medicaments');
insert into resources (resource_id, resource_name, resource_type) values (63, 'medrol', 'medicaments');
insert into resources (resource_id, resource_name, resource_type) values (64, 'famotidine', 'medicaments');
insert into resources (resource_id, resource_name, resource_type) values (65, 'hydroxyzine', 'medicaments');
insert into resources (resource_id, resource_name, resource_type) values (66, 'epinephrine', 'medicaments');
insert into resources (resource_id, resource_name, resource_type) values (67, 'zyrtec', 'medicaments');
insert into resources (resource_id, resource_name, resource_type) values (68, 'prednisolone', 'medicaments');
insert into resources (resource_id, resource_name, resource_type) values (69, 'albuterol', 'medicaments');
insert into resources (resource_id, resource_name, resource_type) values (70, 'singulair', 'medicaments');
insert into resources (resource_id, resource_name, resource_type) values (71, 'fluticasone_nasal_product', 'medicaments');
insert into resources (resource_id, resource_name, resource_type) values (72, 'budesonide', 'medicaments');
insert into resources (resource_id, resource_name, resource_type) values (73, 'xopenex', 'medicaments');
insert into resources (resource_id, resource_name, resource_type) values (74, 'combivent', 'medicaments');
insert into resources (resource_id, resource_name, resource_type) values (75, 'mometasone', 'medicaments');
insert into resources (resource_id, resource_name, resource_type) values (76, 'mucinex', 'medicaments');
insert into resources (resource_id, resource_name, resource_type) values (77, 'pcm_la', 'medicaments');
insert into resources (resource_id, resource_name, resource_type) values (78, 'robitussin_dm', 'medicaments');
insert into resources (resource_id, resource_name, resource_type) values (79, 'spiriva', 'medicaments');
insert into resources (resource_id, resource_name, resource_type) values (80, 'carboplatin', 'medicaments');
insert into resources (resource_id, resource_name, resource_type) values (81, 'dexamethasone_topical_product', 'medicaments');
insert into resources (resource_id, resource_name, resource_type) values (82, 'taxol', 'medicaments');
insert into resources (resource_id, resource_name, resource_type) values (83, 'compro', 'medicaments');
insert into resources (resource_id, resource_name, resource_type) values (84, 'cholecalciferol', 'medicaments');
insert into resources (resource_id, resource_name, resource_type) values (85, 'kayexalate', 'medicaments');
insert into resources (resource_id, resource_name, resource_type) values (86, 'arixtra', 'medicaments');
insert into resources (resource_id, resource_name, resource_type) values (87, 'fludrocortisone', 'medicaments');
insert into resources (resource_id, resource_name, resource_type) values (88, 'lidocaine_topical', 'medicaments');
insert into resources (resource_id, resource_name, resource_type) values (89, 'cefepime', 'medicaments');
insert into resources (resource_id, resource_name, resource_type) values (90, 'emend', 'medicaments');
insert into resources (resource_id, resource_name, resource_type) values (91, 'ceftriaxone', 'medicaments');
insert into resources (resource_id, resource_name, resource_type) values (92, 'levaquin', 'medicaments');
insert into resources (resource_id, resource_name, resource_type) values (93, 'avelox', 'medicaments');
insert into resources (resource_id, resource_name, resource_type) values (94, 'zosyn', 'medicaments');
insert into resources (resource_id, resource_name, resource_type) values (95, 'morphine', 'medicaments');
insert into resources (resource_id, resource_name, resource_type) values (96, 'fentanyl', 'medicaments');
insert into resources (resource_id, resource_name, resource_type) values (97, 'versed', 'medicaments');
insert into resources (resource_id, resource_name, resource_type) values (98, 'oxygen', 'medicaments');
insert into resources (resource_id, resource_name, resource_type) values (99, 'chantix', 'medicaments');
insert into resources (resource_id, resource_name, resource_type) values (100, 'nicotine', 'medicaments');