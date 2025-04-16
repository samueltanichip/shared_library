def call() {
    stage('Instalar Dependências') {
        bat '"C:\\Program Files\\nodejs\\npm.cmd" install'
    }
}
