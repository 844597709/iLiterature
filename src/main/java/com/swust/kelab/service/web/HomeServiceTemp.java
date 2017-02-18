package com.swust.kelab.service.web;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.mongodb.AggregationOutput;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.swust.kelab.dao.AuthorDao;
import com.swust.kelab.dao.WorksCommentDao;
import com.swust.kelab.dao.WorksDao;
import com.swust.kelab.dao.model.Area;
import com.swust.kelab.dao.query.BaseQuery;
import com.swust.kelab.repos.SiteDao;
import com.swust.kelab.repos.bean.GenericQuery;
import com.swust.kelab.repos.bean.ListQuery;
import com.swust.kelab.web.model.NameValuePair;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;

@Service(value = "homeService")
public class HomeServiceTemp {
	@Resource
	private AuthorDao authorDao;
	@Resource
	private SiteDao siteDao;
	@Resource
	private WorksDao worksDao;
	@Resource
	private WorksCommentDao worksCommentDao;

	public Map<String, Long> countInfo() throws Exception {
		Map<String, Long> map = new HashMap<String, Long>();
		long author = authorDao.getCount(null);
		long site = siteDao.countSite();
		long works = worksDao.getCount(null);
		long worksComments = worksCommentDao.getCount(null);
		map.put("author", author);
		map.put("site", site);
		map.put("works", works);
		map.put("worksComments", worksComments);
		return map;
	}
}
