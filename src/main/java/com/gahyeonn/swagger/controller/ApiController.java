package com.gahyeonn.swagger.controller;

import com.gahyeonn.swagger.dto.UserReq;
import com.gahyeonn.swagger.dto.UserRes;
import io.swagger.annotations.*;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.web.bind.annotation.*;

@Api(tags = "swagger 사용 Controller")
@RestController
@RequestMapping("/api")
public class ApiController {

    @GetMapping("/hello")
    public String hello(){
        return "hello";
    }

    @ApiImplicitParams({
            @ApiImplicitParam(name = "x", value = "x 값", required = true, dataType = "int", paramType = "path"),
            @ApiImplicitParam(name = "y", value = "y 값", required = true, dataType = "int", paramType = "query")})
    @GetMapping("/plus/{x}")
    public int plus(@PathVariable int x, @RequestParam int y){
        return x+y;
    }

    @ApiResponse(code = 100, message = "사용자의 나이가 10살 이하일때") //response code 내용
    @ApiOperation(value = "사용자의 이름과 나이를 입력받는 메소드") //api 설명
    @GetMapping("/user")
    public UserRes user(UserReq userReq){
        return new UserRes(userReq.getName(), userReq.getAge());

    }

    @Operation(summary = "user 정보 Post", description = "유저 이름, 나이 입력 후 반환")
    @ApiOperation(value = "사용자 정보 입력 메소드",httpMethod = "POST")
    @PostMapping("/user")
    public UserRes userPost(@RequestBody UserReq req){
        return new UserRes(req.getName(), req.getAge());
    }
}
