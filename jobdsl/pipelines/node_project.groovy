pipelineJob('Applications/NodeJS/NODE-DEMO') {

    description('Node.js sample pipeline running on Kubernetes node-docker-agent.')

    definition {
        cps {
            script("""
pipeline {
    agent {
        label 'node-docker-agent'
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

        stage('Verify Node') {
            steps {
                container('node') {
                    sh '''
                        node --version
                        npm --version
                    '''
                }
            }
        }
    }

    post {
        success {
            echo '✅ Build Successful'
        }
        failure {
            echo '❌ Build Failed'
        }
        always {
            container('node') {
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