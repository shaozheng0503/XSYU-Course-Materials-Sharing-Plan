package com.ruoyi.system.controller;

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
import com.ruoyi.system.domain.chstcmcatalog;
import com.ruoyi.system.service.IchstcmcatalogService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 药品信息Controller
 * 
 * @author ruoyi
 * @date 2024-12-08
 */
@RestController
@RequestMapping("/system/chstcmcatalog")
public class chstcmcatalogController extends BaseController
{
    @Autowired
    private IchstcmcatalogService chstcmcatalogService;

    /**
     * 查询药品信息列表
     */
    @PreAuthorize("@ss.hasPermi('system:chstcmcatalog:list')")
    @GetMapping("/list")
    public TableDataInfo list(chstcmcatalog chstcmcatalog)
    {
        startPage();
        List<chstcmcatalog> list = chstcmcatalogService.selectchstcmcatalogList(chstcmcatalog);
        return getDataTable(list);
    }

    /**
     * 导出药品信息列表
     */
    @PreAuthorize("@ss.hasPermi('system:chstcmcatalog:export')")
    @Log(title = "药品信息", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, chstcmcatalog chstcmcatalog)
    {
        List<chstcmcatalog> list = chstcmcatalogService.selectchstcmcatalogList(chstcmcatalog);
        ExcelUtil<chstcmcatalog> util = new ExcelUtil<chstcmcatalog>(chstcmcatalog.class);
        util.exportExcel(response, list, "药品信息数据");
    }

    /**
     * 获取药品信息详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:chstcmcatalog:query')")
    @GetMapping(value = "/{ID}")
    public AjaxResult getInfo(@PathVariable("ID") Long ID)
    {
        return success(chstcmcatalogService.selectchstcmcatalogByID(ID));
    }

    /**
     * 新增药品信息
     */
    @PreAuthorize("@ss.hasPermi('system:chstcmcatalog:add')")
    @Log(title = "药品信息", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody chstcmcatalog chstcmcatalog)
    {
        return toAjax(chstcmcatalogService.insertchstcmcatalog(chstcmcatalog));
    }

    /**
     * 修改药品信息
     */
    @PreAuthorize("@ss.hasPermi('system:chstcmcatalog:edit')")
    @Log(title = "药品信息", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody chstcmcatalog chstcmcatalog)
    {
        return toAjax(chstcmcatalogService.updatechstcmcatalog(chstcmcatalog));
    }

    /**
     * 删除药品信息
     */
    @PreAuthorize("@ss.hasPermi('system:chstcmcatalog:remove')")
    @Log(title = "药品信息", businessType = BusinessType.DELETE)
	@DeleteMapping("/{IDs}")
    public AjaxResult remove(@PathVariable Long[] IDs)
    {
        return toAjax(chstcmcatalogService.deletechstcmcatalogByIDs(IDs));
    }
}
