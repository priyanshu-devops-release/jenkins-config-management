pipelineJob('Utilities/Seed') {

    description('Regenerates all Jenkins jobs from Job DSL')

    definition {
        cpsScm {
            scm {
                git {
                    remote {
                        url('https://github.com/priyanshu-devops-release/jenkins-config-management.git')
                    }
                    branch('master')
                }
            }

            scriptPath('Jenkinsfile')
        }
    }
}