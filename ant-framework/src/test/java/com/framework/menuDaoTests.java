package com.framework;

import com.framework.modules.demo.dao.TablesDao;
import com.framework.modules.sys.dao.MenuDao;
import com.framework.modules.sys.dao.RoleDao;
import com.framework.modules.sys.dao.UserDao;
import com.framework.modules.sys.dao.UserMapper;
import com.framework.modules.sys.pojo.Menu;
import com.framework.modules.sys.pojo.User;
import com.framework.modules.sys.service.IMenuService;
import com.framework.modules.sys.service.IUserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class menuDaoTests {

    @Autowired
    private UserDao userDao;

    @Autowired
    private RoleDao roleDao;

    @Autowired
    private MenuDao menuDao;

    @Autowired
    private IUserService userService;

    @Autowired
    private IMenuService menuService;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private TablesDao tablesDao;


    @Test
    public void testSelect() {

//       List<Menu> menuList = menuDao.getMenuList();
//       System.out.print(menuList);
//
//        List<Menu> menuList1 =  menuDao.selectList();
//        System.out.print(menuList1);
//        menuService.deleteMenu(63);
        Menu menu = new Menu();
        menu.setId(5);
        menu.setName("权限信息1");
        menu.setPath("RoleList1");
        menu.setParentId(null);
        menuService.saveMenu(menu);
    }
}
