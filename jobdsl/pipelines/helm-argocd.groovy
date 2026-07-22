pipelineJob('Applications/helm-argocd') {

    description('Deploy Helm ArgoCD Repository')

    definition {
        cpsScm {
            scm {
                git {
                    remote {
                        url('https://github.com/priyanshu-devops-release/helm-argocd.git')
                        credentials('github-token')
                    }

                    branch('master')
                }
            }

            scriptPath('Jenkinsfile')
        }
    }

    logRotator {
        daysToKeep(30)
        numToKeep(20)
    }
}