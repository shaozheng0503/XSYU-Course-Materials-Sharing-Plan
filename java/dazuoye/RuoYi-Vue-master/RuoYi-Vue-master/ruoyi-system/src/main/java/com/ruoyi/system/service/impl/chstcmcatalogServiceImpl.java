package com.ruoyi.system.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.chstcmcatalogMapper;
import com.ruoyi.system.domain.chstcmcatalog;
import com.ruoyi.system.service.IchstcmcatalogService;

/**
 * 药品信息Service业务层处理
 *
 * @author ruoyi
 * @date 2024-12-08
 */
@Service
public class chstcmcatalogServiceImpl implements IchstcmcatalogService
{
    @Autowired
    private chstcmcatalogMapper chstcmcatalogMapper;

    /**
     * 查询药品信息
     *
     * @param ID 药品信息主键
     * @return 药品信息
     */
    @Override
    public chstcmcatalog selectchstcmcatalogByID(Long ID)
    {
        return chstcmcatalogMapper.selectchstcmcatalogById(ID);
    }

    /**
     * 查询药品信息列表
     *
     * @param chstcmcatalog 药品信息
     * @return 药品信息
     */
    @Override
    public List<chstcmcatalog> selectchstcmcatalogList(chstcmcatalog chstcmcatalog)
    {
        return chstcmcatalogMapper.selectchstcmcatalogList(chstcmcatalog);
    }

    /**
     * 新增药品信息
     *
     * @param chstcmcatalog 药品信息
     * @return 结果
     */
    @Override
    public int insertchstcmcatalog(chstcmcatalog chstcmcatalog)
    {
        return chstcmcatalogMapper.insertchstcmcatalog(chstcmcatalog);
    }

    /**
     * 修改药品信息
     *
     * @param chstcmcatalog 药品信息
     * @return 结果
     */
    @Override
    public int updatechstcmcatalog(chstcmcatalog chstcmcatalog)
    {
        return chstcmcatalogMapper.updatechstcmcatalog(chstcmcatalog);
    }

    /**
     * 批量删除药品信息
     *
     * @param IDs 需要删除的药品信息主键
     * @return 结果
     */
    @Override
    public int deletechstcmcatalogByIDs(Long[] IDs)
    {
        return chstcmcatalogMapper.deletechstcmcatalogbyids(IDs);
    }

    /**
     * 删除药品信息信息
     *
     * @param ID 药品信息主键
     * @return 结果
     */
    @Override
    public int deletechstcmcatalogByID(Long ID)
    {
        return chstcmcatalogMapper.deletechstcmcatalogbyid(ID);
    }
}
