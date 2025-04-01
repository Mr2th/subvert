package com.subvert.server.common.util;

import cn.hutool.core.collection.CollUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
* @author xujianguo
* @date 2025/4/1
* @description
*/

@Data
public class PageUtil implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 总记录数
     */
    private int total;
    /**
     * 每页记录数
     */
    private int pageSize;

    /**
     * 总页数
     */
    private int pageCount;

    private int pageIndex;
    /**
     * 列表数据
     */
    private List<?> list;

    /**
     * 分页
     * @param list        列表数据
     * @param total  总记录数
     * @param pageSize    每页记录数
     * @param pageIndex    当前页数
     */
    public PageUtil(List<?> list, int total, int pageSize, int pageIndex) {
        this.list = list;
        this.total = total;
        this.pageSize = pageSize;
        this.pageIndex = pageIndex;
        this.pageCount = (int)Math.ceil((double)total/pageSize);
    }

    /**
     * 分页
     */
    public PageUtil(IPage<?> page) {
        this.list = page.getRecords();
        this.total = (int)page.getTotal();
        this.pageSize = (int)page.getSize();
        this.pageIndex = (int)page.getCurrent();
        this.pageCount = (int)page.getPages();
    }

    /**
     * 手动分页
     * @param list 数据集合
     * @param pageNum  页码
     * @param pageSize 每页多少条数据
     * @return list
     */
    public static <T> List<T> startPage(List<T> list, Integer pageNum,Integer pageSize) {
        if (CollUtil.isEmpty(list)) {
            return Collections.emptyList();
        }

        Integer count = list.size(); // 记录总数
        int pageCount = 0; // 页数
        if (count % pageSize == 0) {
            pageCount = count / pageSize;
        } else {
            pageCount = count / pageSize + 1;
        }

        int fromIndex = 0; // 开始索引
        int toIndex = 0; // 结束索引

        if (!pageNum.equals(pageCount)) {
            fromIndex = (pageNum - 1) * pageSize;
            toIndex = fromIndex + pageSize;
        } else {
            fromIndex = (pageNum - 1) * pageSize;
            toIndex = count;
        }

        return list.subList(fromIndex, toIndex);
    }

    public static List toPage(int page, int size , List<?> list) {
        int fromIndex = page * size;
        int toIndex = page * size + size;
        if(fromIndex > list.size()){
            return new ArrayList();
        } else if(toIndex >= list.size()) {
            return list.subList(fromIndex,list.size());
        } else {
            return list.subList(fromIndex,toIndex);
        }
    }
    public static Map<String, Object> toPage(List<?> list, Integer pageNumber, Integer pageSize) {
        Map<String,Object> map = new LinkedHashMap<>(2);
        map.put("content", toPage(pageNumber, pageSize, list));
        map.put("pageNumber", pageNumber);
        map.put("pageSize", pageSize);
        map.put("totalNumber", list.size());
        return map;
    }
}