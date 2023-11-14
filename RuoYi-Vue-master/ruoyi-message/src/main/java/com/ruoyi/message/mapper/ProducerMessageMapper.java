package com.ruoyi.message.mapper;

import java.util.Date;
import java.util.List;
import com.ruoyi.message.domain.ProducerMessage;
import org.apache.ibatis.annotations.Mapper;

/**
 * 发送方消息Mapper接口
 *
 * @author ruoyi
 * @date 2023-02-01
 */
@Mapper
public interface ProducerMessageMapper
{
    /**
     * 查询发送方消息
     *
     * @param id 发送方消息主键
     * @return 发送方消息
     */
    public ProducerMessage selectProducerMessageById(Integer id);

    /**
     * 查询发送方消息列表
     *
     * @param producerMessage 发送方消息
     * @return 发送方消息集合
     */
    public List<ProducerMessage> selectProducerMessageList(ProducerMessage producerMessage);

    /**
     * 新增发送方消息
     *
     * @param producerMessage 发送方消息
     * @return 结果
     */
    public int insertProducerMessage(ProducerMessage producerMessage);

    /**
     * 修改发送方消息
     *
     * @param producerMessage 发送方消息
     * @return 结果
     */
    public int updateProducerMessage(ProducerMessage producerMessage);

    /**
     * 删除发送方消息
     *
     * @param id 发送方消息主键
     * @return 结果
     */
    public int deleteProducerMessageById(Integer id);

    /**
     * 批量删除发送方消息
     *
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteProducerMessageByIds(Integer[] ids);


    /**
     * 查询未发送消息
     */

    public List<ProducerMessage> selectunsend(Integer send, Date senddate);


    /**
     *更新
     */
    public int updatesend(Integer id);
}
