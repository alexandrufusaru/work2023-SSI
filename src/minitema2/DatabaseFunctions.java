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

	public List<Article> getSortedArticles() {

		return new WithSessionAndTransaction<List<Article>>() {
			@Override
			public void doAction(Session session) {

				Query<Article> result = session.createQuery("from Article order by name", Article.class);

				setReturnValue(result.list());

			}
		}.run();

	}

	public void removeArticle(String name) {

		new WithSessionAndTransaction() {
			@Override
			public void doAction(Session session) {

				Article a = getArticleByName(name, session);
				if (a == null) {
					throw new RuntimeException("Articol inexistent: " + name);
				}

				session.delete(a);

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

	public List<Store> getSortedStores() {

		return new WithSessionAndTransaction<List<Store>>() {
			@Override
			public void doAction(Session session) {

				Query<Store> result = session.createQuery("from Store order by name", Store.class);
				setReturnValue(result.list());
			}
		}.run();

	}

	public void removeStore(String name) {

		new WithSessionAndTransaction() {
			@Override
			public void doAction(Session session) {

				Store s = getStoreByName(name, session);
				if (s == null) {
					throw new RuntimeException("Magazin inexistent: " + name);
				}

				session.delete(s);

			}
		}.run();

	}

	public void savePrice(String idA, String idS, int price) {

		new WithSessionAndTransaction() {
			@Override
			public void doAction(Session session) {

				Article article = getArticleByName(idA, session);
				if (article == null) {
					throw new RuntimeException("Articol inexistent: " + idA);
				}

				Store store = getStoreByName(idS, session);
				if (store == null) {
					throw new RuntimeException("Magazin inexistent: " + idS);
				}

				Price pricee = new Price(price, article, store);
				session.save(pricee);

			}

		}.run();

	}

	public List<Price> getSortedPrices() {

		return new WithSessionAndTransaction<List<Price>>() {
			@Override
			public void doAction(Session session) {

				Query<Price> result = session.createQuery("from Price order by price", Price.class);

				setReturnValue(result.list());

			}
		}.run();

	}

	public void removePrice(String nameA, String nameS) {

		new WithSessionAndTransaction() {
			@Override
			public void doAction(Session session) {

				Article article = getArticleByName(nameA, session);
				if (article == null) {
					throw new RuntimeException("Articol inexistent: " + nameA);
				}

				Store store = getStoreByName(nameS, session);
				if (store == null) {
					throw new RuntimeException("Magazin inexistent: " + nameS);
				}

				Query<Price> query = session.createQuery("from Price where article_id = :idA and store_id =: idS",
						Price.class);
				query.setParameter("idA", article.getId());
				query.setParameter("idS", store.getId());

				Price pricee = query.uniqueResult();
				if (pricee == null) {
					throw new RuntimeException("Articolul " + nameA + "nu exista in " + nameS);
				}

				session.delete(pricee);

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

	private Store getStoreByName(String idS, Session session) {
		Query<Store> query2 = session.createQuery("from Store where name = :nameParameter", Store.class);
		query2.setParameter("nameParameter", idS);
		Store store = query2.uniqueResult();
		return store;
	}

	private Article getArticleByName(String idA, Session session) {
		Query<Article> query = session.createQuery("from Article where name = :nameParameter", Article.class);
		query.setParameter("nameParameter", idA);
		Article article = query.uniqueResult();
		return article;
	}

	private List<Article> getArticles(Session session) {
		Query<Article> query = session.createQuery("from Article", Article.class);
		return query.list();
	}

	public List<Article> getAllArticles() {
		return new WithSessionAndTransaction<List<Article>>() {
			@Override
			public void doAction(Session session) {
				setReturnValue(getArticles(session));
			}
		}.run();
	}

	private List<Store> getStores(Session session) {
		Query<Store> query2 = session.createQuery("from Store", Store.class);
		return query2.list();
	}

	public List<Store> getAllStores() {
		return new WithSessionAndTransaction<List<Store>>() {
			@Override
			public void doAction(Session session) {

				setReturnValue(getStores(session));

			}
		}.run();
	}

	private List<Price> getPrices(Session session) {
		Query<Price> query2 = session.createQuery("from Price", Price.class);
		return query2.list();
	}

	public List<Price> getAllPrices() {
		return new WithSessionAndTransaction<List<Price>>() {
			@Override
			public void doAction(Session session) {
				setReturnValue(getPrices(session));
			}
		}.run();
	}
}
