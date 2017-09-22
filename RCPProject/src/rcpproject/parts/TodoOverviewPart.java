
package rcpproject.parts;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.inject.Named;

import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.core.databinding.beans.BeansObservables;
import org.eclipse.core.databinding.observable.list.IObservableList;
import org.eclipse.core.databinding.observable.map.IObservableMap;
import org.eclipse.core.databinding.property.Properties;
import org.eclipse.e4.core.di.annotations.Optional;
import org.eclipse.e4.ui.di.Focus;
import org.eclipse.e4.ui.di.UIEventTopic;
import org.eclipse.e4.ui.services.EMenuService;
import org.eclipse.e4.ui.services.IServiceConstants;
import org.eclipse.e4.ui.workbench.modeling.ESelectionService;
import org.eclipse.jface.databinding.viewers.ObservableListContentProvider;
import org.eclipse.jface.databinding.viewers.ObservableMapLabelProvider;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TableViewerColumn;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;

import rcpproject.events.MyEventConstants;
import rcpproject.model.ITodoService;
import rcpproject.model.Todo;

public class TodoOverviewPart {
	private DataBindingContext m_bindingContext;
	private Button btnLoadData;
	private TableViewer viewer;

	@Inject
	ESelectionService selectionService;

	@Inject
	ITodoService todoService;

	List<Object> todoList = new ArrayList<>();
	private TableColumn tblclmnId;
	private TableColumn tblclmnDueDate;
	private TableViewerColumn columnDueDate;
	private TableColumn tblclmnDone;
	private TableViewerColumn columnDone;

	@PostConstruct
	public void createControls(Composite parent, EMenuService menuService) {
		for (Todo todo : todoService.getTodos()) {
			todoList.add(todo);
		}
		parent.setLayout(new GridLayout(1, false));
		btnLoadData = new Button(parent, SWT.PUSH);
		btnLoadData.setText("Load Data");
		// more code, e.g. your search box
		// ...
		// ...
		viewer = new TableViewer(parent, SWT.MULTI | SWT.H_SCROLL | SWT.V_SCROLL | SWT.FULL_SELECTION);
		menuService.registerContextMenu(viewer.getControl(), "rcpproject.popupmenu.table");
		viewer.addSelectionChangedListener(new ISelectionChangedListener() {
			public void selectionChanged(SelectionChangedEvent event) {
				IStructuredSelection selection = (IStructuredSelection) viewer.getSelection();
				selectionService.setSelection(selection.getFirstElement());
			}
		});
		Table table = viewer.getTable();
		table.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		table.setHeaderVisible(true);
		table.setLinesVisible(true);

		TableViewerColumn columnID = new TableViewerColumn(viewer, SWT.NONE);
		tblclmnId = columnID.getColumn();
		tblclmnId.setWidth(100);
		tblclmnId.setText(" ID");
		TableViewerColumn column = new TableViewerColumn(viewer, SWT.NONE);
		column.getColumn().setWidth(100);
		column.getColumn().setText("Summary");
		column = new TableViewerColumn(viewer, SWT.NONE);
		column.getColumn().setWidth(100);
		column.getColumn().setText("Description");

		columnDueDate = new TableViewerColumn(viewer, SWT.NONE);
		tblclmnDueDate = columnDueDate.getColumn();
		tblclmnDueDate.setWidth(100);
		tblclmnDueDate.setText("Due Date");

		columnDone = new TableViewerColumn(viewer, SWT.NONE);
		tblclmnDone = columnDone.getColumn();
		tblclmnDone.setWidth(100);
		tblclmnDone.setText("Done");
		// more code for your table, e.g. filter, etc.
		// use data binding to bind the viewer
		// writableList = new WritableList(todoList, Todo.class);
		// ViewerSupport.bind(viewer, writableList,
		// BeanProperties.values(new String[] { Todo.FIELD_SUMMARY,
		// Todo.FIELD_DESCRIPTION }));
		m_bindingContext = initDataBindings();
	}

	@Inject
	@Optional
	public void getNotified(@UIEventTopic(MyEventConstants.TOPIC_TODO_ALLTOPICS) Todo topic) {
		if (viewer != null) {
			todoList.clear();
			todoList.addAll(todoService.getTodos());
			m_bindingContext = initDataBindings();
		}
	}

	@Focus
	private void setFocus() {
		btnLoadData.setFocus();
	}

	protected DataBindingContext initDataBindings() {
		DataBindingContext bindingContext = new DataBindingContext();
		//
		ObservableListContentProvider listContentProvider = new ObservableListContentProvider();
		IObservableMap[] observeMaps = BeansObservables.observeMaps(listContentProvider.getKnownElements(), Todo.class,
				new String[] { "id", "summary", "description", "dueDate", "done" });
		viewer.setLabelProvider(new ObservableMapLabelProvider(observeMaps));
		viewer.setContentProvider(listContentProvider);
		//
		IObservableList selfList = Properties.selfList(Todo.class).observe(todoList);
		viewer.setInput(selfList);
		//
		//
		return bindingContext;
	}

	@Inject
	public void setTodo(@Optional @Named(IServiceConstants.ACTIVE_SELECTION) Todo todo) {
		if (todo != null)
			System.out.println(todo);
	}
}