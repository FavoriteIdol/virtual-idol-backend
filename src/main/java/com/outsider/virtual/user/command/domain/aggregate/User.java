package com.outsider.virtual.user.command.domain.aggregate;

import com.outsider.virtual.user.command.domain.aggregate.embeded.*;
import com.outsider.virtual.util.BaseEntity;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "tbl_user")
public class User extends BaseEntity {

    @Id
    @Column(name = "user_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 또는 AUTO, SEQUENCE, TABLE 중 하나 선택
    private Long id;
    @Column(name = "user_email")
    private String email;

    @Column(name = "user_password")
    private String password;

    @Column(name = "user_name")
    private String userName;

    @Column(name = "user_withdrawal")
    private Boolean isWithdrawal;
    @Column(name = "user_Img")
    private String userImg;

    public String getUserImg() {
        return userImg;
    }

    public void setUserImg(String userImg) {
        this.userImg = userImg;
    }
    @Enumerated(EnumType.STRING)
    @Column(name = "user_authority")
    private Authority authority;
    @Column(name = "user_avatar")
    private String avatar;

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public void setJoinDate(LocalDate joinDate) {
        this.joinDate = joinDate;
    }

    @Column(name = "join_date")
    private LocalDate joinDate;

    public Authority getAuthority() {
        return authority;
    }

    public void setAuthority(String authority) {
        this.authority = Authority.fromString(authority);
    }

    public void setWithdrawal(Boolean withdrawal) {
        isWithdrawal = withdrawal;
    }

    public Boolean getWithdrawal() {
        return isWithdrawal;
    }

    public User(Boolean isWithdrawal) {
        this.isWithdrawal = isWithdrawal;
    }

    public User() {
    }
    public User(String email, String password, String userName,String userImg,Authority authority) {
        this.email = email;
        this.password = password;
        this.userName = userName;
        this.isWithdrawal = false;
        this.authority =authority;
        this.userImg = userImg;
        this.joinDate = LocalDate.now();
    }


    public Long getId() {
        return id;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getUserName() {
        return userName;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setUsername(String userName) {
        this.userName = userName;
    }

    @Override
    public String toString() {
        return "User{" +
                "id='" + id + '\'' +
                ", password='" + password + '\'' +
                ", userName='" + userName + '\'' +
                '}';
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
