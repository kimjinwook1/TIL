- https://www.sonarqube.org
- Continuous Integration + Analysis
  - Code Quality Assurance tool -> Issues + Defect + Code Complexity
  - Detect Bugs & Vulnerabilities
  - Track Code Smells
  - Code Quality Metrics & History
- CI/CD integration  
  

- Maven에서 SonarQube 빌드
  - mvn sonar:sonar -Dsonar.url=http://IP_address:9000 -Dsonar.login=[the-sonar-token]

sonar-token : squ_32e74c255c8424686cb516afbed47d21aec6facf


### Jenkins + SonarQube
- Manage Jenkins -> Plugin Manager -> Available
  - SonarQube Scanner for Jenkins
- Manaage Jenkins -> Manage Credentials -> Add Credentials

- SonarQube 연동 pipeline 코드
stage('SonarQube analysis') {
    steps {
        withSonarQubeEnv('SonarQube-server') {
            sh 'mvn sonar:sonar'
        }
    }
}

### Jenkins Master + Slaves
- Jenkins Slave
  - Remote에서 실행되는 Jekins 실행 Node
  - Jekins Master의 요청 처리
  - Master로부터 전달된 job 실행
  - 다양한 운영체제에서 실행 가능
  - Jenkins 프로젝트 생성 시 특정 Slave를 선택하여 실행 가능

- 새로운 젠킨스 서버 실행
- docker run --privileged --name jenkins-node1 -itd -p 30022:22 -e container=docker -v /sys/fs/cgroup:/sys/fs/cgroup:rw --cgroupns=host  edowon0623/docker-server:m1 /usr/sbin/init

- Jenkins 서버에 JDK 설치
  - yum install -y ncurses git
  - yum list java*jdk-devel
  - yum install -y java-11-openjdk-devel.aarch64
