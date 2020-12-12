# CatchFly(🐝파리 트랩🐝)
 
## 게임 방법
1. 플레이어가 6*6 맵의 블럭 중 하나를 클릭한다.
2. 파리(컴퓨터)는 ← ↑ → ↓ 네 방향 중 하나의 방향으로 인접한 블럭을 랜덤으로 이동한다.
3. 1과 2를 반복한다.
4. 파리와 가장 인접한 ← ↑ → ↓ 네 방향을 모두 클릭해 파리가 더 이상 이동할 블럭이 없고, 남은 블럭의 개수가 0 이상인 경우 사용자는 우승한다.
5. 파리와 가장 인접한 ← ↑ → ↓ 네 방향 중 하나라도 클릭되어있지 않고, 남은 블럭의 개수가 없는 경우 사용자는 패배한다.

## UI
1. 스플래쉬 화면
2. (optional) 난이도 설정 화면
3. 게임 화면
4. AlertDialog로 게임 결과 보여주기(종료 -> 2번 화면으로, 다시 플레이 버튼 -> 3번 화면으로)

## 기능
- (optional) 브금 넣기
- (optional) 사용자가 버튼 선택 시 효과음 넣기
- (optional) 5초 안에 선택 안하면 카운트다운 5초 실행 후 블락 수 -5
    - CountDownTimer
    - 1초씩 카운트다운될 때마다 효과음 뚱뚱

- 블락 색상 변경
    - button.setColor
- 남은 블락 수 -1
    - 전역변수 leftBlocks - 1

- 파리의 시작 위치를 설정한다. 36개 버튼 중 하나 랜덤으로 선택해서 이미지 바꾸기
    - ArrayList<Button>, 0 ~ 35 랜덤 선택 후 해당 버튼 setBackground
- 사용자가 파리를 가두기 위해 버튼을 클릭한다
	- 현재 위치 저장하는 변수
	📌 파리가 위치한 버튼을 선택하면 아무 변화 없게 한다.
	📌 이미 클릭된 버튼이면 아무 변화 없게 한다. if(isSelected == true)
- 현재 파리 위치 받아와서 ← ↑ → ↓ 네 방향 중에 클릭되지 않은 블락 하나 선택하기
    - 현재 위치 저장 변수 currentPos
    - currentPos - 1, currentPos + 1, currentPos - 6, currentPos + 6 중 하나 랜덤 선택 후 변수 selectedPos
    - currentPos = selectedPos;
    - ArrayList<Boolean> isSelected
	- 📌 현재 위치가 테두리에 위치해있다.
	- 📌 (0, 0) : currentPos + 1, currentPos + 6
	- 📌 (0, 1) ~ (0, 4) : currentPos - 1, currentPos + 1, currentPos + 6
	- 📌 (0, 5) : currentPos - 1, currentPos + 6
	- 📌 (1, 0) ~ (4, 0) : currentPos + 1, currentPos - 6, currentPos + 6
	- 📌 (5, 0) : currentPos + 1, currentPos - 6, currentPos + 6
	- 📌 (1, 5) ~ (4, 5) : currentPos - 1, currentPos + 1, currentPos - 6
	- 📌 (5, 5) : currentPos - 1, currentPos - 6
	- 📌 (5, 1) ~ (5, 4) : currentPos - 1, currentPos - 6, currentPos + 6
	- 📌 만약 이동 불가능한 위치가 선택되면 위치를 다시 랜덤으로 선택해라
- 컴퓨터가 선택한 방향의 버튼 이미지 바꾸기
    - currentPos.setBackground

- 현재 파리 위치에 인접한 ← ↑ → ↓ 네 방향 블락 클릭되어있는지 검사
    - isSelected[currentPos - 1], isSelected[currentPos + 1], isSelected[currentPos - 6], isSelected[currentPos + 6] true/false
	- 📌 인덱스가 0 ~ 35를 안벗어나는 것만 검사??
- 남은 블락 수 검사
    - 전역변수 leftBlocks >= 0 인지 검사

## 리소스
- 파리 이미지
- 브금
- 스플래쉬 화면 이미지
- (optional) 효과음

## 버전
- 1.0 : optional을 제외한 모든 기능 구현

## 일정
- 2020.12.12 : UI, 리소스 구하기, 사용자가 버튼 선택할 때 기능 구현
- 2020.12.13 : 컴퓨터 차례일 때 기능 구현
- 2020.12.14 : 효과음, 카운트다운타이머 구현
- 2020.12.15 : 예외처리, 테스트
- 2020.12.16 : 동영상 만들기