package minitema2;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Price implements Serializable {
	private int price;

	@Id
	@ManyToOne
	@JoinColumn(name = "article_id", nullable = false)
	private Article article;

	@Id
	@ManyToOne
	@JoinColumn(name = "store_id", nullable = false)
	private Store store;

	public Price() {
	}

	public Price(int price, Article article, Store store) {
		super();
		this.price = price;
		this.article = article;
		this.store = store;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public Article getArticle() {
		return article;
	}

	public void setArticle(Article article) {
		this.article = article;
	}

	public Store getStore() {
		return store;
	}

	public void setStore(Store store) {
		this.store = store;
	}

	@Override
	public String toString() {
		return "Price [price=" + price + ", article=" + article + ", store=" + store + "]";
	}
}
