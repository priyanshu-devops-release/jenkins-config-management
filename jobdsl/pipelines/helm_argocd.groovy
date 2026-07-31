pipelineJob('Infrastructure/ArgoCD/ArgoCD-Application-Restart') {

    description('Helm sample pipeline running on Kubernetes kubelet-docker-agent.')

    definition {
        cps {
            script("""
pipeline {
    agent {
        label 'kubelet-docker-agent'
    }

    parameters {
        choice(
            name: 'APPLICATION',
            choices: [
                'jenkins',
                'external-secrets',
                'prometheus',
                'grafana'
            ],
            description: 'Select the application to restart'
        )
        string(
            name: 'NAMESPACE',
            defaultValue: 'devops-tools',
            description: 'Kubernetes Namespace'
        )

        choices(
            name: 'K8S_COMPONENT',
            choices: [
                'ingress',
                'service',
                'deployment'
            ],
            description: 'K8s Component Name'
        )
    }

    stages {
        stage('Restart Application') {
            steps {
                container('kubectl') {
                    sh '''
                        kubectl rollout restart ${K8S_COMPONENT} ${APPLICATION} -n ${NAMESPACE}
                        kubectl rollout status ${K8S_COMPONENT} ${APPLICATION} -n ${NAMESPACE} --timeout=300s
                    '''
                }
            }
        }
    }

    post {
        success {
            echo "Application restarted successfully."
        }
    }
}
""".stripIndent())
            sandbox(true)
        }
    }
}