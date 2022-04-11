package io.gitchecker.com.shellhandler;
//TODO : 쉘 스크립트 핸들링 할때 써야할 것들 정리

//자바로 쉘 스크립트를 실행하야 한다.
// 킷허브 커밋을 시켜줄 수 있게 해야함.

//깃허브 커밋에 대한 순서 :

/**
 * 준비물 :
 * - 깃허브 리포지토리에 결석일에 커밋을 때려줄 비공개 리포지토리 생성
 * - 서버가 실행될 로컬 환경에 리포지토리 개발환경이 세팅되어있어야함.
 * - 비공개 리포지토리에 MD파일을 통하여 커밋정보 입력.
 * - 날짜 및 형식 template을 통하여 일일단위의 입력
 * 실행순서 :
 * git pull 받는다.
 * readme.md 파일을 읽어들인다.
 * readme.md 파일을 수정한다 (템플릿의 형식에 맞게 추가작성)
 * git add .
 * git commit -m "YYYY-MM-DD 통한의 코팅 테스트 연습!!! ()"
 * git push origin main
 */

public class ShellHandler {

    public static void main(String[] args) {

    }


}
