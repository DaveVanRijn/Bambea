package nl.editor;

import nl.model.Group;
import nl.service.GroupService;
import java.beans.PropertyEditorSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class GroupEditor extends PropertyEditorSupport {

    @Autowired
    private GroupService groupService;

    // Converts a String group id to a Group (when submitting form)
    @Override
    public void setAsText(String text) {

       Group g = this.groupService.getGroup(Integer.valueOf(text));

        this.setValue(g);
    }
}
