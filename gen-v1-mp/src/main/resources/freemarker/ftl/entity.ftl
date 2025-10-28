/**
 * Copyright (c) 2025 Tu Personal Research
 * All rights reserved.
 */
package ${entityUrl};

import com.fasterxml.jackson.annotation.JsonFormat;
<#if isSwagger=="true" >
import io.swagger.annotations.ApiModelProperty;
</#if>
<#if entityExtendsClassPackage!="" >
import ${entityExtendsClassPackage};
</#if>
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.springframework.format.annotation.DateTimeFormat;
import java.io.Serializable;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import java.time.LocalDate;
import java.time.LocalDateTime;
<#list pkgs as ps>
    <#if ps??>
import ${ps};
    </#if>
</#list>

/**   
 * ${entityComment} 实体类
 *
 * @author ${author}
 * @date ${createTime}
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("${table}")
public class ${entityName}${addSuffix}<#if entityExtendsClassName!="" > extends ${entityExtendsClassName} </#if> implements Serializable {

	private static final long serialVersionUID = ${serialVersionId}L;

<#list cis as ci>
    /**
     * ${ci.comment}
     */
    <#-- 针对日期类字段 -->
    <#if ci.javaType=="LocalDate">
    @JsonFormat(pattern="yyyy-MM-dd", timezone="GMT+8")
    <#elseif ci.javaType=="LocalDateTime">
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss", timezone="GMT+8")
    </#if>
    <#if ci.hump=="true">
    @TableField("${ci.column}")
    </#if>
	private ${ci.javaType} ${ci.property};
</#list>

}
	