/**
 * Copyright (c) 2025 Tu Personal Research
 * All rights reserved.
 */
package ${daoUrl};

<#if mapperExtendsClassPackage!="" >
import ${mapperExtendsClassPackage};
</#if>
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import ${entityUrl}.${entityName}${addSuffix};
import ${queryUrl}.${entityName}PageQuery;
import ${voUrl}.${entityName}PageVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * ${entityComment} 持久层
 *
 * @author ${author}
 * @date ${createTime}
 */
public interface ${entityName}Mapper <#if mapperExtendsClassName!="" >extends ${mapperExtendsClassName}<${entityName}${addSuffix}></#if> {

   /**
   * 分页列表
   */
   Page<${entityName}PageVO> select${entityName}PageList(Page page, @Param("query") ${entityName}PageQuery query);
}
	