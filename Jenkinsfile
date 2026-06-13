pipeline {
	agent any
	
	triggers {
		pollSCM('H/2 * * * *')
	}
	stages {
		stage('Checkout') {
			steps {
				git branch : 'main', url: 'https://github.com/harish-bendale/spring-boot-ci-cd-pipeline.git'
			}
		}
		stage('Build JAR') {
			steps {
				sh './mvnw clean package -DskipTests'
			}
		}
		stage('Build Docker Image') {
			steps {
				sh '/usr/local/bin/docker compose build'
			}
		}
		
		stage('Deploy application') {
			steps {
				sh '/usr/local/bin/docker compose down'
				sh '/usr/local/bin/docker compose up -d'
			}
		}
	}
}