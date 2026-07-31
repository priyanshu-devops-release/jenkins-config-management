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

        choice(
            name: 'K8S_COMPONENT',
            choices: [
                'ingress',
                'daemonset',
                'statefulset'
            ],
            description: 'K8s Component Name'
        )
    }

    stages {
        stage('Restart Application') {
            steps {
                container('kubectl') {
                    sh '''
                        kubectl rollout restart \${params.K8S_COMPONENT} \${params.APPLICATION} -n \${params.NAMESPACE}
                        kubectl rollout status \${params.K8S_COMPONENT} \${params.APPLICATION} -n \${params.NAMESPACE} --timeout=300s
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
            sandbox(false)
        }
    }
}