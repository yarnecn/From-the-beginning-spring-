package cn.yarne.com.base.controller;


import cn.yarne.com.base.model.SysUser;
import cn.yarne.com.base.other.ResultData;
import cn.yarne.com.base.service.SysUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author yarne
 * @version 2016年6月21日 上午9:50:58
 */
@RestController
@RequestMapping("sysUser")
@Api(value = "用户管理", description = "用户进行用户的一些操作")
public class SysUserAction {
	//getLogger里面写的哪个类，之后日志就会打在哪个类下边
	private static final  Logger logger=Logger.getLogger(SysUserAction.class);
	@Autowired
	private SysUserService sysUserService;
	
	@RequestMapping(value="querySysUser",method=RequestMethod.GET)
	@ApiOperation(value = "查询用户信息")
	public ResultData querySysUser(@ApiParam(required = false, value = "登录名")String loginName,
								   @ApiParam(required = false, value = "用户名")String userName,
								   @ApiParam(required = false, value = "总页数",defaultValue = "3")Integer pageCount,
								   @ApiParam(required = false, value = "当前页数",defaultValue = "1")Integer pageNow){
		ResultData resultData=new ResultData();
		List<SysUser> sysUsers = sysUserService.querySysUsersByExample(userName, loginName);
		resultData.setData(sysUsers);
		return resultData;
	}

}
