package com.milkywire.impacterservice.service;

import com.milkywire.impacterservice.dao.ImpacterDao;
import javax.inject.Inject;
import org.springframework.stereotype.Service;

@Service
public class ImpacterService {

    @Inject
    ImpacterDao impacterDao;

    public String getProperMessage() {
        return impacterDao.getMessage();
    }
}
