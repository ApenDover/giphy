pipeline {
    agent any

    environment {
            REMOTE_SERVER = 'root@ts.andrey.o.fvds.ru'
            SSH_CREDENTIALS_ID = '0ba5bed8-ecae-4c36-b781-304d6b40d838'
            REMOTE_PATH = '~/jarFiles'
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
                        sh 'scp ./build/libs/giphy-0.0.1.jar ${REMOTE_SERVER}:${REMOTE_PATH}'
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