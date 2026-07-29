// This is a Job DSL script to create folders in Jenkins for organizing jobs.
folder('Applications') {
    displayName('Applications')
    description('Application Pipelines')
}

folder('Applications/NodeJS') {
    displayName('NodeJS')
    description('Node.js Pipelines')
}

folder('Applications/Python') {
    displayName('Python')
    description('Python Pipelines')
}

folder('Applications/Maven') {
    displayName('Maven')
    description('Maven Pipelines')
}

folder('Infrastructure') {
    displayName('Infrastructure')
    description('Infrastructure Pipelines')
}
folder('Infrastructure/ArgoCD') {
    displayName('ArgoCD')
    description('ArgoCD Pipelines')
}

// folder('Utilities') {
//     displayName('Utilities')
//     description('Utility Jobs')
// }