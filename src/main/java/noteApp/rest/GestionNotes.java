package noteApp.rest;

import java.time.LocalDateTime;
import java.util.List;

import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.FormParam;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import noteApp.bll.NoteManager;
import noteApp.bo.Note;

@Path("notes")
public class GestionNotes {

	@GET
	public List<Note> getNotes() {
		return NoteManager.selectAll();
	}

	@GET
	@Path("/{id:\\d+}")
	public Note getNoteById(@PathParam("id") int id) {
		return NoteManager.selectById(id);
	}

	@POST
	public Note insertNote(@FormParam("content") String content) {
		Note noteToInsert = new Note();
		noteToInsert.setContent(content);
		noteToInsert.setCreationDate(LocalDateTime.now());
		return NoteManager.insert(noteToInsert);
	}

	@PUT
	@Path("/{id :  \\d+}")
	public Note updateNote(@PathParam("id") int id, @FormParam("content") String content) {
		Note noteToUpdate = NoteManager.selectById(id);
		noteToUpdate.setContent(content);
		return NoteManager.update(noteToUpdate);
	}

	@DELETE
	@Path("/{id :  \\d+}")
	public Note deleteNoteById(@PathParam("id") int id) {
		return NoteManager.delete(NoteManager.selectById(id));
	}
}
