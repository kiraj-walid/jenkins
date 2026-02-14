pipeline {
    agent any
    
    stages {
        stage('📥 Checkout') {
            steps {
                echo '▶️ Récupération du code depuis GitHub...'
                sleep 2
            }
        }
        
        stage('🔨 Build') {
            steps {
                echo '▶️ Compilation des microservices...'
                sleep 3
            }
        }
        
        stage('🐳 Docker Build') {
            steps {
                echo '▶️ Construction des images Docker...'
                sleep 3
            }
        }
        
        stage('🚀 Deploy') {
            steps {
                echo '▶️ Déploiement en cours...'
                sleep 2
                echo '✅ Application déployée avec succès!'
            }
        }
    }
}