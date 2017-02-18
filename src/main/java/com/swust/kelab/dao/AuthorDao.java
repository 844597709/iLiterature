package com.swust.kelab.dao;

import com.mongodb.BasicDBObject;
import com.swust.kelab.dao.domain.TempAuthor;
import com.swust.kelab.dao.query.BaseDao;
import org.springframework.stereotype.Component;

/**
 * Created by zengdan on 2017/2/9.
 */
@Component("authorDao")
public class AuthorDao extends BaseDao<TempAuthor> {
    @Override
    public void init() {
        super.collection = "author";
    }

    @Override
    public boolean updateOrSave(TempAuthor entity) {
        if(entity.getAuthId()!=null){//更新
            BasicDBObject query = new BasicDBObject("authId", entity.getAuthId());
            BasicDBObject update = new BasicDBObject("$set", entity);
            super.getDBCollection().update(query, update);
            return true;
        }
        return false;
    }

    /*@Override
    public boolean delete(Integer id) {
        BasicDBObject query = new BasicDBObject("authId", id);
        super.getDBCollection().findAndRemove(query);
        return true;
    }*/

    @Override
    public TempAuthor findById(Integer id) {
        BasicDBObject query = new BasicDBObject("authId", id);
        TempAuthor author = decode(super.getDBCollection().findOne(query), TempAuthor.class);
        return author;
    }

    /*@Override
    public List<Author> getBusinessesByIds(List<Integer> ids) {
        BasicDBList values = new BasicDBList();
        values.addAll(ids);
        BasicDBObject query = new BasicDBObject("$in", values);
        List<Author> list = decode(super.getDBCollection().find(query), Author.class);
        return list;
    }*/
}
