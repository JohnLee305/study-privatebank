package io.gitchecker.com.restcaller;
//TODO : 깃허브 API REST 통신하는 객체
// 여기서는 GitInfoEntity 받아와서 rest 통신해서 다시 건네주는 역할만 해야하고, 얘는 그냥 빈으로 관리해서 싱글톤으로 갖고있는게 맞다.

import io.gitchecker.com.gitCheckManager.entity.GitInfoEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class GitCallService {

   private static RestTemplate restTemplate;

   // public GitInfoEntity callGithubUserCommitStatus(){

    public GitInfoEntity callGithubUserCommitStatus(){

    }




}
