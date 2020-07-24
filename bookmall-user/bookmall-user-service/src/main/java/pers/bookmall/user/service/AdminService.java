package pers.bookmall.user.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import pers.bookmall.user.mapper.AdminMapper;
import pers.bookmall.user.pojo.Admin;

import java.util.List;

@Service
public class AdminService {

    @Autowired
    private AdminMapper adminMapper;


    public Boolean checkAdmin(Admin admin){

        List<Admin> select = this.adminMapper.select(admin);
        if(CollectionUtils.isEmpty(select)){
            return false;
        }
        return true;
    }

}
