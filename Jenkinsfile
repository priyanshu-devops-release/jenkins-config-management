pipeline {
     agent {
        label 'jenkins-agent',
        showRawYaml: false
    }
    stages {
        stage('Checkout') {
            steps {
                git(
                    url: 'https://github.com/priyanshu-devops-release/jenkins-config-management.git',
                    branch: 'master',
                    credentialsId: 'github-token'
                )
            }
        }

        stage('Generate Jobs') {
            steps {
                jobDsl(
                    targets: '''
                        jobdsl/folders.groovy
                        jobdsl/applications.groovy
                        jobdsl/pipelines/*.groovy
                    ''',
                    removedJobAction: 'DELETE',
                    removedViewAction: 'DELETE',
                    removedConfigFilesAction: 'DELETE',
                    lookupStrategy: 'JENKINS_ROOT'
                )
            }
        }
    }
}