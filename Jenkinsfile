pipeline {
    agent any

    environment {
            REMOTE_SERVER = 'root@ts.andrey.o.fvds.ru'
            SSH_CREDENTIALS_ID = '0ba5bed8-ecae-4c36-b781-304d6b40d838'
        }

    stages {
        stage('Checkout') {
            steps {
                checkout scm
            }
        }

        stage('Build') {
            steps {
                sh './gradlew build'
            }
        }

        stage('Deploy') {
            steps {
                sshagent([SSH_CREDENTIALS_ID]) {
                    sh 'ssh ${REMOTE_SERVER} "docker compose down"'
                    sh 'scp ./build/libs/giphy-0.0.1.jar ${REMOTE_SERVER}:~/giphy.jar'
                    sh 'scp ./Dockerfile.prod ${REMOTE_SERVER}:~/Dockerfile'
                    sh 'scp ./docker-compose.yml ${REMOTE_SERVER}:~'
                    sh 'ssh ${REMOTE_SERVER} "docker compose build"'
                    sh 'ssh ${REMOTE_SERVER} "docker compose up -d"'
                }
            }
        }

    }

    post {
        cleanup {
            // Очистка временных файлов или других ресурсов после выполнения pipeline
            sh './gradlew clean'
        }
    }
}