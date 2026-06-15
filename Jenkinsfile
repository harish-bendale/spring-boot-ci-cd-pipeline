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
		stage('Unit Tests') {
			steps {
				sh './mvnw clean test'
			}
			post {
				always {
					junit 'target/surefire-reports/*.xml'
				}
			}
		}
		stage('Integration Tests') {
			steps {
				sh './mvnw verify'
			}
			post {
				always {
					junit 'target/failsafe-reports/*.xml'
				}
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
	
	post {
		success {
			echo 'Pipeline completed successfully.'
		}
		
		failure {
			echo 'Pipeline failed.'
		}
		
		always {
			archiveArtifacts artifacts: 'target/*.jar', fingerprint: true
		}
	}
}