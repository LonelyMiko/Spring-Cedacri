package md.cedacri.spring.dao.student;


import md.cedacri.spring.hibernate.HibernateUtils;
import md.cedacri.spring.model.Student;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;

import java.util.*;


@Repository("student")
public class StudentImplement implements StudentDAO {

    private final SessionFactory sessionFactory = HibernateUtils.getSessionFactory();


    public StudentImplement() {

        UUID id = UUID.randomUUID();

        Student student = new Student(id, 22, "William", "Siqueira", 0);

    }

    @Override
    public void insertStudent(Student student) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            session.save(student);
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public Student selectStudent(UUID id) {
        Session session = sessionFactory.openSession();
        Transaction transaction = null;
        Student student = null;
        try {
            transaction = session.beginTransaction();
            List<?> students = session.createQuery("FROM student WHERE id = " + id).list();
            if (students != null && !students.isEmpty()) {
                for (Object obj : students) {
                    student = (Student) obj;
                }
            }
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
            return student;
        } finally {
            session.close();
        }
        return student;
    }

    @Override
    public List<Student> selectAllStudents() {
        List<Student> students = new ArrayList<>();
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction;
            transaction = session.beginTransaction();
            students = session.createQuery("FROM Student", Student.class).list();
            transaction.commit();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return students;
    }

    @Override
    public void updateStudent(UUID id, Student student) {

        try (Session session = sessionFactory.openSession()) {
            Transaction transaction;
            transaction = session.beginTransaction();
            Student currentStudent = session.get(Student.class, id);
            currentStudent = student;
            session.update(currentStudent);
            transaction.commit();
        } catch (HibernateException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteStudent(UUID id) {
        Transaction transaction;

        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            Student student = session.get(Student.class, id);
            session.delete(student);
            transaction.commit();
        } catch (HibernateException e) {
            e.printStackTrace();
        }
    }
}
