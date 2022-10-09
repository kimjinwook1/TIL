# Jenkins + Ansible + Kubernetes 와의 연동

### kubernetses(K8s)

- 오픈소스 기반의 컨테이너화 된 애플리케이션(워크로드와 서비스)의 자동 배포, 스케일링 등을 제공하는 관리 플랫폼


| 가능한 서비스 | 불가능한 서비스 |
| --- | --- |
| 컨테이너 화 된 애플리케이션 구동 | 소스코드 배포X, 빌드 X |
| 서비스 디스커버리와 로드 밸런싱 | 애플리케이션 레벨 서비스 X |
| 스토리지 오케스트레이tmxhflwl | 로깅, 모니터링 솔루션 X |
| 자동화된 롤아웃과 롤백 | 포괄적인 머신설정, 유지보수, 관리, 자동 복구시스템 제공 X |
| 자동화된 빈 패킹(bin packing) |     |
| 자동화된 복구(self-healing) |     |
| 시크릿과 구성관리 |     |

### Kubernetes Cluster

- Master(Control plane)

  전체 구성되어있는 각각의 노드를 관리

    - 설정 정보

    - 스케줄 관리

    - api 처리

- 노드

  실제 컨테이너 자체를 운영하고 컨테이너에 대한 스케줄링 작업

    - 실제 운영하고자 하는 컨테이너를 관리하기 위한 Pod

    - Pod를 운영하기위한 Kublet

    - Pods: **애플리케이션**을 위해 서로 상호작용해야 하는 컨테이너들의 논리적인 **집합**


### Kubernetes 기본 명령어

- 노드 확인

    - kubectl get nodes
- 파드 확인

    - kubectl get pods
- 디플로이먼트 확인

    - kubectl get deployments
- 서비스 확인

    - kubectl get services
- Nginx 서버 실행

    - kubectl run sample-nginx --image=nginx --port=80
- 컨테이너 정보 확인

    - kubectl describe pod/sample-nginx
- 파드 삭제

    - kubectl delete pod/sample-nginx-XXXXX-XXXXX
- deployment 생성

    - kubectl create deployment sample-nginx --image=nginx
- Scale 변경 (2개로 변경)

    - kubectl scale deployment sample-nginx --replicas=2
- **Script 실행** - 가장 많이 사용되는 명령어

    - kubectl apply -f sample1.yml
      - 실행에 오류가 발생할 경우 경로를 지정해주어야한다.
      - /usr/local/bin/kubectl apply -f /Users/kimjinwook/~~~.yml

- 파드 확인
  - kubectl get pods -o wide
- 파드에 터널링으로 접속
  - kubectl exec -it nginx-deployment-XXXX-XXXX -- /bin/bash
- 파드 노출(공개)
  - kubectl expose deployment nginx-deployment --port=80 --type=NodePort
- 파드 삭제
  - kubectl delete pod/nginx-deployment-XXXX-XXXX
- 디플로이먼트 삭제
  - kubectl delete deployment nginx-deployment
  
