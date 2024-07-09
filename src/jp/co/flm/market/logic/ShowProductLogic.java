package jp.co.flm.market.logic;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import jp.co.flm.market.common.MarketBusinessException;
import jp.co.flm.market.common.MarketSystemException;
import jp.co.flm.market.dao.ConnectionManager;
import jp.co.flm.market.dao.ProductDAO;
//import jp.co.flm.market.entity.Category;
//import jp.co.flm.market.entity.Stock;
import jp.co.flm.market.entity.Product;

public class ShowProductLogic {
    /**
     * 商品一覧・商品詳細を表示する。
     *
     * @param categoryId
     *            引数のカテゴリーID
     * @param productId
     *            引数の商品ID
     * @return 商品一覧・商品詳細
     * @throws MarketSystemException
     *             本システムのシステム例外
     * @throws MarketBusinessException
     */
    public ArrayList<Product> showCategory(String categoryId) throws MarketSystemException, MarketBusinessException {
        Connection con = null;
        ArrayList<Product> productlist = null;

        try {
         // データベースの接続を取得する
         con = ConnectionManager.getConnection();
         //テーブルアクセス用のDAOを生成し、メソッドを呼び出す
         ProductDAO productDAO = new ProductDAO(con);
         productlist = productDAO.showCategory(categoryId);

         //在庫がなかった場合、メッセージを格納
         if (productlist.size() > 0) {
             int stock = productlist.get(0).getStock().getQuantity(); // 在庫を取得
             if (stock == 0) {
                 throw new MarketBusinessException("商品" + productlist.get(0).getProductName() + "は在庫切れです。");
             }
         }
         }catch(SQLException e) {
            e.printStackTrace();
            throw new MarketSystemException("システムエラーが発生しました。システム管理者に連絡してください。");
        }finally {
            try {
                if(con != null){
                    con.close();
                    }
                } catch(SQLException e) {
                    e.printStackTrace();
                    throw new MarketSystemException("システムエラーが発生しました。システム管理者に連絡してください。");
                    }
            }
        return productlist;
        }

    public Product showProduct(String productId) throws MarketSystemException {
        Connection con = null;
        Product product = null;

        try {
            // データベースの接続を取得する
            con = ConnectionManager.getConnection();
            //テーブルアクセス用のDAOを生成し、メソッドを呼び出す
            ProductDAO productDAO = new ProductDAO(con);
            product = productDAO.showProduct(productId);

          //在庫がなかった場合、メッセージを格納
            if (product != null) {
                int stock = product.getStock().getQuantity(); // 在庫を取得
                if (stock == 0) {
                    throw new MarketBusinessException("商品" + product.getProductName() + " は在庫切れです。");
                }
            }
        catch(SQLException e) {
            e.printStackTrace();
            throw new MarketSystemException("システムエラーが発生しました。システム管理者に連絡してください。");
        }finally {
            try {
                if(con != null){
                    con.close();
                    }
                } catch(SQLException e) {
                    e.printStackTrace();
                    throw new MarketSystemException("システムエラーが発生しました。システム管理者に連絡してください。");
                    }
            }
        return product;
        }
    }