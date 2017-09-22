
package rcpproject.parts;

import java.util.Date;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.inject.Named;

import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.core.databinding.beans.BeanProperties;
import org.eclipse.core.databinding.observable.value.IObservableValue;
import org.eclipse.e4.core.di.annotations.Optional;
import org.eclipse.e4.ui.di.UIEventTopic;
import org.eclipse.e4.ui.model.application.ui.basic.MPart;
import org.eclipse.e4.ui.services.IServiceConstants;
import org.eclipse.e4.ui.workbench.UIEvents;
import org.eclipse.e4.ui.workbench.UIEvents.EventTags;
import org.eclipse.jface.databinding.swt.WidgetProperties;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.DateTime;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.osgi.service.event.Event;

import rcpproject.model.Todo;

public class DataBindingPart {
	private DataBindingContext m_bindingContext;
	private Text txtSummary;
	private Text txtDescription;
	private Text txtSummary_1;
	private Text txtDescription_1;

	Todo todo = new Todo(1, "Summary", "Description", true, new Date());
	private DateTime dateTime;
	private Button btnDone;
	private DateTime dateTime_1;
	private Button btnDone_1;

	@Inject
	public DataBindingPart() {

	}

	@PostConstruct
	public void postConstruct(Composite parent) {
		FillLayout fillLayout = (FillLayout) parent.getLayout();
		fillLayout.type = SWT.VERTICAL;

		Composite composite_showdata = new Composite(parent, SWT.NONE);
		composite_showdata.setLayout(new GridLayout(2, false));

		Label lblSummary = new Label(composite_showdata, SWT.NONE);
		lblSummary.setText("Summary");

		txtSummary = new Text(composite_showdata, SWT.BORDER);
		txtSummary.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));

		Label lblDescription = new Label(composite_showdata, SWT.NONE);
		lblDescription.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, false, true, 1, 1));
		lblDescription.setText("Description");

		txtDescription = new Text(composite_showdata, SWT.BORDER);
		txtDescription.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));

		Label lblDueDate = new Label(composite_showdata, SWT.NONE);
		lblDueDate.setText("Due Date");

		dateTime = new DateTime(composite_showdata, SWT.BORDER);
		dateTime.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));
		new Label(composite_showdata, SWT.NONE);

		btnDone = new Button(composite_showdata, SWT.CHECK);
		btnDone.setText("Done");

		Composite composite_modifydata = new Composite(parent, SWT.NONE);
		composite_modifydata.setLayout(new GridLayout(2, false));

		Label lblSummary_1 = new Label(composite_modifydata, SWT.NONE);
		lblSummary_1.setText("Summary");

		txtSummary_1 = new Text(composite_modifydata, SWT.BORDER);
		txtSummary_1.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));

		Label lblDescription_1 = new Label(composite_modifydata, SWT.NONE);
		lblDescription_1.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, false, true, 1, 1));
		lblDescription_1.setText("Description");

		txtDescription_1 = new Text(composite_modifydata, SWT.BORDER);
		txtDescription_1.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));

		Label lblDueDate_1 = new Label(composite_modifydata, SWT.NONE);
		lblDueDate_1.setText("Due Date");

		dateTime_1 = new DateTime(composite_modifydata, SWT.BORDER);
		dateTime_1.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		new Label(composite_modifydata, SWT.NONE);

		btnDone_1 = new Button(composite_modifydata, SWT.CHECK);
		btnDone_1.setText("Done");
		m_bindingContext = initDataBindings();

	}

	@Inject
	public void setTodo(@Optional @Named(IServiceConstants.ACTIVE_SELECTION) Todo todo) {
		if (todo != null && this.m_bindingContext != null) {
			this.todo = todo;

			/*
			 * Ensure that you call the dispose() method on your instance of the
			 * DataBindingContext before creating a new binding. Otherwise the
			 * binding between the user interface widgets and the old Todo
			 * objects stays active. A new Todo will update the user interface
			 * and the user interface binding will update all the old Todo
			 * objects, which is not what you want.
			 */
			m_bindingContext.dispose();
			m_bindingContext = initDataBindings();
			;
		}
	}

	protected DataBindingContext initDataBindings() {
		DataBindingContext bindingContext = new DataBindingContext();
		//
		IObservableValue observeTextTxtSummaryObserveWidget = WidgetProperties.text(SWT.Modify).observe(txtSummary);
		IObservableValue summaryTodoObserveValue = BeanProperties.value("summary").observe(todo);
		bindingContext.bindValue(observeTextTxtSummaryObserveWidget, summaryTodoObserveValue, null, null);
		//
		IObservableValue observeTextTxtDescriptionObserveWidget = WidgetProperties.text(SWT.Modify)
				.observe(txtDescription);
		IObservableValue descriptionTodoObserveValue = BeanProperties.value("description").observe(todo);
		bindingContext.bindValue(observeTextTxtDescriptionObserveWidget, descriptionTodoObserveValue, null, null);
		//
		IObservableValue observeSelectionDateTimeObserveWidget = WidgetProperties.selection().observe(dateTime);
		IObservableValue dueDateTodoObserveValue = BeanProperties.value("dueDate").observe(todo);
		bindingContext.bindValue(observeSelectionDateTimeObserveWidget, dueDateTodoObserveValue, null, null);
		//
		IObservableValue observeSelectionBtnDoneObserveWidget = WidgetProperties.selection().observe(btnDone);
		IObservableValue doneTodoObserveValue = BeanProperties.value("done").observe(todo);
		bindingContext.bindValue(observeSelectionBtnDoneObserveWidget, doneTodoObserveValue, null, null);
		//
		IObservableValue observeTextTxtSummary_1ObserveWidget = WidgetProperties.text(SWT.Modify).observe(txtSummary_1);
		bindingContext.bindValue(observeTextTxtSummary_1ObserveWidget, summaryTodoObserveValue, null, null);
		//
		IObservableValue observeTextTxtDescription_1ObserveWidget = WidgetProperties.text(SWT.Modify)
				.observe(txtDescription_1);
		bindingContext.bindValue(observeTextTxtDescription_1ObserveWidget, descriptionTodoObserveValue, null, null);
		//
		IObservableValue observeSelectionDateTime_1ObserveWidget = WidgetProperties.selection().observe(dateTime_1);
		bindingContext.bindValue(observeSelectionDateTime_1ObserveWidget, dueDateTodoObserveValue, null, null);
		//
		IObservableValue observeSelectionBtnDone_1ObserveWidget = WidgetProperties.selection().observe(btnDone_1);
		bindingContext.bindValue(observeSelectionBtnDone_1ObserveWidget, doneTodoObserveValue, null, null);
		//
		return bindingContext;
	}

	@Inject
	@Optional
	public void partActivation(@UIEventTopic(UIEvents.UILifeCycle.ACTIVATE) Event event) {
		// do something
		Object element = event.getProperty(EventTags.ELEMENT);
		if (!(element instanceof MPart)) {
			return;
		}
		MPart part = (MPart) element;
		System.out.println("Part activited: " + part.getLabel());
	}
}