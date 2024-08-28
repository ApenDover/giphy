pipeline {
    agent any

    stages {
        stage('Build') {
            steps {
                // Сборка проекта с использованием Gradle
                sh './gradlew build'
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