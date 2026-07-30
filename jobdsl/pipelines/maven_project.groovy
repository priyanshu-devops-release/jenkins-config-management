pipelineJob('Applications/Maven/MAVEN-DEMO') {

    description('Maven sample pipeline running on Kubernetes maven-docker-agent.')

    definition {
        cps {
            script("""
pipeline {
    agent {
        label 'maven-docker-agent'
    }

    stages {
        stage('Verify Maven') {
            steps {
                container('maven') {
                    sh '''
                        echo "Java Version:"
                        java -version

                        echo "Maven Version:"
                        mvn -version

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
            echo '✅ Maven agent is working!'
        }
        failure {
            echo '❌ Maven agent failed!'
        }
    }
}
""".stripIndent())
            sandbox(true)
        }
    }
}