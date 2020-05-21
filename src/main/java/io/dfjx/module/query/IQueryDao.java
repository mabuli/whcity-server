package io.dfjx.module.query;

import io.dfjx.core.base.ISysQueryDao;
import io.dfjx.core.base.SysBaseEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository(value="queryDao")
@Mapper
public interface IQueryDao extends ISysQueryDao<SysBaseEntity> {

    /*
    @Select("select distinct dim_desc, value from stat_year " +
            "where col_id='S011801000' and data_year='2015' and zone_id='350722000000'" +
            " and ind_id='I011801001' and dim_id<>'999999'" +
            " order by dim_id")
    List<Map<String, Object>> query01(Map<String, Object> params);
     */

    @Select("${m.sql}")
    List<Map<String, Object>> querySql(@Param("m") Map<String, Object> params);
}