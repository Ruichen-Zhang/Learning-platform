package com.ruoyi.message.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 消息模版对象 MessageTemplate
 * 
 * @author ruoyi
 * @date 2023-02-01
 */
public class MessageTemplate extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键 */
    private Integer id;

    /** 模版名称 */
    @Excel(name = "模版名称")
    private String messagetemplatename;

    /** 模版拥有者 */
    @Excel(name = "模版拥有者")
    private Integer templateownerid;

    /** 消息消费者 */
    @Excel(name = "消息消费者")
    private Integer consumerid;

    /** 消息内容 */
    @Excel(name = "消息内容")
    private String messagecontent;

    /** 消费者类型 */
    @Excel(name = "消费者类型")
    private Integer consumerjudge;

    /** 消息链接 */
    @Excel(name = "消息链接")
    private String messagelink;

    /** 删除标记 */
    private Integer delFlag;

    public void setId(Integer id) 
    {
        this.id = id;
    }

    public Integer getId() 
    {
        return id;
    }
    public void setMessagetemplatename(String messagetemplatename) 
    {
        this.messagetemplatename = messagetemplatename;
    }

    public String getMessagetemplatename() 
    {
        return messagetemplatename;
    }
    public void setTemplateownerid(Integer templateownerid) 
    {
        this.templateownerid = templateownerid;
    }

    public Integer getTemplateownerid() 
    {
        return templateownerid;
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
            .append("messagetemplatename", getMessagetemplatename())
            .append("templateownerid", getTemplateownerid())
            .append("consumerid", getConsumerid())
            .append("messagecontent", getMessagecontent())
            .append("consumerjudge", getConsumerjudge())
            .append("messagelink", getMessagelink())
            .append("delFlag", getDelFlag())
            .toString();
    }
}
