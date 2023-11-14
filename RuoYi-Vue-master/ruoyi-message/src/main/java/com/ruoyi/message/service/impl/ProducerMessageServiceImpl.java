package com.ruoyi.message.service.impl;

import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.message.mapper.ProducerMessageMapper;
import com.ruoyi.message.domain.ProducerMessage;
import com.ruoyi.message.service.IProducerMessageService;

/**
 * 发送方消息Service业务层处理
 *
 * @author ruoyi
 * @date 2023-02-01
 */
@Service
public class ProducerMessageServiceImpl implements IProducerMessageService
{
    @Autowired
    private ProducerMessageMapper producerMessageMapper;

    /**
     * 查询发送方消息
     *
     * @param id 发送方消息主键
     * @return 发送方消息
     */
    @Override
    public ProducerMessage selectProducerMessageById(Integer id)
    {
        return producerMessageMapper.selectProducerMessageById(id);
    }

    /**
     * 查询发送方消息列表
     *
     * @param producerMessage 发送方消息
     * @return 发送方消息
     */
    @Override
    public List<ProducerMessage> selectProducerMessageList(ProducerMessage producerMessage)
    {
        return producerMessageMapper.selectProducerMessageList(producerMessage);
    }

    /**
     * 新增发送方消息
     *
     * @param producerMessage 发送方消息
     * @return 结果
     */
    @Override
    public int insertProducerMessage(ProducerMessage producerMessage)
    {
        return producerMessageMapper.insertProducerMessage(producerMessage);
    }

    /**
     * 修改发送方消息
     *
     * @param producerMessage 发送方消息
     * @return 结果
     */
    @Override
    public int updateProducerMessage(ProducerMessage producerMessage)
    {
        return producerMessageMapper.updateProducerMessage(producerMessage);
    }

    /**
     * 批量删除发送方消息
     *
     * @param ids 需要删除的发送方消息主键
     * @return 结果
     */
    @Override
    public int deleteProducerMessageByIds(Integer[] ids)
    {
        return producerMessageMapper.deleteProducerMessageByIds(ids);
    }

    /**
     * 删除发送方消息信息
     *
     * @param id 发送方消息主键
     * @return 结果
     */
    @Override
    public int deleteProducerMessageById(Integer id)
    {
        return producerMessageMapper.deleteProducerMessageById(id);
    }


    /**
     * 查询未发送信息
     */
    @Override
    public List<ProducerMessage> selectunsend(Integer send, Date senddate){
        return producerMessageMapper.selectunsend(send,senddate);
    }

    /**
     *更新
     */
    public boolean updatesend(Integer id) {
        return producerMessageMapper.updatesend(id)>0;
    };
}
