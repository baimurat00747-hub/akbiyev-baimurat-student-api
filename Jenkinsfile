
pipeline {
    agent any

    options {
        timestamps()
    }

    environment {
        IMAGE_NAME = 'akbiyev-baimurat-student-api'
        IMAGE_TAG = 'v1.0-38150'
        STUDENT_NAME = 'Baimurat Akbiyev'
        STUDENT_ID = '38150'
    }

    stages {

        stage('Checkout') {
            steps {
                echo "Checkout for ${STUDENT_NAME}"
                checkout scm
            }
        }

        stage('Build') {
            steps {
                echo "Building project for ${STUDENT_NAME}"
                sh 'chmod +x ./gradlew'
                sh './gradlew clean compileJava --no-daemon'
            }
        }

        stage('Test') {
            steps {
                echo "Running tests for student ${STUDENT_ID}"
                sh './gradlew test --no-daemon'
            }
        }

        stage('Docker Build') {
            steps {
                echo "Building Docker image"
                sh 'docker version'
                sh """
                    docker build \
                    -t ${IMAGE_NAME}:${IMAGE_TAG} .
                """
            }
        }

        stage('Verify') {
            steps {
                echo "Verifying image for ${STUDENT_NAME}"
                sh """
                    docker image inspect \
                    ${IMAGE_NAME}:${IMAGE_TAG}
                """
                echo "Docker image verification successful"
            }
        }
    }

    post {
        success {
            echo "SUCCESS: Baimurat Akbiyev pipeline completed"
        }

        failure {
            echo "FAILED: Check Jenkins console output"
        }

        always {
            echo "Student: Baimurat Akbiyev"
            echo "Student ID: 38150"
        }
    }
}
