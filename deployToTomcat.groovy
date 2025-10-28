def call() {
    sh '''
        echo "Deploying WAR to Tomcat..."
        curl -u karthik:password \
        --upload-file target/maven-web-application.war \
        "http://13.232.108.91:8080/manager/text/deploy?path=/devops144&update=true"
    '''
}
