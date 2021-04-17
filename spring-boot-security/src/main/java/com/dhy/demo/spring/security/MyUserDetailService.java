package com.dhy.demo.spring.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * 主要是封装从数据库获取的用户信息
 *
 * @author yiaz
 * @date 2019年3月19日10:50:58
 */
@Component
public class MyUserDetailService implements UserDetailsService {

    // demo  不想写 service层，直接 dao 层穿透到 controller 层
    //@Autowired
    //private LoginMapper loginMapper;

    @Override
    public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException {
        // 根据用户名查询数据库，查到对应的用户
       // MyUser myUser = loginMapper.loadUserByUsername(name);

        // ... 做一些异常处理，没有找到用户之类的
//        if(myUser == null){
//            throw new UsernameNotFoundException("用户不存在") ;
//        }

//        // 根据用户ID，查询用户的角色
//        List<Role> roles = loginMapper.findRoleByUserId(myUser.getId());
//        // 添加角色
//        List<GrantedAuthority> authorities = new ArrayList<>();
//        for (int i = 0; i < roles.size(); i++) {
//            authorities.add(new SimpleGrantedAuthority(roles.get(i).getName()));
//        }
//        // 构建 Security 的 User 对象
//        User user = new User(myUser.getName(), myUser.getPassword(), authorities);
//
//        return user;
        MyUserDetials myUserDetials = new MyUserDetials();
        return  myUserDetials;
    }
}

