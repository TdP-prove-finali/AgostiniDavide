# Simulazione per trasporto di merci su gomma su scala regionale.

## Istruzioni per l'installazione e l'utilizzo dell'applicazione
1. Forkare e clonare sul proprio editor (IDE testato: Eclipse) il progetto al link: https://github.com/TdP-prove-finali/AgostiniDavide.
2. Modificare all'interno della classe DBConnect, package db, la password di accesso al database, inserendo la propria.
3. Creare il database con le istruzioni riportate qui sotto.
4. Aprire il progetto ed eseguire la classe Main
5. Per una breve guida introduttiva, guarda il video al link: https://youtu.be/MHyL_SAqDWc

### Istruzioni per la creazione del database
Data l'enorme quantità di dati presenti nel data-set github ha impedito il caricamento del file sql completo e perciò tale operazione richiede lo svolgimento dei passaggi sotto elencati.
1. Creare sul proprio server locale il database con nome "comuni".
2. Importare tutte le tabelle presenti nella cartella "DB" al link: https://github.com/TdP-prove-finali/AgostiniDavide/tree/master/DB. \
Le varie tabelle sono riportate in file .zip e ciascuna contiene il file .sql che si richiede di importare.
3. Data la grande quantità di casi talvolta può impiegare troppo tempo o il processo di importazione mostra dei problemi.\
Se il problema persiste e le tabelle risultano essere state create, ma vuote allora assicurarsi di svuotarle.\
Nel caso in cui le tabelle non siano state create allora crearle tutte manualmente con i nomi dei file sql.
4. Successivamente accedere al link dropbox: https://www.dropbox.com/sh/5imnidtgxesvh2j/AACDecSIiVGNmNo5eka_Kju1a?dl=0. \
Nel link sono presenti file .csv delle varie regioni che bisogna importare nelle rispettive tabelle. \
(E' inoltre presente nel link il file .sql completo che richiede un enorme quantità di tempo per essere scaricato e può provocare gli errori prima descritti.)
5. Importare i file .csv nelle rispettive tabelle con le seguenti specifiche (dovrebbe essere automatico):
* Codifica UTF-8 Unicode.
* Ignorare le prime 1 linee.
* Campi terminati da ;
* Campi racchiusi tra "
* Carattere di escape "
* Linee terminate tra \r\n

