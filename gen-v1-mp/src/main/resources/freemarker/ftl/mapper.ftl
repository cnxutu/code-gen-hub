<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN" "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
<mapper namespace="${daoUrl}.${entityName}Mapper">

    <sql id="baseWhere">
        where is_deleted = 0
    </sql>

    <!-- 分页列表 -->
    <select id="select${entityName}PageList"
            resultType="${voUrl}.${entityName}PageVO">
        select
        id,
        <#list cis as ci>
            ${ci.column} AS ${ci.property}<#if ci_has_next>,</#if>
        </#list>
        from
        ${table}
        <include refid="baseWhere"/>
        <#-- 单值条件 -->
            <#if query??>
                <if test="query.id != null">
                    and id = \#{query.id}
                </if>

                <if test="query.name != null and query.name != ''">
                    and name like concat('%', \#{query.name}, '%')
                </if>

                <if test="query.createTimeStart != null">
                    and create_time &gt;= \#{query.createTimeStart}
                </if>

                <if test="query.createTimeEnd != null">
                    and create_time &lt;= \#{query.createTimeEnd}
                </if>

                <#-- 集合条件 -->
                <if test="query.ids != null and query.ids.size() &gt; 0">
                    and id in
                    <foreach collection="query.ids" item="item" open="(" separator="," close=")">
                        \#{item}
                    </foreach>
                </if>

                <#-- 自定义条件占位，可直接传 SQL 片段 -->
                <if test="query.customCondition != null and query.customCondition != ''">
                    and ${query.customCondition}
                </if>
            </#if>

            <#-- 排序 -->
            order by create_time desc

    </select>

	<resultMap id="BaseResultMap" type="${entityUrl}.${entityName}${addSuffix}">
        <id column="id" property="id" />
	<#list cis as ci>
		<result column="${ci.column}" property="${ci.property}" />
	</#list>
        <result column="create_time" property="createTime" />
        <result column="update_time" property="updateTime" />
        <result column="created_by" property="createdBy" />
        <result column="updated_by" property="updatedBy" />
        <result column="is_deleted" property="isDeleted" />
	</resultMap>
	<sql id="Base_Column_List">
        id, ${agile}, create_time, update_time, created_by, updated_by, is_deleted
	</sql>
	
</mapper>