Marine Chauveau
François Gillioen
Marc Guicheau


---------------------------------------------------------------------------------------------------
README File

################################# Lien du projet ####################################
https://gitlab.com/Doratik/jee---employees-management-application


################################ Etat du rendu ########################################

V1 : Full
V2 : Full, exceptions management, page not found error management
V3 : Sonarqube (external install and server logs) along with Junit (getters setters + EJBs support),
Restful application. Sonarqube code recommendations were followed.

################Activer les tests junit de la V3 sur les ejbs ############################################

Le serveur embedded situé dans test dependencies ne possède pas les ressources pour pouvoir se
connecter à mysql à distance, pour cela : 


1) télécharger les dépendances Maven
2) Déplacer le fichier domain.xml dans  /org/glassfish/extras/glassfish-embedded-all/3.1.1/glassfish-embedded-all-3.1.1.jar/org/glassfish/embed
et remplacer le fichier domain.xml existant avec le fichier domain fourni(à la racine du projet Employees_Web_App_V3_REST) pour que les tests utilisent mysql et les bons connexion pool
(winrar est conseillé pour ouvrir l'archive glassfish-embedded-all-3.1.1.jar) 

########################## Activer la V3 REST ############################

Ajout de la ressource glassfish au serveur glassfish
----------------------------------------------------
La resource glassfish-resources.xml est déjà créee dans WEB-INF, il suffit de l'ajouter au serveur, pour cela: 

a) Cliquer sur Services --> Servers --> GlassFish Server
Si le serveur est éteint : Démarrer le serveur glassfish avec clic droit -> Start
b) Glassfish Server  --> View Domain Admin Console

c) Vous arrivez sur une page d'administration, dans l'onglet à gauche cliquer sur 'Resources' --> 'Add Resources'
Location XML File to be Uploaded to the server --> cliquer sur Browse
d) Indiquer le chemin du fichier glassfish-resources.xml se trouvant dans WEB-INF puis cliquer sur ok

Test facultatif de la connexion à distance : retourner dans resources->JDBC->connection pools cliquer sur Az_connectionPool et sur 'ping'


##################################### Utiliser sonarqube avec le projet ###########################
S'assurer que Maven 3+ est installé 
Démarrer le serveur en cliquant ici -->  (Chemin que vous avez choisi d'extraire sonarqube)\sonarqube-8.0\bin\windows-x86-64\StartSonar.bat
taper mvn sonar:sonar


################################## Facultatif : tester la connexion à distance pour la V3 ###########
 Créer une nouvelle connexion mysql (version 5.1.X mais  pas 8+) dans services->databases(FACULTATIF)
-----------------------------------------------------------------------------------------------------
On utilise les données suivantes
Host : jeeprj.mysql.database.azure.com
Port : 3306
Username : jee@jeeprj
Password : Marechaldu94 
Database : jeeprj

URL : jdbc:mysql://jeeprj.mysql.database.azure.com:3306/jeeprj?useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&amp;serverTimezone=UTC

############################################   Utilisations externes ########################
sonarqube 8.0 with JDK 11 and not JDK 13
Maven 3+
MySql