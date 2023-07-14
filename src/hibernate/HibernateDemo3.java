package hibernate;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import utils.WithSessionAndTransaction;

public class HibernateDemo3 {
	public static void main(String[] args) throws Exception {
		HibernateDemo3 demo = new HibernateDemo3();
		demo.run();
	}

	private SessionFactory sessionFactory;

	private void run() throws Exception {
		saveEntities();
		updateEntity();
		deleteEntity();
		queryEntities();
		queryOneEntity();
	}

	private void deleteEntity() {
		new WithSessionAndTransaction() {
			@Override
			public void doAction(Session session) {
				TypedQuery<Course> query = session.createQuery("from Course where name = :nameParameter", Course.class);
				query.setParameter("nameParameter", "Sport");

				Course result = query.getSingleResult();
				session.delete(result);
			}
		}.run();
	}

	private void updateEntity() {
		new WithSessionAndTransaction() {
			@Override
			public void doAction(Session session) {
				EntityManager entityManager = sessionFactory.createEntityManager();

				entityManager.getTransaction().begin();

				TypedQuery<Course> query = entityManager.createQuery("from Course where name = :nameParameter",
						Course.class);
				query.setParameter("nameParameter", "Romana");

				Course result = query.getSingleResult();
				result.setName("Limba Romana");

				entityManager.getTransaction().commit();
				entityManager.close();
			}
		}.run();
	}

	private void queryOneEntity() {
		new WithSessionAndTransaction() {
			@Override
			public void doAction(Session session) {
				EntityManager entityManager = sessionFactory.createEntityManager();

				TypedQuery<Course> query = entityManager.createQuery("from Course where name = :nameParameter",
						Course.class);
				query.setParameter("nameParameter", "Matematica");

				Course result = query.getSingleResult();

				System.out.println(result);

				entityManager.close();
			}
		}.run();
	}

	private void queryEntities() {
		new WithSessionAndTransaction() {
			@Override
			public void doAction(Session session) {
				EntityManager entityManager = sessionFactory.createEntityManager();
				List<Course> result = entityManager.createQuery("from Course", Course.class).getResultList();

				for (Course c : result) {
					System.out.println(c);
				}

			}
		}.run();
	}

	private void saveEntities() {
		new WithSessionAndTransaction() {
			@Override
			public void doAction(Session session) {
				EntityManager entityManager = sessionFactory.createEntityManager();

				entityManager.getTransaction().begin();

				Trainee trainee1 = new Trainee("Ghita", 20);
				entityManager.persist(trainee1);
				Trainee trainee2 = new Trainee("Ana", 30);
				entityManager.persist(trainee2);

				Course course1 = new Course("Romana");
				entityManager.persist(course1);
				Course course2 = new Course("Matematica");
				entityManager.persist(course2);
				Course course3 = new Course("Sport");
				entityManager.persist(course3);

				trainee1.getCourses().add(course2);
				trainee2.getCourses().add(course1);

				Evaluation ev1 = new Evaluation(10, course1, trainee1);
				Evaluation ev2 = new Evaluation(9, course2, trainee2);
				entityManager.persist(ev1);
				entityManager.persist(ev2);

				entityManager.getTransaction().commit();
				entityManager.close();
			}

		}.run();
	}
}
