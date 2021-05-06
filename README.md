# knowledgeReasoning

Repozitorijum projekta u okviru predmeta Inženjering znanja. Knowledge reasoning aplikacija namenjena je podršci lekarima specijalistima u postupku lečenja pacijenta. Aplikacija osim što omogućava evidenciju pregleda i praćenje stanje pacijenta, pruža i pomoć u predviđanju narednih koraka u postupku njegovog lečenja.

## O samoj aplikaciji

Aplikacija je Spring Boot projekat implementirana u Javi prilagođena da bude desktop gui aplikacija. Razvijena je u programskom razvojnom okruženju Eclipse. Za bazu podataka korišćena je MySql baza. Biblioteke koje su korišćene u razvoju rešenja su jiprolog, unbbayes, jColibri i jena grupa biblioteka.

### Uputstvo za pokretanje aplikacije
_Klonirajte repozitorijum. Uvucite Maven projekat u Eclipse razvojno okruženje. Obezbedite da biblioteke u direktorijumima lib i lib_rdf budu uključene u java build path. Za potrebe čuvanja podataka potrebno je obezbediti MySql bazu, a svoje konekcione parametre neophodno je podesiti u application.properties fajlu u src/main/resources. Nakon komande mvn install, pokrenite aplikaciju._

### Demo
https://user-images.githubusercontent.com/44602021/117358601-a591aa80-aeb6-11eb-86a2-a00c01104712.mp4
