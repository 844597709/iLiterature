package com.swust.kelab.web.controller;

import com.swust.kelab.dao.base.PageResult;
import com.swust.kelab.dao.domain.TempAuthor;
import com.swust.kelab.dao.query.AuthorQuery;
import com.swust.kelab.service.web.AuthorServiceTemp;
import com.swust.kelab.web.json.JsonAndView;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.Resource;

@Controller
@RequestMapping("author")
public class AuthorControllerTemp {
	@Resource
	private AuthorServiceTemp authorService;

	@RequestMapping(value = "/viewAllAuthor", method = RequestMethod.POST)
	public JsonAndView viewAllAuthor(AuthorQuery query) {
		JsonAndView jv = new JsonAndView();
		PageResult<TempAuthor> result = authorService.viewAllAuthor(query);
		jv.addData("result", result);
		return jv;
	}

	/*@RequestMapping(value = "/viewAuthorUpdate", method = RequestMethod.POST)
	public JsonAndView viewAuthorUpdate(Integer auupAuthId) throws Exception {
		JsonAndView jv = new JsonAndView();
		// 查询条件格式验证
		if (auupAuthId <= 0) {
			jv.setRet(false);
			jv.setErrcode(601);
			jv.setErrmsg("数据格式错误");
			return jv;
		}
		List<AuthorUpdate> result = authorService.viewAuthorUpdate(auupAuthId);
		jv.addData("result", result);
		return jv;

	}

	@RequestMapping(value = "/viewAuthor", method = RequestMethod.POST)
	public JsonAndView viewAuthor(Integer authorId) throws Exception {
		JsonAndView jv = new JsonAndView();
		// 查询条件格式验证
		if (authorId <= 0) {
			jv.setRet(false);
			jv.setErrcode(601);
			jv.setErrmsg("数据格式错误");
			return jv;
		}
		Author result = authorService.viewAuthor(authorId);
		jv.addData("result", result);
		return jv;
	}

	@RequestMapping(value = "/countInfo", method = RequestMethod.POST)
	public JsonAndView countAuthor() throws Exception {
		JsonAndView jv = new JsonAndView();
		Map<String, Long> result = authorService.countInfo();
		jv.addData("result", result);
		return jv;
	}

	// liujie siteId=0 查找全部
	@RequestMapping(value = "/countInfoGender", method = RequestMethod.POST)
	public JsonAndView countAuthorGender(Integer siteId) throws Exception {
		JsonAndView jv = new JsonAndView();
		Map<String, Long> result = authorService.countInfoGender(siteId);
		jv.addData("result", result);
		return jv;
	}

	// liujie siteId=0 查找全部
	@RequestMapping(value = "/countInfoArea", method = RequestMethod.POST)
	public JsonAndView countAuthorArea(Integer siteId) throws Exception {
		JsonAndView jv = new JsonAndView();
		List<Area> list=	authorService.countAreaInfo( siteId);
		Area area=authorService.countAreaMaxPeople(siteId);
		int allAuthorNum=authorService.countAreaSumPeople( siteId);
		jv.addData("allAuthorNum",allAuthorNum);
		jv.addData("maxProvinceName", area.getName());
		jv.addData("maxProvinceNum", area.getValue());
		jv.addData("result", list);
		return jv;
	}

	// liujie 根据作者信息（1点击量、2评论数、3推荐数、4作品数）查询对应作者数量
	// 此函数已舍弃，要用注意增加siteId
	@RequestMapping(value = "/countInfoNum", method = RequestMethod.POST)
	public JsonAndView countAuthorNum(Integer type, String range) throws Exception {
		JsonAndView jv = new JsonAndView();
		if (type < 1 || type > 4 || range == null) {
			jv.setRet(false);
			jv.setErrcode(601);
			jv.setErrmsg("数据范围错误");
			return jv;
		}
		// List<NameValuePair> result = authorService.countInfoNum(type, range);
		List<NameValuePair> result = authorService.countInfoNumByJava(type, range, null);
		jv.addData("result", result);
		return jv;
	}

	// liujie 一次全部查出作者统计：range1~4：1点击量、2评论数、3推荐数、4作品数
	@RequestMapping(value = "/countInfoNumAll", method = RequestMethod.POST)
	public JsonAndView countAuthorNumAll(String range1, String range2, String range3, String range4, Integer siteId)
			throws Exception {
		System.out.println(range1+" "+range2+" "+range3+" "+range4+" "+siteId);
		JsonAndView jv = new JsonAndView();
		Map<String, Object> map = authorService.countInfoNumAll(range1, range2, range3, range4, siteId);
		if (map == null || map.size() == 0) {
			jv.setRet(false);
			jv.setErrcode(601);// 随便写的哈
			jv.setErrmsg("没有符合条件的作者");
			return jv;
		}
		jv.addData("result", map);
		return jv;
	}*/

}
