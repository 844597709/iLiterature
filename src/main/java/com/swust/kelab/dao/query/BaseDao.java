package com.swust.kelab.dao.query;


import com.google.common.collect.Lists;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.swust.kelab.dao.base.PageInfo;
import com.swust.kelab.dao.base.PageResult;

import java.lang.reflect.ParameterizedType;
import java.util.List;

/**
 * 包含增、删、改、查等基本操作
 */
public abstract class BaseDao<T extends BaseModel> extends MongoTemplateDao<T>{
    /**
     * 保存或更新
     * @param entity
     */
    public abstract boolean updateOrSave(T entity);

    /**
     * 删除
     * @param id
     */
    public abstract boolean delete(Integer id);

    /**
     * 根据查询条件分页查询
     */
    public PageResult<T> query(DBObject query, DBObject fields, PageInfo pageInfo) {
        Long count = getDBCollection().count(query);
        // 分页信息
        if (pageInfo == null) {
            pageInfo = new PageInfo();
            pageInfo.setCurrentPage(1);
            pageInfo.setPageSize(count.intValue()); // 如果没有传递pageInfo信息，默认查找全部数据
        }
        pageInfo.setTotal(count.intValue());
        DBCursor cursor = null;
        if(fields==null){ //没有传递字段默认为全部字段
            cursor = super.getDBCollection().find(query).skip((pageInfo.getCurrentPage() - 1) * pageInfo.getPageSize()).limit(pageInfo.getPageSize());
        }else{
            cursor = super.getDBCollection().find(query, fields).skip((pageInfo.getCurrentPage() - 1) * pageInfo.getPageSize()).limit(pageInfo.getPageSize());
        }
        List<T> list = Lists.newArrayList();
        Class<T> clazz = (Class<T>)((ParameterizedType)getClass().getGenericSuperclass()).getActualTypeArguments()[0];
        while(cursor.hasNext()){
            list.add(decode(cursor.next(), clazz));
        }
        PageResult<T> result = new PageResult<T>();
        result.setContents(list);
        result.setPageInfo(pageInfo);
        return result;
    }

    /**
     * 根据id查询
     * @param id
     */
    public abstract T findById(Integer id);

    /**
     * 查询多个id记录值
     * @param ids
     */
    public abstract List<T> getBusinessesByIds(List<Integer> ids);
}
