package auth.service;

import auth.model.LoginUser;

public interface LoginService {
    /**
     * ログインユーザー情報を取得します.
     * <p>
     * 取得できない場合は、ユーザー名を空文字列で返します.
     * </p>
     * @param username ユーザー名
     * @return ログインユーザー情報
     */
    LoginUser login(String username);
}
