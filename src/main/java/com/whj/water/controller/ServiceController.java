package com.whj.water.controller;

import com.whj.water.model.Service;
import com.whj.water.repository.ServiceRepository;
import javafx.geometry.Pos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/service")
public class ServiceController {

    @Autowired
    private ServiceRepository serviceRepository;


    @RequestMapping(value = "/create",method = RequestMethod.POST)
    public Object create(String name,String price,String detail){
        Service service = new Service();
        service.setName(name);
        service.setPrice(price);
        service.setDetail(detail);
        return  serviceRepository.save(service);
    }




}
