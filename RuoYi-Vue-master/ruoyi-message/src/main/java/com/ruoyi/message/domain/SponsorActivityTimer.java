package com.ruoyi.message.domain;


import com.ruoyi.message.controller.ConsumerMessageController;
import com.ruoyi.message.controller.UserController;
import com.ruoyi.message.service.IProducerMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashSet;
import java.util.List;


@Component
public class SponsorActivityTimer {
    @Autowired
    private IProducerMessageService producerMessageService;
    @Autowired
    private ConsumerMessageController consumerMessageController;
    @Autowired
    private UserController userController;
    // 执行方法
    @Scheduled(cron = "*/3 * * * * ? ")
//    @Scheduled(cron = "* * * */3 * ? ")
    public void run() {
        Date date = new Date();
        List<ProducerMessage> producerMessageList= producerMessageService.selectunsend(0,date);
        for(ProducerMessage producerMessage :producerMessageList){
            producerMessageService.updatesend(producerMessage.getId());
        }
        for(ProducerMessage producerMessage :producerMessageList){
            HashSet<Integer> useridlist = userController.selectUser(producerMessage.getConsumerid(),producerMessage.getConsumerjudge());
            System.out.println(useridlist);
            for(Integer userid:useridlist){
                consumerMessageController.addConsumerMessage(producerMessage.getProducerid(),userid,producerMessage.getMessagecontent(),producerMessage.getProducerdate(),0,producerMessage.getMessagelink());
            }
        }
    }
}
