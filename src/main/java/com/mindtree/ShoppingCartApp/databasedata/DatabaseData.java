package com.mindtree.ShoppingCartApp.databasedata;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.mindtree.ShoppingCartApp.entities.Apparel;
import com.mindtree.ShoppingCartApp.entities.Book;
import com.mindtree.ShoppingCartApp.entities.Cart;
import com.mindtree.ShoppingCartApp.entities.StockQuantity;
import com.mindtree.ShoppingCartApp.entities.User;





public class DatabaseData {

	public List<Book> getBookData() {

		Book book1 = new Book();
		book1.setBookAuthor("Chetan Bhagat");
		book1.setBookGenre("love story");
		book1.setBookPublication("SN894");
		book1.setProductId(101);
		book1.setProductName("Two States");
		book1.setProductPrice(460);

		Book book2 = new Book();
		book2.setBookAuthor("Chetan Bhagat");
		book2.setBookGenre("comedy");
		book2.setBookPublication("PN980");
		book2.setProductId(102);
		book2.setProductName("Friends");
		book2.setProductPrice(320);

		Book book3 = new Book();
		book3.setBookAuthor("J K Rowling");
		book3.setBookGenre("adventurous");
		book3.setBookPublication("KY321");
		book3.setProductId(103);
		book3.setProductName("Harry Potter");
		book3.setProductPrice(360);

		Book book4 = new Book();
		book4.setBookAuthor("J K Rowling");
		book4.setBookGenre("thriller");
		book4.setBookPublication("HJ9KL");
		book4.setProductId(104);
		book4.setProductName("After");
		book4.setProductPrice(530);

		Book book5 = new Book();
		book5.setBookAuthor("Jeffrey Archer ");
		book5.setBookGenre("comedy");
		book5.setBookPublication("LM8RI");
		book5.setProductId(105);
		book5.setProductName("The Trip");
		book5.setProductPrice(670);

		List<Book> bookList = Stream.of(book1, book2, book3, book4, book5).collect(Collectors.toList());
		return bookList;

	}

	public List<Apparel> getApparalData() {

		Apparel apparel1 = new Apparel();
		apparel1.setApparelBrand("Woodland");
		apparel1.setApparelDesign("Fancy");
		apparel1.setApparelType("cotton");
		apparel1.setProductId(201);
		apparel1.setProductName("Stylish Top");
		apparel1.setProductPrice(599);

		Apparel apparel2 = new Apparel();
		apparel2.setApparelBrand("W");
		apparel2.setApparelDesign("Ethnic");
		apparel2.setApparelType("crepe");
		apparel2.setProductId(202);
		apparel2.setProductName("Fancy Kurti");
		apparel2.setProductPrice(799);

		Apparel apparel3 = new Apparel();
		apparel3.setApparelBrand("Spunk");
		apparel3.setApparelDesign("Stylish");
		apparel3.setApparelType("cotton");
		apparel3.setProductId(203);
		apparel3.setProductName("Stylish T-shirt");
		apparel3.setProductPrice(499);

		Apparel apparel4 = new Apparel();
		apparel4.setApparelBrand("Spunk");
		apparel4.setApparelDesign("Modern");
		apparel4.setApparelType("cotton");
		apparel4.setProductId(204);
		apparel4.setProductName("Shirt");
		apparel4.setProductPrice(899);

		Apparel apparel5 = new Apparel();
		apparel5.setApparelBrand("Woodland");
		apparel5.setApparelDesign("Stylish");
		apparel5.setApparelType("cotton");
		apparel5.setProductId(205);
		apparel5.setProductName("Trendy Jeans");
		apparel5.setProductPrice(999);

		List<Apparel> apparelList = Stream.of(apparel1, apparel2, apparel3, apparel4, apparel5)
				.collect(Collectors.toList());
		return apparelList;

	}

	public List<User> getUserData() {

		User user1 = new User();
		user1.setUserId(1001);
		user1.setUserName("suchitra");

		User user2 = new User();
		user2.setUserId(1002);
		user2.setUserName("nidhi");

		User user3 = new User();
		user3.setUserId(1003);
		user3.setUserName("preeti");

		List<User> userList = Stream.of(user1, user2, user3).collect(Collectors.toList());
		return userList;
	}

	public List<Cart> getCartData() {
		List<StockQuantity> pList = new ArrayList<StockQuantity>();

		User user1 = new User();
		user1.setUserId(1001);
		user1.setUserName("suchitra");
		Cart cart1 = new Cart();
		cart1.setCartId(1001);
		cart1.setUser(user1);
		cart1.setStockQuantityInCart(pList);

		User user2 = new User();
		user2.setUserId(1002);
		user2.setUserName("nidhi");
		Cart cart2 = new Cart();
		cart2.setCartId(1002);
		cart2.setUser(user2);
		cart2.setStockQuantityInCart(pList);

		User user3 = new User();
		user3.setUserId(1003);
		user3.setUserName("preeti");
		Cart cart3 = new Cart();
		cart3.setCartId(1003);
		cart3.setUser(user3);
		cart3.setStockQuantityInCart(pList);

		List<Cart> cartList = Stream.of(cart1, cart2, cart3).collect(Collectors.toList());
		return cartList;

	}
}

