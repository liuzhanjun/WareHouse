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

    @Override
    public String toString() {
        return "商品编号：" + ComInfoNO + "   位置：" + loc1 + "," + loc2 + "," + loc3 + "," + loc4 + "," + loc5;
    }
}
