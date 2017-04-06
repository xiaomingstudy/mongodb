package com.demo.service.account;

import com.demo.entity.Button;
import com.demo.repository.ButtonDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by 27259 on 2017/4/3.
 */
@Component
@Transactional
public class ButtonServer {
    @Autowired
    private ButtonDao buttonDao;

    public Iterable<Button> getAllButton() {

        return buttonDao.findAll();
    }

    public  Button getButtonById(Long Id){
        return  buttonDao.findOne(Id);
    }

}
