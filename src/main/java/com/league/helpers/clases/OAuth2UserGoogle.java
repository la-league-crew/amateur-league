package com.league.helpers.clases;

import com.league.entity.LeagueUser;
import com.league.helpers.enums.LeagueAuthProvider;
import com.league.helpers.enums.LeagueUserRole;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Map;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class OAuth2UserGoogle {
    private String name;
    private String lastName;
    private String email;
    private String nonce;

    public OAuth2UserGoogle(Map<String, Object> attributes){
            this.setEmail((String) attributes.get("email"));
            this.setName((String) attributes.get("given_name"));
        this.setLastName((String) attributes.get("family_name"));
        this.setNonce((String) attributes.get("nonce"));
        }

    public LeagueUser gelLeagueUser(){
    return LeagueUser.builder()
        .firstName(name)
        .lastName(lastName)
        .password(nonce)
        .email(email)
        .userRole(LeagueUserRole.USER)
        .authProvider(LeagueAuthProvider.GOOGLE)
        .build();
  }
}
