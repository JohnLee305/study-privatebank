package io.gitchecker.com.shellhandler;
//TODO : 쉘 스크립트 핸들링 할때 써야할 것들 정리

//자바로 쉘 스크립트를 실행하야 한다.
// 킷허브 커밋을 시켜줄 수 있게 해야함.

//깃허브 커밋에 대한 순서 :

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * 준비물 :
 * - 깃허브 리포지토리에 결석일에 커밋을 때려줄 비공개 리포지토리 생성
 * - 서버가 실행될 로컬 환경에 리포지토리 개발환경이 세팅되어있어야함.
 * - 비공개 리포지토리에 MD파일을 통하여 커밋정보 입력.
 * - 날짜 및 형식 template을 통하여 일일단위의 입력
 * - 정해진 순서의 쉘 스크립트
 * - 쉘 스크립트의 실행 권한 부여 여부 확인 (chmod +x gitScript.sh)
 *
 *
 * 실행순서 : (쉘 스크립팅이 필요할것같다.)
 * git pull 받는다.
 * readme.md 파일을 읽어들인다.
 * readme.md 파일을 수정한다 (템플릿의 형식에 맞게 추가작성)
 * git add .
 * git commit -m "YYYY-MM-DD 통한의 코팅 테스트 연습!!! ()"
 * git push origin main
 */

public class ShellHandler {
    private InputStream inputStream;

    //실행하는 OS판별
    // mac os x : 개발
    // windows : 예외처리
    // or... else = linux...(sh)
    public String checkOSName() {
        boolean isWindows = System.getProperty("os.name")
                .toLowerCase().startsWith("windows");

        System.out.println("실행환경이 윈도우인가? " + isWindows);
        System.out.println("그렇다면? "+ System.getProperty("os.name").toLowerCase());
        return System.getProperty("os.name").toLowerCase();
    }

    // 쉘 파일 실행
    public void shellFileExe(){


        //OS 이름에 따른 경로 명 변경(profile 분리 필요하다면 확장)
        String shellPath = "./src/main/resources/shellScripts/mac/"; //MAC
        String shellFileName = "gitScript.sh";

        try {
            String[] command = new String[] {shellPath+shellFileName};

            ProcessBuilder processBuilder = new ProcessBuilder(command);
            Process process = processBuilder.start();
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line;

            // 결과를 한줄씩 받아와서 읽어냄.
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
            int exitCode = process.waitFor();


            System.out.println("\nExited with error code : " + exitCode);
        }catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    public static void main(String[] args) {
        String filePath = "/Users/leejeonghoon/Desktop/DevStud/hubchecker";

        ShellHandler shellHandler = new ShellHandler();
        shellHandler.checkOSName();

    }


}
