/* Définition des variables utilisées */
// version de maven utilisée, ainsi que son jdk
def maven = docker.image('maven:3.3.3-jdk-8');
// nom de l'application
def application='easyFactory';
//nom des différentes branches sur le git
def gitflow_develop = 'develop'
def gitflow_release = 'release'
def gitflow_master = 'master'
//récupération des résultats des tests surefire sous junit
def maven_tests_reports = false
//URL du serveur sonar
def sonarServerUrl = 'http://192.168.4.248:8085'

/* Première étape : 
	Récupération et clonage du dépot git
	Récupération de l'image docker de maven
*/
stage 'Checkout & Prepare'
	node ('slave2') {
		parallel 'checkout': {
			//branch = branche du git sélectionné
			//credentialsId = identifiants du git enregistrés sur le jenkins
			//url = URL du dépot git
			git branch: 'master', credentialsId: 'root', url: 'http://192.168.4.248:8083/root/'+application+'.git'
		}, 'maven': { 
		    maven.pull()
		}
	}

/* Deuxième étape : 
	Maven lance un build du projet
*/
stage 'Build'
	node ('slave2') {
		docker.withRegistry('https://hub.docker.com/') {
			maven.inside {
				try {
					sh 'mvn -Dmaven.test.failure.ignore=true -e clean install'
				} finally {
					//Si les résultats des tests JUnit sont demandés, il va envoyer les résultats dans le dossier spécifié
					if (maven_tests_reports) {
						step([$class: 'JUnitResultArchiver', testResults: '**/target/surefire-reports/TEST-*.xml'])
					}
				}
			}
			//Les tests ne seront pas exécutés sur les fichiers du dossier target, ni logs, mais seront exécutés sur les fichiers du dossier source
			stash excludes: '*/target, logs', includes: '**', name: 'source'
		}
		// a supprimer ?
		if (env.BRANCH_NAME==gitflow_master) {
			sh 'docker build -f '+application+'-docker/Dockerfile-web -t eseo-'+application+'-web:$BRANCH_NAME --no-cache .'
		}
	}

/* Troisième étape : 
	Analyse de la qualité des fichiers source via maven
	Utilisation des plugins cobertura et sonar de maven
*/
stage 'Quality'
	node ('slave2') {
		unstash 'source'
		docker.withRegistry('https://hub.docker.com/') {
		   	maven.inside() {
		   		sh 'mvn -Dsonar.branch=master -Dsonar.host.url='+sonarServerUrl+' -Dcobertura.report.format=xml -e cobertura:cobertura sonar:sonar'
			} 
		}
	}

/* Quatrième étape :
	Compilation du projet (fichiers sources) en un fichier .war
	Si besoin, le .jar est possible aussi, il faut juste le spécifier dans le pom.xml
*/
stage 'Packaging'
	node ('master') {
		unstash 'source'
		archive '*/target/*.war'
	}

/* Cinquième étape :
	Déploiement de l'application sur un serveur Tomcat (ou autre si besoin)
	Le fichier docker-compose doit être mis dans le dépot git
*/
stage 'Deploy'
	node ('master'){
		docker.withRegistry('https://hub.docker.com/') {
			sh 'docker-compose -H tcp://192.168.4.248:2375 -f '+application+'-docker/docker-compose.yml down'
			sh 'docker-compose -H tcp://192.168.4.248:2375 -f '+application+'-docker/docker-compose.yml up -d'
		}
	}