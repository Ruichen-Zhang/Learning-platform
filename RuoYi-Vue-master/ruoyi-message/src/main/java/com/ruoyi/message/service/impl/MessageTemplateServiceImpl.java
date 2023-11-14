package com.ruoyi.message.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.message.mapper.MessageTemplateMapper;
import com.ruoyi.message.domain.MessageTemplate;
import com.ruoyi.message.service.IMessageTemplateService;

/**
 * 消息模版Service业务层处理
 *
 * @author ruoyi
 * @date 2023-02-01
 */
@Service
public class MessageTemplateServiceImpl implements IMessageTemplateService
{
    @Autowired
    private MessageTemplateMapper messageTemplateMapper;

    /**
     * 查询消息模版
     *
     * @param id 消息模版主键
     * @return 消息模版
     */
    @Override
    public MessageTemplate selectMessageTemplateById(Integer id)
    {
        return messageTemplateMapper.selectMessageTemplateById(id);
    }

    /**
     * 查询消息模版列表
     *
     * @param messageTemplate 消息模版
     * @return 消息模版
     */
    @Override
    public List<MessageTemplate> selectMessageTemplateList(MessageTemplate messageTemplate)
    {
        return messageTemplateMapper.selectMessageTemplateList(messageTemplate);
    }

    /**
     * 新增消息模版
     *
     * @param messageTemplate 消息模版
     * @return 结果
     */
    @Override
    public int insertMessageTemplate(MessageTemplate messageTemplate)
    {
        return messageTemplateMapper.insertMessageTemplate(messageTemplate);
    }

    /**
     * 修改消息模版
     *
     * @param messageTemplate 消息模版
     * @return 结果
     */
    @Override
    public int updateMessageTemplate(MessageTemplate messageTemplate)
    {
        return messageTemplateMapper.updateMessageTemplate(messageTemplate);
    }

    /**
     * 批量删除消息模版
     *
     * @param ids 需要删除的消息模版主键
     * @return 结果
     */
    @Override
    public int deleteMessageTemplateByIds(Integer[] ids)
    {
        return messageTemplateMapper.deleteMessageTemplateByIds(ids);
    }

    /**
     * 删除消息模版信息
     *
     * @param id 消息模版主键
     * @return 结果
     */
    @Override
    public int deleteMessageTemplateById(Integer id)
    {
        return messageTemplateMapper.deleteMessageTemplateById(id);
    }

    /**
     * 根据id查询整个对象
     */
    @Override
    public MessageTemplate selectByid(Integer id){
        return messageTemplateMapper.selectByid(id);
    }

}
