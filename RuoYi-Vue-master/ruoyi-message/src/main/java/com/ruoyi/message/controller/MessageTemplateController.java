package com.ruoyi.message.controller;

import java.io.StringWriter;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import javax.servlet.http.HttpServletResponse;

import com.ruoyi.message.domain.ProducerMessage;
import com.ruoyi.message.service.IProducerMessageService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.message.domain.MessageTemplate;
import com.ruoyi.message.service.IMessageTemplateService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;
import org.apache.velocity.app.VelocityEngine;

/**
 * 消息模版Controller
 *
 * @author ruoyi
 * @date 2023-02-01
 */
@RestController
@RequestMapping("/message/MessageTemplate")
public class MessageTemplateController extends BaseController
{
    @Autowired
    private IMessageTemplateService messageTemplateService;
    @Autowired
    private IProducerMessageService producerMessageService;
    private static Properties props = new Properties();

    static {
        props.setProperty(Velocity.INPUT_ENCODING, "UTF-8");
        props.setProperty(Velocity.RESOURCE_LOADER, "class");
        props.setProperty("class.resource.loader.class",
                "org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader");
    }
    /**
     * 查询消息模版列表
     */
    @PreAuthorize("@ss.hasPermi('message:MessageTemplate:list')")
    @GetMapping("/list")
    public TableDataInfo list(MessageTemplate messageTemplate)
    {
        startPage();
        List<MessageTemplate> list = messageTemplateService.selectMessageTemplateList(messageTemplate);
        return getDataTable(list);
    }

    /**
     * 导出消息模版列表
     */
    @PreAuthorize("@ss.hasPermi('message:MessageTemplate:export')")
    @Log(title = "消息模版", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, MessageTemplate messageTemplate)
    {
        List<MessageTemplate> list = messageTemplateService.selectMessageTemplateList(messageTemplate);
        ExcelUtil<MessageTemplate> util = new ExcelUtil<MessageTemplate>(MessageTemplate.class);
        util.exportExcel(response, list, "消息模版数据");
    }

    /**
     * 获取消息模版详细信息
     */
    @PreAuthorize("@ss.hasPermi('message:MessageTemplate:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Integer id)
    {
        return success(messageTemplateService.selectMessageTemplateById(id));
    }

    /**
     * 新增消息模版
     */
    @PreAuthorize("@ss.hasPermi('message:MessageTemplate:add')")
    @Log(title = "消息模版", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody MessageTemplate messageTemplate)
    {
        return toAjax(messageTemplateService.insertMessageTemplate(messageTemplate));
    }

    /**
     * 修改消息模版
     */
    @PreAuthorize("@ss.hasPermi('message:MessageTemplate:edit')")
    @Log(title = "消息模版", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody MessageTemplate messageTemplate)
    {
        return toAjax(messageTemplateService.updateMessageTemplate(messageTemplate));
    }

    /**
     * 删除消息模版
     */
    @PreAuthorize("@ss.hasPermi('message:MessageTemplate:remove')")
    @Log(title = "消息模版", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Integer[] ids)
    {
        return toAjax(messageTemplateService.deleteMessageTemplateByIds(ids));
    }



    /**
     * 根据模版发送消息
     */
//    @PreAuthorize("@ss.hasPermi('message:MessageTemplate:sendbytemplate')")
    @PostMapping("/sendbytemplate")
    public AjaxResult sendbytemplate(@RequestBody Map<String,String> map) throws Exception {
        String id=map.get("id");
        MessageTemplate messageTemplate=messageTemplateService.selectByid(Integer.valueOf(id));
        String messagecontent=messageTemplate.getMessagecontent();



        VelocityEngine engine = new VelocityEngine(props);
        VelocityContext context = new VelocityContext();
        for(Map.Entry<String,String> entry: map.entrySet()){
            context.put(entry.getKey(),entry.getValue());
        }
        StringWriter writer = new StringWriter();
        engine.evaluate(context, writer, "", messagecontent);
        messagecontent=writer.toString();


        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date producerdate = new Date();
        if(map.get("producerdate") != null){
            try {
                producerdate = dateFormat.parse(map.get("producerdate"));
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }


        DateFormat dateFormat2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date producerdate2 = new Date();
        if(map.get("senddate") != null) {

            try {
                producerdate2 = dateFormat2.parse(map.get("senddate"));
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }


        ProducerMessage producerMessage = new ProducerMessage();
        producerMessage.setProducerid(Integer.parseInt(map.get("producerid")));
        producerMessage.setConsumerid(Integer.parseInt(map.get("consumerid")));
        producerMessage.setMessagecontent(messagecontent);
        producerMessage.setConsumerjudge(Integer.parseInt(map.get("consumerjudge")));
        producerMessage.setProducerdate(producerdate);
        producerMessage.setMessagelink(messageTemplate.getMessagelink());
        producerMessage.setSend(0);
        producerMessage.setSenddate(producerdate2);

        return toAjax(producerMessageService.insertProducerMessage(producerMessage));
    }



}
