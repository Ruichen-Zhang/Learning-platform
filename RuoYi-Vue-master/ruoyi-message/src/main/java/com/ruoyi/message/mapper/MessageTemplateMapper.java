package com.ruoyi.message.mapper;

import java.util.List;
import com.ruoyi.message.domain.MessageTemplate;
import org.apache.ibatis.annotations.Mapper;

/**
 * 消息模版Mapper接口
 *
 * @author ruoyi
 * @date 2023-02-01
 */
@Mapper
public interface MessageTemplateMapper
{
    /**
     * 查询消息模版
     *
     * @param id 消息模版主键
     * @return 消息模版
     */
    public MessageTemplate selectMessageTemplateById(Integer id);

    /**
     * 查询消息模版列表
     *
     * @param messageTemplate 消息模版
     * @return 消息模版集合
     */
    public List<MessageTemplate> selectMessageTemplateList(MessageTemplate messageTemplate);

    /**
     * 新增消息模版
     *
     * @param messageTemplate 消息模版
     * @return 结果
     */
    public int insertMessageTemplate(MessageTemplate messageTemplate);

    /**
     * 修改消息模版
     *
     * @param messageTemplate 消息模版
     * @return 结果
     */
    public int updateMessageTemplate(MessageTemplate messageTemplate);

    /**
     * 删除消息模版
     *
     * @param id 消息模版主键
     * @return 结果
     */
    public int deleteMessageTemplateById(Integer id);

    /**
     * 批量删除消息模版
     *
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteMessageTemplateByIds(Integer[] ids);
    /**
     * 根据id查询整个对象
     */
    public MessageTemplate selectByid(Integer id);
}
