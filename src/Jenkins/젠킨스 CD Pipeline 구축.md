# Jenkins를 이용한 CI/CD Pipeline 구축



### Setup PollSCM

Project > Configure > Build Triggers

- Build periodically -> cron job -> update가 없더라도 빌드

- Poll SCM -> cron job -> commit된 내용이 있어야지 빌드



구성 - 빌드 유발 - Poll SCM 체크 - Schedule - * * * * *  -> 매초 마다 스케쥴러가 실행 



### SSH + Docker가 설치되어 있는 VM(컨테이너) 사용하기

1.  war파일을 SSH를 이용해서 복사(서버2)

2. (서버2) Dockerfile + *.war -> Docker Image 빌드

3. Docker Image -> 컨테이너 생성






