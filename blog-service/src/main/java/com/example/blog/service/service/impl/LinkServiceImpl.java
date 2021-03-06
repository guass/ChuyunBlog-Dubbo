package com.example.blog.service.service.impl;

import cn.hutool.core.util.StrUtil;
import com.alibaba.dubbo.config.annotation.Service;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.blog.api.entity.Link;
import com.example.blog.service.mapper.LinkMapper;
import com.example.blog.api.service.LinkService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * <pre>
 *     友情链接业务逻辑实现类
 * </pre>
 *
 */
@Service(
        version = "1.0.0",
        application = "${dubbo.application.id}",
        protocol = "${dubbo.protocol.id}",
        registry = "${dubbo.registry.id}"
)
public class LinkServiceImpl implements LinkService {


    @Autowired
    private LinkMapper linkMapper;

    @Override
    public BaseMapper<Link> getRepository() {
        return linkMapper;
    }

    @Override
    public QueryWrapper<Link> getQueryWrapper(Link link) {
        //对指定字段查询
        QueryWrapper<Link> queryWrapper = new QueryWrapper<>();
        if (link != null) {
            if (StrUtil.isNotBlank(link.getLinkName())) {
                queryWrapper.like("link_name", link.getLinkName());
            }
            if (StrUtil.isNotBlank(link.getLinkUrl())) {
                queryWrapper.like("link_url", link.getLinkUrl());
            }
        }
        return queryWrapper;
    }

    @Override
    public Link insertOrUpdate(Link entity) {
        if (entity.getId() == null) {
            insert(entity);
        } else {
            update(entity);
        }
        return entity;
    }

    @Override
    public void delete(Long id) {
        linkMapper.deleteById(id);
    }

    @Override
    public List<Link> findAll() {
        List<Link> linkList = linkMapper.selectList(null);
        return linkList;
    }
}
