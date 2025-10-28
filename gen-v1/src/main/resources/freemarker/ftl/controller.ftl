/**
 * Copyright (c) 2025 Tu Personal Research
 * All rights reserved.
 */
package ${controllerUrl};

import ${frameworkPackage}.${frameworkErrorCodeEnum};
import ${frameworkPackage}.${frameworkDeletedEnum};
import ${frameworkPackage}.${frameworkBizException};
import ${entityUrl}.${entityName}${addSuffix};
import ${paramUrl}.${entityName}AddParam;
import ${queryUrl}.${entityName}PageQuery;
import ${voUrl}.${entityName}PageVO;
import ${voUrl}.${entityName}VO;
import ${serviceUrl}.I${entityName}Service;
import ${frameworkPackage}.${frameworkDeleteByIdListQuery};
import ${frameworkPackage}.${frameworkPageInfoVO};
import ${frameworkPackage}.${frameworkResult};
<#if isSl4j=="true" >
import lombok.extern.slf4j.Slf4j;
</#if>
import org.springframework.util.CollectionUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestParam;
import javax.annotation.Resource;

import java.util.List;

/**
 * ${entityComment}API接口层
 *
 * @author ${author}
 * @date ${createTime}
 */
@RestController
@RequestMapping("/${objectName}")
<#if isSl4j=="true" >
@Slf4j
</#if>
public class ${entityName}Controller {

    @Resource
    private I${entityName}Service ${objectName}Service;

    /**
     * @param param {@link ${entityName}AddParam}
     * @return {@link Result<Long>}
     * @author ${author}
     * @date ${createTime}
     * @description 新增
     * @menu ${entityComment}管理
     **/
    @PostMapping("/add")
    public Result<Long> add(@RequestBody @Validated ${entityName}AddParam param) {
        Long id = ${objectName}Service.add(param);
        return Result.success(id);
    }

    /**
     * @param param {@link ${entityName}EditParam}
     * @return {@link Result<Long>}
     * @author ${author}
     * @date ${createTime}
     * @description 编辑
     * @menu ${entityComment}管理
     **/
    @PostMapping("/edit")
    public Result<Long> edit(@RequestBody @Validated ${entityName}AddOrEditParam param) {
        ${objectName}Service.edit(param);
        return Result.success(param.getId());
    }

    /**
     * @param query id
     * @return {@link Result}
     * @author ${author}
     * @date ${createTime}
     * @description 删除
     * @menu ${entityComment}管理
     **/
    @PostMapping("/delete")
    public Result delete(@RequestBody DeletedByIdListQuery query) {
        if (CollectionUtils.isEmpty(query.getIdList())) {
            return Result.fail(ErrorCodeEnum.REQUEST_PARAM_ERROR, "请选择要删除的数据");
        }
        ${objectName}Service.delete(query);
        return Result.success();
    }

    /**
     * @param ${entityName?uncap_first}Id 主键id
     * @return {@link Result}
     * @author ${author}
     * @date ${createTime}
     * @description 详情
     * @menu ${entityComment}管理
     **/
    @GetMapping("/detail/{${entityName?uncap_first}Id}")
    public Result detail(@PathVariable("${entityName?uncap_first}Id") Long ${entityName?uncap_first}Id) {
        return Result.success(${objectName}Service.detail(${entityName?uncap_first}Id));
    }

    /**
     * @param query {@link }
     * @return {@link Result<PageInfoVO>}
     * @author ${author}
     * @date ${createTime}
     * @description 分页列表
     * @menu ${entityComment}管理
     **/
    @RequestMapping(value = "/pageList", method = RequestMethod.POST)
    public Result<PageInfoVO<${entityName}PageVO>> pageList(@RequestBody ${entityName}PageQuery query) {
        PageInfoVO vo = ${objectName}Service.pageList(query);
        return Result.success(vo);
    }

}