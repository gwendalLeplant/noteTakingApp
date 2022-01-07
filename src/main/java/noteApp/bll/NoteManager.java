package noteApp.bll;

import java.util.List;

import noteApp.bo.Note;
import noteApp.dal.NotesDAOJDBCImpl;

public class NoteManager {
	public static List<Note> selectAll(){
		return NotesDAOJDBCImpl.selectAll();
	}
	
	public static Note selectById(Integer id){
		return NotesDAOJDBCImpl.selectById(id);
	}
		
	public static Note insert(Note note) {
		return NotesDAOJDBCImpl.insert(note);
	}
	
	public static Note update(Note note) {
		return NotesDAOJDBCImpl.update(note);
	}
	
	public static Note delete(Note note) {
		return NotesDAOJDBCImpl.delete(note);
	}
}
