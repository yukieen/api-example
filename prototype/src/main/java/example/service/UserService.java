package example.service;

import java.util.List;

import example.model.User;

/**
 * ユーザー
 */
public interface UserService {

	/**
	 * ユーザーの追加を行います.
	 * @param user ユーザー
	 */
	public void add(User user);

	/**
	 * すべてのユーザーを取得します.
	 * @return すべてのユーザー
	 */
	public List<User> findAll();

	/**
	 * 指定したIDのユーザーを取得します.
	 * @param id
	 * @return 指定したIDのユーザー
	 */
	public User find(Long id);
}
