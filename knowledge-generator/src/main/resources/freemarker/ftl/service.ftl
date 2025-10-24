/**
 * Copyright (c) 2025 Tu Personal Research
 * All rights reserved.
 */
package ${serviceUrl};

<#if serviceExtendsClassPackage!="" >
import ${serviceExtendsClassPackage};
</#if>
import ${entityUrl}.${entityName}${addSuffix};
import ${paramUrl}.${entityName}AddParam;
import ${paramUrl}.${entityName}EditParam;
import ${queryUrl}.${entityName}PageQuery;
import ${voUrl}.${entityName}PageVO;
import ${voUrl}.${entityName}VO;
import ${frameworkPackage}.${frameworkDeleteByIdListQuery};
import ${frameworkPackage}.${frameworkPageInfoVO};

import java.util.List;
/**
 * ${entityComment} 服务接口
 *
 * @author ${author}
 * @date ${createTime}
 */
public interface I${entityName}Service <#if serviceExtendsClassName!="" >extends ${serviceExtendsClassName}<${entityName}${addSuffix}></#if> {

    /**
     * 新增
     *
     * @param param {@link ${entityName}AddParam}
     * @return id
     * @author ${author}
     * @date ${createTime}
     */
    Long add(${entityName}AddParam param);

    /**
     * 编辑
     *
     * @param param {@link ${entityName}EditParam}
     * @author ${author}
     * @date ${createTime}
     */
    void edit(${entityName}EditParam param);

    /**
     * 删除
     *
     * @param query id集合
     * @author ${author}
     * @date ${createTime}
     */
    void delete(DeletedByIdListQuery query);

    /**
     * 分页
     *
     * @param query 入参
     * @return 分页结果
     * @author ${author}
     * @date ${createTime}
     */
    PageInfoVO<${entityName}PageVO> pageList(${entityName}PageQuery query);

    /**
     * 详情
     *
     * @param ${entityName?uncap_first}Id 主键id
     * @return {@link }
     * @author ${author}
     * @date ${createTime}
     */
    ${entityName}VO detail(Long ${entityName?uncap_first}Id);

}