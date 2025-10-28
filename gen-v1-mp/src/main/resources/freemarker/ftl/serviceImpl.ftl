/**
 * Copyright (c) 2025 Tu Personal Research
 * All rights reserved.
 */
package ${serviceImplUrl};

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import ${frameworkPackage}.${frameworkErrorCodeEnum};
import ${frameworkPackage}.${frameworkDeletedEnum};
import ${frameworkPackage}.${frameworkBizException};
import ${entityUrl}.${entityName}${addSuffix};
import ${paramUrl}.${entityName}AddOrEditParam;
import ${queryUrl}.${entityName}PageQuery;
import ${voUrl}.${entityName}PageVO;
import ${voUrl}.${entityName}VO;
import ${daoUrl}.${entityName}Mapper;
import ${mapperExtendsClassPackage};
import ${serviceUrl}.I${entityName}Service;
<#if serviceImplExtendsClassPackage!="" >
import ${serviceImplExtendsClassPackage};
</#if>
import ${frameworkPackage}.${frameworkDeleteByIdListQuery};
import ${frameworkPackage}.${frameworkPageInfoVO};
import ${frameworkPackage}.${frameworkResult};
import ${frameworkPackage}.${frameworkPageUtils};
import cn.hutool.core.util.StrUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import cn.hutool.core.collection.CollUtil;
<#if isSl4j=="true" >
import lombok.extern.slf4j.Slf4j;
</#if>
import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**   
 * ${entityComment}服务实现层
 *
 * @author ${author}
 * @date ${createTime}
 */
@Service
<#if isSl4j=="true" >
@Slf4j
</#if>
public class ${entityName}ServiceImpl <#if serviceImplExtendsClassName!="" >extends ${serviceImplExtendsClassName}<${entityName}Mapper, ${entityName}${addSuffix}></#if> implements I${entityName}Service  {

    @Resource
    private ${entityName}Mapper ${objectName}Mapper;

    /**
     * 分页列表
     *
     * @param query {@link }
     * @return {@link PageInfoVO}
     * @author ${author}
     * @date ${createTime}
     */
    @Override
    public PageInfoVO<${entityName}PageVO> pageList(${entityName}PageQuery query) {
        Page page = new Page(query.getCurrent(), query.getSize());
        Page<${entityName}PageVO> pageList = ${objectName}Mapper.select${entityName}PageList(page, query);
        return PageUtils.buildPage(pageList);
    }

    /**
    * 新增
    *
    * @param param {@link ${entityName}AddOrEditParam}
    * @author ${author}
    * @date ${createTime}
    */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Long add(${entityName}AddOrEditParam param) {
        // 使用 hutool BeanUtil 进行 Param -> PO 转换
        ${entityName}${addSuffix} po = new ${entityName}${addSuffix}();
        BeanUtil.copyProperties(param, po);
        int i = ${objectName}Mapper.insert(po);
        if (i <= 0) {
            throw new BizException(ErrorCodeEnum.OPERATION_FAIL);
        }
        return po.getId();
    }

    /**
    * 编辑
    *
    * @param param {@link ${entityName}AddOrEditParam}
    * @author ${author}
    * @date ${createTime}
    */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void edit(${entityName}AddOrEditParam param) {
        // 使用 hutool BeanUtil 进行 Param -> PO 转换
        ${entityName}${addSuffix} po = new ${entityName}${addSuffix}();
        BeanUtil.copyProperties(param, po);
        boolean b = updateById(po);
        if (!b) {
            throw new BizException(ErrorCodeEnum.OPERATION_FAIL);
        }
    }

    /**
    * 删除
    *
    * @param query id
    * @author ${author}
    * @date ${createTime}
    */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(DeletedByIdListQuery query) {
        ${entityName}${addSuffix} po = new ${entityName}${addSuffix}();
        po.setIsDeleted(DeletedEnum.DELETED.getCode());
        boolean b = update(po, new UpdateWrapper<${entityName}${addSuffix}>()
                .in("id", query.getIdList())
                .eq("is_deleted", DeletedEnum.NORMAL.getCode()));
        if (!b) {
            throw new BizException(ErrorCodeEnum.OPERATION_FAIL);
        }
    }

    /**
     * 详情
     *
     * @param ${entityName?uncap_first}Id 主键id
     * @return {@link }
     * @author ${author}
     * @date ${createTime}
     */
    @Override
    public ${entityName}VO detail(Long ${entityName?uncap_first}Id) {
        ${entityName}VO vo = new ${entityName}VO();
        ${entityName}${addSuffix} po = isExistById(${entityName?uncap_first}Id);
        BeanUtil.copyProperties(po, vo);
        return vo;
    }

    private ${entityName}${addSuffix} isExistById(Long ${entityName?uncap_first}Id) {
        if (null == ${entityName?uncap_first}Id) {
            throw new BizException(ErrorCodeEnum.REQUEST_PARAM_ERROR, "唯一标识不能为空");
        }
        ${entityName}${addSuffix} detail = this.getById(${entityName?uncap_first}Id);
        if (null == detail || DeletedEnum.DELETED.getCode().equals(detail.getIsDeleted())) {
            throw new BizException(ErrorCodeEnum.DATA_NOT_FOUND);
        }
        return detail;
    }

}