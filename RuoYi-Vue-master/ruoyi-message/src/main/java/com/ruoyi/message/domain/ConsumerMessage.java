package com.ruoyi.message.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 接收方消息对象 ConsumerMessage
 *
 * @author ruoyi
 * @date 2023-02-01
 */
public class ConsumerMessage extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键 */
    private Integer id;

    /** 消息生产者id */
    @Excel(name = "消息生产者id")
    private Integer producerid;

    /** 消息消费者id */
    @Excel(name = "消息消费者id")
    private Integer consumerid;

    /** 消息内容 */
    @Excel(name = "消息内容")
    private String messagecontent;

    /** 发送时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Excel(name = "发送时间", width = 30, dateFormat = "yyyy-MM-dd HH:mm:ss")
    private Date producerdate;

    /** 是否已读 */
    @Excel(name = "是否已读")
    private Integer readjudge;

    /** 消息链接 */
    @Excel(name = "消息链接")
    private String messagelink;

    /** 删除标志 */
    private Integer delFlag;

    /** 阅读时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Excel(name = "阅读时间", width = 30, dateFormat = "yyyy-MM-dd HH:mm:ss")
    private Date readtime;

    public Date getReadtime() {
        return readtime;
    }

    public void setReadtime(Date readtime) {
        this.readtime = readtime;
    }

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
    public void setProducerdate(Date producerdate)
    {
        this.producerdate = producerdate;
    }

    public Date getProducerdate()
    {
        return producerdate;
    }
    public void setReadjudge(Integer readjudge)
    {
        this.readjudge = readjudge;
    }

    public Integer getReadjudge()
    {
        return readjudge;
    }
    public void setMessagelink(String messagelink)
    {
        this.messagelink = messagelink;
    }

    public String getMessagelink()
    {
        return messagelink;
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
            .append("producerdate", getProducerdate())
            .append("readjudge", getReadjudge())
            .append("messagelink", getMessagelink())
            .append("delFlag", getDelFlag())
            .toString();
    }
}
