// Load folders
evaluate(readFileFromWorkspace('jobdsl/folders.groovy'))

// Load common application jobs
evaluate(readFileFromWorkspace('jobdsl/applications.groovy'))

// Load all pipeline definitions
[
    'jobdsl/pipelines/helm-argocd.groovy'
].each { script ->
    evaluate(readFileFromWorkspace(script))
}