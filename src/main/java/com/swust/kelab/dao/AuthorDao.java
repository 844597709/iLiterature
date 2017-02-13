package com.swust.kelab.dao;

import com.mongodb.BasicDBList;
import com.mongodb.BasicDBObject;
import com.swust.kelab.dao.model.Author;
import com.swust.kelab.dao.query.BaseDao;

import java.util.List;

/**
 * Created by zengdan on 2017/2/9.
 */
public class AuthorDao extends BaseDao<Author> {
    @Override
    public void init() {
        super.collection = "author";
    }

    @Override
    public boolean updateOrSave(Author entity) {
        if(entity.getAuthId()!=null){//更新
            BasicDBObject query = new BasicDBObject("authId", entity.getAuthId());
            BasicDBObject update = new BasicDBObject("$set", entity);
            super.getDBCollection().update(query, update);
        }else{//新增
            //之后再写,涉及id自增
        }
        return true;
    }

    @Override
    public boolean delete(Integer id) {
        BasicDBObject query = new BasicDBObject("authId", id);
        super.getDBCollection().findAndRemove(query);
        return true;
    }

    @Override
    public Author findById(Integer id) {
        BasicDBObject query = new BasicDBObject("userId", id);
        Author author = decode(super.getDBCollection().findOne(query), Author.class);
        return author;
    }

    @Override
    public List<Author> getBusinessesByIds(List<Integer> ids) {
        BasicDBList values = new BasicDBList();
        values.addAll(ids);
        BasicDBObject query = new BasicDBObject("$in", values);
        List<Author> list = decode(super.getDBCollection().find(query), Author.class);
        return list;
    }
}
