package web.service;

import web.model.LoginUser;

/**
* 認証サービス
*
*/
public interface AuthenticationService {
    /**
    * 入力されたユーザー名、パスワードを元にログインユーザーを取得します.
    * @param userForm　入力されたユーザー名
    * @return ログインユーザー
    */
    LoginUser auth(String username);
}
