package nl.editor;

import nl.model.Action;
import nl.service.ActionService;
import java.beans.PropertyEditorSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ActionEditor extends PropertyEditorSupport {

    @Autowired
    private ActionService actionService;

    // Converts a String role id to a Roll (when submitting form)
    @Override
    public void setAsText(String text) {
       Action r = this.actionService.getAction(Integer.valueOf(text));

        this.setValue(r);
    }
}


