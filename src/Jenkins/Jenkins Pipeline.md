- Declarative
- Scripted(Groovy + DSL)
- 차이점
  - 시작 시 유효성 검사 유무
  - 특정 Stage 실행 가능 여부
  - 제어문
  - Option

### Declarative
- Groovy script 없이 간단하게 시작
```
pipeline {
    agent any // 실행 가능한 Agent에서 Pipeline 실행
    stages {
        steps('build') { // build 스테이지 선언
            // build 스테이지에 필요한 작업을 수행
        }
        steps('test') { // test 스테이지 선언
            // test 스테이지에 필요한 작업을 수행
        }
        steps('deploy') { // deploy 스테이지 선언
            // deploy 스테이지에 필요한 작업을 수행
        }
    }
}
```
