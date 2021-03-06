package lzj.jey.warehouse.bean;

import lzj.jey.warehouse.db.DbManager;
import lzj.jey.warehouse.db.FieldConstraint;
import lzj.jey.warehouse.db.FieldType;
import lzj.jey.warehouse.db.TableField;

/**
 * 商品信息
 */
public class ComInfo implements DbManager.TableModel {

    @TableField
    @FieldType(value = "Integer")
    @FieldConstraint(value = {"primary key"})
    Integer _id;//商品id
    @TableField
    @FieldType(value = "String")
    @FieldConstraint(value = {"unique"})
    String ComInfoNO;//商品编号
    @TableField
    @FieldType(value = "String")
    String loc1;//库存位置1
    @TableField
    @FieldType(value = "Integer")
    Integer locState1;//位置状态
    @TableField
    @FieldType(value = "String")
    String loc2;//库存位置2
    @TableField
    @FieldType(value = "Integer")
    Integer locState2;//位置状态
    @TableField
    @FieldType(value = "String")
    String loc3;//库存位置3
    @TableField
    @FieldType(value = "Integer")
    Integer locState3;//位置状态
    @TableField
    @FieldType(value = "String")
    String loc4;//库存位置4
    @TableField
    @FieldType(value = "Integer")
    Integer locState4;//位置状态
    @TableField
    @FieldType(value = "String")
    String loc5;//库存位置5
    @TableField
    @FieldType(value = "Integer")
    Integer locState5;//位置状态
    @TableField
    @FieldType(value = "Float")
    Float price1;//价格1

    @TableField
    @FieldType(value = "Float")
    Float price2;//价格2

    @TableField
    @FieldType(value = "Float")
    Float price3;//价格3


    @TableField
    @FieldType(value = "Integer")
    Integer store1;//仓库是否有 0为没有，1为有

    @TableField
    @FieldType(value = "Integer")
    Integer store2;//展示架是否有 0为没有，1为有


    public ComInfo() {

    }

    public ComInfo(int query) {
        this._id = 0;
        ComInfoNO = "";
        this.loc1 = "";
        this.locState1 = 0;
        this.loc2 = "";
        this.locState2 = 0;
        this.loc3 = "";
        this.locState3 = 0;
        this.loc4 = "";
        this.locState4 = 0;
        this.loc5 = "";
        this.locState5 = 0;
        this.price1 = 0f;
        this.price2 = 0f;
        this.price3 = 0f;
        this.store1=0;
        this.store2=0;
    }


    public Integer getStore1() {
        return store1;
    }

    public void setStore1(Integer store1) {
        this.store1 = store1;
    }

    public Integer getStore2() {
        return store2;
    }

    public void setStore2(Integer store2) {
        this.store2 = store2;
    }

    public Integer get_id() {
        return _id;
    }

    public void set_id(Integer _id) {
        this._id = _id;
    }

    public String getComInfoNO() {
        return ComInfoNO;
    }

    public void setComInfoNO(String comInfoNO) {
        ComInfoNO = comInfoNO;
    }

    public String getLoc1() {
        return loc1;
    }

    public void setLoc1(String loc1) {
        this.loc1 = loc1;
    }

    public Integer getLocState1() {
        return locState1;
    }

    public void setLocState1(Integer locState1) {
        this.locState1 = locState1;
    }

    public String getLoc2() {
        return loc2;
    }

    public void setLoc2(String loc2) {
        this.loc2 = loc2;
    }

    public Integer getLocState2() {
        return locState2;
    }

    public void setLocState2(Integer locState2) {
        this.locState2 = locState2;
    }

    public String getLoc3() {
        return loc3;
    }

    public void setLoc3(String loc3) {
        this.loc3 = loc3;
    }

    public Integer getLocState3() {
        return locState3;
    }

    public void setLocState3(Integer locState3) {
        this.locState3 = locState3;
    }

    public String getLoc4() {
        return loc4;
    }

    public void setLoc4(String loc4) {
        this.loc4 = loc4;
    }

    public Integer getLocState4() {
        return locState4;
    }

    public void setLocState4(Integer locState4) {
        this.locState4 = locState4;
    }

    public String getLoc5() {
        return loc5;
    }

    public void setLoc5(String loc5) {
        this.loc5 = loc5;
    }

    public Integer getLocState5() {
        return locState5;
    }

    public void setLocState5(Integer locState5) {
        this.locState5 = locState5;
    }


    public Float getPrice1() {
        return price1;
    }

    public void setPrice1(Float price1) {
        this.price1 = price1;
    }

    public Float getPrice2() {
        return price2;
    }

    public void setPrice2(Float price2) {
        this.price2 = price2;
    }

    public Float getPrice3() {
        return price3;
    }

    public void setPrice3(Float price3) {
        this.price3 = price3;
    }

    @Override
    public String toString() {
        return "编号：" + ComInfoNO + "   位置：" + loc1 + "," + loc2 + "," + loc3 + "," + loc4 + "," + loc5;
    }


    public String toString2() {
        String st1="";
        if (store1 == null) {
            st1="无";
        }else {
            if (store1==0){
                st1="无";
            }else {
                st1="有";
            }
        }
        String st2="";
        if (store2 == null) {
            st2="无";
        }else {
            if (store2==0){
                st2="无";
            }else {
                st2="有";
            }
        }
        return "编号：" + ComInfoNO + "\t\t\t\t   仓库："+st1+", 展架："+st2 ;
    }
}
