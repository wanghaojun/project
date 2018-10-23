package com.whj.water.controller;

import com.whj.water.dto.Message;
import com.whj.water.model.Worker;
import com.whj.water.repository.UserRepository;
import com.whj.water.repository.WorkerRepository;
import com.whj.water.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/worker")
public class WorkerController {

    @Autowired
    private WorkerRepository workerRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    /**
     * 新建一名用户
     * @param name 用户姓名
     * @param phone 手机号
     * @param province 省份
     * @param city 城市
     * @param region 地区
     * @param address 详细地址
     * @param type 净水器类型
     * @param wxname 微信名（可以为空）
     * @return 用户信息
     */
    @RequestMapping(value = "/saveUser",method = RequestMethod.POST)
    public Object saveUser(String name,String phone,String province,String city,String region,String address, String type,String wxname){
        return userService.crateUser(name,phone,province,city,region,address,type,wxname);
    }

    /**
     * 新建一名工人
     * @param name 姓名
     * @param wxname 微信名
     * @param card 工号
     * @param phone 手机号
     * @param province 省份
     * @param city 城市
     * @param region 地区
     * @return 工人信息
     */
    @RequestMapping(value = "/saveWorker",method = RequestMethod.POST)
    public Object saveWorker(String name,String wxname,String card,String phone,String province,String city,String region){

        if (workerRepository.findFirstByWxname(wxname)!=null){
            return new Message(-1,"微信用户已经存在");
        }

        if (workerRepository.findFirstByPhone(phone)!=null){
            return new Message(-1,"手机用户已经存在");
        }

        if (workerRepository.findFirstByCard(card)!=null){
            return new Message(-1,"工号已经存在");
        }

        Worker worker = new Worker();
        worker.setName(name);
        worker.setWxname(wxname);
        worker.setCard(card);
        worker.setPhone(phone);
        worker.setProvince(province);
        worker.setCity(city);
        worker.setRegion(region);

        return  workerRepository.save(worker);
    }

    /**
     * 查询所有工人信息
     * @return 工人信息
     */
    @RequestMapping(value = "/findAll",method = RequestMethod.POST)
    public Object findAllWorker(){return workerRepository.findAll();}

    /**
     * 根据微信名查询工人信息
     * @param wxname 微信名
     * @return 工人信息
     */
    @RequestMapping("/findByWxname")
    public Object findWorkerByWxname(String wxname){
         return workerRepository.findFirstByWxname(wxname);
    }




}
