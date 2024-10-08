package com.jang.app.members;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import com.jang.app.vaildate.MemberAddGroup;
import com.jang.app.vaildate.MemberUpdateGroup;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Pattern;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.core.user.OAuth2User;

import lombok.Data;

@Data
public class MemberVO implements UserDetails, OAuth2User { //security 사용되는 data type은 UserDetails 이다.
	
	@NotBlank(groups = {MemberAddGroup.class, MemberUpdateGroup.class})
	private String username;
	@Pattern(groups = {MemberAddGroup.class}, regexp = "(?=.*[0-9])(?=.*[a-z])(?=.*\\W)(?=\\S+$).{6,12}")
	@NotBlank(groups = {MemberAddGroup.class})
	private String password;
	
	private String passwordCheck;
	
	@NotBlank(groups = {MemberAddGroup.class, MemberUpdateGroup.class})
	private String name;
	@Email(groups = {MemberAddGroup.class, MemberUpdateGroup.class})
	private String email;
	@Past(groups = {MemberAddGroup.class, MemberUpdateGroup.class})
	private Date birth;
	private boolean enabled;
	private List<RoleVO> vos;
	
	private String accessToken;
	private String sns;
	
	// OAuth2User
	// attributes의 어떤 데이터를 저장할 것이냐? : token 정보 저
	private Map<String, Object> attributes;
	
	@Override
	public Map<String, Object> getAttributes() {
		// TODO Auto-generated method stub
		return this.attributes;
	}
	
	@Override
	// UserDetails 의 오버라이딩
	// <? extends GrantedAuthority> : 타입의 제한을 두려고, GrantedAuthority 타입이거나 ,? 아무거나 타입이지만, GrantedAuthority를 구현한 타입
	// super : 부모라는 뜻
	public Collection<? extends GrantedAuthority> getAuthorities() { 
		
		List<GrantedAuthority> authorities = new ArrayList<>();
		
		for(RoleVO roleVO : vos) {
			GrantedAuthority authority = new SimpleGrantedAuthority(roleVO.getRoleName());
			authorities.add(authority);
		}
		
		return authorities;
	}
	
	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}
	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}
	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}


	
//	public boolean isEnabled() {
//		return true;
//	}
	
}
