package com.ruoyi.message.mapper;

import java.util.List;
import com.ruoyi.message.domain.ConsumerMessage;
import org.apache.ibatis.annotations.Mapper;

/**
 * 接收方消息Mapper接口
 *
 * @author ruoyi
 * @date 2023-02-01
 */
@Mapper
public interface ConsumerMessageMapper
{
    /**
     * 查询接收方消息
     *
     * @param id 接收方消息主键
     * @return 接收方消息
     */
    public ConsumerMessage selectConsumerMessageById(Integer id);

    /**
     * 查询接收方消息列表
     *
     * @param consumerMessage 接收方消息
     * @return 接收方消息集合
     */
    public List<ConsumerMessage> selectConsumerMessageList(ConsumerMessage consumerMessage);

    /**
     * 新增接收方消息
     *
     * @param consumerMessage 接收方消息
     * @return 结果
     */
    public int insertConsumerMessage(ConsumerMessage consumerMessage);

    /**
     * 修改接收方消息
     *
     * @param consumerMessage 接收方消息
     * @return 结果
     */
    public int updateConsumerMessage(ConsumerMessage consumerMessage);

    /**
     * 删除接收方消息
     *
     * @param id 接收方消息主键
     * @return 结果
     */
    public int deleteConsumerMessageById(Integer id);

    /**
     * 批量删除接收方消息
     *
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteConsumerMessageByIds(Integer[] ids);

    /**
     *增加
     */
    public int insert(ConsumerMessage consumerMessage);

    /**
     *更新
     */
    public int updateread(ConsumerMessage consumerMessage);

}
