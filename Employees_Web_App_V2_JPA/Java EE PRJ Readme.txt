Marine Chauveau
François Gillioen
Marc Guicheau


---------------------------------------------------------------------------------------------------
README File

################################# Lien du projet ####################################
https://gitlab.com/Doratik/jee---employees-management-application


################################ Etat du rendu ########################################

V1 : Full
V2 : Full
V3 : Sonarqube (external install and server logs) along with Junit (getters setters + EJBs support),
Restful application. Sonarqube code recommendations were followed.

################Activer les tests junit de la V3 sur les ejbs ############################################
Déplacer le fichier domain.xml dans  /org/glassfish/extras/glassfish-embedded-all/3.1.1/glassfish-embedded-all-3.1.1.jar/org/glassfish/embed
et remplacer le fichier domain.xml existant avec le fichier domain fourni(à la racine du projet Employees_Web_App_V3_REST) pour que les tests utilisent mysql et les bons connexion pool
(winrar est conseillé pour ouvrir l'archive glassfish-embedded-all-3.1.1.jar) 

########################## Activer la V3 ############################
1) Créer une nouvelle connexion mysql (version 5.1.X mais  pas 8+) dans services->databases
Host : localhost , Port : 3306  , Username : jee, Password : jee Database : jeeprj
URL : jdbc:mysql://localhost:3306/jeeprj?zeroDateTimeBehavior=convertToNull

2) Cliquer sur Services --> Servers --> GlassFish Server
si le serveur est stoppé : Démarrer le serveur glassfish avec clic droit -> Start
Glassfish Server  --> View Domain Admin Console

Vous arrivez sur une page

Vous arrivez sur une page d'administration, dans l'onglet à gauche cliquer sur 'Resources' --> 'Add Resources'
Location XML File to be Uploaded to the server --> cliquer sur Browse
Indiquer le chemin du fichier glassfish-resources.xml (crée grâce à l'étape 2) cliquer sur ok
Test : retourner dans resources->JDBC->connection pools cliquer sur emp_connectionPool et ping

 

##################################### Utiliser sonarqube avec le projet ###########################
S'assurer que Maven 3+ est installé 
Démarrer le serveur en cliquant ici -->  (Chemin que vous avez choisi d'extraire sonarqube)\sonarqube-8.0\bin\windows-x86-64\StartSonar.bat
taper mvn sonar:sonar


Utilisations externes : 
sonarqube 8.0 with JDK 11 and not JDK 13
Maven 3+