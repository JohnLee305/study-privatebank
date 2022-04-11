package io.gitchecker.com.restcaller;
//TODO : 깃허브 API REST 통신하는 객체
// 여기서는 GitInfoEntity 받아와서 rest 통신해서 다시 건네주는 역할만 해야하고, 얘는 그냥 빈으로 관리해서 싱글톤으로 갖고있는게 맞다.

import io.gitchecker.com.gitCheckManager.entity.GitInfoEntity;
import io.gitchecker.com.gitCheckManager.entity.RepoCommitInfoEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Component
public class GitCallService {

    //상수값 도출 : 나중에 properties 정리.
    final String BASIC_URL = "https://api.github.com";
    final String USER_NAME = "JohnLee305";

   private static RestTemplate restTemplate;
    //내가 원하는 결과에 대한 링크
    // https://stackoverflow.com/questions/21869795/github-api-retrieve-user-commits
    //사용자에 대한 리포지토리를 우선 받은 후 해당 리포지토리에 대한 오너의 커밋정보를 받아와서 업데이트 체크 해야함

    /**
     * 사용자 이름으로 만들어진 리포지토리의 모든 최근 커밋 일자를 리턴
     * @return
     * 모든 리포지토리의 가장 최근 커밋 일자
     */
    public List<String>  callGithubUserCommitDate(){
        List<String> commitDates = new ArrayList<String>();

        //사용자의 리포지토리 리스트 모두 가지고 오기
        URI repoUri = UriComponentsBuilder
                .fromUriString(BASIC_URL) //깃헙 호출
                .path("/users/"+USER_NAME+"/repos")
                .encode()
                .build()
                .toUri();

        // uri 주소 생성
       // RestTemplate에 URI를 String 타입으로 전달하면 내부적으로 인코딩 작업을 수행한다는 점입니다.
        // 한글을 포함하는 경우라면 builder와 RestTemplate에서 이중 인코딩하지 않도록 유의해야 합니다.
        // 반면, URI타입을 반환하는 경우 buider에서 encoding 처리를 해줘야 합니다.
        // encode()에 인자 값을 지정하지 않으면 기본적으로 UTF-8로 인코딩 작업을 수행합니다.

        RestTemplate restTemplete = new RestTemplate();
        ResponseEntity<List<GitInfoEntity>> result = restTemplete.exchange(repoUri, HttpMethod.GET, null,new ParameterizedTypeReference<List<GitInfoEntity>>() {});
        List<GitInfoEntity> repoInfoList = new ArrayList<>();
        repoInfoList.addAll(result.getBody());

        for (GitInfoEntity gitInfo : repoInfoList) {

            //각각의 리포지토리 커밋 정보 가져오기.
            URI commitUri = UriComponentsBuilder
                    .fromUriString(BASIC_URL)
                    .path("/repos/"+USER_NAME+"/" + gitInfo.getName() + "/commits")
                    .queryParam("per_page", "1")  // query parameter가 필요한 경우 이와 같이 사용
                    .encode()
                    .build()
                    .toUri();

            RestTemplate restCommitTemplete = new RestTemplate();

            ResponseEntity<List<RepoCommitInfoEntity>> commitResult = restCommitTemplete.exchange(commitUri, HttpMethod.GET, null, new ParameterizedTypeReference<List<RepoCommitInfoEntity>>() {
            });
            List<RepoCommitInfoEntity> infoList = new ArrayList<>();
            infoList.addAll(commitResult.getBody());

            commitDates.add(infoList.get(0).getCommit().getAuthor().getDate());
        }
        return commitDates;
    }

    /**
     * 오늘 날짜와 비교해서 오늘 커밋 정보가 있는지 확인한다.
     * @return boolean
     * TRUE : 오늘 커밋한 이력이 있음
     * FALSE : 오늘 커밋한 이력이 없음
     */
    public boolean isCommitted(List<String> latestCommitDates){

        LocalDate today = LocalDate.now();

        for (String dates : latestCommitDates){
            LocalDate targetDay = LocalDate.parse(dates.substring(0,10), DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            if(today.isEqual(targetDay)){return true;}
        }
        return false;
    }

    //public static void main(String[] args) {
    //    GitCallService gitCallService = new GitCallService();
    //    boolean toDayStatus = gitCallService.isCommitted(gitCallService.callGithubUserCommitDate());
    //    System.out.println("오늘 커밋 했나요~? : "+ toDayStatus);
    //    gitCallService.callGithubUserCommitDate();
    //}
}
