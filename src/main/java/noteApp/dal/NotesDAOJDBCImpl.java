package noteApp.dal;

import java.util.List;

import javax.persistence.RollbackException;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import noteApp.bo.Note;

public class NotesDAOJDBCImpl {
	private static Session session;

	static {
		StandardServiceRegistry ssr = new StandardServiceRegistryBuilder().configure("hibernate.cfg.xml").build();
		Metadata meta = new MetadataSources(ssr).getMetadataBuilder().build();
		SessionFactory factory = meta.getSessionFactoryBuilder().build();

		session = factory.openSession();
	}

	public static List<Note> selectAll() {
		return session.createQuery("from Note n ORDER BY n.creationDate DESC", Note.class).list();
	}

	public static Note selectById(Integer id) {
		return session.find(Note.class, id);
	}

	public static Note insert(Note note) {
		Transaction t = session.beginTransaction();
		try {
			session.save(note);
			session.flush();
			t.commit();
			return note;
		} catch (RollbackException rbe) {
			t.rollback();
			System.err.println(rbe.getMessage());
			return null;
		}
	}

	public static Note update(Note note) {
		Transaction t = session.beginTransaction();
		try {
			session.update(note);
			session.flush();
			t.commit();
			return note;
		} catch (RollbackException rbe) {
			t.rollback();
			System.err.println(rbe.getMessage());
			return null;
		}
	}
	
	public static Note delete(Note note) {
		Transaction t = session.beginTransaction();
		try {
			session.delete(note);
			session.flush();
			t.commit();
			return note;
		} catch (RollbackException rbe) {
			t.rollback();
			System.err.println(rbe.getMessage());
			return null;
		}
	}
}
