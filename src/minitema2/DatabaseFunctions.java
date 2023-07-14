package minitema2;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.query.Query;

import utils.WithSessionAndTransaction;

public class DatabaseFunctions {

	public void saveArticle(String name) {

		new WithSessionAndTransaction() {
			@Override
			public void doAction(Session session) {

				Article a = new Article(name);
				session.save(a);

			}
		}.run();

	}

	public void saveStore(String name) {

		new WithSessionAndTransaction() {
			@Override
			public void doAction(Session session) {

				Store s = new Store(name);
				session.save(s);

			}
		}.run();

	}

	public void savePrice(int idA, int idS, int price) {

		new WithSessionAndTransaction() {
			@Override
			public void doAction(Session session) {

				Article article = getArticleById(idA, session);
				if (article == null) {
					throw new RuntimeException("Articol inexistent: id=" + idA);
				}

				Store store = getStoreByName(idS, session);
				if (store == null) {
					throw new RuntimeException("Magazin inexistent: id=" + idS);
				}

				Price pricee = new Price(price, article, store);
				session.save(pricee);

			}

		}.run();

	}

	public void showArticles() {
		new WithSessionAndTransaction() {
			@Override
			public void doAction(Session session) {

				List<Article> result = session.createQuery("from Article", Article.class).getResultList();

				for (Article c : result) {
					System.out.println(c);
				}

			}
		}.run();
	}

	public void showStores() {
		new WithSessionAndTransaction() {
			@Override
			public void doAction(Session session) {

				List<Store> result = session.createQuery("from Store", Store.class).getResultList();

				for (Store c : result) {
					System.out.println(c);
				}

			}
		}.run();
	}

	public void showPrices() {
		new WithSessionAndTransaction() {
			@Override
			public void doAction(Session session) {

				List<Price> result = session.createQuery("from Price", Price.class).getResultList();

				for (Price c : result) {
					System.out.println(c);
				}

			}
		}.run();
	}

	private Store getStoreByName(int idS, Session session) {
		Query<Store> query2 = session.createQuery("from Store where id = :nameParameter", Store.class);
		query2.setParameter("nameParameter", idS);
		Store store = query2.getSingleResult();
		return store;
	}

	private Article getArticleById(int idA, Session session) {
		Query<Article> query = session.createQuery("from Article where id = :nameParameter", Article.class);
		query.setParameter("nameParameter", idA);
		Article article = query.getSingleResult();
		return article;
	}
}
