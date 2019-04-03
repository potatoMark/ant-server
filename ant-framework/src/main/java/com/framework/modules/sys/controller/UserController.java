package com.framework.modules.sys.controller;


import com.framework.common.utils.*;
import com.framework.common.utils.token.TokenUtils;
import com.framework.modules.sys.pojo.User;
import com.framework.modules.sys.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 系统用户 前端控制器
 * </p>
 *
 * @author Mark
 * @since 2018-10-02
 */
@RestController
@RequestMapping("/sys")
public class UserController {

    @Autowired
    IUserService userService;

    @Autowired
    RedisUtil redisUtil;

    @GetMapping("/users")
    public R getUsers(){

        return R.ok().putResult(userService.getUsers());
    }

    @GetMapping("/users/{id}")
    public R getUser(@PathVariable(name="id", required = true) Long id){

        User user = userService.getUser(id);

        return R.ok().putResult(user);
    }

    @PostMapping("/users/page")
    public R userPageList(@RequestBody RequestUtils params){

        PageUtils page = userService.queryPage(params);
        return R.ok().putResult(page);
    }

    @PostMapping("/users/delete")
    public R deleteUser(@RequestParam List<Integer> userIds){
        int rst = userService.deleteUsers(userIds);
        return R.ok().putResult(rst);
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
                return R.error(404,"user login time out");
            }
            User user = userService.getUser(userId);

            return R.ok().putResult(user);
        } else {
            return R.error();
        }
    }


}
