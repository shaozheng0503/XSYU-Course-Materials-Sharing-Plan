package com.example.cinema.data.user;

import com.example.cinema.CinemaApplication;
import com.example.cinema.po.User;
import com.example.cinema.vo.UserForm;
import junit.framework.TestSuite;
import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

@SpringBootTest(classes = CinemaApplication.class)
@RunWith(SpringJUnit4ClassRunner.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class AccountMapperTest {

    @Autowired
    private AccountMapper accountMapper;
    private static Integer id;

    @Test
    public void AcreateNewAccountTest(){
        int res=accountMapper.createNewAccount("tttttest","tttttest",0);
        //这个要肿么验证
        Assert.assertEquals(1,res);
        System.out.println();

    }

    @Test
    public void BgetAccountByNameTest(){
        User user=accountMapper.getAccountByName("tttttest");
        id=user.getId();
        Assert.assertEquals("tttttest",user.getUsername());
        System.out.println(id);
    }

    @Test
    public void CupdatePasswordTest(){
        UserForm userForm=new UserForm();
        userForm.setUsername("tttttest");
        userForm.setPassword("123123");
        int res=accountMapper.updatePassword(userForm);
        Assert.assertEquals(1,res);
        System.out.println(id);
    }

    @Test
    public void DgetCinemaRolesTest(){
        List<User> userList=accountMapper.getCinemaRoles();
        Assert.assertEquals(2,userList.size());
        System.out.println(id);

    }

    @Test
    public void EupdateRoleByIdTest(){
        int res=accountMapper.updateRoleById(id,"tttttest3","test",2);
        Assert.assertEquals(1,res);
        System.out.println(id);

    }

    @Test
    public void FdeleteRoleByIdTest(){
        int res=accountMapper.deleteRoleById(id);
        Assert.assertEquals(1,res);
        System.out.println(id);

    }

}
