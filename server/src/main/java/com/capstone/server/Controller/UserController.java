package com.capstone.server.Controller;

import com.capstone.server.DTO.RequestDTO.JoinRequestDTO;
import com.capstone.server.DTO.ResponseDTO.ResponseDTO;
import com.capstone.server.DTO.ResponseDTO.ResultResponseDTO;
import com.capstone.server.DTO.TokenDTO;
import com.capstone.server.Etc.JsonRequestWrapper;
import com.capstone.server.JWT.JwtProperties;
import com.capstone.server.Service.TokenService;
import com.capstone.server.Service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.api.Http;
import lombok.extern.slf4j.Slf4j;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
@RestController
public class UserController {
    private final UserService userService;
    private final TokenService tokenService;

    @Autowired
    public UserController(UserService userService, TokenService tokenService){
        this.userService = userService;
        this.tokenService = tokenService;
    }

    @PostMapping("/users")
    public ResponseDTO join(@RequestBody JoinRequestDTO joinRequestDTO){
        userService.join(joinRequestDTO);

        return ResponseDTO.builder()
                .code(HttpStatus.OK.value())
                .message(HttpStatus.OK.getReasonPhrase()).build();
    }

    @PostMapping("/users/authorize")
    public void login(JsonRequestWrapper request, HttpServletResponse response) throws IOException{
        String userId = response.getHeader("id");
        TokenDTO dto = TokenDTO.builder()
                .userId(userId)
                .token(response.getHeader(JwtProperties.REFRESH_HEADER_STRING))
                .status(true).build();
        tokenService.TokenSave(dto);

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("access_token", response.getHeader(JwtProperties.ACCESS_HEADER_STRING));
        jsonObject.put("refresh_token", response.getHeader(JwtProperties.REFRESH_HEADER_STRING));

        ObjectMapper om = new ObjectMapper();
        ResultResponseDTO responseDTO = ResultResponseDTO.builder()
                .code(HttpStatus.OK.value())
                .message(HttpStatus.OK.getReasonPhrase())
                .data(jsonObject).build();

        response.reset();
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        String result = om.writeValueAsString(responseDTO);
        response.getWriter().write(result);
    }
}
