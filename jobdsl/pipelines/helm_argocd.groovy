pipelineJob('Applications/helm-argocd') {

    description('Deploy helm-argocd')

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
}