package nl.editor;

import java.beans.PropertyEditorSupport;
import nl.model.Rule;
import nl.service.RuleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RuleEditor extends PropertyEditorSupport {

    @Autowired
    private RuleService ruleService;

    // Converts a String role id to a Roll (when submitting form)
    @Override
    public void setAsText(String text) {
       Rule r = this.ruleService.getRule(Integer.valueOf(text));

        this.setValue(r);
    }
}


