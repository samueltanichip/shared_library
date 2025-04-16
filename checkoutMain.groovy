def call() {
    stage('Checkout main') {
        git branch: 'main', 
            url: 'https://github.com/samueltanichip/express_server_for_flutter_app_testing.git'
    }
}
