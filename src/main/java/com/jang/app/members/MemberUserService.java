package com.jang.app.members;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
// extends : 상속 1나, implements : 여러개
public class MemberUserService extends DefaultOAuth2UserService implements UserDetailsService {
	
	@Autowired
	private MemberMapper memberMapper;
	
	@Override
	public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
		// TODO Auto-generated method stub
		
		ClientRegistration registration = userRequest.getClientRegistration();
		
		String sns = registration.getRegistrationId();
		
		OAuth2User auth2User = super.loadUser(userRequest);
		
		if(sns.equals("kakao")) {
			auth2User = this.useKako(userRequest);
		}
		
		if(sns.equals("naver")) {
			
		}

		return auth2User;
	}
	
	private OAuth2User useKako(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
		OAuth2User auth2User = super.loadUser(userRequest);
		
		
		
		log.error("==============================================");
		log.error("ID : {}", auth2User.getName());
		log.error("Attributes : {}", auth2User.getAttributes());
		// 카카오톡에서 보내주는 것
		log.error("kako-Authorities : {}", auth2User.getAuthorities());
		
		log.error("test : {}", auth2User.getAttributes().get("properties"));
		
		Map<String, Object> attributes =auth2User.getAttributes();
		Map<String, Object> properties = (Map<String, Object>)attributes.get("properties");
		
		
		MemberVO memberVO = new MemberVO();
		
		memberVO.setAccessToken(userRequest.getAccessToken().getTokenValue());
		memberVO.setSns(userRequest.getClientRegistration().getRegistrationId());
		memberVO.setAttributes(attributes);
		
		memberVO.setUsername(auth2User.getName());
		memberVO.setName(properties.get("nickname").toString());
		
		List<RoleVO> list = new ArrayList<>();
		RoleVO roleVO = new RoleVO();
		roleVO.setRoleName("ROLE_USER");
		
		
		list.add(roleVO);
		memberVO.setVos(list);
		
		return memberVO;
	}
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		MemberVO memberVO = new MemberVO();
		memberVO.setUsername(username);
		try {
			memberVO = memberMapper.detail(memberVO);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return memberVO;
	}
}
