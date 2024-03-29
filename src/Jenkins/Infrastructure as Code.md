### Infrastructure as Code

- 시스템, 하드웨어 또는 인터페이스의 구성정보를 파일(스크립트)을 통해 관리 및 프로비저닝

- IT 인프라 스트럭처, 베어 메탈 서버 등의 물리 장비 및 가상 머신과 관련된 구성 리소를 관리

- 버전 관리를 통한 리소스 관리


Terraform

- 클라우드 상관없이 다중 클라우드 다중 환경을 관리할 수 있는 도구

- 별도의 스크립트 언어 문법을 알아야 함

- 어떠한 부분이 문제가 생겼을 경우 해당하는 부분을 리로드 또는 삭제하고 새로 만들어 주는 작업을 할 수 있음

Ansible

- 기존 구성되어있는 서버들의 정보 변경이나 설정등을 원하는 형태로 변경하는데 특화되어있는 IaC 도구(**구성관리 도구**)

- 다른 IaC 대비 빠르고 편리함

- 어떤 문제가 발생했을 때 왜 문제가 발생했는지, 그 문제에 대한 해결을 하기 위한 스크립트를 작성하는데 사용


- 테라폼은 인프라를 구축하는 용도로 많이 사용, 앤서블은 이미 구축되어있는 서버들의 구성정보를 변경하거나 관리하는 용도로 사용


Ansible

- 여러 개의 서버를 효율적으로 관리할 수 있게 해주는 환경 구성 자동화 도구
  
  - Configuration Management, Deployment & Orchestration tool
  
  - IT infrastructure 자동화

- Push 기반 서비스

- Simple, Agentless

- 할 수 있는 일
  
  - 설치: apt-get, yum, homebrew
  
  - 파일 및 스크립트 배포: copy
  
  - 다운로드: get_url, git
  
  - 실행: shell, task


Install Ansible

- Ansible Server 설치(Linux)
  
  - $yum install ansible
  
  - $ ansible --version

- 환경 설정 파일 -> /etc/ansible/ansible.cfg

- Ansible에서 접속하는 호스트 목록 -> /etc/ansible/hosts


- docker run --privileged --name ansible-server -itd -p 20022:22 -p 8081:8080 -e container=docker -v /sys/fs/cgroup:/sys/fs/cgroup --cgroupns=host edowon0623/ansible-server:m1 /usr/sbin/init

ansible-server : 172.17.0.2/16

docker-server: 172.17.0.4/16

jenkins-server: 172.17.0.3

비밀번호: P@ssw0rd

### Test Ansible module

- 실행 옵션
  
  - -i(--inventiory-file) -> 적용될 호스트들에 대한 파일 정보
  
  - -m(--moduel-name) -> 모듈 선택
  
  - -k(--ask-pass) -> 관리자 암호 요청
  
  - -k(--ask-become-pass) -> 관리자 권한 상승
  
  - --list-hosts -> 적용되는 호스트 목록

- **멱등성**

  - 같은 설정을 여러 번 적용하더라도 결과가 달라지지 않는 설정
  
  - ex) echo -e "[mygroup]\n172.20.10.11" >> /etc/ansible/hosts


- Ansible Test 
  
  - $ansible all -m ping // host에 등록된 모든 ip에 모듈을 실행시킨다.


### Ansible Playbook

- 사용자가 원하는 내용을 미리 작성해 놓은 파일

  - ex) 설치, 파일 전송, 서비스 재시작 ...

  - ex) 다수의 서버에 반복 작업을 처리하는 경우

- Playbook

- $vi first-playbook.yml 작성

- $ ansbile-playbook first-playbook.yml

- $ cat/etc/ansible/hosts


-> ansible의 playbook 모듈을 통해 우리가 ansible 에서 해야할 스크립트를 미리 작성한 후 젠킨스에서 ansible-playbook을 실행만 시키면 되도록 한다.

ex) war파일을 배포할 경우 도커서버에 이미지와 프로세스가 살아있어서 재 배포가 안되는 문제가 발생하는데 배포 전 도커 이미지와 프로세스를 삭제하는 스크립트를 작성, 그 후 도커를 배포하는 스크립트를 실행하여 문제를 해결할 수 있다.

vi create-cicd-devops-image.yml // ->ansible-server

```yaml
- hosts: all
#   become: true

  tasks:
  - name: create a docker image with deployed waf file //이미지 생성
    command: docker build -t edowon0623/cicd-project-ansible .
    args: 
        chdir: /root
    
  - name: push the image on Docker Hub //이미지 업로드
    command: docker push edowon0623/cicd-project-ansible

  - name: remove the docker image from the ansible server //이미지 삭제
    command: docker rmi edowon0623/cicd-project-ansible  
    ignore_errors: yes
```

vi create-cicd-devops-container.yml // ->docker-server

```yaml
- hosts: all
#   become: true  

  tasks:
  - name: stop current running container //도커 중지
    command: docker stop my_cicd_project
    ignore_errors: yes

  - name: remove stopped cotainer //도커 삭제
    command: docker rm my_cicd_project
    ignore_errors: yes

  - name: remove current docker image //이미지 삭제
    command: docker rmi edowon0623/cicd-project-ansible
    ignore_errors: yes

  - name: pull the newest docker image from Docker Hub //허브에서 도커이미지 다운로드
    command: docker pull edowon0623/cicd-project-ansible

  - name: create a container using cicd-project-ansible image //도커 생
    command: docker run -d --name my_cicd_project -p 8080:8080 edowon0623/cicd-project-ansible
```

> ### ssh 로그인 오류시
> 
> - cd .ssh
> 
> - host_name 파일 내용 삭제
> 
> ### ssh에서 clear 명령어 사용 가능
> 
> - yum install -y ncurses
