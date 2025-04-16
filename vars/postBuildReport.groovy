def call(Map config = [:]) {
    def recipient = config.get('to', 'samueltani@chiptronic.com.br')
    def replyTo = config.get('replyTo', 'samueltanifrancisco@gmail.com')
    def from = config.get('from', 'samueltanifrancisco@gmail.com')

    script {
        def buildTime = new Date().format("dd/MM/yyyy HH:mm:ss", TimeZone.getTimeZone('America/Sao_Paulo'))

        def changes = ""
        for (changeLog in currentBuild.changeSets) {
            for (entry in changeLog.items) {
                changes += "- ${entry.author} - ${entry.msg}\n"
            }
        }

        emailext(
            mimeType: 'text/html',
            subject: "${currentBuild.currentResult} - ${env.JOB_NAME} #${env.BUILD_NUMBER}",
            body: """
                <h2>Resultado do Build: ${currentBuild.currentResult}</h2>
                <ul>
                    <li><strong>Job:</strong> ${env.JOB_NAME}</li>
                    <li><strong>Build:</strong> #${env.BUILD_NUMBER}</li>
                    <li><strong>Status:</strong> ${currentBuild.currentResult}</li>
                    <li><strong>Data e Hora:</strong> ${buildTime}</li>
                    <li><strong>Duração:</strong> ${currentBuild.durationString}</li>
                    <li><strong>Branch:</strong> ${env.BRANCH_NAME}</li>
                    <li><strong>Commit:</strong> ${env.GIT_COMMIT}</li>
                    <li><strong>Autor:</strong> ${env.GIT_AUTHOR_NAME}</li>
                    <li><strong>Mensagem do Commit:</strong> ${env.GIT_COMMIT_MESSAGE}</li>
                    <li><strong>Veja detalhes:</strong> <a href="${env.BUILD_URL}">${env.BUILD_URL}</a></li>
                </ul>
                <h3>Commits incluídos nesta build:</h3>
                <pre>${changes}</pre>
            """,
            to: recipient,
            replyTo: replyTo,
            from: from
        )
    }
}
