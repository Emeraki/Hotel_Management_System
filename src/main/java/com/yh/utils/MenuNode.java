package com.yh.utils;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
/**
 * 菜单节点工具类
 */
public class MenuNode {
    private int id;
    private int pid;
    private String title;
    private String href;
    private int spread;
    private String target;
    private String icon;
    private List<MenuNode> child;//子菜单的列表
}
