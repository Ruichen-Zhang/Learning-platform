package com.ruoyi.message.controller;

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
import com.ruoyi.message.domain.ProducerMessage;
import com.ruoyi.message.service.IProducerMessageService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 发送方消息Controller
 * 
 * @author ruoyi
 * @date 2023-02-01
 */
@RestController
@RequestMapping("/message/ProducerMessage")
public class ProducerMessageController extends BaseController
{
    @Autowired
    private IProducerMessageService producerMessageService;

    /**
     * 查询发送方消息列表
     */
    @PreAuthorize("@ss.hasPermi('message:ProducerMessage:list')")
    @GetMapping("/list")
    public TableDataInfo list(ProducerMessage producerMessage)
    {
        startPage();
        List<ProducerMessage> list = producerMessageService.selectProducerMessageList(producerMessage);
        return getDataTable(list);
    }

    /**
     * 导出发送方消息列表
     */
    @PreAuthorize("@ss.hasPermi('message:ProducerMessage:export')")
    @Log(title = "发送方消息", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, ProducerMessage producerMessage)
    {
        List<ProducerMessage> list = producerMessageService.selectProducerMessageList(producerMessage);
        ExcelUtil<ProducerMessage> util = new ExcelUtil<ProducerMessage>(ProducerMessage.class);
        util.exportExcel(response, list, "发送方消息数据");
    }

    /**
     * 获取发送方消息详细信息
     */
    @PreAuthorize("@ss.hasPermi('message:ProducerMessage:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Integer id)
    {
        return success(producerMessageService.selectProducerMessageById(id));
    }

    /**
     * 新增发送方消息
     */
    @PreAuthorize("@ss.hasPermi('message:ProducerMessage:add')")
    @Log(title = "发送方消息", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody ProducerMessage producerMessage)
    {
        return toAjax(producerMessageService.insertProducerMessage(producerMessage));
    }

    /**
     * 修改发送方消息
     */
    @PreAuthorize("@ss.hasPermi('message:ProducerMessage:edit')")
    @Log(title = "发送方消息", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody ProducerMessage producerMessage)
    {
        return toAjax(producerMessageService.updateProducerMessage(producerMessage));
    }

    /**
     * 删除发送方消息
     */
    @PreAuthorize("@ss.hasPermi('message:ProducerMessage:remove')")
    @Log(title = "发送方消息", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Integer[] ids)
    {
        return toAjax(producerMessageService.deleteProducerMessageByIds(ids));
    }
}
