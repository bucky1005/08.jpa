package com.ohgiraffers.section03.bidirection;

import jakarta.persistence.*;

@Entity(name="bidirection_menu")
@Table(name="tbl_menu")
public class Menu {

    @Id
    @Column(name="menu_code")
    private int menuCode;
    @Column(name="menu_name")
    private String menuName;
    @Column(name="menu_price")
    private int menuPrice;

    /* 설명. 해당 코드에선 아래부분만 잘 이해하면 됨.
    *   부모와 연관 관계를 맺고있는 자식테이블의 fk (= category) */
    @JoinColumn(name="category_code")
    @ManyToOne
    private Category category;
    @Column(name="orderable_status")
    private String orderableStatus;

    public Menu() {}

    public Menu(int menuCode, String menuName, int menuPrice, Category category, String orderableStatus) {
        super();
        this.menuCode = menuCode;
        this.menuName = menuName;
        this.menuPrice = menuPrice;
        this.category = category;
        this.orderableStatus = orderableStatus;
    }

    public int getMenuCode() {
        return menuCode;
    }

    public void setMenuCode(int menuCode) {
        this.menuCode = menuCode;
    }

    public String getMenuName() {
        return menuName;
    }

    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }

    public int getMenuPrice() {
        return menuPrice;
    }

    public void setMenuPrice(int menuPrice) {
        this.menuPrice = menuPrice;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public String getOrderableStatus() {
        return orderableStatus;
    }

    public void setOrderableStatus(String orderableStatus) {
        this.orderableStatus = orderableStatus;
    }

    @Override
    public String toString() {
        return "Menu [menuCode=" + menuCode + ", menuName=" + menuName + ", menuPrice=" + menuPrice + ", category="
                + category + ", orderableStatus=" + orderableStatus + "]";
    }

}
