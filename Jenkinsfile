pipeline {
    agent any
    
    stages {
        stage('Build') {
            steps {
                echo 'Proje derleniyor...'
                sh 'mvn clean compile'
            }
        }
        
        stage('Test - URL Erişilebilirlik Kontrolü') {
            steps {
                echo 'URL erişilebilirlik testi çalıştırılıyor...'
                sh 'mvn test -Dtest=UrlAccessibilityTest'
            }
            post {
                success {
                    echo '✓ URL erişilebilir - Deploy adımına geçiliyor'
                }
                failure {
                    echo '✗ URL erişilemiyor - Pipeline başarısız!'
                    error('URL erişilebilirlik testi başarısız oldu. Pipeline durduruldu.')
                }
            }
        }
        
        stage('Deploy') {
            steps {
                echo 'Deploy adımı çalıştırılıyor...'
                script {
                    // Deploy işlemleri buraya eklenebilir
                    // Örnek: Docker build, Kubernetes deploy, FTP upload, vb.
                    
                    echo 'Deploy işlemi başlatılıyor...'
                    
                    // Örnek deploy komutları (gerçek deploy işleminize göre değiştirin)
                    // sh 'docker build -t myapp:latest .'
                    // sh 'docker push myapp:latest'
                    // sh 'kubectl apply -f k8s/deployment.yaml'
                    
                    // Şimdilik sadece bir mesaj yazdırıyoruz
                    sh '''
                        echo "Deploy script çalıştırılıyor..."
                        echo "Deploy başarıyla tamamlandı!"
                    '''
                }
            }
            post {
                success {
                    echo '✓ Deploy başarıyla tamamlandı!'
                }
                failure {
                    echo '✗ Deploy başarısız oldu!'
                }
            }
        }
    }
    
    post {
        always {
            echo 'Pipeline tamamlandı.'
            // Test sonuçlarını arşivle
            junit 'target/surefire-reports/*.xml'
        }
        success {
            echo '✓ Pipeline başarıyla tamamlandı!'
        }
        failure {
            echo '✗ Pipeline başarısız oldu!'
        }
    }
}

