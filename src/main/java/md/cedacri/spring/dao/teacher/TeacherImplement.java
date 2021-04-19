package md.cedacri.spring.dao.teacher;

import md.cedacri.spring.dao.course.CourseImplementation;
import md.cedacri.spring.exception.RecordNotFoundException;
import md.cedacri.spring.hibernate.HibernateUtils;
import md.cedacri.spring.model.Teacher;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class TeacherImplement implements TeacherDAO {
    private final SessionFactory sessionFactory = HibernateUtils.getSessionFactory();

    @Override
    public void insertTeacher(Teacher teacher) {
        if (selectTeacher(teacher.getId()) == null && validateTeacher(teacher)) {
            try (Session session = sessionFactory.openSession()) {
                Transaction transaction = session.beginTransaction();
                session.save(teacher);
                transaction.commit();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        else
            {
                try {
                    updateTeacher(teacher.getId(),teacher);
                } catch (RecordNotFoundException e) {
                    e.printStackTrace();
                }
            }
    }

    @Override
    public Teacher selectTeacher(int id) {
        Session session = sessionFactory.openSession();
        Transaction transaction = null;
        Teacher teacher = null;
        try {
            transaction = session.beginTransaction();
            List<?> teachers = session.createQuery("FROM Teacher WHERE id = " + id).list();
            if (teachers != null && !teachers.isEmpty()) {
                for (Object obj : teachers) {
                    teacher = (Teacher) obj;
                }
            }
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
            return teacher;
        } finally {
            session.close();
        }
        return teacher;
    }

    @Override
    public List<Teacher> selectAllTeachers() {
        List<Teacher> teachers = new ArrayList<>();
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction;
            transaction = session.beginTransaction();
            teachers = session.createQuery("FROM Teacher", Teacher.class).list();
            transaction.commit();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return teachers;
    }

    @Override
    public void updateTeacher(int id, Teacher teacher) throws RecordNotFoundException {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction;
            transaction = session.beginTransaction();
            Teacher currentTeacher = session.get(Teacher.class, id);
            if (currentTeacher != null) {
                currentTeacher.setAge(teacher.getAge());
                currentTeacher.setExperience(teacher.getExperience());
                currentTeacher.setCourseID(teacher.getCourseID());
                currentTeacher.setFirstName(teacher.getFirstName());
                currentTeacher.setLastName(teacher.getLastName());
                session.update(currentTeacher);
                transaction.commit();
            }
            else {
                throw new RecordNotFoundException("No teacher record exist for given id");
            }
        } catch (HibernateException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteTeacher(int id) throws RecordNotFoundException {
        Transaction transaction;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            if (selectTeacher(id) != null) {
                Teacher teacher = session.get(Teacher.class, id);
                session.delete(teacher);
                transaction.commit();
            }
            else
                {
                    throw new RecordNotFoundException("No teacher record exist for given id");
                }
        } catch (HibernateException e) {
            e.printStackTrace();
        }
    }

    private boolean validateTeacher(Teacher teacher)
    {
        if (teacher.getAge() > 100)
        {
            return false;
        } else if (teacher.getExperience() > teacher.getAge())
        {
            return false;
        }
        else {
            return new CourseImplementation().selectCourse(teacher.getId()).getId() != teacher.getId();
        }
    }
}
