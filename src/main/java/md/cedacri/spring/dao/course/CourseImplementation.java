package md.cedacri.spring.dao.course;

import md.cedacri.spring.exception.RecordNotFoundException;
import md.cedacri.spring.hibernate.HibernateUtils;
import md.cedacri.spring.model.Course;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CourseImplementation implements CourseDAO {
    private final SessionFactory sessionFactory = HibernateUtils.getSessionFactory();

    @Override
    public void insertCourse(Course course) {
        if (selectCourse(course.getId()) == null) {
            try (Session session = sessionFactory.openSession()) {
                Transaction transaction = session.beginTransaction();
                session.save(course);
                transaction.commit();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        else
        {
            try {
                updateCourse(course.getId(),course);
            } catch (RecordNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public Course selectCourse(int id) {
        Session session = sessionFactory.openSession();
        Transaction transaction = null;
        Course course = null;
        try {
            transaction = session.beginTransaction();
            List<?> courses = session.createQuery("FROM Course WHERE id = " + id).list();
            if (courses != null && !courses.isEmpty()) {
                for (Object obj : courses) {
                    course = (Course) obj;
                }
            }
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
            return course;
        } finally {
            session.close();
        }
        return course;
    }

    @Override
    public List<Course> selectAllCourses() {
        List<Course> courses = new ArrayList<>();
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction;
            transaction = session.beginTransaction();
            courses = session.createQuery("FROM Course", Course.class).list();
            transaction.commit();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return courses;
    }

    @Override
    public void updateCourse(int id, Course course) throws RecordNotFoundException {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction;
            transaction = session.beginTransaction();
            Course currentCourse = session.get(Course.class, id);
            if (currentCourse != null) {
                currentCourse.setName(course.getName());
                currentCourse.setCategory(course.getCategory());
                currentCourse.setUsers(course.getUsers());
                currentCourse.setReviews(course.getReviews());
                currentCourse.setHearts(course.getHearts());
                session.update(currentCourse);
                transaction.commit();
            }
            else
                {
                    throw new RecordNotFoundException("No course record exist for given id");
                }
        } catch (HibernateException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteCourse(int id) throws RecordNotFoundException {
        Transaction transaction;

        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            if (selectCourse(id) != null) {
                Course course = session.get(Course.class, id);
                session.delete(course);
                transaction.commit();
            }
            else
                {
                    throw new RecordNotFoundException("No course record exist for given id");
                }
        } catch (HibernateException e) {
            e.printStackTrace();
        }
    }
}
