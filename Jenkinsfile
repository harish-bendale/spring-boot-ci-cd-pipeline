pipeline {
	agent any
	
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
				sh 'dockker compose build'
			}
		}
		
		stage('Deploy application') {
			steps {
				sh 'docker compose down'
				sh 'docker compose up'
			}
		}
	}
}