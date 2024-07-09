package jp.co.flm.market.web;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import jp.co.flm.market.common.MarketSystemException;
import jp.co.flm.market.common.MarketBusinessException;
import jp.co.flm.market.entity.Product;
import jp.co.flm.market.logic.ShowProductLogic;
/**
 * 指定されたカテゴリー内にあるすべての商品を表示する。
 *
 * @author FLM
 * @version 1.0 YYYY/MM/DD
 */
public class B0101ShowCategoryAction {
    /*public void checkSession(HttpServletRequest req) {
        // セッションを取得する。
        req.getSession(false);
    }*/

    public String execute(HttpServletRequest request) {
        String page = null;

        try {
        //指定されたカテゴリーIDを取得する。
            String categoryId = req.getParameter("categoryId");
        // 得意先検索用の業務Controllerを生成し、メソッドを呼び出す。
            ShowProductLogic logic = new ShowProductLogic();
            ArrayList<Product> productlist = logic.showCategory(categoryId);

        // 検索結果をリクエストスコープに設定する。
        request.setAttribute("productlist", productlist);

        // 結果画面を戻り値に設定する。
        page = "/product-result-view.jsp";

        } catch(MarketBusinessException e) {
            e.printStackTrace();
            request.setAttribute("message", e.getMessage());
        } catch(MarketSystemException e) {
            e.printStackTrace();
            request.setAttribute("message", e.getMessage());
        // システムエラー画面を戻り値に設定する。
            page = "/error.jsp";
        }
        return page;

        }
}
