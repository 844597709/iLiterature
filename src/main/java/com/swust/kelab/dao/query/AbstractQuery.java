package com.swust.kelab.dao.query;

import com.swust.kelab.dao.base.PageInfo;

import java.io.Serializable;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;

/**
 * 查询基础类
 * <p>
 * 如果查询结果需要使用分页，则请设置{@link PageInfo},默认不使用分页查询
 */
public abstract class AbstractQuery implements Serializable {
    private static final long serialVersionUID = -2379671566690754406L;

    /**
     * 记录开始时间
     */
    private Date startDate;

    /**
     * 记录结束时间
     */
    private Date endDate;

    /**
     * 操作者名称
     */
    private String operatorName;

    /**
     * 分页信息
     */
    private PageInfo pageInfo;
    /**
     * 排序信息，注意add顺序
     */
    private LinkedHashMap<String, Integer> sortInfo = new LinkedHashMap<String, Integer>();

    /**
     * 模糊查询多个字段
     */
    private List<String> searchFields;

    /**
     * 模糊查询的value
     */
    private String searchValue;

    public List<String> getSearchFields() {
        return searchFields;
    }

    public void setSearchFields(List<String> searchFields) {
        this.searchFields = searchFields;
    }

    public String getSearchValue() {
        return searchValue;
    }

    public void setSearchValue(String searchValue) {
        this.searchValue = searchValue;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public String getOperatorName() {
        return operatorName;
    }

    public void setOperatorName(String operatorName) {
        this.operatorName = operatorName;
    }

    public PageInfo getPageInfo() {
        return pageInfo;
    }

    public void setPageInfo(PageInfo pageInfo) {
        this.pageInfo = pageInfo;
    }

    /**
     * 添加排序参数 注意：如果有多个排序条件，请把主排序条件先add,默认以创建时间倒序排列
     *
     * @param key
     * @param sort -1为倒序，1为正序
     */
    protected void addSortInfo(String key, Integer sort) {
        if (sort != 1 && sort != -1) {
            return;
        }

        sortInfo.put(key, sort);
    }

    public LinkedHashMap<String, Integer> getSortInfo() {
        return sortInfo;
    }
}
