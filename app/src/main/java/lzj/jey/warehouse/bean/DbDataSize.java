package lzj.jey.warehouse.bean;

import lzj.jey.warehouse.db.DbManager;

public class DbDataSize implements DbManager.TableModel{
    public Integer _size;

    public Integer get_size() {
        return _size;
    }

    public void set_size(Integer _size) {
        this._size = _size;
    }

    public DbDataSize(boolean query) {
        this._size = 0;
    }
}