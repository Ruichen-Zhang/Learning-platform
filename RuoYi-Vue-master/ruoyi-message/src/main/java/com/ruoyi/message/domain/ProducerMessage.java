package com.ruoyi.message.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 发送方消息对象 ProducerMessage
 *
 * @author ruoyi
 * @date 2023-02-01
 */
public class ProducerMessage extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键 */
    private Integer id;

    /** 发送方id */
    @Excel(name = "发送方id")
    private Integer producerid;

    /** 接收方id */
    @Excel(name = "接收方id")
    private Integer consumerid;

    /** 消息内容 */
    @Excel(name = "消息内容")
    private String messagecontent;

    /** 接收方类型 */
    @Excel(name = "接收方类型")
    private Integer consumerjudge;

    /** 发送时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Excel(name = "发送时间", width = 30, dateFormat = "yyyy-MM-dd HH:mm:ss")
    private Date producerdate;

    /** 消息链接 */
    @Excel(name = "消息链接")
    private String messagelink;

    /** 是否已发送 */
    @Excel(name = "是否已发送")
    private Integer send;

    /** 预定发送时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Excel(name = "预定发送时间", width = 30, dateFormat = "yyyy-MM-dd HH:mm:ss")
    private Date senddate;

    /** 删除标志 */
    private Integer delFlag;

    public void setId(Integer id)
    {
        this.id = id;
    }

    public Integer getId()
    {
        return id;
    }
    public void setProducerid(Integer producerid)
    {
        this.producerid = producerid;
    }

    public Integer getProducerid()
    {
        return producerid;
    }
    public void setConsumerid(Integer consumerid)
    {
        this.consumerid = consumerid;
    }

    public Integer getConsumerid()
    {
        return consumerid;
    }
    public void setMessagecontent(String messagecontent)
    {
        this.messagecontent = messagecontent;
    }

    public String getMessagecontent()
    {
        return messagecontent;
    }
    public void setConsumerjudge(Integer consumerjudge)
    {
        this.consumerjudge = consumerjudge;
    }

    public Integer getConsumerjudge()
    {
        return consumerjudge;
    }
    public void setProducerdate(Date producerdate)
    {
        this.producerdate = producerdate;
    }

    public Date getProducerdate()
    {
        return producerdate;
    }
    public void setMessagelink(String messagelink)
    {
        this.messagelink = messagelink;
    }

    public String getMessagelink()
    {
        return messagelink;
    }
    public void setSend(Integer send)
    {
        this.send = send;
    }

    public Integer getSend()
    {
        return send;
    }
    public void setSenddate(Date senddate)
    {
        this.senddate = senddate;
    }

    public Date getSenddate()
    {
        return senddate;
    }
    public void setDelFlag(Integer delFlag)
    {
        this.delFlag = delFlag;
    }

    public Integer getDelFlag()
    {
        return delFlag;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("producerid", getProducerid())
            .append("consumerid", getConsumerid())
            .append("messagecontent", getMessagecontent())
            .append("consumerjudge", getConsumerjudge())
            .append("producerdate", getProducerdate())
            .append("messagelink", getMessagelink())
            .append("send", getSend())
            .append("senddate", getSenddate())
            .append("delFlag", getDelFlag())
            .toString();
    }
}
