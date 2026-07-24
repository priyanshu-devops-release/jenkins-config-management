pipelineJob('Applications/Seed') {

    description('Regenerates all Jenkins jobs')

    definition {
        cpsScm {
            scm {
                git {
                    remote {
                        url('https://github.com/priyanshu-devops-release/jenkins-config-management.git')
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