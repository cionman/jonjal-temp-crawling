## 실행 전 세팅해야할 환경 변수
- SUPABASE_DB_URL = 회의록 제공
- SUPABASE_DB_USER = 회의록 제공
- SUPABASE_DB_PASSWORD = 회의록 제공
- OPEN_ASSEMBLY_API_KEY = https://open.assembly.go.kr/portal/openapi/openApiActKeyPage.do 에서 발급


## 유의사항
- 그래들 빌드시 테스트 코드가 돌아가고 테스트 코드에 모든 데이터를 입력하도록 되어 있다. 표결 정보 데이터가 꽤 크므로 시간이 오래 걸린다.
  테스트 수행 없이 빌드만 원할 경우 `JonjalTempCrawlingApplicationTests` 파일에서 테스트 코드 부분을 주석처리하자
- DB가 멤버 모두에게 알려져 있으므로 복수 실행 시 문제가 될 수 있다. 로컬 환경에서 테스트환경을 구축하는 것을 검토하자 (로컬DB 변경 시 `application.properties` 파일 확인)
- 코드에 주석이 첨부되어 있으니 확인
- 해당 코드는 기획에 필요한 데이터를 제공하기 위해 제공되었으므로 실프로젝트에 그대로 적용하는 것은 바람직하지 않다고 생각됩니다. 참고용으로만 사용