package com.example.demo.auth.security;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AppUserDetails implements UserDetails {
    private String userId;
    private String password;
    private Date passUpdateDate;
    private int loginMissTimes;
    private boolean unlock;
    private boolean enabled;
    private Date userDueDate;
    private Collection<? extends GrantedAuthority> authorities;

    // original field(フィールドにusenameを使わないこと。username=userIdとなるため。)
    private String appUserName;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
       // 実質的にuserIdをusernameとして取り扱っている
       return userId;
    }

    @Override
    public boolean isAccountNonExpired() {
        // TODO 実質無チェック
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        // TODO 実質無チェック
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        // TODO 実質無チェック
        return true;
    }

    @Override
    public boolean isEnabled() {
        // TODO 実質無チェック
        return true;
    }
}
