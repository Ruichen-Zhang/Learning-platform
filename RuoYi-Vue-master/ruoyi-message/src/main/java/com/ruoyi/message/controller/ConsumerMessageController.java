package com.ruoyi.message.controller;

import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpServletResponse;
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
import com.ruoyi.message.domain.ConsumerMessage;
import com.ruoyi.message.service.IConsumerMessageService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 接收方消息Controller
 *
 * @author ruoyi
 * @date 2023-02-01
 */
@RestController
@RequestMapping("/message/ConsumerMessage")
public class ConsumerMessageController extends BaseController
{
    @Autowired
    private IConsumerMessageService consumerMessageService;

    /**
     * 查询接收方消息列表
     */
    @PreAuthorize("@ss.hasPermi('message:ConsumerMessage:list')")
    @GetMapping("/list")
    public TableDataInfo list(ConsumerMessage consumerMessage)
    {
        startPage();
        List<ConsumerMessage> list = consumerMessageService.selectConsumerMessageList(consumerMessage);
        return getDataTable(list);
    }

    /**
     * 导出接收方消息列表
     */
    @PreAuthorize("@ss.hasPermi('message:ConsumerMessage:export')")
    @Log(title = "接收方消息", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, ConsumerMessage consumerMessage)
    {
        List<ConsumerMessage> list = consumerMessageService.selectConsumerMessageList(consumerMessage);
        ExcelUtil<ConsumerMessage> util = new ExcelUtil<ConsumerMessage>(ConsumerMessage.class);
        util.exportExcel(response, list, "接收方消息数据");
    }

    /**
     * 获取接收方消息详细信息
     */
    @PreAuthorize("@ss.hasPermi('message:ConsumerMessage:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Integer id)
    {
        return success(consumerMessageService.selectConsumerMessageById(id));
    }

    /**
     * 新增接收方消息
     */
    @PreAuthorize("@ss.hasPermi('message:ConsumerMessage:add')")
    @Log(title = "接收方消息", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody ConsumerMessage consumerMessage)
    {
        return toAjax(consumerMessageService.insertConsumerMessage(consumerMessage));
    }

    /**
     * 修改接收方消息
     */
    @PreAuthorize("@ss.hasPermi('message:ConsumerMessage:edit')")
    @Log(title = "接收方消息", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody ConsumerMessage consumerMessage)
    {
        return toAjax(consumerMessageService.updateConsumerMessage(consumerMessage));
    }

    /**
     * 删除接收方消息
     */
    @PreAuthorize("@ss.hasPermi('message:ConsumerMessage:remove')")
    @Log(title = "接收方消息", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Integer[] ids)
    {
        return toAjax(consumerMessageService.deleteConsumerMessageByIds(ids));
    }

    public void addConsumerMessage(Integer producerid, Integer consumerid, String messagecontent, Date producerdate, Integer readjudge, String messagelink){

        //保存到对象中
        ConsumerMessage consumerMessage = new ConsumerMessage();
        consumerMessage.setProducerid(producerid);
        consumerMessage.setConsumerid(consumerid);
        consumerMessage.setMessagecontent(messagecontent);
        Date producerdate2 = new Date();
        consumerMessage.setProducerdate(producerdate2);
        consumerMessage.setReadjudge(readjudge);
        consumerMessage.setMessagelink(messagelink);
        consumerMessageService.insert(consumerMessage);

    }


    /**
     * 修改已读未读
     */
//    @PreAuthorize("@ss.hasPermi('message:ConsumerMessage:read')")
    @Log(title = "接收方消息", businessType = BusinessType.INSERT)
    @PutMapping("/{id}/{readjudge}")
    public AjaxResult changeRead(@PathVariable Integer id,@PathVariable Integer readjudge){
        ConsumerMessage consumerMessage = new ConsumerMessage();
        consumerMessage.setId(Integer.valueOf(id));
        consumerMessage.setReadjudge(Integer.valueOf(readjudge));
        Date producerdate2 = new Date();
        consumerMessage.setReadtime(producerdate2);
        return toAjax(consumerMessageService.updateread(consumerMessage));
    }
}
