pipeline {
	agent any
	
	stages {
		stage('Check Tools') {
			steps {
				sh '''
				whoami
				/usr/local/bin/docker --version
				/usr/local/bin/docker ps
				'''
			}
		}
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
				sh 'docker compose down'
				sh 'docker compose up'
			}
		}
	}
}