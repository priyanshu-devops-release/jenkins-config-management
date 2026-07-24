pipelineJob('Applications/NodeJS/node-sample') {

    description('Node.js sample pipeline running on Kubernetes node-docker-agent.')

    definition {
        cps {
            script("""
pipeline {
    agent {
        label 'node-docker-agent'
    }

    options {
        timestamps()
        disableConcurrentBuilds()
    }

    stages {

        stage('Checkout') {
            steps {
                checkout scm
            }
        }

        stage('Verify Environment') {
            steps {
                container('node') {
                    sh '''
                        echo "Node Version"
                        node --version

                        echo "NPM Version"
                        npm --version
                    '''
                }
            }
        }

        stage('Install') {
            steps {
                container('node') {
                    sh 'npm install'
                }
            }
        }

        stage('Test') {
            steps {
                container('node') {
                    sh 'npm test'
                }
            }
        }

        stage('Build') {
            steps {
                container('node') {
                    sh 'npm run build'
                }
            }
        }
    }

    post {
        success {
            echo 'Build Successful'
        }

        failure {
            echo 'Build Failed'
        }
    }
}
""".stripIndent())
            sandbox(true)
        }
    }
}