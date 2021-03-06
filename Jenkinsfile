def gitCommit() {
        sh "git rev-parse HEAD > GIT_COMMIT"
        def gitCommit = readFile('GIT_COMMIT').trim()
        sh "rm -f GIT_COMMIT"
        return gitCommit
    }

    node {
        // Checkout source code from Git
        stage 'Checkout'
        checkout scm
      
dir ('UI') { 
        // Build Docker image
        stage 'Build'
        sh "docker build -t digitalemil/mypublicrepo:ubs-ui-v1.0.0 ."

        // Log in and push image to GitLab
        stage 'Publish'
        withCredentials(
            [[
                $class: 'UsernamePasswordMultiBinding',
                credentialsId: 'dockerhub',
                passwordVariable: 'DOCKERHUB_PASSWORD',
                usernameVariable: 'DOCKERHUB_USERNAME'
            ]]
        ) {
            sh "docker login -u ${env.DOCKERHUB_USERNAME} -p ${env.DOCKERHUB_PASSWORD}"
            sh "docker push digitalemil/mypublicrepo:ubs-ui-v1.0.0"
        }
}


        // Deploy
        stage 'Deploy'

        marathon(
            url: 'http://marathon.mesos:8080',
            forceUpdate: true,
            credentialsId: 'dcos-token',
            filename: 'ui-config.tmp',
            id: '/dcosappstudio-ubs/management/ui',
            docker: 'digitalemil/mypublicrepo:ubs-ui-v1.0.0'
        )

        marathon(
            url: 'http://marathon.mesos:8080',
            forceUpdate: true,
            credentialsId: 'dcos-token',
            filename: 'versions/listener-v1.0.0.json',
            id: '/dcosappstudio-ubs/message-handler/message-listener',
            docker: 'digitalemil/mypublicrepo:dcosappstudio-messagelistener-v1.0.0'
        )
    }
