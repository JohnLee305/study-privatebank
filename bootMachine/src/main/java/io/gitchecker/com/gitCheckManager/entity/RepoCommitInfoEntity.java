package io.gitchecker.com.gitCheckManager.entity;
//TODO : 깃허브 정보에 대하여 기본적으로 갖고있어야 하는 정보들을 담는 DTO

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.stereotype.Component;

import javax.persistence.Entity;

@Entity
@Component
@Getter
@Setter
@ToString
public class RepoCommitInfoEntity {

    private String commit;
    private String url;

}
