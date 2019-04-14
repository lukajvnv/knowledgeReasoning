% symptom, related symptom, percentage

symptom_related_to(congestion_in_chest, cough,			88).
symptom_related_to(congestion_in_chest, coryza,	  	                53).
symptom_related_to(congestion_in_chest, nasal_congestion,	51).
symptom_related_to(congestion_in_chest, fever,	  		48).
symptom_related_to(congestion_in_chest, sore_throat,	  	45).
symptom_related_to(congestion_in_chest, shortness_of_breath,	38).
symptom_related_to(congestion_in_chest, coughing_up_sputum,	30).
symptom_related_to(congestion_in_chest, sinus_congestion,	29).
symptom_related_to(congestion_in_chest, headeache,	  	27).
symptom_related_to(congestion_in_chest, frontal_headache,	 8).
symptom_related_to(congestion_in_chest, plugged_feeling_in_ear,     7).
symptom_related_to(congestion_in_chest, hoarse_voice,	  	 7).



symptom_related_to(cough, fever,			74).
symptom_related_to(cough, nasal_congestion,	74).
symptom_related_to(cough, sore_throat,		55).
symptom_related_to(cough, shortness_of_breath,	38).
symptom_related_to(cough, coryza	,	                32).
symptom_related_to(cough, vomiting,		31).
symptom_related_to(cough, wheezing,		31).
symptom_related_to(cough, ear_pain,		27).
symptom_related_to(cough, sharp_chest_pain,	26).
symptom_related_to(cough, headache,		26).
symptom_related_to(cough, difficulty_breathing,	21).
symptom_related_to(cough, congestion_in_chest,	16).



symptom_related_to(coughing_up_sputum, nasal_congestion,	63).
symptom_related_to(coughing_up_sputum, shortness_of_breath,       59).
symptom_related_to(coughing_up_sputum, sore_throat,   	                57).
symptom_related_to(coughing_up_sputum, fever,   		                53).
symptom_related_to(coughing_up_sputum, sharp_chest_pain,   	39).
symptom_related_to(coughing_up_sputum, cough,   		36).
symptom_related_to(coughing_up_sputum, headache,   		31).
symptom_related_to(coughing_up_sputum, congestion_in_chest,       29).
symptom_related_to(coughing_up_sputum, coryza,   		28).
symptom_related_to(coughing_up_sputum, ache_all_over,   	                27).
symptom_related_to(coughing_up_sputum, difficulty_breathing,            25).
symptom_related_to(coughing_up_sputum, wheezing,   		20).



symptom_related_to(fever, cough, 			79).
symptom_related_to(fever, sore_throat,    	                59).
symptom_related_to(fever, vomiting,    		55).
symptom_related_to(fever, nasal_congestion,    	52).
symptom_related_to(fever, headache,    		37).
symptom_related_to(fever, diarrhea,    		31).
symptom_related_to(fever, ear_pain,    		28).
symptom_related_to(fever, chills,    		28).
symptom_related_to(fever, coryza,    	                21).
symptom_related_to(fever, decreased_appetite,    	16).
symptom_related_to(fever, pulling_at_ears,    	12).
symptom_related_to(fever, irritable_infant,    	7).



symptom_related_to(hemopthysis, cough, 			52).
symptom_related_to(hemopthysis, shortness_of_breath, 	45).
symptom_related_to(hemopthysis, sore_throat, 		44).
symptom_related_to(hemopthysis, fever, 			31).
symptom_related_to(hemopthysis, coughing_up_sputum, 	29).
symptom_related_to(hemopthysis, sharp_chest_pain, 	29).
symptom_related_to(hemopthysis, nosebleed, 		21).
symptom_related_to(hemopthysis, feeling_ill, 		13).
symptom_related_to(hemopthysis, rib_pain, 		10).
symptom_related_to(hemopthysis, congestion_in_chest, 	10).
symptom_related_to(hemopthysis, blood_in_stool,   	 8).
symptom_related_to(hemopthysis, lump_in_throat,                  	 8).



symptom_related_to(shortness_of_breath, radiographic,      	100).
symptom_related_to(shortness_of_breath, blood_test,     	91.56).
symptom_related_to(shortness_of_breath, x_ray,       	80.01).
symptom_related_to(shortness_of_breath, cbc,          	78.37).
symptom_related_to(shortness_of_breath, ecg,         	60.19).
symptom_related_to(shortness_of_breath, kidney_test,     	19.73).
symptom_related_to(shortness_of_breath, glucose_level,     	12.98).
symptom_related_to(shortness_of_breath, electrolytes_panel, 10.00).

% -------------------------------------------------------------------------------------------------
% tight relations between symptoms
% symptom, tight related symptom
symptom_strong_predictor(congestion_in_chest, coughing_up_sputum).
symptom_strong_predictor(congestion_in_chest, sinus_congestion).


symptom_strong_predictor(cough, nasal_congestion).
symptom_strong_predictor(cough, congestion_in_chest).


symptom_strong_predictor(coughing_up_sputum, congestion_in_chest).


symptom_strong_predictor(fever, chills).


symptom_strong_predictor(hemopthysis, coughing_up_sputum).  
symptom_strong_predictor(hemopthysis, lump_in_throat). 

% ---------------------------------------------------------------------------------------------------
% find potential symptoms while input is some established symptom

potential_symptom(Sym, Pot_sym) :- symptom_strong_predictor(Sym, Pot_sym), symptom_related_to(Sym, Pot_sym, _). 
