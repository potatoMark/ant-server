package com.framework.modules.sys.controller;


import com.framework.common.utils.*;
import com.framework.common.utils.token.TokenUtils;
import com.framework.modules.sys.pojo.User;
import com.framework.modules.sys.service.IUserService;
import com.framework.modules.sys.vo.UserVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * <p>
 * 系统用户 前端控制器
 * </p>
 *
 * @author Mark
 * @since 2018-10-02
 */
@Api("用户信息管理")
@RestController
@RequestMapping("/sys")
public class UserController {

    @Autowired
    IUserService userService;

    @Autowired
    RedisUtil redisUtil;

    @ApiOperation("获取所有用户信息")
    @GetMapping("/users")
    public R getUsers(){

        return R.ok().putResult(userService.getUsers());
    }

    @ApiOperation("根据ID查询用户信息")
    @ApiImplicitParam(name = "id", value = "用户ID", dataType = "Long")
    @GetMapping("/users/{id}")
    public R getUser(@PathVariable(name="id", required = true) Long id){

        User user = userService.getUser(id);

        return R.ok().putResult(user);
    }

    @GetMapping("/users/usercode/{usercode}")
    public R getUserByUserCode(@PathVariable(name="usercode", required = true) String usercode){

        User user = userService.getUserByUserCode(usercode);

        return R.ok().putResult(user);
    }

    @ApiOperation("按照分页的方式查询用户信息")
    @ApiImplicitParam(name = "params", value = "查询条件", dataType = "RequestUtils<UserVO>")
    @PostMapping("/users/page")
    public R userPageList(@RequestBody RequestUtils<UserVO> params){
        PageUtils page = userService.queryPage(params);
        return R.ok().putResult(page);
    }

    @ApiOperation("批量删除")
    @ApiImplicitParam(name = "userIds", value = "多个个用户ID信息", dataType = "List<Integer>")
    @DeleteMapping("/users/delete")
    public R deleteUser(@RequestParam List<Integer> userIds){
        int rst = userService.deleteUsers(userIds);
        return R.ok().putResult(rst);
    }

    @ApiOperation("更新/新增用户信息")
    @ApiImplicitParam(name = "userVo", value = "用户信息传输VO", dataType = "UserVO")
    @PostMapping("/users/save")
    public R saveUser(@RequestBody UserVO userVo){
        User user = userVo.getPojoUser();
        int rst = userService.saveUser(user);
        return R.ok();
    }

    @PostMapping("/users/userinfo")
    public R getUserByToken(@RequestParam String token) throws Exception {
        String jwt = new String (Base64Util.decryptBASE64(token));
        boolean flg = TokenUtils.verifyJWT(token);
        if (flg) {
            String signature = jwt.split("\\.")[2];
            //根据signature去redis找出用户编码信息，
            Long userId = (Long) redisUtil.get(signature);
            if (userId == null) {
                return R.error(403,"user login time out");
            }
            User user = userService.getUser(userId);

            return R.ok().putResult(user);
        } else {
            return R.error();
        }
    }

    @ApiOperation("根据条件查询用户信息")
    @ApiImplicitParam(name = "userVo", value = "用户信息传输VO", dataType = "UserVO")
    @PostMapping(value = "/users/getUsersByCondition")
    public R getUsersByCondition(@RequestBody UserVO userVO) throws Exception {

        List<User> users = userService.getUsersByCondition(userVO);

        return R.ok().putResult(users);
    }

}
