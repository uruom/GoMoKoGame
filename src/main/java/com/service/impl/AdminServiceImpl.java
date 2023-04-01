package com.service.impl;

import com.entity.AdminDo;
import com.service.AdminService;
import com.view.LoginView;
import lombok.extern.slf4j.Slf4j;
import org.apache.log4j.Logger;
import org.apache.log4j.spi.LoggerFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class AdminServiceImpl implements AdminService {
    public static Logger logger = Logger.getLogger(AdminServiceImpl.class);

    @Override
    public boolean validateAdmin(AdminDo adminDo) {
        String userName = adminDo.getUserName();
        String pwdParm = adminDo.getPwd();
        if(userName==null||"".equals(userName.trim())){
            return false;
        }
//        logger.info("開始比較");
//        System.out.println(adminDo.getUserName()+"  ");
        if(adminDo.getUserName().equals("uruom")&&(adminDo.getPwd().equals("123456"))){

            return true;
        }

        return false;
    }


}
