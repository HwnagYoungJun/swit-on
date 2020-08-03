# ERD

> Req1-1. ERD 기획, 200720



### Study

- id
- name
- master_name
- description
- code
- isPrivate
- isRecruit
- start_term
- end_term
- created_at
- week(비트)
- start_time
- end_time
- lowercategory
- logo
- limit_user_cnt
- study_like_users



### Board

- id
- name
- study_id(외래키)



### Article

- id

- title

- content

- user_name

- article_like_users

- article_bookmark_users

- file

- board_id(FK)

- created_at

- updated_at

  

### Comment

- id
- content
- user_id
- comment_like_users
- article_id(FK)
- created_at
- updated_at



### Uppercategory

- id
- name



### Lowercategory

- id
- name
- uppercategory_id



### Notice

- id
- title
- content
- user_id
- study_id



### User

- id
- name
- email
- password
- introduce
- profile_image
- interested (uppercategory와 관련?)



### Join

- id
- user_id(FK)
- study_id(FK)
- isComplete(bool)
- reg_date




변경 사항:
1. user 테이블의 complete_study 삭제 (Join으로 연결돼있으므로)
2. study 테이블에 created_at 추가
3. study 테이블 필드 순서 조금 변경
4. notice에 study_id(FK) 추가
5. study 테이블에 master -> master_id, lowercategory -> lowercategory_id로 변경, article 테이블에 user -> user_id로 변경 comment 테이블에 user -> user_id로 변경 등 (FK 이름 형식 통일)
6. article 테이블에 comments 제거 (comment 테이블과 역할 중복)
7. join 테이블에 reg_date(가입일) 추가
8. study 테이블에서 uppercategory 필드 제거, 이유: lowercategory가 어차피 FK로 uppercategory를 갖고 있음
9. boolean (T/F) 필드의 경우 boolean처럼 사용하지만 varchar(1)로 만들어둠, 이유: 그냥 그게 편해서