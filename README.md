# Backend Docapp

## In breve


Questa repository rappresenta il back-end dell'applicazione Docapp creata per l'esame di Ingegneria del software, all'interno
della repository si può trovare la cartella tests, in cui sono presenti i testing di unità effettuati con Junit e Mockito. Sono presenti le cartelle
contenenti i file rappresentanti la logica dell'applicazione ed il lato di persistenza dei dati; gestiti tramite springboot, nascondendo il lato implementativo e fornendo maggiore sicurezza al sistema.
In riferimento alla documentazione di Ingegneria del Software, questa repository rappresenta la rappresentazione grafica del packaging, in cui sono definite le stesse classi che sono state trascritte nell'ODD; nella stessa repository, per di più, sono presenti le interfacce repository raffiguranti il design pattern utilizzato nel nostro sistema (Repository Pattern). Oltre gli elementi sopracitati sono anche presenti la connessione con il server effettuata per permettere le operazioni di back-end nell'applicazione


### Di seguito il packaging dei models
![image](https://user-images.githubusercontent.com/80829428/221190540-bcce36d1-9345-4ebc-8eba-38b60f280f1b.png)


### Di seguito il packaging dei controllers
![image](https://user-images.githubusercontent.com/80829428/221190604-5023965d-97f5-4484-8479-c1c7e22a6bd5.png)


### Le richieste passano tutte tramite il servizio di package security.
![image](https://user-images.githubusercontent.com/80829428/221190667-a72c87f7-4af0-4a5f-9cf3-817e279505bd.png)


### Le richieste
![image](https://user-images.githubusercontent.com/80829428/221190831-4af17af0-4057-4ce6-b05b-a1a96fd76bbf.png)


Le richieste vengono fatte tramite un servizio di access Token.
La sessione dura:
tuttavia si potrebbe estendere aggiungendo un servizio di Refresh Token
![image](https://user-images.githubusercontent.com/80829428/221190866-6b8a6fe2-5559-43d6-9abd-8f6536fba660.png)




Una richiesta di autenticazione invece risulterà in:
![image](https://user-images.githubusercontent.com/80829428/221190918-8635eda2-e440-494c-912d-adb01234d13a.png)
