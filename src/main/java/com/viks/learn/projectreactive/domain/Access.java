package com.viks.learn.projectreactive.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Access {
    public static final String PRIMARY_ACCESS_DEVELOPER = "developer";
    public static final String PRIMARY_ACCESS_MANAGER = "manager";
    public static final String ACCESS_GIT = "git";
    public static final String ACCESS_DEVOPS = "devops";
    public static final String ACCESS_JENKINS = "jenkins";
    public static final String ACCESS_MM = "mattermost";
    public static final String[] DEFAULT_ACCESS_LIST = { "git", "devops", "jenkins" };

    public static Access getDefaultNewInstance() {
        return new Access(PRIMARY_ACCESS_DEVELOPER, DEFAULT_ACCESS_LIST);
    }

    private String primaryAccess;
    private String[] allAccess;
}
