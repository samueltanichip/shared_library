def call() {
    def branch = env.BRANCH_NAME ?: 'main' // fallback para 'main' se estiver vazio

    stage("Checkout ${branch}") {
        git branch: branch,
            url: 'https://github.com/samueltanichip/express_server_for_flutter_app_testing.git'
    }
}
