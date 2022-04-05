package io.gitchecker.com.restcaller;
//TODO : 깃허브 API REST 통신하는 객체
// 여기서는 GitInfoEntity 받아와서 rest 통신해서 다시 건네주는 역할만 해야하고, 얘는 그냥 빈으로 관리해서 싱글톤으로 갖고있는게 맞다.

import io.gitchecker.com.gitCheckManager.entity.GitInfoEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Component
public class GitCallService {

   private static RestTemplate restTemplate;
   //탐색중
    //https://docs.github.com/en/rest/reference/commits
   // https://api.github.com/users/JohnLee305

    //리포지토리 커밋 정보
    // https://api.github.com/repos/JohnLee305/JohnLee305/commits?per_page=1
    // http://docs2.lfe.io/v3/repos/statistics/#contributors

    //공개 리포지토리 정보
    //https://api.github.com/users/JohnLee305/repos

    //내가 원하는 결과에 대한 링크
    // https://stackoverflow.com/questions/21869795/github-api-retrieve-user-commits

    //사용자에 대한 리포지토리를 우선 받은 후 해당 리포지토리에 대한 오너의 커밋정보를 받아와서 업데이트 체크 해야함




   // public GitInfoEntity callGithubUserCommitStatus(){
    public List<GitInfoEntity> callGithubUserCommitStatus(){
        URI uri = UriComponentsBuilder
                .fromUriString("https://api.github.com") //http://localhost에 호출
                .path("/users/JohnLee305/repos")
                //.queryParam("name", "steve")  // query parameter가 필요한 경우 이와 같이 사용
                //.queryParam("age", 10)
                .encode()
                .build()
                .toUri();

        // uri 주소 생성
       // RestTemplate에 URI를 String 타입으로 전달하면 내부적으로 인코딩 작업을 수행한다는 점입니다.
        // 한글을 포함하는 경우라면 builder와 RestTemplate에서 이중 인코딩하지 않도록 유의해야 합니다.
        // 반면, URI타입을 반환하는 경우 buider에서 encoding 처리를 해줘야 합니다.
        // encode()에 인자 값을 지정하지 않으면 기본적으로 UTF-8로 인코딩 작업을 수행합니다.


        RestTemplate restTemplete = new RestTemplate();
        ResponseEntity<List<GitInfoEntity>> result = restTemplete.exchange(uri, HttpMethod.GET, null,new ParameterizedTypeReference<List<GitInfoEntity>>() {});
        List<GitInfoEntity> infoList = new ArrayList<>();
        infoList.addAll(result.getBody());
        return infoList;
    }

    public static void main(String[] args) {
        GitCallService gitCallService = new GitCallService();
        System.out.println("!!!!");
        List<GitInfoEntity> repoList =  gitCallService.callGithubUserCommitStatus();

        for (GitInfoEntity gitInfo : repoList){
            System.out.println(gitInfo.toString());
        }
    }
}
