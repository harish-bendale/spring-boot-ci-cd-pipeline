pipeline {
	agent any
	
	triggers {
		pollSCM('H/2 * * * *')
	}
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
		stage('Verify commit') {
			steps {
				sh 'git log -1 --oneline'
			}
		}
		stage('Build JAR') {
			steps {
				sh './mvnw clean package -DskipTests'
			}
		}
		stage('Verify JAR') {
			steps {
				sh 'ls -ltr target/'
			}
		}
		stage('Build Docker Image') {
			steps {
				sh '/usr/local/bin/docker compose build --no-cache'
			}
		}
		
		stage('Deploy application') {
			steps {
				sh '/usr/local/bin/docker compose down'
				sh '/usr/local/bin/docker compose up -d --force-recreate'
			}
		}
	}
}