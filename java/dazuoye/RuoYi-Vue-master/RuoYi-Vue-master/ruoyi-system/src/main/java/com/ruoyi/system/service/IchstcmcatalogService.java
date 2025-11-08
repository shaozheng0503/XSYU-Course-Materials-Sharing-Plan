package com.ruoyi.system.service;

import java.util.List;
import com.ruoyi.system.domain.chstcmcatalog;

/**
 * 药品信息Service接口
 * 
 * @author ruoyi
 * @date 2024-12-08
 */
public interface IchstcmcatalogService 
{
    /**
     * 查询药品信息
     * 
     * @param ID 药品信息主键
     * @return 药品信息
     */
    public chstcmcatalog selectchstcmcatalogByID(Long ID);

    /**
     * 查询药品信息列表
     * 
     * @param chstcmcatalog 药品信息
     * @return 药品信息集合
     */
    public List<chstcmcatalog> selectchstcmcatalogList(chstcmcatalog chstcmcatalog);

    /**
     * 新增药品信息
     * 
     * @param chstcmcatalog 药品信息
     * @return 结果
     */
    public int insertchstcmcatalog(chstcmcatalog chstcmcatalog);

    /**
     * 修改药品信息
     * 
     * @param chstcmcatalog 药品信息
     * @return 结果
     */
    public int updatechstcmcatalog(chstcmcatalog chstcmcatalog);

    /**
     * 批量删除药品信息
     * 
     * @param IDs 需要删除的药品信息主键集合
     * @return 结果
     */
    public int deletechstcmcatalogByIDs(Long[] IDs);

    /**
     * 删除药品信息信息
     * 
     * @param ID 药品信息主键
     * @return 结果
     */
    public int deletechstcmcatalogByID(Long ID);
}
