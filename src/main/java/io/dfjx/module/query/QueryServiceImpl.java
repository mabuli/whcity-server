package io.dfjx.module.query;

import io.dfjx.common.util.R;
import io.dfjx.core.base.ISysQueryService;
import io.dfjx.core.base.SysBaseEntity;
import io.dfjx.core.base.SysQueryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service("queryService")
public class QueryServiceImpl extends SysQueryService<IQueryDao, SysBaseEntity> implements ISysQueryService<SysBaseEntity> {

    @Autowired
    private IQueryDao dao;

    public R querySql(Map<String, Object> params){
        List<Map<String, Object>> list = dao.querySql(params);
        return R.ok().put("data", list);
    }
}
