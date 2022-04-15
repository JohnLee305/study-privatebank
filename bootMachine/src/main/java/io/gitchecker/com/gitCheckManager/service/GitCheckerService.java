package io.gitchecker.com.gitCheckManager.service;
//TODO : 지금 할꺼는 아닌데 혹시나 뒤에 request받아서 뭔가를 처리해야 할수도 있어서 만들어 놓는 공간.

import io.gitchecker.com.restcaller.GitCallService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GitCheckerService {

    @Autowired
    GitCallService gitCallService;

    public void autoCommitDaily(){

        boolean toDayStatus = gitCallService.isCommitted(gitCallService.callGithubUserCommitDate());
        System.out.println("오늘 커밋 했나요~? : "+ toDayStatus);
        gitCallService.callGithubUserCommitDate();

    }

}
