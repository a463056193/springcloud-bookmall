package pers.bookmall.common.pojo;

/**
 * 载荷对象
 */
public class UserInfo {

    private Long uid;

    private String username;

    public UserInfo() {
    }

    public UserInfo(Long uid, String username) {
        this.uid = uid;
        this.username = username;
    }

    public Long getUid() {
        return uid;
    }

    public void setUid(Long uid) {
        this.uid = uid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}