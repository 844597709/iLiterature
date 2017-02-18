package com.swust.kelab.service.web;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.mongodb.AggregationOutput;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.swust.kelab.dao.AuthorDao;
import com.swust.kelab.dao.base.PageResult;
import com.swust.kelab.dao.domain.TempAuthor;
import com.swust.kelab.dao.query.BaseQuery;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

@Service(value = "authorService")
public class AuthorServiceTemp {
	@Resource
	private AuthorDao authorDao;

	/**
	 * 分页查询作者信息
	 * 
	 * @throws Exception
	 */
	public PageResult<TempAuthor> viewAllAuthor(BaseQuery query) {
		PageResult<TempAuthor> result = authorDao.query(query);
		return result;
	}

	/**
	 * 根据authId查询作者信息
	 */
	public TempAuthor viewAuthor(Integer authId) {
		TempAuthor author = authorDao.findById(authId);
		return author;
	}

	/**
	 * 统计性别
	 */
	public Map<String, Integer> countInfoGender(int wesiId) throws Exception {
		Map<String, Integer> map = Maps.newHashMap();
		DBObject match = new BasicDBObject("$match", new BasicDBObject("authWebsiteId", wesiId));
		DBObject queryFields = new BasicDBObject();
		queryFields.put("_id", "authGender");
		queryFields.put("value", new BasicDBObject("$sum", 1));
		DBObject group = new BasicDBObject("$group", queryFields);
		List<DBObject> list = Lists.newArrayList();
		list.add(match);
		list.add(group);
		AggregationOutput output = authorDao.getDBCollection().aggregate(list);
		Iterator<DBObject> iter = output.results().iterator();
		while(iter.hasNext()){
			DBObject obj = iter.next();
			String json = JSON.toJSONString(obj);
			com.swust.kelab.dao.model.Area area = JSON.parseObject(json, com.swust.kelab.dao.model.Area.class);
			//get_id暂时
			String gender = area.get_id();//获取性别
			map.put(gender, area.getValue());
		}
		return map;
	}

	/**
	 * 统计省份作者数
	 */
	private Map<String, Integer> countInfoArea(Integer wesiId) {
		Map<String, Integer> map = Maps.newHashMap();
		DBObject match = new BasicDBObject("$match", new BasicDBObject("authWebsiteId", wesiId));
		DBObject queryFields = new BasicDBObject();
		queryFields.put("_id", "$authArea");
		queryFields.put("sum", new BasicDBObject("$sum", 1));
		DBObject group = new BasicDBObject("$group", queryFields);
		List<DBObject> list = Lists.newArrayList();
		list.add(match);
		list.add(group);
		AggregationOutput output = authorDao.getDBCollection().aggregate(list);
		Iterator<DBObject> iter = output.results().iterator();
		Integer num = 0;
		while(iter.hasNext()){
			DBObject obj = iter.next();
			String json = JSON.toJSONString(obj);
			com.swust.kelab.dao.model.Area area = JSON.parseObject(json, com.swust.kelab.dao.model.Area.class);
			//get_id暂时
			String name = area.get_id().split("-")[0].trim();//获取省份名称
			if(map.keySet().contains(name)){
				Integer oldValue = map.get(name);
				map.put(name, oldValue+area.getValue());
			}else{
				map.put(name, area.getValue());
			}
			num+=area.getValue();
		}
		System.out.println("总数num:"+num);
		return map;
	}

	// lj
	/*private String getNumStr(int type, long a, long b) {
		if (type == 4) {
			if (a + 1 == b)
				return String.valueOf(a) + "部";
			return String.valueOf(a) + "~" + String.valueOf(b) + "部";
		} else
			return String.valueOf(a) + "~" + String.valueOf(b);
	}

	// lj
	private String getNumStr(int type, long a) {
		if (type == 4)
			return String.valueOf(a) + "部及以上";
		else
			return String.valueOf(a) + "以上";
	}

	// lj
	public List<NameValuePair> countInfoNumByJava(int type, String range, List<Author> authors) throws Exception {
		if (authors == null || authors.size() == 0)
			authors = authorDao.selectList(new GenericQuery());// 所有作者,没加siteId哦
		List<NameValuePair> list = new ArrayList<NameValuePair>();
		long[] r = getRange(range);
		long[] n = new long[r.length];
		for (int i = 0; i < n.length; i++)
			n[i] = 0;
		for (int i = 0; i < authors.size(); i++) {
			long num = authors.get(i).getNum(type);
			if (num < 0 || num < r[0])
				continue;
			if (num >= r[r.length - 1]) {
				n[n.length - 1]++;
				continue;
			}
			for (int j = 1; j < r.length; j++) {
				if (num < r[j]) {
					n[j - 1]++;
					break;
				}
			}
		}
		for (int i = 0; i < n.length - 1; i++)
			list.add(new NameValuePair(getNumStr(type, r[i], r[i + 1]), String.valueOf(n[i])));
		list.add(new NameValuePair(getNumStr(type, r[r.length - 1]), String.valueOf(n[n.length - 1])));
		return list;
	}

	// lj
	private long[] getRange(String rangeStr) {
		String str[] = rangeStr.split(",");
		long[] range = new long[str.length];
		for (int i = 0; i < str.length; i++) {
			range[i] = Long.parseLong(str[i]);
		}
		return range;
	}

	// lj
	public Map<String, Object> countInfoNumAll(String range1, String range2, String range3, String range4, int siteId)
			throws Exception {
		GenericQuery gq = new GenericQuery();
		gq.put("siteId", siteId);
		List<Author> authors = authorDao.selectList(gq);
		if (authors == null || authors.size() == 0)
			return null;
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("totalHits", countInfoNumByJava(1, range1, authors));
		map.put("commentsNum", countInfoNumByJava(2, range2, authors));
		map.put("totalRecoms", countInfoNumByJava(3, range3, authors));
		map.put("worksCount", countInfoNumByJava(4, range4, authors));
		return map;
	}*/
	//cdk
	/*public List<Area> countAreaInfo(Integer siteId){
		return authorDao.countAreaInfo(siteId);
	}
	
	//cdk
	public Area countAreaMaxPeople(Integer siteId){
		return authorDao.countAreaMaxPeople(siteId);
	}
	//cdk
	public int countAreaSumPeople(Integer siteId){
		return authorDao.countAreaSumPeople(siteId);
	}*/
}
