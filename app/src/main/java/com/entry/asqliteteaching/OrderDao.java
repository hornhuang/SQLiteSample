package com.entry.asqliteteaching;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteConstraintException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class OrderDao {

    private static final String TAG = "OrdersDao";

    /**
     * 列定义
     */
    private final String[] ORDER_COLUMNS = new String[] {"Id", "CustomName","OrderPrice","Country"};

    private Context context;
    private TeachDBHelper ordersDBHelper;

    public OrderDao(Context context) {
        this.context = context;
        // 初始化数据库
        ordersDBHelper = new TeachDBHelper(context);
    }

    /**
     * 判断表中是否有数据
     */
    public boolean isDataExist(){
        int count = 0;

        SQLiteDatabase database = null;
        Cursor cursor = null;

        try {
            database = ordersDBHelper.getReadableDatabase();
            // select count(Id) from Orders
            cursor = database.query(TeachDBHelper.TABLE_USER, new String[]{"COUNT(Age)"}, null, null, null, null, null);

            if (cursor.moveToFirst()) {
                count = cursor.getInt(0);
            }
            if (count > 0) return true;
        }
        catch (Exception e) {
            Log.e(TAG, "", e);
        }
        finally {
            if (cursor != null) {
                cursor.close();
            }
            if (database != null) {
                database.close();
            }
        }
        return false;
    }

    /**
     * 初始化数据
     */
    public void initTable(){
        SQLiteDatabase db = null;

        try {
            db = ordersDBHelper.getWritableDatabase();
            db.beginTransaction();

            db.execSQL("insert into " + TeachDBHelper.TABLE_USER + " (Id, CustomName, OrderPrice, Country) values (1, 'Arc', 100, 'China')");
            db.execSQL("insert into " + TeachDBHelper.TABLE_USER + " (Id, CustomName, OrderPrice, Country) values (2, 'Bor', 200, 'USA')");
            db.execSQL("insert into " + TeachDBHelper.TABLE_USER + " (Id, CustomName, OrderPrice, Country) values (3, 'Cut', 500, 'Japan')");
            db.execSQL("insert into " + TeachDBHelper.TABLE_USER + " (Id, CustomName, OrderPrice, Country) values (4, 'Bor', 300, 'USA')");
            db.execSQL("insert into " + TeachDBHelper.TABLE_USER + " (Id, CustomName, OrderPrice, Country) values (5, 'Arc', 600, 'China')");
            db.execSQL("insert into " + TeachDBHelper.TABLE_USER + " (Id, CustomName, OrderPrice, Country) values (6, 'Doom', 200, 'China')");

            db.setTransactionSuccessful();
        }catch (Exception e){
            Log.e(TAG, "", e);
        }finally {
            if (db != null) {
                db.endTransaction();
                db.close();
            }
        }
    }

    /**
     * 执行自定义SQL语句
     */
    public void execSQL(String sql) {
        SQLiteDatabase db = null;

        try {
            if (sql.contains("select")){
                Toast.makeText(context, R.string.strUnableSql, Toast.LENGTH_SHORT).show();
            }else if (sql.contains("insert") || sql.contains("update") || sql.contains("delete")){
                db = ordersDBHelper.getWritableDatabase();
                db.beginTransaction();
                db.execSQL(sql);
                db.setTransactionSuccessful();
                Toast.makeText(context, R.string.strSuccessSql, Toast.LENGTH_SHORT).show();
            }
        } catch (Exception e) {
            Toast.makeText(context, R.string.strErrorSql, Toast.LENGTH_SHORT).show();
            Log.e(TAG, "", e);
        } finally {
            if (db != null) {
                db.endTransaction();
                db.close();
            }
        }
    }

    /**
     * 查询数据库中所有数据
     */
    public List<Order> getAllDate(){
        SQLiteDatabase db = null;
        Cursor cursor = null;

        try {
            db = ordersDBHelper.getReadableDatabase();
            // select * from Orders
            cursor = db.query(TeachDBHelper.TABLE_USER, ORDER_COLUMNS, null, null, null, null, null);

            if (cursor.getCount() > 0) {
                List<Order> orderList = new ArrayList<Order>(cursor.getCount());
                while (cursor.moveToNext()) {
                    orderList.add(parseOrder(cursor));
                }
                return orderList;
            }
        }
        catch (Exception e) {
            Log.e(TAG, "", e);
        }
        finally {
            if (cursor != null) {
                cursor.close();
            }
            if (db != null) {
                db.close();
            }
        }

        return null;
    }

    /**
     * 新增一条数据
     */
    public boolean insertDate(){
        SQLiteDatabase database = null;

        try {
            database = ordersDBHelper.getWritableDatabase();
            database.beginTransaction();

            // insert into Orders(Id, CustomName, OrderPrice, Country) values (7, "Jne", 700, "China");
            ContentValues contentValues = new ContentValues();
            contentValues.put("Id", 7);
            contentValues.put("CustomName", "Jne");
            contentValues.put("OrderPrice", 700);
            contentValues.put("Country", "China");
            database.insertOrThrow(TeachDBHelper.TABLE_USER, null, contentValues);

            database.setTransactionSuccessful();
            return true;
        }catch (SQLiteConstraintException e){
            Toast.makeText(context, "主键重复", Toast.LENGTH_SHORT).show();
        }catch (Exception e){
            Log.e(TAG, "", e);
        }finally {
            if (database != null) {
                database.endTransaction();
                database.close();
            }
        }
        return false;
    }

    /**
     * 删除一条数据  此处删除Id为7的数据
     */
    public boolean deleteOrder() {
        SQLiteDatabase database = null;

        try {
            database = ordersDBHelper.getWritableDatabase();
            database.beginTransaction();

            // 删除 Age 为 0 的数据
            database.delete(TeachDBHelper.TABLE_USER, "Age = ?", new String[]{String.valueOf(0)});
            database.setTransactionSuccessful();
            return true;
        } catch (Exception e) {
            Log.e(TAG, "", e);
        } finally {
            if (database != null) {
                database.endTransaction();
                database.close();
            }
        }
        return false;
    }

    /**
     * 修改一条数据  此处将Id为6的数据的OrderPrice修改了800
     */
    public boolean updateOrder(){
        SQLiteDatabase db = null;
        try {
            db = ordersDBHelper.getWritableDatabase();
            db.beginTransaction();

            // update Orders set OrderPrice = 800 where Id = 6
            ContentValues values = new ContentValues();
            values.put("OrderPrice", 800);
            db.update(TeachDBHelper.TABLE_USER,
                    values,
                    "Id = ?",
                    new String[]{String.valueOf(6)});
            db.setTransactionSuccessful();
            return true;
        }
        catch (Exception e) {
            Log.e(TAG, "", e);
        }
        finally {
            if (db != null) {
                db.endTransaction();
                db.close();
            }
        }
        return false;
    }

    /**
     * 数据查询  此处将用户名为"Bor"的信息提取出来
     */
    public List<Order> getBorOrder(){
        SQLiteDatabase database = null;
        Cursor cursor = null;

        try {
            database = ordersDBHelper.getReadableDatabase();

            // select * from Orders where CustomName = 'Bor'
            cursor = database.query(TeachDBHelper.TABLE_USER,
                    ORDER_COLUMNS,
                    "CustomName = ?",
                    new String[] {"Bor"},
                    null, null, null);

            if (cursor.getCount() > 0) {
                List<Order> orderList = new ArrayList<Order>(cursor.getCount());
                while (cursor.moveToNext()) {
                    Order order = parseOrder(cursor);
                    orderList.add(order);
                }
                return orderList;
            }
        }
        catch (Exception e) {
            Log.e(TAG, "", e);
        }
        finally {
            if (cursor != null) {
                cursor.close();
            }
            if (database != null) {
                database.close();
            }
        }
        return null;
    }

    /**
     * 统计查询  此处查询Country为China的用户总数
     */
    public int getChinaCount(){
        int count = 0;

        SQLiteDatabase db = null;
        Cursor cursor = null;

        try {
            db = ordersDBHelper.getReadableDatabase();
            // select count(Id) from Orders where Country = 'China'
            cursor = db.query(TeachDBHelper.TABLE_USER,
                    new String[]{"COUNT(Id)"},
                    "Country = ?",
                    new String[] {"China"},
                    null, null, null);

            if (cursor.moveToFirst()) {
                count = cursor.getInt(0);
            }
        }
        catch (Exception e) {
            Log.e(TAG, "", e);
        }
        finally {
            if (cursor != null) {
                cursor.close();
            }
            if (db != null) {
                db.close();
            }
        }

        return count;
    }

    /**
     * 比较查询  此处查询单笔数据中OrderPrice最高的
     */
    public Order getMaxOrderPrice(){
        SQLiteDatabase db = null;
        Cursor cursor = null;

        try {
            db = ordersDBHelper.getReadableDatabase();
            // select Id, CustomName, Max(OrderPrice) as OrderPrice, Country from Orders
            cursor = db.query(TeachDBHelper.TABLE_USER,
                    new String[]{"Id",
                            "CustomName",
                            "Max(OrderPrice) as OrderPrice",
                            "Country"},
                    null,
                    null,
                    null,
                    null,
                    null);

            if (cursor.getCount() > 0){
                if (cursor.moveToFirst()) {
                    return parseOrder(cursor);
                }
            }
        }
        catch (Exception e) {
            Log.e(TAG, "", e);
        }
        finally {
            if (cursor != null) {
                cursor.close();
            }
            if (db != null) {
                db.close();
            }
        }

        return null;
    }

    /**
     * 将查找到的数据转换成Order类
     */
    private Order parseOrder(Cursor cursor){
        Order order = new Order();
        order.id = (cursor.getInt(cursor.getColumnIndex("Id")));
        order.customName = (cursor.getString(cursor.getColumnIndex("CustomName")));
        order.orderPrice = (cursor.getInt(cursor.getColumnIndex("OrderPrice")));
        order.country = (cursor.getString(cursor.getColumnIndex("Country")));
        return order;
    }
}
