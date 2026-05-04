# 📑 [Report] Engineering Knowledge Base & Logic Archive

## 1. 개요 (Introduction)

본 저장소는 단순한 코드 저장소를 넘어, 문제 해결 역량을 시스템 설계 지식으로 자산화하는 개인 지식 베이스(Knowledge Base)입니다. 알고리즘 풀이 과정에서 얻은 논리적 통찰을 기록하고, 이를 체계적으로 데이터베이스화하여 향후 백엔드 아키텍쳐 및 DB 설계의 근거로 활용할 예정입니다.

<br>

## 2. 핵심 가치관 (Core Values)

* **문제 분석 및 설계 (Analysis & Design):** 코드를 작성하기 전 '구조'를 먼저 설계하는 데 집중합니다. 조건 분기를 줄이거나, 한정된 메모리를 가장 효율적으로 제어할 수 있는 로직의 흐름을 고민합니다.
* **성능과 가독성의 트레이드오프 (Performance vs Readability):** 기계적 수준(Low-level)의 극한 최적화와 유지보수 가능한 가독성 사이의 균형을 조율합니다. 무조건적인 성능 우선주의보다는, 상황과 도메인에 맞는 적정 기술(Appropriate Technology)을 선택합니다.
* **인사이트 추출 및 자산화 (Insight Extraction):** 하나의 문제에 다양한 풀이 방식을 적용해 보며, 접근 관점의 차이가 가져오는 결과를 분석합니다. 도출된 인사이트는 휘발되지 않도록 정형화된 이원화 로그로 기록하여 자산화합니다.

<br>

## 3. 알고리즘 접근 및 자산화 프로세스 (Methodology)

1. **[Design] 설계 및 분석:** 문제의 요구사항을 분석한 후 한정된 자원 내에서 최적화된 구조를 설계합니다. 알고리즘 템플릿을 따라가는 것이 아닌, 문제의 적합한 형태로 변형하여 적용합니다. 예를 들어 BFS나 DFS 탐색 알고리즘 구현시 visited 배열이 불필요하다고 판단되면, 과감하게 생략하여 메모리 사용 효율을 높입니다.
2. **[Implementation] 구현 및 검증:** **Java 8** 환경을 기반으로 최적의 성능을 낼 수 있는 구조를 짭니다. 조건 분기를 줄이는 방법 등 불필요한 연산을 줄일 수 있는지 AI를 활용해 검증합니다. AI를 활용할 때는 로직을 검증하고, 로직을 입증하는 용도로 활용합니다.
3. **[Archiving] 이원화 로그 관리:** 검증이 끝난 로직은 github 및 velog로 문서화합니다. 이후 `log.md`에 인덱싱하여 검색성을 확보하고, 핵심 사이트 및 트러블 슈팅 등 기술적 고찰은 `summary.md`에 기록합니다.

<br>

## 4. 파일 구조 (Directory Structure)

```text
📦 ALGORITHM-KNOWLEDGE-BASE
 ┣ 📂 Algorithm         # [Archive] 문제 풀이 코드 및 설계 분석(.md) 리포트
 ┃ ┗ 📂 YYYY/MM         # 연도 / 월별 문제 풀이 파일 분리
 ┃ 📂 BaekJoon          # [Automation] 백준 문제 풀이 기록 및 제출 데이터 아카이브
 ┃ ┣ 📂 BaekJoon        # 백준 문제 풀이 코드
 ┃ ┣ 📂 BOJ_ARCHIVE     # 2026년 백준 문제 풀이 코드
 ┃ ┗ 📂 BOJ_record      # 백준 기록
 ┣ 📂 docs              # [Database] 레포지토리의 핵심인 지식 베이스 및 인덱싱
 ┃ ┣ 📜 log.md          # Indexing Page: 빠른 탐색을 위한 문제별 메인 색인 (표 형식)
 ┃ ┣ 📜 summary.md      # Summary Page: 핵심 인사이트 및 트러블 슈팅 상세 기록
 ┃ ┗ 📜 BOJ_log.md      # Tech Report: 2023년 ~ 2026년 BOJ 통계 및 시각화 데이터
 ┗ 📜 README.md         # 본 문서 (Project Report)
```

<br>

## 5. 상세 관리 체계 (Specifications)

* **`docs/log.md` (Indexing Page):** 본 저장소의 **네비게이션**입니다. `날짜 | 플랫폼 | 문제 | 알고리즘 | 핵심 키워드 | gitbub | velog`를 정형화된 표로 관리하여, 수천 개의 데이터 속에서도 필요한 설계 패턴을 즉시 탐색할 수 있게 합니다.
* **`docs/summary.md` (Summary Page):** 문제 해결의 **'정수'**를 담습니다. 접근 방식의 차이(예: 미래 예측 vs 현재 수습), 메모리 관리 전략, JVM 오버헤드 최소화 기법 등 얕은 팁이 아닌 깊이 있는 트러블 슈팅 과정을 복기합니다.
* **`Algorithm/` (Year/Month/Day):** 시간의 흐름에 따른 사고의 확장을 증명합니다. 각 폴더에는 소스 코드와 함께, 해당 시점의 치열한 고민이 담긴 개별 설계 문서가 보관됩니다.

<br> 

## 6. 마무리 및 향후 방향 (Conclusion & Roadmap)

1,000일이 넘는 시간 동안 쌓아온 알고리즘 해결의 기록은 이 레포지토리의 '완성'이 아닌 새로운 '시작점'입니다. 문제 풀이의 진정한 가치는 플랫폼 상의 정답(AC)에 머무는 것이 아니라, 이를 실제 서비스의 **'견고한 백엔드 시스템 설계'**로 전이시키는 데 있다고 생각합니다.

앞으로도 본 저장소는 과거의 아카이브에 머물지 않고, 엔지니어로서 부딪히고 해결해 나갈 치열한 고민들을 계속해서 담아내는 **'살아있는 지식 베이스(Living Knowledge Base)'**로 진화할 것입니다.

### 🚀 Roadmap & Extensions
* **[Backend Architecture : SeedLog]**: 알고리즘에서 체득한 '저수준 메모리 제어'와 '조건 분기 최적화' 감각을 실무에 이식합니다. 현재 DB 인덱싱과 쿼리 성능이 최적화된 백엔드 서비스(SeedLog)를 직접 설계하고 구축하며, 논리적 자산을 실제 프로덕트로 실체화하고 있습니다.
* **[Data Infrastructure : DB Optimization]**: 프로그램의 성능을 결정짓는 핵심은 결국 데이터의 효율적인 탐색입니다. 알고리즘에서 다루던 자료구조적 원리를 RDBMS의 인덱스 아키텍처와 SQL 튜닝 역량으로 확장하며 시스템의 근간을 단단하게 다지고 있습니다.
* **[Continuous Logic Training]**: 특정 플랫폼에 얽매이지 않고, 글로벌 환경(LeetCode 등)과 다양한 문제 상황 속에서 '성능과 가독성의 트레이드오프'를 조율하는 구조적 사고 훈련을 멈추지 않고 기록할 예정입니다.

### 🔗 Link
* **기술 블로그:** [Velog - 설계 및 트러블 슈팅 회고록](https://velog.io/@dong20)
* **진행 중인 프로젝트:** [SeedLog Repository](https://github.com/DWinging/seed-log-project)