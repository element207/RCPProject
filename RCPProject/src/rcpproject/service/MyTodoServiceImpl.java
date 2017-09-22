package rcpproject.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;

import org.eclipse.e4.core.services.events.IEventBroker;

import rcpproject.events.MyEventConstants;
import rcpproject.model.ITodoService;
import rcpproject.model.Todo;

/**
 * Example service impl, data is not persisted between application restarts
 *
 */
public class MyTodoServiceImpl implements ITodoService {
	private static int current = 1;
	private List<Todo> todos;
	// can now use @Inject in the MyTodoServiceImpl!
	@Inject
	private IEventBroker broker;

	public MyTodoServiceImpl() {
		todos = createInitialModel();
	}

	// always return a new copy of the data
	@Override
	public List<Todo> getTodos() {
		List<Todo> list = new ArrayList<Todo>();
		for (Todo todo : todos) {
			list.add(todo.copy());
		}
		return list;
	}

	// create new or update existing Todo object
	@Override
	public synchronized boolean saveTodo(Todo newTodo) {
		boolean created = false;
		Todo updateTodo = findById(newTodo.getId());
		if (updateTodo == null) {
			created = true;
			updateTodo = new Todo(current++);
			todos.add(updateTodo);
		}
		updateTodo.setSummary(newTodo.getSummary());
		updateTodo.setDescription(newTodo.getDescription());
		updateTodo.setDone(newTodo.isDone());
		updateTodo.setDueDate(newTodo.getDueDate());
		// post created or changed event
		if (created) {
			broker.post(MyEventConstants.TOPIC_TODO_NEW, updateTodo);
		} else {
			broker.post(MyEventConstants.TOPIC_TODO_UPDATE, updateTodo);
		}
		return true;
	}

	@Override
	public Todo getTodo(long id) {
		Todo todo = findById(id);
		if (todo != null) {
			return todo.copy();
		}
		return null;
	}

	@Override
	public boolean deleteTodo(long id) {
		Todo deleteTodo = findById(id);
		if (deleteTodo != null) {
			todos.remove(deleteTodo);
			// post deletion event
			broker.post(MyEventConstants.TOPIC_TODO_DELETE, deleteTodo);
			return true;
		}
		return false;
	}

	// example data, change if you like
	private List<Todo> createInitialModel() {
		List<Todo> list = new ArrayList<Todo>();
		list.add(createTodo("Application model", "Flexible and extensible"));
		list.add(createTodo("DI", "@Inject as programming mode"));
		list.add(createTodo("OSGi", "Services"));
		list.add(createTodo("SWT", "Widgets"));
		list.add(createTodo("JFace", "Especially Viewers!"));
		list.add(createTodo("CSS Styling", "Style your application"));
		list.add(createTodo("Eclipse services", "Selection, model, Part"));
		list.add(createTodo("Renderer", "Different UI toolkit"));
		list.add(createTodo("Compatibility Layer", "Run Eclipse 3.x"));
		return list;
	}

	private Todo createTodo(String summary, String description) {
		return new Todo(current++, summary, description, false, new Date());
	}

	private Todo findById(long id) {
		for (Todo todo : todos) {
			if (id == todo.getId()) {
				return todo;
			}
		}
		return null;
	}
}