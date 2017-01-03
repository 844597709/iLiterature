package com.swust.kelab.repos;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.swust.kelab.domain.WebSite;
import com.swust.kelab.repos.bean.ListQuery;

@Repository(value = "webSiteDao")
public class WebSiteDao {
    @Resource
    private SqlSession sqlSession;
    @Resource
    HttpServletRequest request;
    
    public int selectCount(ListQuery query) throws Exception {
        return sqlSession.selectOne("webSite.selectCount", query);
    }

    public int insert(WebSite topic) throws Exception {
        return sqlSession.insert("webSite.insert", topic);
    }

    public int update(WebSite topic) throws Exception {
        return sqlSession.update("webSite.update", topic);
    }

    public int delete(int topicId) throws Exception {
        return sqlSession.delete("webSite.delete", topicId);
    }

    public List<WebSite> selectList(ListQuery query) throws Exception {
        return sqlSession.selectList("webSite.select", query);
    }
}
