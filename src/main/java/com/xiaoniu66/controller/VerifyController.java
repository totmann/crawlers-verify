package com.xiaoniu66.controller;

import com.xiaoniu66.domain.FoshanHouseReq;
import com.xiaoniu66.service.VerifyService;
import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by owner on 2017/11/2.
 */
@RestController
public class VerifyController {

  Logger logger = LoggerFactory.getLogger(getClass());

  @Autowired
  VerifyService verifyService;


  @RequestMapping(value = "/verify/grzxbg")
  public String verifyGrzxbg(){
    String result = verifyService.execute("zyf810810=zyf810810=hr6ipv");
    logger.info("个人征信报告->verifyGrzxbg: {}",result);
    return result;
  }

  @RequestMapping(value = "/verify/sxbzxr")
  public String verifySxbzxr(){
    String result = verifyService.exeSxbzxr("苏州福宁投资发展有限公司");
    logger.info("失信被执行人->verifySxbzxr: {}",result);
    return result;
  }

  @RequestMapping(value = "/verify/fybzxr")
  public String verifyFybzxr(){
    String result = verifyService.exeFybzxr("天津市宇盛钢管镀锌有限公司");
    logger.info("法院被执行人->verifyFybzxr: {}",result);
    return result;
  }

  @RequestMapping(value = "/verify/encredit")
  public String verifyEncredit(){
    String result = verifyService.exeEncredit("广西鹏诚联合惠创科技有限公司");
    logger.info("国家企业信用信息网爬虫->verifyEncredit: {}",result);
    return result;
  }

  @RequestMapping(value = "/verify/house")
  public String verifyHouse(){
    String result = verifyService.exeGuanzhouHouse();
    logger.info("verifyHouse: {}",result);
    return result;
  }
  @RequestMapping(value = "/verify/foshan/house")
  public String verifyFoshanHouse(){
    FoshanHouseReq req = new FoshanHouseReq();
    req.setBusinessNum("20160426036405");
    req.setIdentNum("420114198307112516");
    String result = verifyService.exeFoshanHouse(req);
    logger.info("佛山房产->verifyFoshanHouse: {}",result);
    return result;
  }

  @RequestMapping(value = "/verify/engines")
  public String verifyEngines(){
    List<String> searchList = new ArrayList<>();
    searchList.add("13760485196");
    searchList.add("22222222222222");
    searchList.add("33333333");
    String result = verifyService.exeSearchEngines(searchList);
    logger.info("网查爬虫->verifyFoshanHouse: {}",result);
    return result;
  }






  @RequestMapping(value = "/grzxbg/query")
  public void getCallbackGrzxbg(@RequestBody Object respJson){
    logger.info("个人征信报告->getCallbackGrzxbg: {}",respJson);
  }

  @RequestMapping(value = "/sxbzxr/query")
  public void getCallbackSxbzxr(@RequestBody Object respJson){
    logger.info("失信被执行人->getCallbackSxbzxr: {}",respJson);
  }

  @RequestMapping(value = "/fybzxr/query")
  public void getCallbackFybzxr(@RequestBody Object respJson){
    logger.info("法院被执行人->getCallbackFybzxr: {}",respJson);
  }

  @RequestMapping(value = "/encredit/query")
  public void getCallbackEncredit(@RequestBody Object respJson){
    logger.info("国家企业信用信息网爬虫->getCallbackEncredit: {}",respJson);
  }

  @RequestMapping(value = "/foshan/query")
  public void getCallbackFoshanHouse(@RequestBody Object respJson){
    logger.info("佛山房产->getCallbackFoshanHouse: {}",respJson);
  }

  @RequestMapping(value = "/engines/query")
  public void getCallbackEngines(@RequestBody Object respJson){
    logger.info("网查爬虫->getCallbackEngines: {}",respJson);
  }


}
