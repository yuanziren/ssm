package com.shsxt.controller;

import com.github.pagehelper.PageInfo;
import com.shsxt.base.BaseController;
import com.shsxt.base.BaseQuery;
import com.shsxt.base.ParamException;
import com.shsxt.model.ResultInfo;
import com.shsxt.po.User;
import com.shsxt.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("user")
public class UserController extends BaseController {

    @Autowired
    private UserService userService;

    //http://localhost:9999/ssm/user/queryUser?id=6
//    @RequestMapping("queryUser")
    @RequestMapping(value = "queryUser",method = RequestMethod.GET)
    @ResponseBody
    public User queryUser(Integer id) throws Exception {

        if (true) {
            throw new ParamException("参数异常");
        }

        return userService.queryById(id);
    }

    /**
     * restFul的url风格，省去了写问号，让请求路径和参数统一起来
     * http://localhost:9999/ssm/user/queryUser2/6
     * @param id
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "queryUser2/{id}",method = RequestMethod.GET)
    @ResponseBody
    public User queryUser2(@PathVariable Integer id) throws Exception {
        return userService.queryById(id);
    }


    //http://localhost:9999/ssm/user/addUser?userName=fage&userPwd=123321
//    @RequestMapping("addUser")

    /**
     * 浏览器不能模拟post请求，如果想要使用，可以用postman发送请求
     * @param user
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "addUser",method = RequestMethod.POST)
    @ResponseBody
    public ResultInfo addUser(User user) throws Exception {
        userService.insert(user);
        ResultInfo resultInfo = new ResultInfo();
        resultInfo.setCode(200);
        resultInfo.setMsg("添加成功");
        return resultInfo;
    }

    //http://localhost:9999/ssm/user/deleteUser?id=114
    @RequestMapping("deleteUser")
    @ResponseBody
    public ResultInfo deleteUser(Integer id) throws Exception {
        userService.delete(id);
        ResultInfo resultInfo = new ResultInfo();
        resultInfo.setCode(200);
        resultInfo.setMsg("删除成功");
        return resultInfo;
    }

    //http://localhost:9999/ssm/user/queryUserList?pageNum=1&pageSize=5
    @RequestMapping("queryUserList")
    @ResponseBody
    public PageInfo<User> queryUserList(BaseQuery query) throws Exception {
        return userService.queryForPage(query);
    }



}
