package com.ohgiraffers.section03.bidirection;

import jakarta.persistence.*;

import java.util.List;

/* 설명. 해당 방법은 권장하지 않음 */
@Entity(name="bidirection_category")
@Table(name="tbl_category")
public class Category {

    @Id
    @Column(name="category_code")
    private int categoryCode;
    @Column(name="category_name")
    private String categoryName;
    @Column(name="ref_category_code")
    private Integer refCategoryCode;

    /* 설명. 해당 코드에서는 mappedBy부분만 잘 이해하면 됨 */
    @OneToMany(mappedBy="category")
    private List<Menu> menuList;

    public Category() {}

    public Category(int categoryCode, String categoryName, Integer refCategoryCode, List<Menu> menuList) {
        super();
        this.categoryCode = categoryCode;
        this.categoryName = categoryName;
        this.refCategoryCode = refCategoryCode;
        this.menuList = menuList;
    }

    public int getCategoryCode() {
        return categoryCode;
    }

    public void setCategoryCode(int categoryCode) {
        this.categoryCode = categoryCode;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public Integer getRefCategoryCode() {
        return refCategoryCode;
    }

    public void setRefCategoryCode(Integer refCategoryCode) {
        this.refCategoryCode = refCategoryCode;
    }

    public List<Menu> getMenuList() {
        return menuList;
    }

    public void setMenuList(List<Menu> menuList) {
        this.menuList = menuList;
    }

    @Override
    public String toString() {
        return "Category [categoryCode=" + categoryCode + ", categoryName=" + categoryName + ", refCategoryCode="
                + refCategoryCode + "]";
    }

}