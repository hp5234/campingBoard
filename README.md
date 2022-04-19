# campingBoard - 제작중 

# 사용기술 
  - java 11
  - spring boot v2.6.4
  - gradle 
  - mysql v14.14
  - thymeleaf
  - spring mvc 
  - bootstrap

# 프로젝트 설명 
  - 카카오 지도 api 를 활용한 게시판 프로젝트 
  - 카카오 지도를 통해 화면을 구성하고 사용자가 클릭한 지역의 위도와 경도를 획득
  - 위도, 경도를 기반으로 게시판을 생성
  - 사용자는 생성된 게시판에 게시물과 댓글을 남길 수 있습니다. 

# 현시점 구현 기능 
  - mvc 패턴 적용 
  - mybatis 를 통한 mysql DB 연동 
  - 게시판 CRUD
  - 게시물 CRUD 
  - 댓글 CRUD 
  - 회원 CRUD 
  - 로그인 처리 과정 
  - 인터셉터, 필터 적용 

# 보완 예정사항  
  - 프론트 측 미비 
  - 입력값 검증 
  - 사용자 비밀번호 암호화 
  - 로깅 처리 
  - 좋아요, 싫어요 분리 
