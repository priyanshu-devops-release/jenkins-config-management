pipelineJob('Applications/Python/PYTHON-DEMO') {

    description('Python sample pipeline running on Kubernetes python-docker-agent.')

    definition {
        cps {
            script("""
pipeline {
    agent {
        label 'python-docker-agent'
    }

    options {
        disableConcurrentBuilds()
    }

    stages {

        stage('Checkout') {
            steps {
                git branch: 'master',
                    url: 'https://github.com/priyanshu-devops-release/toolkit.git'
                // Add credentialsId: 'github-creds' if the repo is private
            }
        }

        stage('Verify Python') {
            steps {
                container('python') {
                    sh '''
                        echo "Python Version:"
                        python --version

                        echo "Pip Version:"
                        pip --version

                        echo "Working Directory:"
                        pwd

                        echo "Current Files:"
                        ls -la
                    '''
                }
            }
        }
    }

    post {
        success {
            echo '✅ Python agent is working!'
        }
        failure {
            echo '❌ Python agent failed!'
        }
        always {
            container('python') {
                sh 'ls -la'
            }
        }
    }
}
""".stripIndent())
            sandbox(true)
        }
    }
}