package com.ruoyi.system.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 药品信息对象 chstcmcatalog
 *
 * @author ruoyi
 * @date 2024-12-08
 */
public class chstcmcatalog extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** ID */
    private Long id;

    /** 一级分类 */
    @Excel(name = "一级分类")
    private String level1;

    /** 二级分类，为空时应填入 */
    @Excel(name = "二级分类，为空时应填入")
    private String level2;

    /** 三级分类，为空时应填入 */
    @Excel(name = "三级分类，为空时应填入")
    private String level3;

    /** 药品类型 */
    @Excel(name = "药品类型")
    private String type;

    /** 药品名称 */
    @Excel(name = "药品名称")
    private String drugName;

    /** 使用限制 */
    @Excel(name = "使用限制")
    private String constrain;

    public void setId(Long id)
    {
        this.id = id;
    }

    public Long getId()
    {
        return id;
    }
    public void setLevel1(String level1)
    {
        this.level1 = level1;
    }

    public String getLevel1()
    {
        return level1;
    }
    public void setLevel2(String level2)
    {
        this.level2 = level2;
    }

    public String getLevel2()
    {
        return level2;
    }
    public void setLevel3(String level3)
    {
        this.level3 = level3;
    }

    public String getLevel3()
    {
        return level3;
    }
    public void setType(String type)
    {
        this.type = type;
    }

    public String getType()
    {
        return type;
    }
    public void setDrugName(String drugName)
    {
        this.drugName = drugName;
    }

    public String getDrugName()
    {
        return drugName;
    }
    public void setConstrain(String constrain)
    {
        this.constrain = constrain;
    }

    public String getConstrain()
    {
        return constrain;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
                .append("id", getId())
                .append("level1", getLevel1())
                .append("level2", getLevel2())
                .append("level3", getLevel3())
                .append("type", getType())
                .append("drugName", getDrugName())
                .append("constrain", getConstrain())
                .toString();
    }
}
