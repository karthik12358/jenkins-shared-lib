# jenkins-shared-lib

jenkins-shared-lib/
│
├── vars/
│   ├── checkoutCode.groovy
│   ├── mavenBuild.groovy
│   ├── sonarAnalysis.groovy
│   ├── deployToTomcat.groovy
│   └── slackNotify.groovy
│
└── README.md
def call() {
    git branch: 'staging', 
        credentialsId: 'homegit-passwd', 
        url: 'https://github.com/karthik12358/maven-web-app-project-kk-funda.git'
}
def call() {
    sh 'mvn clean install'
}
def call() {
    sh 'mvn sonar:sonar'
}
def call() {
    sh '''
        echo "Deploying WAR to Tomcat..."
        curl -u karthik:password \
        --upload-file target/maven-web-application.war \
        "http://13.232.108.91:8080/manager/text/deploy?path=/devops144&update=true"
    '''
}
def call(String status) {
    def color = 'good'
    if (status.contains('FAIL')) color = 'danger'
    if (status.contains('UNSTABLE')) color = 'warning'

    slackSend(
        channel: '#practice505',
        color: color,
        message: status
    )
}

