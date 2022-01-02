# 적용해야할 시스템
- [x] 회원가입
  - [x] 아이디, 비밀번호 입력 
  - [x] 중복된 아이디 확인 
  - [x] 비밀번호 중복 체크
  - [x] 회원가입 시 아이디, 비밀번호, 이름 필요.
  - [x] tbl_member와 tbl_todo를 fk로 연결해준다. 
- [x] 로그인 시스템 변경 -> 아이디 + 비밀번호 
- [x] 로그아웃 시스템 
- [x] 회원 정보
  - [x] 회원 정보 조회
  - [x] 회원 정보 수정
  - [x] 회원 정보 삭제
- [x] TODO
  - [x] todo/register 실행 시 로그인한 유저의 등록만 저장되도록 설정
      -> tbl_member 의 uno값을 tbl_todo의 writerid에 넘겨줘야함
  - [x] Todo/list에 로그인한 유저의 목록만 보이도록 설정
  - [x] todo 에서 작성자를 tbl_member의 userid 또는 username으로 알려준다.
- [ ] 페이징 처리 
-----------------------------------------
##스프링 관련
- [x] @RequestParam 중복부분 -> @ModelAttribute 로 변경
- [x] 쿠키 -> 세션 방식으로 처리 -> 코드 길이 줄어듦 
- [ ] todo 등록 

