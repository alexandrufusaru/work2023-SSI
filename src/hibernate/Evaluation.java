package hibernate;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Evaluation implements Serializable {
//	@GeneratedValue(generator = "increment")
//	@GenericGenerator(name = "increment", strategy = "increment")
	// private long id;
	private int value;

	@Id
	@ManyToOne
	@JoinColumn(name = "course_id", nullable = false)
	private Course course;

	@Id
	@ManyToOne
	@JoinColumn(name = "trainee_id", nullable = false)
	private Trainee trainee;

	public Evaluation() {

	}

	public Evaluation(int value, Course course, Trainee trainee) {
		super();
		this.value = value;
		this.course = course;
		this.trainee = trainee;
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}

	public Course getCourse() {
		return course;
	}

	public void setCourse(Course course) {
		this.course = course;
	}

	public Trainee getTrainee() {
		return trainee;
	}

	public void setTrainee(Trainee trainee) {
		this.trainee = trainee;
	}
}
