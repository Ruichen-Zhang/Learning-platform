package com.ruoyi.message.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.message.mapper.ConsumerMessageMapper;
import com.ruoyi.message.domain.ConsumerMessage;
import com.ruoyi.message.service.IConsumerMessageService;

/**
 * 接收方消息Service业务层处理
 *
 * @author ruoyi
 * @date 2023-02-01
 */
@Service
public class ConsumerMessageServiceImpl implements IConsumerMessageService
{
    @Autowired
    private ConsumerMessageMapper consumerMessageMapper;

    /**
     * 查询接收方消息
     *
     * @param id 接收方消息主键
     * @return 接收方消息
     */
    @Override
    public ConsumerMessage selectConsumerMessageById(Integer id)
    {
        return consumerMessageMapper.selectConsumerMessageById(id);
    }

    /**
     * 查询接收方消息列表
     *
     * @param consumerMessage 接收方消息
     * @return 接收方消息
     */
    @Override
    public List<ConsumerMessage> selectConsumerMessageList(ConsumerMessage consumerMessage)
    {
        return consumerMessageMapper.selectConsumerMessageList(consumerMessage);
    }

    /**
     * 新增接收方消息
     *
     * @param consumerMessage 接收方消息
     * @return 结果
     */
    @Override
    public int insertConsumerMessage(ConsumerMessage consumerMessage)
    {
        return consumerMessageMapper.insertConsumerMessage(consumerMessage);
    }

    /**
     * 修改接收方消息
     *
     * @param consumerMessage 接收方消息
     * @return 结果
     */
    @Override
    public int updateConsumerMessage(ConsumerMessage consumerMessage)
    {
        return consumerMessageMapper.updateConsumerMessage(consumerMessage);
    }

    /**
     * 批量删除接收方消息
     *
     * @param ids 需要删除的接收方消息主键
     * @return 结果
     */
    @Override
    public int deleteConsumerMessageByIds(Integer[] ids)
    {
        return consumerMessageMapper.deleteConsumerMessageByIds(ids);
    }

    /**
     * 删除接收方消息信息
     *
     * @param id 接收方消息主键
     * @return 结果
     */
    @Override
    public int deleteConsumerMessageById(Integer id)
    {
        return consumerMessageMapper.deleteConsumerMessageById(id);
    }


    /**
     * 增加
     *
     */
    @Override
    public boolean insert(ConsumerMessage consumerMessage) {
        return consumerMessageMapper.insert(consumerMessage)>0;
    }

    /**
     *更新
     */
    public boolean updateread(ConsumerMessage consumerMessage) {
        return consumerMessageMapper.updateread(consumerMessage)>0;
    };

}
