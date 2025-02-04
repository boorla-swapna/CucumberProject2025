pipeline {
    agent any
    stages {

 stage('Checkout') {
            steps {
                git branch: 'cucumberProject', url: 'https://github.com/boorla-swapna/CucumberProject2025.git'
            }
        }
        stage('Test') {
            steps {
                echo 'Testing...'
                bat 'mvn test'
            }
        }
    }
    post {
        always {
            echo 'Pipeline completed.'
        }
    }
}