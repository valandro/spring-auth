package com.valandro.contract.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AuthResponse {
    private String token;
    @JsonProperty(value = "user_name")
    private String name;
    @JsonProperty(value = "access_level")
    private Integer accessLevel;
}
