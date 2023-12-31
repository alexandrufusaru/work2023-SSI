package minitema2;

import java.util.List;
import java.util.Scanner;

public class HibernateMain {
	DatabaseFunctions df = new DatabaseFunctions();

	public static void main(String[] args) {
		HibernateMain hibernateMain = new HibernateMain();
		hibernateMain.run();

	}

	private void run() {

		while (true) {
			showMenu();

			System.out.print("Alegeti optiunea: ");
			Scanner scan = new Scanner(System.in);
			String opt = scan.nextLine();

			switch (opt) {
			case "0":
				System.exit(0);
			case "1":
				addArticle();
				break;
			case "2":
				addStore();
				break;
			case "3":
				addPrice();
				break;
			case "4":
				df.showArticles();
				break;
			case "5":
				df.showStores();
				break;
			case "6":
				df.showPrices();
				break;
			case "7":
				sortedArticles();
				break;
			case "8":
				sortedStores();
				break;
			case "9":
				sortedPrices();
				break;
			case "10":
				deleteArticle();
				break;
			case "11":
				deleteStore();
				break;
			case "12":
				deletePrice();
				break;
			default:
				System.out.println("Optiune gresita!");
				break;
			}
		}
	}

	private void showMenu() {
		System.out.println("-------------MENIU-------------");
		System.out.println("0. Iesire");
		System.out.println("1. Adaugare articol");
		System.out.println("2. Adaugare magazin");
		System.out.println("3. Adaugare pret");
		System.out.println("4. Afisare articole");
		System.out.println("5. Afisare magazine");
		System.out.println("6. Afisare preturi");
		System.out.println("7. Sortare articole");
		System.out.println("8. Sortare magazine");
		System.out.println("9. Sortare preturi");
		System.out.println("10. Stergere articol");
		System.out.println("11. Stergere magazin");
		System.out.println("12. Stergere pret");

	}

	private void addArticle() {
		System.out.println("Nume articol: ");
		Scanner scan = new Scanner(System.in);
		String nameA = scan.nextLine();

		df.saveArticle(nameA);
	}

	private void sortedArticles() {
		List<Article> sortedArticles = df.getSortedArticles();

		for (Article a : sortedArticles)
			System.out.println(a.getName());
	}

	private void deleteArticle() {
		System.out.println("Nume articol: ");
		Scanner scan = new Scanner(System.in);
		String nameA = scan.nextLine();

		df.removeArticle(nameA);
	}

	private void addStore() {
		System.out.println("Nume magazin: ");
		Scanner scan = new Scanner(System.in);
		String nameS = scan.nextLine();

		df.saveStore(nameS);
	}

	private void sortedStores() {
		List<Store> sortedStores = df.getSortedStores();

		for (Store a : sortedStores)
			System.out.println(a.getName());
	}

	private void deleteStore() {
		System.out.println("Nume magazin: ");
		Scanner scan = new Scanner(System.in);
		String nameS = scan.nextLine();

		df.removeStore(nameS);
	}

	private void addPrice() {
		Scanner scan = new Scanner(System.in);
		System.out.println("Nume articol: ");
		String idA = scan.nextLine();
		System.out.println("Nume magazin: ");
		String idS = scan.nextLine();
		System.out.println("Pret: ");
		int pr = scan.nextInt();

		df.savePrice(idA, idS, pr);
	}

	private void sortedPrices() {
		List<Price> sortedPrices = df.getSortedPrices();

		for (Price a : sortedPrices)
			System.out.println(a);
	}

	private void deletePrice() {
		System.out.println("Nume articol: ");
		Scanner scan = new Scanner(System.in);
		String nameA = scan.nextLine();
		System.out.println("Nume magazin: ");
		String nameS = scan.nextLine();

		df.removePrice(nameA, nameS);
	}
}
